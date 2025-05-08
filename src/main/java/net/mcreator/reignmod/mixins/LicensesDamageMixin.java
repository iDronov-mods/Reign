package net.mcreator.reignmod.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.GameType;
import net.mcreator.reignmod.network.ReignModModVariables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class LicensesDamageMixin {

    @ModifyVariable(method = "hurt", at = @At("HEAD"), argsOnly = true)
    private float modifyDamageAmount(float amount, DamageSource source) {
        Entity sourceentity = source.getEntity();
        if (!(sourceentity instanceof Player player)) return amount;

        if (isCreative(player)) return amount;

        if (amount <= 5.0f) return amount;

        ReignModModVariables.PlayerVariables vars =
                sourceentity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
                        .orElse(new ReignModModVariables.PlayerVariables());

        ItemStack mainHand = player.getMainHandItem();

        // +1 урон при определённых условиях
        if (
                (vars.license_soldier && vars.ADD_LVL >= 3 && mainHand.getItem() instanceof SwordItem) ||
                        (vars.license_woodcuter && vars.ADD_LVL >= 5 && mainHand.getItem() instanceof AxeItem) ||
                        (vars.license_hunter && vars.ADD_LVL >= 5 && source.isIndirect() && mainHand.getItem() instanceof BowItem)
        ) {
            return amount + 1.0f;
        }

        return amount;
    }

    private boolean isCreative(Entity entity) {
        if (entity instanceof ServerPlayer serverPlayer) {
            return serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
        } else if (entity.level().isClientSide() && entity instanceof Player player) {
            var info = Minecraft.getInstance().getConnection().getPlayerInfo(player.getGameProfile().getId());
            return info != null && info.getGameMode() == GameType.CREATIVE;
        }
        return false;
    }
}
