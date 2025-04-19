package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.init.ReignModModBlocks;
import net.mcreator.reignmod.house.HouseManager;
import net.mcreator.reignmod.claim.chunk.ClaimType;
import net.mcreator.reignmod.claim.chunk.ChunkClaimManager;

import java.util.UUID;

public class CreateKnightDomainProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		String UUID = "";
		boolean flag = false;
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				if (!IsLordProcedure.execute(world, entity) && IsKnightProcedure.execute(world, entity)) {
					if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == ReignModModBlocks.SHAFT.get() && (world.getBlockState(BlockPos.containing(x, y - 2, z))).getBlock() == ReignModModBlocks.SHAFT.get()) {
						if ((world.getBlockState(BlockPos.containing(x, y - 3, z))).getBlock() == ReignModModBlocks.OBELISK_FOUNDATION.get()) {
							UUID = entity.getStringUUID();
							flag = ChunkClaimManager.createClaim((ServerPlayer) entity, ClaimType.DOMAIN, (int) x, (int) y - 3, (int) z);;
							if (flag) {
								if (!world.isClientSide() && world.getServer() != null)
									world.getServer().getPlayerList()
											.broadcastSystemMessage(Component.literal((HouseManager.getDomainByKnightUUID(UUID).getDomainTitle().getString() + " " + Component.translatable("translation.key.set_knight_domain").getString())), false);
								{
									BlockPos _pos = BlockPos.containing(x, y, z);
									BlockState _bs = world.getBlockState(_pos);
									if (_bs.getBlock().getStateDefinition().getProperty("protected") instanceof BooleanProperty _booleanProp)
										world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
								}
								{
									BlockPos _pos = BlockPos.containing(x, y - 1, z);
									BlockState _bs = world.getBlockState(_pos);
									if (_bs.getBlock().getStateDefinition().getProperty("protected") instanceof BooleanProperty _booleanProp)
										world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
								}
								{
									BlockPos _pos = BlockPos.containing(x, y - 2, z);
									BlockState _bs = world.getBlockState(_pos);
									if (_bs.getBlock().getStateDefinition().getProperty("protected") instanceof BooleanProperty _booleanProp)
										world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
								}
								{
									BlockPos _pos = BlockPos.containing(x, y - 3, z);
									BlockState _bs = world.getBlockState(_pos);
									if (_bs.getBlock().getStateDefinition().getProperty("protected") instanceof BooleanProperty _booleanProp)
										world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
								}
								if (!world.isClientSide()) {
									BlockPos _bp = BlockPos.containing(x, y - 3, z);
									BlockEntity _blockEntity = world.getBlockEntity(_bp);
									BlockState _bs = world.getBlockState(_bp);
									if (_blockEntity != null)
										_blockEntity.getPersistentData().putString("UUID", (entity.getStringUUID()));
									if (world instanceof Level _level)
										_level.sendBlockUpdated(_bp, _bs, _bs, 3);
								}
								if (!world.isClientSide()) {
									BlockPos _bp = BlockPos.containing(x, y - 3, z);
									BlockEntity _blockEntity = world.getBlockEntity(_bp);
									BlockState _bs = world.getBlockState(_bp);
									if (_blockEntity != null)
										_blockEntity.getPersistentData().putString("lordUUID", ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).house));
									if (world instanceof Level _level)
										_level.sendBlockUpdated(_bp, _bs, _bs, 3);
								}
							}
						}
					}
				}
			}
			world = _worldorig;
		}
	}
}
