
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.reignmod.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import net.mcreator.reignmod.block.entity.PlusBlockEntity;
import net.mcreator.reignmod.block.entity.MarketBlockBlockEntity;
import net.mcreator.reignmod.block.entity.FundBlockEntity;
import net.mcreator.reignmod.block.entity.CoffersBlockEntity;
import net.mcreator.reignmod.ReignModMod;

public class ReignModModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ReignModMod.MODID);
	public static final RegistryObject<BlockEntityType<?>> MARKET_BLOCK = register("market_block", ReignModModBlocks.MARKET_BLOCK, MarketBlockBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> COFFERS = register("coffers", ReignModModBlocks.COFFERS, CoffersBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> FUND = register("fund", ReignModModBlocks.FUND, FundBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> PLUS = register("plus", ReignModModBlocks.PLUS, PlusBlockEntity::new);

	// Start of user code block custom block entities
	// End of user code block custom block entities
	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
