package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.init.ReignModModBlocks;

public class Add3KingdomsProcedure {
	public static void execute(LevelAccessor world) {
		world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X - 5, ReignModModVariables.MapVariables.get(world).CAPITAL_Y + -1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z + 0),
				Blocks.STRIPPED_OAK_LOG.defaultBlockState(), 3);
		world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X + 0, ReignModModVariables.MapVariables.get(world).CAPITAL_Y + -1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z + 5),
				Blocks.STRIPPED_OAK_LOG.defaultBlockState(), 3);
		world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X + 0, ReignModModVariables.MapVariables.get(world).CAPITAL_Y + -1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z - 5),
				Blocks.STRIPPED_OAK_LOG.defaultBlockState(), 3);
		world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X - 5, ReignModModVariables.MapVariables.get(world).CAPITAL_Y + 0, ReignModModVariables.MapVariables.get(world).CAPITAL_Z + 0),
				Blocks.SPRUCE_FENCE.defaultBlockState(), 3);
		world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X + 0, ReignModModVariables.MapVariables.get(world).CAPITAL_Y + 0, ReignModModVariables.MapVariables.get(world).CAPITAL_Z + 5),
				Blocks.SPRUCE_FENCE.defaultBlockState(), 3);
		world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X + 0, ReignModModVariables.MapVariables.get(world).CAPITAL_Y + 0, ReignModModVariables.MapVariables.get(world).CAPITAL_Z - 5),
				Blocks.SPRUCE_FENCE.defaultBlockState(), 3);
		world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X - 5, ReignModModVariables.MapVariables.get(world).CAPITAL_Y + 1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z + 0),
				ReignModModBlocks.PLUS.get().defaultBlockState(), 3);
		world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X + 0, ReignModModVariables.MapVariables.get(world).CAPITAL_Y + 1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z + 5),
				ReignModModBlocks.PLUS.get().defaultBlockState(), 3);
		world.setBlock(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X + 0, ReignModModVariables.MapVariables.get(world).CAPITAL_Y + 1, ReignModModVariables.MapVariables.get(world).CAPITAL_Z - 5),
				ReignModModBlocks.PLUS.get().defaultBlockState(), 3);
	}
}
