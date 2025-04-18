package net.mcreator.reignmod.networking.packet.S2C;

import net.mcreator.reignmod.networking.ClientPlayerData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Ответ сервер -> клиент: «В чанке (chunkX, chunkZ) можно ли ломать? (canBreak)»
 */
public class ChunkBreakPermissionSyncS2CPacket {

    private final int chunkX;
    private final int chunkZ;
    private final boolean canBreak;

    public ChunkBreakPermissionSyncS2CPacket(int chunkX, int chunkZ, boolean canBreak) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.canBreak = canBreak;
    }

    public ChunkBreakPermissionSyncS2CPacket(FriendlyByteBuf buf) {
        this.chunkX = buf.readInt();
        this.chunkZ = buf.readInt();
        this.canBreak = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(chunkX);
        buf.writeInt(chunkZ);
        buf.writeBoolean(canBreak);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            // Клиентская логика
            ClientPlayerData.setLastKnownChunk(this.chunkX, this.chunkZ, this.canBreak);
        });
    }
}