package net.mcreator.reignmod.claim.capital;

import net.mcreator.reignmod.claim.chunk.ChunkClaimManager;
import net.mcreator.reignmod.claim.chunk.ChunkClaimProtectionHandler;
import net.mcreator.reignmod.init.ReignModModMobEffects;
import net.mcreator.reignmod.kingdom.KingdomData;
import net.mcreator.reignmod.kingdom.KingdomManager;
import net.mcreator.reignmod.networking.ClientPlayerData;
import net.mcreator.reignmod.networking.ReignNetworking;
import net.mcreator.reignmod.networking.packet.C2S.BlockBreakPermissionQueryC2SPacket;
import net.mcreator.reignmod.networking.packet.C2S.ChunkBreakPermissionQueryC2SPacket;
import net.mcreator.reignmod.procedures.IsKingProcedure;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;

import java.util.Objects;

import static net.mcreator.reignmod.ReignModMod.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class CapitalClaimProtectionHandler {

    private static boolean isWithinCapital(int localX, int localZ) {
        return localX >= 0 && localX < CapitalClaimSavedData.CAPITAL_SIZE &&
                localZ >= 0 && localZ < CapitalClaimSavedData.CAPITAL_SIZE;
    }

    public static boolean isWithinCapitalGlobal(int globalX, int globalZ) {
        return isWithinCapital(CapitalClaimManager.toLocalX(globalX), CapitalClaimManager.toLocalZ(globalZ));
    }

    public static boolean hasPermission(ServerPlayer player, BlockPos pos) {
        if (!CapitalClaimSavedData.getInstance().isCapitalClaimsEnabled() || player.gameMode.isCreative()) {
            return true;
        }

        int localX = CapitalClaimManager.toLocalX(pos.getX());
        int localZ = CapitalClaimManager.toLocalZ(pos.getZ());
        if (!isWithinCapital(localX, localZ)) {
            return true;
        }

        if (player.getStringUUID().equals(KingdomManager.getCourtier(KingdomData.CourtPosition.HAND_OF_THE_KING)) || IsKingProcedure.execute(CapitalClaimSavedData.getInstance().getServerLevelInstance(), player)) {
            return true;
        }

        ClaimOwner owner = CapitalClaimSavedData.getInstance().getOwnerAt(localX, localZ);

        if (owner == null && player.getStringUUID().equals(KingdomManager.getCourtier(KingdomData.CourtPosition.ARCHITECT))) {
            return true;
        }

        return owner != null && owner.hasAccess(player.getUUID());
    }

    private static boolean isOverworld(Level level) {
        return level.dimension().equals(Level.OVERWORLD);
    }

    private static boolean isPlacementItem(Item item) {
        return item instanceof BlockItem || item instanceof HangingEntityItem || item instanceof ArmorStandItem || item instanceof BucketItem;
    }

    private static boolean isUsableItem(Item item) {
        return item instanceof EnderpearlItem || item instanceof ChorusFruitItem;
    }

    private static void cancel(Event event) {
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

    // --- SERVER EVENTS ---

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (!(event.getEntity() instanceof ServerPlayer player) || !isOverworld(player.level())) return;
        BlockPos pos = event.getPosition().orElse(null);
        if (pos != null && !hasPermission(player, pos)) cancel(event);
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getPlayer() instanceof ServerPlayer player && isOverworld(player.level()) &&
                !hasPermission(player, event.getPos())) cancel(event);
    }

    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
        if (event.getEntity() instanceof ServerPlayer player && isOverworld(player.level()) &&
                !hasPermission(player, event.getPos())) cancel(event);
    }

    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            Entity target = event.getTarget();
            if ((target instanceof HangingEntity || target instanceof ArmorStand || target instanceof Boat) &&
                    !hasPermission(player, target.blockPosition())) cancel(event);
        }
    }

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickItem event) {
        if (event.getEntity() instanceof ServerPlayer player && isOverworld(player.level()) && isUsableItem(event.getItemStack().getItem())) {
            BlockPos pos = event.getPos();
            if (isWithinCapitalGlobal(pos.getX(), pos.getZ()))  {
                cancel(event);
            }
        }
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getEntity() instanceof ServerPlayer player && isOverworld(player.level()) && (isPlacementItem(event.getItemStack().getItem()) || isUsableItem(event.getItemStack().getItem()))) {
            BlockPos pos = event.getPos().relative(Objects.requireNonNull(event.getFace()));

            if (isUsableItem(event.getItemStack().getItem()) && isWithinCapitalGlobal(pos.getX(), pos.getZ()) || !hasPermission(player, pos)) {
                cancel(event);
            }
        }
    }

    // --- CLIENT EVENTS ---

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (!(event.getEntity() instanceof LocalPlayer player) || !isOverworld(player.level())) return;
        BlockPos pos = event.getPosition().orElse(null);

        if (!ClientPlayerData.isLastKnownBlock(pos)) {
            ReignNetworking.sendToServer(new BlockBreakPermissionQueryC2SPacket(pos));
        }

        if (!ClientPlayerData.isLastKnownBlockAvailable()) {
            cancel(event);
        }
    }


    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientBlockBreak(BlockEvent.BreakEvent event) {
        if (!(event.getPlayer() instanceof LocalPlayer player) || !isOverworld(player.level()))
            return;
        BlockPos pos = event.getPos();

        if (!ClientPlayerData.isLastKnownBlock(pos)) {
            ReignNetworking.sendToServer(new BlockBreakPermissionQueryC2SPacket(pos));
        }

        if (!ClientPlayerData.isLastKnownBlockAvailable()) {
            cancel(event);
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientRightClick(PlayerInteractEvent.RightClickItem event) {
        if (!(event.getEntity() instanceof LocalPlayer lp) || !isOverworld(lp.level()) || !isUsableItem(event.getItemStack().getItem())) return;

        BlockPos pos = event.getPos();

        if (isWithinCapitalGlobal(pos.getX(), pos.getZ())) {
            cancel(event);
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!(event.getEntity() instanceof LocalPlayer lp) || !isOverworld(lp.level()) || (!isPlacementItem(event.getItemStack().getItem()) && !isUsableItem(event.getItemStack().getItem()))) return;

        BlockPos pos = event.getPos().relative(Objects.requireNonNull(event.getFace()));

        if (isUsableItem(event.getItemStack().getItem()) && isWithinCapitalGlobal(pos.getX(), pos.getZ())) {
            cancel(event);
        }

        if (!ClientPlayerData.isLastKnownBlock(pos)) {
            ReignNetworking.sendToServer(new BlockBreakPermissionQueryC2SPacket(pos));
        }

        if (!ClientPlayerData.isLastKnownBlockAvailable()) {
            cancel(event);
        }
    }

    public static void register() {
        MinecraftForge.EVENT_BUS.register(CapitalClaimProtectionHandler.class);
    }
}
