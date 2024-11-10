package net.mcreator.reignmod.house;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class HouseManager {

    public static boolean createHouse(Entity lordEntity, String houseTitle, String color) {
        if (lordEntity instanceof Player lordPlayer) {
            ServerLevel level = lordPlayer.getServer().overworld();
            HouseSavedData houseData = HouseSavedData.getOrCreate(level);

            return houseData.addHouse(lordPlayer.getStringUUID(), houseTitle, color);
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

    public static boolean isPlayerLord(Entity lordEntity) {
        if (lordEntity instanceof Player lordPlayer) {
            ServerLevel level = lordPlayer.getServer().overworld();
            HouseSavedData houseData = HouseSavedData.getOrCreate(level);

            return houseData.getHouseData().findHouseByLord(lordPlayer.getStringUUID()).isNull();
        }
        return false;
    }

    public static boolean isPlayerKnight(Entity knightEntity) {
        if (knightEntity instanceof Player knightPlayer) {
            ServerLevel level = knightPlayer.getServer().overworld();
            HouseSavedData houseData = HouseSavedData.getOrCreate(level);

            return houseData.getHouseData().findDomainByKnight(knightPlayer.getStringUUID()).isNull();
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
}
