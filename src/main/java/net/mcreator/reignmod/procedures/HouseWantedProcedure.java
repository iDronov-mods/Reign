package net.mcreator.reignmod.procedures;

import net.mcreator.reignmod.house.HouseCommands;
import net.mcreator.reignmod.network.ReignModModVariables;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class HouseWantedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).house).isEmpty()) {
			if (!(entity instanceof ServerPlayer sp))
				return;
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(HouseCommands.getHouseWantedPlayers(sp)), false);
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal((Component.translatable("translation.key.player_free").getString())), false);
		}
	}
}
