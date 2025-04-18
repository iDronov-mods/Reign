package net.mcreator.reignmod.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.mcreator.reignmod.network.ReignModModVariables;
import net.minecraft.world.entity.animal.horse.Horse;
import net.mcreator.reignmod.house.HouseManager;


@Mixin(Horse.class)
public class HorseSaddleMixin {
//    @Inject(at = @At("HEAD"), method = "mobInteract", cancellable = true)
//    private void onSaddle(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
//        if (!(player instanceof ServerPlayer)) return; // работаем только с серверным игроком
//
//        ReignModModVariables.PlayerVariables playerVars = player.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(null);
//        if (playerVars == null) {
//            cir.setReturnValue(InteractionResult.CONSUME);
//            return;
//        }
//
//        boolean isCowboy = playerVars.license_cowboy;
//        boolean isKnight = HouseManager.isPlayerKnight(player);
//        boolean isLord = HouseManager.isPlayerLord(player);
//
//        if (!isCowboy && !isKnight && !isLord) {
//            cir.setReturnValue(InteractionResult.CONSUME);
//        }
//    }
}


