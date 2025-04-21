package net.mcreator.reignmod.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.house.HouseManager;
import net.mcreator.reignmod.house.House;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class DeathArrestProcedure {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(Entity entity, Entity sourceentity) {
		execute(null, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		String sourceplayerUUID = "";
		String playerUUID = "";
		String sourceplayer_suzirainUUID = "";
		if (!(entity == sourceentity) && entity instanceof ServerPlayer && sourceentity instanceof ServerPlayer) {
			playerUUID = entity.getStringUUID();
			House house = HouseManager.getPlayerHouse((Player) sourceentity);
			if (!house.isNull() && house.isWanted(playerUUID)) {
				{
					String _setval = (sourceentity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).house;
					entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.prison_house = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
	}
}
