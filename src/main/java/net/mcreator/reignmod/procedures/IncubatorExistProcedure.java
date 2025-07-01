package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.init.ReignModModBlocks;

public class IncubatorExistProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, String lordUUID) {
		if (lordUUID == null)
			return false;
		BlockState incubator = Blocks.AIR.defaultBlockState();
		incubator = (world.getBlockState(BlockPos.containing(x, y, z)));
		if (incubator.getBlock() == ReignModModBlocks.INCUBATOR.get()) {
			if (incubator.getBlock().getStateDefinition().getProperty("protected") instanceof BooleanProperty _getbp2 && incubator.getValue(_getbp2)) {
				if ((new Object() {
					public String getValue(LevelAccessor world, BlockPos pos, String tag) {
						BlockEntity blockEntity = world.getBlockEntity(pos);
						if (blockEntity != null)
							return blockEntity.getPersistentData().getString(tag);
						return "";
					}
				}.getValue(world, BlockPos.containing(x, y, z), "UUID")).equals(lordUUID)) {
					return true;
				}
			}
		}
		return false;
	}
}
