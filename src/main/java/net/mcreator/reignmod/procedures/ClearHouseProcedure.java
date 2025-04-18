package net.mcreator.reignmod.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.reignmod.network.ReignModModVariables;

public class ClearHouseProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			String _setval = "";
			entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.house = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
