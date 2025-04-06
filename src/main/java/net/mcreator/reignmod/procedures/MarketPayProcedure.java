package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.reignmod.network.ReignModModVariables;

public class MarketPayProcedure {
	public static boolean execute(LevelAccessor world, double value) {
		if (value <= ReignModModVariables.MapVariables.get(world).market_copper) {
			ReignModModVariables.MapVariables.get(world).market_copper = ReignModModVariables.MapVariables.get(world).market_copper - value;
			ReignModModVariables.MapVariables.get(world).syncData(world);
			return true;
		}
		return false;
	}
}
