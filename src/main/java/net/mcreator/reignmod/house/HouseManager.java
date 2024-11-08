package net.mcreator.reignmod.house;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class HouseManager {
    public static boolean createHouse(String name, Entity entity, String color) {
        if (entity instanceof Player player) {
            ServerLevel level = player.getServer().overworld();
            HouseSavedData houseData = HouseSavedData.getOrCreate(level);

            return houseData.addHouse(name, player.getUUID().toString(), color);
        }
        return false;
    }

    public static House getHouse(Entity entity, String name) {
        if (entity instanceof Player player) {
            ServerLevel level = player.getServer().overworld();
            HouseSavedData houseData = HouseSavedData.getOrCreate(level);

            return houseData.findHouseByName(name);
        }
        return new House();
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
