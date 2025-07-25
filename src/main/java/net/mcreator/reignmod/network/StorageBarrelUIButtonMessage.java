
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

import net.mcreator.reignmod.world.inventory.StorageBarrelUIMenu;
import net.mcreator.reignmod.procedures.StorageBarrelSelectToolsProcedure;
import net.mcreator.reignmod.procedures.StorageBarrelSelectOtherProcedure;
import net.mcreator.reignmod.procedures.StorageBarrelSelectOresProcedure;
import net.mcreator.reignmod.procedures.StorageBarrelSelectLogsProcedure;
import net.mcreator.reignmod.procedures.StorageBarrelSelectFuelProcedure;
import net.mcreator.reignmod.procedures.StorageBarrelSelectFoodsProcedure;
import net.mcreator.reignmod.procedures.StorageBarrelSelectExpProcedure;
import net.mcreator.reignmod.procedures.StorageBarrelSelectBlocksProcedure;
import net.mcreator.reignmod.procedures.StorageBarrelSelectArmorsProcedure;
import net.mcreator.reignmod.ReignModMod;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class StorageBarrelUIButtonMessage {
	private final int buttonID, x, y, z;
	private HashMap<String, String> textstate;

	public StorageBarrelUIButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
		this.textstate = readTextState(buffer);
	}

	public StorageBarrelUIButtonMessage(int buttonID, int x, int y, int z, HashMap<String, String> textstate) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
		this.textstate = textstate;

	}

	public static void buffer(StorageBarrelUIButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
		writeTextState(message.textstate, buffer);
	}

	public static void handler(StorageBarrelUIButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			HashMap<String, String> textstate = message.textstate;
			handleButtonAction(entity, buttonID, x, y, z, textstate);
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z, HashMap<String, String> textstate) {
		Level world = entity.level();
		HashMap guistate = StorageBarrelUIMenu.guistate;
		for (Map.Entry<String, String> entry : textstate.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			guistate.put(key, value);
		}
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			StorageBarrelSelectLogsProcedure.execute(world, x, y, z);
		}
		if (buttonID == 1) {

			StorageBarrelSelectFuelProcedure.execute(world, x, y, z);
		}
		if (buttonID == 2) {

			StorageBarrelSelectOresProcedure.execute(world, x, y, z);
		}
		if (buttonID == 3) {

			StorageBarrelSelectArmorsProcedure.execute(world, x, y, z);
		}
		if (buttonID == 4) {

			StorageBarrelSelectToolsProcedure.execute(world, x, y, z);
		}
		if (buttonID == 5) {

			StorageBarrelSelectBlocksProcedure.execute(world, x, y, z);
		}
		if (buttonID == 6) {

			StorageBarrelSelectExpProcedure.execute(world, x, y, z);
		}
		if (buttonID == 7) {

			StorageBarrelSelectOtherProcedure.execute(world, x, y, z);
		}
		if (buttonID == 8) {

			StorageBarrelSelectFoodsProcedure.execute(world, x, y, z);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		ReignModMod.addNetworkMessage(StorageBarrelUIButtonMessage.class, StorageBarrelUIButtonMessage::buffer, StorageBarrelUIButtonMessage::new, StorageBarrelUIButtonMessage::handler);
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
