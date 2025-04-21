package net.mcreator.reignmod.basics;

import net.mcreator.reignmod.house.HouseSavedData;
import net.mcreator.reignmod.claim.capital.CapitalClaimSavedData;
import net.mcreator.reignmod.claim.chunk.ChunkClaimSavedData;


import net.mcreator.reignmod.kingdom.KingdomSavedData;
import net.mcreator.reignmod.market.MarketSavedData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.apache.logging.log4j.LogManager;
import net.minecraftforge.event.server.ServerStoppingEvent;





@EventBusSubscriber
public class ReignServerEvent {

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {
        MinecraftServer server = event.getServer();
        ServerLevel overworld = server.overworld();

        LogManager.getLogger("ReignMod").info("Mod configs are loading...");
        ConfigLoader.initialize();
        LogManager.getLogger("ReignMod").info("Mod configs are successfully loaded!");

        LogManager.getLogger("ReignMod").info("Kingdom data is loading...");
        KingdomSavedData.initialize(overworld);
        LogManager.getLogger("ReignMod").info("Kingdom data is successfully loaded!");

        LogManager.getLogger("ReignMod").info("Market data is loading...");
        MarketSavedData.initialize(overworld);
        LogManager.getLogger("ReignMod").info("Market data is successfully loaded!");

        LogManager.getLogger("ReignMod").info("House data is loading...");
        HouseSavedData.initialize(overworld);
        HouseSavedData.getInstance();
        LogManager.getLogger("ReignMod").info("House data is successfully loaded!");

        LogManager.getLogger("ReignMod").info("Capital claim data is loading...");
        CapitalClaimSavedData.initialize(overworld);
        LogManager.getLogger("ReignMod").info("Capital claim data is successfully loaded!");

        LogManager.getLogger("ReignMod").info("Chunk claim data is loading...");
        ChunkClaimSavedData.initialize(overworld);
        LogManager.getLogger("ReignMod").info("Chunk claim data is successfully loaded!");
    }

    @SubscribeEvent
    public static void onServerStopping(ServerStoppingEvent event) {
        KingdomSavedData.resetInstance();
        MarketSavedData.resetInstance();
        HouseSavedData.resetInstance();
        ChunkClaimSavedData.resetInstance();
        CapitalClaimSavedData.resetInstance();
    }
}