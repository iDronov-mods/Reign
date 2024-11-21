package net.mcreator.reignmod.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

public class PrivateShopSetGoodsProcedure {
	public static boolean execute(ItemStack itemstack) {
		if (!itemstack.is(ItemTags.create(new ResourceLocation("reign:coins")))) {
			return false;
		}
		return true;
	}
}
