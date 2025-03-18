package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.init.ReignModModBlocks;
import net.mcreator.reignmod.claim.chunk.ClaimType;
import net.mcreator.reignmod.claim.chunk.ChunkClaimManager;

import java.util.UUID;

public class CreateFirstDomainProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		String UUID = "";
		if (IsLordProcedure.execute(world, entity)) {
			if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == ReignModModBlocks.SHAFT.get() && (world.getBlockState(BlockPos.containing(x, y - 2, z))).getBlock() == ReignModModBlocks.SHAFT.get()
					&& (world.getBlockState(BlockPos.containing(x, y - 3, z))).getBlock() == ReignModModBlocks.SHAFT.get()) {
				if ((world.getBlockState(BlockPos.containing(x, y - 4, z))).getBlock() == ReignModModBlocks.INCUBATOR.get()) {
					if (ChunkClaimManager.isAreaCompletelyFree((int) x, (int) y, (int) z)) {
						UUID = entity.getStringUUID();
						ChunkClaimManager.createClaimIfFree(UUID, ClaimType.HOUSE, (int) x, (int) y, (int) z);
					}
				}
			}
		}
	}
}
