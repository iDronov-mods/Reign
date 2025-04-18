package net.mcreator.reignmod.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.kingdom.KingdomData;
import net.mcreator.reignmod.init.ReignModModItems;
import net.mcreator.reignmod.ReignModMod;

import java.util.function.Supplier;
import java.util.Map;

public class FundTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(1)).getItem() : ItemStack.EMPTY).getItem() == ReignModModItems.PLATINUM_COIN.get()
				&& (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == ReignModModItems.PLATINUM_COIN
						.get()) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				((Slot) _slots.get(1)).remove(1);
				_player.containerMenu.broadcastChanges();
			}
			ReignModModVariables.MapVariables.get(world).market_copper_all = Math.max(ReignModModVariables.MapVariables.get(world).market_copper_all - 4096, 0);
			ReignModModVariables.MapVariables.get(world).syncData(world);
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				((Slot) _slots.get(0)).remove(1);
				_player.containerMenu.broadcastChanges();
			}
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem()) {
				if (world instanceof ServerLevel _level) {
					LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
					entityToSpawn
							.moveTo(Vec3.atBottomCenterOf(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X, ReignModModVariables.MapVariables.get(world).CAPITAL_Y, ReignModModVariables.MapVariables.get(world).CAPITAL_Z)));
					entityToSpawn.setVisualOnly(true);
					_level.addFreshEntity(entityToSpawn);
				}
				ReignModModVariables.MapVariables.get(world).ERA = ReignModModVariables.MapVariables.get(world).ERA + 1;
				ReignModModVariables.MapVariables.get(world).syncData(world);
				KingdomData.upgradeCapitalEra();
				if (world.isClientSide())
					Minecraft.getInstance().gameRenderer.displayItemActivation(new ItemStack(ReignModModItems.PLATINUM_COIN.get()));
				ReignModMod.queueServerWork(40, () -> {
					world.getLevelData().setRaining(true);
					EraCostCreateProcedure.execute(world, ReignModModVariables.MapVariables.get(world).CAPITAL_X, ReignModModVariables.MapVariables.get(world).CAPITAL_Y, ReignModModVariables.MapVariables.get(world).CAPITAL_Z);
				});
				ReignModMod.queueServerWork(80, () -> {
					for (int index0 = 0; index0 < (int) (20 * ReignModModVariables.MapVariables.get(world).ERA); index0++) {
						if (world instanceof ServerLevel _level)
							_level.addFreshEntity(new ExperienceOrb(_level, (x + Mth.nextInt(RandomSource.create(), -32, 32)), (y + Mth.nextInt(RandomSource.create(), 40, 100)), (z + Mth.nextInt(RandomSource.create(), -32, 32)), 5));
					}
				});
				if (!world.isClientSide() && world.getServer() != null)
					world.getServer().getPlayerList()
							.broadcastSystemMessage(Component.literal((Component.translatable((Component.translatable("translation.key.new_era").getString())).getString() + "" + ReignModModVariables.MapVariables.get(world).ERA)), false);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.lightning_bolt.thunder")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.lightning_bolt.thunder")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (ReignModModVariables.MapVariables.get(world).ERA == 6) {
					Add1KingdomProcedure.execute(world);
				} else if (ReignModModVariables.MapVariables.get(world).ERA == 9) {
					Add3KingdomsProcedure.execute(world);
				}
			}
		}
	}
}
