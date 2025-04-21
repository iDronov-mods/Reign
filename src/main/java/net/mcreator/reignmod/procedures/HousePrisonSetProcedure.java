package net.mcreator.reignmod.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.house.HouseManager;

import java.util.UUID;

public class HousePrisonSetProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		String UUID = "";
		double x_block = 0;
		double y_block = 0;
		double z_block = 0;
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				if (IsLordProcedure.execute(world, entity)) {
					if (true) {
						if (entity.isShiftKeyDown()) {
							UUID = entity.getStringUUID();
							x_block = x;
							y_block = y;
							z_block = z;
							HouseManager.getHouseByLordUUID(UUID).setHousePrisonCoordinates(new int[]{(int) x_block, (int) y_block + 1, (int) z_block});
							if (entity instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal((Component.translatable("translation.key.house_prison_set").getString())), false);
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.chain.place")), SoundSource.BLOCKS, 1, 1);
								} else {
									_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.chain.place")), SoundSource.BLOCKS, 1, 1, false);
								}
							}
						}
					}
				}
			}
			world = _worldorig;
		}
	}
}
