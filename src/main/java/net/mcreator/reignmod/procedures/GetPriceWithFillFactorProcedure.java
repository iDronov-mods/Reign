package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.reignmod.market.MarketManager;

public class GetPriceWithFillFactorProcedure {
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
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				if (goodsName.contains("minecraft:")) {
					goods = goodsName;
				} else {
					goods = "minecraft:" + goodsName;
				}
				price = MarketManager.getPrice(goods);
				max_amount = MarketManager.getMaxAmount(goods);
				min_price = price / 4;
				amount = MarketManager.getItemCount(goods);
				trashhold = max_amount / 4;
				if (amount <= trashhold) {
					totalPrice = price;
				} else {
					totalPrice = price - Math.pow((amount - trashhold) / (max_amount - trashhold), 2) * (price - min_price);
					if (totalPrice < min_price) {
						totalPrice = min_price;
					}
				}
			}
			world = _worldorig;
		}
		return Math.round(totalPrice);
	}
}
