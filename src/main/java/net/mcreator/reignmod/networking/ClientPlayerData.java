package net.mcreator.reignmod.networking;

import java.util.Map;
import java.util.HashMap;

public class ClientPlayerData {
	private static Map<String, String> playersPrefixes = new HashMap<>();
    


    public static void set(Map<String, String> playersPrefixes) {
        ClientPlayerData.playersPrefixes = playersPrefixes;
    }

    public static Map<String, String> getPlayersPrefixes() {
        return playersPrefixes;
    }
}