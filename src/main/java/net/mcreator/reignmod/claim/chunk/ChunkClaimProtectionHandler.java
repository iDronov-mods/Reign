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
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
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

        if (!sp.isCreative() && sp.hasEffect(ReignModModMobEffects.SUSPECT.get())) {
            cancelEvent(event);
            return;
        }

        if (!ChunkClaimManager.hasPermission(sp, pos)) {
            increaseSuspicion(sp, SuspiciousAction.BLOCK_BREAK, event.getState().getBlock(), event.getPos());
            sp.addEffect(new MobEffectInstance(ReignModModMobEffects.SUSPECT.get(),100, 0,false, false, true));
            sp.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,100, 0,false, false, true));
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

        Item targetItem = event.getItemStack().getItem();
        BlockPos pos = event.getPos().relative(Objects.requireNonNull(event.getFace()));

        Block blockToReport;
        if (targetItem instanceof BlockItem blockItem) {
            blockToReport = blockItem.getBlock();
        } else if (targetItem instanceof HangingEntityItem || targetItem instanceof ArmorStandItem) {
            blockToReport = Blocks.AIR;
        } else if (targetItem instanceof BucketItem) {
            blockToReport = Blocks.WATER;
        } else if (targetItem instanceof ShovelItem) {
            blockToReport = Blocks.DIRT_PATH;
        } else{
            return;
        }

        if (!sp.isCreative() && sp.hasEffect(ReignModModMobEffects.SUSPECT.get())) {
            cancelEvent(event);
            return;
        }

        if (!ChunkClaimManager.hasPermission(sp, pos)) {
            increaseSuspicion(sp, SuspiciousAction.BLOCK_PLACE, blockToReport, pos);
            sp.addEffect(new MobEffectInstance(ReignModModMobEffects.SUSPECT.get(),200, 0,false, false, true));
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

        if (!lp.isCreative() && lp.hasEffect(ReignModModMobEffects.SUSPECT.get())) {
            cancelEvent(event);
        }

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
    public static void onClientBreakBlock(BlockEvent.BreakEvent event) {
        if (!(event.getPlayer() instanceof LocalPlayer lp)) return;
        if (!lp.level().dimension().equals(Level.OVERWORLD)) return;

        if (!lp.isCreative() && lp.hasEffect(ReignModModMobEffects.SUSPECT.get())) {
            cancelEvent(event);
        }
    }

    /**
     * CLIENT-ONLY
     */
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!(event.getEntity() instanceof LocalPlayer lp)) return;
        if (!lp.level().dimension().equals(Level.OVERWORLD)) return;

        Item item = event.getItemStack().getItem();
        if (!(item instanceof BlockItem
                || item instanceof HangingEntityItem
                || item instanceof ArmorStandItem
                || item instanceof BucketItem
                || item instanceof ShovelItem)) {
            return;
        }

        if (!lp.isCreative() && lp.hasEffect(ReignModModMobEffects.SUSPECT.get())) {
            cancelEvent(event);
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
            ClientPlayerData.setLastKnownBlock();
            ClientPlayerData.setLastKnownDoor();
        }
    }

    /**
     * CLIENT-ONLY
     */
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientPlayerLogout(ClientPlayerNetworkEvent.LoggingIn event) {
        ClientPlayerData.setLastKnownChunk();
        ClientPlayerData.setLastKnownBlock();
        ClientPlayerData.setLastKnownDoor();
    }

    /**
     * COMMON
     */
    private static void cancelEvent(Event event) {
        if (event.isCancelable()) event.setCanceled(true);
        if (event instanceof PlayerInteractEvent.RightClickBlock rc) {
            rc.setUseItem(PlayerInteractEvent.Result.DENY);
            rc.setUseBlock(PlayerInteractEvent.Result.DENY);
            rc.setCancellationResult(InteractionResult.FAIL);
        }
        if (event instanceof PlayerInteractEvent.RightClickItem rc) {
            rc.setCancellationResult(InteractionResult.FAIL);
        }
    }

    /**
     * COMMON
     */
    private static int getSuspicionByBlock(SuspiciousAction susAction, Block block) {
        int placeSuspicion = 5;
        int breakSuspicion = 10;

        if (block instanceof BushBlock || block instanceof DirtPathBlock || block instanceof SnowLayerBlock) {
            placeSuspicion = 1;
            breakSuspicion = 1;
        } else if (block instanceof ChestBlock || block instanceof BarrelBlock || block instanceof DoorBlock) {
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
