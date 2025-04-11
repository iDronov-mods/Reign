package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.reignmod.kingdom.KingdomData;
import net.mcreator.reignmod.init.ReignModModItems;

public class RemovePositionsProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity.isShiftKeyDown() && IsKingProcedure.execute(world, entity)) {
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ReignModModItems.RIGHT_HAND.get()) {
				KingdomData.dismissCourtier(KingdomData.CourtPosition.HAND_OF_THE_KING);
			} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ReignModModItems.MARSHAL_INSIGNIA.get()) {
				KingdomData.dismissCourtier(KingdomData.CourtPosition.MARSHAL);
			} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ReignModModItems.TREASURER_KEY.get()) {
				KingdomData.dismissCourtier(KingdomData.CourtPosition.TREASURER);
			} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ReignModModItems.ARCHITECT_COMPASS.get()) {
				KingdomData.dismissCourtier(KingdomData.CourtPosition.ARCHITECT);
			}
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal((Component.translatable("translation.key.remove_position").getString())), false);
			if (entity instanceof Player _player)
				_player.getCooldowns().addCooldown((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem(), 20);
		}
	}
}
