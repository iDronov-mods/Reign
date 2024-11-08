package net.mcreator.reignmod.procedures;

import net.minecraft.world.entity.Entity;

public class ReturnMeProcedure {
	public static Entity execute(Entity entity) {
		if (entity == null)
			return null;
		return entity;
	}
}
