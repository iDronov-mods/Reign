package net.mcreator.reignmod.procedures;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;

public class IncubatorMeatAddProcedure {
	public static boolean execute(ItemStack itemstack) {
		if (itemstack.getItem() == Items.COOKED_RABBIT || itemstack.getItem() == Items.COOKED_MUTTON || itemstack.getItem() == Items.COOKED_CHICKEN || itemstack.getItem() == Items.COOKED_BEEF || itemstack.getItem() == Items.COOKED_PORKCHOP
				|| itemstack.getItem() == Items.COOKED_COD || itemstack.getItem() == Items.COOKED_SALMON) {
			return false;
		}
		return true;
	}
}
