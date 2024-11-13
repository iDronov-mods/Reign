package net.mcreator.reignmod.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.house.HouseManager;

public class IsLordProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		Entity player = null;
		if (((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).house).isEmpty()) {
			return false;
		}
		player = entity;
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("" + HouseManager.isPlayerLord(player))), false);
		return HouseManager.isPlayerLord(player);
	}
}
