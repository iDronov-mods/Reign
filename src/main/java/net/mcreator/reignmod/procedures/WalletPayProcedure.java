package net.mcreator.reignmod.procedures;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import net.mcreator.reignmod.init.ReignModModItems;

import java.util.concurrent.atomic.AtomicReference;
import java.util.List;
import java.util.ArrayList;

public class WalletPayProcedure {
	public static boolean execute(Entity entity, double cost) {
		if (entity == null)
			return false;
		ItemStack wallet = ItemStack.EMPTY;
		ItemStack item = ItemStack.EMPTY;
		List<Object> coinsArray = new ArrayList<>();
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
			}.getItemStack((int) slotIndex, entity));
			if (item.getItem() == ReignModModItems.WALLET.get()) {
				wallet = item;
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
				coinsArray.add(slotIndex);
				if (coinsCount >= cost) {
					for (int index1 = 0; index1 < (int) coinsArray.size(); index1++) {
						{
							final int _slotid = (int) (coinsArray.get((int) arrayIndex) instanceof Double _d ? _d : 0);
							final ItemStack _setstack = ItemStack.EMPTY.copy();
							_setstack.setCount(0);
							entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
								if (capability instanceof IItemHandlerModifiable _modHandlerEntSetSlot)
									_modHandlerEntSetSlot.setStackInSlot(_slotid, _setstack);
							});
						}
						arrayIndex = arrayIndex + 1;
					}
					refund = coinsCount - cost;
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("Refund: " + refund)), false);
					if (refund != 0) {
						if (entity instanceof Player _player) {
							ItemStack _setstack = new ItemStack(ReignModModItems.PLATINUM_COIN.get()).copy();
							_setstack.setCount((int) (Math.floor(refund) / 4096));
							ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
						}
						refund = refund - Math.floor(refund / 4096) * 4096;
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal(("\u041F\u043E\u0441\u043B\u0435 \u043F\u043B\u0430\u0442\u0438\u043D\u044B:" + refund)), false);
						if (entity instanceof Player _player) {
							ItemStack _setstack = new ItemStack(ReignModModItems.GOLD_COIN.get()).copy();
							_setstack.setCount((int) (Math.floor(refund) / 256));
							ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
						}
						refund = refund - Math.floor(refund / 256) * 256;
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal(("\u041F\u043E\u0441\u043B\u0435 \u0437\u043E\u043B\u043E\u0442\u0430:" + refund)), false);
						if (entity instanceof Player _player) {
							ItemStack _setstack = new ItemStack(ReignModModItems.SILVER_COIN.get()).copy();
							_setstack.setCount((int) (Math.floor(refund) / 16));
							ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
						}
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal(("\u041F\u043E\u0441\u043B\u0435 \u041F\u043E\u0441\u043B\u0435 \u0441\u0435\u0440\u0435\u0431\u0440\u0430:" + refund)), false);
						refund = refund - Math.floor(refund / 16) * 16;
						if (entity instanceof Player _player) {
							ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
							_setstack.setCount((int) refund);
							ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
						}
					}
					return true;
				}
			}
			slotIndex = slotIndex + 1;
		}
		return false;
	}
}
