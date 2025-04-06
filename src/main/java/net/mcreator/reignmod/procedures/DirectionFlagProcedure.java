package net.mcreator.reignmod.procedures;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.minecraft.world.level.LevelAccessor;

public class DirectionFlagProcedure {
	public static double execute(LevelAccessor world, double x, double z) {
		if (x == ReignModModVariables.MapVariables.get(world).CAPITAL_X && z > ReignModModVariables.MapVariables.get(world).CAPITAL_Z) {
			return 0;
		} else if (x < ReignModModVariables.MapVariables.get(world).CAPITAL_X && z == ReignModModVariables.MapVariables.get(world).CAPITAL_Z) {
			return 4;
		} else if (x == ReignModModVariables.MapVariables.get(world).CAPITAL_X && z < ReignModModVariables.MapVariables.get(world).CAPITAL_Z) {
			return 8;
		} else if (x > ReignModModVariables.MapVariables.get(world).CAPITAL_X && z == ReignModModVariables.MapVariables.get(world).CAPITAL_Z) {
			return 12;
		} else if (x < ReignModModVariables.MapVariables.get(world).CAPITAL_X && z > ReignModModVariables.MapVariables.get(world).CAPITAL_Z) {
			return 2;
		} else if (x < ReignModModVariables.MapVariables.get(world).CAPITAL_X && z < ReignModModVariables.MapVariables.get(world).CAPITAL_Z) {
			return 6;
		} else if (x > ReignModModVariables.MapVariables.get(world).CAPITAL_X && z < ReignModModVariables.MapVariables.get(world).CAPITAL_Z) {
			return 10;
		} else if (x > ReignModModVariables.MapVariables.get(world).CAPITAL_X && z > ReignModModVariables.MapVariables.get(world).CAPITAL_Z) {
			return 14;
		}
		return 0;
	}
}
