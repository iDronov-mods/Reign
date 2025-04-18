package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.init.ReignModModBlocks;
import net.mcreator.reignmod.claim.capital.CapitalClaimSavedData;

public class CoffersUpdateInfoProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		BlockState blockstate = Blocks.AIR.defaultBlockState();
		blockstate = (world.getBlockState(BlockPos.containing(x, y, z)));
		if (blockstate.getBlock() == ReignModModBlocks.COFFERS.get()) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null)
					_blockEntity.getPersistentData().putBoolean("safed", CapitalClaimSavedData.getInstance().isCapitalClaimsEnabled());
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
	}
}
