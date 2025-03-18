package net.mcreator.reignmod.networking.packet.S2C;

import net.mcreator.reignmod.networking.ClientPlayerData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.function.Supplier;

public class PlayerPrefixSyncS2CPacket {
    private final HashMap<String, String> playersPrefixes;

    public PlayerPrefixSyncS2CPacket(HashMap<String, String> playersPrefixes) {
        this.playersPrefixes = playersPrefixes;
    }

    public PlayerPrefixSyncS2CPacket(FriendlyByteBuf buf) {
        this.playersPrefixes = buf.readMap(HashMap::new, FriendlyByteBuf::readUtf, FriendlyByteBuf::readUtf);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeMap(playersPrefixes, FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeUtf);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // Клиентская логика
            ClientPlayerData.setPlayersPrefixes(playersPrefixes);
        });
    }
}
