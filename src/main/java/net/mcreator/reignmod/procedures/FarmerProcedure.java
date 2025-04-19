package net.mcreator.reignmod.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.level.BlockEvent;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.StemGrownBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.network.ReignModModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class FarmerProcedure {
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
		double yShift = 0;
		if (blockstate.getBlock() instanceof CropBlock || blockstate.getBlock() instanceof StemGrownBlock || blockstate.getBlock() == Blocks.SUGAR_CANE || blockstate.getBlock() == Blocks.NETHER_WART) {
			if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).license_farmer) {
				if (Mth.nextInt(RandomSource.create(), 1, 100) <= (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).efficiency) {
					if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).MAIN_LVL < 2) {
						if (Mth.nextInt(RandomSource.create(), 1, 5) != 1 && (blockstate.getBlock() == Blocks.POTATOES || blockstate.getBlock() == Blocks.CARROTS || blockstate.getBlock() == Blocks.BEETROOTS)) {
							world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
							if (entity instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal((Component.translatable("translation.key.farmer.cant_roots").getString())), true);
						}
					}
					if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).MAIN_LVL < 3) {
						if (blockstate.getBlock() instanceof StemGrownBlock) {
							world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
							if (entity instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal((Component.translatable("translation.key.farmer.cant_block_crops").getString())), true);
						} else if (blockstate.getBlock() == Blocks.SUGAR_CANE) {
							yShift = 0;
							while ((world.getBlockState(BlockPos.containing(x, y + yShift, z))).getBlock() == Blocks.SUGAR_CANE) {
								world.setBlock(BlockPos.containing(x, y + yShift, z), Blocks.AIR.defaultBlockState(), 3);
								yShift = yShift + 1;
							}
						}
					}
					if (blockstate.getBlock() == Blocks.NETHER_WART) {
						if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).MAIN_LVL < 5) {
							world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
							if (entity instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal((Component.translatable("translation.key.farmer.cant_wart").getString())), true);
						}
					}
					if (Mth.nextInt(RandomSource.create(), 1, 4) == 1 && blockstate.is(BlockTags.create(new ResourceLocation("minecraft:crops")))
							&& (blockstate.getBlock().getStateDefinition().getProperty("age") instanceof IntegerProperty _getip32 ? blockstate.getValue(_getip32) : -1) == 7) {
						if (entity instanceof Player _player)
							_player.giveExperiencePoints(1);
					}
				} else {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
				}
			} else {
				if (blockstate.getBlock() == Blocks.SUGAR_CANE) {
					yShift = 0;
					while ((world.getBlockState(BlockPos.containing(x, y + yShift, z))).getBlock() == Blocks.SUGAR_CANE) {
						world.setBlock(BlockPos.containing(x, y + yShift, z), Blocks.AIR.defaultBlockState(), 3);
						yShift = yShift + 1;
					}
				} else if (blockstate.getBlock() instanceof StemGrownBlock) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
				}
			}
		}
	}
}
