package net.mcreator.reignmod.procedures;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.reignmod.init.ReignModModItems;

import java.util.concurrent.atomic.AtomicReference;

public class WalletGiveProcedure {
	public static boolean execute(Entity entity, double value) {
		if (entity == null)
			return false;
		ItemStack item = ItemStack.EMPTY;
		ItemStack wallet = ItemStack.EMPTY;
		double slotIndex = 0;
		double refund = 0;
		while (slotIndex <= 35) {
			item = (new Object() {
				public ItemStack getItemStack(int sltid, Entity entity) {
					AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
					entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						_retval.set(capability.getStackInSlot(sltid).copy());
					});
					return _retval.get();
				}
			}.getItemStack((int) slotIndex, entity));
			if (item.getItem() == ReignModModItems.WALLET.get()) {
				wallet = item;
				if (wallet.getOrCreateTag().getDouble("amount") + value <= 262144) {
					wallet.getOrCreateTag().putDouble("amount", (wallet.getOrCreateTag().getDouble("amount") + value));
					{
						final int _slotid = (int) slotIndex;
						final ItemStack _setstack = wallet.copy();
						_setstack.setCount(1);
						entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
							if (capability instanceof IItemHandlerModifiable _modHandlerEntSetSlot)
								_modHandlerEntSetSlot.setStackInSlot(_slotid, _setstack);
						});
					}
					return true;
				}
			}
			slotIndex = slotIndex + 1;
		}
		refund = value;
		if (entity instanceof Player _player) {
			ItemStack _setstack = new ItemStack(ReignModModItems.PLATINUM_COIN.get()).copy();
			_setstack.setCount((int) (Math.floor(refund) / 4096));
			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
		refund = refund - Math.floor(refund / 4096) * 4096;
		if (entity instanceof Player _player) {
			ItemStack _setstack = new ItemStack(ReignModModItems.GOLD_COIN.get()).copy();
			_setstack.setCount((int) (Math.floor(refund) / 256));
			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
		refund = refund - Math.floor(refund / 256) * 256;
		if (entity instanceof Player _player) {
			ItemStack _setstack = new ItemStack(ReignModModItems.SILVER_COIN.get()).copy();
			_setstack.setCount((int) (Math.floor(refund) / 16));
			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
		refund = refund - Math.floor(refund / 16) * 16;
		if (entity instanceof Player _player) {
			ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
			_setstack.setCount((int) refund);
			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
		return true;
	}
}
