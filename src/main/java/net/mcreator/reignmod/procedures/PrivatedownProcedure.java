package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.reignmod.network.ReignModModVariables;

public class PrivatedownProcedure {
	public static void execute(LevelAccessor world) {
		if (ReignModModVariables.MapVariables.get(world).private_price > 0) {
			ReignModModVariables.MapVariables.get(world).private_price = ReignModModVariables.MapVariables.get(world).private_price - 0.05;
			ReignModModVariables.MapVariables.get(world).syncData(world);
		}
	}
}
