package net.mcreator.reignmod.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

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
		pUUID = entity.getStringUUID();
		if (!((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).house).isEmpty() && HouseManager.getPlayerHouse((Player) entity).isNull()) {
			{
				String _setval = "";
				entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.house = _setval;
					capability.syncPlayerVariables(entity);
				});
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
				changed = true;
			}
		}
		HouseManager.playerPrefixSynchronize((Player) entity);
		if (changed) {
			HouseManager.allPlayersPrefixPacketSend();
		}
	}
}
