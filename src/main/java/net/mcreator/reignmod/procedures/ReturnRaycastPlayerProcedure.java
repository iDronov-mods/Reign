package net.mcreator.reignmod.procedures;

import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

public class ReturnRaycastPlayerProcedure {
	public static Entity execute(Entity entity) {
		if (entity == null)
			return null;
		Entity player = null;
		Entity hit_player = null;
		player = entity;
		HitResult result = player.pick(5.0D, 1.0F, false);
		if (result.getType() == HitResult.Type.ENTITY) {
			EntityHitResult entityHit = (EntityHitResult) result;
			Entity target = entityHit.getEntity();
			if (target instanceof Player targetPlayer) {
				hit_player = targetPlayer;
			}
		}
		return hit_player;
	}
}
