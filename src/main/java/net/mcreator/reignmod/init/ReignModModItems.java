
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.reignmod.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.BlockItem;

import net.mcreator.reignmod.item.YourLawnIsBoolItem;
import net.mcreator.reignmod.item.WoodcutterCoinItem;
import net.mcreator.reignmod.item.WalletItem;
import net.mcreator.reignmod.item.TreasurerKeyItem;
import net.mcreator.reignmod.item.TradeLicenseItem;
import net.mcreator.reignmod.item.TextbookItem;
import net.mcreator.reignmod.item.StateRentalItem;
import net.mcreator.reignmod.item.SmithCoinItem;
import net.mcreator.reignmod.item.SilverCoinItem;
import net.mcreator.reignmod.item.ShacklesItem;
import net.mcreator.reignmod.item.RuinLord3Item;
import net.mcreator.reignmod.item.RuinLord2Item;
import net.mcreator.reignmod.item.RuinLord1Item;
import net.mcreator.reignmod.item.RightHandItem;
import net.mcreator.reignmod.item.PrivateRental3Item;
import net.mcreator.reignmod.item.PrivateRental2Item;
import net.mcreator.reignmod.item.PrivateRental1Item;
import net.mcreator.reignmod.item.PlatinumCoinItem;
import net.mcreator.reignmod.item.PicklockItem;
import net.mcreator.reignmod.item.PenWithInkItem;
import net.mcreator.reignmod.item.MinerCoinItem;
import net.mcreator.reignmod.item.MarshalInsigniaItem;
import net.mcreator.reignmod.item.IronLockItem;
import net.mcreator.reignmod.item.HoneyConcentrateItem;
import net.mcreator.reignmod.item.HeartOfHouseItem;
import net.mcreator.reignmod.item.GoldLockItem;
import net.mcreator.reignmod.item.GoldCoinItem;
import net.mcreator.reignmod.item.FragmentOfRecordItem;
import net.mcreator.reignmod.item.FragmentOfRecord3Item;
import net.mcreator.reignmod.item.FragmentOfRecord2Item;
import net.mcreator.reignmod.item.FarmerCoinItem;
import net.mcreator.reignmod.item.EnderPicklockItem;
import net.mcreator.reignmod.item.CrownItem;
import net.mcreator.reignmod.item.CowboyCoinItem;
import net.mcreator.reignmod.item.CopperLockItem;
import net.mcreator.reignmod.item.CopperCoinItem;
import net.mcreator.reignmod.item.ContractKillItem;
import net.mcreator.reignmod.item.BlackmarkItem;
import net.mcreator.reignmod.item.ArchitectCompassItem;
import net.mcreator.reignmod.item.AccursedBlackMarkItem;
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
	public static final RegistryObject<Item> PRIVATE_RENTAL_1 = REGISTRY.register("private_rental_1", () -> new PrivateRental1Item());
	public static final RegistryObject<Item> PRIVATE_RENTAL_2 = REGISTRY.register("private_rental_2", () -> new PrivateRental2Item());
	public static final RegistryObject<Item> PRIVATE_RENTAL_3 = REGISTRY.register("private_rental_3", () -> new PrivateRental3Item());
	public static final RegistryObject<Item> CONTRACT_KILL = REGISTRY.register("contract_kill", () -> new ContractKillItem());
	public static final RegistryObject<Item> GOLD_LOCK = REGISTRY.register("gold_lock", () -> new GoldLockItem());
	public static final RegistryObject<Item> COPPER_LOCK = REGISTRY.register("copper_lock", () -> new CopperLockItem());
	public static final RegistryObject<Item> SAFE = block(ReignModModBlocks.SAFE);
	public static final RegistryObject<Item> TRADE_LICENSE = REGISTRY.register("trade_license", () -> new TradeLicenseItem());
	public static final RegistryObject<Item> PEN_WITH_INK = REGISTRY.register("pen_with_ink", () -> new PenWithInkItem());
	public static final RegistryObject<Item> IRON_LOCK = REGISTRY.register("iron_lock", () -> new IronLockItem());
	public static final RegistryObject<Item> PRIVATE_SHOP = block(ReignModModBlocks.PRIVATE_SHOP);
	public static final RegistryObject<Item> COWBOY_COIN = REGISTRY.register("cowboy_coin", () -> new CowboyCoinItem());
	public static final RegistryObject<Item> PICKLOCK = REGISTRY.register("picklock", () -> new PicklockItem());
	public static final RegistryObject<Item> INCUBATOR = block(ReignModModBlocks.INCUBATOR);
	public static final RegistryObject<Item> HEART_OF_HOUSE = REGISTRY.register("heart_of_house", () -> new HeartOfHouseItem());
	public static final RegistryObject<Item> ACCURSED_BLACK_MARK = REGISTRY.register("accursed_black_mark", () -> new AccursedBlackMarkItem());
	public static final RegistryObject<Item> PRIVATEDOOR = doubleBlock(ReignModModBlocks.PRIVATEDOOR);
	public static final RegistryObject<Item> RENTAL_BLOCK = block(ReignModModBlocks.RENTAL_BLOCK);
	public static final RegistryObject<Item> OBELISK_FOUNDATION = block(ReignModModBlocks.OBELISK_FOUNDATION);
	public static final RegistryObject<Item> ENDER_PICKLOCK = REGISTRY.register("ender_picklock", () -> new EnderPicklockItem());
	public static final RegistryObject<Item> SHACKLES = REGISTRY.register("shackles", () -> new ShacklesItem());
	public static final RegistryObject<Item> FINIAL_OF_REIGN = block(ReignModModBlocks.FINIAL_OF_REIGN);
	public static final RegistryObject<Item> FINIAL_OF_MIGHT = block(ReignModModBlocks.FINIAL_OF_MIGHT);
	public static final RegistryObject<Item> SHAFT = block(ReignModModBlocks.SHAFT);
	public static final RegistryObject<Item> ARCHITECT_COMPASS = REGISTRY.register("architect_compass", () -> new ArchitectCompassItem());
	public static final RegistryObject<Item> TREASURER_KEY = REGISTRY.register("treasurer_key", () -> new TreasurerKeyItem());
	public static final RegistryObject<Item> MARSHAL_INSIGNIA = REGISTRY.register("marshal_insignia", () -> new MarshalInsigniaItem());
	public static final RegistryObject<Item> STATE_RENTAL = REGISTRY.register("state_rental", () -> new StateRentalItem());

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}

	private static RegistryObject<Item> doubleBlock(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new DoubleHighBlockItem(block.get(), new Item.Properties()));
	}
}
