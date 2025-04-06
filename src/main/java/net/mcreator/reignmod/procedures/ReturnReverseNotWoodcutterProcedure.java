package net.mcreator.reignmod.procedures;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.minecraft.world.entity.Entity;

public class ReturnReverseNotWoodcutterProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return !(!(entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).license_woodcuter && IsHaveMainLicensesProcedure.execute(entity));
	}
}
