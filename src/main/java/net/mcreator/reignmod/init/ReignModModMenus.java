
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.reignmod.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;

import net.mcreator.reignmod.world.inventory.WoodcutterwindowMenu;
import net.mcreator.reignmod.world.inventory.WalletwinMenu;
import net.mcreator.reignmod.world.inventory.TraderPointMenu;
import net.mcreator.reignmod.world.inventory.StrategyBlockUIMenu;
import net.mcreator.reignmod.world.inventory.StorageBarrelUIMenu;
import net.mcreator.reignmod.world.inventory.SoldierWindowMenu;
import net.mcreator.reignmod.world.inventory.SmithWindowMenu;
import net.mcreator.reignmod.world.inventory.SafeUIMenu;
import net.mcreator.reignmod.world.inventory.RentalBlockUIMenu;
import net.mcreator.reignmod.world.inventory.RefuseLicensesWindowMenu;
import net.mcreator.reignmod.world.inventory.PrivateShopUIMenu;
import net.mcreator.reignmod.world.inventory.PrivateShopBuyerUIMenu;
import net.mcreator.reignmod.world.inventory.PlayerSearchUIMenu;
import net.mcreator.reignmod.world.inventory.NewHouseMenu;
import net.mcreator.reignmod.world.inventory.MinerWindowMenu;
import net.mcreator.reignmod.world.inventory.MarketMenu;
import net.mcreator.reignmod.world.inventory.LicensesWindowMenu;
import net.mcreator.reignmod.world.inventory.LicenseIsSelectedMenu;
import net.mcreator.reignmod.world.inventory.KingtableUIMenu;
import net.mcreator.reignmod.world.inventory.HunterWindowMenu;
import net.mcreator.reignmod.world.inventory.HouseSuspectsUIMenu;
import net.mcreator.reignmod.world.inventory.HouseIncubatorUIMenu;
import net.mcreator.reignmod.world.inventory.HoarderMenu;
import net.mcreator.reignmod.world.inventory.FundUIMenu;
import net.mcreator.reignmod.world.inventory.FisherWindowMenu;
import net.mcreator.reignmod.world.inventory.FarmerWindowMenu;
import net.mcreator.reignmod.world.inventory.EnchanterWindowMenu;
import net.mcreator.reignmod.world.inventory.DomainUIMenu;
import net.mcreator.reignmod.world.inventory.CowboyWindowMenu;
import net.mcreator.reignmod.world.inventory.CoffersUIMenu;
import net.mcreator.reignmod.world.inventory.CoffersTaxUIMenu;
import net.mcreator.reignmod.world.inventory.AlchemistWindowMenu;
import net.mcreator.reignmod.world.inventory.AddLicenseListMenu;
import net.mcreator.reignmod.world.inventory.AddLicenseIsSelectedMenu;
import net.mcreator.reignmod.ReignModMod;

