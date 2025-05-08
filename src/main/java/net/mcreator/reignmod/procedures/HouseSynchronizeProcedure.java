package net.mcreator.reignmod.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.house.HouseManager;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class HouseSynchronizeProcedure {
	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		execute(event, event.getEntity().level(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		boolean changed = false;
		String pUUID = "";
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				pUUID = entity.getStringUUID();
				if (!((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).house).isEmpty() && HouseManager.getPlayerHouse((Player) entity).isNull()) {
					{
						String _setval = "";
						entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.house = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						Entity _entityTeam = entity;
						PlayerTeam _pt = _entityTeam.level().getScoreboard()
								.getPlayerTeam((entity instanceof LivingEntity _teamEnt && _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()) != null
										? _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()).getName()
										: ""));
						if (_pt != null)
							_entityTeam.level().getScoreboard().removePlayerFromTeam(_entityTeam.getStringUUID(), _pt);
					}
					changed = true;
				}
				if (IsSlaveProcedure.execute(world, entity)) {
					if (!HouseManager.getPlayerDomain((Player) entity).isPlayerInDomain(pUUID)) {
						{
							String _setval = "";
							entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.house = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							Entity _entityTeam = entity;
							PlayerTeam _pt = _entityTeam.level().getScoreboard()
									.getPlayerTeam((entity instanceof LivingEntity _teamEnt && _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()) != null
											? _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()).getName()
											: ""));
							if (_pt != null)
								_entityTeam.level().getScoreboard().removePlayerFromTeam(_entityTeam.getStringUUID(), _pt);
						}
						changed = true;
					}
				}
				HouseManager.playerPrefixSynchronize((Player) entity);
				if (changed) {
					HouseManager.allPlayersPrefixPacketSend();
				}
			}
			world = _worldorig;
		}
	}
}
