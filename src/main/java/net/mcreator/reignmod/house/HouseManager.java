package net.mcreator.reignmod.house;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.mcreator.reignmod.procedures.IsKingProcedure;
import net.mcreator.reignmod.networking.packet.S2C.PlayerPrefixSyncS2CPacket;
import net.mcreator.reignmod.networking.ReignNetworking;


import java.util.*;

public class HouseManager {

    private static final HashMap<String, String> colorCodes = fillColorCodes();

    private static HashMap<String, String> fillColorCodes() {
        HashMap<String, String> colorCodes = new HashMap<>();
        List<String> colors = List.of("yellow", "lime", "green", "aqua", "blue", "purple", "pink", "red", "orange", "black");
        List<String> codes = List.of("§e", "§a", "§2", "§b", "§9", "§5", "§d", "§c", "§6", "§8");

        Iterator<String> colorsIterator = colors.iterator();
        Iterator<String> codesIterator = codes.iterator();
        while (colorsIterator.hasNext() && codesIterator.hasNext()) {
            colorCodes.put(colorsIterator.next(), codesIterator.next());
        }
        return colorCodes;
    }

    public static Collection<House> getHouses() {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            return houseSavedData.getHouseData().getHouses().values();
        }
        return new ArrayList<>();
    }

	public static Collection<House> getHousesCopies() {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            Collection<House> houses = houseSavedData.getHouseData().getHouses().values();
            Collection<House> housesCopies = new ArrayList<>();
            houses.forEach(house -> {housesCopies.add(new House(house.serializeNBT()));});
            return housesCopies;
        }
        return new ArrayList<>();
    }
    
    public static int getHousesCount() {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            return houseSavedData.getHouseData().getHouses().size();
        }
        return 0;
    }

    public static boolean createHouse(Player lordPlayer, String houseTitle, String houseColor) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();
        if (houseSavedData != null) {
            return houseSavedData.addHouse(lordPlayer.getStringUUID(), houseTitle, houseColor);
        }
        return false;
    }

    public static boolean createDomain(Player lordPlayer, Player knightPlayer) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            return houseSavedData.addDomain(lordPlayer.getStringUUID(), knightPlayer.getStringUUID(), knightPlayer.getDisplayName().getString());
        }
        return false;
    }

    public static void deleteHouse(String lordUUID) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            houseSavedData.removeHouse(lordUUID);
        }
    }

    public static void deleteDomain(Player lordPlayer, Player knightPlayer) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            houseSavedData.removeDomain(lordPlayer.getStringUUID(), knightPlayer.getStringUUID());
        }
    }

   public static boolean pushPlayerToDomain(Player knightPlayer, Player player) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            return houseSavedData.pushPlayerToDomain(knightPlayer.getStringUUID(), player.getStringUUID());
        }
        return false;
    }

    public static void removePlayerFromDomain(Player player) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            String suzerainUUID = getPlayerSuzerain(player);
            houseSavedData.removePlayerFromDomain(suzerainUUID, player.getStringUUID());
        }
    }

    public static boolean isPlayerLord(Player lordPlayer) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            return !houseSavedData.getHouseData().findHouseByLord(lordPlayer.getStringUUID()).isNull();
        }
        return false;
    }

    public static boolean isPlayerKnight(Player knightPlayer) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            return !houseSavedData.getHouseData().findDomainByKnight(knightPlayer.getStringUUID()).isNull();
        }
        return false;
    }

    public static boolean isColorAvailable(String color) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            return houseSavedData.isColorAvailable(color);
        }
        return false;
    }

    public static House getHouseByLordUUID(String lordUUID) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            return houseSavedData.getHouseData().findHouseByLord(lordUUID);
        }
        return new House();
    }

    public static String getPlayerSuzerain(Player player) {
        return (player.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).house;
    }

    public static String getPlayerDisplayName(Player player) {
        String displayNameWithPrefix = player.getDisplayName().getString();
        int playerNameLength = player.getName().getString().length();
        return displayNameWithPrefix.substring(displayNameWithPrefix.length() - playerNameLength);
    }

    public static void playerPrefixSynchronize(Player player) {
        String colorPrefix = HouseManager.getPlayerHouseColorCode(player);
        if (!Objects.equals(colorPrefix, "")) {

            if (IsKingProcedure.execute(player.getCommandSenderWorld(), player)) {
                colorPrefix = "§r[§6👑§r] " + colorPrefix + "§l";
            } else if (HouseManager.isPlayerLord(player)) {
                colorPrefix = "§r[" + colorPrefix + "🏰§r] " + colorPrefix + "§l";
            } else if (HouseManager.isPlayerKnight(player)) {
                colorPrefix = "§r[" + colorPrefix + "🗡§r] " + colorPrefix;
            }
        }
        String finalPlayerPrefix = colorPrefix;
        HouseSavedData houseSavedData = HouseSavedData.getInstance();
        houseSavedData.getHouseData().addOrUpdatePlayerCode(player.getStringUUID(), finalPlayerPrefix);

        player.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
            capability.player_prefix = finalPlayerPrefix;
            capability.syncPlayerVariables(player);
        });

        if (player instanceof ServerPlayer) {
            ReignNetworking.sendToPlayer(new PlayerPrefixSyncS2CPacket(houseSavedData.getHouseData().getPlayerCodes()), (ServerPlayer) player);
        }
        player.refreshDisplayName();
        if (player instanceof ServerPlayer) {
            ((ServerPlayer) player).refreshTabListName();
        }
    }

    public static void allPlayersPrefixPacketSend() {
        var serverInstance = HouseSavedData.getServerInstance();
        serverInstance.getPlayers(serverPlayer -> true).forEach(serverPlayer -> {
            HouseSavedData houseSavedData = HouseSavedData.getInstance();
            ReignNetworking.sendToPlayer(new PlayerPrefixSyncS2CPacket(houseSavedData.getHouseData().getPlayerCodes()), serverPlayer);
        });
    }

    public static House getPlayerHouse(Player player) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            String suzerainUUID = getPlayerSuzerain(player);
            return houseSavedData.getPlayerHouse(suzerainUUID);
        }
        return new House();
    }

    public static String getPlayerHouseTitle(Player player) {
        return getPlayerHouse(player).getHouseTitle();
    }

    public static String getPlayerHouseColor(Player player) {
        return getPlayerHouse(player).getHouseColor();
    }

    public static String getPlayerHouseColorCode(Player player) {
        return colorCodes.getOrDefault(getPlayerHouseColor(player), "");
    }

    public static String getHouseColorCode(String color) {
        return colorCodes.getOrDefault(color, "");
    }

    public static String getPlayerHouseLord(Player player) {
        return getPlayerHouse(player).getLordUUID();
    }

    public static int getHouseDomainCount(Player player) {
        return getPlayerHouse(player).getDomains().size();
    }

    public static int getHousePlayerCount(Player player) {
        return getPlayerHouse(player).getPlayers().size();
    }

    public static Domain getPlayerDomain(Player player) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            String suzerainUUID = getPlayerSuzerain(player);
            return houseSavedData.getPlayerDomain(player.getStringUUID(), suzerainUUID);
        }
        return new Domain();
    }

    public static String getDomainLordByKnight(String knightUUID) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();
        if (houseSavedData != null) {
            return houseSavedData.getHouseData().findDomainByKnight(knightUUID).getLordUUID();
        }
        return "null";
    }

    public static String getPlayerDomainTitle(Player player) {
        return getPlayerDomain(player).getDomainTitle().getString();
    }

    public static String getPlayerDomainKnight(Player player) {
        return getPlayerDomain(player).getKnightUUID();
    }

    public static int getDomainPlayerCount(Player player) {
        return getPlayerDomain(player).getPlayers().size();
    }

    private static int[] getHouseIncubatorCoordinates(String lordUUID) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            return houseSavedData.getHouseData().getHouseIncubatorCoordinates(lordUUID);
        }
        return new int[] {0, 0, 0};
    }
    public static boolean isHouseIncubatorNull(String lordUUID) {
        return Arrays.equals(getHouseIncubatorCoordinates(lordUUID), new int[]{0, 0, 0});
    }
    public static int getHouseIncubatorX(String lordUUID) {
        return getHouseIncubatorCoordinates(lordUUID)[0];
    }
    public static int getHouseIncubatorY(String lordUUID) {
        return getHouseIncubatorCoordinates(lordUUID)[1];
    }
    public static int getHouseIncubatorZ(String lordUUID) {
        return getHouseIncubatorCoordinates(lordUUID)[2];
    }


    public static void setHouseIncubatorCoordinates(String lordUUID, int x, int y, int z) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            houseSavedData.getHouseData().setHouseIncubatorCoordinates(lordUUID, x, y, z);
        }
    }

    private static int[] getHousePrisonCoordinates(String lordUUID) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            return houseSavedData.getHouseData().getHousePrisonCoordinates(lordUUID);
        }
        return new int[] {0, 0, 0};
    }
    public static boolean isHousePrisonNull(String lordUUID) {
        return Arrays.equals(getHousePrisonCoordinates(lordUUID), new int[]{0, 0, 0});
    }
    public static int getHousePrisonX(String lordUUID) {
        return getHousePrisonCoordinates(lordUUID)[0];
    }
    public static int getHousePrisonY(String lordUUID) {
        return getHousePrisonCoordinates(lordUUID)[1];
    }
    public static int getHousePrisonZ(String lordUUID) {
        return getHousePrisonCoordinates(lordUUID)[2];
    }

    public void setHousePrisonCoordinates(String lordUUID, int x, int y, int z) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            houseSavedData.getHouseData().setHousePrisonCoordinates(lordUUID, x, y, z);
        }
    }
}
