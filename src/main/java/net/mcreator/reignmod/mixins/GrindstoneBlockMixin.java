package net.mcreator.reignmod.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.mcreator.reignmod.network.ReignModModVariables;
import net.minecraft.world.level.block.GrindstoneBlock;

@Mixin(GrindstoneBlock.class)
public abstract class GrindstoneBlockMixin {
    @Inject(at = @At("HEAD"), method = "use", cancellable = true)
    private void use(BlockState p_48804_, Level p_48805_, BlockPos p_48806_, Player entity, InteractionHand p_48808_, BlockHitResult p_48809_, CallbackInfoReturnable<InteractionResult> cir) {
        if (!((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).license_smith
            && (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).MAIN_LVL >= 2)) 
        {
            entity.displayClientMessage(Component.translatable("NotSmithSay"), true);
            cir.setReturnValue(InteractionResult.FAIL);
        }
    }
}
