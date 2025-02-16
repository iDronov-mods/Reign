
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
import net.mcreator.reignmod.client.gui.WinViewScreen;
import net.mcreator.reignmod.client.gui.WalletwinScreen;
import net.mcreator.reignmod.client.gui.TraderPointScreen;
import net.mcreator.reignmod.client.gui.TextbookmainScreen;
import net.mcreator.reignmod.client.gui.Textbook1Screen;
import net.mcreator.reignmod.client.gui.SafeUIScreen;
import net.mcreator.reignmod.client.gui.RoyaleSettingsScreen;
import net.mcreator.reignmod.client.gui.RefuseLicensesWindowScreen;
import net.mcreator.reignmod.client.gui.PrivateShopUIScreen;
import net.mcreator.reignmod.client.gui.PrivateShopBuyerUIScreen;
import net.mcreator.reignmod.client.gui.NewHouseScreen;
import net.mcreator.reignmod.client.gui.MarketScreen;
import net.mcreator.reignmod.client.gui.LicensesWindowScreen;
import net.mcreator.reignmod.client.gui.LicenseIsSelectedScreen;
import net.mcreator.reignmod.client.gui.KingtableUIScreen;
import net.mcreator.reignmod.client.gui.HouseIncubatorUIScreen;
import net.mcreator.reignmod.client.gui.HoarderScreen;
import net.mcreator.reignmod.client.gui.FundUIScreen;
import net.mcreator.reignmod.client.gui.DsfScreen;
import net.mcreator.reignmod.client.gui.CoffersUIScreen;
import net.mcreator.reignmod.client.gui.BetaTextbookScreen;
import net.mcreator.reignmod.client.gui.BetaTextbook2Screen;
import net.mcreator.reignmod.client.gui.AddLicenseListScreen;
import net.mcreator.reignmod.client.gui.AddLicenseIsSelectedScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ReignModModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(ReignModModMenus.DSF.get(), DsfScreen::new);
			MenuScreens.register(ReignModModMenus.LICENSE_IS_SELECTED.get(), LicenseIsSelectedScreen::new);
			MenuScreens.register(ReignModModMenus.WIN_VIEW.get(), WinViewScreen::new);
			MenuScreens.register(ReignModModMenus.TRADER_POINT.get(), TraderPointScreen::new);
			MenuScreens.register(ReignModModMenus.ROYALE_SETTINGS.get(), RoyaleSettingsScreen::new);
			MenuScreens.register(ReignModModMenus.MARKET.get(), MarketScreen::new);
			MenuScreens.register(ReignModModMenus.COFFERS_UI.get(), CoffersUIScreen::new);
			MenuScreens.register(ReignModModMenus.ADD_LICENSE_LIST.get(), AddLicenseListScreen::new);
			MenuScreens.register(ReignModModMenus.ADD_LICENSE_IS_SELECTED.get(), AddLicenseIsSelectedScreen::new);
			MenuScreens.register(ReignModModMenus.FUND_UI.get(), FundUIScreen::new);
			MenuScreens.register(ReignModModMenus.TEXTBOOK_1.get(), Textbook1Screen::new);
			MenuScreens.register(ReignModModMenus.BETA_TEXTBOOK.get(), BetaTextbookScreen::new);
			MenuScreens.register(ReignModModMenus.BETA_TEXTBOOK_2.get(), BetaTextbook2Screen::new);
			MenuScreens.register(ReignModModMenus.HOARDER.get(), HoarderScreen::new);
			MenuScreens.register(ReignModModMenus.WALLETWIN.get(), WalletwinScreen::new);
			MenuScreens.register(ReignModModMenus.TEXTBOOKMAIN.get(), TextbookmainScreen::new);
			MenuScreens.register(ReignModModMenus.KINGTABLE_UI.get(), KingtableUIScreen::new);
			MenuScreens.register(ReignModModMenus.NEW_HOUSE.get(), NewHouseScreen::new);
			MenuScreens.register(ReignModModMenus.SAFE_UI.get(), SafeUIScreen::new);
			MenuScreens.register(ReignModModMenus.HOUSE_INCUBATOR_UI.get(), HouseIncubatorUIScreen::new);
			MenuScreens.register(ReignModModMenus.PRIVATE_SHOP_UI.get(), PrivateShopUIScreen::new);
			MenuScreens.register(ReignModModMenus.PRIVATE_SHOP_BUYER_UI.get(), PrivateShopBuyerUIScreen::new);
			MenuScreens.register(ReignModModMenus.LICENSES_WINDOW.get(), LicensesWindowScreen::new);
			MenuScreens.register(ReignModModMenus.WOODCUTTERWINDOW.get(), WoodcutterwindowScreen::new);
			MenuScreens.register(ReignModModMenus.REFUSE_LICENSES_WINDOW.get(), RefuseLicensesWindowScreen::new);
		});
	}
}
