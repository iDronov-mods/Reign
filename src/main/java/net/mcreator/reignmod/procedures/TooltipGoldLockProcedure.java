package net.mcreator.reignmod.procedures;

import net.minecraft.network.chat.Component;

public class TooltipGoldLockProcedure {
	public static String execute() {
		return Component.translatable("translation.key.goldlock_tooltip").getString();
	}
}
