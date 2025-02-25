package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import net.mcreator.reignmod.network.ReignModModVariables;

public class MarketUpdateTickProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!ReignModModVariables.MapVariables.get(world).market_update) {
			MarketUpdateProcedure.execute(world, entity);
		}
	}
}
