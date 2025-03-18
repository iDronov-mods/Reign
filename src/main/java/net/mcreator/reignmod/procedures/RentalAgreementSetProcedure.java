package net.mcreator.reignmod.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

public class RentalAgreementSetProcedure {
	public static boolean execute(ItemStack itemstack) {
		return !itemstack.is(ItemTags.create(new ResourceLocation("reign:rental_documents")));
	}
}
