package net.mcreator.reignmod.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.level.BlockEvent;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.BlockTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.network.ReignModModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class MinerProcedure {
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
		if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).license_miner) {
			if (blockstate.is(BlockTags.create(new ResourceLocation("forge:ores")))
					&& !(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)) != 0)) {
				if (entity instanceof Player _player)
					_player.giveExperiencePoints(1);
			}
			if (Mth.nextInt(RandomSource.create(), 1, 100) <= (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).efficiency) {
				if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).MAIN_LVL < 3
						|| !(entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).R_LVL) {
					if (blockstate.getBlock() == Blocks.DEEPSLATE_GOLD_ORE || blockstate.getBlock() == Blocks.DEEPSLATE_LAPIS_ORE || blockstate.getBlock() == Blocks.DEEPSLATE_DIAMOND_ORE || blockstate.getBlock() == Blocks.DEEPSLATE_EMERALD_ORE
							|| blockstate.getBlock() == Blocks.DEEPSLATE_COPPER_ORE || blockstate.getBlock() == Blocks.DEEPSLATE_COAL_ORE || blockstate.getBlock() == Blocks.DEEPSLATE_IRON_ORE
							|| blockstate.getBlock() == Blocks.DEEPSLATE_REDSTONE_ORE) {
						world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 80, 1, false, false));
					}
				}
				if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).MAIN_LVL < 3
						|| (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).R_LVL) {
					if (blockstate.getBlock() == Blocks.EMERALD_ORE) {
						world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
					}
				}
				if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).MAIN_LVL >= 3
						&& (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).R_LVL) {
					if (Mth.nextInt(RandomSource.create(), 1, 4) == 1 && !(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)) != 0)) {
						if (blockstate.getBlock() == Blocks.COAL_ORE) {
							if (world instanceof ServerLevel _level) {
								ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(Items.COAL));
								entityToSpawn.setPickUpDelay(5);
								_level.addFreshEntity(entityToSpawn);
							}
						} else if (blockstate.getBlock() == Blocks.IRON_ORE && ReignModModVariables.MapVariables.get(world).ERA >= 1) {
							if (world instanceof ServerLevel _level) {
								ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(Items.RAW_IRON));
								entityToSpawn.setPickUpDelay(5);
								_level.addFreshEntity(entityToSpawn);
							}
						} else if ((blockstate.getBlock() == Blocks.REDSTONE_ORE || blockstate.getBlock() == Blocks.REDSTONE_ORE) && ReignModModVariables.MapVariables.get(world).ERA >= 5) {
							if (world instanceof ServerLevel _level) {
								ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(Items.REDSTONE));
								entityToSpawn.setPickUpDelay(5);
								_level.addFreshEntity(entityToSpawn);
							}
						} else if (blockstate.getBlock() == Blocks.COPPER_ORE) {
							if (world instanceof ServerLevel _level) {
								ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(Items.RAW_COPPER));
								entityToSpawn.setPickUpDelay(5);
								_level.addFreshEntity(entityToSpawn);
							}
						}
					}
				}
				if (blockstate.getBlock() == Blocks.ANCIENT_DEBRIS) {
					if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).MAIN_LVL < 5) {
						world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
					}
				}
			} else {
				world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
			}
		}
	}
}
