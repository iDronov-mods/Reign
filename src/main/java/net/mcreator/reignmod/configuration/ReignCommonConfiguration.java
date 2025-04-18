package net.mcreator.reignmod.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class ReignCommonConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	public static final ForgeConfigSpec.ConfigValue<Boolean> DISABLE_VANILLA_VILLAGERS;
	public static final ForgeConfigSpec.ConfigValue<Boolean> DISABLE_MARKET_TAX;
	public static final ForgeConfigSpec.ConfigValue<Boolean> DISABLE_HOUSE_FEEDING;
	public static final ForgeConfigSpec.ConfigValue<Boolean> DISABLE_DAILY_PAYOUTS;
	public static final ForgeConfigSpec.ConfigValue<Boolean> DISABLE_CAPITAL_FEEDING;
	public static final ForgeConfigSpec.ConfigValue<Double> HOURLY_MEAL_PERIOD;
	static {
		BUILDER.push("World");
		DISABLE_VANILLA_VILLAGERS = BUILDER.define("disable_vanilla_villagers", true);
		DISABLE_MARKET_TAX = BUILDER.define("disable_market_tax", false);
		DISABLE_HOUSE_FEEDING = BUILDER.define("disable_house_feeding", false);
		DISABLE_DAILY_PAYOUTS = BUILDER.define("disable_daily_payouts", false);
		DISABLE_CAPITAL_FEEDING = BUILDER.define("disable_capital_feeding", false);
		HOURLY_MEAL_PERIOD = BUILDER.comment("Frequency of feeding Houses and capital, in hours. (Default: 1)").define("hourly_meal_period", (double) 1);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
