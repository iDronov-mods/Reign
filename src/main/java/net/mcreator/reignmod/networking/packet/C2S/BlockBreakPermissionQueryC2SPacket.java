package net.mcreator.reignmod.networking.packet.C2S;

import net.mcreator.reignmod.claim.capital.CapitalClaimManager;
import net.mcreator.reignmod.claim.capital.CapitalClaimProtectionHandler;
import net.mcreator.reignmod.claim.chunk.ChunkClaimManager;
import net.mcreator.reignmod.networking.ReignNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Запрос от клиента: «Могу ли я ломать блок на координатах (blockPos)?».
 */
public class BlockBreakPermissionQueryC2SPacket {

    private final BlockPos blockPos;

    public BlockBreakPermissionQueryC2SPacket(BlockPos blockPos) {
        this.blockPos = new BlockPos(blockPos);
    }

    public BlockBreakPermissionQueryC2SPacket(FriendlyByteBuf buf) {
        this.blockPos = new BlockPos(buf.readBlockPos());
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(this.blockPos);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            // Серверная логика
            ServerPlayer sender = ctx.getSender();
            if (sender != null) {
                boolean canBreak = CapitalClaimProtectionHandler.hasPermission(sender, blockPos);
                ReignNetworking.sendBlockBreakPermissionSync(sender, blockPos, canBreak);
            }
        });
    }

    public BlockPos getBlockPos() { return blockPos; }
}
