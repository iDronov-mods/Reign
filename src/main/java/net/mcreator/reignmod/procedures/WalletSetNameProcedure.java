package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class WalletSetNameProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if ((itemstack.getOrCreateTag().getString("owner")).isEmpty()) {
			itemstack.getOrCreateTag().putString("owner", (entity.getDisplayName().getString()));
		}
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == itemstack.getItem()) {
			if (entity.isShiftKeyDown()) {
				InventoryToWalletProcedure.execute(world, x, y, z, entity);
				if (entity instanceof Player _player)
					_player.closeContainer();
			} else {
				OpenWalletProcedure.execute(world, x, y, z, entity, itemstack);
			}
		}
	}
}
