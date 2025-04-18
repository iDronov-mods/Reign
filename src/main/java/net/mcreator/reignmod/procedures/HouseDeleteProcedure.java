package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;

public class HouseDeleteProcedure {
	public static void execute(LevelAccessor world, String house_name) {
		if (house_name == null)
			return;
		if (!world.isClientSide() && world.getServer() != null)
			world.getServer().getPlayerList().broadcastSystemMessage(Component.literal((house_name + " " + Component.translatable("translation.key.house_delete").getString())), false);
	}
}
