package net.mcreator.reignmod.procedures;

import net.minecraft.commands.CommandSourceStack;

import net.mcreator.reignmod.kingdom.KingdomData;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.DoubleArgumentType;

public class CapitalSurgingSetProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments) {
		double value = 0;
		value = DoubleArgumentType.getDouble(arguments, "value");
		KingdomData.setSourceDisturbance((int) value);
	}
}
