package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;

import java.util.function.Supplier;
import java.util.Map;

public class PrivateShopOwnerCloseProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		ItemStack item = ItemStack.EMPTY;
		double x_block = 0;
		double y_block = 0;
		double z_block = 0;
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				item = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(2)).getItem() : ItemStack.EMPTY).copy();
				x_block = x;
				y_block = y;
				z_block = z;
				if (!(item.getItem() == ItemStack.EMPTY.getItem())) {
					PrivateShopEntitySpawnProcedure.spawnEntity(world, x, y, z, item);
				}
			}
			world = _worldorig;
		}
	}
}
