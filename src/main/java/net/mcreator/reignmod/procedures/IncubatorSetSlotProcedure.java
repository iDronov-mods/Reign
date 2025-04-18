package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

public class IncubatorSetSlotProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, ItemStack itemstack) {
		if (itemstack.getItem() == Items.CHARCOAL || itemstack.getItem() == Items.COAL || itemstack.getItem() == Items.BREAD) {
			return false;
		}
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "lvl") >= 3) {
			if (itemstack.getItem() == Items.CARROT || itemstack.getItem() == Items.BEETROOT_SOUP || itemstack.getItem() == Items.BAKED_POTATO) {
				return false;
			}
		}
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "lvl") >= 4) {
			if (itemstack.getItem() == Items.COOKED_BEEF || itemstack.getItem() == Items.COOKED_PORKCHOP || itemstack.getItem() == Items.COOKED_MUTTON || itemstack.getItem() == Items.COOKED_CHICKEN || itemstack.getItem() == Items.COOKED_SALMON
					|| itemstack.getItem() == Items.COOKED_COD || itemstack.getItem() == Items.COOKED_RABBIT) {
				return false;
			}
		}
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "lvl") >= 5) {
			if (itemstack.is(ItemTags.create(new ResourceLocation("minecraft:wool")))) {
				return false;
			}
		}
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "lvl") >= 6) {
			if (itemstack.getItem() == Items.CAKE) {
				return false;
			}
		}
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "lvl") == 7) {
			if (itemstack.getItem() == Items.GOLD_INGOT || itemstack.getItem() == Items.AMETHYST_SHARD) {
				return false;
			}
		}
		return true;
	}
}
