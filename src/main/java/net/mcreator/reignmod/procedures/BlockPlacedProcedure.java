package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.init.ReignModModBlocks;

public class BlockPlacedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (blockstate.getBlock() == ReignModModBlocks.COFFERS.get()
				&& !((world.getBlockState(BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z)))
						.getBlock() == ReignModModBlocks.COFFERS.get())) {
			ReignModModVariables.MapVariables.get(world).VC_X = x;
			ReignModModVariables.MapVariables.get(world).syncData(world);
			ReignModModVariables.MapVariables.get(world).VC_Y = y;
			ReignModModVariables.MapVariables.get(world).syncData(world);
			ReignModModVariables.MapVariables.get(world).VC_Z = z;
			ReignModModVariables.MapVariables.get(world).syncData(world);
		}
	}
}
