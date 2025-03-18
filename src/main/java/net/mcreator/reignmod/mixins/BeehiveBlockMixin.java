package net.mcreator.reignmod.mixins;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.util.LazyOptional;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BeehiveBlock.class)
public class BeehiveBlockMixin {
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void onUse(BlockState state, net.minecraft.world.level.Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit, CallbackInfoReturnable<InteractionResult> cir) {
        LazyOptional<ReignModModVariables.PlayerVariables> variables = player.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null);
        ReignModModVariables.PlayerVariables playerVars = variables.orElse(new ReignModModVariables.PlayerVariables());

        if (!(playerVars.license_farmer && playerVars.MAIN_LVL >= 3 && playerVars.R_LVL)) {
            player.displayClientMessage(Component.translatable("translation.key.cant_collect_honey"), true);
            cir.setReturnValue(InteractionResult.FAIL);
        }
    }
}