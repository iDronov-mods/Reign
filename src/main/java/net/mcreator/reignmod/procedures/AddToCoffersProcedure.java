package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.init.ReignModModBlocks;

public class AddToCoffersProcedure {
	public static void execute(LevelAccessor world, double value) {
		double add_coins = 0;
		add_coins = value;
		if ((world.getBlockState(BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z))).getBlock() == ReignModModBlocks.COFFERS
				.get()) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z);
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
					}.getValue(world, BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z), "amount") + value));
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
	}
}
