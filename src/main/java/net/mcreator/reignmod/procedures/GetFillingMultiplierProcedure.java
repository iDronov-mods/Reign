package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.basics.ConfigLoader;

public class GetFillingMultiplierProcedure {
	public static double execute(LevelAccessor world, String goodsName) {
		if (goodsName == null)
			return 0;
		String goods = "";
		double amount = 0;
		double min_price = 0;
		double totalPrice = 0;
		double price = 0;
		double max_amount = 0;
		double trashhold = 0;
		goods = "minecraft:" + goodsName;
		price = ConfigLoader.getPrice(goods);
		max_amount = ConfigLoader.getMaxAmount(goods);
		min_price = price / 4;
		amount = new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X, ReignModModVariables.MapVariables.get(world).CAPITAL_Y, ReignModModVariables.MapVariables.get(world).CAPITAL_Z), goods);
		trashhold = max_amount / 4;
		if (amount <= trashhold) {
			totalPrice = price;
		} else {
			totalPrice = price - Math.pow((amount - trashhold) / (max_amount - trashhold), 2) * (price - min_price);
			if (totalPrice < min_price) {
				totalPrice = min_price;
			}
		}
		return totalPrice;
	}
}
