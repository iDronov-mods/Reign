package net.mcreator.reignmod.mixins;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.MushroomCow;
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

import net.mcreator.reignmod.network.ReignModModVariables; // Убедитесь, что путь правильный

@Mixin(MushroomCow.class)
public abstract class NoMilkingMushroomMixin {

    // Конструктор для миксина
    public NoMilkingMushroomMixin(EntityType<? extends MushroomCow> entityType, Level level) {
        super();
    }

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    private void blockCowMilking(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack itemInHand = player.getItemInHand(hand);

        // Проверяем, что в руке ведро или миска
        if (!(itemInHand.is(Items.BUCKET) || itemInHand.is(Items.BOWL))) return;

        // Получаем переменные игрока
        LazyOptional<ReignModModVariables.PlayerVariables> cap = player.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null);
        ReignModModVariables.PlayerVariables vars = cap.orElse(new ReignModModVariables.PlayerVariables());

        // Если игрок не имеет лицензии или недостаточен уровень, отменяем действие
        if (!vars.license_cowboy || vars.MAIN_LVL < 4) {
            // Показываем сообщение
            player.displayClientMessage(Component.translatable("translation.key.cant_milking"), true);
            // Отменяем стандартное поведение (дойка)
            cir.setReturnValue(InteractionResult.FAIL);
        }
    }
}