package net.mcreator.reignmod.basics;

import net.mcreator.reignmod.house.HouseSavedData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.apache.logging.log4j.LogManager;

@EventBusSubscriber
public class ReignServerEvent {

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {
        MinecraftServer server = event.getServer();
        ServerLevel overworld = server.overworld();
        LogManager.getLogger("ReignMod").info("House data is loading...");
        HouseSavedData.initialize(overworld);
        HouseSavedData.getInstance();
        LogManager.getLogger("ReignMod").info("House data is successfully loaded!");
    }
}