package net.mcreator.reignmod.mixins;

import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.Component;
import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.procedures.IsKingProcedure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.apache.logging.log4j.LogManager;

import java.util.Objects;

@Mixin(Player.class)
public abstract class PlayerSetNameMixin {

    @Inject(method = "getDisplayName", at = @At("HEAD"), cancellable = true)
    private void injectCustomDisplayName(CallbackInfoReturnable<Component> cir) {

        try {
            Player player = (Player) (Object) this;
            String playerPrefix = (player.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).player_prefix;
            LogManager.getLogger("ReignMod").error("Value of player_color_code ->" +
                    (player.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).player_prefix + "<-");

            if (!playerPrefix.isEmpty()) {
                String customName = playerPrefix + player.getGameProfile().getName();
                cir.setReturnValue(Component.literal(customName));
            } else {
                cir.setReturnValue(player.getName());
            }
        } catch (Exception e) {
            LogManager.getLogger("ReignMod").error("Exception thrown in PlayerSetNameMixin: ", e);
        }
    }

}