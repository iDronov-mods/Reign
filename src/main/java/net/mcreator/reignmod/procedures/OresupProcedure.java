package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.reignmod.network.ReignModModVariables;

public class OresupProcedure {
	public static void execute(LevelAccessor world) {
		if (ReignModModVariables.MapVariables.get(world).ores_price < 100) {
			ReignModModVariables.MapVariables.get(world).ores_price = ReignModModVariables.MapVariables.get(world).ores_price + 20;
			ReignModModVariables.MapVariables.get(world).syncData(world);
		}
	}
}
