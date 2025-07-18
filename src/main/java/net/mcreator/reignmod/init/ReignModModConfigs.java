package net.mcreator.reignmod.init;

import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.mcreator.reignmod.configuration.ReignMarketConfiguration;
import net.mcreator.reignmod.configuration.ReignCommonConfiguration;
import net.mcreator.reignmod.ReignModMod;

@Mod.EventBusSubscriber(modid = ReignModMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ReignModModConfigs {
	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		event.enqueueWork(() -> {
			ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ReignCommonConfiguration.SPEC, "reign_common.toml");
			ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ReignMarketConfiguration.SPEC, "reign_market.toml");
		});
	}
}
