package net.mcreator.reignmod.procedures;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.mcreator.reignmod.house.HouseManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;

public class HouseCriminalProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		String wanted_player = "";
		String UUID = "";
		if (IsLordProcedure.execute(world, entity)) {
			wanted_player = (new Object() {
				public String getMessage() {
					try {
						return MessageArgument.getMessage(arguments, "player").getString();
					} catch (CommandSyntaxException ignored) {
						return "";
					}
				}
			}).getMessage();
			UUID = entity.getStringUUID();
			if (HouseManager.addWantedPlayer(UUID, wanted_player)) {
				if (!world.isClientSide() && world.getServer() != null)
					world.getServer().getPlayerList().broadcastSystemMessage(Component.literal((Component.translatable(((new Object() {
						public String getMessage() {
							try {
								return MessageArgument.getMessage(arguments, "player").getString();
							} catch (CommandSyntaxException ignored) {
								return "";
							}
						}
					}).getMessage() + " " + Component.translatable("translation.key.house_criminal_success").getString() + " " + HouseManager.getHouseByLordUUID(UUID).getHouseTitle())).getString())), false);
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal((Component.translatable("translation.key.house_criminal_fail").getString())), false);
			}
		}
	}
}
