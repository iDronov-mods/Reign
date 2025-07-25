
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.reignmod.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import net.mcreator.reignmod.block.entity.StrategyBlockBlockEntity;
import net.mcreator.reignmod.block.entity.StorageBarrelBlockEntity;
import net.mcreator.reignmod.block.entity.SafeBlockEntity;
import net.mcreator.reignmod.block.entity.RentalBlockBlockEntity;
import net.mcreator.reignmod.block.entity.PrivatedoorBlockEntity;
import net.mcreator.reignmod.block.entity.PrivateShopBlockEntity;
import net.mcreator.reignmod.block.entity.PlusBlockEntity;
import net.mcreator.reignmod.block.entity.ObeliskFoundationBlockEntity;
import net.mcreator.reignmod.block.entity.MineBlockEntity;
import net.mcreator.reignmod.block.entity.KingtableBlockEntity;
import net.mcreator.reignmod.block.entity.IncubatorBlockEntity;
import net.mcreator.reignmod.block.entity.FundBlockEntity;
import net.mcreator.reignmod.block.entity.CoffersBlockEntity;
import net.mcreator.reignmod.ReignModMod;

public class ReignModModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ReignModMod.MODID);
	public static final RegistryObject<BlockEntityType<?>> COFFERS = register("coffers", ReignModModBlocks.COFFERS, CoffersBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> FUND = register("fund", ReignModModBlocks.FUND, FundBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> PLUS = register("plus", ReignModModBlocks.PLUS, PlusBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> KINGTABLE = register("kingtable", ReignModModBlocks.KINGTABLE, KingtableBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> SAFE = register("safe", ReignModModBlocks.SAFE, SafeBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> PRIVATE_SHOP = register("private_shop", ReignModModBlocks.PRIVATE_SHOP, PrivateShopBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> INCUBATOR = register("incubator", ReignModModBlocks.INCUBATOR, IncubatorBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> PRIVATEDOOR = register("privatedoor", ReignModModBlocks.PRIVATEDOOR, PrivatedoorBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> RENTAL_BLOCK = register("rental_block", ReignModModBlocks.RENTAL_BLOCK, RentalBlockBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> OBELISK_FOUNDATION = register("obelisk_foundation", ReignModModBlocks.OBELISK_FOUNDATION, ObeliskFoundationBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> STORAGE_BARREL = register("storage_barrel", ReignModModBlocks.STORAGE_BARREL, StorageBarrelBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> STRATEGY_BLOCK = register("strategy_block", ReignModModBlocks.STRATEGY_BLOCK, StrategyBlockBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> MINE = register("mine", ReignModModBlocks.MINE, MineBlockEntity::new);

	// Start of user code block custom block entities
	// End of user code block custom block entities
	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
