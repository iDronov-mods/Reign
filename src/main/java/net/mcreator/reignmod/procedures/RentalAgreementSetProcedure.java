package net.mcreator.reignmod.procedures;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;

public class RentalAgreementSetProcedure {
	public static boolean execute(ItemStack itemstack) {
		return !itemstack.is(ItemTags.create(new ResourceLocation("reign:rental_documents")));
	}
}
