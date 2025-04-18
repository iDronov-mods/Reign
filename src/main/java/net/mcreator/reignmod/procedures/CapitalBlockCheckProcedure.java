package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.reignmod.network.ReignModModVariables;

public class CapitalBlockCheckProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if (!ReignModModVariables.MapVariables.get(world).CapitalHave) {
			return false;
		} else if (Math.abs(x - ReignModModVariables.MapVariables.get(world).CAPITAL_X) <= 256 && Math.abs(z - ReignModModVariables.MapVariables.get(world).CAPITAL_Z) <= 256 && y != 0) {
			return true;
		}
		return false;
	}
}
