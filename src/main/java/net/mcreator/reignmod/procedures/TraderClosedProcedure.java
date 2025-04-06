package net.mcreator.reignmod.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Map;
import java.util.function.Supplier;

public class TraderClosedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		ItemStack Type = ItemStack.EMPTY;
		ItemStack ShiftItem = ItemStack.EMPTY;
		double CoinLost = 0;
		double slotIndex = 0;
		Type = ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(1)).getItem() : ItemStack.EMPTY).copy());
		CoinLost = new Object() {
			public int getAmount(int sltid) {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
					if (stack != null)
						return stack.getCount();
				}
				return 0;
			}
		}.getAmount(1) - 64;
		while (CoinLost > 0) {
			if (CoinLost >= 64) {
				if (entity instanceof Player _player) {
					ItemStack _setstack = Type.copy();
					_setstack.setCount(64);
					ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
				}
				CoinLost = CoinLost - 64;
			} else {
				if (entity instanceof Player _player) {
					ItemStack _setstack = Type.copy();
					_setstack.setCount((int) CoinLost);
					ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
				}
				CoinLost = 0;
			}
		}
		InventoryToWalletProcedure.execute(world, x, y, z, entity);
	}
}
