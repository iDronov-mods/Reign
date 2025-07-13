package net.mcreator.reignmod;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.util.thread.SidedThreadGroups;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;

import net.mcreator.reignmod.networking.ReignNetworking;
import net.mcreator.reignmod.init.ReignModModTabs;
import net.mcreator.reignmod.init.ReignModModSounds;
import net.mcreator.reignmod.init.ReignModModParticleTypes;
import net.mcreator.reignmod.init.ReignModModMobEffects;
import net.mcreator.reignmod.init.ReignModModMenus;
import net.mcreator.reignmod.init.ReignModModItems;
import net.mcreator.reignmod.init.ReignModModBlocks;
import net.mcreator.reignmod.init.ReignModModBlockEntities;
import net.mcreator.reignmod.claim.chunk.ChunkClaimProtectionHandler;
import net.mcreator.reignmod.claim.capital.CapitalClaimProtectionHandler;
import net.mcreator.reignmod.basics.ReignServerEvent;
import net.mcreator.reignmod.basics.ReignChatEventSubscriber;

import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.BiConsumer;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.AbstractMap;

@Mod("reign_mod")
public class ReignModMod {
	public static final Logger LOGGER = LogManager.getLogger(ReignModMod.class);
	public static final String MODID = "reign_mod";

	public ReignModMod() {
		// Start of user code block mod constructor
		// End of user code block mod constructor
		MinecraftForge.EVENT_BUS.register(this);
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ReignModModSounds.REGISTRY.register(bus);
		ReignModModBlocks.REGISTRY.register(bus);
		ReignModModBlockEntities.REGISTRY.register(bus);
		ReignModModItems.REGISTRY.register(bus);
		ReignModModTabs.REGISTRY.register(bus);
		ReignModModMobEffects.REGISTRY.register(bus);
		ReignModModParticleTypes.REGISTRY.register(bus);
		ReignModModMenus.REGISTRY.register(bus);
		// Start of user code block mod init
		bus.addListener(this::commonSetup);
		bus.addListener(this::loadComplete);
		// End of user code block mod init
	}

	// Start of user code block mod methods
	private void commonSetup(final FMLCommonSetupEvent event) {
		ReignNetworking.register();
	}

	private void loadComplete(FMLLoadCompleteEvent event) {
		MinecraftForge.EVENT_BUS.register(new ReignServerEvent());
		MinecraftForge.EVENT_BUS.register(new CapitalClaimProtectionHandler());
		MinecraftForge.EVENT_BUS.register(new ChunkClaimProtectionHandler());
		if (FMLEnvironment.dist.equals(Dist.CLIENT)) {
			MinecraftForge.EVENT_BUS.register(new ReignChatEventSubscriber());
		}
	}

	// End of user code block mod methods
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, MODID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	private static int messageID = 0;

	public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
		PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
		messageID++;
	}

	private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

	public static void queueServerWork(int tick, Runnable action) {
		if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER)
			workQueue.add(new AbstractMap.SimpleEntry<>(action, tick));
	}

	@SubscribeEvent
	public void tick(TickEvent.ServerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
			workQueue.forEach(work -> {
				work.setValue(work.getValue() - 1);
				if (work.getValue() == 0)
					actions.add(work);
			});
			actions.forEach(e -> e.getKey().run());
			workQueue.removeAll(actions);
		}
	}
}
