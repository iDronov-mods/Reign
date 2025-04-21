package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.init.ReignModModBlocks;
import net.mcreator.reignmod.house.HouseManager;
import net.mcreator.reignmod.claim.chunk.ClaimType;
import net.mcreator.reignmod.claim.chunk.ChunkClaimManager;

import java.util.UUID;

public class CreateFirstDomainProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		String UUID = "";
		boolean flag = false;
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				if ((world instanceof Level _lvl ? _lvl.dimension() : (world instanceof WorldGenLevel _wgl ? _wgl.getLevel().dimension() : Level.OVERWORLD)) == Level.OVERWORLD) {
					if (IsLordProcedure.execute(world, entity)) {
						if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == ReignModModBlocks.SHAFT.get() && (world.getBlockState(BlockPos.containing(x, y - 2, z))).getBlock() == ReignModModBlocks.SHAFT.get()
								&& (world.getBlockState(BlockPos.containing(x, y - 3, z))).getBlock() == ReignModModBlocks.SHAFT.get()) {
							if ((world.getBlockState(BlockPos.containing(x, y - 4, z))).getBlock() == ReignModModBlocks.INCUBATOR.get()) {
								UUID = entity.getStringUUID();
								flag = ChunkClaimManager.createClaim((ServerPlayer) entity, ClaimType.HOUSE, (int) x, (int) y - 4, (int) z);;
								if (flag) {
									IncubatorSetProcedure.execute(world, x, y - 4, z, entity);
									if (!world.isClientSide() && world.getServer() != null)
										world.getServer().getPlayerList()
												.broadcastSystemMessage(Component.literal((HouseManager.getHouseByLordUUID(UUID).getHouseTitleWithColor() + " " + Component.translatable("translation.key.set_first_domain").getString())), false);
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
									{
										BlockPos _pos = BlockPos.containing(x, y - 4, z);
										BlockState _bs = world.getBlockState(_pos);
										if (_bs.getBlock().getStateDefinition().getProperty("protected") instanceof BooleanProperty _booleanProp)
											world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
									}
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
