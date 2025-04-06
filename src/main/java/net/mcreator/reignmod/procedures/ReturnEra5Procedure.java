package net.mcreator.reignmod.procedures;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.minecraft.world.level.LevelAccessor;

public class ReturnEra5Procedure {
	public static boolean execute(LevelAccessor world) {
		if (ReignModModVariables.MapVariables.get(world).ERA >= 5) {
			return true;
		}
		return false;
	}
}
