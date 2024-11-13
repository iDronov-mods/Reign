package net.mcreator.reignmod.licenses;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockState;
import net.mcreator.reignmod.network.ReignModModVariables;

@Mod.EventBusSubscriber
public class BreakSpeedModify {

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        // Доступ к игроку через getPlayer()
        var player = event.getPlayer();
        BlockState blockState = event.getState();

        // Проверяем, является ли блок деревом
        if (blockState.is(BlockTags.create(new ResourceLocation("minecraft:logs")))) {
            // Получаем значение лицензии игрока
            boolean hasWoodcutterLicense = player.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
                .map(vars -> vars.license_woodcuter)
                .orElse(false);

            // Если лицензии нет, замедляем копание
            if (!hasWoodcutterLicense) {
                event.setNewSpeed(event.getNewSpeed() / 5.0f);
            }
        }
    }
}
