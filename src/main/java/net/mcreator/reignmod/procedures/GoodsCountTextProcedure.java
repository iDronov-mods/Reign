package net.mcreator.reignmod.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.reignmod.network.ReignModModVariables;

import java.util.function.Supplier;
import java.util.Map;

public class GoodsCountTextProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		String item_name = "";
		double count = 0;
		if (new Object() {
			public int getAmount(int sltid) {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
					if (stack != null)
						return stack.getCount();
				}
				return 0;
			}
		}.getAmount(68) == 0) {
			return "";
		}
		return (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).market_goods_count;
	}
}
