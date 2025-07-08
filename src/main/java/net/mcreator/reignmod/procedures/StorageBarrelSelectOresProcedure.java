package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.market.MarketManager;
import net.mcreator.reignmod.market.MarketItem;

public class StorageBarrelSelectOresProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		StorageBarrelCategoryDeleteProcedure.execute(world, x, y, z);
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null)
				_blockEntity.getPersistentData().putString("category", "ores");
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				MarketManager.adjustBarrelCapacity(MarketItem.MarketItemType.ORES, 64);
			}
			world = _worldorig;
		}
	}
}
