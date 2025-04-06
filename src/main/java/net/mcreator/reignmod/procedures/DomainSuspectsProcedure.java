package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;

import net.mcreator.reignmod.house.HouseCommands;

public class DomainSuspectsProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (IsKnightProcedure.execute(world, entity)) {
			if (!(entity instanceof ServerPlayer sp))
				return;
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(HouseCommands.getDomainSuspectPlayers(sp.getStringUUID(), 7)), false);
		}
	}
}
