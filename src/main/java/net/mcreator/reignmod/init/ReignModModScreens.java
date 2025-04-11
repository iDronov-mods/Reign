
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.reignmod.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import net.mcreator.reignmod.client.gui.WoodcutterwindowScreen;
import net.mcreator.reignmod.client.gui.WalletwinScreen;
import net.mcreator.reignmod.client.gui.TraderPointScreen;
import net.mcreator.reignmod.client.gui.SoldierWindowScreen;
import net.mcreator.reignmod.client.gui.SmithWindowScreen;
import net.mcreator.reignmod.client.gui.SafeUIScreen;
import net.mcreator.reignmod.client.gui.RoyaleSettingsScreen;
import net.mcreator.reignmod.client.gui.RentalBlockUIScreen;
import net.mcreator.reignmod.client.gui.RefuseLicensesWindowScreen;
import net.mcreator.reignmod.client.gui.PrivateShopUIScreen;
import net.mcreator.reignmod.client.gui.PrivateShopBuyerUIScreen;
import net.mcreator.reignmod.client.gui.NewHouseScreen;
import net.mcreator.reignmod.client.gui.MinerWindowScreen;
import net.mcreator.reignmod.client.gui.MarketScreen;
import net.mcreator.reignmod.client.gui.LicensesWindowScreen;
import net.mcreator.reignmod.client.gui.LicenseIsSelectedScreen;
import net.mcreator.reignmod.client.gui.KingtableUIScreen;
import net.mcreator.reignmod.client.gui.HunterWindowScreen;
import net.mcreator.reignmod.client.gui.HouseIncubatorUIScreen;
import net.mcreator.reignmod.client.gui.HoarderScreen;
import net.mcreator.reignmod.client.gui.FundUIScreen;
import net.mcreator.reignmod.client.gui.FisherWindowScreen;
import net.mcreator.reignmod.client.gui.FarmerWindowScreen;
import net.mcreator.reignmod.client.gui.EnchanterWindowScreen;
import net.mcreator.reignmod.client.gui.DsfScreen;
import net.mcreator.reignmod.client.gui.DomainUIScreen;
import net.mcreator.reignmod.client.gui.CowboyWindowScreen;
import net.mcreator.reignmod.client.gui.CoffersUIScreen;
import net.mcreator.reignmod.client.gui.CoffersTaxUIScreen;
import net.mcreator.reignmod.client.gui.AlchemistWindowScreen;
import net.mcreator.reignmod.client.gui.AddLicenseListScreen;
import net.mcreator.reignmod.client.gui.AddLicenseIsSelectedScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ReignModModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(ReignModModMenus.DSF.get(), DsfScreen::new);
			MenuScreens.register(ReignModModMenus.LICENSE_IS_SELECTED.get(), LicenseIsSelectedScreen::new);
			MenuScreens.register(ReignModModMenus.TRADER_POINT.get(), TraderPointScreen::new);
			MenuScreens.register(ReignModModMenus.ROYALE_SETTINGS.get(), RoyaleSettingsScreen::new);
			MenuScreens.register(ReignModModMenus.MARKET.get(), MarketScreen::new);
			MenuScreens.register(ReignModModMenus.COFFERS_UI.get(), CoffersUIScreen::new);
			MenuScreens.register(ReignModModMenus.ADD_LICENSE_LIST.get(), AddLicenseListScreen::new);
			MenuScreens.register(ReignModModMenus.ADD_LICENSE_IS_SELECTED.get(), AddLicenseIsSelectedScreen::new);
			MenuScreens.register(ReignModModMenus.FUND_UI.get(), FundUIScreen::new);
			MenuScreens.register(ReignModModMenus.HOARDER.get(), HoarderScreen::new);
			MenuScreens.register(ReignModModMenus.WALLETWIN.get(), WalletwinScreen::new);
			MenuScreens.register(ReignModModMenus.KINGTABLE_UI.get(), KingtableUIScreen::new);
			MenuScreens.register(ReignModModMenus.NEW_HOUSE.get(), NewHouseScreen::new);
			MenuScreens.register(ReignModModMenus.SAFE_UI.get(), SafeUIScreen::new);
			MenuScreens.register(ReignModModMenus.HOUSE_INCUBATOR_UI.get(), HouseIncubatorUIScreen::new);
			MenuScreens.register(ReignModModMenus.PRIVATE_SHOP_UI.get(), PrivateShopUIScreen::new);
			MenuScreens.register(ReignModModMenus.PRIVATE_SHOP_BUYER_UI.get(), PrivateShopBuyerUIScreen::new);
			MenuScreens.register(ReignModModMenus.LICENSES_WINDOW.get(), LicensesWindowScreen::new);
			MenuScreens.register(ReignModModMenus.WOODCUTTERWINDOW.get(), WoodcutterwindowScreen::new);
			MenuScreens.register(ReignModModMenus.REFUSE_LICENSES_WINDOW.get(), RefuseLicensesWindowScreen::new);
			MenuScreens.register(ReignModModMenus.RENTAL_BLOCK_UI.get(), RentalBlockUIScreen::new);
			MenuScreens.register(ReignModModMenus.MINER_WINDOW.get(), MinerWindowScreen::new);
			MenuScreens.register(ReignModModMenus.SMITH_WINDOW.get(), SmithWindowScreen::new);
			MenuScreens.register(ReignModModMenus.FARMER_WINDOW.get(), FarmerWindowScreen::new);
			MenuScreens.register(ReignModModMenus.COWBOY_WINDOW.get(), CowboyWindowScreen::new);
			MenuScreens.register(ReignModModMenus.FISHER_WINDOW.get(), FisherWindowScreen::new);
			MenuScreens.register(ReignModModMenus.ALCHEMIST_WINDOW.get(), AlchemistWindowScreen::new);
			MenuScreens.register(ReignModModMenus.ENCHANTER_WINDOW.get(), EnchanterWindowScreen::new);
			MenuScreens.register(ReignModModMenus.SOLDIER_WINDOW.get(), SoldierWindowScreen::new);
			MenuScreens.register(ReignModModMenus.HUNTER_WINDOW.get(), HunterWindowScreen::new);
			MenuScreens.register(ReignModModMenus.DOMAIN_UI.get(), DomainUIScreen::new);
			MenuScreens.register(ReignModModMenus.COFFERS_TAX_UI.get(), CoffersTaxUIScreen::new);
		});
	}
}
