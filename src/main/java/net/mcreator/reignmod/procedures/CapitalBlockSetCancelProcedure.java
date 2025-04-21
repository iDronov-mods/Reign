package net.mcreator.reignmod.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.level.BlockEvent;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.reignmod.init.ReignModModBlocks;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CapitalBlockSetCancelProcedure {
	@SubscribeEvent
	public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getState(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		execute(null, world, x, y, z, blockstate, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		if (entity == null)
			return;
		if (blockstate.getBlock() == ReignModModBlocks.MARKET_BLOCK.get() || blockstate.getBlock() == ReignModModBlocks.TRADE_BLOCK.get() || blockstate.getBlock() == ReignModModBlocks.RENTAL_BLOCK.get()
				|| blockstate.getBlock() == ReignModModBlocks.COFFERS.get()) {
			if (!CapitalBlockCheckProcedure.execute(world, x, y, z)) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("\u00A7c" + Component.translatable("translation.key.capital_block_set_cancel").getString())), true);
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				} else if (event != null && event.hasResult()) {
					event.setResult(Event.Result.DENY);
				}
			}
		}
		if (blockstate.getBlock() == ReignModModBlocks.COFFERS.get()) {
			if (IsKingProcedure.execute(world, entity)) {
				CoffersPlacedProcedure.execute(world, x, y, z);
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("\u00A7c" + Component.translatable("translation.key.coffers_set_only_king").getString())), true);
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				} else if (event != null && event.hasResult()) {
					event.setResult(Event.Result.DENY);
				}
			}
		}
	}
}
