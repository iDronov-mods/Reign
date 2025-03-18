package net.mcreator.reignmod.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.reignmod.network.ReignModModVariables;

import javax.annotation.Nullable;

import java.util.concurrent.atomic.AtomicReference;

@Mod.EventBusSubscriber
public class RecipeBlockedProcedure {
	@SubscribeEvent
	public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
		execute(event, event.getEntity().level(), event.getEntity(), event.getCrafting());
	}

	public static void execute(LevelAccessor world, Entity entity, ItemStack itemstack) {
		execute(null, world, entity, itemstack);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double slotIndex = 0;
		double previousRecipe = 0;
		ItemStack ShiftItem = ItemStack.EMPTY;
		if (itemstack.is(ItemTags.create(new ResourceLocation("reign:smith_items"))) && !(entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).license_smith) {
			{
				ItemStack _ist = itemstack;
				if (_ist.hurt((int) (itemstack.getMaxDamage() * Mth.nextDouble(RandomSource.create(), 0.9, 0.98)), RandomSource.create(), null)) {
					_ist.shrink(1);
					_ist.setDamageValue(0);
				}
			}
			itemstack.getOrCreateTag().putBoolean("crafted", true);
			slotIndex = 0;
			while (slotIndex <= 35) {
				if ((new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
							_retval.set(capability.getStackInSlot(sltid).copy());
						});
						return _retval.get();
					}
				}.getItemStack((int) slotIndex, entity)).getItem() == (itemstack.copy()).getItem()) {
					ShiftItem = (new Object() {
						public ItemStack getItemStack(int sltid, Entity entity) {
							AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
							entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
								_retval.set(capability.getStackInSlot(sltid).copy());
							});
							return _retval.get();
						}
					}.getItemStack((int) slotIndex, entity));
					if (!ShiftItem.isDamaged()) {
						if (!(entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).license_smith && ShiftItem.getOrCreateTag().getBoolean("crafted") == false) {
							{
								ItemStack _ist = ShiftItem;
								if (_ist.hurt((int) (ShiftItem.getMaxDamage() * Mth.nextDouble(RandomSource.create(), 0.9, 0.98)), RandomSource.create(), null)) {
									_ist.shrink(1);
									_ist.setDamageValue(0);
								}
							}
							ShiftItem.getOrCreateTag().putBoolean("crafted", true);
							{
								final int _slotid = (int) slotIndex;
								final ItemStack _setstack = ShiftItem.copy();
								_setstack.setCount(1);
								entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable _modHandlerEntSetSlot)
										_modHandlerEntSetSlot.setStackInSlot(_slotid, _setstack);
								});
							}
						}
					}
				}
				slotIndex = slotIndex + 1;
			}
		}
		if (itemstack.is(ItemTags.create(new ResourceLocation("reign:smith_items"))) && (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).license_smith) {
			itemstack.getOrCreateTag().putBoolean("crafted", true);
			SmithProcedure.execute(world, entity, itemstack, false, 0);
			slotIndex = 0;
			while (slotIndex <= 35) {
				if ((new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
							_retval.set(capability.getStackInSlot(sltid).copy());
						});
						return _retval.get();
					}
				}.getItemStack((int) slotIndex, entity)).getItem() == (itemstack.copy()).getItem()) {
					if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).license_smith && (new Object() {
						public ItemStack getItemStack(int sltid, Entity entity) {
							AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
							entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
								_retval.set(capability.getStackInSlot(sltid).copy());
							});
							return _retval.get();
						}
					}.getItemStack((int) slotIndex, entity)).getOrCreateTag().getBoolean("crafted") == false) {
						SmithProcedure.execute(world, entity, new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
									_retval.set(capability.getStackInSlot(sltid).copy());
								});
								return _retval.get();
							}
						}.getItemStack((int) slotIndex, entity), true, slotIndex);
					} else {
						ShiftItem = (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
									_retval.set(capability.getStackInSlot(sltid).copy());
								});
								return _retval.get();
							}
						}.getItemStack((int) slotIndex, entity));
						ShiftItem.getOrCreateTag().putBoolean("crafted", true);
						{
							final int _slotid = (int) slotIndex;
							final ItemStack _setstack = ShiftItem.copy();
							_setstack.setCount(1);
							entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
								if (capability instanceof IItemHandlerModifiable _modHandlerEntSetSlot)
									_modHandlerEntSetSlot.setStackInSlot(_slotid, _setstack);
							});
						}
					}
				}
				slotIndex = slotIndex + 1;
			}
			if (itemstack.getItem() instanceof AxeItem) {
				if (entity instanceof Player _player)
					_player.giveExperiencePoints(6);
			} else if (itemstack.getItem() instanceof PickaxeItem) {
				if (entity instanceof Player _player)
					_player.giveExperiencePoints(6);
			} else if (itemstack.getItem() instanceof SwordItem) {
				if (entity instanceof Player _player)
					_player.giveExperiencePoints(4);
			} else if (itemstack.getItem() instanceof HoeItem) {
				if (entity instanceof Player _player)
					_player.giveExperiencePoints(4);
			} else if (itemstack.getItem() instanceof ShovelItem) {
				if (entity instanceof Player _player)
					_player.giveExperiencePoints(2);
			} else if (itemstack.getItem() instanceof ShieldItem) {
				if (entity instanceof Player _player)
					_player.giveExperiencePoints(3);
			} else if (itemstack.getItem() instanceof ArmorItem) {
				if (entity instanceof Player _player)
					_player.giveExperiencePoints(7);
			} else if (itemstack.getItem() instanceof BowItem) {
				if (entity instanceof Player _player)
					_player.giveExperiencePoints(6);
			} else if (itemstack.getItem() instanceof CrossbowItem) {
				if (entity instanceof Player _player)
					_player.giveExperiencePoints(6);
			}
		}
	}
}
