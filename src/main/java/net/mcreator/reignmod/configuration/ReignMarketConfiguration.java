package net.mcreator.reignmod.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class ReignMarketConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	public static final ForgeConfigSpec.ConfigValue<Boolean> LOGS;
	static {
		BUILDER.push("Market");
		LOGS = BUILDER.define("logs", true);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
