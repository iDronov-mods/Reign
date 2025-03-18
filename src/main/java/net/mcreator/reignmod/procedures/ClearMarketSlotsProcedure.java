package net.mcreator.reignmod.procedures;

import net.mcreator.reignmod.market.MarketManager;

public class ClearMarketSlotsProcedure {
	public static void execute() {
		MarketManager.markRefresh();
	}
}
