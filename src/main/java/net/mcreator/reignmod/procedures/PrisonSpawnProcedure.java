package net.mcreator.reignmod.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.kingdom.KingdomManager;
import net.mcreator.reignmod.init.ReignModModMobEffects;
import net.mcreator.reignmod.house.HouseManager;
import net.mcreator.reignmod.house.House;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class PrisonSpawnProcedure {
	@SubscribeEvent
	public static void onPlayerRespawned(PlayerEvent.PlayerRespawnEvent event) {
		execute(event, event.getEntity().level(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double x_house_prison = 0;
		double y_house_prison = 0;
		double z_house_prison = 0;
		String SuzerainUUID = "";
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).isCriminal) {
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(ReignModModMobEffects.CRIMINAL.get(), -1, 0));
				}
				if (!((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).prison_house).isEmpty()) {
					SuzerainUUID = (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).prison_house;
					House house = HouseManager.getHouseBySuzerainUUID(SuzerainUUID);
					x_house_prison = house.getHousePrisonCoordinates()[0];
					y_house_prison = house.getHousePrisonCoordinates()[1];
					z_house_prison = house.getHousePrisonCoordinates()[2];
					if (x_house_prison != 0 && y_house_prison != 0 && z_house_prison != 0) {
						{
							Entity _ent = entity;
							_ent.teleportTo(x_house_prison, y_house_prison, z_house_prison);
							if (_ent instanceof ServerPlayer _serverPlayer)
								_serverPlayer.connection.teleport(x_house_prison, y_house_prison, z_house_prison, _ent.getYRot(), _ent.getXRot());
						}
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, -1, 2));
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, -1, 5));
						if (!world.isClientSide() && world.getServer() != null)
							world.getServer().getPlayerList()
									.broadcastSystemMessage(Component.literal((entity.getDisplayName().getString() + " \u00A78" + Component.translatable("translation.key.house_imprisoned").getString() + " " + house.getHouseTitleWithColor())), false);
						{
							String _setval = "";
							entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.prison_house = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
					}
				} else if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).isCriminal) {
					if (!(KingdomManager.getPrisonCoordinates()[0] == 0 && KingdomManager.getPrisonCoordinates()[1] == 0 && KingdomManager.getPrisonCoordinates()[2] == 0)) {
						{
							Entity _ent = entity;
							_ent.teleportTo(KingdomManager.getPrisonCoordinates()[0], KingdomManager.getPrisonCoordinates()[1], KingdomManager.getPrisonCoordinates()[2]);
							if (_ent instanceof ServerPlayer _serverPlayer)
								_serverPlayer.connection.teleport(KingdomManager.getPrisonCoordinates()[0], KingdomManager.getPrisonCoordinates()[1], KingdomManager.getPrisonCoordinates()[2], _ent.getYRot(), _ent.getXRot());
						}
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, -1, 2));
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, -1, 5));
						if (!world.isClientSide() && world.getServer() != null)
							world.getServer().getPlayerList().broadcastSystemMessage(Component.literal((entity.getDisplayName().getString() + " \u00A78" + Component.translatable("translation.key.royale_imprisoned").getString())), false);
						if (entity instanceof ServerPlayer _player) {
							Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("reign_mod:gotcha"));
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								for (String criteria : _ap.getRemainingCriteria())
									_player.getAdvancements().award(_adv, criteria);
							}
						}
					}
				}
			}
			world = _worldorig;
		}
	}
}
