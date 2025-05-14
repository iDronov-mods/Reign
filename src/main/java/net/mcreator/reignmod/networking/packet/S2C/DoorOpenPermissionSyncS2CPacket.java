package net.mcreator.reignmod.networking.packet.S2C;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.mcreator.reignmod.networking.ClientPlayerData;

import java.util.function.Supplier;

public class DoorOpenPermissionSyncS2CPacket {
    private final BlockPos pos;
    private final boolean canOpen;

    public DoorOpenPermissionSyncS2CPacket(BlockPos pos, boolean canOpen) {
        this.pos = pos;
        this.canOpen = canOpen;
    }

    public DoorOpenPermissionSyncS2CPacket(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
        this.canOpen = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeBoolean(canOpen);
    }

    public void handle(Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            ClientPlayerData.setLastKnownDoor(pos, canOpen);
        });
        ctx.setPacketHandled(true);
    }
}
