package net.mcreator.reignmod.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.reignmod.world.inventory.WalletwinMenu;
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
		ItemStack wallet_copy = ItemStack.EMPTY;
		double index = 0;
		double value = 0;
		if (itemstack.is(ItemTags.create(new ResourceLocation("reign:coins")))) {
			if (itemstack.getItem() == ReignModModItems.COPPER_COIN.get()) {
				value = itemstack.getCount();
			} else if (itemstack.getItem() == ReignModModItems.SILVER_COIN.get()) {
				value = itemstack.getCount() * 16;
			} else if (itemstack.getItem() == ReignModModItems.GOLD_COIN.get()) {
				value = itemstack.getCount() * 256;
			} else {
				value = itemstack.getCount() * 4096;
			}
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
					if (entity instanceof Player _plr19 && _plr19.containerMenu instanceof WalletwinMenu) {
						if (entity instanceof Player _player)
							_player.closeContainer();
					}
					wallet_copy.getOrCreateTag().putDouble("amount", (value + wallet_copy.getOrCreateTag().getDouble("amount")));
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
