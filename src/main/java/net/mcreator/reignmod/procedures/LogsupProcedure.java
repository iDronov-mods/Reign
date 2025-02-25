package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.reignmod.network.ReignModModVariables;

public class LogsupProcedure {
	public static void execute(LevelAccessor world) {
		if (ReignModModVariables.MapVariables.get(world).logs_price < 2) {
			ReignModModVariables.MapVariables.get(world).logs_price = ReignModModVariables.MapVariables.get(world).logs_price + 0.25;
			ReignModModVariables.MapVariables.get(world).syncData(world);
		}
	}
}
