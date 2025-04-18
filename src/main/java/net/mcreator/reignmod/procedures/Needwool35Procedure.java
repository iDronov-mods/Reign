package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class Needwool35Procedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double value = 0;
		value = new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "wool");
		if (value > 512 && value <= 1024) {
			return true;
		}
		return false;
	}
}
