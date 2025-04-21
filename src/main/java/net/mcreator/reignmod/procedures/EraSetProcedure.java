package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.commands.CommandSourceStack;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.kingdom.KingdomManager;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.DoubleArgumentType;

public class EraSetProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments) {
		double x = 0;
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				x = DoubleArgumentType.getDouble(arguments, "value");
				ReignModModVariables.MapVariables.get(world).ERA = x;
				ReignModModVariables.MapVariables.get(world).syncData(world);
				KingdomManager.setCapitalEra((int) x);
			}
			world = _worldorig;
		}
	}
}
