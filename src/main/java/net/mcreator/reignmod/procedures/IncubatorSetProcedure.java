package net.mcreator.reignmod.procedures;

import net.mcreator.reignmod.house.HouseManager;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class IncubatorSetProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		String lordUUID = "";
		double x_coord = 0;
		double y_coord = 0;
		double z_coord = 0;
		if (IsLordProcedure.execute(world, entity)) {
			lordUUID = entity.getStringUUID();
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null)
					_blockEntity.getPersistentData().putString("UUID", (entity.getStringUUID()));
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null)
					_blockEntity.getPersistentData().putDouble("HP", HouseManager.getHouseByLordUUID(lordUUID).getHouseHP());
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			x_coord = x;
			y_coord = y;
			z_coord = z;
			HouseManager.setHouseIncubatorCoordinates(lordUUID, (int) x_coord, (int) y_coord, (int) z_coord);
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal((Component.translatable("translation.key.incubator_set").getString())), false);
		}
	}
}
