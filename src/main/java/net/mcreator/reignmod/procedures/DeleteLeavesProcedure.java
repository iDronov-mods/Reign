package net.mcreator.reignmod.procedures;

import net.mcreator.reignmod.ReignModMod;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;

public class DeleteLeavesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, double count) {
		if (count < 4) {
			if (!(world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BlockTags.create(new ResourceLocation("minecraft:logs")))
					&& (world.getBlockState(BlockPos.containing(x, y, z))).is(BlockTags.create(new ResourceLocation("minecraft:leaves")))) {
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y, z), null);
					world.destroyBlock(_pos, false);
				}
			}
			ReignModMod.queueServerWork(10, () -> {
				if ((world.getBlockState(BlockPos.containing(x + 0, y + 1, z + 0))).is(BlockTags.create(new ResourceLocation("minecraft:leaves")))) {
					DeleteLeavesProcedure.execute(world, x + 0, y + 1, z + 0, count + 1);
				}
				if ((world.getBlockState(BlockPos.containing(x + 1, y + 1, z + 0))).is(BlockTags.create(new ResourceLocation("minecraft:leaves")))) {
					DeleteLeavesProcedure.execute(world, x + 1, y + 1, z + 0, count + 1);
				}
				if ((world.getBlockState(BlockPos.containing(x + 1, y + 1, z + 1))).is(BlockTags.create(new ResourceLocation("minecraft:leaves")))) {
					DeleteLeavesProcedure.execute(world, x + 1, y + 1, z + 1, count + 1);
				}
				if ((world.getBlockState(BlockPos.containing(x + 0, y + 1, z + 1))).is(BlockTags.create(new ResourceLocation("minecraft:leaves")))) {
					DeleteLeavesProcedure.execute(world, x + 0, y + 1, z + 1, count + 1);
				}
				if ((world.getBlockState(BlockPos.containing(x + -1, y + 1, z + 1))).is(BlockTags.create(new ResourceLocation("minecraft:leaves")))) {
					DeleteLeavesProcedure.execute(world, x + -1, y + 1, z + 1, count + 1);
				}
				if ((world.getBlockState(BlockPos.containing(x + -1, y + 1, z + 0))).is(BlockTags.create(new ResourceLocation("minecraft:leaves")))) {
					DeleteLeavesProcedure.execute(world, x + -1, y + 1, z + 0, count + 1);
				}
				if ((world.getBlockState(BlockPos.containing(x + -1, y + 1, z + -1))).is(BlockTags.create(new ResourceLocation("minecraft:leaves")))) {
					DeleteLeavesProcedure.execute(world, x + -1, y + 1, z + -1, count + 1);
				}
				if ((world.getBlockState(BlockPos.containing(x + 0, y + 1, z + -1))).is(BlockTags.create(new ResourceLocation("minecraft:leaves")))) {
					DeleteLeavesProcedure.execute(world, x + 0, y + 1, z + -1, count + 1);
				}
				if ((world.getBlockState(BlockPos.containing(x + 1, y + 1, z + -1))).is(BlockTags.create(new ResourceLocation("minecraft:leaves")))) {
					DeleteLeavesProcedure.execute(world, x + 1, y + 1, z + -1, count + 1);
				}
				if ((world.getBlockState(BlockPos.containing(x + 1, y + 0, z + 0))).is(BlockTags.create(new ResourceLocation("minecraft:leaves")))) {
					DeleteLeavesProcedure.execute(world, x + 1, y + 0, z + 0, count + 1);
				}
				if ((world.getBlockState(BlockPos.containing(x + 1, y + 0, z + 1))).is(BlockTags.create(new ResourceLocation("minecraft:leaves")))) {
					DeleteLeavesProcedure.execute(world, x + 1, y + 0, z + 1, count + 1);
				}
				if ((world.getBlockState(BlockPos.containing(x + 0, y + 0, z + 1))).is(BlockTags.create(new ResourceLocation("minecraft:leaves")))) {
					DeleteLeavesProcedure.execute(world, x + 0, y + 0, z + 1, count + 1);
				}
				if ((world.getBlockState(BlockPos.containing(x + -1, y + 0, z + 1))).is(BlockTags.create(new ResourceLocation("minecraft:leaves")))) {
					DeleteLeavesProcedure.execute(world, x + -1, y + 0, z + 1, count + 1);
				}
				if ((world.getBlockState(BlockPos.containing(x + -1, y + 0, z + 0))).is(BlockTags.create(new ResourceLocation("minecraft:leaves")))) {
					DeleteLeavesProcedure.execute(world, x + -1, y + 0, z + 0, count + 1);
				}
				if ((world.getBlockState(BlockPos.containing(x + -1, y + 0, z + -1))).is(BlockTags.create(new ResourceLocation("minecraft:leaves")))) {
					DeleteLeavesProcedure.execute(world, x + -1, y + 0, z + -1, count + 1);
				}
				if ((world.getBlockState(BlockPos.containing(x + 0, y + 0, z + -1))).is(BlockTags.create(new ResourceLocation("minecraft:leaves")))) {
					DeleteLeavesProcedure.execute(world, x + 0, y + 0, z + -1, count + 1);
				}
				if ((world.getBlockState(BlockPos.containing(x + 1, y + 0, z + -1))).is(BlockTags.create(new ResourceLocation("minecraft:leaves")))) {
					DeleteLeavesProcedure.execute(world, x + 1, y + 0, z + -1, count + 1);
				}
			});
		}
	}
}
