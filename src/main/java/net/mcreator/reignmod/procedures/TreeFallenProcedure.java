package net.mcreator.reignmod.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.level.BlockEvent;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.ReignModMod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class TreeFallenProcedure {
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
		BlockState leaves = Blocks.AIR.defaultBlockState();
		if (blockstate.is(BlockTags.create(new ResourceLocation("minecraft:logs"))) && !blockstate.is(BlockTags.create(new ResourceLocation("minecraft:s_logs")))) {
			if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).license_woodcuter && entity.isShiftKeyDown()
					&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("minecraft:axes")))
					&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.WOODEN_AXE)) {
				if (blockstate.getBlock() == Blocks.OAK_LOG) {
					leaves = Blocks.OAK_LEAVES.defaultBlockState();
				} else if (blockstate.getBlock() == Blocks.BIRCH_LOG) {
					leaves = Blocks.BIRCH_LEAVES.defaultBlockState();
				} else if (blockstate.getBlock() == Blocks.SPRUCE_LOG) {
					leaves = Blocks.SPRUCE_LEAVES.defaultBlockState();
				} else if (blockstate.getBlock() == Blocks.JUNGLE_LOG) {
					leaves = Blocks.JUNGLE_LEAVES.defaultBlockState();
				} else if (blockstate.getBlock() == Blocks.DARK_OAK_LOG) {
					leaves = Blocks.DARK_OAK_LEAVES.defaultBlockState();
				} else if (blockstate.getBlock() == Blocks.ACACIA_LOG) {
					leaves = Blocks.ACACIA_LEAVES.defaultBlockState();
				} else if (blockstate.getBlock() == Blocks.MANGROVE_LOG) {
					leaves = Blocks.MANGROVE_LEAVES.defaultBlockState();
				} else if (blockstate.getBlock() == Blocks.CHERRY_LOG) {
					leaves = Blocks.CHERRY_LEAVES.defaultBlockState();
				}
				if (ThatTreeProcedure.execute(world, x, y, z, blockstate, leaves)) {
					if (entity instanceof Player _player)
						_player.giveExperiencePoints(1);
					StepFellingProcedure.execute(world, x, y, z, leaves, blockstate, entity);
					ReignModMod.LOGGER.info(("Tree fallen: " + entity));
				}
			}
		}
	}
}
