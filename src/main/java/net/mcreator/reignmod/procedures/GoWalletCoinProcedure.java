package net.mcreator.reignmod.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.reignmod.world.inventory.WalletwinMenu;
import net.mcreator.reignmod.init.ReignModModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class GoWalletCoinProcedure {
	@SubscribeEvent
	public static void onPickup(EntityItemPickupEvent event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity(), event.getItem().getItem());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		execute(null, world, x, y, z, entity, itemstack);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		ItemStack wallet_copy = ItemStack.EMPTY;
		double index = 0;
		double value = 0;
		if (itemstack.is(ItemTags.create(new ResourceLocation("reign:coins")))) {
			if (itemstack.getItem() == ReignModModItems.COPPER_COIN.get()) {
				value = itemstack.getCount();
			} else if (itemstack.getItem() == ReignModModItems.SILVER_COIN.get()) {
				value = itemstack.getCount() * 16;
			} else if (itemstack.getItem() == ReignModModItems.GOLD_COIN.get()) {
				value = itemstack.getCount() * 256;
			} else {
				value = itemstack.getCount() * 4096;
			}
			if (entity instanceof Player _plr16 && _plr16.containerMenu instanceof WalletwinMenu) {
				if (entity instanceof Player _player)
					_player.closeContainer();
			}
			WalletGiveProcedure.execute(entity, value);
			SoundGiveCoinProcedure.execute(world, x, y, z);
			itemstack.setCount(0);
		}
	}
}
