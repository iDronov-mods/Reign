package net.mcreator.reignmod.procedures;

import net.minecraft.network.chat.Component;

public class TooltipCooperLockProcedure {
	public static String execute() {
		return Component.translatable("translation.key.cooperlock_tooltip").getString();
	}
}
