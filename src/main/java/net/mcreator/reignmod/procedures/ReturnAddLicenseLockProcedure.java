package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.reignmod.network.ReignModModVariables;

public class ReturnAddLicenseLockProcedure {
	public static boolean execute(LevelAccessor world) {
		if (ReignModModVariables.MapVariables.get(world).ERA < 3) {
			return false;
		}
		return true;
	}
}
