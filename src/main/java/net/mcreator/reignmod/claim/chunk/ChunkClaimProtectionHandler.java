package net.mcreator.reignmod.claim.chunk;

import net.mcreator.reignmod.networking.ClientPlayerData;
import net.mcreator.reignmod.networking.ReignNetworking;
import net.mcreator.reignmod.networking.packet.C2S.ChunkBreakPermissionQueryC2SPacket;
import net.mcreator.reignmod.potion.CriminalMobEffect;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.ChunkPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.mcreator.reignmod.ReignModMod.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class ChunkClaimProtectionHandler {

    /**
     * SERVER-ONLY
     */

    @SubscribeEvent
    public static void onServerBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (!(event.getEntity() instanceof ServerPlayer sp)) return;
        var pos = event.getPosition().orElse(null);
        if (pos == null) return;

        if (!ChunkClaimManager.hasPermission(sp, pos)) {
            event.setNewSpeed(event.getNewSpeed() / 5.0F);
        }
    }

    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer sp)) return;
        if (sp.hasEffect(new CriminalMobEffect())) {
            event.setCanceled(true);
        }
        if (!ChunkClaimManager.hasPermission(sp, event.getPos())) {
            sp.addEffect(new MobEffectInstance(new CriminalMobEffect(), 200, 1, false, false));
        }
    }

    /**
     * CLIENT-ONLY
     */
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (!(event.getEntity() instanceof LocalPlayer player)) return;

        BlockPos pos = event.getPosition().orElse(null);
        if (pos == null) return;

        ChunkPos cp = new ChunkPos(pos);

        // Сравниваем с ClientPlayerData
        if (cp.x == ClientPlayerData.getLastKnownChunkX() && cp.z == ClientPlayerData.getLastKnownChunkZ()) {
            // Уже знаем, можно ли ломать
            if (!ClientPlayerData.canBreakInThisChunk()) {
                event.setNewSpeed(event.getNewSpeed() / 5.0F);
            }
        } else {
            // Не совпадает -> шлём запрос на сервер
            ReignNetworking.sendToServer(new ChunkBreakPermissionQueryC2SPacket(cp.x, cp.z));
        }
    }

    /**
     * Сбрасываем lastKnownChunk при входе в новый чанк.
     * Forge-событие: EntityEvent.EnteringSection (1.20+).
     */
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onEnteringSection(EntityEvent.EnteringSection event) {
        // Проверим, что entity – это localPlayer
        if (event.getEntity() instanceof LocalPlayer) {
            ClientPlayerData.setLastKnown();
        }
    }

    /**
     * Дополнительно можно сбрасывать при переподключении или смене мира,
     * если нужно (необязательно).
     */
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientPlayerLogout(ClientPlayerNetworkEvent.LoggingIn event) {
        ClientPlayerData.setLastKnown();
    }

    /**
     * COMMON
     */

    public static void register() {
        MinecraftForge.EVENT_BUS.register(ChunkClaimProtectionHandler.class);
    }
}
