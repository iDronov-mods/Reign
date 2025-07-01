package net.mcreator.reignmod.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.market.MarketManager;

import java.util.function.Supplier;
import java.util.Map;

public class MarketCountSetProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		String goods_name = "";
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(68)).getItem() : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem()) {
					{
						String _setval = "";
						entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.market_goods_count = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				} else {
					goods_name = ForgeRegistries.ITEMS
							.getKey((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(68)).getItem() : ItemStack.EMPTY).getItem()).toString();
					{
						String _setval = new java.text.DecimalFormat("##").format(MarketManager.getItemCount(goods_name)) + "\u00A78/" + new java.text.DecimalFormat("##").format(MarketManager.getMaxAmount(goods_name));
						entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.market_goods_count = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
			}
			world = _worldorig;
		}
	}
}
