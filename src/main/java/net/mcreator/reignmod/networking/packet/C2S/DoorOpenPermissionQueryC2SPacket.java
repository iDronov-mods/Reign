package net.mcreator.reignmod.networking.packet.C2S;

import net.mcreator.reignmod.house.HouseManager;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.mcreator.reignmod.networking.ReignNetworking;
import net.mcreator.reignmod.block.PrivatedoorBlock;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.mcreator.reignmod.block.entity.PrivatedoorBlockEntity;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * Запрос от клиента → сервер: "Можно ли открыть эту дверь?"
 */
public class DoorOpenPermissionQueryC2SPacket {
    private final BlockPos pos;

    public DoorOpenPermissionQueryC2SPacket(BlockPos pos) {
        this.pos = pos;
    }

    public DoorOpenPermissionQueryC2SPacket(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    public void handle(Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            ServerPlayer sender = ctx.getSender();
            if (sender == null) return;

            Level world = sender.level();
            BlockEntity be = world.getBlockEntity(pos);
            boolean canOpen = true;

            if (be instanceof PrivatedoorBlockEntity door && world.getBlockState(pos).getBlock() instanceof PrivatedoorBlock) {
                var tag = door.getPersistentData();
                if (tag.contains("owner")) {
                    String owner = tag.getString("owner");
                    String lockType = tag.getString("lock_type");
                    canOpen = switch (lockType) {
                        case "personal" -> sender.getStringUUID().equals(owner);
                        case "domain" -> sender.getStringUUID().equals(owner)
                                || Objects.equals(HouseManager.getDomainLordByKnight(owner), sender.getStringUUID())
                                || Objects.equals(HouseManager.getPlayerDomainKnight(sender), owner);
                        case "house" -> Objects.equals(HouseManager.getPlayerHouseLord(sender), owner);
                        default -> false;
                    };
                }
            }

            ReignNetworking.sendDoorOpenPermissionSync(sender, pos, canOpen);
        });
        ctx.setPacketHandled(true);
    }
}
