package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

public class ReturnDiscontentProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z) {
		double value = 0;
		value = new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "discontent");
		if (value <= 10) {
			return Component.translatable("translation.key.discontent_1").getString();
		} else if (value <= 50) {
			return "\u00A76" + Component.translatable("translation.key.discontent_2").getString();
		} else if (value <= 80) {
			return "\u00A7c" + Component.translatable("translation.key.discontent_3").getString();
		}
		return "\u00A74" + Component.translatable("translation.key.discontent_4").getString();
	}
}
