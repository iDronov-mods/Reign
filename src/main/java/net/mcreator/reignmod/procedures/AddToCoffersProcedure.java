package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.kingdom.KingdomManager;
import net.mcreator.reignmod.init.ReignModModBlocks;

public class AddToCoffersProcedure {
	public static void execute(LevelAccessor world, double value) {
		double add_coins = 0;
		double x = 0;
		double y = 0;
		double z = 0;
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				add_coins = value;
				x = KingdomManager.getCoffersCoordinates()[0];
				y = KingdomManager.getCoffersCoordinates()[1];
				z = KingdomManager.getCoffersCoordinates()[2];
				if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == ReignModModBlocks.COFFERS.get()) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getPersistentData().putDouble("amount", Math.floor(new Object() {
								public double getValue(LevelAccessor world, BlockPos pos, String tag) {
									BlockEntity blockEntity = world.getBlockEntity(pos);
									if (blockEntity != null)
										return blockEntity.getPersistentData().getDouble(tag);
									return -1;
								}
							}.getValue(world, BlockPos.containing(x, y, z), "amount") + value));
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			}
			world = _worldorig;
		}
	}
}
