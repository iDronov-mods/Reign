package net.mcreator.reignmod.procedures;

import net.mcreator.reignmod.kingdom.KingdomManager;
import net.mcreator.reignmod.claim.chunk.Expansion;

public class FeedCapitalCommandProcedure {
	public static void execute() {
		KingdomManager.feedCapital();
		Expansion.expandAll();
	}
}
