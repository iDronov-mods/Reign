package net.mcreator.reignmod.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BlastFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SmokerBlock;
import net.minecraft.world.level.block.entity.Hopper;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HopperBlockEntity.class)
public class HopperBlockMixin {

    @Inject(method = "suckInItems", at = @At("HEAD"), cancellable = true)
    private static void preventSuckingFromFurnace(Level pLevel, Hopper pHopper, CallbackInfoReturnable<Boolean> cir)
    {
        BlockPos blockpos = BlockPos.containing(pHopper.getLevelX(), pHopper.getLevelY() + 1, pHopper.getLevelZ());
        BlockState blockstate = pLevel.getBlockState(blockpos);
        Block block = blockstate.getBlock();
        if (block instanceof BlastFurnaceBlock || block instanceof SmokerBlock) {
            cir.setReturnValue(false);
        }
    }
}