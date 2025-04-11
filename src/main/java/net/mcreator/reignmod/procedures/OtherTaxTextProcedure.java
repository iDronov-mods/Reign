package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.reignmod.network.ReignModModVariables;

public class OtherTaxTextProcedure {
	public static String execute(LevelAccessor world) {
		return new java.text.DecimalFormat("##").format(ReignModModVariables.MapVariables.get(world).other_price * 100) + "%";
	}
}
