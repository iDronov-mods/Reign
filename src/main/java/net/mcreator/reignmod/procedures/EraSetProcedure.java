package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.commands.CommandSourceStack;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.kingdom.KingdomData;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.DoubleArgumentType;

public class EraSetProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments) {
		double x = 0;
		x = DoubleArgumentType.getDouble(arguments, "value");
		ReignModModVariables.MapVariables.get(world).ERA = x;
		ReignModModVariables.MapVariables.get(world).syncData(world);
		KingdomData.setCapitalEra((int) x);
	}
}
