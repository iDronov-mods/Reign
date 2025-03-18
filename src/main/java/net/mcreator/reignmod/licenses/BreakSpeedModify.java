package net.mcreator.reignmod.licenses;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockState;
import net.mcreator.reignmod.network.ReignModModVariables;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

@Mod.EventBusSubscriber
public class BreakSpeedModify {

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        // Доступ к игроку через getPlayer()
        var player = event.getEntity();
        BlockState blockState = event.getState();

        //Дровосек
        if (blockState.is(BlockTags.create(new ResourceLocation("minecraft:logs")))) {
            boolean hasWoodcutterLicense = player.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
                .map(vars -> vars.license_woodcuter)
                .orElse(false);

            if (!hasWoodcutterLicense) {
                event.setNewSpeed(event.getNewSpeed() / 5.0f);
            }
        }

        //Шахтёр
        if (blockState.is(BlockTags.create(new ResourceLocation("forge:ores")))) {
            boolean hasMinerLicense = player.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
                    .map(vars -> vars.license_miner)
                    .orElse(false);

            if (!hasMinerLicense) {
                event.setNewSpeed(event.getNewSpeed() / 5.0f);
            }
        }

        // Замедление для deepslate_blocks
        if (blockState.is(BlockTags.create(new ResourceLocation("minecraft:deepslate_blocks")))) {
            boolean hasMinerLicense = player.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
                    .map(vars -> vars.license_miner)
                    .orElse(false);
            boolean hasRLvl = player.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
                    .map(vars -> vars.R_LVL)
                    .orElse(false);
                    
            if (hasMinerLicense && player.experienceLevel < 3 || !hasRLvl) {
                event.setNewSpeed(event.getNewSpeed() / 5.0f);
            }
        }
    }
}
