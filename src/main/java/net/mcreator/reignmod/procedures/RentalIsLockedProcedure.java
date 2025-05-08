package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

public class RentalIsLockedProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide() && world.getServer() != null)
			world.getServer().getPlayerList().broadcastSystemMessage(Component.literal((x + "" + y + "" + z)), false);
		return (world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("protected") instanceof BooleanProperty _getbp2 && (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp2);
	}
}
