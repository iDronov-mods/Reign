package net.mcreator.reignmod.procedures;

import net.minecraft.world.item.ItemStack;

public class WalletNameProcedure {
	public static String execute(ItemStack itemstack) {
		if (!(itemstack.getOrCreateTag().getString("owner")).isEmpty()) {
			return itemstack.getOrCreateTag().getString("owner") + " \u00A7r \u00A77" + new java.text.DecimalFormat("##").format(itemstack.getOrCreateTag().getDouble("amount"));
		}
		return "";
	}
}
