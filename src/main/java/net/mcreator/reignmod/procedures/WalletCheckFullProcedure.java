package net.mcreator.reignmod.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.reignmod.init.ReignModModItems;

public class WalletCheckFullProcedure {
	public static boolean execute(ItemStack itemstack) {
		double value_add = 0;
		double amount = 0;
		if (itemstack.is(ItemTags.create(new ResourceLocation("reign:coins")))) {
			if (itemstack.getItem() == ReignModModItems.COPPER_COIN.get()) {
				value_add = itemstack.getCount();
			} else if (itemstack.getItem() == ReignModModItems.SILVER_COIN.get()) {
				value_add = itemstack.getCount() * 16;
			} else if (itemstack.getItem() == ReignModModItems.GOLD_COIN.get()) {
				value_add = itemstack.getCount() * 256;
			} else {
				value_add = itemstack.getCount() * 4096;
			}
			amount = 0;
			if (value_add + amount <= 262144) {
				return true;
			}
		}
		return false;
	}
}
