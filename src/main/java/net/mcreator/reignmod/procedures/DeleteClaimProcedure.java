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

public class DeleteClaimProcedure {
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
					_player.displayClientMessage(Component.literal("\u0414\u043E \u0440\u044B\u0446\u0430\u0440\u0441\u043A\u0438\u0445 \u0434\u0435\u043B"), false);
				ChunkClaimManager.removeClaim((ServerPlayer) entity, HouseManager.getDomainByKnightUUID(UUID).getClaimId());
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u041F\u043E\u0441\u043B\u0435 \u0440\u044B\u0446\u0430\u0440\u0441\u043A\u0438\u0445 \u0422\u0430\u043B\u0437\u0438\u044F\u0440\u0441\u043A\u0438\u0445 \u0434\u0435\u043B"), false);
				world.setBlock(BlockPos.containing(x, y + ChunkClaimConstants.DOMAIN_SHAFT_LENGTH, z), Blocks.AIR.defaultBlockState(), 3);
				world.setBlock(BlockPos.containing(x, y + ChunkClaimConstants.DOMAIN_SHAFT_LENGTH - 1, z), Blocks.AIR.defaultBlockState(), 3);
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
