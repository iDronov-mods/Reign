package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.reignmod.network.ReignModModVariables;

public class FooddownProcedure {
	public static void execute(LevelAccessor world) {
		if (ReignModModVariables.MapVariables.get(world).food_price > 0) {
			ReignModModVariables.MapVariables.get(world).food_price = ReignModModVariables.MapVariables.get(world).food_price - 25;
			ReignModModVariables.MapVariables.get(world).syncData(world);
		}
	}
}
