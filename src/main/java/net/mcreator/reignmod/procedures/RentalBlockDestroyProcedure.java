package net.mcreator.reignmod.procedures;

import net.mcreator.reignmod.claim.capital.CapitalClaimManager;

public class RentalBlockDestroyProcedure {
	public static void execute(double x, double z) {
		double center_x = 0;
		double center_z = 0;
		center_x = x;
		center_z = z;
		CapitalClaimManager.removeClaim(center_x, center_z);
	}
}
