package net.mcreator.reignmod.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.init.ReignModModBlocks;
import net.mcreator.reignmod.ReignModMod;

public class CapitalCreateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!ReignModModVariables.MapVariables.get(world).CapitalHave) {
			ReignModModVariables.MapVariables.get(world).CAPITAL_X = Math.floor(entity.getX());
			ReignModModVariables.MapVariables.get(world).syncData(world);
			ReignModModVariables.MapVariables.get(world).CAPITAL_Y = Math.floor(entity.getY());
			ReignModModVariables.MapVariables.get(world).syncData(world);
			ReignModModVariables.MapVariables.get(world).CAPITAL_Z = Math.floor(entity.getZ());
			ReignModModVariables.MapVariables.get(world).syncData(world);
			world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X, ReignModModVariables.MapVariables.get(world).CAPITAL_Y, ReignModModVariables.MapVariables.get(world).CAPITAL_Z),
					ReignModModBlocks.FUND.get().defaultBlockState(), 3);
			world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X + 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y - 1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z + 4),
					Blocks.STRIPPED_OAK_LOG.defaultBlockState(), 3);
			world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X + 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y - 1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z - 4),
					Blocks.STRIPPED_OAK_LOG.defaultBlockState(), 3);
			world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X - 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y - 1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z + 4),
					Blocks.STRIPPED_OAK_LOG.defaultBlockState(), 3);
			world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X - 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y - 1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z - 4),
					Blocks.STRIPPED_OAK_LOG.defaultBlockState(), 3);
			world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X - 0, ReignModModVariables.MapVariables.get(world).CAPITAL_Y - 1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z - 0),
					Blocks.BEDROCK.defaultBlockState(), 3);
			ReignModMod.queueServerWork(20, () -> {
				world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X - 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y, ReignModModVariables.MapVariables.get(world).CAPITAL_Z + 4),
						Blocks.SPRUCE_FENCE.defaultBlockState(), 3);
				world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X - 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y, ReignModModVariables.MapVariables.get(world).CAPITAL_Z - 4),
						Blocks.SPRUCE_FENCE.defaultBlockState(), 3);
				world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X + 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y, ReignModModVariables.MapVariables.get(world).CAPITAL_Z - 4),
						Blocks.SPRUCE_FENCE.defaultBlockState(), 3);
				world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X + 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y, ReignModModVariables.MapVariables.get(world).CAPITAL_Z + 4),
						Blocks.SPRUCE_FENCE.defaultBlockState(), 3);
			});
			ReignModMod.queueServerWork(40, () -> {
				world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X - 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y + 1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z + 4),
						ReignModModBlocks.PLUS.get().defaultBlockState(), 3);
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X - 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y + 1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z + 4);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putDouble("numKingdom", 1);
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X - 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y + 1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z - 4),
						ReignModModBlocks.PLUS.get().defaultBlockState(), 3);
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X - 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y + 1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z - 4);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putDouble("numKingdom", 2);
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X + 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y + 1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z - 4),
						ReignModModBlocks.PLUS.get().defaultBlockState(), 3);
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X + 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y + 1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z - 4);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putDouble("numKingdom", 3);
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X + 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y + 1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z + 4),
						ReignModModBlocks.PLUS.get().defaultBlockState(), 3);
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X + 4, ReignModModVariables.MapVariables.get(world).CAPITAL_Y + 1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z + 4);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putDouble("numKingdom", 4);
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			});
			ReignModModVariables.MapVariables.get(world).CapitalHave = true;
			ReignModModVariables.MapVariables.get(world).syncData(world);
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "setworldspawn");
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("The capital exists."), false);
		}
	}
}
