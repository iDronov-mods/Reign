package net.mcreator.reignmod.procedures;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.minecraft.world.level.LevelAccessor;

public class ReturnMarketCopperProcedure {
	public static String execute(LevelAccessor world) {
		return new java.text.DecimalFormat("##").format(ReignModModVariables.MapVariables.get(world).market_copper) + " / " + new java.text.DecimalFormat("##").format(ReignModModVariables.MapVariables.get(world).market_copper_all);
	}
}
