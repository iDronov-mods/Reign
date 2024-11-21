package net.mcreator.reignmod.mixins;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.house.HouseManager;
import net.mcreator.reignmod.procedures.IsKingProcedure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

@Mixin(Player.class)
public abstract class PlayerSetNameMixin {

    private static final Logger LOGGER = LogManager.getLogger("ReignMod");

    @Inject(method = "getDisplayName", at = @At("HEAD"), cancellable = true)
    private void injectCustomDisplayName(CallbackInfoReturnable<Component> cir) {
        try {
            	Player player = (Player) (Object) this;
                String colorPrefix = HouseManager.getPlayerHouseColorCode(player);
            // Проверка, что suzerainId не пуст и не null
            if (!Objects.equals(colorPrefix, "null")) {

                String iconPrefix = "";

                if (IsKingProcedure.execute(player.getCommandSenderWorld(), player)) {
                	colorPrefix += "§l";
                    iconPrefix = "§r[§e👑§r]";
                } else if (HouseManager.isPlayerLord(player)) {
                    colorPrefix += "§l";
                    iconPrefix = "§r[§7🏰§r]";
                } else if (HouseManager.isPlayerKnight(player)) {
                    iconPrefix = "§r[§7🗡§r]";
                }
                
				if (!Objects.equals(iconPrefix, "")) {iconPrefix+=" ";}
				
                String customName = iconPrefix + colorPrefix + player.getGameProfile().getName();
                cir.setReturnValue(Component.literal(customName));
            } else {
                cir.setReturnValue(player.getName());
            }
        } catch (Exception e) {
            LOGGER.error("Exception thrown in PlayerSetNameMixin: ", e);
        }
}

}
