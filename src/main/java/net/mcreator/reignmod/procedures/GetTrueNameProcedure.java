package net.mcreator.reignmod.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.reignmod.house.HouseManager;

public class GetTrueNameProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		Entity player = null;
		player = entity;
		return HouseManager.getPlayerDisplayName((Player) player);
	}
}
