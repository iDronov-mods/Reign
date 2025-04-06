package net.mcreator.reignmod.procedures;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.mcreator.reignmod.kingdom.KingdomManager;
import net.minecraft.commands.CommandSourceStack;

public class CapitalSurgingSetProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments) {
		double value = 0;
		value = DoubleArgumentType.getDouble(arguments, "value");
		KingdomManager.setSurgingSource((int) value);
	}
}
