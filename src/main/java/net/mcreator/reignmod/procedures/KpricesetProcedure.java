package net.mcreator.reignmod.procedures;

public class KpricesetProcedure {
	public static double execute(double amount, double max) {
		double fullness = 0;
		fullness = amount / max;
		if (fullness >= 0.75) {
			return 0.5;
		} else if (fullness >= 0.5) {
			return 0.75;
		}
		return 1;
	}
}
