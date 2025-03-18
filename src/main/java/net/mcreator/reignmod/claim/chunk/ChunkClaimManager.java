package net.mcreator.reignmod.claim.chunk;

import net.mcreator.reignmod.house.Domain;
import net.mcreator.reignmod.house.House;
import net.mcreator.reignmod.house.HouseManager;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;

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
    public static boolean isAreaCompletelyFree(int centerX, int centerY, int centerZ) {
        int radius = ChunkClaimConstants.CLAIM_RADIUS; // 10
        var ch = new ChunkPos(new BlockPos(centerX, centerY, centerZ));
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                long chunkId = ChunkPos.asLong(ch.x + dx, ch.z + dz);
                var existing = ChunkClaimSavedData.getInstance().getClaimByChunk(chunkId);
                if (existing.isPresent()) {
                    return false; // Уже занято
                }
            }
        }
        return true;
    }

    /**
     * Создаём новый приват (ClaimData) размером 21×21,
     * если вся территория свободна (никаких пересечений).
     */
    public static Optional<String> createClaimIfFree(String ownerId, ClaimType claimType, int centerX, int centerY, int centerZ) {

        // Формируем ClaimData
        String claimId = UUID.randomUUID().toString();
        ClaimData claimData = new ClaimData(claimId, ownerId, claimType, centerX, centerZ);

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
        ChunkClaimSavedData.getServerInstance().getServer().sendSystemMessage(Component.literal("URAURAURAURA, сработало! ;)"));

        return Optional.of(claimId);
    }

    /**
     * Удаляем приват по claimId (расприват).
     */
    public static void removeClaim(String claimId) {
        ChunkClaimSavedData.getInstance().removeClaim(claimId);
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
                return chunkHouse.getPlayers().contains(player.getStringUUID());
            }
        } else {
            // DOMAIN
            Domain domain = HouseManager.getDomainByKnightUUID(claimData.getOwnerId());
            if (!domain.isNull()) {
                return domain.getPlayers().contains(player.getStringUUID());
            }
        }

        return false;
    }
}
