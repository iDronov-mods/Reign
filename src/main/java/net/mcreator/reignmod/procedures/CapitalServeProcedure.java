package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.kingdom.KingdomData;
import net.mcreator.reignmod.init.ReignModModBlocks;

public class CapitalServeProcedure {
	public static boolean execute(LevelAccessor world) {
		double coins = 0;
		BlockState coffers = Blocks.AIR.defaultBlockState();
		if ((world.getBlockState(BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z))).getBlock() == ReignModModBlocks.COFFERS
				.get()) {
			coins = KingdomData.getCapitalMaintenance();;
			if (new Object() {
				public double getValue(LevelAccessor world, BlockPos pos, String tag) {
					BlockEntity blockEntity = world.getBlockEntity(pos);
					if (blockEntity != null)
						return blockEntity.getPersistentData().getDouble(tag);
					return -1;
				}
			}.getValue(world, BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z), "value") >= coins) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putDouble("value", ((new Object() {
							public double getValue(LevelAccessor world, BlockPos pos, String tag) {
								BlockEntity blockEntity = world.getBlockEntity(pos);
								if (blockEntity != null)
									return blockEntity.getPersistentData().getDouble(tag);
								return -1;
							}
						}.getValue(world, BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z), "value")) - coins));
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				return true;
			}
		}
		return false;
	}
}
