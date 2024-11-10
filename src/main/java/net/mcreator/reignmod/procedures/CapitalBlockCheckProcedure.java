package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.reignmod.network.ReignModModVariables;

public class CapitalBlockCheckProcedure {
	public static boolean execute(LevelAccessor world) {
		if (ReignModModVariables.MapVariables.get(world).CAPITAL_Y == 0) {
			return false;
		} else if (true) {
			return true;
		}
		return false;
	}
}
