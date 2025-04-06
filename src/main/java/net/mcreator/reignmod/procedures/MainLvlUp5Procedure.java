package net.mcreator.reignmod.procedures;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class MainLvlUp5Procedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).MAIN_LVL == 4) {
			if ((entity instanceof Player _plr ? _plr.experienceLevel : 0) >= 50) {
				return true;
			}
		}
		return false;
	}
}
