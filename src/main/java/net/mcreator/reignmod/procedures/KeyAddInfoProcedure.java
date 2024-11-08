package net.mcreator.reignmod.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;

public class KeyAddInfoProcedure {
	public static String execute(ItemStack itemstack) {
		return Component.translatable("translation.key.keyinfo").getString() + " " + itemstack.getOrCreateTag().getString("owner");
	}
}
