package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.house.HouseManager;

public class IncubatorSetProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		String lordUUID = "";
		double x_coord = 0;
		double y_coord = 0;
		double z_coord = 0;
		if (IsLordProcedure.execute(world, entity)) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null)
					_blockEntity.getPersistentData().putString("UUID", (entity.getStringUUID()));
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			lordUUID = entity.getStringUUID();
			x_coord = x;
			y_coord = y;
			z_coord = z;
			HouseManager.setHouseIncubatorCoordinates(lordUUID, (int) x_coord, (int) y_coord, (int) z_coord);
		}
	}
}
