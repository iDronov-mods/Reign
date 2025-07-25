package net.mcreator.reignmod.basics;

import net.mcreator.reignmod.claim.capital.CapitalClaimSavedData;
import net.mcreator.reignmod.claim.chunk.ChunkClaimSavedData;
import net.mcreator.reignmod.house.HouseSavedData;
import net.mcreator.reignmod.kingdom.KingdomSavedData;
import net.mcreator.reignmod.market.MarketSavedData;
import net.mcreator.reignmod.mines.MineSavedData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xaero.pac.common.server.api.OpenPACServerAPI;
import xaero.pac.common.server.player.config.PlayerConfig;
import xaero.pac.common.server.player.config.api.IPlayerConfigAPI;
import xaero.pac.common.server.player.config.api.IPlayerConfigManagerAPI;
import xaero.pac.common.server.player.config.api.PlayerConfigOptions;


@EventBusSubscriber
public class ReignServerEvent {

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {
        Logger LOGGER = LogManager.getLogger("ReignMod");
        MinecraftServer server = event.getServer();
        ServerLevel overworld = server.overworld();

        LOGGER.info("Mod configs are loading...");
        ConfigLoader.initialize();
        LOGGER.info("Mod configs are successfully loaded!");

        LOGGER.info("Kingdom data is loading...");
        KingdomSavedData.initialize(overworld);
        LOGGER.info("Kingdom data is successfully loaded!");

        LOGGER.info("Market data is loading...");
        MarketSavedData.initialize(overworld);
        LOGGER.info("Market data is successfully loaded!");

        LOGGER.info("House data is loading...");
        HouseSavedData.initialize(overworld);
        LOGGER.info("House data is successfully loaded!");

        LOGGER.info("Capital claim data is loading...");
        CapitalClaimSavedData.initialize(overworld);
        LOGGER.info("Capital claim data is successfully loaded!");

        LOGGER.info("Chunk claim data is loading...");
        ChunkClaimSavedData.initialize(overworld);
        LOGGER.info("Chunk claim data is successfully loaded!");

        LOGGER.info("Mine data is loading...");
        MineSavedData.initialize(overworld);
        LOGGER.info("Mine data is successfully loaded!");

        if (ModList.get().isLoaded("openpartiesandclaims")) {
            LOGGER.info("OPAC integration is loading...");
            OpenPACServerAPI.get(server).getPlayerConfigs().getDefaultConfig().tryToSet(PlayerConfigOptions.PROTECT_CLAIMED_CHUNKS, false);
            IPlayerConfigManagerAPI cfgMgr = OpenPACServerAPI.get(server).getPlayerConfigs();
            cfgMgr.getLoadedConfig(PlayerConfig.SERVER_CLAIM_UUID).tryToSet(PlayerConfigOptions.PROTECT_CLAIMED_CHUNKS, false);
            cfgMgr.getLoadedConfig(PlayerConfig.EXPIRED_CLAIM_UUID).tryToSet(PlayerConfigOptions.PROTECT_CLAIMED_CHUNKS, false);
            cfgMgr.getLoadedConfig(null).tryToSet(PlayerConfigOptions.PROTECT_CLAIMED_CHUNKS, false);
            LOGGER.info("OPAC integration is successfully loaded!");
        }
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