package net.mcreator.reignmod.procedures;

import net.minecraft.client.gui.screens.Screen;

public class TooltipRentalBlockProcedure {
	public static String execute() {
		if (Screen.hasShiftDown()) {
			return ".!.";
		}
		return "";
	}
}
