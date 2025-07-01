package net.mcreator.reignmod.mixins;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class LicensesDamageMixin {

    @ModifyVariable(
            method = "hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 0
    )
    private float modifyDamageAmount(float amount, DamageSource source) {
        if (source.getEntity() instanceof Player player) {
            if (reign$isSoldier(player) && reign$isSword(player) && amount > 5f) {
                amount += 1f;
            }

            if (reign$isLumberjack(player) && reign$isAxe(player) && amount > 5f) {
                amount += 1f;
            }
        }

        if (source.getDirectEntity() instanceof AbstractArrow arrow) {
            if (arrow.getOwner() instanceof Player player) {
                if (reign$isHunter(player) && amount > 7f) {
                    amount += 1f;
                }
            }
        }

        return amount;
    }

    // ========= ЗАГЛУШКИ ДЛЯ ПРОВЕРКИ РОЛЕЙ И УРОВНЯ =========

    @Unique
    private boolean reign$isSoldier(Player player) {
        var playerVars = reign$getPlayerVariables(player);
        return playerVars.license_soldier && playerVars.ADD_LVL >= 3;
    }

    @Unique
    private boolean reign$isLumberjack(Player player) {
        var playerVars = reign$getPlayerVariables(player);
        return playerVars.license_woodcuter && playerVars.MAIN_LVL == 5;
    }

    @Unique
    private boolean reign$isHunter(Player player) {
        var playerVars = reign$getPlayerVariables(player);
        return playerVars.license_hunter && playerVars.ADD_LVL == 5;
    }

    @Unique
    private ReignModModVariables.PlayerVariables reign$getPlayerVariables(Player player) {
        return player.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables());
    }

    // ========= ЗАГЛУШКИ ДЛЯ ТИПОВ ОРУЖИЯ =========

    @Unique
    private boolean reign$isSword(Player player) {
        return player.getMainHandItem().getItem() instanceof SwordItem;
    }

    @Unique
    private boolean reign$isAxe(Player player) {
        return player.getMainHandItem().getItem() instanceof AxeItem;
    }
}