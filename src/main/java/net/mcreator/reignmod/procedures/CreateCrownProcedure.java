package net.mcreator.reignmod.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.init.ReignModModItems;

public class CreateCrownProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		ItemStack Crown = ItemStack.EMPTY;
		ItemStack Hand = ItemStack.EMPTY;
		ReignModModVariables.MapVariables.get(world).crown_id = ReignModModVariables.MapVariables.get(world).crown_id + 1;
		ReignModModVariables.MapVariables.get(world).syncData(world);
		Crown = new ItemStack(ReignModModItems.CROWN_HELMET.get());
		Crown.enchant(Enchantments.BINDING_CURSE, 10);
		Hand = new ItemStack(ReignModModItems.RIGHT_HAND.get());
		Crown.getOrCreateTag().putDouble("id", ReignModModVariables.MapVariables.get(world).crown_id);
		Hand.getOrCreateTag().putDouble("id", ReignModModVariables.MapVariables.get(world).crown_id);
		if (entity instanceof Player _player) {
			ItemStack _setstack = Crown.copy();
			_setstack.setCount(1);
			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
		if (entity instanceof Player _player) {
			ItemStack _setstack = Hand.copy();
			_setstack.setCount(1);
			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
	}
}
