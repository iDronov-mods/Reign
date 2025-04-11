package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.reignmod.network.ReignModModVariables;

public class ArmorTaxTextProcedure {
	public static String execute(LevelAccessor world) {
		return new java.text.DecimalFormat("##").format(ReignModModVariables.MapVariables.get(world).armor_price * 100) + "%";
	}
}
