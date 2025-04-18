package net.mcreator.reignmod.mixins;

import net.minecraft.world.level.Explosion;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.mcreator.reignmod.kingdom.KingdomData;
import net.mcreator.reignmod.claim.chunk.ChunkClaimManager;

import java.util.Optional;

@Mixin(Explosion.class)
public class CapitalExplosionCancelMixin {

    @Inject(method = "explode", at = @At("HEAD"), cancellable = true)
    private void onExplosion(CallbackInfo ci) {
        Explosion explosion = (Explosion) (Object) this;
        Vec3 pos = explosion.getPosition();

        // Получаем координаты столицы
        double capitalX = KingdomData.getFundCoordinates()[0];
        double capitalZ = KingdomData.getFundCoordinates()[2];

        double dx = pos.x - capitalX;
        double dz = pos.z - capitalZ;

        double distanceSquared = dx * dx + dz * dz;

        // Если взрыв в пределах 256 блоков от столицы — отменяем разрушение
        if (distanceSquared <= 256 * 256) {
            ci.cancel(); // Останавливаем разрушение блоков и урон
            return;
        }

        // Получаем координаты чанка
        int chunkX = (int) Math.floor(pos.x / 16);
        int chunkZ = (int) Math.floor(pos.z / 16);

        // Получаем владельца чанка
        Optional<String> owner = ChunkClaimManager.getChunkOwner(chunkX, chunkZ);

        if (owner.isPresent()) {
            ci.cancel(); // ПЕРЕПИСАТЬ ПОТОМ
            return;
        }
    }
}
