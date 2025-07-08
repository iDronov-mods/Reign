package net.mcreator.reignmod.claim.chunk;

import net.mcreator.reignmod.house.House;
import net.mcreator.reignmod.house.HouseManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.ChunkPos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jline.utils.Log;

import java.util.*;

public class Expansion {
    // ——— Константы —————————————————————————————————————————————
    private static final double EXPANSION_CHANCE_NORMAL = 0.1;
    private static final double EXPANSION_CHANCE_RIVER  = 0.001;

    private static final double MIN_COST_PER_CHUNK = 0.25;
    private static final double MAX_COST_PER_CHUNK = 1.0;

    private static final int MAX_HOUSE_CHUNKS  = 8192;
    private static final int MAX_DOMAIN_CHUNKS = 8192;
    private static final int MIN_STANDARD_CHUNKS = ChunkClaimConstants.CLAIM_DIAMETER * ChunkClaimConstants.CLAIM_DIAMETER;

    private static final Logger LOGGER = LogManager.getLogger();

    // ——— Публичный API ————————————————————————————————————————
    public static void expandAll() {
        var saved = ChunkClaimSavedData.getInstance();
        for (String claimID : saved.getClaims().keySet()) {
            if (saved.getClaim(claimID).isEmpty() || saved.getClaim(claimID).get().getClaimType() == ClaimType.CAPITAL) continue;
            LOGGER.info("Expanding territory {}", saved.getClaim(claimID).get().getOwnerName());
            expandTerritory(claimID);
            LOGGER.info("Expansion complete for territory {}", saved.getClaim(claimID).get().getOwnerName());
            saved.setDirty();
        }
    }

    // ——— Основной рабочий метод для одной территории ————————————
    private static void expandTerritory(String claimID) {
        Optional<ClaimData> territory = ChunkClaimSavedData.getInstance().getClaim(claimID);
        if (territory.isEmpty() || territory.get().getClaimType() == ClaimType.CAPITAL) {
            return;
        }
        ServerLevel level = ChunkClaimSavedData.getServerInstance();

        // 1) Первичный проход: найти кандидатов на расширение
        Set<Long> candidates = collectExpansionCandidates(level, territory.get());
        LOGGER.info("Expansion candidates: {}", candidates.size());

        if (candidates.isEmpty()) return;

        // 2) Рассчитать цену за чанк
        double cost = calculateCostPerChunk(territory.get());
        LOGGER.info("Expansion cost per chunk: {}", cost);

        // 3) Получить данные стратега
        BlockEntity strat = getStrategyData(level, territory.get());
        double attackCoins = strat.getPersistentData().getDouble("attack_coins");
        LOGGER.info("Attack coins: {}", attackCoins);

        // 4) Применить расширение/защиту
        double remainingCoins = applyExpansion(level, territory.get(), candidates, cost, attackCoins);
        LOGGER.info("Remaining attack coins: {}", remainingCoins);

        // 5) Сохранить обновлённое число монет в NBT
        strat.getPersistentData().putDouble("attack_coins", remainingCoins);
        strat.setChanged();
    }

    // ——— Шаг 1: найти кандидатов и обновить outerChunks —————————

    private static final int[][] CARDINAL_DIRS = {
            { 1,  0},  // +X (восток)
            {-1,  0},  // -X (запад)
            { 0,  1},  // +Z (юг)
            { 0, -1}   // -Z (север)
    };

    private static Set<Long> collectExpansionCandidates(ServerLevel level, ClaimData territory) {
        Set<Long> newCandidates = new LinkedHashSet<>();
        // делаем копию, чтобы безопасно удалять из оригинала
        Set<Long> outer = new HashSet<>(territory.getOuterChunks());
        LOGGER.info("OuterChunks amount: {}", outer.size());

        for (long chunkId : outer) {
            ChunkPos cp = new ChunkPos(chunkId);
            boolean allNeighborsOwned = true;

            // обход только 4 соседних чанков
            for (int[] dir : CARDINAL_DIRS) {
                int dx = dir[0], dz = dir[1];
                ChunkPos neighbor = new ChunkPos(cp.x + dx, cp.z + dz);

                if (!territory.containsChunk(neighbor.toLong())) {
                    allNeighborsOwned = false;
                    if (isExpandableNeighbor(level, neighbor)) {
                        newCandidates.add(neighbor.toLong());
                    }
                }
            }

            if (allNeighborsOwned) {
                territory.removeOuterChunk(chunkId);
            }
        }

        return newCandidates;
    }

