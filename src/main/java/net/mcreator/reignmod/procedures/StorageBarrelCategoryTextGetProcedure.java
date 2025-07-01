package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

public class StorageBarrelCategoryTextGetProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z) {
		String category = "";
		category = new Object() {
			public String getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getString(tag);
				return "";
			}
		}.getValue(world, BlockPos.containing(x, y, z), "category");
		if ((category).equals("logs")) {
			return Component.translatable("translation.storage_barrel.logs").getString();
		} else if ((category).equals("fuel")) {
			return Component.translatable("translation.storage_barrel.fuel").getString();
		} else if ((category).equals("ores")) {
			return Component.translatable("translation.storage_barrel.ores").getString();
		} else if ((category).equals("tools")) {
			return Component.translatable("translation.storage_barrel.tools").getString();
		} else if ((category).equals("armors")) {
			return Component.translatable("translation.storage_barrel.armors").getString();
		} else if ((category).equals("blocks")) {
			return Component.translatable("translation.storage_barrel.blocks").getString();
		} else if ((category).equals("foods")) {
			return Component.translatable("translation.storage_barrel.foods").getString();
		} else if ((category).equals("other")) {
			return Component.translatable("translation.storage_barrel.other").getString();
		} else if ((category).equals("exp")) {
			return Component.translatable("translation.storage_barrel.exp").getString();
		}
		return "\u00A7r" + Component.translatable("translation.storage_barrel.none").getString();
	}
}
