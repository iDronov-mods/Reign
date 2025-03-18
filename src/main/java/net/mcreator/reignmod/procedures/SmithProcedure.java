package net.mcreator.reignmod.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.reignmod.network.ReignModModVariables;

public class SmithProcedure {
	public static void execute(LevelAccessor world, Entity entity, ItemStack itemstack, boolean shift, double slot) {
		if (entity == null)
			return;
		ItemStack Shift = ItemStack.EMPTY;
		if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).license_smith) {
			if (!shift) {
				if (itemstack.is(ItemTags.create(new ResourceLocation("minecraft:tools"))) || itemstack.getItem() instanceof ArmorItem) {
					if (Mth.nextInt(RandomSource.create(), 1, 100) > (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).efficiency) {
						{
							ItemStack _ist = itemstack;
							if (_ist.hurt((int) (itemstack.getMaxDamage() * Mth.nextDouble(RandomSource.create(), 0.3, 0.7)), RandomSource.create(), null)) {
								_ist.shrink(1);
								_ist.setDamageValue(0);
							}
						}
					}
					if (((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).MAIN_LVL < 3
							|| !(entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).R_LVL)
							&& (itemstack.getItem() == Items.DIAMOND_SWORD || itemstack.getItem() == Items.NETHERITE_SWORD)) {
						{
							ItemStack _ist = itemstack;
							if (_ist.hurt((int) (itemstack.getMaxDamage() * Mth.nextDouble(RandomSource.create(), 0.8, 0.95)), RandomSource.create(), null)) {
								_ist.shrink(1);
								_ist.setDamageValue(0);
							}
						}
					}
					if (((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).MAIN_LVL < 3
							|| (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).R_LVL)
							&& (itemstack.getItem() == Items.DIAMOND_HELMET || itemstack.getItem() == Items.DIAMOND_CHESTPLATE || itemstack.getItem() == Items.DIAMOND_LEGGINGS || itemstack.getItem() == Items.DIAMOND_BOOTS
									|| itemstack.getItem() == Items.NETHERITE_HELMET || itemstack.getItem() == Items.NETHERITE_CHESTPLATE || itemstack.getItem() == Items.NETHERITE_LEGGINGS || itemstack.getItem() == Items.NETHERITE_BOOTS)) {
						{
							ItemStack _ist = itemstack;
							if (_ist.hurt((int) (itemstack.getMaxDamage() * Mth.nextDouble(RandomSource.create(), 0.8, 0.95)), RandomSource.create(), null)) {
								_ist.shrink(1);
								_ist.setDamageValue(0);
							}
						}
					}
					itemstack.getOrCreateTag().putBoolean("crafted", true);
				}
			} else {
				Shift = (itemstack.copy());
				if (Shift.is(ItemTags.create(new ResourceLocation("minecraft:tools"))) || Shift.getItem() instanceof ArmorItem) {
					if (Mth.nextInt(RandomSource.create(), 1, 100) > (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).efficiency) {
						{
							ItemStack _ist = Shift;
							if (_ist.hurt((int) (Shift.getMaxDamage() * Mth.nextDouble(RandomSource.create(), 0.3, 0.7)), RandomSource.create(), null)) {
								_ist.shrink(1);
								_ist.setDamageValue(0);
							}
						}
					}
					if (((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).MAIN_LVL < 3
							|| !(entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).R_LVL)
							&& (Shift.getItem() == Items.DIAMOND_SWORD || Shift.getItem() == Items.NETHERITE_SWORD)) {
						{
							ItemStack _ist = Shift;
							if (_ist.hurt((int) (Shift.getMaxDamage() * Mth.nextDouble(RandomSource.create(), 0.8, 0.95)), RandomSource.create(), null)) {
								_ist.shrink(1);
								_ist.setDamageValue(0);
							}
						}
					}
					if (((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).MAIN_LVL < 3
							|| (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).R_LVL)
							&& (Shift.getItem() == Items.DIAMOND_HELMET || Shift.getItem() == Items.DIAMOND_CHESTPLATE || Shift.getItem() == Items.DIAMOND_LEGGINGS || Shift.getItem() == Items.DIAMOND_BOOTS || Shift.getItem() == Items.NETHERITE_HELMET
									|| Shift.getItem() == Items.NETHERITE_CHESTPLATE || Shift.getItem() == Items.NETHERITE_LEGGINGS || Shift.getItem() == Items.NETHERITE_BOOTS)) {
						{
							ItemStack _ist = Shift;
							if (_ist.hurt((int) (Shift.getMaxDamage() * Mth.nextDouble(RandomSource.create(), 0.8, 0.95)), RandomSource.create(), null)) {
								_ist.shrink(1);
								_ist.setDamageValue(0);
							}
						}
					}
					Shift.getOrCreateTag().putBoolean("crafted", true);
					{
						final int _slotid = (int) slot;
						final ItemStack _setstack = Shift.copy();
						_setstack.setCount(1);
						entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
							if (capability instanceof IItemHandlerModifiable _modHandlerEntSetSlot)
								_modHandlerEntSetSlot.setStackInSlot(_slotid, _setstack);
						});
					}
				}
			}
		}
	}
}
