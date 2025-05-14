package net.mcreator.reignmod.networking;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

/**
 * Клиентские данные, синхронизируемые с сервера.
 * Тут и префиксы игроков, и (chunkId -> ownerId) карта.
 */
public class ClientPlayerData {

    // ---------- ПРЕФИКСЫ ИГРОКОВ ----------
    private static Map<String, String> playersPrefixes = new HashMap<>();


    // ---------- КЛИЕНТСКАЯ ДАТА ДЛЯ ЧАНК ПРИВАТОВ ----------
    private static int lastKnownChunkX = Integer.MIN_VALUE;
    private static int lastKnownChunkZ = Integer.MIN_VALUE;
    private static boolean lastKnownChunkAccess = true;

    // ---------- КЛИЕНТСКАЯ ДАТА ДЛЯ СТОЛИЧНЫХ ПРИВАТОВ ----------
    private static BlockPos lastKnownBlock = new BlockPos(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    private static boolean lastKnownBlockAccess = true;



    public static Map<String, String> getPlayersPrefixes() {
        return playersPrefixes;
    }

    public static void setPlayersPrefixes(Map<String, String> playersPrefixes) {
        ClientPlayerData.playersPrefixes = playersPrefixes;
    }



    public static void setLastKnownChunk(int chunkX, int chunkZ, boolean canBreak) {
        lastKnownChunkX = chunkX;
        lastKnownChunkZ = chunkZ;
        lastKnownChunkAccess = canBreak;
    }


    public static void setLastKnownChunk() {
        lastKnownChunkX = Integer.MIN_VALUE;
        lastKnownChunkZ = Integer.MIN_VALUE;
        lastKnownChunkAccess = true;
    }

    public static boolean isLastKnownChunk(int chunkX, int chunkZ) {
        return lastKnownChunkX == chunkX || lastKnownChunkZ == chunkZ;
    }

    public static boolean isLastKnownChunkEmpty() {
        return lastKnownChunkX == Integer.MIN_VALUE || lastKnownChunkZ == Integer.MIN_VALUE;
    }

    public static int getLastKnownChunkX() {
        return lastKnownChunkX;
    }

    public static int getLastKnownChunkZ() {
        return lastKnownChunkZ;
    }

    public static boolean isLastKnownChunkAvailable() {
        return lastKnownChunkAccess;
    }



    public static void setLastKnownBlock(BlockPos blockPos, boolean canBreak) {
        lastKnownBlock = blockPos;
        lastKnownBlockAccess = canBreak;
    }

    public static void setLastKnownBlock() {
        lastKnownBlock = BlockPos.ZERO;
        lastKnownBlockAccess = true;
    }

    public static boolean isLastKnownBlock(BlockPos blockPos) {
        return lastKnownBlock.equals(blockPos);
    }

    public static boolean isLastKnownBlockEmpty() {
        return lastKnownBlock.equals(BlockPos.ZERO);
    }

    public static BlockPos getLastKnownBlock() {
        return lastKnownBlock;
    }

    public static boolean isLastKnownBlockAvailable() {
        return lastKnownBlockAccess;
    }


    // ---------- КЛИЕНТСКАЯ ДАТА ДЛЯ СТОЛИЧНЫХ ПРИВАТОВ ----------
    private static BlockPos lastKnownDoorPos = BlockPos.ZERO;
    private static boolean lastKnownDoorAccess = true;

    /** Синхронизирует на клиенте результат проверки двери */
    public static void setLastKnownDoor(BlockPos pos, boolean canOpen) {
        lastKnownDoorPos = pos;
        lastKnownDoorAccess = canOpen;
    }

    /** Сбрасывает данные (пока нет информации) */
    public static void setLastKnownDoor() {
        lastKnownDoorPos = BlockPos.ZERO;
        lastKnownDoorAccess = true;
    }

    /** Совпадает ли запрошенная позиция с последней сохранённой */
    public static boolean isLastKnownDoor(BlockPos pos) {
        return lastKnownDoorPos.equals(pos);
    }

    /** Есть ли у нас вообще данные по двери */
    public static boolean isLastKnownDoorEmpty() {
        return lastKnownDoorPos.equals(BlockPos.ZERO);
    }

    /** Можно ли открыть дверь (последний синхронизированный результат) */
    public static boolean isLastKnownDoorAvailable() {
        return lastKnownDoorAccess;
    }

}