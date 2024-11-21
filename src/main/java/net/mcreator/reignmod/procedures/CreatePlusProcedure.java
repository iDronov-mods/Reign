package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.EditBox;

import net.mcreator.reignmod.house.HouseManager;
import net.mcreator.reignmod.ReignModMod;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

public class CreatePlusProcedure {
	public static void execute(LevelAccessor world, Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		ReignModMod.queueServerWork(1, () -> {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				((Slot) _slots.get(0)).set(ItemStack.EMPTY);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				((Slot) _slots.get(1)).set(ItemStack.EMPTY);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				((Slot) _slots.get(2)).set(ItemStack.EMPTY);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				((Slot) _slots.get(3)).set(ItemStack.EMPTY);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				((Slot) _slots.get(4)).set(ItemStack.EMPTY);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				((Slot) _slots.get(5)).set(ItemStack.EMPTY);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				((Slot) _slots.get(6)).set(ItemStack.EMPTY);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				((Slot) _slots.get(7)).set(ItemStack.EMPTY);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				((Slot) _slots.get(8)).set(ItemStack.EMPTY);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				((Slot) _slots.get(9)).set(ItemStack.EMPTY);
				_player.containerMenu.broadcastChanges();
			}
			if (guistate.get("text:house_name") instanceof EditBox _tf)
				_tf.setValue("");
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("" + HouseManager.isColorAvailable("yellow"))), false);
			if (HouseManager.isColorAvailable("yellow")) {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack _setstack = new ItemStack(Blocks.YELLOW_BANNER).copy();
					_setstack.setCount(1);
					((Slot) _slots.get(0)).set(_setstack);
					_player.containerMenu.broadcastChanges();
				}
			}
			if (HouseManager.isColorAvailable("lime")) {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack _setstack = new ItemStack(Blocks.LIME_BANNER).copy();
					_setstack.setCount(1);
					((Slot) _slots.get(1)).set(_setstack);
					_player.containerMenu.broadcastChanges();
				}
			}
			if (HouseManager.isColorAvailable("green")) {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack _setstack = new ItemStack(Blocks.GREEN_BANNER).copy();
					_setstack.setCount(1);
					((Slot) _slots.get(2)).set(_setstack);
					_player.containerMenu.broadcastChanges();
				}
			}
			if (HouseManager.isColorAvailable("aqua")) {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack _setstack = new ItemStack(Blocks.CYAN_BANNER).copy();
					_setstack.setCount(1);
					((Slot) _slots.get(3)).set(_setstack);
					_player.containerMenu.broadcastChanges();
				}
			}
			if (HouseManager.isColorAvailable("blue")) {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack _setstack = new ItemStack(Blocks.BLUE_BANNER).copy();
					_setstack.setCount(1);
					((Slot) _slots.get(4)).set(_setstack);
					_player.containerMenu.broadcastChanges();
				}
			}
			if (HouseManager.isColorAvailable("purple")) {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack _setstack = new ItemStack(Blocks.PURPLE_BANNER).copy();
					_setstack.setCount(1);
					((Slot) _slots.get(5)).set(_setstack);
					_player.containerMenu.broadcastChanges();
				}
			}
			if (HouseManager.isColorAvailable("pink")) {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack _setstack = new ItemStack(Blocks.PINK_BANNER).copy();
					_setstack.setCount(1);
					((Slot) _slots.get(6)).set(_setstack);
					_player.containerMenu.broadcastChanges();
				}
			}
			if (HouseManager.isColorAvailable("red")) {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack _setstack = new ItemStack(Blocks.RED_BANNER).copy();
					_setstack.setCount(1);
					((Slot) _slots.get(7)).set(_setstack);
					_player.containerMenu.broadcastChanges();
				}
			}
			if (HouseManager.isColorAvailable("orange")) {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack _setstack = new ItemStack(Blocks.ORANGE_BANNER).copy();
					_setstack.setCount(1);
					((Slot) _slots.get(8)).set(_setstack);
					_player.containerMenu.broadcastChanges();
				}
			}
			if (HouseManager.isColorAvailable("black")) {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack _setstack = new ItemStack(Blocks.BLACK_BANNER).copy();
					_setstack.setCount(1);
					((Slot) _slots.get(9)).set(_setstack);
					_player.containerMenu.broadcastChanges();
				}
			}
		});
	}
}
