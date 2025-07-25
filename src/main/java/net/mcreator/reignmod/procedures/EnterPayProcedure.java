package net.mcreator.reignmod.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.init.ReignModModItems;
import net.mcreator.reignmod.init.ReignModModBlocks;
import net.mcreator.reignmod.house.HouseManager;
import net.mcreator.reignmod.configuration.ReignCommonConfiguration;

import javax.annotation.Nullable;

import java.util.UUID;
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
		BlockState strategy_block = Blocks.AIR.defaultBlockState();
		String prefix = "";
		String UUID = "";
		String knightUUID = "";
		if (IsKingProcedure.execute(world, entity)) {
			if (!world.isClientSide() && world.getServer() != null)
				world.getServer().getPlayerList().broadcastSystemMessage(Component.literal((Component.translatable("KingOnline").getString())), false);
		}
		if (!((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).LastEnter_Day == Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
				&& (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).LastEnter_Week == Calendar.getInstance().get(Calendar.WEEK_OF_YEAR))) {
			if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).LastEnter_Day == Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1
					&& (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).LastEnter_Week == Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)
					|| (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).LastEnter_Week == Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) - 1
							&& (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).LastEnter_Day == 7 && Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == 1
					|| (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).LastEnter_Week == 52 && Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) == 1) {
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
			if (!ReignCommonConfiguration.DISABLE_DAILY_PAYOUTS.get()) {
				if (!((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).house).isEmpty()) {
					UUID = (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).house;
					strategy_block = (world.getBlockState(BlockPos.containing(HouseManager.getDomainByKnightUUID(UUID).getDomainStrategyBlockCoordinates()[0], HouseManager.getDomainByKnightUUID(UUID).getDomainStrategyBlockCoordinates()[1],
							HouseManager.getDomainByKnightUUID(UUID).getDomainStrategyBlockCoordinates()[2])));
					if (strategy_block.getBlock() == ReignModModBlocks.STRATEGY_BLOCK.get()) {
						if (IsKingProcedure.execute(world, entity)) {
							{
								BlockEntity _ent = world.getBlockEntity(BlockPos.containing(HouseManager.getDomainByKnightUUID(UUID).getDomainStrategyBlockCoordinates()[0],
										HouseManager.getDomainByKnightUUID(UUID).getDomainStrategyBlockCoordinates()[1], HouseManager.getDomainByKnightUUID(UUID).getDomainStrategyBlockCoordinates()[2]));
								if (_ent != null) {
									final int _slotid = 2;
									final ItemStack _setstack = new ItemStack(ReignModModItems.REIGN_COIN.get()).copy();
									_setstack.setCount(5);
									_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
										if (capability instanceof IItemHandlerModifiable)
											((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
									});
								}
							}
						} else if (IsLordProcedure.execute(world, entity)) {
							{
								BlockEntity _ent = world.getBlockEntity(BlockPos.containing(HouseManager.getDomainByKnightUUID(UUID).getDomainStrategyBlockCoordinates()[0],
										HouseManager.getDomainByKnightUUID(UUID).getDomainStrategyBlockCoordinates()[1], HouseManager.getDomainByKnightUUID(UUID).getDomainStrategyBlockCoordinates()[2]));
								if (_ent != null) {
									final int _slotid = 2;
									final ItemStack _setstack = new ItemStack(ReignModModItems.REIGN_COIN.get()).copy();
									_setstack.setCount(3);
									_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
										if (capability instanceof IItemHandlerModifiable)
											((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
									});
								}
							}
						} else if (IsKnightProcedure.execute(world, entity)) {
							{
								BlockEntity _ent = world.getBlockEntity(BlockPos.containing(HouseManager.getDomainByKnightUUID(UUID).getDomainStrategyBlockCoordinates()[0],
										HouseManager.getDomainByKnightUUID(UUID).getDomainStrategyBlockCoordinates()[1], HouseManager.getDomainByKnightUUID(UUID).getDomainStrategyBlockCoordinates()[2]));
								if (_ent != null) {
									final int _slotid = 2;
									final ItemStack _setstack = new ItemStack(ReignModModItems.REIGN_COIN.get()).copy();
									_setstack.setCount(2);
									_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
										if (capability instanceof IItemHandlerModifiable)
											((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
									});
								}
							}
							knightUUID = entity.getStringUUID();
							{
								BlockEntity _ent = world.getBlockEntity(BlockPos.containing(HouseManager.getDomainByKnightUUID(knightUUID).getDomainStrategyBlockCoordinates()[0],
										HouseManager.getDomainByKnightUUID(knightUUID).getDomainStrategyBlockCoordinates()[1], HouseManager.getDomainByKnightUUID(knightUUID).getDomainStrategyBlockCoordinates()[2]));
								if (_ent != null) {
									final int _slotid = 2;
									final ItemStack _setstack = new ItemStack(ReignModModItems.REIGN_COIN.get()).copy();
									_setstack.setCount(2);
									_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
										if (capability instanceof IItemHandlerModifiable)
											((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
									});
								}
							}
						} else {
							{
								BlockEntity _ent = world.getBlockEntity(BlockPos.containing(HouseManager.getDomainByKnightUUID(UUID).getDomainStrategyBlockCoordinates()[0],
										HouseManager.getDomainByKnightUUID(UUID).getDomainStrategyBlockCoordinates()[1], HouseManager.getDomainByKnightUUID(UUID).getDomainStrategyBlockCoordinates()[2]));
								if (_ent != null) {
									final int _slotid = 2;
									final ItemStack _setstack = new ItemStack(ReignModModItems.REIGN_COIN.get()).copy();
									_setstack.setCount(1);
									_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
										if (capability instanceof IItemHandlerModifiable)
											((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
									});
								}
							}
						}
					}
				}
				value = 8 + (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).DaysOnline * 8;
				if (value >= 550) {
					value = 550;
				}
				if (entity instanceof Player _player)
					_player.giveExperiencePoints((int) value);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal((Component.translatable("translation.key.get_dailypay_exp").getString() + " \u00A7a" + new java.text.DecimalFormat("##").format(value))), false);
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
		} else if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).DaysOnline >= 100) {
			if (entity instanceof ServerPlayer _player) {
				Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("reign_mod:days_online_100"));
				AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
				if (!_ap.isDone()) {
					for (String criteria : _ap.getRemainingCriteria())
						_player.getAdvancements().award(_adv, criteria);
				}
			}
		}
		{
			double _setval = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
			entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.LastEnter_Day = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
			entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.LastEnter_Week = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if ((world instanceof Level _lvl ? _lvl.dimension() : (world instanceof WorldGenLevel _wgl ? _wgl.getLevel().dimension() : Level.OVERWORLD)) == Level.NETHER) {
			CheckBeeResistProcedure.execute(entity);
		}
	}
}
