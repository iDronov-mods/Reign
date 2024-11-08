
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.reignmod.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.reignmod.ReignModMod;

public class ReignModModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ReignModMod.MODID);
	public static final RegistryObject<CreativeModeTab> CREATIVE_TAB = REGISTRY.register("creative_tab",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.reign_mod.creative_tab")).icon(() -> new ItemStack(ReignModModItems.GOLD_COIN.get())).displayItems((parameters, tabData) -> {
				tabData.accept(ReignModModItems.CROWN_HELMET.get());
				tabData.accept(ReignModModItems.RIGHT_HAND.get());
				tabData.accept(ReignModModItems.TEXTBOOK.get());
				tabData.accept(ReignModModItems.COPPER_COIN.get());
				tabData.accept(ReignModModItems.SILVER_COIN.get());
				tabData.accept(ReignModModItems.GOLD_COIN.get());
				tabData.accept(ReignModModItems.PLATINUM_COIN.get());
				tabData.accept(ReignModModBlocks.TRADE_BLOCK.get().asItem());
				tabData.accept(ReignModModBlocks.MARKET_BLOCK.get().asItem());
				tabData.accept(ReignModModBlocks.COFFERS.get().asItem());
				tabData.accept(ReignModModBlocks.FUND.get().asItem());
				tabData.accept(ReignModModBlocks.HOARDER_BLOCK.get().asItem());
				tabData.accept(ReignModModBlocks.KINGTABLE.get().asItem());
				tabData.accept(ReignModModItems.WOODCUTTER_COIN.get());
				tabData.accept(ReignModModItems.SMITH_COIN.get());
				tabData.accept(ReignModModItems.MINER_COIN.get());
				tabData.accept(ReignModModItems.FARMER_COIN.get());
				tabData.accept(ReignModModItems.BLACKMARK.get());
				tabData.accept(ReignModModItems.LOCK.get());
				tabData.accept(ReignModModItems.KEY.get());
				tabData.accept(ReignModModItems.WALLET.get());
				tabData.accept(ReignModModItems.HONEY_CONCENTRATE.get());
				tabData.accept(ReignModModItems.RUIN_LORD_2.get());
				tabData.accept(ReignModModItems.RUIN_LORD_1.get());
				tabData.accept(ReignModModItems.RUIN_LORD_3.get());
				tabData.accept(ReignModModItems.YOUR_LAWN_IS_BOOL.get());
			})

					.build());
}
