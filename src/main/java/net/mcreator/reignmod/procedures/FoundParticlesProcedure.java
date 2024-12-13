package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.reignmod.init.ReignModModParticleTypes;

public class FoundParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (Mth.nextInt(RandomSource.create(), 1, 10) == 1) {
			world.addParticle((SimpleParticleType) (ReignModModParticleTypes.EREOUS.get()), (x + Mth.nextDouble(RandomSource.create(), 0.1, 0.9)), (y + 0.6), (z + Mth.nextDouble(RandomSource.create(), 0.1, 0.9)),
					(Mth.nextDouble(RandomSource.create(), -0.2, 0.2)), (Mth.nextDouble(RandomSource.create(), 0.1, 0.2)), (Mth.nextDouble(RandomSource.create(), -0.2, 0.2)));
		}
	}
}
