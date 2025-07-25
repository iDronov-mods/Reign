package net.mcreator.reignmod.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.mines.MineUtils;
import net.mcreator.reignmod.mines.MineManager;
import net.mcreator.reignmod.init.ReignModModItems;

public class MineTakeSampleProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		if (entity == null)
			return;
		ItemStack sample = ItemStack.EMPTY;
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				if (blockstate.getBlock() == Blocks.BEDROCK) {
					sample = new ItemStack(ReignModModItems.DEEP_ROCK_SAMPLE.get()).copy();
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("reign_mod:take_sample")), SoundSource.BLOCKS, (float) 0.8,
									(float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("reign_mod:take_sample")), SoundSource.BLOCKS, (float) 0.8, (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1), false);
						}
					}
					sample.getOrCreateTag().putDouble("x", x);
					sample.getOrCreateTag().putDouble("y", y);
					sample.getOrCreateTag().putDouble("z", z);
					var biome = MineUtils.getLocalizedBiomeName(new ChunkPos(new BlockPos((int) x, (int) y, (int) z)));
					var result = MineManager.tryCreateMine(new ChunkPos(new BlockPos((int) x, (int) y, (int) z)));
					if (result.isEmpty()) {
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal("\u0416\u0438\u043B\u0430 \u043F\u0443\u0441\u0442\u0430"), false);
					} else {
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal("\u0416\u0438\u043B\u0430 \u0435\u0441\u0442\u044C"), false);
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal(result.get().getType().name()), false);
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal(result.get().getRichness().name()), false);
					}
					sample.getOrCreateTag().putString("biome", biome.getString());
					if (entity instanceof Player _player) {
						ItemStack _stktoremove = new ItemStack(ReignModModItems.BOTTLE_OF_COAL_DUST.get());
						_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
					}
					if (entity instanceof Player _player) {
						ItemStack _setstack = sample.copy();
						_setstack.setCount(1);
						ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
					}
				}
			}
			world = _worldorig;
		}
	}
}
