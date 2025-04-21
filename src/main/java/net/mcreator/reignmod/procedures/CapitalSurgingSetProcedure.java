package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.commands.CommandSourceStack;

import net.mcreator.reignmod.kingdom.KingdomManager;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.DoubleArgumentType;

public class CapitalSurgingSetProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments) {
		double value = 0;
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				value = DoubleArgumentType.getDouble(arguments, "value");
				KingdomManager.setSourceDisturbance((int) value);
			}
			world = _worldorig;
		}
	}
}
