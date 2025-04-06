package net.mcreator.reignmod.procedures;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class MainLvlUp1Procedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).MAIN_LVL == 0) {
			if ((entity instanceof Player _plr ? _plr.experienceLevel : 0) >= 10) {
				return true;
			}
		}
		return false;
	}
}
