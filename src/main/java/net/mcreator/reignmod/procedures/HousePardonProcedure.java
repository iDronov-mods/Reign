package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.commands.CommandSourceStack;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.house.HouseManager;

import java.util.UUID;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;

public class HousePardonProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		String pardon_player = "";
		String UUID = "";
		if (!((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).house).isEmpty()) {
			pardon_player = (new Object() {
				public String getMessage() {
					try {
						return MessageArgument.getMessage(arguments, "player").getString();
					} catch (CommandSyntaxException ignored) {
						return "";
					}
				}
			}).getMessage();
			UUID = entity.getStringUUID();
			if (HouseManager.removeWantedPlayer(UUID, pardon_player)) {
				if (!world.isClientSide() && world.getServer() != null)
					world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(((new Object() {
						public String getMessage() {
							try {
								return MessageArgument.getMessage(arguments, "player").getString();
							} catch (CommandSyntaxException ignored) {
								return "";
							}
						}
					}).getMessage() + " " + Component.translatable("translation.key.house_pardon_success").getString() + " " + HouseManager.getHouseByLordUUID(UUID).getHouseTitle())), false);
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal((Component.translatable("translation.key.house_pardon_fail").getString())), false);
			}
		}
	}
}
