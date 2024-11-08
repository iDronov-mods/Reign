package net.mcreator.reignmod.procedures;

import net.minecraft.world.item.ItemStack;

public class WalletNameProcedure {
	public static String execute(ItemStack itemstack) {
		return itemstack.getOrCreateTag().getString("owner");
	}
}
