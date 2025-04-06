package net.mcreator.reignmod.procedures;

import net.mcreator.reignmod.house.HouseManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class GetTrueNameProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		Entity player = null;
		player = entity;
		return HouseManager.getPlayerDisplayName((Player) player);
	}
}
