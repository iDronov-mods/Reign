package net.mcreator.reignmod.procedures;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.minecraft.world.level.LevelAccessor;

public class ReturnEra3Procedure {
	public static boolean execute(LevelAccessor world) {
		if (ReignModModVariables.MapVariables.get(world).ERA >= 3) {
			return true;
		}
		return false;
	}
}
