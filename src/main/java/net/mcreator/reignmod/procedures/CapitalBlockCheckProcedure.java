package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.reignmod.network.ReignModModVariables;

public class CapitalBlockCheckProcedure {
	public static boolean execute(LevelAccessor world, double x, double z) {
		if (ReignModModVariables.MapVariables.get(world).CAPITAL_Y == 0) {
			return false;
		} else if (Math.abs(x - ReignModModVariables.MapVariables.get(world).CAPITAL_X) <= 64 && Math.abs(z - ReignModModVariables.MapVariables.get(world).CAPITAL_Z) <= 64) {
			return true;
		}
		return false;
	}
}
