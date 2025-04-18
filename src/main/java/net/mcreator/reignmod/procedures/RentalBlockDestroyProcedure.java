package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.reignmod.claim.capital.CapitalClaimManager;

public class RentalBlockDestroyProcedure {
	public static void execute(LevelAccessor world, double x, double z) {
		double center_x = 0;
		double center_z = 0;
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				center_x = x;
				center_z = z;
				CapitalClaimManager.removeClaim(center_x, center_z);
			}
			world = _worldorig;
		}
	}
}
