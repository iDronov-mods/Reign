package net.mcreator.reignmod.networking;

import net.mcreator.reignmod.networking.packet.C2S.BlockBreakPermissionQueryC2SPacket;
import net.mcreator.reignmod.networking.packet.C2S.ChunkBreakPermissionQueryC2SPacket;
import net.mcreator.reignmod.networking.packet.C2S.DoorOpenPermissionQueryC2SPacket;
import net.mcreator.reignmod.networking.packet.S2C.BlockBreakPermissionSyncS2CPacket;
import net.mcreator.reignmod.networking.packet.S2C.ChunkBreakPermissionSyncS2CPacket;
import net.mcreator.reignmod.networking.packet.S2C.DoorOpenPermissionSyncS2CPacket;
import net.mcreator.reignmod.networking.packet.S2C.PlayerPrefixSyncS2CPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.server.ServerLifecycleHooks;

public class ReignNetworking {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation("reign_mod", "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(PlayerPrefixSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PlayerPrefixSyncS2CPacket::new)
                .encoder(PlayerPrefixSyncS2CPacket::toBytes)
                .consumerMainThread(PlayerPrefixSyncS2CPacket::handle)
                .add();

        net.messageBuilder(ChunkBreakPermissionQueryC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ChunkBreakPermissionQueryC2SPacket::new)
                .encoder(ChunkBreakPermissionQueryC2SPacket::toBytes)
                .consumerMainThread(ChunkBreakPermissionQueryC2SPacket::handle)
                .add();

        net.messageBuilder(ChunkBreakPermissionSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ChunkBreakPermissionSyncS2CPacket::new)
                .encoder(ChunkBreakPermissionSyncS2CPacket::toBytes)
                .consumerMainThread(ChunkBreakPermissionSyncS2CPacket::handle)
                .add();

        net.messageBuilder(BlockBreakPermissionQueryC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(BlockBreakPermissionQueryC2SPacket::new)
                .encoder(BlockBreakPermissionQueryC2SPacket::toBytes)
                .consumerMainThread(BlockBreakPermissionQueryC2SPacket::handle)
                .add();

        net.messageBuilder(BlockBreakPermissionSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(BlockBreakPermissionSyncS2CPacket::new)
                .encoder(BlockBreakPermissionSyncS2CPacket::toBytes)
                .consumerMainThread(BlockBreakPermissionSyncS2CPacket::handle)
                .add();
        net.messageBuilder(DoorOpenPermissionQueryC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(DoorOpenPermissionQueryC2SPacket::new)
                .encoder(DoorOpenPermissionQueryC2SPacket::toBytes)
                .consumerMainThread(DoorOpenPermissionQueryC2SPacket::handle)
                .add();
        net.messageBuilder(DoorOpenPermissionSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(DoorOpenPermissionSyncS2CPacket::new)
                .encoder(DoorOpenPermissionSyncS2CPacket::toBytes)
                .consumerMainThread(DoorOpenPermissionSyncS2CPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static void sendDoorOpenPermissionSync(ServerPlayer player, BlockPos pos, boolean canOpen) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new DoorOpenPermissionSyncS2CPacket(pos, canOpen));
    }

    public static void sendChunkBreakPermissionSync(ServerPlayer player, int chunkX, int chunkZ, boolean canBreak) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player),
                new ChunkBreakPermissionSyncS2CPacket(chunkX, chunkZ, canBreak));
    }

    public static void sendBlockBreakPermissionSync(ServerPlayer player, BlockPos blockPos, boolean canBreak) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player),
                new BlockBreakPermissionSyncS2CPacket(blockPos, canBreak));
    }

    public static void resetLastKnownBlockForAllPlayers() {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server == null) {
            return;
        }
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            sendBlockBreakPermissionSync(player, BlockPos.ZERO, true);
        }
    }
}