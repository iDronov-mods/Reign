package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.reignmod.network.ReignModModVariables;

public class ExpupProcedure {
	public static void execute(LevelAccessor world) {
		if (ReignModModVariables.MapVariables.get(world).exp_price < 2) {
			ReignModModVariables.MapVariables.get(world).exp_price = ReignModModVariables.MapVariables.get(world).exp_price + 0.1;
			ReignModModVariables.MapVariables.get(world).syncData(world);
		}
	}
}
