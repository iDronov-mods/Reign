//package net.mcreator.reignmod.procedures;
//
//import net.minecraft.world.entity.Entity;
//import net.mcreator.reignmod.HouseData;
//import net.mcreator.reignmod.House;
//import net.mcreator.reignmod.network.ReignModModVariables;
//
//
//public class ReturnLordProcedure {
//    public static boolean execute(Entity entity) {
//        // Получаем имя дома игрока из переменных игрока
//        String playerHouseName = entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
//                .orElse(new ReignModModVariables.PlayerVariables()).house;
//
//        // Получаем дом по имени
//        House playerHouse = HouseData.findHouseByName(playerHouseName);
//
//        // Проверяем, является ли игрок главой своего дома
//        return playerHouse != null && playerHouse.getHeadUUID().equals(entity.getUUID().toString());
//    }
//}
