package net.mcreator.reignmod.network;

import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.client.Minecraft;

import net.mcreator.reignmod.ReignModMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ReignModModVariables {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		ReignModMod.addNetworkMessage(SavedDataSyncMessage.class, SavedDataSyncMessage::buffer, SavedDataSyncMessage::new, SavedDataSyncMessage::handler);
		ReignModMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handler);
	}

	@SubscribeEvent
	public static void init(RegisterCapabilitiesEvent event) {
		event.register(PlayerVariables.class);
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			event.getOriginal().revive();
			PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			clone.license_woodcuter = original.license_woodcuter;
			clone.license_smith = original.license_smith;
			clone.license_fisher = original.license_fisher;
			clone.license_miner = original.license_miner;
			clone.license_farmer = original.license_farmer;
			clone.license_soldier = original.license_soldier;
			clone.license_alchemist = original.license_alchemist;
			clone.license_librarian = original.license_librarian;
			clone.taxInTransaction = original.taxInTransaction;
			clone.MAIN_LVL = original.MAIN_LVL;
			clone.ADD_LVL = original.ADD_LVL;
			clone.last_refuse_day = original.last_refuse_day;
			clone.isCriminal = original.isCriminal;
			clone.FirstEnter = original.FirstEnter;
			clone.LastEnter_Day = original.LastEnter_Day;
			clone.DaysOnline = original.DaysOnline;
			clone.license_cowboy = original.license_cowboy;
			clone.license_hunter = original.license_hunter;
			clone.house = original.house;
			clone.player_prefix = original.player_prefix;
			clone.R_LVL = original.R_LVL;
			clone.LastEnter_Week = original.LastEnter_Week;
			clone.efficiency = original.efficiency;
			clone.last_refuse_week = original.last_refuse_week;
			clone.prison_house = original.prison_house;
			clone.market_goods_count = original.market_goods_count;
			if (!event.isWasDeath()) {
			}
		}

		@SubscribeEvent
		public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				SavedData mapdata = MapVariables.get(event.getEntity().level());
				SavedData worlddata = WorldVariables.get(event.getEntity().level());
				if (mapdata != null)
					ReignModMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncMessage(0, mapdata));
				if (worlddata != null)
					ReignModMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncMessage(1, worlddata));
			}
		}

		@SubscribeEvent
		public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				SavedData worlddata = WorldVariables.get(event.getEntity().level());
				if (worlddata != null)
					ReignModMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncMessage(1, worlddata));
			}
		}
	}

	public static class WorldVariables extends SavedData {
		public static final String DATA_NAME = "reign_mod_worldvars";

		public static WorldVariables load(CompoundTag tag) {
			WorldVariables data = new WorldVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
		}

		@Override
		public CompoundTag save(CompoundTag nbt) {
			return nbt;
		}

		public void syncData(LevelAccessor world) {
			this.setDirty();
			if (world instanceof Level level && !level.isClientSide())
				ReignModMod.PACKET_HANDLER.send(PacketDistributor.DIMENSION.with(level::dimension), new SavedDataSyncMessage(1, this));
		}

		static WorldVariables clientSide = new WorldVariables();

		public static WorldVariables get(LevelAccessor world) {
			if (world instanceof ServerLevel level) {
				return level.getDataStorage().computeIfAbsent(e -> WorldVariables.load(e), WorldVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class MapVariables extends SavedData {
		public static final String DATA_NAME = "reign_mod_mapvars";
		public double logs_price = 0.0;
		public double coal_price = 0;
		public double food_price = 0;
		public double armor_price = 0;
		public double tools_price = 0;
		public double other_price = 0;
		public double ores_price = 0;
		public double ERA = 0.0;
		public boolean CapitalHave = false;
		public double CAPITAL_X = 0;
		public double CAPITAL_Y = 0;
		public double CAPITAL_Z = 0;
		public double crown_id = 0;
		public boolean EVENT_PLAGUE = false;
		public boolean EVENT_STARVATION = false;
		public double Tutorial_pass = 0.0;
		public double MAX_AMOUNT_VALUE = 279616.0;
		public double FEED_K = 1.0;
		public double market_copper = 0;
		public double market_copper_all = 0;
		public double SurgingSource = 0.0;
		public double exp_price = 0;
		public double private_price = 0;
		public boolean coinage_block = false;
		public boolean needRefresh = false;
		public double dpk = 0;

		public static MapVariables load(CompoundTag tag) {
			MapVariables data = new MapVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
			logs_price = nbt.getDouble("logs_price");
			coal_price = nbt.getDouble("coal_price");
			food_price = nbt.getDouble("food_price");
			armor_price = nbt.getDouble("armor_price");
			tools_price = nbt.getDouble("tools_price");
			other_price = nbt.getDouble("other_price");
			ores_price = nbt.getDouble("ores_price");
			ERA = nbt.getDouble("ERA");
			CapitalHave = nbt.getBoolean("CapitalHave");
			CAPITAL_X = nbt.getDouble("CAPITAL_X");
			CAPITAL_Y = nbt.getDouble("CAPITAL_Y");
			CAPITAL_Z = nbt.getDouble("CAPITAL_Z");
			crown_id = nbt.getDouble("crown_id");
			EVENT_PLAGUE = nbt.getBoolean("EVENT_PLAGUE");
			EVENT_STARVATION = nbt.getBoolean("EVENT_STARVATION");
			Tutorial_pass = nbt.getDouble("Tutorial_pass");
			MAX_AMOUNT_VALUE = nbt.getDouble("MAX_AMOUNT_VALUE");
			FEED_K = nbt.getDouble("FEED_K");
			market_copper = nbt.getDouble("market_copper");
			market_copper_all = nbt.getDouble("market_copper_all");
			SurgingSource = nbt.getDouble("SurgingSource");
			exp_price = nbt.getDouble("exp_price");
			private_price = nbt.getDouble("private_price");
			coinage_block = nbt.getBoolean("coinage_block");
			needRefresh = nbt.getBoolean("needRefresh");
			dpk = nbt.getDouble("dpk");
		}

		@Override
		public CompoundTag save(CompoundTag nbt) {
			nbt.putDouble("logs_price", logs_price);
			nbt.putDouble("coal_price", coal_price);
			nbt.putDouble("food_price", food_price);
			nbt.putDouble("armor_price", armor_price);
			nbt.putDouble("tools_price", tools_price);
			nbt.putDouble("other_price", other_price);
			nbt.putDouble("ores_price", ores_price);
			nbt.putDouble("ERA", ERA);
			nbt.putBoolean("CapitalHave", CapitalHave);
			nbt.putDouble("CAPITAL_X", CAPITAL_X);
			nbt.putDouble("CAPITAL_Y", CAPITAL_Y);
			nbt.putDouble("CAPITAL_Z", CAPITAL_Z);
			nbt.putDouble("crown_id", crown_id);
			nbt.putBoolean("EVENT_PLAGUE", EVENT_PLAGUE);
			nbt.putBoolean("EVENT_STARVATION", EVENT_STARVATION);
			nbt.putDouble("Tutorial_pass", Tutorial_pass);
			nbt.putDouble("MAX_AMOUNT_VALUE", MAX_AMOUNT_VALUE);
			nbt.putDouble("FEED_K", FEED_K);
			nbt.putDouble("market_copper", market_copper);
			nbt.putDouble("market_copper_all", market_copper_all);
			nbt.putDouble("SurgingSource", SurgingSource);
			nbt.putDouble("exp_price", exp_price);
			nbt.putDouble("private_price", private_price);
			nbt.putBoolean("coinage_block", coinage_block);
			nbt.putBoolean("needRefresh", needRefresh);
			nbt.putDouble("dpk", dpk);
			return nbt;
		}

		public void syncData(LevelAccessor world) {
			this.setDirty();
			if (world instanceof Level && !world.isClientSide())
				ReignModMod.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new SavedDataSyncMessage(0, this));
		}

		static MapVariables clientSide = new MapVariables();

		public static MapVariables get(LevelAccessor world) {
			if (world instanceof ServerLevelAccessor serverLevelAcc) {
				return serverLevelAcc.getLevel().getServer().getLevel(Level.OVERWORLD).getDataStorage().computeIfAbsent(e -> MapVariables.load(e), MapVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class SavedDataSyncMessage {
		private final int type;
		private SavedData data;

		public SavedDataSyncMessage(FriendlyByteBuf buffer) {
			this.type = buffer.readInt();
			CompoundTag nbt = buffer.readNbt();
			if (nbt != null) {
				this.data = this.type == 0 ? new MapVariables() : new WorldVariables();
				if (this.data instanceof MapVariables mapVariables)
					mapVariables.read(nbt);
				else if (this.data instanceof WorldVariables worldVariables)
					worldVariables.read(nbt);
			}
		}

		public SavedDataSyncMessage(int type, SavedData data) {
			this.type = type;
			this.data = data;
		}

		public static void buffer(SavedDataSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeInt(message.type);
			if (message.data != null)
				buffer.writeNbt(message.data.save(new CompoundTag()));
		}

		public static void handler(SavedDataSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer() && message.data != null) {
					if (message.type == 0)
						MapVariables.clientSide = (MapVariables) message.data;
					else
						WorldVariables.clientSide = (WorldVariables) message.data;
				}
			});
			context.setPacketHandled(true);
		}
	}

	public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
	});

	@Mod.EventBusSubscriber
	private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {
		@SubscribeEvent
		public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
				event.addCapability(new ResourceLocation("reign_mod", "player_variables"), new PlayerVariablesProvider());
		}

		private final PlayerVariables playerVariables = new PlayerVariables();
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public Tag serializeNBT() {
			return playerVariables.writeNBT();
		}

		@Override
		public void deserializeNBT(Tag nbt) {
			playerVariables.readNBT(nbt);
		}
	}

	public static class PlayerVariables {
		public boolean license_woodcuter = false;
		public boolean license_smith = false;
		public boolean license_fisher = false;
		public boolean license_miner = false;
		public boolean license_farmer = false;
		public boolean license_soldier = false;
		public boolean license_alchemist = false;
		public boolean license_librarian = false;
		public double taxInTransaction = 0;
		public double MAIN_LVL = 0;
		public double ADD_LVL = 0;
		public double last_refuse_day = 0;
		public boolean isCriminal = false;
		public boolean FirstEnter = false;
		public double LastEnter_Day = 0;
		public double DaysOnline = 0;
		public boolean license_cowboy = false;
		public boolean license_hunter = false;
		public String house = "";
		public String player_prefix = "";
		public boolean R_LVL = false;
		public double LastEnter_Week = 0;
		public double efficiency = 0.0;
		public double last_refuse_week = 0;
		public String prison_house = "";
		public String market_goods_count = "\"\"";

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				ReignModMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerVariablesSyncMessage(this));
		}

		public Tag writeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putBoolean("license_woodcuter", license_woodcuter);
			nbt.putBoolean("license_smith", license_smith);
			nbt.putBoolean("license_fisher", license_fisher);
			nbt.putBoolean("license_miner", license_miner);
			nbt.putBoolean("license_farmer", license_farmer);
			nbt.putBoolean("license_soldier", license_soldier);
			nbt.putBoolean("license_alchemist", license_alchemist);
			nbt.putBoolean("license_librarian", license_librarian);
			nbt.putDouble("taxInTransaction", taxInTransaction);
			nbt.putDouble("MAIN_LVL", MAIN_LVL);
			nbt.putDouble("ADD_LVL", ADD_LVL);
			nbt.putDouble("last_refuse_day", last_refuse_day);
			nbt.putBoolean("isCriminal", isCriminal);
			nbt.putBoolean("FirstEnter", FirstEnter);
			nbt.putDouble("LastEnter_Day", LastEnter_Day);
			nbt.putDouble("DaysOnline", DaysOnline);
			nbt.putBoolean("license_cowboy", license_cowboy);
			nbt.putBoolean("license_hunter", license_hunter);
			nbt.putString("house", house);
			nbt.putString("player_prefix", player_prefix);
			nbt.putBoolean("R_LVL", R_LVL);
			nbt.putDouble("LastEnter_Week", LastEnter_Week);
			nbt.putDouble("efficiency", efficiency);
			nbt.putDouble("last_refuse_week", last_refuse_week);
			nbt.putString("prison_house", prison_house);
			nbt.putString("market_goods_count", market_goods_count);
			return nbt;
		}

		public void readNBT(Tag tag) {
			CompoundTag nbt = (CompoundTag) tag;
			license_woodcuter = nbt.getBoolean("license_woodcuter");
			license_smith = nbt.getBoolean("license_smith");
			license_fisher = nbt.getBoolean("license_fisher");
			license_miner = nbt.getBoolean("license_miner");
			license_farmer = nbt.getBoolean("license_farmer");
			license_soldier = nbt.getBoolean("license_soldier");
			license_alchemist = nbt.getBoolean("license_alchemist");
			license_librarian = nbt.getBoolean("license_librarian");
			taxInTransaction = nbt.getDouble("taxInTransaction");
			MAIN_LVL = nbt.getDouble("MAIN_LVL");
			ADD_LVL = nbt.getDouble("ADD_LVL");
			last_refuse_day = nbt.getDouble("last_refuse_day");
			isCriminal = nbt.getBoolean("isCriminal");
			FirstEnter = nbt.getBoolean("FirstEnter");
			LastEnter_Day = nbt.getDouble("LastEnter_Day");
			DaysOnline = nbt.getDouble("DaysOnline");
			license_cowboy = nbt.getBoolean("license_cowboy");
			license_hunter = nbt.getBoolean("license_hunter");
			house = nbt.getString("house");
			player_prefix = nbt.getString("player_prefix");
			R_LVL = nbt.getBoolean("R_LVL");
			LastEnter_Week = nbt.getDouble("LastEnter_Week");
			efficiency = nbt.getDouble("efficiency");
			last_refuse_week = nbt.getDouble("last_refuse_week");
			prison_house = nbt.getString("prison_house");
			market_goods_count = nbt.getString("market_goods_count");
		}
	}

	public static class PlayerVariablesSyncMessage {
		private final PlayerVariables data;

		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this.data = new PlayerVariables();
			this.data.readNBT(buffer.readNbt());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeNbt((CompoundTag) message.data.writeNBT());
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
					variables.license_woodcuter = message.data.license_woodcuter;
					variables.license_smith = message.data.license_smith;
					variables.license_fisher = message.data.license_fisher;
					variables.license_miner = message.data.license_miner;
					variables.license_farmer = message.data.license_farmer;
					variables.license_soldier = message.data.license_soldier;
					variables.license_alchemist = message.data.license_alchemist;
					variables.license_librarian = message.data.license_librarian;
					variables.taxInTransaction = message.data.taxInTransaction;
					variables.MAIN_LVL = message.data.MAIN_LVL;
					variables.ADD_LVL = message.data.ADD_LVL;
					variables.last_refuse_day = message.data.last_refuse_day;
					variables.isCriminal = message.data.isCriminal;
					variables.FirstEnter = message.data.FirstEnter;
					variables.LastEnter_Day = message.data.LastEnter_Day;
					variables.DaysOnline = message.data.DaysOnline;
					variables.license_cowboy = message.data.license_cowboy;
					variables.license_hunter = message.data.license_hunter;
					variables.house = message.data.house;
					variables.player_prefix = message.data.player_prefix;
					variables.R_LVL = message.data.R_LVL;
					variables.LastEnter_Week = message.data.LastEnter_Week;
					variables.efficiency = message.data.efficiency;
					variables.last_refuse_week = message.data.last_refuse_week;
					variables.prison_house = message.data.prison_house;
					variables.market_goods_count = message.data.market_goods_count;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
