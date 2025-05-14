package net.mcreator.reignmod.claim.chunk;

import net.mcreator.reignmod.claim.capital.CapitalClaimManager;
import net.mcreator.reignmod.claim.capital.CapitalClaimSavedData;
import net.mcreator.reignmod.house.Domain;
import net.mcreator.reignmod.house.House;
import net.mcreator.reignmod.house.HouseManager;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;

import java.util.Optional;
import java.util.UUID;

/**
 * Утилитный класс, упрощающий работу с ChunkClaimData.
 * - Проверяет свободность области (21×21),
 * — Создаёт/удаляет приваты,
 * — Возвращает владельца чанка.
 */
public class ChunkClaimManager {



    /**
     * Проверяет, свободна ли вся квадратная область 21×21 чанков
     * (радиус 10 от центра (centerX, centerZ)).
     */
    private static boolean isAreaCompletelyFree(int centerX, int centerY, int centerZ, ClaimType claimType) {
        int radius = (claimType == ClaimType.CAPITAL ? ChunkClaimConstants.CAPITAL_CLAIM_RADIUS : ChunkClaimConstants.CLAIM_RADIUS);
        var ch = new ChunkPos(new BlockPos(centerX, centerY, centerZ));
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                long chunkId = ChunkPos.asLong(ch.x + dx, ch.z + dz);
                var existing = ChunkClaimSavedData.getInstance().getClaimByChunk(chunkId);
                if (existing.isPresent()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Создаём новый приват (ClaimData),
     * если вся территория свободна (никаких пересечений).
     */
    public static boolean createClaim(ServerPlayer sv, ClaimType claimType, int centerX, int centerY, int centerZ) {
        if (claimType == ClaimType.CAPITAL) {
            return false;
        }
        if (!isAreaCompletelyFree(centerX, centerY, centerZ, claimType)) {
            sv.displayClientMessage(Component.translatable("chunkclaim.add.fail.occupied"), true);
            return false;
        }

        String claimId, ownerName;
        if (claimType == ClaimType.DOMAIN) {
            var foundDomain = HouseManager.getDomainByKnightUUID(sv.getStringUUID());
            var foundHouse = HouseManager.getHouseByLordUUID(foundDomain.getLordUUID());

            claimId = foundDomain.getClaimId();
            ownerName = foundHouse.getHouseTitleWithColor() + ": " + foundDomain.getDomainTitle().getString();
        } else {
            var foundHouse = HouseManager.getHouseByLordUUID(sv.getStringUUID());
            var foundDomain = foundHouse.getDomains().get(sv.getStringUUID());

            claimId = foundHouse.getClaimId();
            ownerName = foundHouse.getHouseTitleWithColor() + ": " + foundDomain.getDomainTitle().getString();
        }

        if (claimId != null) {
            sv.displayClientMessage(Component.translatable("chunkclaim.add.fail.already_own"), true);
            return false;
        }

        // Формируем ClaimData
        claimId = UUID.randomUUID().toString();
        ClaimData claimData = new ClaimData(claimId, sv.getStringUUID(), ownerName, claimType, centerX, centerZ);

        // Заполняем все чанки
        int radius = ChunkClaimConstants.CLAIM_RADIUS;
        var ch = new ChunkPos(new BlockPos(centerX, centerY, centerZ));
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                long chunkId = ChunkPos.asLong(ch.x + dx, ch.z + dz);
                claimData.getClaimedChunks().add(chunkId);
            }
        }

        // Добавляем в хранилище
        ChunkClaimSavedData.getInstance().addClaim(claimData);
        if (claimType == ClaimType.DOMAIN) {
            Domain domain = HouseManager.getDomainByKnightUUID(sv.getStringUUID());
            domain.setClaimId(claimId);
            domain.setDomainFoundationCoordinates(new int[]{centerX, centerY, centerZ});
        } else {
            HouseManager.getHouseByLordUUID(sv.getStringUUID()).setClaimId(claimId);
        }

        sv.displayClientMessage(Component.translatable("chunkclaim.add.success"), true);
        return true;
    }

