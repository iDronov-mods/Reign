package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.reignmod.kingdom.KingdomManager;
import net.mcreator.reignmod.kingdom.KingdomData;

import java.util.UUID;

public class IsRightHandProcedure {
	public static boolean execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return false;
		String UUID = "";
		boolean flag = false;
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				UUID = entity.getStringUUID();
				flag = KingdomManager.getPlayerPosition(UUID) == KingdomData.CourtPosition.HAND_OF_THE_KING;
			}
			world = _worldorig;
		}
		if (flag) {
			return true;
		}
		return false;
	}
}
