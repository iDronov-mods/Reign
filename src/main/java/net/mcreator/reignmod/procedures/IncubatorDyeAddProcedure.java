package net.mcreator.reignmod.procedures;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;

public class IncubatorDyeAddProcedure {
	public static boolean execute(ItemStack itemstack) {
		if (itemstack.getItem() == Items.CYAN_DYE) {
			return false;
		}
		return true;
	}
}
