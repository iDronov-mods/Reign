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
//import net.mcreator.reignmod.house.HouseManager;


@Mixin(Horse.class)
public class HorseSaddleMixin {
    @Inject(at = @At("HEAD"), method = "mobInteract", cancellable = true)
    private void onSaddle(Player entity, InteractionHand p_29854_, CallbackInfoReturnable<InteractionResult> cir) {
//        ReignModModVariables.PlayerVariables playerVars = entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(null);
//        if (playerVars == null || !playerVars.license_cowboy && !HouseManager.isPlayerKnight(entity) && !HouseManager.isPlayerLord(entity)) {
//            cir.setReturnValue(InteractionResult.CONSUME);
//        }
    }
}
