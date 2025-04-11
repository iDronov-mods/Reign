package net.mcreator.reignmod.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.world.inventory.CoffersUIMenu;
import net.mcreator.reignmod.kingdom.KingdomData;
import net.mcreator.reignmod.init.ReignModModItems;

import java.util.function.Supplier;
import java.util.Map;

import io.netty.buffer.Unpooled;

public class CoffersOpenProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double amount = 0;
		double coins_count = 0;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("reign:coins")))) {
			CoffersDonateProcedure.execute(world, x, y, z, entity);
		} else {
			if (IsKingProcedure.execute(world, entity) || IsRightHandProcedure.execute(entity) || IsTreasurerProcedure.execute(entity)) {
				if (world instanceof ServerLevel _origLevel) {
					LevelAccessor _worldorig = world;
					world = _origLevel.getServer().getLevel(Level.OVERWORLD);
					if (world != null) {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("reign_mod:safe_open")), SoundSource.BLOCKS, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("reign_mod:safe_open")), SoundSource.BLOCKS, 1, 1, false);
							}
						}
					}
					world = _worldorig;
				}
				if (entity instanceof ServerPlayer _ent) {
					BlockPos _bpos = BlockPos.containing(x, y, z);
					NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
						@Override
						public Component getDisplayName() {
							return Component.literal("CoffersUI");
						}

						@Override
						public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
							return new CoffersUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
						}
					}, _bpos);
				}
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putDouble("service", KingdomData.getCapitalMaintenance());
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(0)).set(ItemStack.EMPTY);
					_player.containerMenu.broadcastChanges();
				}
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(1)).set(ItemStack.EMPTY);
					_player.containerMenu.broadcastChanges();
				}
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(2)).set(ItemStack.EMPTY);
					_player.containerMenu.broadcastChanges();
				}
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(3)).set(ItemStack.EMPTY);
					_player.containerMenu.broadcastChanges();
				}
				amount = new Object() {
					public double getValue(LevelAccessor world, BlockPos pos, String tag) {
						BlockEntity blockEntity = world.getBlockEntity(pos);
						if (blockEntity != null)
							return blockEntity.getPersistentData().getDouble(tag);
						return -1;
					}
				}.getValue(world, BlockPos.containing(x, y, z), "amount");
				if (amount >= 4096) {
					coins_count = Math.floor(amount / 4096);
					if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
						ItemStack _setstack = new ItemStack(ReignModModItems.PLATINUM_COIN.get()).copy();
						_setstack.setCount((int) coins_count);
						((Slot) _slots.get(3)).set(_setstack);
						_player.containerMenu.broadcastChanges();
					}
					amount = amount - coins_count * 4096;
				}
				if (amount >= 256) {
					if (amount >= 16384) {
						coins_count = 64;
					} else {
						coins_count = Math.floor(amount / 256);
					}
					if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
						ItemStack _setstack = new ItemStack(ReignModModItems.GOLD_COIN.get()).copy();
						_setstack.setCount((int) coins_count);
						((Slot) _slots.get(2)).set(_setstack);
						_player.containerMenu.broadcastChanges();
					}
					amount = amount - coins_count * 256;
				}
				if (amount >= 16) {
					if (amount >= 1024) {
						coins_count = 64;
					} else {
						coins_count = Math.floor(amount / 16);
					}
					if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
						ItemStack _setstack = new ItemStack(ReignModModItems.SILVER_COIN.get()).copy();
						_setstack.setCount((int) coins_count);
						((Slot) _slots.get(1)).set(_setstack);
						_player.containerMenu.broadcastChanges();
					}
					amount = amount - coins_count * 16;
				}
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
					_setstack.setCount((int) amount);
					((Slot) _slots.get(0)).set(_setstack);
					_player.containerMenu.broadcastChanges();
				}
			} else {
				if (world instanceof ServerLevel _origLevel) {
					LevelAccessor _worldorig = world;
					world = _origLevel.getServer().getLevel(Level.OVERWORLD);
					if (world != null) {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("reign_mod:locked_chest")), SoundSource.BLOCKS, (float) 0.3, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("reign_mod:locked_chest")), SoundSource.BLOCKS, (float) 0.3, 1, false);
							}
						}
					}
					world = _worldorig;
				}
			}
		}
	}
}
