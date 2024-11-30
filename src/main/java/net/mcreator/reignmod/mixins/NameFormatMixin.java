package net.mcreator.reignmod.mixins;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.mcreator.reignmod.network.ReignModModVariables;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.mcreator.reignmod.networking.ClientPlayerData;
import net.minecraft.server.level.ServerPlayer;


@Mixin(PlayerEvent.NameFormat.class)
public abstract class NameFormatMixin {

    @Shadow public abstract void setDisplayname(@Nullable Component displayName);

    @Inject(method = "<init>", at = @At("RETURN"))
    public void nameFormatConstructor(Player player, Component username, CallbackInfo ci) {
        String playerPrefix;
        if (player instanceof ServerPlayer) {
            playerPrefix = (player.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).player_prefix;
        } else {
            playerPrefix = ClientPlayerData.getPlayersPrefixes().getOrDefault(player.getStringUUID(), "");
        }

        String customName = playerPrefix + player.getGameProfile().getName();
        this.setDisplayname(Component.literal(customName));
    }

}
