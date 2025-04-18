package net.mcreator.reignmod.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.reignmod.kingdom.KingdomData;

public class PrisonCreateProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double x_prison = 0;
		double y_prison = 0;
		double z_prison = 0;
		x_prison = entity.getX();
		y_prison = entity.getY();
		z_prison = entity.getZ();
		KingdomData.setPrisonCoordinates((int) x_prison, (int) y_prison, (int) z_prison);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal((Component.translatable("translation.royale_prison_set").getString())), false);
	}
}
