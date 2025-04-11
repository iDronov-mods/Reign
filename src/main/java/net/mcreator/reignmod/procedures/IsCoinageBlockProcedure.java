package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.reignmod.network.ReignModModVariables;

public class IsCoinageBlockProcedure {
	public static boolean execute(LevelAccessor world) {
		return ReignModModVariables.MapVariables.get(world).coinage_block;
	}
}
