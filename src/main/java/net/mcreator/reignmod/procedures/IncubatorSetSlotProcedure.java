package net.mcreator.reignmod.procedures;

import net.mcreator.reignmod.init.ReignModModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;

public class IncubatorSetSlotProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, ItemStack itemstack) {
		if (itemstack.getItem() == Items.CHARCOAL || itemstack.getItem() == Items.COAL || itemstack.getItem() == Items.BREAD) {
			return true;
		}
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "houseLevel") >= 3) {
			if (itemstack.getItem() == Items.CARROT || itemstack.getItem() == Items.BEETROOT || itemstack.getItem() == Items.BEETROOT_SOUP || itemstack.getItem() == Items.BAKED_POTATO) {
				return true;
			}
		}
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "houseLevel") >= 5) {
			if (itemstack.getItem() == Items.COOKED_BEEF || itemstack.getItem() == Items.COOKED_PORKCHOP || itemstack.getItem() == Items.COOKED_MUTTON || itemstack.getItem() == Items.COOKED_CHICKEN || itemstack.getItem() == Items.COOKED_SALMON
					|| itemstack.getItem() == Items.COOKED_COD || itemstack.getItem() == Items.COOKED_RABBIT) {
				return true;
			}
		}
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "houseLevel") >= 7) {
			if (itemstack.is(ItemTags.create(new ResourceLocation("minecraft:wool")))) {
				return true;
			}
		}
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "houseLevel") >= 9) {
			if (itemstack.getItem() == Items.HONEY_BOTTLE || itemstack.getItem() == ReignModModItems.HONEY_CONCENTRATE.get()) {
				return true;
			}
		}
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "houseLevel") == 10) {
			if (itemstack.getItem() == Items.CAKE) {
				return true;
			}
		}
		return false;
	}
}
