package net.mcreator.reignmod.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.house.HouseManager;

import javax.annotation.Nullable;

import java.util.Calendar;

@Mod.EventBusSubscriber
public class EnterPayProcedure {
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
		double value = 0;
		String prefix = "";
		HouseManager.playerPrefixSynchronize((Player) entity);
		if (IsKingProcedure.execute(world, entity)) {
			if (!world.isClientSide() && world.getServer() != null)
				world.getServer().getPlayerList().broadcastSystemMessage(Component.literal((Component.translatable("KingOnline").getString())), false);
		}
		if (!((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).LastEnter_Day == Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
				&& (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).LastEnter_Month == Calendar.getInstance().get(Calendar.MONTH))) {
			if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).LastEnter_Day == Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1
					&& (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).LastEnter_Month == Calendar.getInstance().get(Calendar.MONTH)
					|| Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == 1
							&& ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).LastEnter_Day == 30
									|| (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).LastEnter_Day == 31)
							&& (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).LastEnter_Month == Calendar.getInstance().get(Calendar.MONTH) - 1) {
				{
					double _setval = (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).DaysOnline + 1;
					entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.DaysOnline = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else {
				{
					double _setval = 1;
					entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.DaysOnline = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal((Component.translatable("count_days").getString() + " "
						+ new java.text.DecimalFormat("##").format((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).DaysOnline))), false);
			if (IsKingProcedure.execute(world, entity)) {
				value = HouseManager.getHousesCount() * 256
						+ HouseManager.getHousePlayerCount((Player) entity) * 16 * (1 + 0.05 * (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).DaysOnline);
				WalletGiveProcedure.execute(entity, value);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal((Component.translatable("pay_king").getString() + " \u00A7l" + new java.text.DecimalFormat("##").format(value) + "\u00A7r " + Component.translatable("copper_coins_pay").getString())),
							false);
			} else if (IsLordProcedure.execute(world, entity)) {
				value = HouseManager.getHousePlayerCount((Player) entity) * 16 * (1 + 0.05 * (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).DaysOnline);
				WalletGiveProcedure.execute(entity, value);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal((Component.translatable("pay_lord").getString() + " \u00A7l" + new java.text.DecimalFormat("##").format(value) + "\u00A7r " + Component.translatable("copper_coins_pay").getString())),
							false);
			} else if (IsKnightProcedure.execute(world, entity)) {
				value = HouseManager.getDomainPlayerCount((Player) entity) * 16 * (1 + 0.05 * (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).DaysOnline);
				WalletGiveProcedure.execute(entity, value);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(
							Component.literal((Component.translatable("pay_knight").getString() + " \u00A7l" + new java.text.DecimalFormat("##").format(value) + "\u00A7r " + Component.translatable("copper_coins_pay").getString())), false);
			} else if (IsSlaveProcedure.execute(world, entity)) {
				value = 8 * (1 + 0.05 * (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).DaysOnline);
				WalletGiveProcedure.execute(entity, value);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(
							Component.literal((Component.translatable("pay_slave").getString() + " \u00A7l" + new java.text.DecimalFormat("##").format(value) + "\u00A7r " + Component.translatable("copper_coins_pay").getString())), false);
			}
		}
		if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).DaysOnline == 7) {
			if (entity instanceof ServerPlayer _player) {
				Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("reign_mod:days_online_7"));
				AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
				if (!_ap.isDone()) {
					for (String criteria : _ap.getRemainingCriteria())
						_player.getAdvancements().award(_adv, criteria);
				}
			}
		} else if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).DaysOnline == 14) {
			if (entity instanceof ServerPlayer _player) {
				Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("reign_mod:days_online_14"));
				AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
				if (!_ap.isDone()) {
					for (String criteria : _ap.getRemainingCriteria())
						_player.getAdvancements().award(_adv, criteria);
				}
			}
		} else if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).DaysOnline == 30) {
			if (entity instanceof ServerPlayer _player) {
				Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("reign_mod:days_online_30"));
				AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
				if (!_ap.isDone()) {
					for (String criteria : _ap.getRemainingCriteria())
						_player.getAdvancements().award(_adv, criteria);
				}
			}
		}
		{
			double _setval = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
			entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.LastEnter_Day = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = Calendar.getInstance().get(Calendar.MONTH);
			entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.LastEnter_Month = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
