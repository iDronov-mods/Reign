package net.mcreator.reignmod.mixins;

import net.minecraft.world.level.Explosion;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.mcreator.reignmod.kingdom.KingdomManager;

@Mixin(Explosion.class)
public class CapitalExplosionCancelMixin {

    @Inject(method = "explode", at = @At("HEAD"), cancellable = true)
    private void onExplosion(CallbackInfo ci) {
        Explosion explosion = (Explosion) (Object) this;
        Vec3 pos = explosion.getPosition();

        double capitalX = KingdomManager.getFundCoordinates()[0];
        double capitalZ = KingdomManager.getFundCoordinates()[2];

        double dx = pos.x - capitalX;
        double dz = pos.z - capitalZ;

        double distanceSquared = dx * dx + dz * dz;

        if (distanceSquared <= 256 * 256) {
            ci.cancel();
        }
    }
}
