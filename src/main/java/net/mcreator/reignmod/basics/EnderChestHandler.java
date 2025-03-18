package net.mcreator.reignmod.basics;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class EnderChestHandler {

    /**
     * Открывает эндер сундук случайного игрока, присутствующего на сервере.
     * @param viewer Игрок, который открывает сундук.
     */
    public static void openEnderChest(Player viewer) {
        if (viewer instanceof ServerPlayer serverViewer) {
            PlayerList playerList = serverViewer.getServer().getPlayerList();
            List<ServerPlayer> players = playerList.getPlayers();

            // Выбираем случайного игрока
            Random random = new Random();
            ServerPlayer randomPlayer = players.get(random.nextInt(players.size()));

            MenuProvider provider = getMenuProvider(randomPlayer);

            // Открываем инвентарь
            serverViewer.openMenu(provider);
        }
    }

    @NotNull
    private static MenuProvider getMenuProvider(ServerPlayer randomPlayer) {
        PlayerEnderChestContainer enderChest = randomPlayer.getEnderChestInventory();
        return new MenuProvider() {
            @Override
            public @NotNull Component getDisplayName() {
                return Component.translatable ("translation.key.ender_lock_pick_chest_open_player_name").append(randomPlayer.getName().getString());
            }

            @Override
            public AbstractContainerMenu createMenu(int id, @NotNull Inventory playerInventory, @NotNull Player player) {
                return new ChestMenu(MenuType.GENERIC_9x3, id, playerInventory, enderChest, 3);
            }
        };
    }
}