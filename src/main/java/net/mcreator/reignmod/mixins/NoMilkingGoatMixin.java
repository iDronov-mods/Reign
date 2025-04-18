package net.mcreator.reignmod.mixins;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.EntityType;

import net.minecraftforge.common.util.LazyOptional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.mcreator.reignmod.network.ReignModModVariables;

@Mixin(Goat.class)
public abstract class NoMilkingGoatMixin {

    public NoMilkingGoatMixin(EntityType<? extends Goat> entityType, Level level) {
        super();
    }

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    private void blockGoatMilking(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack itemInHand = player.getItemInHand(hand);

        if (!itemInHand.is(Items.BUCKET)) return;

        LazyOptional<ReignModModVariables.PlayerVariables> cap = player.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null);
        ReignModModVariables.PlayerVariables vars = cap.orElse(new ReignModModVariables.PlayerVariables());

        if (!vars.license_cowboy || vars.MAIN_LVL < 4) {
            player.displayClientMessage(Component.translatable("translation.key.cant_milking"), true);
            cir.setReturnValue(InteractionResult.FAIL);
        }
    }
}
