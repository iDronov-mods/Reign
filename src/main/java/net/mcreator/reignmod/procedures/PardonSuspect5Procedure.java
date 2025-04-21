package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.house.HouseManager;
import net.mcreator.reignmod.house.Domain;

public class PardonSuspect5Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		String lordUUID = "";
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				if (!(new Object() {
					public String getValue(LevelAccessor world, BlockPos pos, String tag) {
						BlockEntity blockEntity = world.getBlockEntity(pos);
						if (blockEntity != null)
							return blockEntity.getPersistentData().getString(tag);
						return "";
					}
				}.getValue(world, BlockPos.containing(x, y, z), "suspect_5")).isEmpty()) {
					lordUUID = entity.getStringUUID();
					Domain domain = HouseManager.getDomainByKnightUUID(lordUUID);
					domain.adjustSuspicionForPlayer(domain.getSortedSuspects(1).get(0).getKey(), -100);
				}
			}
			world = _worldorig;
		}
	}
}
