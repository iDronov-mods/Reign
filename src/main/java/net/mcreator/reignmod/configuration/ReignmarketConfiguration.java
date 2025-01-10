package net.mcreator.reignmod.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class ReignmarketConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	public static final ForgeConfigSpec.ConfigValue<Double> OAK_LOG;
	static {
		BUILDER.push("MARKET PRICES");
		OAK_LOG = BUILDER.define("oak log", (double) 3);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
