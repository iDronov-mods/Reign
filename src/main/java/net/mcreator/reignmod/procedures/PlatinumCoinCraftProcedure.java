package net.mcreator.reignmod.procedures;

import net.mcreator.reignmod.init.ReignModModItems;
import net.mcreator.reignmod.network.ReignModModVariables;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class PlatinumCoinCraftProcedure {
	@SubscribeEvent
	public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
		execute(event, event.getEntity().level(), event.getCrafting());
	}

	public static void execute(LevelAccessor world, ItemStack itemstack) {
		execute(null, world, itemstack);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, ItemStack itemstack) {
		if (itemstack.getItem() == ReignModModItems.PLATINUM_COIN.get()) {
			ReignModModVariables.MapVariables.get(world).market_copper_all = ReignModModVariables.MapVariables.get(world).market_copper_all + 4096;
			ReignModModVariables.MapVariables.get(world).syncData(world);
		}
	}
}
