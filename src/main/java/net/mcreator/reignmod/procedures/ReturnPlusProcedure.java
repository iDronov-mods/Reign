package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.init.ReignModModParticleTypes;
import net.mcreator.reignmod.init.ReignModModBlocks;

public class ReturnPlusProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		world.setBlock(BlockPos.containing(x, y, z), ReignModModBlocks.PLUS.get().defaultBlockState(), 3);
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (ReignModModParticleTypes.EREOUS.get()), x, y, z, 5, 3, 3, 3, 1);
	}
}
