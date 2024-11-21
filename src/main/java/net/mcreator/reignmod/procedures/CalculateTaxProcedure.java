package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.reignmod.network.ReignModModVariables;

public class CalculateTaxProcedure {
	public static double execute(LevelAccessor world, Entity Player, Entity entity, double cost, double tax) {
		if (Player == null || entity == null)
			return 0;
		double TAX = 0;
		double k = 0;
		TextMarketTaxProcedure.execute(world, entity);
		if (tax == 0) {
			{
				double _setval = 0;
				Player.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.taxInTransaction = _setval;
					capability.syncPlayerVariables(Player);
				});
			}
			return cost;
		} else {
			TAX = Math.floor((cost / 100) * tax);
			if (TAX >= 1) {
				{
					double _setval = TAX;
					Player.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.taxInTransaction = _setval;
						capability.syncPlayerVariables(Player);
					});
				}
				return cost + TAX;
			}
		}
		{
			double _setval = 0;
			Player.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.taxInTransaction = _setval;
				capability.syncPlayerVariables(Player);
			});
		}
		return cost;
	}
}
