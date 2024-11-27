
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

import net.mcreator.reignmod.world.inventory.RoyaleSettingsMenu;
import net.mcreator.reignmod.procedures.ToolsupProcedure;
import net.mcreator.reignmod.procedures.ToolsdownProcedure;
import net.mcreator.reignmod.procedures.OtherupProcedure;
import net.mcreator.reignmod.procedures.OtherdownProcedure;
import net.mcreator.reignmod.procedures.OresupProcedure;
import net.mcreator.reignmod.procedures.OresdownProcedure;
import net.mcreator.reignmod.procedures.LogsupProcedure;
import net.mcreator.reignmod.procedures.LogsdownProcedure;
import net.mcreator.reignmod.procedures.FoodupProcedure;
import net.mcreator.reignmod.procedures.FooddownProcedure;
import net.mcreator.reignmod.procedures.CoalupProcedure;
import net.mcreator.reignmod.procedures.CoaldownProcedure;
import net.mcreator.reignmod.procedures.ArmorupProcedure;
import net.mcreator.reignmod.procedures.ArmordownProcedure;
import net.mcreator.reignmod.ReignModMod;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RoyaleSettingsButtonMessage {
	private final int buttonID, x, y, z;
	private HashMap<String, String> textstate;

	public RoyaleSettingsButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
		this.textstate = readTextState(buffer);
	}

	public RoyaleSettingsButtonMessage(int buttonID, int x, int y, int z, HashMap<String, String> textstate) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
		this.textstate = textstate;

	}

	public static void buffer(RoyaleSettingsButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
		writeTextState(message.textstate, buffer);
	}

	public static void handler(RoyaleSettingsButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
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
		HashMap guistate = RoyaleSettingsMenu.guistate;
		for (Map.Entry<String, String> entry : textstate.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			guistate.put(key, value);
		}
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			LogsupProcedure.execute(world);
		}
		if (buttonID == 1) {

			LogsdownProcedure.execute(world);
		}
		if (buttonID == 2) {

			CoalupProcedure.execute(world);
		}
		if (buttonID == 3) {

			CoaldownProcedure.execute(world);
		}
		if (buttonID == 4) {

			FoodupProcedure.execute(world);
		}
		if (buttonID == 5) {

			FooddownProcedure.execute(world);
		}
		if (buttonID == 6) {

			ToolsupProcedure.execute(world);
		}
		if (buttonID == 7) {

			ToolsdownProcedure.execute(world);
		}
		if (buttonID == 8) {

			ArmorupProcedure.execute(world);
		}
		if (buttonID == 9) {

			ArmordownProcedure.execute(world);
		}
		if (buttonID == 10) {

			OresupProcedure.execute(world);
		}
		if (buttonID == 11) {

			OresdownProcedure.execute(world);
		}
		if (buttonID == 12) {

			OtherupProcedure.execute(world);
		}
		if (buttonID == 13) {

			OtherdownProcedure.execute(world);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		ReignModMod.addNetworkMessage(RoyaleSettingsButtonMessage.class, RoyaleSettingsButtonMessage::buffer, RoyaleSettingsButtonMessage::new, RoyaleSettingsButtonMessage::handler);
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
