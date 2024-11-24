package net.mcreator.reignmod.procedures;

import net.minecraft.network.chat.Component;

public class TradeLicenseTooltipProcedure {
	public static String execute() {
		double typo = 0;
		String source = "";
		source = Component.translatable("translation.key.trade_license_tooltip").getString();
		return source;
	}
}
