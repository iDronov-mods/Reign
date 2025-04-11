package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.reignmod.network.ReignModModVariables;

import java.util.HashMap;

public class SetCoinageBlockTickProcedure {
	public static void execute(LevelAccessor world, HashMap guistate) {
		if (guistate == null)
			return;
		ReignModModVariables.MapVariables.get(world).coinage_block = guistate.containsKey("checkboxin:coinage") && ((String) guistate.get("checkboxin:coinage")).equals("true") ? true : false;
		ReignModModVariables.MapVariables.get(world).syncData(world);
	}
}
