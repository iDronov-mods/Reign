package net.mcreator.reignmod.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

import net.mcreator.reignmod.init.ReignModModItems;

public class SourceBottleDrinkProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double value = 0;
		double k = 0;
		value = Mth.nextInt(RandomSource.create(), 1, 7);
		k = Mth.nextDouble(RandomSource.create(), 0.5, 1.5);
		if (value == 1) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, (int) Math.round(k * 600), 9));
		} else if (value == 2) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.UNLUCK, (int) Math.round(k * 72000), 4));
		} else if (value == 3) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, (int) Math.round(k * 600), 1));
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, (int) Math.round(k * 600), 1));
		} else if (value == 4) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, (int) Math.round(k * 100), 0));
		} else if (value == 5) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.HARM, 1, Mth.nextInt(RandomSource.create(), 0, 2)));
		} else if (value == 6) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, (int) Math.round(k * 600), 4));
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, (int) Math.round(k * 600), 1));
		} else {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.POISON, (int) Math.round(k * 400), 0));
		}
		if (entity instanceof Player _player) {
			ItemStack _stktoremove = new ItemStack(ReignModModItems.SOURCE_BOTTLE.get());
			_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
		}
		if (entity instanceof Player _player) {
			ItemStack _setstack = new ItemStack(Items.GLASS_BOTTLE).copy();
			_setstack.setCount(1);
			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
		if (entity instanceof Player _player) {
			ItemStack _setstack = new ItemStack(ReignModModItems.REIGN_COIN.get()).copy();
			_setstack.setCount(Mth.nextInt(RandomSource.create(), 1, 2));
			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
	}
}
