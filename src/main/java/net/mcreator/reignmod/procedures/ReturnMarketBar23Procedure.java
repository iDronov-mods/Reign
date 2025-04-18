package net.mcreator.reignmod.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.reignmod.market.MarketManager;

import java.util.function.Supplier;
import java.util.Map;

public class ReturnMarketBar23Procedure {
	public static boolean execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return false;
		String stringName = "";
		double k = 0;
		if (new Object() {
			public int getAmount(int sltid) {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
					if (stack != null)
						return stack.getCount();
				}
				return 0;
			}
		}.getAmount(68) != 0) {
			stringName = ForgeRegistries.ITEMS.getKey((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(68)).getItem() : ItemStack.EMPTY).getItem())
					.toString();
			if (world instanceof ServerLevel _origLevel) {
				LevelAccessor _worldorig = world;
				world = _origLevel.getServer().getLevel(Level.OVERWORLD);
				if (world != null) {
					k = MarketManager.getItemCount(stringName) / MarketManager.getMaxAmount(stringName);
				}
				world = _worldorig;
			}
			if (k >= 0.5 && k < 0.75) {
				return true;
			}
		}
		return false;
	}
}
