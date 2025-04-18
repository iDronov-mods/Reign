package net.mcreator.reignmod.networking.packet.S2C;

import net.mcreator.reignmod.networking.ClientPlayerData;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Ответ сервер -> клиент: «В чанке (chunkX, chunkZ) можно ли ломать? (canBreak)»
 */
public class BlockBreakPermissionSyncS2CPacket {

    private final BlockPos blockPos;
    private final boolean canBreak;

    public BlockBreakPermissionSyncS2CPacket(BlockPos blockPos, boolean canBreak) {
        this.blockPos = new BlockPos(blockPos);
        this.canBreak = canBreak;
    }

    public BlockBreakPermissionSyncS2CPacket(FriendlyByteBuf buf) {
        this.blockPos = new BlockPos(buf.readBlockPos());
        this.canBreak = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(this.blockPos);
        buf.writeBoolean(canBreak);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            // Клиентская логика
            ClientPlayerData.setLastKnownBlock(blockPos, canBreak);
        });
    }
}