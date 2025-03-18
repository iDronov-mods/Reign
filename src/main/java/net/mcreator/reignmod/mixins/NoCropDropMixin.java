package net.mcreator.reignmod.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class NoCropDropMixin {

    @Inject(method = "popResource", at = @At("HEAD"), cancellable = true)
    private static void onPopResource(Level level, BlockPos pos, ItemStack stack, CallbackInfo ci) {
        if (level.isClientSide) return;

        BlockState state = level.getBlockState(pos);
        if (!(state.getBlock() instanceof CropBlock || state.getBlock() instanceof NetherWartBlock)) return;

        for (BlockPos neighborPos : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) {
            FluidState fluidState = level.getFluidState(neighborPos);
            if (!fluidState.isEmpty()) {
                ci.cancel();
                return;
            }
        }
    }
}