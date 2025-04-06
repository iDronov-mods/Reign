package net.mcreator.reignmod.claim.chunk;

import net.mcreator.reignmod.house.Domain;
import net.mcreator.reignmod.house.HouseManager;
import net.mcreator.reignmod.init.ReignModModMobEffects;
import net.mcreator.reignmod.networking.ClientPlayerData;
import net.mcreator.reignmod.networking.ReignNetworking;
import net.mcreator.reignmod.networking.packet.C2S.ChunkBreakPermissionQueryC2SPacket;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.mcreator.reignmod.ReignModMod.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class ChunkClaimProtectionHandler {
    public enum SuspiciousAction {
        BLOCK_BREAK,
        BLOCK_PLACE
    }

    private static final float BLOCK_BREAK_MULTIPLIER = 25.0F;

    /**
     * SERVER-ONLY
     */
    public static void increaseSuspicion(ServerPlayer sp, SuspiciousAction susAction, Block block, BlockPos blockPos) {
        int suspicion = getSuspicionByBlock(susAction, block);

        if (suspicion > 0) {
            var claimOpt = ChunkClaimSavedData.getInstance().getClaimByChunk(new ChunkPos(blockPos).toLong());
            if (claimOpt.isEmpty()) {
                return;
            }

            var claimData = claimOpt.get();
            Domain chunkDomain = HouseManager.getDomainByKnightUUID(claimData.getOwnerId());
            if (!chunkDomain.isNull()) {
                chunkDomain.adjustSuspicionForPlayer(sp.getStringUUID(), suspicion);
            }
        }
    }

    /**
     * SERVER-ONLY
     */
    @SubscribeEvent
    public static void onServerBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (!(event.getEntity() instanceof ServerPlayer sp)) return;
        var pos = event.getPosition().orElse(null);
        if (pos == null) return;

        if (!ChunkClaimManager.hasPermission(sp, pos)) {
            event.setNewSpeed(event.getNewSpeed() / BLOCK_BREAK_MULTIPLIER);
        }
    }

    /**
     * SERVER-ONLY
     */
    @SubscribeEvent
    public static void onServerBreakSpeed(BlockEvent.BreakEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayer sp)) return;
        var pos = event.getPos();
        if (pos == null) return;

        if (!ChunkClaimManager.hasPermission(sp, pos)) {
            increaseSuspicion(sp, SuspiciousAction.BLOCK_BREAK, event.getState().getBlock(), event.getPos());
        }
    }

    /**
     * SERVER-ONLY
     */
    @SubscribeEvent
    public static void onServerBlockPlace(BlockEvent.EntityPlaceEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer sp)) return;
        if (sp.hasEffect(ReignModModMobEffects.SUSPECT.get())) {
            cancelEvent(event);
        }
        if (!ChunkClaimManager.hasPermission(sp, event.getPos())) {
            increaseSuspicion(sp, SuspiciousAction.BLOCK_PLACE, event.getPlacedBlock().getBlock(), event.getPos());
            sp.addEffect(new MobEffectInstance(ReignModModMobEffects.SUSPECT.get(), 200, 0, false, false, true));
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

        if (ClientPlayerData.isLastKnownEmpty()) {
            ReignNetworking.sendToServer(new ChunkBreakPermissionQueryC2SPacket(cp.x, cp.z));
        }

        if (ClientPlayerData.isLastKnown(cp.x, cp.z)) {
            if (!ClientPlayerData.canBreakInThisChunk()) {
                event.setNewSpeed(event.getNewSpeed() / BLOCK_BREAK_MULTIPLIER);
            }
        }
    }

    /**
     * CLIENT-ONLY
     */
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientBlockPlace(BlockEvent.EntityPlaceEvent event) {
        if (!(event.getEntity() instanceof LocalPlayer player)) return;
        if (player.hasEffect(ReignModModMobEffects.SUSPECT.get())) {
            cancelEvent(event);
        }

        BlockPos pos = event.getPos();
        if (pos == null) return;

        ChunkPos cp = new ChunkPos(pos);

        if (ClientPlayerData.isLastKnownEmpty()) {
            ReignNetworking.sendToServer(new ChunkBreakPermissionQueryC2SPacket(cp.x, cp.z));
        }

        if (ClientPlayerData.isLastKnown(cp.x, cp.z)) {
            if (!ClientPlayerData.canBreakInThisChunk()) {
                player.addEffect(new MobEffectInstance(ReignModModMobEffects.SUSPECT.get(), 200, 0, false, false, true));
            }
        }
    }

    /**
     * CLIENT-ONLY
     */
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onEnteringSection(EntityEvent.EnteringSection event) {
        if (event.getEntity() instanceof LocalPlayer) {
            ClientPlayerData.setLastKnown();
        }
    }

    /**
     * CLIENT-ONLY
     */
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientPlayerLogout(ClientPlayerNetworkEvent.LoggingIn event) {
        ClientPlayerData.setLastKnown();
    }

    /**
     * COMMON
     */
    private static void cancelEvent(Event event) {
        if (event != null && event.isCancelable()) {
            event.setCanceled(true);
        } else if (event != null && event.hasResult()) {
            event.setResult(Event.Result.DENY);
        }
    }

    /**
     * COMMON
     */
    private static int getSuspicionByBlock(SuspiciousAction susAction, Block block) {
        int placeSuspicion = 5;
        int breakSuspicion = 10;

        if (block instanceof CropBlock) {
            placeSuspicion = 1;
            breakSuspicion = 1;
        } else if (block instanceof ChestBlock || block instanceof BarrelBlock) {
            placeSuspicion = 10;
            breakSuspicion = 50;
        } else if (block == Blocks.TNT) {
            placeSuspicion = 50;
            breakSuspicion = 5;
        } else if (block == Blocks.LAVA || block == Blocks.WATER) {
            placeSuspicion = 20;
            breakSuspicion = 0;
        }

        return (susAction == SuspiciousAction.BLOCK_PLACE) ? placeSuspicion : breakSuspicion;
    }

    /**
     * COMMON
     */
    public static void register() {
        MinecraftForge.EVENT_BUS.register(ChunkClaimProtectionHandler.class);
    }
}
