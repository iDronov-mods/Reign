package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.reignmod.init.ReignModModItems;

import java.util.function.Supplier;
import java.util.Map;

public class MarketPriceSetProcedure {
	public static void execute(LevelAccessor world, Entity entity, double tax, String goodsName) {
		if (entity == null || goodsName == null)
			return;
		String goods = "";
		double totalPrice = 0;
		double addTax = 0;
		TextMarketTaxProcedure.execute(world, entity);
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			((Slot) _slots.get(69)).set(ItemStack.EMPTY);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			((Slot) _slots.get(70)).set(ItemStack.EMPTY);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			((Slot) _slots.get(71)).set(ItemStack.EMPTY);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			((Slot) _slots.get(72)).set(ItemStack.EMPTY);
			_player.containerMenu.broadcastChanges();
		}
		totalPrice = GetPriceWithFillFactorProcedure.execute(goodsName);
		addTax = tax * totalPrice;
		totalPrice = totalPrice + addTax;
		if (totalPrice >= 4096) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.PLATINUM_COIN.get()).copy();
				_setstack.setCount((int) Math.floor(totalPrice / 4096));
				((Slot) _slots.get(72)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			totalPrice = totalPrice - Math.floor(totalPrice / 4096) * 4096;
		}
		if (totalPrice >= 256) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.GOLD_COIN.get()).copy();
				_setstack.setCount((int) Math.floor(totalPrice / 256));
				((Slot) _slots.get(71)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			totalPrice = totalPrice - Math.floor(totalPrice / 256) * 256;
		}
		if (totalPrice >= 16) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.SILVER_COIN.get()).copy();
				_setstack.setCount((int) Math.floor(totalPrice / 16));
				((Slot) _slots.get(70)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			totalPrice = totalPrice - Math.floor(totalPrice / 16) * 16;
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
			_setstack.setCount((int) totalPrice);
			((Slot) _slots.get(69)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
	}
}
