package net.mcreator.reignmod.procedures;

import net.mcreator.reignmod.kingdom.KingdomManager;
import net.mcreator.reignmod.network.ReignModModVariables;
import net.minecraft.world.level.LevelAccessor;

public class CoffersPlacedProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if (CapitalBlockCheckProcedure.execute(world, x, y, z)) {
			KingdomManager.setCoffersCoordinates((int) x, (int) y, (int) z);
			ReignModModVariables.MapVariables.get(world).VC_X = x;
			ReignModModVariables.MapVariables.get(world).syncData(world);
			ReignModModVariables.MapVariables.get(world).VC_Y = y;
			ReignModModVariables.MapVariables.get(world).syncData(world);
			ReignModModVariables.MapVariables.get(world).VC_Z = z;
			ReignModModVariables.MapVariables.get(world).syncData(world);
			return true;
		}
		return false;
	}
}
