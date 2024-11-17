package net.mcreator.reignmod.procedures;

import net.minecraft.network.chat.Component;

public class TooltipIronLockProcedure {
	public static String execute() {
		return Component.translatable("translation.key.ironlock_tooltip").getString();
	}
}
