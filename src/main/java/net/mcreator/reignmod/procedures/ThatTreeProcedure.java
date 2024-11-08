package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class ThatTreeProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, BlockState leaves) {
		double shift = 0;
		shift = 0;
		while ((world.getBlockState(BlockPos.containing(x, y + shift, z))).getBlock() == blockstate.getBlock()) {
			shift = shift + 1;
		}
		if ((world.getBlockState(BlockPos.containing(x, y + shift, z))).getBlock() == leaves.getBlock()) {
			return true;
		}
		return false;
	}
}
