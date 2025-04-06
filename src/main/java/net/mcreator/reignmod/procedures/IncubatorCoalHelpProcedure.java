package net.mcreator.reignmod.procedures;

import net.minecraft.network.chat.Component;

public class IncubatorCoalHelpProcedure {
	public static String execute() {
		return Component.translatable("translation.key.incubator_help_fuel").getString() + " " + "971" + "/" + "4096";
	}
}
