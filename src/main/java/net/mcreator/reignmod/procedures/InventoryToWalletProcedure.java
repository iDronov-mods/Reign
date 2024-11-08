package net.mcreator.reignmod.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.init.ReignModModItems;

import java.util.concurrent.atomic.AtomicReference;

public class InventoryToWalletProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		boolean isHave = false;
		boolean CoinHave = false;
		ItemStack wallet_copy = ItemStack.EMPTY;
		ItemStack coin_copy = ItemStack.EMPTY;
		double index = 0;
		double coin_index = 0;
		double wallet_index = 0;
		index = 0;
		while (index <= 35) {
			if ((new Object() {
				public ItemStack getItemStack(int sltid, Entity entity) {
					AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
					entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						_retval.set(capability.getStackInSlot(sltid).copy());
					});
					return _retval.get();
				}
			}.getItemStack((int) index, entity)).getItem() == ReignModModItems.WALLET.get()) {
				isHave = true;
				wallet_copy = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
							_retval.set(capability.getStackInSlot(sltid).copy());
						});
						return _retval.get();
					}
				}.getItemStack((int) index, entity));
				wallet_index = index;
			} else if ((new Object() {
				public ItemStack getItemStack(int sltid, Entity entity) {
					AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
					entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						_retval.set(capability.getStackInSlot(sltid).copy());
					});
					return _retval.get();
				}
			}.getItemStack((int) index, entity)).is(ItemTags.create(new ResourceLocation("reign:coins")))) {
				CoinHave = true;
				coin_copy = (new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
							_retval.set(capability.getStackInSlot(sltid).copy());
						});
						return _retval.get();
					}
				}.getItemStack((int) index, entity));
				coin_index = index;
			}
			index = index + 1;
		}
		if (isHave && CoinHave) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("reign_mod:wallet_shake")), SoundSource.NEUTRAL, (float) 0.7, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("reign_mod:wallet_shake")), SoundSource.NEUTRAL, (float) 0.7, 1, false);
				}
			}
			while (CoinHave) {
				if (coin_copy.getItem() == ReignModModItems.COPPER_COIN.get()) {
					wallet_copy.getOrCreateTag().putDouble("amount", (coin_copy.getCount() + wallet_copy.getOrCreateTag().getDouble("amount")));
				} else if (coin_copy.getItem() == ReignModModItems.SILVER_COIN.get()) {
					wallet_copy.getOrCreateTag().putDouble("amount", (coin_copy.getCount() * 16 + wallet_copy.getOrCreateTag().getDouble("amount")));
				} else if (coin_copy.getItem() == ReignModModItems.GOLD_COIN.get()) {
					wallet_copy.getOrCreateTag().putDouble("amount", (coin_copy.getCount() * 256 + wallet_copy.getOrCreateTag().getDouble("amount")));
				} else {
					wallet_copy.getOrCreateTag().putDouble("amount", (coin_copy.getCount() * 4096 + wallet_copy.getOrCreateTag().getDouble("amount")));
				}
				{
					final int _slotid = (int) wallet_index;
					final ItemStack _setstack = wallet_copy.copy();
					_setstack.setCount(1);
					entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable _modHandlerEntSetSlot)
							_modHandlerEntSetSlot.setStackInSlot(_slotid, _setstack);
					});
				}
				if (entity instanceof Player _player) {
					ItemStack _stktoremove = coin_copy;
					_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), coin_copy.getCount(), _player.inventoryMenu.getCraftSlots());
				}
				CoinHave = false;
				index = 0;
				while (index <= 35) {
					if ((new Object() {
						public ItemStack getItemStack(int sltid, Entity entity) {
							AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
							entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
								_retval.set(capability.getStackInSlot(sltid).copy());
							});
							return _retval.get();
						}
					}.getItemStack((int) index, entity)).is(ItemTags.create(new ResourceLocation("reign:coins")))) {
						CoinHave = true;
						coin_copy = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
									_retval.set(capability.getStackInSlot(sltid).copy());
								});
								return _retval.get();
							}
						}.getItemStack((int) index, entity));
						coin_index = index;
						break;
					}
					index = index + 1;
				}
			}
		}
	}
}
