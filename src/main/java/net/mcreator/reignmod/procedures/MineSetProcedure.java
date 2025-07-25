package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.mines.MineManager;
import net.mcreator.reignmod.init.ReignModModItems;
import net.mcreator.reignmod.init.ReignModModBlocks;

public class MineSetProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				if (blockstate.getBlock() == Blocks.BEDROCK) {
					if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ReignModModItems.DEEPCRACK.get()) {
						var result = MineManager.setMineBlockPos(new BlockPos((int) x, (int) y, (int) z));
						if (result == MineManager.SetMineResult.SUCCESS) {
							if (entity instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal((Component.translatable("translation.key.mine_set_success").getString())), true);
							world.setBlock(BlockPos.containing(x, y, z), ReignModModBlocks.MINE.get().defaultBlockState(), 3);
							ChunkPos c = new ChunkPos(new BlockPos((int) x, (int) y, (int) z));
							var type = MineManager.getMineType(c);
							var capacity = MineManager.getMineCapacity(c);
							if (type.isPresent()) {
								if (!world.isClientSide()) {
									BlockPos _bp = BlockPos.containing(x, y, z);
									BlockEntity _blockEntity = world.getBlockEntity(_bp);
									BlockState _bs = world.getBlockState(_bp);
									if (_blockEntity != null)
										_blockEntity.getPersistentData().putString("type", type.get().name());
									if (world instanceof Level _level)
										_level.sendBlockUpdated(_bp, _bs, _bs, 3);
								}
							}
							if (capacity.isPresent()) {
								if (!world.isClientSide()) {
									BlockPos _bp = BlockPos.containing(x, y, z);
									BlockEntity _blockEntity = world.getBlockEntity(_bp);
									BlockState _bs = world.getBlockState(_bp);
									if (_blockEntity != null)
										_blockEntity.getPersistentData().putDouble("oreLeft", capacity.get());
									if (world instanceof Level _level)
										_level.sendBlockUpdated(_bp, _bs, _bs, 3);
								}
							}
							{
								ItemStack _ist = itemstack;
								if (_ist.hurt(2048, RandomSource.create(), null)) {
									_ist.shrink(1);
									_ist.setDamageValue(0);
								}
							}
						} else if (result == MineManager.SetMineResult.SKY_NOT_CLEAR) {
							if (entity instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal((Component.translatable("translation.key.mine_set_sky_not_clear").getString())), true);
							{
								ItemStack _ist = itemstack;
								if (_ist.hurt(256, RandomSource.create(), null)) {
									_ist.shrink(1);
									_ist.setDamageValue(0);
								}
							}
						} else if (result == MineManager.SetMineResult.MINE_NOT_FOUND) {
							if (entity instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal((Component.translatable("translation.key.mine_set_mine_not_found").getString())), true);
							{
								ItemStack _ist = itemstack;
								if (_ist.hurt(512, RandomSource.create(), null)) {
									_ist.shrink(1);
									_ist.setDamageValue(0);
								}
							}
						} else if (result == MineManager.SetMineResult.POSITION_ALREADY_SET) {
							if (entity instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal((Component.translatable("translation.key.mine_set_exist").getString())), true);
							{
								ItemStack _ist = itemstack;
								if (_ist.hurt(128, RandomSource.create(), null)) {
									_ist.shrink(1);
									_ist.setDamageValue(0);
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
