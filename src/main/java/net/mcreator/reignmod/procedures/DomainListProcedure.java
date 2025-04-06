package net.mcreator.reignmod.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class DomainListProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (IsKnightProcedure.execute(world, entity)) {/*code*/
		}
	}
}
