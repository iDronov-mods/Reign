package net.mcreator.reignmod.mixins;

import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.InteractionResult;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.mcreator.reignmod.network.ReignModModVariables;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.network.chat.Component;

@Mixin(BeehiveBlock.class)
public abstract class DontCollectHoneyMixin {

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void onUse(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit, CallbackInfoReturnable<InteractionResult> cir) {
        // Проверяем, является ли игрок фермером с уровнем >= 3
        if (!(player.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
                .orElse(new ReignModModVariables.PlayerVariables()).license_farmer
                && player.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
                .orElse(new ReignModModVariables.PlayerVariables()).MAIN_LVL >= 3)) {
            // Сообщение игроку
            player.displayClientMessage(Component.translatable("BeeLock"), true);
            // Отменяем дальнейшую обработку события
            cir.setReturnValue(InteractionResult.CONSUME);
        }
    }
}
