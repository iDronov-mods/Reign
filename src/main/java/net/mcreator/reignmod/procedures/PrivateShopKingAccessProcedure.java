package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

public class PrivateShopKingAccessProcedure {
	public static boolean execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return false;
		return !(IsKingProcedure.execute(world, entity) || IsRightHandProcedure.execute(entity));
	}
}
