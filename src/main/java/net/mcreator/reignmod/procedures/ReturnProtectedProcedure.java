package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;

public class ReturnProtectedProcedure {
	public static boolean execute(BlockState blockstate) {
		return blockstate.getBlock().getStateDefinition().getProperty("protected") instanceof BooleanProperty _getbp1 && blockstate.getValue(_getbp1);
	}
}
