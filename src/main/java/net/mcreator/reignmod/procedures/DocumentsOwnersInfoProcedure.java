package net.mcreator.reignmod.procedures;

import net.minecraft.world.item.ItemStack;

public class DocumentsOwnersInfoProcedure {
	public static String execute(ItemStack itemstack) {
		return "\u00A77\u00A7o" + itemstack.getOrCreateTag().getString("owners");
	}
}