    // ——— Проверка, что соседний чанк годится под расширение ————————
    private static boolean isExpandableNeighbor(ServerLevel level, ChunkPos pos) {
        BlockPos center = new BlockPos(pos.getMinBlockX() + 8, 0, pos.getMinBlockZ() + 8);
        Holder<Biome> holder = level.getBiome(center);

        if (holder.is(BiomeTags.IS_OCEAN) || holder.is(BiomeTags.IS_DEEP_OCEAN)) {
            return false;
        }

        var claimId = ChunkClaimManager.getClaimIdByChunk(pos)
                .flatMap(id -> ChunkClaimSavedData.getInstance().getClaim(id))
                .map(ClaimData::getClaimType)
                .orElse(null);

        if (claimId == ClaimType.CAPITAL) return false;

        double chance = (holder.is(BiomeTags.IS_RIVER) ? EXPANSION_CHANCE_RIVER : EXPANSION_CHANCE_NORMAL);
        return Math.random() < chance;
    }

    // ——— Шаг 2: рассчитать цену за чанк с учётом заполненности ——————
    private static double calculateCostPerChunk(ClaimData territory) {
        int currentSize = territory.getClaimedChunks().size();
        int maxSize = territory.getClaimType() == ClaimType.HOUSE ? MAX_HOUSE_CHUNKS : MAX_DOMAIN_CHUNKS;

        double ratio     = (double) currentSize / maxSize;
        double minRatio  = (double) MIN_STANDARD_CHUNKS / maxSize;
        double factor    = (ratio - minRatio) / (1 - minRatio);

        return MIN_COST_PER_CHUNK + factor * (MAX_COST_PER_CHUNK - MIN_COST_PER_CHUNK);
    }

    // ——— Утилита: получить BlockEntity и NBT стратегической точки ————
    private static BlockEntity getStrategyData(ServerLevel level, ClaimData territory) {
        int[] coords;
        LOGGER.info("Territory type: {}", territory.getClaimType() == ClaimType.HOUSE ? "HOUSE" : "DOMAIN");
        if (territory.getClaimType() == ClaimType.HOUSE) {
            coords = HouseManager.getHouseByLordUUID(territory.getOwnerId()).getHouseStrategyBlockCoordinates();
        } else {
            coords = HouseManager.getDomainByKnightUUID(territory.getOwnerId()).getDomainStrategyBlockCoordinates();
        }
        LOGGER.info("Strategy block coordinates: {}", Arrays.toString(coords));
        BlockPos pos = new BlockPos(coords[0], coords[1], coords[2]);
        BlockEntity be = level.getBlockEntity(pos);
        LOGGER.info("Strategy block entity: {}", be != null ? "EXISTS" : "DOES NOT EXIST");

        return be;
    }

    // ——— Шаг 3–4: попытаться захватить каждый кандидат —————————————
    private static double applyExpansion(ServerLevel level, ClaimData attacker,
                                         Set<Long> candidates, double cost, double attackCoins) {
        for (long cid : candidates) {
            if (attackCoins < cost) break;

            Optional<ClaimData> defenderOpt = ChunkClaimSavedData.getInstance().getClaimByChunk(cid);
            if (defenderOpt.isEmpty()) {
                // ничейный — просто захватываем
                attackCoins = claimChunk(attacker, cid, cost, attackCoins);
            } else {
                ClaimData defender = defenderOpt.get();

                House attackerHouse = HouseManager.getHouseBySuzerainUUID(attacker.getOwnerId());
                House defenderHouse = HouseManager.getHouseBySuzerainUUID(defender.getOwnerId());
                if (attackerHouse != null && attackerHouse.equals(defenderHouse)) {
                    continue;
                }

                attackCoins = fightOverChunk(level, attacker, defender, cid, cost, attackCoins);
            }
        }
        return attackCoins;
    }

    private static double claimChunk(ClaimData territory, long cid, double cost, double attackCoins) {
        ChunkClaimSavedData.getInstance().addChunk(territory.getClaimId(), cid);
        return attackCoins - cost;
    }

    private static double fightOverChunk(ServerLevel level, ClaimData attacker, ClaimData defender, long cid, double cost, double attackCoins) {
        // достаём defenceCoins защитника
        BlockEntity defStrat = getStrategyData(level, defender);
        double defCoins = defStrat.getPersistentData().getDouble("defence_coins");

        if (defCoins >= cost) {
            // защитник отбивает атаку
            attackCoins -= cost;
            defStrat.getPersistentData().putDouble("defence_coins", defCoins - cost);
            defStrat.setChanged();
        } else {
            // защитник не смог заплатить — атакующий захватывает чанк
            attackCoins -= cost;

            // 1) убираем у защитника
            ChunkClaimSavedData.getInstance().removeChunk(defender.getClaimId(), cid);

            // 2) добавляем атакующему
            ChunkClaimSavedData.getInstance().addChunk(defender.getClaimId(), cid);
        }
        return attackCoins;
    }

    // ——— Вспомогательный класс для хранения данных стратега —————————
    private record StrategyData(BlockEntity be, double attackCoins) {
    }
}
