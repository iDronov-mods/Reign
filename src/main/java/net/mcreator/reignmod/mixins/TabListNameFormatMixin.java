package net.mcreator.reignmod.mixins;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(PlayerEvent.TabListNameFormat.class)
public abstract class TabListNameFormatMixin {

    @Shadow public abstract void setDisplayName(@Nullable Component displayName);

    @Inject(method = "<init>", at = @At("RETURN"))
    public void nameFormatConstructor(Player player, CallbackInfo ci) {
        this.setDisplayName(player.getDisplayName());
    }

}
