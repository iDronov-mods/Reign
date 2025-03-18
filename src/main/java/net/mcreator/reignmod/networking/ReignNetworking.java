package net.mcreator.reignmod.networking;

import net.mcreator.reignmod.networking.packet.C2S.ChunkBreakPermissionQueryC2SPacket;
import net.mcreator.reignmod.networking.packet.S2C.ChunkBreakPermissionSyncS2CPacket;
import net.mcreator.reignmod.networking.packet.S2C.PlayerPrefixSyncS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

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
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static void sendChunkBreakPermissionSync(ServerPlayer player, int chunkX, int chunkZ, boolean canBreak) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player),
                new ChunkBreakPermissionSyncS2CPacket(chunkX, chunkZ, canBreak));
    }
}