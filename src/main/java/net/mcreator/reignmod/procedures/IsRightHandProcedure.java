package net.mcreator.reignmod.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.reignmod.kingdom.KingdomData;

import java.util.UUID;

public class IsRightHandProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		String UUID = "";
		UUID = entity.getStringUUID();
		if (KingdomData.getPlayerPosition(UUID) == KingdomData.CourtPosition.HAND_OF_THE_KING) {
			return true;
		}
		return false;
	}
}
