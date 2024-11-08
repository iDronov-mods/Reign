package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.ReignModMod;

public class StepFellingProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState leaves, BlockState type, Entity entity) {
		if (entity == null)
			return;
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == type.getBlock()) {
			{
				BlockPos _pos = BlockPos.containing(x, y, z);
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y, z), null);
				world.destroyBlock(_pos, false);
			}
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.STONE_AXE) {
				{
					ItemStack _ist = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
					if (_ist.hurt(2, RandomSource.create(), null)) {
						_ist.shrink(1);
						_ist.setDamageValue(0);
					}
				}
			} else {
				if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).MAIN_LVL > 0) {
					if (Mth.nextInt(RandomSource.create(), 1, 4) != 1) {
						{
							ItemStack _ist = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
							if (_ist.hurt(1, RandomSource.create(), null)) {
								_ist.shrink(1);
								_ist.setDamageValue(0);
							}
						}
					}
				}
			}
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() instanceof AxeItem
					&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.WOODEN_AXE)) {
				if ((world.getBlockState(BlockPos.containing(x + 0, y + 1, z + 0))).getBlock() == type.getBlock()) {
					ReignModMod.queueServerWork(5, () -> {
						StepFellingProcedure.execute(world, x + 0, y + 1, z + 0, leaves, type, entity);
					});
				}
				if ((world.getBlockState(BlockPos.containing(x + 1, y + 1, z + 0))).getBlock() == type.getBlock()) {
					ReignModMod.queueServerWork(5, () -> {
						StepFellingProcedure.execute(world, x + 1, y + 1, z + 0, leaves, type, entity);
					});
				}
				if ((world.getBlockState(BlockPos.containing(x + 1, y + 1, z + 1))).getBlock() == type.getBlock()) {
					ReignModMod.queueServerWork(5, () -> {
						StepFellingProcedure.execute(world, x + 1, y + 1, z + 1, leaves, type, entity);
					});
				}
				if ((world.getBlockState(BlockPos.containing(x + 0, y + 1, z + 1))).getBlock() == type.getBlock()) {
					ReignModMod.queueServerWork(5, () -> {
						StepFellingProcedure.execute(world, x + 0, y + 1, z + 1, leaves, type, entity);
					});
				}
				if ((world.getBlockState(BlockPos.containing(x + -1, y + 1, z + 1))).getBlock() == type.getBlock()) {
					ReignModMod.queueServerWork(5, () -> {
						StepFellingProcedure.execute(world, x + -1, y + 1, z + 1, leaves, type, entity);
					});
				}
				if ((world.getBlockState(BlockPos.containing(x + -1, y + 1, z + 0))).getBlock() == type.getBlock()) {
					ReignModMod.queueServerWork(5, () -> {
						StepFellingProcedure.execute(world, x + -1, y + 1, z + 0, leaves, type, entity);
					});
				}
				if ((world.getBlockState(BlockPos.containing(x + -1, y + 1, z + -1))).getBlock() == type.getBlock()) {
					ReignModMod.queueServerWork(5, () -> {
						StepFellingProcedure.execute(world, x + -1, y + 1, z + -1, leaves, type, entity);
					});
				}
				if ((world.getBlockState(BlockPos.containing(x + 0, y + 1, z + -1))).getBlock() == type.getBlock()) {
					ReignModMod.queueServerWork(5, () -> {
						StepFellingProcedure.execute(world, x + 0, y + 1, z + -1, leaves, type, entity);
					});
				}
				if ((world.getBlockState(BlockPos.containing(x + 1, y + 1, z + -1))).getBlock() == type.getBlock()) {
					ReignModMod.queueServerWork(5, () -> {
						StepFellingProcedure.execute(world, x + 1, y + 1, z + -1, leaves, type, entity);
					});
				}
				if ((world.getBlockState(BlockPos.containing(x + 1, y + 0, z + 0))).getBlock() == type.getBlock()) {
					StepFellingProcedure.execute(world, x + 1, y + 0, z + 0, leaves, type, entity);
				}
				if ((world.getBlockState(BlockPos.containing(x + 1, y + 0, z + 1))).getBlock() == type.getBlock()) {
					StepFellingProcedure.execute(world, x + 1, y + 0, z + 1, leaves, type, entity);
				}
				if ((world.getBlockState(BlockPos.containing(x + 0, y + 0, z + 1))).getBlock() == type.getBlock()) {
					StepFellingProcedure.execute(world, x + 0, y + 0, z + 1, leaves, type, entity);
				}
				if ((world.getBlockState(BlockPos.containing(x + -1, y + 0, z + 1))).getBlock() == type.getBlock()) {
					StepFellingProcedure.execute(world, x + -1, y + 0, z + 1, leaves, type, entity);
				}
				if ((world.getBlockState(BlockPos.containing(x + -1, y + 0, z + 0))).getBlock() == type.getBlock()) {
					StepFellingProcedure.execute(world, x + -1, y + 0, z + 0, leaves, type, entity);
				}
				if ((world.getBlockState(BlockPos.containing(x + -1, y + 0, z + -1))).getBlock() == type.getBlock()) {
					StepFellingProcedure.execute(world, x + -1, y + 0, z + -1, leaves, type, entity);
				}
				if ((world.getBlockState(BlockPos.containing(x + 0, y + 0, z + -1))).getBlock() == type.getBlock()) {
					StepFellingProcedure.execute(world, x + 0, y + 0, z + -1, leaves, type, entity);
				}
				if ((world.getBlockState(BlockPos.containing(x + 1, y + 0, z + -1))).getBlock() == type.getBlock()) {
					StepFellingProcedure.execute(world, x + 1, y + 0, z + -1, leaves, type, entity);
				}
			}
			if (true) {
				if ((world.getBlockState(BlockPos.containing(x + 0, y + 1, z + 0))).getBlock() == leaves.getBlock()) {
					DeleteLeavesProcedure.execute(world, x + 0, y + 1, z + 0, leaves, 0);
				}
				if ((world.getBlockState(BlockPos.containing(x + 1, y + 1, z + 0))).getBlock() == leaves.getBlock()) {
					DeleteLeavesProcedure.execute(world, x + 1, y + 1, z + 0, leaves, 0);
				}
				if ((world.getBlockState(BlockPos.containing(x + 1, y + 1, z + 1))).getBlock() == leaves.getBlock()) {
					DeleteLeavesProcedure.execute(world, x + 1, y + 1, z + 1, leaves, 0);
				}
				if ((world.getBlockState(BlockPos.containing(x + 0, y + 1, z + 1))).getBlock() == leaves.getBlock()) {
					DeleteLeavesProcedure.execute(world, x + 0, y + 1, z + 1, leaves, 0);
				}
				if ((world.getBlockState(BlockPos.containing(x + -1, y + 1, z + 1))).getBlock() == leaves.getBlock()) {
					DeleteLeavesProcedure.execute(world, x + -1, y + 1, z + 1, leaves, 0);
				}
				if ((world.getBlockState(BlockPos.containing(x + -1, y + 1, z + 0))).getBlock() == leaves.getBlock()) {
					DeleteLeavesProcedure.execute(world, x + -1, y + 1, z + 0, leaves, 0);
				}
				if ((world.getBlockState(BlockPos.containing(x + -1, y + 1, z + -1))).getBlock() == leaves.getBlock()) {
					DeleteLeavesProcedure.execute(world, x + -1, y + 1, z + -1, leaves, 0);
				}
				if ((world.getBlockState(BlockPos.containing(x + 0, y + 1, z + -1))).getBlock() == leaves.getBlock()) {
					DeleteLeavesProcedure.execute(world, x + 0, y + 1, z + -1, leaves, 0);
				}
				if ((world.getBlockState(BlockPos.containing(x + 1, y + 1, z + -1))).getBlock() == leaves.getBlock()) {
					DeleteLeavesProcedure.execute(world, x + 1, y + 1, z + -1, leaves, 0);
				}
				if ((world.getBlockState(BlockPos.containing(x + 1, y + 0, z + 0))).getBlock() == leaves.getBlock()) {
					DeleteLeavesProcedure.execute(world, x + 1, y + 0, z + 0, leaves, 0);
				}
				if ((world.getBlockState(BlockPos.containing(x + 1, y + 0, z + 1))).getBlock() == leaves.getBlock()) {
					DeleteLeavesProcedure.execute(world, x + 1, y + 0, z + 1, leaves, 0);
				}
				if ((world.getBlockState(BlockPos.containing(x + 0, y + 0, z + 1))).getBlock() == leaves.getBlock()) {
					DeleteLeavesProcedure.execute(world, x + 0, y + 0, z + 1, leaves, 0);
				}
				if ((world.getBlockState(BlockPos.containing(x + -1, y + 0, z + 1))).getBlock() == leaves.getBlock()) {
					DeleteLeavesProcedure.execute(world, x + -1, y + 0, z + 1, leaves, 0);
				}
				if ((world.getBlockState(BlockPos.containing(x + -1, y + 0, z + 0))).getBlock() == leaves.getBlock()) {
					DeleteLeavesProcedure.execute(world, x + -1, y + 0, z + 0, leaves, 0);
				}
				if ((world.getBlockState(BlockPos.containing(x + -1, y + 0, z + -1))).getBlock() == leaves.getBlock()) {
					DeleteLeavesProcedure.execute(world, x + -1, y + 0, z + -1, leaves, 0);
				}
				if ((world.getBlockState(BlockPos.containing(x + 0, y + 0, z + -1))).getBlock() == leaves.getBlock()) {
					DeleteLeavesProcedure.execute(world, x + 0, y + 0, z + -1, leaves, 0);
				}
				if ((world.getBlockState(BlockPos.containing(x + 1, y + 0, z + -1))).getBlock() == leaves.getBlock()) {
					DeleteLeavesProcedure.execute(world, x + 1, y + 0, z + -1, leaves, 0);
				}
			}
		}
	}
}
