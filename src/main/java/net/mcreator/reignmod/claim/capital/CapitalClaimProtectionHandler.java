package net.mcreator.reignmod.claim.capital;

import net.mcreator.reignmod.procedures.IsKingProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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
        LogManager.getLogger("ReignMod").info("Inside check");
        return localX >= 0 && localX < CapitalClaimSavedData.CAPITAL_SIZE &&
                localZ >= 0 && localZ < CapitalClaimSavedData.CAPITAL_SIZE;
    }

    /**
     * Проверяет, имеет ли игрок доступ к блоку по мировым координатам.
     * Преобразует мировые координаты в локальные и проверяет, что блок находится в пределах столицы.
     */
    private static boolean hasPermission(ServerPlayer player, BlockPos pos) {
        LogManager.getLogger("ReignMod").info("Checking permission))");

        if (!CapitalClaimSavedData.getInstance().isCapitalClaimsEnabled() || player.gameMode.isCreative()) {
            return true;
        }

        int localX = CapitalClaimManager.toLocalX(pos.getX());
        int localZ = CapitalClaimManager.toLocalZ(pos.getZ());
        if (!isWithinCapital(localX, localZ) || IsKingProcedure.execute(CapitalClaimSavedData.getInstance().getServerLevelInstance(), player)) {
            return true;
        }


        ClaimOwner owner = CapitalClaimSavedData.getInstance().getOwnerAt(localX, localZ);
        return owner != null && owner.hasAccess(player.getUUID());
    }

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        LogManager.getLogger("ReignMod").info("breaking speed ev");
        if (!(event.getEntity() instanceof ServerPlayer player) || !player.level().dimension().equals(Level.OVERWORLD)) return;
        BlockPos pos = event.getPosition().get();
        if (!hasPermission(player, pos)) {
            event.setNewSpeed(event.getNewSpeed() / 5.0F);
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        LogManager.getLogger("ReignMod").info("breaking block");
        if (!(event.getPlayer() instanceof ServerPlayer player) || !player.level().dimension().equals(Level.OVERWORLD)) return;
        BlockPos pos = event.getPos();
        if (!hasPermission(player, pos)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
        LogManager.getLogger("ReignMod").info("placing block");
        if (!(event.getEntity() instanceof ServerPlayer player) || !player.level().dimension().equals(Level.OVERWORLD)) return;
        BlockPos pos = event.getPos();
        if (!hasPermission(player, pos)) {
            event.setCanceled(true);
        }
    }

    public static void register() {
        MinecraftForge.EVENT_BUS.register(CapitalClaimProtectionHandler.class);
    }
}
