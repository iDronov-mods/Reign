package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.commands.CommandSourceStack;

import net.mcreator.reignmod.network.ReignModModVariables;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.DoubleArgumentType;

public class EraSetProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments) {
		ReignModModVariables.MapVariables.get(world).ERA = DoubleArgumentType.getDouble(arguments, "value");
		ReignModModVariables.MapVariables.get(world).syncData(world);
	}
}
