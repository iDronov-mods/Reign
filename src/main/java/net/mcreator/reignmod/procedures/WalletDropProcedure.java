package net.mcreator.reignmod.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.reignmod.world.inventory.WalletwinMenu;

public class WalletDropProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _plr0 && _plr0.containerMenu instanceof WalletwinMenu) {
			if (entity instanceof Player _player)
				_player.closeContainer();
		}
	}
}
