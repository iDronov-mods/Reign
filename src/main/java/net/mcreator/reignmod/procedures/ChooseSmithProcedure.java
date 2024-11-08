package net.mcreator.reignmod.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import net.mcreator.reignmod.network.ReignModModVariables;

import java.util.Collections;

public class ChooseSmithProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			boolean _setval = true;
			entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.license_smith = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("You are a Smith!"), true);
		if (entity instanceof ServerPlayer _serverPlayer)
			_serverPlayer.server.getRecipeManager().byKey(new ResourceLocation("minecraft:stone_pickaxe")).ifPresent(_rec -> _serverPlayer.resetRecipes(Collections.singleton(_rec)));
		if (entity instanceof Player _player)
			_player.closeContainer();
	}
}
