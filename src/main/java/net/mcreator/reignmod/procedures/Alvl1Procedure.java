package net.mcreator.reignmod.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.reignmod.network.ReignModModVariables;

public class Alvl1Procedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).ADD_LVL >= 1) {
			return true;
		}
		return false;
	}
}
