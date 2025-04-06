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

public class HouseRenameProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		String UUID = "";
		String name = "";
		if (IsLordProcedure.execute(world, entity)) {
			UUID = entity.getStringUUID();
			name = (new Object() {
				public String getMessage() {
					try {
						return MessageArgument.getMessage(arguments, "name").getString();
					} catch (CommandSyntaxException ignored) {
						return "";
					}
				}
			}).getMessage();
			if (!(name).isEmpty()) {
				HouseManager.getHouseByLordUUID(UUID).setHouseTitle(name);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal((Component.translatable("translation.key.house_rename").getString())), false);
			}
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal((Component.translatable("translation.key.is_not_lord").getString())), false);
		}
	}
}
