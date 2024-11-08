package net.mcreator.reignmod.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;  // Добавлено
import net.minecraft.world.item.Items;  // Добавлено
import net.mcreator.reignmod.network.ReignModModVariables;

@Mixin(Sheep.class)
public abstract class DontCutSheepMixin {
    @Inject(at = @At("HEAD"), method = "mobInteract", cancellable = true)
    private void onShear(Player entity, InteractionHand p_29854_, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack itemstack = entity.getItemInHand(p_29854_);
        if (itemstack.getItem() == Items.SHEARS) {
            if (!(entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables()).license_cowboy 
                && entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables()).MAIN_LVL >= 1)) {
                entity.displayClientMessage(Component.translatable("NotCowboy1LVL"), true);
                
                cir.setReturnValue(InteractionResult.CONSUME);

                
            }
        }
    }
}
