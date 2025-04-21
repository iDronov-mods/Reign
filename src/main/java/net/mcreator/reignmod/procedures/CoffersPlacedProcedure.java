package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.reignmod.kingdom.KingdomManager;

public class CoffersPlacedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double x_coffers = 0;
		double y_coffers = 0;
		double z_coffers = 0;
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				x_coffers = x;
				y_coffers = y;
				z_coffers = z;
				KingdomManager.setCoffersCoordinates((int) x_coffers, (int) y_coffers, (int) z_coffers);
			}
			world = _worldorig;
		}
	}
}
