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

@Mixin(Player.class)
public abstract class PlayerSetNameMixin {

    private static final Logger LOGGER = LogManager.getLogger("ReignMod");

    @Inject(method = "getDisplayName", at = @At("HEAD"), cancellable = true)
    private void injectCustomDisplayName(CallbackInfoReturnable<Component> cir) {
        try {
            Player player = (Player) (Object) this;

            // Получение данных из capability
            ReignModModVariables.PlayerVariables variables = player.getCapability(
                ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null
            ).orElse(null);

            if (variables == null) {
                LOGGER.error("Не удалось получить capability для игрока.");
                return;
            }

            // Извлечение ID сюзерена
            String suzerainId = variables.house;
            LOGGER.info("Suzerain ID: " + suzerainId);

            // Проверка, что suzerainId не пуст и не null
            if (suzerainId != null && !suzerainId.isEmpty()) {
                String color = HouseManager.getPlayerHouseColor(player, suzerainId);

                String iconPrefix = "";
                String colorPrefix = "";

                switch (color) {
                    case "yellow" -> colorPrefix = "§e";
                    case "lime" -> colorPrefix = "§a";
                    case "green" -> colorPrefix = "§2";
                    case "aqua" -> colorPrefix = "§b";
                    case "blue" -> colorPrefix = "§1";
                    case "purple" -> colorPrefix = "§d";
                    case "pink" -> colorPrefix = "§5";
                    case "red" -> colorPrefix = "§c";
                    case "orange" -> colorPrefix = "§6";
                    case "black" -> colorPrefix = "§8";
                }

                if (IsKingProcedure.execute(player.getCommandSenderWorld(), player)) {
                	colorPrefix += "§l";
                    iconPrefix = "[§e👑§f]";
                } else if (HouseManager.isPlayerLord(player)) {
                    colorPrefix += "§l";
                    iconPrefix = "[§7🏰§f]";
                } else if (HouseManager.isPlayerKnight(player)) {
                    iconPrefix = "[§7⚔️§f]";
                }
                
				if (iconPrefix != "") {iconPrefix+=" ";}
				
                String customName = iconPrefix + colorPrefix + player.getGameProfile().getName();
                cir.setReturnValue(Component.literal(customName));
            } else {
                cir.setReturnValue(player.getName());
            }
        } catch (Exception e) {
            LOGGER.error("Ошибка в миксине PlayerSetNameMixin: ", e);
        }
    }
}
