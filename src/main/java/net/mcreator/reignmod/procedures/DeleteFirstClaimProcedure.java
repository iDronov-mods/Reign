package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.house.HouseManager;
import net.mcreator.reignmod.claim.chunk.ChunkClaimManager;
import net.mcreator.reignmod.claim.chunk.ChunkClaimConstants;

import java.util.UUID;

public class DeleteFirstClaimProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		String UUID = "";
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				UUID = entity.getStringUUID();
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u0417\u0410\u041F\u0423\u0421\u041A\u0410\u042E \u041A\u041E\u0414 \u0422\u0410\u041B\u0417\u0418\u042F\u0420\u0410"), false);
				ChunkClaimManager.removeClaim((ServerPlayer) entity, HouseManager.getHouseByLordUUID(UUID).getClaimId());
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u041A\u041E\u0414 \u0412\u042B\u041F\u041E\u041B\u041D\u0415\u041D"), false);
				world.setBlock(BlockPos.containing(x, y + ChunkClaimConstants.HOUSE_SHAFT_LENGTH, z), Blocks.AIR.defaultBlockState(), 3);
				world.setBlock(BlockPos.containing(x, y + ChunkClaimConstants.HOUSE_SHAFT_LENGTH - 1, z), Blocks.AIR.defaultBlockState(), 3);
				world.setBlock(BlockPos.containing(x, y + ChunkClaimConstants.HOUSE_SHAFT_LENGTH - 2, z), Blocks.AIR.defaultBlockState(), 3);
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("protected") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
				{
					BlockPos _pos = BlockPos.containing(x, y + 1, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("protected") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
				if (entity instanceof Player _player)
					_player.closeContainer();
			}
			world = _worldorig;
		}
	}
}
