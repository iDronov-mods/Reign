package net.mcreator.reignmod.procedures;

import net.mcreator.reignmod.init.ReignModModItems;
import net.mcreator.reignmod.init.ReignModModParticleTypes;
import net.mcreator.reignmod.network.ReignModModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.registries.ForgeRegistries;

public class FundGetBlackMarkProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).getItem() == ReignModModItems.ACCURSED_BLACK_MARK.get()) {
			ReignModModVariables.MapVariables.get(world).SurgingSource = ReignModModVariables.MapVariables.get(world).SurgingSource + (entity instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).getCount() * 10;
			ReignModModVariables.MapVariables.get(world).syncData(world);
			if (ReignModModVariables.MapVariables.get(world).SurgingSource > 200) {
				ReignModModVariables.MapVariables.get(world).SurgingSource = 200;
				ReignModModVariables.MapVariables.get(world).syncData(world);
			}
			if (!entity.level().isClientSide())
				entity.discard();
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("reign_mod:fall_source")), SoundSource.BLOCKS, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("reign_mod:fall_source")), SoundSource.BLOCKS, 1, 1, false);
				}
			}
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (ReignModModParticleTypes.EREOUS.get()), x, y, z, (int) (5 + ReignModModVariables.MapVariables.get(world).SurgingSource / 100), 0.3, 1, 0, 0.5);
		} else if ((entity instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).getItem() == ReignModModItems.PLATINUM_COIN.get()) {
			ReignModModVariables.MapVariables.get(world).SurgingSource = ReignModModVariables.MapVariables.get(world).SurgingSource - (entity instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).getCount() * 12;
			ReignModModVariables.MapVariables.get(world).syncData(world);
			if (ReignModModVariables.MapVariables.get(world).SurgingSource < 0) {
				ReignModModVariables.MapVariables.get(world).SurgingSource = 0;
				ReignModModVariables.MapVariables.get(world).syncData(world);
			}
			if (!entity.level().isClientSide())
				entity.discard();
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("reign_mod:fall_source")), SoundSource.BLOCKS, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("reign_mod:fall_source")), SoundSource.BLOCKS, 1, 1, false);
				}
			}
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (ReignModModParticleTypes.EREOUS.get()), x, y, z, (int) (ReignModModVariables.MapVariables.get(world).SurgingSource / 100), 0.3, 1, 0, 0.5);
		}
	}
}
