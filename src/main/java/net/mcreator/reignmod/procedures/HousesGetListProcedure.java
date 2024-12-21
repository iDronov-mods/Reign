package net.mcreator.reignmod.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.mcreator.reignmod.house.House;


import net.mcreator.reignmod.house.HouseManager;

public class HousesGetListProcedure {
public static void execute( Entity entity ) {
	if( entity == null ) return ;
	var houses = HouseManager.getHousesCopies();
	
	houses.forEach(house -> {

		String name = house.getHouseTitle();
		String color_prefix = HouseManager.getHouseColorCode(house.getHouseColor());
		int count_domains = house.getDomains().size();
		int count_players = house.getPlayers().size();

		if (entity instanceof Player _player && !_player.level().isClientSide())
		_player.displayClientMessage(Component.literal(color_prefix+ "§l" + name + "§r: ")
    	.append(Component.translatable("translation.key.domains"))
    	.append(Component.literal(": " + count_domains + ", "))
    	.append(Component.translatable("translation.key.players"))
    	.append(Component.literal(": " + count_players)), false);

	});
	}
}
