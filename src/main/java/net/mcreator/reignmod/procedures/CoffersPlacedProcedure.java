package net.mcreator.reignmod.procedures;

import net.mcreator.reignmod.kingdom.KingdomData;

public class CoffersPlacedProcedure {
	public static void execute(double x, double y, double z) {
		double x_coffers = 0;
		double y_coffers = 0;
		double z_coffers = 0;
		x_coffers = x;
		y_coffers = y;
		z_coffers = z;
		KingdomData.setCoffersCoordinates((int) x_coffers, (int) y_coffers, (int) z_coffers);
	}
}