    public static boolean createCapitalClaim(int centerX, int centerY, int centerZ) {
        if (!isAreaCompletelyFree(centerX, centerY, centerZ, ClaimType.CAPITAL)) {
            return false;
        }

        String claimId = CapitalClaimSavedData.getChunkClaimId();
        String ownerName = Component.translatable("chunkclaim.capital.name").getString();

        if (claimId != null) {
            return false;
        }

        // Формируем ClaimData
        claimId = UUID.randomUUID().toString();
        ClaimData claimData = new ClaimData(claimId, claimId, ownerName, ClaimType.CAPITAL, centerX, centerZ);

        // Заполняем все чанки
        int radius = ChunkClaimConstants.CAPITAL_CLAIM_RADIUS;
        var ch = new ChunkPos(new BlockPos(centerX, centerY, centerZ));
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                long chunkId = ChunkPos.asLong(ch.x + dx, ch.z + dz);
                claimData.getClaimedChunks().add(chunkId);
            }
        }

        // Добавляем в хранилище
        ChunkClaimSavedData.getInstance().addClaim(claimData);
        CapitalClaimSavedData.setChunkClaimId(claimId);
        return true;
    }

    /**
     * Удаляем приват по claimId и логируем игроку.
     */
    public static void removeClaim(ServerPlayer sv, String claimId) {
        if (ChunkClaimSavedData.getInstance().getClaim(claimId).isPresent()) {
            removeClaim(claimId);
            sv.displayClientMessage(Component.translatable("chunkclaim.remove.success"), true);
        }
        sv.displayClientMessage(Component.translatable("chunkclaim.remove.not_found"), true);
    }

    /**
     * Удаляем приват по claimId.
     */
    public static void removeClaim(String claimId) {
        if (ChunkClaimSavedData.getInstance().getClaim(claimId).isPresent()) {
            String ownerId = ChunkClaimSavedData.getInstance().getClaim(claimId).get().getOwnerId();
            ClaimType type = ChunkClaimSavedData.getInstance().getClaim(claimId).get().getClaimType();
            if (type == ClaimType.DOMAIN) {
                HouseManager.getDomainByKnightUUID(ownerId).setClaimId(null);
            } else if (type == ClaimType.HOUSE) {
                HouseManager.getHouseByLordUUID(ownerId).setClaimId(null);
            } else {
                CapitalClaimSavedData.setChunkClaimId(null);
            }
            ChunkClaimSavedData.getInstance().removeClaim(claimId);
        }
    }

    /**
     * Удаляем приват по координатам.
     */
    public static void removeClaim(ServerPlayer sp) {
        var claim = getClaimIdByChunk(new ChunkPos(sp.getOnPos()));
        if (claim.isPresent()) {
            removeClaim(claim.get());
            sp.displayClientMessage(Component.translatable("chunkclaim.remove.success"), false);
        }
        sp.displayClientMessage(Component.translatable("chunkclaim.remove.not_found"), false);
    }

    /**
     * Возвращает владельца чанка (x, z) если есть, иначе пусто.
     */
    public static Optional<String> getChunkOwner(int chunkX, int chunkZ) {
        return ChunkClaimSavedData.getInstance()
                .getClaimByChunk(chunkX, chunkZ)
                .map(ClaimData::getOwnerId);
    }

    /**
     * Возвращает имя владельца чанка (x, z) если есть, иначе пусто.
     */
    public static Optional<String> getChunkOwnerName(ChunkPos chunkPos) {
        return ChunkClaimSavedData.getInstance()
                .getClaimByChunk(chunkPos.x, chunkPos.z).map(ClaimData::getOwnerName);
    }

    /**
     * Проверяем, владеет ли ownerId этим чанком.
     */
    public static boolean isOwnerOfChunk(String ownerId, int chunkX, int chunkZ) {
        return getChunkOwner(chunkX, chunkZ)
                .filter(o -> o.equals(ownerId))
                .isPresent();
    }

    /**
     * Получаем claimId для чанка (если приватен).
     */
    public static Optional<String> getClaimIdByChunk(int chunkX, int chunkZ) {
        return ChunkClaimSavedData.getInstance()
                .getClaimByChunk(chunkX, chunkZ)
                .map(ClaimData::getClaimId);
    }

    public static Optional<String> getClaimIdByChunk(ChunkPos cp) {
        return getClaimIdByChunk(cp.x, cp.z);
    }

    public static boolean hasPermission(ServerPlayer player, BlockPos pos) {
        return hasPermission(player, new ChunkPos(pos));
    }

    public static boolean hasPermission(ServerPlayer player, ChunkPos pos) {
        if (!player.level().dimension().equals(Level.OVERWORLD)) {
            return true;
        }
        if (player.gameMode.isCreative()) {
            return true;
        }

        var claimOpt = ChunkClaimSavedData.getInstance().getClaimByChunk(pos.x, pos.z);
        if (claimOpt.isEmpty()) {
            return true;
        }

        var claimData = claimOpt.get();
        if (claimData.getClaimType() == ClaimType.HOUSE) {
            // HOUSE
            House chunkHouse = HouseManager.getHouseByLordUUID(claimData.getOwnerId());
            if (!chunkHouse.isNull()) {
                return chunkHouse.getDomains().containsKey(player.getStringUUID()) ||
                        chunkHouse.getDomains().get(chunkHouse.getLordUUID()).getPlayers().contains(player.getStringUUID()) ||
                        chunkHouse.isIndirectVassalsBeTrusted() && chunkHouse.getPlayers().contains(player.getStringUUID());
            }
        } else if (claimData.getClaimType() == ClaimType.DOMAIN){
            // DOMAIN
            Domain domain = HouseManager.getDomainByKnightUUID(claimData.getOwnerId());
            House chunkHouse = HouseManager.getHouseByLordUUID(domain.getLordUUID());
            if (!domain.isNull()) {
                if (!chunkHouse.isNull() && chunkHouse.isIndirectVassalsBeTrusted() && chunkHouse.getPlayers().contains(player.getStringUUID())) {
                    return true;
                }
                return domain.getPlayers().contains(player.getStringUUID()) || domain.getLordUUID().equals(player.getStringUUID());
            }
        } else {
            return true;
        }

        return false;
    }
}
