package net.mcreator.reignmod.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.network.ReignModModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class RemoveBookEnchantProcedure {
	@SubscribeEvent
	public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
		if (event.getHand() != event.getEntity().getUsedItemHand())
			return;
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		ItemStack book = ItemStack.EMPTY;
		double exp = 0;
		double value = 0;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() instanceof EnchantedBookItem) {
			if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).license_librarian
					&& (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).ADD_LVL >= 2) {
				book = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
				ListTag enchants = book.getTag().getList("StoredEnchantments", 10);
				for (int i = 0; i < enchants.size(); i++) {
					CompoundTag enchantment = enchants.getCompound(i);
					exp += enchantment.getInt("lvl");
				}
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("" + exp)), false);
				value = Mth.nextInt(RandomSource.create(), 8, 32) * exp;
				if (entity instanceof Player _player)
					_player.giveExperiencePoints((int) value);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.chiseled_bookshelf.insert.enchanted")), SoundSource.PLAYERS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.chiseled_bookshelf.insert.enchanted")), SoundSource.PLAYERS, 1, 1, false);
					}
				}
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal((Component.translatable("translation.key.remove_book_enchant").getString() + " " + new java.text.DecimalFormat("##").format(value))), false);
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack = new ItemStack(Items.BOOK).copy();
					_setstack.setCount(1);
					_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
					if (_entity instanceof Player _player)
						_player.getInventory().setChanged();
				}
			}
		}
	}
}
