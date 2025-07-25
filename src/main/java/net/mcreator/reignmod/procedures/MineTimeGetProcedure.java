package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

public class MineTimeGetProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z) {
		String hour_text = "";
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "remaningHours") > 0) {
			hour_text = Component.translatable("translation.key.mine.remaning_hours").getString() + " " + (new Object() {
				public double getValue(LevelAccessor world, BlockPos pos, String tag) {
					BlockEntity blockEntity = world.getBlockEntity(pos);
					if (blockEntity != null)
						return blockEntity.getPersistentData().getDouble(tag);
					return -1;
				}
			}.getValue(world, BlockPos.containing(x, y, z), "remainingHours")) + " " + Component.translatable("translation.key.mine.remaning_hours_end").getString();
		} else {
			hour_text = Component.translatable("translation.key.mine.dont_work").getString();
		}
		return hour_text;
	}
}
