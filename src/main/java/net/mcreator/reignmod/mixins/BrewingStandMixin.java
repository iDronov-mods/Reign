package net.mcreator.reignmod.mixins;

import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BrewingStandBlockEntity.class)
public class BrewingStandMixin {

    @Inject(method = "canPlaceItemThroughFace", at = @At("HEAD"), cancellable = true)
    private void preventTopHopperInsert(int index, ItemStack stack, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        if (direction != null && direction == Direction.UP) {
            cir.setReturnValue(false);
        }
    }
}