package net.mcreator.reignmod.procedures;

import net.mcreator.reignmod.init.ReignModModItems;
import net.mcreator.reignmod.world.inventory.WalletwinMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
		String prefix = "";
		if (itemstack.is(ItemTags.create(new ResourceLocation("reign:coins")))) {
			if (itemstack.getItem() == ReignModModItems.COPPER_COIN.get()) {
				prefix = " \u00A76";
				value = itemstack.getCount();
			} else if (itemstack.getItem() == ReignModModItems.SILVER_COIN.get()) {
				prefix = " \u00A77";
				value = itemstack.getCount() * 16;
			} else if (itemstack.getItem() == ReignModModItems.GOLD_COIN.get()) {
				prefix = " \u00A7e";
				value = itemstack.getCount() * 256;
			} else {
				prefix = " \u00A79";
				value = itemstack.getCount() * 4096;
			}
			if (entity instanceof Player _plr16 && _plr16.containerMenu instanceof WalletwinMenu) {
				if (entity instanceof Player _player)
					_player.closeContainer();
			}
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(
						Component.literal((itemstack.getCount() + " " + prefix + ((itemstack.getDisplayName().getString()).replace("[", "")).replace("]", "") + " \u00A78(" + new java.text.DecimalFormat("##").format(value) + ")\u00A7r")), true);
			WalletGiveProcedure.execute(entity, value);
			SoundGiveCoinProcedure.execute(world, x, y, z);
			itemstack.setCount(0);
		}
	}
}
