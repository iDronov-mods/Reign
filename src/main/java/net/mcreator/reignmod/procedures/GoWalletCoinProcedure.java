package net.mcreator.reignmod.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.reignmod.init.ReignModModItems;

import javax.annotation.Nullable;

import java.util.concurrent.atomic.AtomicReference;

@Mod.EventBusSubscriber
public class GoWalletCoinProcedure {
	@SubscribeEvent
	public static void onPickup(EntityItemPickupEvent event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity(), event.getItem().getItem());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		execute(null, world, x, y, z, entity, itemstack);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double index = 0;
		ItemStack wallet_copy = ItemStack.EMPTY;
		if (itemstack.is(ItemTags.create(new ResourceLocation("reign:coins")))) {
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
					wallet_copy = (new Object() {
						public ItemStack getItemStack(int sltid, Entity entity) {
							AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
							entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
								_retval.set(capability.getStackInSlot(sltid).copy());
							});
							return _retval.get();
						}
					}.getItemStack((int) index, entity));
					if (itemstack.getItem() == ReignModModItems.COPPER_COIN.get()) {
						wallet_copy.getOrCreateTag().putDouble("amount", (itemstack.getCount() + wallet_copy.getOrCreateTag().getDouble("amount")));
					} else if (itemstack.getItem() == ReignModModItems.SILVER_COIN.get()) {
						wallet_copy.getOrCreateTag().putDouble("amount", (itemstack.getCount() * 16 + wallet_copy.getOrCreateTag().getDouble("amount")));
					} else if (itemstack.getItem() == ReignModModItems.GOLD_COIN.get()) {
						wallet_copy.getOrCreateTag().putDouble("amount", (itemstack.getCount() * 256 + wallet_copy.getOrCreateTag().getDouble("amount")));
					} else {
						wallet_copy.getOrCreateTag().putDouble("amount", (itemstack.getCount() * 4096 + wallet_copy.getOrCreateTag().getDouble("amount")));
					}
					{
						final int _slotid = (int) index;
						final ItemStack _setstack = wallet_copy.copy();
						_setstack.setCount(1);
						entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
							if (capability instanceof IItemHandlerModifiable _modHandlerEntSetSlot)
								_modHandlerEntSetSlot.setStackInSlot(_slotid, _setstack);
						});
					}
					itemstack.setCount(0);
					SoundGiveCoinProcedure.execute(world, x, y, z);
					break;
				}
				index = index + 1;
			}
		}
	}
}
