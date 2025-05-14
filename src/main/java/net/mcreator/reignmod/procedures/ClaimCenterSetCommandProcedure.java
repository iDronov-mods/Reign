package net.mcreator.reignmod.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import net.mcreator.reignmod.claim.capital.CapitalClaimManager;

public class ClaimCenterSetCommandProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		Entity player = null;
		player = entity;
		CapitalClaimManager.setCapitalCenter((ServerPlayer) player);
	}
}
