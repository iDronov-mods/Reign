package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.init.ReignModModItems;

public class CreateCrownProcedure {
	public static void execute(LevelAccessor world) {
		ItemStack Crown = ItemStack.EMPTY;
		ReignModModVariables.MapVariables.get(world).crown_id = ReignModModVariables.MapVariables.get(world).crown_id + 1;
		ReignModModVariables.MapVariables.get(world).syncData(world);
		Crown = new ItemStack(ReignModModItems.CROWN_HELMET.get());
		Crown.enchant(Enchantments.BINDING_CURSE, 10);
		Crown.getOrCreateTag().putDouble("id", ReignModModVariables.MapVariables.get(world).crown_id);
		if (world instanceof ServerLevel _level) {
			ItemEntity entityToSpawn = new ItemEntity(_level, (ReignModModVariables.MapVariables.get(world).CAPITAL_X + 0.5), (ReignModModVariables.MapVariables.get(world).CAPITAL_Y + 1),
					(ReignModModVariables.MapVariables.get(world).CAPITAL_Z + 0.5), Crown);
			entityToSpawn.setPickUpDelay(60);
			entityToSpawn.setUnlimitedLifetime();
			_level.addFreshEntity(entityToSpawn);
		}
	}
}
