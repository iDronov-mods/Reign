
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.reignmod.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;

import net.mcreator.reignmod.block.TradeBlockBlock;
import net.mcreator.reignmod.block.StrategyBlockBlock;
import net.mcreator.reignmod.block.StorageBarrelBlock;
import net.mcreator.reignmod.block.ShaftBlock;
import net.mcreator.reignmod.block.SafeBlock;
import net.mcreator.reignmod.block.RockBlock;
import net.mcreator.reignmod.block.RentalBlockBlock;
import net.mcreator.reignmod.block.PrivatedoorBlock;
import net.mcreator.reignmod.block.PrivateShopBlock;
import net.mcreator.reignmod.block.PlusBlock;
import net.mcreator.reignmod.block.ObeliskFoundationBlock;
import net.mcreator.reignmod.block.MineBlock;
import net.mcreator.reignmod.block.MarketBlockBlock;
import net.mcreator.reignmod.block.KingtableBlock;
import net.mcreator.reignmod.block.IncubatorBlock;
import net.mcreator.reignmod.block.HoarderBlockBlock;
import net.mcreator.reignmod.block.FundBlock;
import net.mcreator.reignmod.block.FinialOfReignBlock;
import net.mcreator.reignmod.block.FinialOfMightBlock;
import net.mcreator.reignmod.block.CoffersBlock;
import net.mcreator.reignmod.ReignModMod;

public class ReignModModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, ReignModMod.MODID);
	public static final RegistryObject<Block> TRADE_BLOCK = REGISTRY.register("trade_block", () -> new TradeBlockBlock());
	public static final RegistryObject<Block> MARKET_BLOCK = REGISTRY.register("market_block", () -> new MarketBlockBlock());
	public static final RegistryObject<Block> COFFERS = REGISTRY.register("coffers", () -> new CoffersBlock());
	public static final RegistryObject<Block> FUND = REGISTRY.register("fund", () -> new FundBlock());
	public static final RegistryObject<Block> PLUS = REGISTRY.register("plus", () -> new PlusBlock());
	public static final RegistryObject<Block> HOARDER_BLOCK = REGISTRY.register("hoarder_block", () -> new HoarderBlockBlock());
	public static final RegistryObject<Block> ROCK = REGISTRY.register("rock", () -> new RockBlock());
	public static final RegistryObject<Block> KINGTABLE = REGISTRY.register("kingtable", () -> new KingtableBlock());
	public static final RegistryObject<Block> SAFE = REGISTRY.register("safe", () -> new SafeBlock());
	public static final RegistryObject<Block> PRIVATE_SHOP = REGISTRY.register("private_shop", () -> new PrivateShopBlock());
	public static final RegistryObject<Block> INCUBATOR = REGISTRY.register("incubator", () -> new IncubatorBlock());
	public static final RegistryObject<Block> PRIVATEDOOR = REGISTRY.register("privatedoor", () -> new PrivatedoorBlock());
	public static final RegistryObject<Block> RENTAL_BLOCK = REGISTRY.register("rental_block", () -> new RentalBlockBlock());
	public static final RegistryObject<Block> OBELISK_FOUNDATION = REGISTRY.register("obelisk_foundation", () -> new ObeliskFoundationBlock());
	public static final RegistryObject<Block> FINIAL_OF_REIGN = REGISTRY.register("finial_of_reign", () -> new FinialOfReignBlock());
	public static final RegistryObject<Block> FINIAL_OF_MIGHT = REGISTRY.register("finial_of_might", () -> new FinialOfMightBlock());
	public static final RegistryObject<Block> SHAFT = REGISTRY.register("shaft", () -> new ShaftBlock());
	public static final RegistryObject<Block> STORAGE_BARREL = REGISTRY.register("storage_barrel", () -> new StorageBarrelBlock());
	public static final RegistryObject<Block> STRATEGY_BLOCK = REGISTRY.register("strategy_block", () -> new StrategyBlockBlock());
	public static final RegistryObject<Block> MINE = REGISTRY.register("mine", () -> new MineBlock());

	// Start of user code block custom blocks
	// End of user code block custom blocks
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class BlocksClientSideHandler {
		@SubscribeEvent
		public static void blockColorLoad(RegisterColorHandlersEvent.Block event) {
			PlusBlock.blockColorLoad(event);
		}

		@SubscribeEvent
		public static void itemColorLoad(RegisterColorHandlersEvent.Item event) {
			PlusBlock.itemColorLoad(event);
		}
	}
}
