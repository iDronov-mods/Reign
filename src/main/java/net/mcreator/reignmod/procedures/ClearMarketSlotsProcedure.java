package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.reignmod.network.ReignModModVariables;

public class ClearMarketSlotsProcedure {
	public static void execute(LevelAccessor world) {
		ReignModModVariables.MapVariables.get(world).NeedRefresh = true;
		ReignModModVariables.MapVariables.get(world).syncData(world);
	}
}