import javax.annotation.Nullable;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ReignModModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, ReignModMod.MODID);
	public static final RegistryObject<MenuType<LicenseIsSelectedMenu>> LICENSE_IS_SELECTED = REGISTRY.register("license_is_selected", () -> IForgeMenuType.create(LicenseIsSelectedMenu::new));
	public static final RegistryObject<MenuType<TraderPointMenu>> TRADER_POINT = REGISTRY.register("trader_point", () -> IForgeMenuType.create(TraderPointMenu::new));
	public static final RegistryObject<MenuType<MarketMenu>> MARKET = REGISTRY.register("market", () -> IForgeMenuType.create(MarketMenu::new));
	public static final RegistryObject<MenuType<CoffersUIMenu>> COFFERS_UI = REGISTRY.register("coffers_ui", () -> IForgeMenuType.create(CoffersUIMenu::new));
	public static final RegistryObject<MenuType<AddLicenseListMenu>> ADD_LICENSE_LIST = REGISTRY.register("add_license_list", () -> IForgeMenuType.create(AddLicenseListMenu::new));
	public static final RegistryObject<MenuType<AddLicenseIsSelectedMenu>> ADD_LICENSE_IS_SELECTED = REGISTRY.register("add_license_is_selected", () -> IForgeMenuType.create(AddLicenseIsSelectedMenu::new));
	public static final RegistryObject<MenuType<FundUIMenu>> FUND_UI = REGISTRY.register("fund_ui", () -> IForgeMenuType.create(FundUIMenu::new));
	public static final RegistryObject<MenuType<HoarderMenu>> HOARDER = REGISTRY.register("hoarder", () -> IForgeMenuType.create(HoarderMenu::new));
	public static final RegistryObject<MenuType<WalletwinMenu>> WALLETWIN = REGISTRY.register("walletwin", () -> IForgeMenuType.create(WalletwinMenu::new));
	public static final RegistryObject<MenuType<KingtableUIMenu>> KINGTABLE_UI = REGISTRY.register("kingtable_ui", () -> IForgeMenuType.create(KingtableUIMenu::new));
	public static final RegistryObject<MenuType<NewHouseMenu>> NEW_HOUSE = REGISTRY.register("new_house", () -> IForgeMenuType.create(NewHouseMenu::new));
	public static final RegistryObject<MenuType<SafeUIMenu>> SAFE_UI = REGISTRY.register("safe_ui", () -> IForgeMenuType.create(SafeUIMenu::new));
	public static final RegistryObject<MenuType<HouseIncubatorUIMenu>> HOUSE_INCUBATOR_UI = REGISTRY.register("house_incubator_ui", () -> IForgeMenuType.create(HouseIncubatorUIMenu::new));
	public static final RegistryObject<MenuType<PrivateShopUIMenu>> PRIVATE_SHOP_UI = REGISTRY.register("private_shop_ui", () -> IForgeMenuType.create(PrivateShopUIMenu::new));
	public static final RegistryObject<MenuType<PrivateShopBuyerUIMenu>> PRIVATE_SHOP_BUYER_UI = REGISTRY.register("private_shop_buyer_ui", () -> IForgeMenuType.create(PrivateShopBuyerUIMenu::new));
	public static final RegistryObject<MenuType<LicensesWindowMenu>> LICENSES_WINDOW = REGISTRY.register("licenses_window", () -> IForgeMenuType.create(LicensesWindowMenu::new));
	public static final RegistryObject<MenuType<WoodcutterwindowMenu>> WOODCUTTERWINDOW = REGISTRY.register("woodcutterwindow", () -> IForgeMenuType.create(WoodcutterwindowMenu::new));
	public static final RegistryObject<MenuType<RefuseLicensesWindowMenu>> REFUSE_LICENSES_WINDOW = REGISTRY.register("refuse_licenses_window", () -> IForgeMenuType.create(RefuseLicensesWindowMenu::new));
	public static final RegistryObject<MenuType<RentalBlockUIMenu>> RENTAL_BLOCK_UI = REGISTRY.register("rental_block_ui", () -> IForgeMenuType.create(RentalBlockUIMenu::new));
	public static final RegistryObject<MenuType<MinerWindowMenu>> MINER_WINDOW = REGISTRY.register("miner_window", () -> IForgeMenuType.create(MinerWindowMenu::new));
	public static final RegistryObject<MenuType<SmithWindowMenu>> SMITH_WINDOW = REGISTRY.register("smith_window", () -> IForgeMenuType.create(SmithWindowMenu::new));
	public static final RegistryObject<MenuType<FarmerWindowMenu>> FARMER_WINDOW = REGISTRY.register("farmer_window", () -> IForgeMenuType.create(FarmerWindowMenu::new));
	public static final RegistryObject<MenuType<CowboyWindowMenu>> COWBOY_WINDOW = REGISTRY.register("cowboy_window", () -> IForgeMenuType.create(CowboyWindowMenu::new));
	public static final RegistryObject<MenuType<FisherWindowMenu>> FISHER_WINDOW = REGISTRY.register("fisher_window", () -> IForgeMenuType.create(FisherWindowMenu::new));
	public static final RegistryObject<MenuType<AlchemistWindowMenu>> ALCHEMIST_WINDOW = REGISTRY.register("alchemist_window", () -> IForgeMenuType.create(AlchemistWindowMenu::new));
	public static final RegistryObject<MenuType<EnchanterWindowMenu>> ENCHANTER_WINDOW = REGISTRY.register("enchanter_window", () -> IForgeMenuType.create(EnchanterWindowMenu::new));
	public static final RegistryObject<MenuType<SoldierWindowMenu>> SOLDIER_WINDOW = REGISTRY.register("soldier_window", () -> IForgeMenuType.create(SoldierWindowMenu::new));
	public static final RegistryObject<MenuType<HunterWindowMenu>> HUNTER_WINDOW = REGISTRY.register("hunter_window", () -> IForgeMenuType.create(HunterWindowMenu::new));
	public static final RegistryObject<MenuType<DomainUIMenu>> DOMAIN_UI = REGISTRY.register("domain_ui", () -> IForgeMenuType.create(DomainUIMenu::new));
	public static final RegistryObject<MenuType<CoffersTaxUIMenu>> COFFERS_TAX_UI = REGISTRY.register("coffers_tax_ui", () -> IForgeMenuType.create(CoffersTaxUIMenu::new));
	public static final RegistryObject<MenuType<HouseSuspectsUIMenu>> HOUSE_SUSPECTS_UI = REGISTRY.register("house_suspects_ui", () -> IForgeMenuType.create(HouseSuspectsUIMenu::new));
	public static final RegistryObject<MenuType<PlayerSearchUIMenu>> PLAYER_SEARCH_UI = REGISTRY.register("player_search_ui", () -> IForgeMenuType.create(PlayerSearchUIMenu::new));
	public static final RegistryObject<MenuType<StorageBarrelUIMenu>> STORAGE_BARREL_UI = REGISTRY.register("storage_barrel_ui", () -> IForgeMenuType.create(StorageBarrelUIMenu::new));
	public static final RegistryObject<MenuType<StrategyBlockUIMenu>> STRATEGY_BLOCK_UI = REGISTRY.register("strategy_block_ui", () -> IForgeMenuType.create(StrategyBlockUIMenu::new));

	public static void setText(String boxname, String value, @Nullable ServerPlayer player) {
		if (player != null) {
			ReignModMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new GuiSyncMessage(boxname, value));
		} else {
			ReignModMod.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new GuiSyncMessage(boxname, value));
		}
	}

	public static class GuiSyncMessage {
		private final String textboxid;
		private final String data;

		public GuiSyncMessage(FriendlyByteBuf buffer) {
			this.textboxid = buffer.readComponent().getString();
			this.data = buffer.readComponent().getString();
		}

		public GuiSyncMessage(String textboxid, String data) {
			this.textboxid = textboxid;
			this.data = data;
		}

		public static void buffer(GuiSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeComponent(Component.literal(message.textboxid));
			buffer.writeComponent(Component.literal(message.data));
		}

		public static void handleData(GuiSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					ReignModModScreens.handleTextBoxMessage(message);
				}
			});
			context.setPacketHandled(true);
		}

		String editbox() {
			return this.textboxid;
		}

		String value() {
			return this.data;
		}
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		ReignModMod.addNetworkMessage(GuiSyncMessage.class, GuiSyncMessage::buffer, GuiSyncMessage::new, GuiSyncMessage::handleData);
	}
}
