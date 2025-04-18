
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.reignmod.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import net.mcreator.reignmod.network.OpenLicensesMessage;
import net.mcreator.reignmod.ReignModMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class ReignModModKeyMappings {
	public static final KeyMapping OPEN_LICENSES = new KeyMapping("key.reign_mod.open_licenses", GLFW.GLFW_KEY_J, "key.categories.reign") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				ReignModMod.PACKET_HANDLER.sendToServer(new OpenLicensesMessage(0, 0));
				OpenLicensesMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(OPEN_LICENSES);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				OPEN_LICENSES.consumeClick();
			}
		}
	}
}
