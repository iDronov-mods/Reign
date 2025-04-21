
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

import net.mcreator.reignmod.world.inventory.HouseSuspectsUIMenu;
import net.mcreator.reignmod.procedures.WantedSuspect7Procedure;
import net.mcreator.reignmod.procedures.WantedSuspect6Procedure;
import net.mcreator.reignmod.procedures.WantedSuspect5Procedure;
import net.mcreator.reignmod.procedures.WantedSuspect4Procedure;
import net.mcreator.reignmod.procedures.WantedSuspect3Procedure;
import net.mcreator.reignmod.procedures.WantedSuspect2Procedure;
import net.mcreator.reignmod.procedures.WantedSuspect1Procedure;
import net.mcreator.reignmod.procedures.PardonSuspect7Procedure;
import net.mcreator.reignmod.procedures.PardonSuspect6Procedure;
import net.mcreator.reignmod.procedures.PardonSuspect5Procedure;
import net.mcreator.reignmod.procedures.PardonSuspect4Procedure;
import net.mcreator.reignmod.procedures.PardonSuspect3Procedure;
import net.mcreator.reignmod.procedures.PardonSuspect2Procedure;
import net.mcreator.reignmod.procedures.PardonSuspect1Procedure;
import net.mcreator.reignmod.procedures.IncubatorHeartHitProcedure;
import net.mcreator.reignmod.ReignModMod;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class HouseSuspectsUIButtonMessage {
	private final int buttonID, x, y, z;
	private HashMap<String, String> textstate;

	public HouseSuspectsUIButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
		this.textstate = readTextState(buffer);
	}

	public HouseSuspectsUIButtonMessage(int buttonID, int x, int y, int z, HashMap<String, String> textstate) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
		this.textstate = textstate;

	}

	public static void buffer(HouseSuspectsUIButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
		writeTextState(message.textstate, buffer);
	}

	public static void handler(HouseSuspectsUIButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
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
		HashMap guistate = HouseSuspectsUIMenu.guistate;
		for (Map.Entry<String, String> entry : textstate.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			guistate.put(key, value);
		}
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			IncubatorHeartHitProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 1) {

			PardonSuspect1Procedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 2) {

			PardonSuspect2Procedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 3) {

			PardonSuspect3Procedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 4) {

			PardonSuspect4Procedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 5) {

			PardonSuspect5Procedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 6) {

			PardonSuspect6Procedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 7) {

			PardonSuspect7Procedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 8) {

			WantedSuspect1Procedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 9) {

			WantedSuspect2Procedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 10) {

			WantedSuspect3Procedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 11) {

			WantedSuspect4Procedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 12) {

			WantedSuspect5Procedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 13) {

			WantedSuspect6Procedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 14) {

			WantedSuspect7Procedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		ReignModMod.addNetworkMessage(HouseSuspectsUIButtonMessage.class, HouseSuspectsUIButtonMessage::buffer, HouseSuspectsUIButtonMessage::new, HouseSuspectsUIButtonMessage::handler);
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
