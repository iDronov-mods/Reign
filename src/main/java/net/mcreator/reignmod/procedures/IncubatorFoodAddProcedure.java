package net.mcreator.reignmod.procedures;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;

public class IncubatorFoodAddProcedure {
	public static boolean execute(ItemStack itemstack) {
		if (itemstack.getItem() == Items.BREAD || itemstack.getItem() == Items.BAKED_POTATO || itemstack.getItem() == Items.CARROT || itemstack.getItem() == Items.APPLE || itemstack.getItem() == Items.PUMPKIN_PIE) {
			return false;
		}
		return true;
	}
}
