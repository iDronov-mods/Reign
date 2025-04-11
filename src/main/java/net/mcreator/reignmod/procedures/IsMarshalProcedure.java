package net.mcreator.reignmod.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.reignmod.kingdom.KingdomData;

import java.util.UUID;

public class IsMarshalProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		String UUID = "";
		UUID = entity.getStringUUID();
		if (KingdomData.getPlayerPosition(UUID) == KingdomData.CourtPosition.MARSHAL) {
			return true;
		}
		return false;
	}
}
