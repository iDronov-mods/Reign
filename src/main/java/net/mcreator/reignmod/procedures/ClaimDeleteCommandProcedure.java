package net.mcreator.reignmod.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import net.mcreator.reignmod.claim.chunk.ChunkClaimManager;

public class ClaimDeleteCommandProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		Entity player = null;
		player = entity;
		ChunkClaimManager.removeClaim((ServerPlayer) player);
	}
}
