package net.mcreator.reignmod.procedures;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.mcreator.reignmod.house.HouseManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class DomainKickSlaveProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		String KnightUUID = "";
		String PlayerName = "";
		KnightUUID = entity.getStringUUID();
		PlayerName = (new Object() {
			public String getMessage() {
				try {
					return MessageArgument.getMessage(arguments, "Player").getString();
				} catch (CommandSyntaxException ignored) {
					return "";
				}
			}
		}).getMessage();
		if (HouseManager.removePlayerFromDomain(KnightUUID, PlayerName)) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal((PlayerName + " " + Component.translatable("translation.key.kicked_from_domain").getString())), false);
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal((Component.translatable("translation.key.not_kicked_from_domain").getString())), false);
		}
	}
}
