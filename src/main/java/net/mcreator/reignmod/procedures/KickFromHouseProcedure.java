package net.mcreator.reignmod.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.init.ReignModModItems;
import net.mcreator.reignmod.house.HouseManager;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class KickFromHouseProcedure {
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
		if (((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).house).equals(sourceentity.getStringUUID())) {
			if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ReignModModItems.BLACKMARK.get()) {
				if (IsKnightProcedure.execute(world, entity)) {
					if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem() && entity.getXRot() >= 75 && entity.isShiftKeyDown()) {
						HouseManager.deleteDomain((Player) sourceentity, (Player) entity);
						{
							String _setval = "";
							entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.house = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
						if (!world.isClientSide() && world.getServer() != null)
							world.getServer().getPlayerList().broadcastSystemMessage(
									Component.literal((entity.getDisplayName().getString() + " " + Component.translatable("translation.key.deleteDomain").getString() + " " + HouseManager.getPlayerHouseTitle((Player) sourceentity))), false);
						HouseManager.playerPrefixSynchronize((Player) entity);
						HouseManager.allPlayersPrefixPacketSend();
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(0, 0, 0), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									("team leave " + entity.getName()));
					}
				} else {
					HouseManager.removePlayerFromDomain((Player) entity);
					{
						String _setval = "";
						entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.house = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
					if (!world.isClientSide() && world.getServer() != null)
						world.getServer().getPlayerList().broadcastSystemMessage(
								Component.literal((entity.getDisplayName().getString() + " " + Component.translatable("translation.key.removePlayerFromDomain").getString() + " " + HouseManager.getPlayerDomainTitle((Player) sourceentity))), false);
					HouseManager.playerPrefixSynchronize((Player) entity);
					HouseManager.allPlayersPrefixPacketSend();
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(0, 0, 0), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								("team leave " + GetTrueNameProcedure.execute(entity)));
				}
			}
		}
	}
}
