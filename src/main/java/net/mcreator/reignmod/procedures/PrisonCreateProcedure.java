package net.mcreator.reignmod.procedures;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.LevelAccessor;

public class PrisonCreateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		ReignModModVariables.MapVariables.get(world).Prison_X = Math.floor(x);
		ReignModModVariables.MapVariables.get(world).syncData(world);
		ReignModModVariables.MapVariables.get(world).Prison_Y = Math.floor(y);
		ReignModModVariables.MapVariables.get(world).syncData(world);
		ReignModModVariables.MapVariables.get(world).Prison_Z = Math.floor(z);
		ReignModModVariables.MapVariables.get(world).syncData(world);
		if (!world.isClientSide() && world.getServer() != null)
			world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("The prison is set."), false);
	}
}
