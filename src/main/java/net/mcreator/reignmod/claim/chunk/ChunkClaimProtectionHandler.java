package net.mcreator.reignmod.claim.chunk;

import net.mcreator.reignmod.house.Domain;
import net.mcreator.reignmod.house.House;
import net.mcreator.reignmod.house.HouseManager;
import net.mcreator.reignmod.house.HouseSavedData;
import net.mcreator.reignmod.init.ReignModModMobEffects;
import net.mcreator.reignmod.networking.ClientPlayerData;
import net.mcreator.reignmod.networking.ReignNetworking;
import net.mcreator.reignmod.networking.packet.C2S.ChunkBreakPermissionQueryC2SPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorStandItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingEntityItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;
import java.util.Optional;

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

            sp.displayClientMessage(Component.nullToEmpty(ChatFormatting.RED + "Вы ведете себя подозрительно!"), true);
            if (!chunkDomain.isNull()) {
                House chunkHouse = HouseManager.getHouseByLordUUID(chunkDomain.getLordUUID());
                if (!chunkHouse.isNull() && !chunkHouse.isWanted(sp.getStringUUID())) {
                    chunkDomain.adjustSuspicionForPlayer(sp.getStringUUID(), suspicion);
                    HouseSavedData.getInstance().setDirty();
                }
            }
        }
    }

    /**
     * SERVER-ONLY
     */
    @SubscribeEvent
    public static void onServerBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (!(event.getEntity() instanceof ServerPlayer sp) || !sp.level().dimension().equals(Level.OVERWORLD)) return;
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
    public static void onServerBreakBlock(BlockEvent.BreakEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayer sp) || !sp.level().dimension().equals(Level.OVERWORLD)) return;
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
    public static void onServerAttackDecorativeEntity(AttackEntityEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer sp) || !sp.level().dimension().equals(Level.OVERWORLD)) return;
        Entity target = event.getTarget();

        if (target instanceof HangingEntity || target instanceof ArmorStand) {
            BlockPos pos = target.blockPosition();
            if (!ChunkClaimManager.hasPermission(sp, pos)) {
                increaseSuspicion(sp, SuspiciousAction.BLOCK_BREAK, Blocks.AIR, pos);
            }
        }
    }


    /**
     * SERVER-ONLY
     */
    @SubscribeEvent
    public static void onServerRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!(event.getEntity() instanceof ServerPlayer sp) || !sp.level().dimension().equals(Level.OVERWORLD)) return;

        if (sp.hasEffect(ReignModModMobEffects.SUSPECT.get())) {
            cancelEvent(event);
            return;
        }

        Item targetItem = event.getItemStack().getItem();
        BlockPos pos = event.getPos().relative(event.getFace());

        if (targetItem instanceof BlockItem blockItem) {
            if (!ChunkClaimManager.hasPermission(sp, pos)) {
                increaseSuspicion(sp, SuspiciousAction.BLOCK_PLACE, blockItem.getBlock(), pos);
                sp.addEffect(new MobEffectInstance(ReignModModMobEffects.SUSPECT.get(), 200, 0, false, false, true));
            }
        } else if (targetItem instanceof HangingEntityItem || targetItem instanceof ArmorStandItem) {
            if (!ChunkClaimManager.hasPermission(sp, pos)) {
                increaseSuspicion(sp, SuspiciousAction.BLOCK_PLACE, Blocks.AIR, pos);
                sp.addEffect(new MobEffectInstance(ReignModModMobEffects.SUSPECT.get(), 200, 0, false, false, true));
            }
        }
    }

    /**
     * SERVER-ONLY
     */
    @SubscribeEvent
    public static void onServerEnteringSection(EntityEvent.EnteringSection event) {
        if (!(event.getEntity() instanceof ServerPlayer sp) || !sp.level().dimension().equals(Level.OVERWORLD)) return;

        Optional<String> oldOwnerData = ChunkClaimManager.getChunkOwnerName(SectionPos.of(event.getPackedOldPos()).chunk());
        Optional<String> newOwnerData = ChunkClaimManager.getChunkOwnerName(SectionPos.of(event.getPackedNewPos()).chunk());

        if (oldOwnerData.isPresent() && newOwnerData.isPresent()) {
            String oldOwner = oldOwnerData.get();
            String newOwner = newOwnerData.get();

            if (!Objects.equals(oldOwner, newOwner)) {
                sp.displayClientMessage(Component.nullToEmpty(newOwner), true);
            }
        } else if (newOwnerData.isPresent()) {
            String owner = newOwnerData.get();
            sp.displayClientMessage(Component.nullToEmpty(owner), true);
        } else if (oldOwnerData.isPresent()) {
            sp.displayClientMessage(Component.nullToEmpty(ChatFormatting.GREEN + "Ничейные земли"), true);
        }
    }


    /**
     * CLIENT-ONLY
     */
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (!(event.getEntity() instanceof LocalPlayer lp) || !lp.level().dimension().equals(Level.OVERWORLD)) return;

        BlockPos pos = event.getPosition().orElse(null);
        if (pos == null) return;

        ChunkPos cp = new ChunkPos(pos);

        if (ClientPlayerData.isLastKnownChunkEmpty()) {
            ReignNetworking.sendToServer(new ChunkBreakPermissionQueryC2SPacket(cp.x, cp.z));
        }

        if (ClientPlayerData.isLastKnownChunk(cp.x, cp.z)) {
            if (!ClientPlayerData.isLastKnownChunkAvailable()) {
                event.setNewSpeed(event.getNewSpeed() / BLOCK_BREAK_MULTIPLIER);
            }
        }
    }

    /**
     * CLIENT-ONLY
     */
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!(event.getEntity() instanceof LocalPlayer lp) || !lp.level().dimension().equals(Level.OVERWORLD)) return;
        if (lp.hasEffect(ReignModModMobEffects.SUSPECT.get())) {
            cancelEvent(event);
            event.setUseItem(PlayerInteractEvent.Result.DENY);
            event.setUseBlock(PlayerInteractEvent.Result.DENY);
            event.setCancellationResult(InteractionResult.FAIL);
        }

        Item targetItem = event.getItemStack().getItem();

        if (targetItem instanceof BlockItem || targetItem instanceof HangingEntityItem || targetItem instanceof ArmorStandItem) {
            ChunkPos cp = new ChunkPos(event.getPos().relative(Objects.requireNonNull(event.getFace())));
            if (ClientPlayerData.isLastKnownChunkEmpty()) {
                ReignNetworking.sendToServer(new ChunkBreakPermissionQueryC2SPacket(cp.x, cp.z));
            }

            if (ClientPlayerData.isLastKnownChunk(cp.x, cp.z)) {
                if (!ClientPlayerData.isLastKnownChunkAvailable()) {
                    lp.addEffect(new MobEffectInstance(ReignModModMobEffects.SUSPECT.get(), 200, 0, false, false, true));
                }
            }

        }
    }

    /**
     * CLIENT-ONLY
     */
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientEnteringSection(EntityEvent.EnteringSection event) {
        if (event.getEntity() instanceof LocalPlayer) {
            ClientPlayerData.setLastKnownChunk();
        }
    }

    /**
     * CLIENT-ONLY
     */
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientPlayerLogout(ClientPlayerNetworkEvent.LoggingIn event) {
        ClientPlayerData.setLastKnownChunk();
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
