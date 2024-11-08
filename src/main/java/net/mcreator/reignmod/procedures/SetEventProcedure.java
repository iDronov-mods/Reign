package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.commands.CommandSourceStack;

import net.mcreator.reignmod.network.ReignModModVariables;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.arguments.BoolArgumentType;

public class SetEventProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments) {
		if ((StringArgumentType.getString(arguments, "event")).equals("starvation")) {
			ReignModModVariables.MapVariables.get(world).EVENT_STARVATION = BoolArgumentType.getBool(arguments, "value");
			ReignModModVariables.MapVariables.get(world).syncData(world);
		} else if ((StringArgumentType.getString(arguments, "event")).equals("plague")) {
			ReignModModVariables.MapVariables.get(world).EVENT_STARVATION = BoolArgumentType.getBool(arguments, "value");
			ReignModModVariables.MapVariables.get(world).syncData(world);
		}
	}
}
