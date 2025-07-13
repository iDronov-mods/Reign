package net.mcreator.reignmod.claim.capital;

import com.mojang.authlib.GameProfile;
import net.mcreator.reignmod.networking.ReignNetworking;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.GameProfileCache;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Менеджер приватов столицы.
 * Предоставляет методы для создания, удаления приватов и управления системой привата.
 * Также хранит координаты "сердца" столицы (фонд), на которые приводятся координаты приватов.
 */
public class CapitalClaimManager {

    private static int capitalCenterX = 0;
    private static int capitalCenterZ = 0;

    // Для приведения мировых координат к локальным: локальный центр = ( (CAPITAL_SIZE+1)/2, (CAPITAL_SIZE+1)/2 )
    // offset = (capitalHeart - (CAPITAL_SIZE+1)/2)
    private static int offsetX() {
        return capitalCenterX - ((CapitalClaimSavedData.CAPITAL_SIZE + 1) / 2);
    }

    private static int offsetZ() {
        return capitalCenterZ - ((CapitalClaimSavedData.CAPITAL_SIZE + 1) / 2);
    }

    public static int getCapitalCenterX() {
        return capitalCenterX;
    }

    public static int getCapitalCenterZ() {
        return capitalCenterZ;
    }

    /**
     * Устанавливает координаты сердца столицы.
     * Эти координаты будут использоваться для преобразования мировых координат в локальные.
     */
    public static void setCapitalCenter(int x, int z) {
        capitalCenterX = x;
        capitalCenterZ = z;
    }

    public static void setCapitalCenter(ServerPlayer sp) {
        setCapitalCenter(sp.getBlockX(), sp.getBlockZ());
    }

    /**
     * Преобразует мировую X-координату в локальную (относительно столицы).
     */
    public static int toLocalX(int worldX) {
        return worldX - offsetX();
    }

    /**
     * Преобразует мировую Z-координату в локальную (относительно столицы).
     */
    public static int toLocalZ(int worldZ) {
        return worldZ - offsetZ();
    }

    /**
     * Добавляет приват.
     *
     * @param serverPlayer Игрок, инициирующий создание привата.
     * @param owners       Строка с никнеймами владельцев, разделёнными запятыми и/или пробелами.
     *                     Первый ник – главный владелец, остальные – совладельцы.
     * @param centerX      Мировая X-координата центра привата.
     * @param centerY      Мировая Y-координата центра привата.
     * @param centerZ      Мировая Z-координата центра привата.
     * @param width        Ширина привата (нечётное число).
     * @param height       Высота привата (нечётное число).
     */
    public static boolean addClaim(ServerPlayer serverPlayer, String owners, double centerX, double centerY, double centerZ, String width, String height) {
        // Проверяем, включена ли система привата
        if (!CapitalClaimSavedData.getInstance().isCapitalClaimsEnabled()) {
            serverPlayer.displayClientMessage(Component.translatable("capitalclaim.system.disabled"), true);
            return false;
        }

        // Парсим строку с никнеймами
        List<String> ownerNames = parseOwnerNames(owners);
        if (ownerNames.isEmpty()) {
            serverPlayer.displayClientMessage(Component.translatable("capitalclaim.add.fail", "Empty owners list"), true);
            return false;
        }

        MinecraftServer server = serverPlayer.getServer();
        // Получаем UUID главного владельца
        UUID mainOwnerUUID = getOfflinePlayerUUID(server, ownerNames.get(0));
        if (mainOwnerUUID == null) {
            serverPlayer.displayClientMessage(Component.translatable("capitalclaim.add.fail", "Main owner not found: " + ownerNames.get(0)), true);
            return false;
        }
        ClaimOwner claimOwner = new ClaimOwner(mainOwnerUUID);

        // Добавляем совладельцев
        for (int i = 1; i < ownerNames.size(); i++) {
            String name = ownerNames.get(i);
            UUID uuid = getOfflinePlayerUUID(server, name);
            if (uuid != null) {
                claimOwner.addCoOwner(uuid);
            } else {
                serverPlayer.displayClientMessage(Component.translatable("capitalclaim.add.warn", "Co-owner not found: " + name), true);
            }
        }

        // Приводим значения double к int
        int localCenterX = toLocalX((int) centerX);
        int localCenterZ = toLocalZ((int) centerZ);
        int intCenterY = (int) centerY;

        // Преобразуем width и height из String в int
        int intWidth;
        int intHeight;
        try {
            intWidth = Integer.parseInt(width);
            intHeight = Integer.parseInt(height);
        } catch (NumberFormatException e) {
            serverPlayer.displayClientMessage(Component.translatable("capitalclaim.add.fail", "Invalid width/height format."), true);
            return false;
        }

        TerritoryClaim claim;
        try {
            claim = new TerritoryClaim(localCenterX, intCenterY, localCenterZ, intWidth, intHeight);
        } catch (IllegalArgumentException ex) {
            serverPlayer.displayClientMessage(Component.translatable("capitalclaim.add.fail", ex.getMessage()), true);
            return false;
        }

        boolean success = CapitalClaimSavedData.getInstance().addClaim(claimOwner, claim);
        if (success) {
            StringBuilder ownersList = new StringBuilder();
            ownersList.append(ownerNames.get(0));
            for (int i = 1; i < ownerNames.size(); i++) {
                ownersList.append(", ").append(ownerNames.get(i));
            }
            serverPlayer.displayClientMessage(Component.translatable("capitalclaim.add.success",
                    (int) centerX, intCenterY, (int) centerZ, ownersList.toString()), true);
            ReignNetworking.resetLastKnownBlockForAllPlayers();
            return true;
        } else {
            serverPlayer.displayClientMessage(Component.translatable("capitalclaim.add.fail", "Area is occupied or invalid."), true);
            return false;
        }
    }

