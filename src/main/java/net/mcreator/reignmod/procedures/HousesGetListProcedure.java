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
		int hp = house.getHouseHP();

		if (entity instanceof Player _player && !_player.level().isClientSide())
		_player.displayClientMessage(Component.literal(name +  " -----> " + hp +" / 1000 HP"), false);
	});
	}
}
