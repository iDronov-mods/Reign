package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.reignmod.network.ReignModModVariables;

public class ArmordownProcedure {
	public static void execute(LevelAccessor world) {
		if (ReignModModVariables.MapVariables.get(world).armor_price > 0) {
			ReignModModVariables.MapVariables.get(world).armor_price = ReignModModVariables.MapVariables.get(world).armor_price - 20;
			ReignModModVariables.MapVariables.get(world).syncData(world);
		}
	}
}
