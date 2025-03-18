package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

public class ThatTreeProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double shift = 0;
		shift = 0;
		while ((world.getBlockState(BlockPos.containing(x, y + shift, z))).getBlock() == blockstate.getBlock()) {
			shift = shift + 1;
		}
		if ((world.getBlockState(BlockPos.containing(x, y + shift, z))).is(BlockTags.create(new ResourceLocation("minecraft:leaves")))) {
			return true;
		} else if ((world.getBlockState(BlockPos.containing(x, y + shift + 3, z))).is(BlockTags.create(new ResourceLocation("minecraft:leaves")))) {
			return true;
		}
		return false;
	}
}
