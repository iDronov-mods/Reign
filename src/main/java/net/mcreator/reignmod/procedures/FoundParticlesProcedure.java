package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.init.ReignModModParticleTypes;

public class FoundParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (Mth.nextInt(RandomSource.create(), 1, 15) <= 1 + (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "disturbance")) / 10) {
			world.addParticle((SimpleParticleType) (ReignModModParticleTypes.EREOUS.get()), (x + Mth.nextDouble(RandomSource.create(), 0.1, 0.9)), (y + 0.6), (z + Mth.nextDouble(RandomSource.create(), 0.1, 0.9)),
					(Mth.nextDouble(RandomSource.create(), -0.2, 0.2)), (Mth.nextDouble(RandomSource.create(), 0.1, 0.2)), (Mth.nextDouble(RandomSource.create(), -0.2, 0.2)));
		}
	}
}
