package net.mcreator.reignmod.procedures;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;

public class IncubatorFuelAddProcedure {
	public static boolean execute(ItemStack itemstack) {
		if (itemstack.getItem() == Items.COAL || itemstack.getItem() == Items.CHARCOAL) {
			return false;
		}
		return true;
	}
}
