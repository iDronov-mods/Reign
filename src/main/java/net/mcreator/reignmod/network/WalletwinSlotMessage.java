
package net.mcreator.reignmod.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.world.inventory.WalletwinMenu;
import net.mcreator.reignmod.procedures.UpdateWalletProcedure;
import net.mcreator.reignmod.procedures.Change2SlotProcedure;
import net.mcreator.reignmod.procedures.Change1SlotProcedure;
import net.mcreator.reignmod.procedures.Change0SlotProcedure;
import net.mcreator.reignmod.ReignModMod;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class WalletwinSlotMessage {
	private final int slotID, x, y, z, changeType, meta;
	private HashMap<String, String> textstate;

	public WalletwinSlotMessage(int slotID, int x, int y, int z, int changeType, int meta, HashMap<String, String> textstate) {
		this.slotID = slotID;
		this.x = x;
		this.y = y;
		this.z = z;
		this.changeType = changeType;
		this.meta = meta;
		this.textstate = textstate;
	}

	public WalletwinSlotMessage(FriendlyByteBuf buffer) {
		this.slotID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
		this.changeType = buffer.readInt();
		this.meta = buffer.readInt();
		this.textstate = readTextState(buffer);
	}

	public static void buffer(WalletwinSlotMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.slotID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
		buffer.writeInt(message.changeType);
		buffer.writeInt(message.meta);
		writeTextState(message.textstate, buffer);
	}

	public static void handler(WalletwinSlotMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int slotID = message.slotID;
			int changeType = message.changeType;
			int meta = message.meta;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			HashMap<String, String> textstate = message.textstate;
			handleSlotAction(entity, slotID, changeType, meta, x, y, z, textstate);
		});
		context.setPacketHandled(true);
	}

	public static void handleSlotAction(Player entity, int slot, int changeType, int meta, int x, int y, int z, HashMap<String, String> textstate) {
		Level world = entity.level();
		HashMap guistate = WalletwinMenu.guistate;
		for (Map.Entry<String, String> entry : textstate.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			guistate.put(key, value);
		}
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (slot == 0 && changeType == 0) {

			Change0SlotProcedure.execute(world, x, y, z, entity);
		}
		if (slot == 0 && changeType == 1) {

			UpdateWalletProcedure.execute(entity);
		}
		if (slot == 1 && changeType == 0) {

			Change1SlotProcedure.execute(world, x, y, z, entity);
		}
		if (slot == 1 && changeType == 1) {

			UpdateWalletProcedure.execute(entity);
		}
		if (slot == 2 && changeType == 0) {

			Change2SlotProcedure.execute(world, x, y, z, entity);
		}
		if (slot == 2 && changeType == 1) {

			UpdateWalletProcedure.execute(entity);
		}
		if (slot == 3 && changeType == 0) {

			UpdateWalletProcedure.execute(entity);
		}
		if (slot == 3 && changeType == 1) {

			UpdateWalletProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		ReignModMod.addNetworkMessage(WalletwinSlotMessage.class, WalletwinSlotMessage::buffer, WalletwinSlotMessage::new, WalletwinSlotMessage::handler);
	}

	public static void writeTextState(HashMap<String, String> map, FriendlyByteBuf buffer) {
		buffer.writeInt(map.size());
		for (Map.Entry<String, String> entry : map.entrySet()) {
			buffer.writeComponent(Component.literal(entry.getKey()));
			buffer.writeComponent(Component.literal(entry.getValue()));
		}
	}

	public static HashMap<String, String> readTextState(FriendlyByteBuf buffer) {
		int size = buffer.readInt();
		HashMap<String, String> map = new HashMap<>();
		for (int i = 0; i < size; i++) {
			String key = buffer.readComponent().getString();
			String value = buffer.readComponent().getString();
			map.put(key, value);
		}
		return map;
	}
}
