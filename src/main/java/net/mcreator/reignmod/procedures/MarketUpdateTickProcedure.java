package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.reignmod.market.MarketManager;

public class MarketUpdateTickProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				if (MarketManager.isRefreshNeeded()) {
					MarketUpdateProcedure.execute(entity);
					MarketManager.clearRefresh();
				}
			}
			world = _worldorig;
		}
	}
}
