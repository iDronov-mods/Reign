package net.mcreator.reignmod.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.reignmod.kingdom.KingdomData;
import net.mcreator.reignmod.init.ReignModModItems;

import javax.annotation.Nullable;

import java.util.UUID;

@Mod.EventBusSubscriber
public class SetPositionsProcedure {
	@SubscribeEvent
	public static void onRightClickEntity(PlayerInteractEvent.EntityInteract event) {
		if (event.getHand() != event.getEntity().getUsedItemHand())
			return;
		execute(event, event.getLevel(), event.getTarget(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
		execute(null, world, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		String UUID = "";
		UUID = entity.getStringUUID();
		if (IsKingProcedure.execute(world, entity) && entity instanceof Player) {
			if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == ReignModModItems.RIGHT_HAND.get()) {
				if (!KingdomData.isPlayerInCourt(UUID)) {
					if (IsLordProcedure.execute(world, entity)) {
						KingdomData.assignCourtier(KingdomData.CourtPosition.HAND_OF_THE_KING, UUID);
						(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).shrink(1);
					} else {
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal(("\u00A7c" + Component.translatable("translation.key.title_dont_match").getString())), false);
					}
				} else {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal((Component.translatable(("\u00A7c" + "translation.key.player_occupied_position")).getString())), false);
				}
			} else if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == ReignModModItems.MARSHAL_INSIGNIA.get()) {
				if (!KingdomData.isPlayerInCourt(UUID)) {
					if (IsKnightProcedure.execute(world, entity)) {
						KingdomData.assignCourtier(KingdomData.CourtPosition.MARSHAL, UUID);
						(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).shrink(1);
					} else {
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal(("\u00A7c" + Component.translatable("translation.key.title_dont_match").getString())), false);
					}
				} else {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal((Component.translatable(("\u00A7c" + "translation.key.player_occupied_position")).getString())), false);
				}
			} else if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == ReignModModItems.TREASURER_KEY.get()) {
				if (!KingdomData.isPlayerInCourt(UUID)) {
					KingdomData.assignCourtier(KingdomData.CourtPosition.TREASURER, UUID);
					(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).shrink(1);
				} else {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7c" + "translation.key.player_occupied_position")), false);
				}
			} else if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == ReignModModItems.ARCHITECT_COMPASS.get()) {
				if (!KingdomData.isPlayerInCourt(UUID)) {
					KingdomData.assignCourtier(KingdomData.CourtPosition.ARCHITECT, UUID);
					(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).shrink(1);
				} else {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal((Component.translatable(("\u00A7c" + "translation.key.player_occupied_position")).getString())), false);
				}
			}
		}
	}
}
