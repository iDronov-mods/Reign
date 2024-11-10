
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.reignmod.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import net.mcreator.reignmod.world.inventory.WinViewMenu;
import net.mcreator.reignmod.world.inventory.WalletwinMenu;
import net.mcreator.reignmod.world.inventory.TraderPointMenu;
import net.mcreator.reignmod.world.inventory.TextbookmainMenu;
import net.mcreator.reignmod.world.inventory.Textbook1Menu;
import net.mcreator.reignmod.world.inventory.SafeUIMenu;
import net.mcreator.reignmod.world.inventory.RoyaleSettingsMenu;
import net.mcreator.reignmod.world.inventory.NewHouseMenu;
import net.mcreator.reignmod.world.inventory.MarketMenu;
import net.mcreator.reignmod.world.inventory.LicenseIsSelectedMenu;
import net.mcreator.reignmod.world.inventory.KingtableUIMenu;
import net.mcreator.reignmod.world.inventory.HoarderMenu;
import net.mcreator.reignmod.world.inventory.FundUIMenu;
import net.mcreator.reignmod.world.inventory.DsfMenu;
import net.mcreator.reignmod.world.inventory.CoffersUIMenu;
import net.mcreator.reignmod.world.inventory.BetaTextbookMenu;
import net.mcreator.reignmod.world.inventory.BetaTextbook2Menu;
import net.mcreator.reignmod.world.inventory.AddLicenseListMenu;
import net.mcreator.reignmod.world.inventory.AddLicenseIsSelectedMenu;
import net.mcreator.reignmod.ReignModMod;

public class ReignModModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, ReignModMod.MODID);
	public static final RegistryObject<MenuType<DsfMenu>> DSF = REGISTRY.register("dsf", () -> IForgeMenuType.create(DsfMenu::new));
	public static final RegistryObject<MenuType<LicenseIsSelectedMenu>> LICENSE_IS_SELECTED = REGISTRY.register("license_is_selected", () -> IForgeMenuType.create(LicenseIsSelectedMenu::new));
	public static final RegistryObject<MenuType<WinViewMenu>> WIN_VIEW = REGISTRY.register("win_view", () -> IForgeMenuType.create(WinViewMenu::new));
	public static final RegistryObject<MenuType<TraderPointMenu>> TRADER_POINT = REGISTRY.register("trader_point", () -> IForgeMenuType.create(TraderPointMenu::new));
	public static final RegistryObject<MenuType<RoyaleSettingsMenu>> ROYALE_SETTINGS = REGISTRY.register("royale_settings", () -> IForgeMenuType.create(RoyaleSettingsMenu::new));
	public static final RegistryObject<MenuType<MarketMenu>> MARKET = REGISTRY.register("market", () -> IForgeMenuType.create(MarketMenu::new));
	public static final RegistryObject<MenuType<CoffersUIMenu>> COFFERS_UI = REGISTRY.register("coffers_ui", () -> IForgeMenuType.create(CoffersUIMenu::new));
	public static final RegistryObject<MenuType<AddLicenseListMenu>> ADD_LICENSE_LIST = REGISTRY.register("add_license_list", () -> IForgeMenuType.create(AddLicenseListMenu::new));
	public static final RegistryObject<MenuType<AddLicenseIsSelectedMenu>> ADD_LICENSE_IS_SELECTED = REGISTRY.register("add_license_is_selected", () -> IForgeMenuType.create(AddLicenseIsSelectedMenu::new));
	public static final RegistryObject<MenuType<FundUIMenu>> FUND_UI = REGISTRY.register("fund_ui", () -> IForgeMenuType.create(FundUIMenu::new));
	public static final RegistryObject<MenuType<Textbook1Menu>> TEXTBOOK_1 = REGISTRY.register("textbook_1", () -> IForgeMenuType.create(Textbook1Menu::new));
	public static final RegistryObject<MenuType<BetaTextbookMenu>> BETA_TEXTBOOK = REGISTRY.register("beta_textbook", () -> IForgeMenuType.create(BetaTextbookMenu::new));
	public static final RegistryObject<MenuType<BetaTextbook2Menu>> BETA_TEXTBOOK_2 = REGISTRY.register("beta_textbook_2", () -> IForgeMenuType.create(BetaTextbook2Menu::new));
	public static final RegistryObject<MenuType<HoarderMenu>> HOARDER = REGISTRY.register("hoarder", () -> IForgeMenuType.create(HoarderMenu::new));
	public static final RegistryObject<MenuType<WalletwinMenu>> WALLETWIN = REGISTRY.register("walletwin", () -> IForgeMenuType.create(WalletwinMenu::new));
	public static final RegistryObject<MenuType<TextbookmainMenu>> TEXTBOOKMAIN = REGISTRY.register("textbookmain", () -> IForgeMenuType.create(TextbookmainMenu::new));
	public static final RegistryObject<MenuType<KingtableUIMenu>> KINGTABLE_UI = REGISTRY.register("kingtable_ui", () -> IForgeMenuType.create(KingtableUIMenu::new));
	public static final RegistryObject<MenuType<NewHouseMenu>> NEW_HOUSE = REGISTRY.register("new_house", () -> IForgeMenuType.create(NewHouseMenu::new));
	public static final RegistryObject<MenuType<SafeUIMenu>> SAFE_UI = REGISTRY.register("safe_ui", () -> IForgeMenuType.create(SafeUIMenu::new));
}
