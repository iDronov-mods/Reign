
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
				tabData.accept(ReignModModItems.MARSHAL_INSIGNIA.get());
				tabData.accept(ReignModModItems.TREASURER_KEY.get());
				tabData.accept(ReignModModItems.ARCHITECT_COMPASS.get());
				tabData.accept(ReignModModItems.WALLET.get());
				tabData.accept(ReignModModItems.REIGN_COIN.get());
				tabData.accept(ReignModModItems.PEN_WITH_INK.get());
				tabData.accept(ReignModModItems.HONEY_CONCENTRATE.get());
				tabData.accept(ReignModModItems.COPPER_COIN.get());
				tabData.accept(ReignModModItems.SILVER_COIN.get());
				tabData.accept(ReignModModItems.GOLD_COIN.get());
				tabData.accept(ReignModModItems.PLATINUM_COIN.get());
				tabData.accept(ReignModModItems.WOODCUTTER_COIN.get());
				tabData.accept(ReignModModItems.MINER_COIN.get());
				tabData.accept(ReignModModItems.SMITH_COIN.get());
				tabData.accept(ReignModModItems.FARMER_COIN.get());
				tabData.accept(ReignModModItems.COWBOY_COIN.get());
				tabData.accept(ReignModModBlocks.FUND.get().asItem());
				tabData.accept(ReignModModBlocks.HOARDER_BLOCK.get().asItem());
				tabData.accept(ReignModModBlocks.TRADE_BLOCK.get().asItem());
				tabData.accept(ReignModModBlocks.MARKET_BLOCK.get().asItem());
				tabData.accept(ReignModModBlocks.COFFERS.get().asItem());
				tabData.accept(ReignModModBlocks.SAFE.get().asItem());
				tabData.accept(ReignModModBlocks.STORAGE_BARREL.get().asItem());
				tabData.accept(ReignModModBlocks.KINGTABLE.get().asItem());
				tabData.accept(ReignModModBlocks.RENTAL_BLOCK.get().asItem());
				tabData.accept(ReignModModBlocks.PRIVATE_SHOP.get().asItem());
				tabData.accept(ReignModModItems.HEART_OF_HOUSE.get());
				tabData.accept(ReignModModBlocks.INCUBATOR.get().asItem());
				tabData.accept(ReignModModBlocks.FINIAL_OF_REIGN.get().asItem());
				tabData.accept(ReignModModBlocks.OBELISK_FOUNDATION.get().asItem());
				tabData.accept(ReignModModBlocks.FINIAL_OF_MIGHT.get().asItem());
				tabData.accept(ReignModModBlocks.SHAFT.get().asItem());
				tabData.accept(ReignModModBlocks.STRATEGY_BLOCK.get().asItem());
				tabData.accept(ReignModModBlocks.PRIVATEDOOR.get().asItem());
				tabData.accept(ReignModModItems.SHACKLES.get());
				tabData.accept(ReignModModItems.COPPER_LOCK.get());
				tabData.accept(ReignModModItems.IRON_LOCK.get());
				tabData.accept(ReignModModItems.GOLD_LOCK.get());
				tabData.accept(ReignModModItems.PICKLOCK.get());
				tabData.accept(ReignModModItems.ENDER_PICKLOCK.get());
				tabData.accept(ReignModModItems.DEEPCRACK.get());
				tabData.accept(ReignModModItems.BLACKMARK.get());
				tabData.accept(ReignModModItems.ACCURSED_BLACK_MARK.get());
				tabData.accept(ReignModModItems.BOTTLE_OF_COAL_DUST.get());
				tabData.accept(ReignModModItems.DEEP_ROCK_SAMPLE.get());
				tabData.accept(ReignModModItems.SOURCE_BOTTLE.get());
				tabData.accept(ReignModModItems.PRIVATE_RENTAL_1.get());
				tabData.accept(ReignModModItems.PRIVATE_RENTAL_2.get());
				tabData.accept(ReignModModItems.PRIVATE_RENTAL_3.get());
				tabData.accept(ReignModModItems.STATE_RENTAL.get());
				tabData.accept(ReignModModItems.TRADE_LICENSE.get());
				tabData.accept(ReignModModItems.CONTRACT_KILL.get());
				tabData.accept(ReignModModItems.RAWSOURCE.get());
				tabData.accept(ReignModModItems.YOUR_LAWN_IS_BOOL.get());
				tabData.accept(ReignModModItems.RUIN_LORD_3.get());
				tabData.accept(ReignModModItems.RUIN_LORD_2.get());
				tabData.accept(ReignModModItems.RUIN_LORD_1.get());
			}).build());
}
