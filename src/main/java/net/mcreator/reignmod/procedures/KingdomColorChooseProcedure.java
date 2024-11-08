package net.mcreator.reignmod.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import java.util.function.Supplier;
import java.util.Map;

public class KingdomColorChooseProcedure {
	public static boolean execute(Entity entity, double slot) {
		if (entity == null)
			return false;
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get((int) slot)).getItem() : ItemStack.EMPTY).copy();
			_setstack.setCount(1);
			((Slot) _slots.get(10)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		return true;
	}
}
