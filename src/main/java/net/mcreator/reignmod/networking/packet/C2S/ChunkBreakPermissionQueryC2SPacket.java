package net.mcreator.reignmod.networking.packet.C2S;

import net.mcreator.reignmod.claim.chunk.ChunkClaimManager;
import net.mcreator.reignmod.networking.ReignNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Запрос от клиента: «Могу ли я ломать блок в чанке (chunkX, chunkZ)?».
 */
public class ChunkBreakPermissionQueryC2SPacket {

    private final int chunkX;
    private final int chunkZ;

    public ChunkBreakPermissionQueryC2SPacket(int chunkX, int chunkZ) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
    }

    public ChunkBreakPermissionQueryC2SPacket(FriendlyByteBuf buf) {
        this.chunkX = buf.readInt();
        this.chunkZ = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(chunkX);
        buf.writeInt(chunkZ);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            // Серверная логика
            ServerPlayer sender = ctx.getSender();
            if (sender != null) {
                boolean canBreak = ChunkClaimManager.hasPermission(sender, new ChunkPos(chunkX, chunkZ));
                ReignNetworking.sendChunkBreakPermissionSync(sender, chunkX, chunkZ, canBreak);
            }
        });
    }

    public int getChunkX() {
        return chunkX;
    }

    public int getChunkZ() {
        return chunkZ;
    }
}
