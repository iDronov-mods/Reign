
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.reignmod.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.mcreator.reignmod.item.YourLawnIsBoolItem;
import net.mcreator.reignmod.item.WoodcutterCoinItem;
import net.mcreator.reignmod.item.WalletItem;
import net.mcreator.reignmod.item.TextbookItem;
import net.mcreator.reignmod.item.SmithCoinItem;
import net.mcreator.reignmod.item.SilverCoinItem;
import net.mcreator.reignmod.item.RuinLord3Item;
import net.mcreator.reignmod.item.RuinLord2Item;
import net.mcreator.reignmod.item.RuinLord1Item;
import net.mcreator.reignmod.item.RightHandItem;
import net.mcreator.reignmod.item.PlatinumCoinItem;
import net.mcreator.reignmod.item.MinerCoinItem;
import net.mcreator.reignmod.item.LockItem;
import net.mcreator.reignmod.item.KeyItem;
import net.mcreator.reignmod.item.HoneyConcentrateItem;
import net.mcreator.reignmod.item.GoldCoinItem;
import net.mcreator.reignmod.item.FragmentOfRecordItem;
import net.mcreator.reignmod.item.FragmentOfRecord3Item;
import net.mcreator.reignmod.item.FragmentOfRecord2Item;
import net.mcreator.reignmod.item.FarmerCoinItem;
import net.mcreator.reignmod.item.CrownItem;
import net.mcreator.reignmod.item.CopperCoinItem;
import net.mcreator.reignmod.item.BlackmarkItem;
import net.mcreator.reignmod.ReignModMod;

public class ReignModModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, ReignModMod.MODID);
	public static final RegistryObject<Item> GOLD_COIN = REGISTRY.register("gold_coin", () -> new GoldCoinItem());
	public static final RegistryObject<Item> SILVER_COIN = REGISTRY.register("silver_coin", () -> new SilverCoinItem());
	public static final RegistryObject<Item> COPPER_COIN = REGISTRY.register("copper_coin", () -> new CopperCoinItem());
	public static final RegistryObject<Item> PLATINUM_COIN = REGISTRY.register("platinum_coin", () -> new PlatinumCoinItem());
	public static final RegistryObject<Item> RIGHT_HAND = REGISTRY.register("right_hand", () -> new RightHandItem());
	public static final RegistryObject<Item> TRADE_BLOCK = block(ReignModModBlocks.TRADE_BLOCK);
	public static final RegistryObject<Item> CROWN_HELMET = REGISTRY.register("crown_helmet", () -> new CrownItem.Helmet());
	public static final RegistryObject<Item> MARKET_BLOCK = block(ReignModModBlocks.MARKET_BLOCK);
	public static final RegistryObject<Item> COFFERS = block(ReignModModBlocks.COFFERS);
	public static final RegistryObject<Item> YOUR_LAWN_IS_BOOL = REGISTRY.register("your_lawn_is_bool", () -> new YourLawnIsBoolItem());
	public static final RegistryObject<Item> FUND = block(ReignModModBlocks.FUND);
	public static final RegistryObject<Item> PLUS = block(ReignModModBlocks.PLUS);
	public static final RegistryObject<Item> WOODCUTTER_COIN = REGISTRY.register("woodcutter_coin", () -> new WoodcutterCoinItem());
	public static final RegistryObject<Item> SMITH_COIN = REGISTRY.register("smith_coin", () -> new SmithCoinItem());
	public static final RegistryObject<Item> MINER_COIN = REGISTRY.register("miner_coin", () -> new MinerCoinItem());
	public static final RegistryObject<Item> FARMER_COIN = REGISTRY.register("farmer_coin", () -> new FarmerCoinItem());
	public static final RegistryObject<Item> TEXTBOOK = REGISTRY.register("textbook", () -> new TextbookItem());
	public static final RegistryObject<Item> HOARDER_BLOCK = block(ReignModModBlocks.HOARDER_BLOCK);
	public static final RegistryObject<Item> BLACKMARK = REGISTRY.register("blackmark", () -> new BlackmarkItem());
	public static final RegistryObject<Item> LOCK = REGISTRY.register("lock", () -> new LockItem());
	public static final RegistryObject<Item> KEY = REGISTRY.register("key", () -> new KeyItem());
	public static final RegistryObject<Item> WALLET = REGISTRY.register("wallet", () -> new WalletItem());
	public static final RegistryObject<Item> HONEY_CONCENTRATE = REGISTRY.register("honey_concentrate", () -> new HoneyConcentrateItem());
	public static final RegistryObject<Item> ROCK = block(ReignModModBlocks.ROCK);
	public static final RegistryObject<Item> KINGTABLE = block(ReignModModBlocks.KINGTABLE);
	public static final RegistryObject<Item> FRAGMENT_OF_RECORD = REGISTRY.register("fragment_of_record", () -> new FragmentOfRecordItem());
	public static final RegistryObject<Item> FRAGMENT_OF_RECORD_2 = REGISTRY.register("fragment_of_record_2", () -> new FragmentOfRecord2Item());
	public static final RegistryObject<Item> FRAGMENT_OF_RECORD_3 = REGISTRY.register("fragment_of_record_3", () -> new FragmentOfRecord3Item());
	public static final RegistryObject<Item> RUIN_LORD_1 = REGISTRY.register("ruin_lord_1", () -> new RuinLord1Item());
	public static final RegistryObject<Item> RUIN_LORD_2 = REGISTRY.register("ruin_lord_2", () -> new RuinLord2Item());
	public static final RegistryObject<Item> RUIN_LORD_3 = REGISTRY.register("ruin_lord_3", () -> new RuinLord3Item());

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
