package net.mcreator.reignmod.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.commands.CommandSourceStack;

import net.mcreator.reignmod.house.HouseManager;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;

public class KickOfflinePlayerFromHouseProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		String suzerainUUID = "";
		String player_name = "";
		suzerainUUID = entity.getStringUUID();
		player_name = (new Object() {
			public String getMessage() {
				try {
					return MessageArgument.getMessage(arguments, "player").getString();
				} catch (CommandSyntaxException ignored) {
					return "";
				}
			}
		}).getMessage();
		var result = HouseManager.removeDirectVassal(suzerainUUID, player_name);
		if (result == HouseManager.removeDirectVassalResult.PLAYER_NOT_FOUND) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(((new Object() {
					public String getMessage() {
						try {
							return MessageArgument.getMessage(arguments, "player").getString();
						} catch (CommandSyntaxException ignored) {
							return "";
						}
					}
				}).getMessage() + " \u00A7c" + Component.translatable("translation.key.kick_player_not_found").getString())), false);
		} else if (result == HouseManager.removeDirectVassalResult.INSUFFICIENT_AUTHORITY) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(((new Object() {
					public String getMessage() {
						try {
							return MessageArgument.getMessage(arguments, "player").getString();
						} catch (CommandSyntaxException ignored) {
							return "";
						}
					}
				}).getMessage() + " \u00A7c" + Component.translatable("translation.key.kick_insufficient_authority").getString())), false);
		} else if (result == HouseManager.removeDirectVassalResult.DOMAIN_IS_PROTECTED) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(((new Object() {
					public String getMessage() {
						try {
							return MessageArgument.getMessage(arguments, "player").getString();
						} catch (CommandSyntaxException ignored) {
							return "";
						}
					}
				}).getMessage() + " \u00A7c" + Component.translatable("translation.key.kick_protected").getString())), false);
		} else if (result == HouseManager.removeDirectVassalResult.TRY_TO_KICK_YOURSELF) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal((" \u00A7c" + Component.translatable("translation.key.try_kick_yourself").getString())), false);
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(((new Object() {
					public String getMessage() {
						try {
							return MessageArgument.getMessage(arguments, "player").getString();
						} catch (CommandSyntaxException ignored) {
							return "";
						}
					}
				}).getMessage() + " \u00A7a" + Component.translatable("translation.key.kicked_from_house").getString())), false);
		}
	}
}
