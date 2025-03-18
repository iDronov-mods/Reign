package net.mcreator.reignmod.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.Screen;

import net.mcreator.reignmod.init.ReignModModItems;
import net.mcreator.reignmod.init.ReignModModBlocks;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class TooltipRendererProcedure {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		execute(event, event.getItemStack(), event.getToolTip());
	}

	public static void execute(ItemStack itemstack, List<Component> tooltip) {
		execute(null, itemstack, tooltip);
	}

	private static void execute(@Nullable Event event, ItemStack itemstack, List<Component> tooltip) {
		if (tooltip == null)
			return;
		if (itemstack.getItem() == ReignModModBlocks.HOARDER_BLOCK.get().asItem()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.hoarder").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.hoarder_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModBlocks.FUND.get().asItem()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.fund").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.fund_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModBlocks.SAFE.get().asItem()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.safe").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.safe_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModBlocks.COFFERS.get().asItem()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.coffers").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.coffers_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModBlocks.RENTAL_BLOCK.get().asItem()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.rentalblock").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.rentalblock_short").getString())));
			}
		}
		if (itemstack.getItem() == ReignModModBlocks.MARKET_BLOCK.get().asItem()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.market").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.market_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModBlocks.TRADE_BLOCK.get().asItem()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.trader").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.trader_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModItems.COPPER_LOCK.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.copperlock").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.copperlock_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModItems.IRON_LOCK.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.ironlock").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.ironlock_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModItems.GOLD_LOCK.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.goldlock").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.goldlock_short").getString())));
			}
		}
	}
}
