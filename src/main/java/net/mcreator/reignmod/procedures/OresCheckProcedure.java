package net.mcreator.reignmod.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.level.BlockEvent;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.network.ReignModModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class OresCheckProcedure {
	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event) {
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getState(), event.getPlayer());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		execute(null, world, x, y, z, blockstate, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		if (entity == null)
			return;
		if (blockstate.getBlock() == Blocks.IRON_ORE || blockstate.getBlock() == Blocks.DEEPSLATE_IRON_ORE) {
			if (ReignModModVariables.MapVariables.get(world).ERA < 1) {
				world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
			}
		} else if (blockstate.getBlock() == Blocks.GOLD_ORE || blockstate.getBlock() == Blocks.DEEPSLATE_GOLD_ORE || blockstate.getBlock() == Blocks.DIAMOND_ORE || blockstate.getBlock() == Blocks.DEEPSLATE_DIAMOND_ORE) {
			if (ReignModModVariables.MapVariables.get(world).ERA < 4) {
				world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
			}
		} else if (blockstate.getBlock() == Blocks.REDSTONE_ORE || blockstate.getBlock() == Blocks.REDSTONE_ORE || blockstate.getBlock() == Blocks.DEEPSLATE_REDSTONE_ORE) {
			if (ReignModModVariables.MapVariables.get(world).ERA < 5) {
				world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
			}
		} else if (blockstate.getBlock() == Blocks.ANCIENT_DEBRIS && (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).license_miner
				&& (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).MAIN_LVL < 5) {
			if (ReignModModVariables.MapVariables.get(world).ERA < 8) {
				world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
			}
		}
	}
}
