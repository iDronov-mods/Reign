package net.mcreator.reignmod.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.EditBox;

import net.mcreator.reignmod.init.ReignModModItems;
import net.mcreator.reignmod.basics.ReignCommon;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

public class RentalAreaProcedure {
	public static String execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return "";
		double max = 0;
		String x = "";
		String z = "";
		String stringArea = "";
		if (new Object() {
			public int getAmount(int sltid) {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
					if (stack != null)
						return stack.getCount();
				}
				return 0;
			}
		}.getAmount(0) != 0) {
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == ReignModModItems.PRIVATE_RENTAL_1
					.get()) {
				max = 256;
			} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY)
					.getItem() == ReignModModItems.PRIVATE_RENTAL_2.get()) {
				max = 512;
			} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY)
					.getItem() == ReignModModItems.PRIVATE_RENTAL_3.get()) {
				max = 1024;
			} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == ReignModModItems.STATE_RENTAL
					.get()) {
				max = 4096;
			}
			x = guistate.containsKey("text:x_value") ? ((EditBox) guistate.get("text:x_value")).getValue() : "";
			z = guistate.containsKey("text:z_value") ? ((EditBox) guistate.get("text:z_value")).getValue() : "";
			if (ReignCommon.isRectangleCorrect(x, z, max)) {
				stringArea = "" + ReignCommon.calculateRectangleArea(x, z, 0);
			} else {
				stringArea = "\u00A7c" + ReignCommon.calculateRectangleArea(x, z, 0);
			}
			return stringArea + " " + Component.translatable("translation.key.rental_area_blocks").getString() + " / " + new java.text.DecimalFormat("##").format(max);
		}
		return Component.translatable("translation.key.rental_document_empty").getString();
	}
}
