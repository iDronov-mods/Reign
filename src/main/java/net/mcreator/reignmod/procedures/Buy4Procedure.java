package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;

public class Buy4Procedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		boolean isHave = false;
		ItemStack item = ItemStack.EMPTY;
		ItemStack Type_coin = ItemStack.EMPTY;
		double shopIndex = 0;
		double slotIndex = 0;
		for (int index0 = 0; index0 < 4; index0++) {
			BuyProcedure.execute(world, entity);
		}
	}
}
