package net.mcreator.reignmod.basics;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class WolfTameRestriction {

    @SubscribeEvent
    public static void onWolfInteract(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getTarget() instanceof Wolf)) return;
        if (!(event.getEntity() instanceof ServerPlayer sp)) return;

        Level level = event.getLevel();
        if (level.isClientSide()) return;

        ItemStack heldItem = event.getItemStack();
        if (heldItem.getItem() == Items.BONE) {
            var cap = sp.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables());
            if (cap.license_hunter && cap.MAIN_LVL >= 3) {
                sp.displayClientMessage(Component.translatable("translation.key.cant_tame_wolf"), true);
                event.setCanceled(true);
            }
        }
    }
}
