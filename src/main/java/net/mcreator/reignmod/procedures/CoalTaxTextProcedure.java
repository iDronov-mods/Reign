package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.reignmod.network.ReignModModVariables;

public class CoalTaxTextProcedure {
	public static String execute(LevelAccessor world) {
		return new java.text.DecimalFormat("##").format(ReignModModVariables.MapVariables.get(world).coal_price * 100) + "%";
	}
}