    /**
     * Удаляет приват по центру, возвращая результат вызвавшему функцию игроку.
     *
     * @param player  Игрок, инициирующий удаление.
     * @param centerX Мировая X-координата центра привата.
     * @param centerZ Мировая Z-координата центра привата.
     */
    public static boolean removeClaim(Player player, double centerX, double centerZ) {
        if (player instanceof ServerPlayer serverPlayer) {
            if (!CapitalClaimSavedData.getInstance().isCapitalClaimsEnabled()) {
                serverPlayer.displayClientMessage(Component.translatable("capitalclaim.system.disabled"), true);
                return false;
            }
            int localCenterX = toLocalX((int) centerX);
            int localCenterZ = toLocalZ((int) centerZ);
            if (CapitalClaimSavedData.getInstance().removeClaim(localCenterX, localCenterZ)) {
                serverPlayer.displayClientMessage(Component.translatable("capitalclaim.remove.success"), true);
                ReignNetworking.resetLastKnownBlockForAllPlayers();
                return true;
            } else {
                serverPlayer.displayClientMessage(Component.translatable("capitalclaim.remove.fail"), true);
            }
        }
        return false;
    }

    /**
     * Удаляет приват по центру.
     *
     * @param centerX Мировая X-координата центра привата.
     * @param centerZ Мировая Z-координата центра привата.
     */
    public static boolean removeClaim(double centerX, double centerZ) {
        if (CapitalClaimSavedData.getInstance().isCapitalClaimsEnabled()) {
            int localCenterX = toLocalX((int) centerX);
            int localCenterZ = toLocalZ((int) centerZ);
            return CapitalClaimSavedData.getInstance().removeClaim(localCenterX, localCenterZ);
        }
        return false;
    }

    /**
     * Включает систему привата.
     */
    public static void enable() {
        CapitalClaimSavedData.getInstance().enable();
    }

    /**
     * Выключает систему привата.
     */
    public static void disable() {
        CapitalClaimSavedData.getInstance().disable();
    }

    /**
     * Парсит строку с никнеймами владельцев.
     */
    private static List<String> parseOwnerNames(String owners) {
        List<String> names = new ArrayList<>();
        if (owners == null || owners.trim().isEmpty()) {
            return names;
        }
        String[] tokens = owners.split("[,\\s]+");
        for (String token : tokens) {
            if (!token.trim().isEmpty()) {
                names.add(token.trim());
            }
        }
        return names;
    }

    /**
     * Получает UUID оффлайн-игрока по имени.
     */
    public static UUID getOfflinePlayerUUID(MinecraftServer server, String playerName) {
        GameProfileCache cache = server.getProfileCache();
        if (cache != null) {
            Optional<GameProfile> profile = cache.get(playerName);
            if (profile.isPresent()) {
                return profile.get().getId();
            }
        }
        return null;
    }
}
