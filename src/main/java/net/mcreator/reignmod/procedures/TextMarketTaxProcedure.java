package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import net.mcreator.reignmod.network.ReignModModVariables;

import java.util.function.Supplier;
import java.util.Map;

public class TextMarketTaxProcedure {
	public static String execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return "";
		String tax_count = "";
		if (new Object() {
			public int getAmount(int sltid) {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
					if (stack != null)
						return stack.getCount();
				}
				return 0;
			}
		}.getAmount(68) == 0) {
			return Component.translatable("translation.key.choose_good").getString();
		} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(68)).getItem() : ItemStack.EMPTY)
				.is(ItemTags.create(new ResourceLocation("minecraft:logs")))) {
			tax_count = new java.text.DecimalFormat("##").format(Math.round(ReignModModVariables.MapVariables.get(world).logs_price * 100)) + "%";
		} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(68)).getItem() : ItemStack.EMPTY)
				.is(ItemTags.create(new ResourceLocation("minecraft:coals")))) {
			tax_count = new java.text.DecimalFormat("##").format(Math.round(ReignModModVariables.MapVariables.get(world).coal_price * 100)) + "%";
		} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(68)).getItem() : ItemStack.EMPTY)
				.is(ItemTags.create(new ResourceLocation("minecraft:tools")))) {
			tax_count = new java.text.DecimalFormat("##").format(Math.round(ReignModModVariables.MapVariables.get(world).tools_price * 100)) + "%";
		} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(68)).getItem() : ItemStack.EMPTY).getItem() instanceof ArmorItem) {
			tax_count = new java.text.DecimalFormat("##").format(Math.round(ReignModModVariables.MapVariables.get(world).armor_price * 100)) + "%";
		} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(68)).getItem() : ItemStack.EMPTY)
				.is(ItemTags.create(new ResourceLocation("minecraft:foods")))
				|| (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(68)).getItem() : ItemStack.EMPTY).getItem() == Blocks.HAY_BLOCK.asItem()) {
			tax_count = new java.text.DecimalFormat("##").format(Math.round(ReignModModVariables.MapVariables.get(world).food_price * 100)) + "%";
		} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(68)).getItem() : ItemStack.EMPTY)
				.is(ItemTags.create(new ResourceLocation("reign:miner_market")))) {
			tax_count = new java.text.DecimalFormat("##").format(Math.round(ReignModModVariables.MapVariables.get(world).ores_price * 100)) + "%";
		} else {
			tax_count = new java.text.DecimalFormat("##").format(Math.round(ReignModModVariables.MapVariables.get(world).other_price * 100)) + "%";
		}
		return Component.translatable("translation.key.good_tax").getString() + " " + tax_count;
	}
}
