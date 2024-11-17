package net.mcreator.reignmod.house;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.Arrays;

public class HouseManager {

    public static boolean createHouse(Entity lordEntity, String houseTitle, String houseColor, int houseHeartIdentifier) {
        if (lordEntity instanceof Player lordPlayer) {
            ServerLevel level = lordPlayer.getServer().overworld();
            HouseSavedData houseData = HouseSavedData.getOrCreate(level);

            return houseData.addHouse(lordPlayer.getStringUUID(), houseTitle, houseColor, houseHeartIdentifier);
        }
        return false;
    }

    public static boolean createDomain(Entity lordEntity, Entity knightEntity) {
        if (lordEntity instanceof Player lordPlayer && knightEntity instanceof Player knightPlayer) {
            ServerLevel level = lordPlayer.getServer().overworld();
            HouseSavedData houseData = HouseSavedData.getOrCreate(level);

            return houseData.addDomain(lordPlayer.getStringUUID(), knightPlayer.getStringUUID(), String.valueOf(knightPlayer.getDisplayName()));
        }
        return false;
    }

    public static void deleteHouse(Entity lordEntity) {
        if (lordEntity instanceof Player lordPlayer) {
            ServerLevel level = lordPlayer.getServer().overworld();
            HouseSavedData houseData = HouseSavedData.getOrCreate(level);

            houseData.removeHouse(lordPlayer.getStringUUID());
        }
    }

    public static void deleteDomain(Entity lordEntity, Entity knightEntity) {
        if (lordEntity instanceof Player lordPlayer && knightEntity instanceof Player knightPlayer) {
            ServerLevel level = lordPlayer.getServer().overworld();
            HouseSavedData houseData = HouseSavedData.getOrCreate(level);

            houseData.removeDomain(lordPlayer.getStringUUID(), knightPlayer.getStringUUID());
        }
    }

    public boolean pushPlayerToDomain(Entity knightEntity, Entity playerEntity) {
        if (knightEntity instanceof Player knightPlayer && playerEntity instanceof Player player) {
            ServerLevel level = knightPlayer.getServer().overworld();
            HouseSavedData houseData = HouseSavedData.getOrCreate(level);

            return houseData.pushPlayerToDomain(knightPlayer.getStringUUID(), player.getStringUUID());
        }
        return false;
    }

    public void removePlayerFromDomain(Entity knightEntity, Entity playerEntity) {
        if (knightEntity instanceof Player knightPlayer && playerEntity instanceof Player player) {
            ServerLevel level = knightPlayer.getServer().overworld();
            HouseSavedData houseData = HouseSavedData.getOrCreate(level);

            houseData.removePlayerFromDomain(knightPlayer.getStringUUID(), player.getStringUUID());
        }
    }

    public static boolean isPlayerLord(Entity lordEntity) {
        if (lordEntity instanceof Player lordPlayer) {
            ServerLevel level = lordPlayer.getServer().overworld();
            HouseSavedData houseData = HouseSavedData.getOrCreate(level);

            return !houseData.getHouseData().findHouseByLord(lordPlayer.getStringUUID()).isNull();
        }
        return false;
    }

    public static boolean isPlayerKnight(Entity knightEntity) {
        if (knightEntity instanceof Player knightPlayer) {
            ServerLevel level = knightPlayer.getServer().overworld();
            HouseSavedData houseData = HouseSavedData.getOrCreate(level);

            return !houseData.getHouseData().findDomainByKnight(knightPlayer.getStringUUID()).isNull();
        }
        return false;
    }

    public static boolean isColorAvailable(Entity entity, String color) {
        if (entity instanceof Player player) {
            ServerLevel level = player.getServer().overworld();
            HouseSavedData houseData = HouseSavedData.getOrCreate(level);

            return houseData.isColorAvailable(color);
        }
        return false;
    }

    public static String getPlayerHouseTitle(Entity playerEntity, String suzerainUUID) {
        if (playerEntity instanceof Player player) {
            ServerLevel level = player.getServer().overworld();
            HouseSavedData houseData = HouseSavedData.getOrCreate(level);

            return houseData.getPlayerHouse(suzerainUUID).getHouseTitle();
        }
        return "null";
    }

    public static String getPlayerHouseColor(Entity playerEntity, String suzerainUUID) {
        if (playerEntity instanceof Player player) {
            ServerLevel level = player.getServer().overworld();
            HouseSavedData houseData = HouseSavedData.getOrCreate(level);

            return houseData.getPlayerHouse(suzerainUUID).getHouseTitle();
        }
        return "null";
    }

    public static String getPlayerHouseLord(Entity playerEntity, String suzerainUUID) {
        if (playerEntity instanceof Player player) {
            ServerLevel level = player.getServer().overworld();
            HouseSavedData houseData = HouseSavedData.getOrCreate(level);

            return houseData.getPlayerHouse(suzerainUUID).getLordUUID();
        }
        return "null";
    }

    public static String getPlayerDomainTitle(Entity playerEntity, String suzerainUUID) {
        if (playerEntity instanceof Player player) {
            ServerLevel level = player.getServer().overworld();
            HouseSavedData houseData = HouseSavedData.getOrCreate(level);

            return houseData.getPlayerDomain(suzerainUUID).getDomainTitle();
        }
        return "null";
    }

    public static String getPlayerDomainKnight(Entity playerEntity, String suzerainUUID) {
        if (playerEntity instanceof Player player) {
            ServerLevel level = player.getServer().overworld();
            HouseSavedData houseData = HouseSavedData.getOrCreate(level);

            return houseData.getPlayerDomain(suzerainUUID).getKnightUUID();
        }
        return "null";
    }

    public static int getHouseDomainCount(Entity playerEntity, String suzerainUUID) {
        if (playerEntity instanceof Player player) {
            ServerLevel level = player.getServer().overworld();
            HouseSavedData houseData = HouseSavedData.getOrCreate(level);

            return houseData.getPlayerHouse(suzerainUUID).getDomains().size();
        }
        return 0;
    }

    private static int[] getHouseIncubatorCoordinates(Entity lordEntity) {
        if (lordEntity instanceof Player lordPlayer) {
            ServerLevel level = lordPlayer.getServer().overworld();
            HouseSavedData houseData = HouseSavedData.getOrCreate(level);

            return houseData.getHouseData().getHouseIncubatorCoordinates(lordPlayer.getStringUUID());
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
        if (lordEntity instanceof Player lordPlayer) {
            ServerLevel level = lordPlayer.getServer().overworld();
            HouseSavedData houseData = HouseSavedData.getOrCreate(level);

            houseData.getHouseData().setHouseIncubatorCoordinates(lordPlayer.getStringUUID(), x, y, z);
        }
    }

    private static int[] getHousePrisonCoordinates(Entity lordEntity) {
        if (lordEntity instanceof Player lordPlayer) {
            ServerLevel level = lordPlayer.getServer().overworld();
            HouseSavedData houseData = HouseSavedData.getOrCreate(level);

            return houseData.getHouseData().getHousePrisonCoordinates(lordPlayer.getStringUUID());
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
        if (lordEntity instanceof Player lordPlayer) {
            ServerLevel level = lordPlayer.getServer().overworld();
            HouseSavedData houseData = HouseSavedData.getOrCreate(level);

            houseData.getHouseData().setHousePrisonCoordinates(lordPlayer.getStringUUID(), x, y, z);
        }
    }
}
