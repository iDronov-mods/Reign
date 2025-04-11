package net.mcreator.reignmod.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.reignmod.kingdom.KingdomData;

public class CapitalSurgingGetProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double value = 0;
		value = KingdomData.getSourceDisturbance();;
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("" + value)), false);
	}
}
