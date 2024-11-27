package net.mcreator.reignmod.house;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class HouseManager {

    private static final HashMap<String, String> colorCodes = fillColorCodes();

    private static HashMap<String, String> fillColorCodes() {
        HashMap<String, String> colorCodes = new HashMap<>();
        List<String> colors = List.of("yellow", "lime", "green", "aqua", "blue", "purple", "pink", "red", "orange", "black");
        List<String> codes = List.of("§e", "§a", "§2", "§b", "§1", "§5", "§d", "§c", "§6", "§8");

        Iterator<String> colorsIterator = colors.iterator();
        Iterator<String> codesIterator = codes.iterator();
        while (colorsIterator.hasNext() && codesIterator.hasNext()) {
            colorCodes.put(colorsIterator.next(), codesIterator.next());
        }
        return colorCodes;
    }

    public static int getHousesCount() {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            return houseSavedData.getHouseData().getHouses().size();
        }
        return 0;
    }

    public static boolean createHouse(Entity lordEntity, String houseTitle, String houseColor, int houseHeartIdentifier) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();
        if (houseSavedData != null && lordEntity instanceof Player lordPlayer) {
            return houseSavedData.addHouse(lordEntity.getStringUUID(), houseTitle, houseColor, houseHeartIdentifier);
        }
        return false;
    }

    public static boolean createDomain(Entity lordEntity, Entity knightEntity) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null && lordEntity instanceof Player lordPlayer && knightEntity instanceof Player knightPlayer) {
            return houseSavedData.addDomain(lordPlayer.getStringUUID(), knightPlayer.getStringUUID(), knightPlayer.getDisplayName().getString());
        }
        return false;
    }

    public static void deleteHouse(Entity lordEntity) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null && lordEntity instanceof Player lordPlayer) {
            houseSavedData.removeHouse(lordPlayer.getStringUUID());
        }
    }

    public static void deleteDomain(Entity lordEntity, Entity knightEntity) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null && lordEntity instanceof Player lordPlayer && knightEntity instanceof Player knightPlayer) {
            houseSavedData.removeDomain(lordPlayer.getStringUUID(), knightPlayer.getStringUUID());
        }
    }

    public static boolean pushPlayerToDomain(Entity playerEntity) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null && playerEntity instanceof Player player) {
            String suzerainUUID = getPlayerSuzerain(playerEntity);
            return houseSavedData.pushPlayerToDomain(suzerainUUID, player.getStringUUID());
        }
        return false;
    }

    public static void removePlayerFromDomain(Entity playerEntity) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null && playerEntity instanceof Player player) {
            String suzerainUUID = getPlayerSuzerain(player);
            houseSavedData.removePlayerFromDomain(suzerainUUID, player.getStringUUID());
        }
    }

    public static boolean isPlayerLord(Entity lordEntity) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null && lordEntity instanceof Player lordPlayer) {
            return !houseSavedData.getHouseData().findHouseByLord(lordPlayer.getStringUUID()).isNull();
        }
        return false;
    }

    public static boolean isPlayerKnight(Entity knightEntity) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null && knightEntity instanceof Player knightPlayer) {
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

    public static String getPlayerSuzerain(Entity playerEntity) {
        if (playerEntity instanceof Player player) {
            return (player.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).house;
        }
        return "null";
    }

    private static House getPlayerHouse(Entity playerEntity) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null && playerEntity instanceof Player player) {
            String suzerainUUID = getPlayerSuzerain(player);
            return houseSavedData.getPlayerHouse(suzerainUUID);
        }
        return new House();
    }

    public static String getPlayerHouseTitle(Entity playerEntity) {
        return getPlayerHouse(playerEntity).getHouseTitle();
    }

    public static String getPlayerHouseColor(Entity playerEntity) {
        return getPlayerHouse(playerEntity).getHouseColor();
    }

    public static String getPlayerHouseColorCode(Entity playerEntity) {
        return colorCodes.getOrDefault(getPlayerHouseColor(playerEntity), "null");
    }

    public static String getPlayerHouseLord(Entity playerEntity) {
        return getPlayerHouse(playerEntity).getLordUUID();
    }

    public static int getHouseDomainCount(Entity playerEntity) {
        return getPlayerHouse(playerEntity).getDomains().size();
    }

    public static int getHousePlayerCount(Entity playerEntity) {
        return getPlayerHouse(playerEntity).getPlayers().size();
    }

    private static Domain getPlayerDomain(Entity playerEntity) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null && playerEntity instanceof Player player) {
            String suzerainUUID = getPlayerSuzerain(player);
            return houseSavedData.getPlayerDomain(playerEntity.getStringUUID(), suzerainUUID);
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

    public static String getPlayerDomainTitle(Entity playerEntity) {
        return getPlayerDomain(playerEntity).getDomainTitle().getString();
    }

    public static String getPlayerDomainKnight(Entity playerEntity) {
        return getPlayerDomain(playerEntity).getKnightUUID();
    }

    public static int getDomainPlayerCount(Entity playerEntity) {
        return getPlayerDomain(playerEntity).getPlayers().size();
    }

    private static int[] getHouseIncubatorCoordinates(Entity lordEntity) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null && lordEntity instanceof Player lordPlayer) {
            return houseSavedData.getHouseData().getHouseIncubatorCoordinates(lordPlayer.getStringUUID());
        }
        return new int[] {0, 0, 0};
    }
    public static boolean isHouseIncubatorNull(Entity lordEntity) {
        return Arrays.equals(getHouseIncubatorCoordinates(lordEntity), new int[]{0, 0, 0});
    }
    public static int getHouseIncubatorX(Entity lordEntity) {
        return getHouseIncubatorCoordinates(lordEntity)[0];
    }
    public static int getHouseIncubatorY(Entity lordEntity) {
        return getHouseIncubatorCoordinates(lordEntity)[1];
    }
    public static int getHouseIncubatorZ(Entity lordEntity) {
        return getHouseIncubatorCoordinates(lordEntity)[2];
    }


    public void setHouseIncubatorCoordinates(Entity lordEntity, int x, int y, int z) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null && lordEntity instanceof Player lordPlayer) {
            houseSavedData.getHouseData().setHouseIncubatorCoordinates(lordPlayer.getStringUUID(), x, y, z);
        }
    }

    private static int[] getHousePrisonCoordinates(Entity lordEntity) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null && lordEntity instanceof Player lordPlayer) {
            return houseSavedData.getHouseData().getHousePrisonCoordinates(lordPlayer.getStringUUID());
        }
        return new int[] {0, 0, 0};
    }
    public static boolean isHousePrisonNull(Entity lordEntity) {
        return Arrays.equals(getHousePrisonCoordinates(lordEntity), new int[]{0, 0, 0});
    }
    public static int getHousePrisonX(Entity lordEntity) {
        return getHousePrisonCoordinates(lordEntity)[0];
    }
    public static int getHousePrisonY(Entity lordEntity) {
        return getHousePrisonCoordinates(lordEntity)[1];
    }
    public static int getHousePrisonZ(Entity lordEntity) {
        return getHousePrisonCoordinates(lordEntity)[2];
    }

    public void setHousePrisonCoordinates(Entity lordEntity, int x, int y, int z) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null && lordEntity instanceof Player lordPlayer) {
            houseSavedData.getHouseData().setHousePrisonCoordinates(lordPlayer.getStringUUID(), x, y, z);
        }
    }
}
