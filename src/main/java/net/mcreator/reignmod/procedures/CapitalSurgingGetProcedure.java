package net.mcreator.reignmod.procedures;

import net.mcreator.reignmod.kingdom.KingdomManager;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class CapitalSurgingGetProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double value = 0;
		value = KingdomManager.getSurgingSource();;
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("" + value)), false);
	}
}
