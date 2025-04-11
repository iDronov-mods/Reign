package net.mcreator.reignmod.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.network.ReignModModVariables;

public class KingOverthrowProcedure {
	public static void execute(LevelAccessor world) {
		if (!world.isClientSide() && world.getServer() != null)
			world.getServer().getPlayerList().broadcastSystemMessage(Component.literal((Component.translatable("translation.key.king_overthrow").getString())), false);
		if (world instanceof ServerLevel _level) {
			LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
			entityToSpawn.moveTo(Vec3.atBottomCenterOf(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X, ReignModModVariables.MapVariables.get(world).CAPITAL_Y, ReignModModVariables.MapVariables.get(world).CAPITAL_Z)));
			entityToSpawn.setVisualOnly(true);
			_level.addFreshEntity(entityToSpawn);
		}
		world.getLevelData().setRaining(true);
		CreateCrownProcedure.execute(world);
	}
}
