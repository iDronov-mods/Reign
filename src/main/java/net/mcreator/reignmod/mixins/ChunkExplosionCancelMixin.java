package net.mcreator.reignmod.mixins;

import net.mcreator.reignmod.claim.chunk.ChunkClaimManager;
import net.mcreator.reignmod.claim.capital.CapitalClaimProtectionHandler;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Explosion.class)
public class ChunkExplosionCancelMixin {

    @Final
    @Mutable
    @Shadow private float radius;

    @Inject(
            method = "<init>(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;DDDFZLnet/minecraft/world/level/Explosion$BlockInteraction;)V",
            at = @At("TAIL")
    )
    private void reduceRadiusIfClaimed(Level level,
                                       @Nullable Entity source,
                                       @Nullable DamageSource damageSource,
                                       @Nullable ExplosionDamageCalculator calculator,
                                       double x, double y, double z,
                                       float radiusIn,
                                       boolean fire,
                                       Explosion.BlockInteraction interaction,
                                       CallbackInfo ci) {
        if (!level.isClientSide) {
            int chunkX = (int) x >> 4;
            int chunkZ = (int) z >> 4;

            if (!CapitalClaimProtectionHandler.isWithinCapitalGlobal((int) x, (int) z) &&  ChunkClaimManager.getChunkOwner(chunkX, chunkZ).isPresent()) {
                this.radius = radiusIn / 4.0f;
            }
        }
    }
}