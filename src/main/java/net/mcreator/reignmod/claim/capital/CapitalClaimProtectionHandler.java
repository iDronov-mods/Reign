package net.mcreator.reignmod.claim.capital;

import net.mcreator.reignmod.kingdom.KingdomData;
import net.mcreator.reignmod.networking.ClientPlayerData;
import net.mcreator.reignmod.networking.ReignNetworking;
import net.mcreator.reignmod.networking.packet.C2S.BlockBreakPermissionQueryC2SPacket;
import net.mcreator.reignmod.networking.packet.C2S.ChunkBreakPermissionQueryC2SPacket;
import net.mcreator.reignmod.procedures.IsKingProcedure;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;

import static net.mcreator.reignmod.ReignModMod.MODID;

/**
 * Обработчик защиты территорий столицы.
 * Если система привата включена и игрок не имеет доступа к блоку, взаимодействие с блоком ограничивается.
 */
@Mod.EventBusSubscriber(modid = MODID)
public class CapitalClaimProtectionHandler {

    /**
     * Проверяет, находится ли блок в пределах столицы (локальные координаты от 0 до 257).
     */
    private static boolean isWithinCapital(int localX, int localZ) {
        return localX >= 0 && localX < CapitalClaimSavedData.CAPITAL_SIZE &&
                localZ >= 0 && localZ < CapitalClaimSavedData.CAPITAL_SIZE;
    }

    /**
     * Проверяет, имеет ли игрок доступ к блоку по мировым координатам.
     * Преобразует мировые координаты в локальные и проверяет, что блок находится в пределах столицы.
     */
    public static boolean hasPermission(ServerPlayer player, BlockPos pos) {
        if (!CapitalClaimSavedData.getInstance().isCapitalClaimsEnabled() || player.gameMode.isCreative()) {
            return true;
        }

        int localX = CapitalClaimManager.toLocalX(pos.getX());
        int localZ = CapitalClaimManager.toLocalZ(pos.getZ());
        if (!isWithinCapital(localX, localZ)) {
            return true;
        }

        if (player.getStringUUID().equals(KingdomData.getCourtier(KingdomData.CourtPosition.HAND_OF_THE_KING)) || IsKingProcedure.execute(CapitalClaimSavedData.getInstance().getServerLevelInstance(), player)) {
            return true;
        }

        ClaimOwner owner = CapitalClaimSavedData.getInstance().getOwnerAt(localX, localZ);

        if (owner == null && player.getStringUUID().equals(KingdomData.getCourtier(KingdomData.CourtPosition.ARCHITECT))) {
            return true;
        }

        return owner != null && owner.hasAccess(player.getUUID());
    }

    /**
     * SERVER-ONLY
     */
    @SubscribeEvent
    public static void onServerBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (!(event.getEntity() instanceof ServerPlayer player) || !player.level().dimension().equals(Level.OVERWORLD))
            return;
        BlockPos pos = event.getPosition().orElse(null);
        if (pos == null) return;

        if (!hasPermission(player, pos)) {
            event.setCanceled(true);
        }
    }

    /**
     * SERVER-ONLY
     */
    @SubscribeEvent
    public static void onServerBlockBreak(BlockEvent.BreakEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayer player) || !player.level().dimension().equals(Level.OVERWORLD))
            return;
        BlockPos pos = event.getPos();
        if (!hasPermission(player, pos)) {
            event.setCanceled(true);
        }
    }

    /**
     * SERVER-ONLY
     */
    @SubscribeEvent
    public static void onServerBlockPlace(BlockEvent.EntityPlaceEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player) || !player.level().dimension().equals(Level.OVERWORLD))
            return;
        BlockPos pos = event.getPos();
        if (!hasPermission(player, pos)) {
            event.setCanceled(true);
        }
    }

    /**
     * CLIENT-ONLY
     */
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (!(event.getEntity() instanceof LocalPlayer player) || !player.level().dimension().equals(Level.OVERWORLD)) return;

        BlockPos pos = event.getPosition().orElse(null);
        if (pos == null) return;

        if (!ClientPlayerData.isLastKnownBlock(pos)) {
            ReignNetworking.sendToServer(new BlockBreakPermissionQueryC2SPacket(pos));
        }

        if (!ClientPlayerData.isLastKnownBlockAvailable()) {
            event.setCanceled(true);
        }
    }

    /**
     * CLIENT-ONLY
     */
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientBlockBreak(BlockEvent.BreakEvent event) {
        if (!(event.getPlayer() instanceof LocalPlayer player) || !player.level().dimension().equals(Level.OVERWORLD))
            return;
        BlockPos pos = event.getPos();

        if (!ClientPlayerData.isLastKnownBlock(pos)) {
            ReignNetworking.sendToServer(new BlockBreakPermissionQueryC2SPacket(pos));
        }

        if (!ClientPlayerData.isLastKnownBlockAvailable()) {
            event.setCanceled(true);
        }
    }

    /**
     * CLIENT-ONLY
     */
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientBlockPlace(BlockEvent.EntityPlaceEvent event) {
        if (!(event.getEntity() instanceof LocalPlayer player) || !player.level().dimension().equals(Level.OVERWORLD))
            return;
        BlockPos pos = event.getPos();

        if (!ClientPlayerData.isLastKnownBlock(pos)) {
            ReignNetworking.sendToServer(new BlockBreakPermissionQueryC2SPacket(pos));
        }

        if (!ClientPlayerData.isLastKnownBlockAvailable()) {
            event.setCanceled(true);
        }
    }

    public static void register() {
        MinecraftForge.EVENT_BUS.register(CapitalClaimProtectionHandler.class);
    }
}
