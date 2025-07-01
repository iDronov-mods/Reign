package net.mcreator.reignmod.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.reignmod.init.ReignModModItems;

import java.util.concurrent.atomic.AtomicReference;

public class WalletPayProcedure {
	public static boolean execute(Entity entity, double cost) {
		if (entity == null)
			return false;
		ItemStack wallet = ItemStack.EMPTY;
		ItemStack item = ItemStack.EMPTY;
		double slotIndex = 0;
		double coinsCount = 0;
		double refund = 0;
		double arrayIndex = 0;
		while (slotIndex <= 35) {
			item = (new Object() {
				public ItemStack getItemStack(int sltid, Entity entity) {
					AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
					entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						_retval.set(capability.getStackInSlot(sltid).copy());
					});
					return _retval.get();
				}
			}.getItemStack((int) slotIndex, entity)).copy();
			if (item.getItem() == ReignModModItems.WALLET.get()) {
				wallet = item.copy();
				if (wallet.getOrCreateTag().getDouble("amount") >= cost) {
					wallet.getOrCreateTag().putDouble("amount", (wallet.getOrCreateTag().getDouble("amount") - cost));
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
		slotIndex = 0;
		while (slotIndex <= 35) {
			item = (new Object() {
				public ItemStack getItemStack(int sltid, Entity entity) {
					AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
					entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						_retval.set(capability.getStackInSlot(sltid).copy());
					});
					return _retval.get();
				}
			}.getItemStack((int) slotIndex, entity)).copy();
			if (item.is(ItemTags.create(new ResourceLocation("reign:coins")))) {
				if (item.getItem() == ReignModModItems.COPPER_COIN.get()) {
					coinsCount = coinsCount + item.getCount();
				} else if (item.getItem() == ReignModModItems.SILVER_COIN.get()) {
					coinsCount = coinsCount + item.getCount() * 16;
				} else if (item.getItem() == ReignModModItems.GOLD_COIN.get()) {
					coinsCount = coinsCount + item.getCount() * 256;
				} else {
					coinsCount = coinsCount + item.getCount() * 4096;
				}
				{
					final int _slotid = (int) slotIndex;
					final ItemStack _setstack = ItemStack.EMPTY.copy();
					_setstack.setCount(1);
					entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable _modHandlerEntSetSlot)
							_modHandlerEntSetSlot.setStackInSlot(_slotid, _setstack);
					});
				}
			}
			slotIndex = slotIndex + 1;
		}
		if (coinsCount >= cost) {
			WalletGiveProcedure.execute(entity, coinsCount - cost);
			return true;
		}
		return false;
	}
}
