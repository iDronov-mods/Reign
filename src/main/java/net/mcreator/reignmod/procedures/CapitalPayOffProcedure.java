package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.reignmod.kingdom.KingdomData;

public class CapitalPayOffProcedure {
	public static void execute(LevelAccessor world) {
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				KingdomData.feedCapital();
			}
			world = _worldorig;
		}
	}
}
