package net.mcreator.reignmod.networking;

import java.util.HashMap;
import java.util.Map;

/**
 * Клиентские данные, синхронизируемые с сервера.
 * Тут и префиксы игроков, и (chunkId -> ownerId) карта.
 */
public class ClientPlayerData {

    // --- Префиксы игроков ---
    private static Map<String, String> playersPrefixes = new HashMap<>();
    // Координаты чанка, для которого мы знаем разрешение
    private static int lastKnownChunkX = Integer.MIN_VALUE;

    public static Map<String, String> getPlayersPrefixes() {
        return playersPrefixes;
    }
    private static int lastKnownChunkZ = Integer.MIN_VALUE;
    // Можно ли ломать в этом чанке?
    private static boolean canBreakInThisChunk = true;

    public static void setPlayersPrefixes(Map<String, String> playersPrefixes) {
        ClientPlayerData.playersPrefixes = playersPrefixes;
    }

    public static void setLastKnown(int chunkX, int chunkZ, boolean canBreak) {
        lastKnownChunkX = chunkX;
        lastKnownChunkZ = chunkZ;
        canBreakInThisChunk = canBreak;
    }

    public static void setLastKnown() {
        lastKnownChunkX = Integer.MIN_VALUE;
        lastKnownChunkZ = Integer.MIN_VALUE;
        canBreakInThisChunk = true;
    }

    public static int getLastKnownChunkX() {
        return lastKnownChunkX;
    }

    public static int getLastKnownChunkZ() {
        return lastKnownChunkZ;
    }

    public static boolean canBreakInThisChunk() {
        return canBreakInThisChunk;
    }

}