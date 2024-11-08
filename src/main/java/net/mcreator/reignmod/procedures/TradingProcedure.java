package net.mcreator.reignmod.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.gui.components.Checkbox;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.init.ReignModModItems;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

public class TradingProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		double id = 0;
		if (!(guistate.containsKey("checkbox:sale_off") && ((Checkbox) guistate.get("checkbox:sale_off")).selected())) {
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY)
					.is(ItemTags.create(new ResourceLocation("minecraft:logs")))
					&& (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getCount() >= 4) {
				if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Blocks.OAK_LOG.asItem()) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 4, 960, 4, "" + ForgeRegistries.ITEMS.getKey(Blocks.OAK_LOG.asItem()).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Blocks.BIRCH_LOG.asItem()) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 4, 960, 4, "" + ForgeRegistries.ITEMS.getKey(Blocks.BIRCH_LOG.asItem()).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Blocks.SPRUCE_LOG
						.asItem()) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 4, 960, 4, "" + ForgeRegistries.ITEMS.getKey(Blocks.SPRUCE_LOG.asItem()).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Blocks.JUNGLE_LOG
						.asItem()) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 4, 960, 4, "" + ForgeRegistries.ITEMS.getKey(Blocks.JUNGLE_LOG.asItem()).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Blocks.DARK_OAK_LOG
						.asItem()) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 4, 960, 4, "" + ForgeRegistries.ITEMS.getKey(Blocks.DARK_OAK_LOG.asItem()).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Blocks.ACACIA_LOG
						.asItem()) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 4, 960, 4, "" + ForgeRegistries.ITEMS.getKey(Blocks.ACACIA_LOG.asItem()).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Blocks.MANGROVE_LOG
						.asItem()) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 4, 960, 4, "" + ForgeRegistries.ITEMS.getKey(Blocks.MANGROVE_LOG.asItem()).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Blocks.CHERRY_LOG
						.asItem()) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 4, 960, 4, "" + ForgeRegistries.ITEMS.getKey(Blocks.CHERRY_LOG.asItem()).toString());
				}
			} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.CHARCOAL) {
				SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 4, 960, 2, "" + ForgeRegistries.ITEMS.getKey(Items.CHARCOAL).toString());
			} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.COAL) {
				SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 4, 960, 2, "" + ForgeRegistries.ITEMS.getKey(Items.COAL).toString());
			} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY)
					.is(ItemTags.create(new ResourceLocation("reign:smith_items")))
					&& !((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).isDamaged())) {
				if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.IRON_PICKAXE) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.SILVER_COIN.get()), 3, 32, 1, ForgeRegistries.ITEMS.getKey(Items.IRON_PICKAXE).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.IRON_AXE) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.SILVER_COIN.get()), 3, 32, 1, ForgeRegistries.ITEMS.getKey(Items.IRON_AXE).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.IRON_SWORD) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.SILVER_COIN.get()), 2, 32, 1, ForgeRegistries.ITEMS.getKey(Items.IRON_SWORD).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.IRON_SHOVEL) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.SILVER_COIN.get()), 1, 32, 1, ForgeRegistries.ITEMS.getKey(Items.IRON_SHOVEL).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.DIAMOND_PICKAXE) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.GOLD_COIN.get()), 3, 8, 1, ForgeRegistries.ITEMS.getKey(Items.DIAMOND_PICKAXE).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.DIAMOND_AXE) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.GOLD_COIN.get()), 3, 8, 1, ForgeRegistries.ITEMS.getKey(Items.DIAMOND_AXE).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.DIAMOND_SWORD) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.GOLD_COIN.get()), 2, 8, 1, ForgeRegistries.ITEMS.getKey(Items.DIAMOND_SWORD).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.DIAMOND_SHOVEL) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.GOLD_COIN.get()), 1, 8, 1, ForgeRegistries.ITEMS.getKey(Items.DIAMOND_SHOVEL).toString());
				}
				if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.SHEARS) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.SILVER_COIN.get()), 2, 16, 1, ForgeRegistries.ITEMS.getKey(Items.SHEARS).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.BOW) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 20, 16, 1, ForgeRegistries.ITEMS.getKey(Items.BOW).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.SHIELD) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 18, 64, 1, ForgeRegistries.ITEMS.getKey(Items.SHIELD).toString());
				}
			}
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() instanceof ArmorItem
					&& !((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).isDamaged())) {
				if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.IRON_HELMET) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.SILVER_COIN.get()), 5, 16, 1, "" + ForgeRegistries.ITEMS.getKey(Items.IRON_HELMET).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.IRON_CHESTPLATE) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.SILVER_COIN.get()), 8, 16, 1, "" + ForgeRegistries.ITEMS.getKey(Items.IRON_CHESTPLATE).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.IRON_LEGGINGS) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.SILVER_COIN.get()), 7, 16, 1, "" + ForgeRegistries.ITEMS.getKey(Items.IRON_LEGGINGS).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.IRON_BOOTS) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.SILVER_COIN.get()), 4, 16, 1, "" + ForgeRegistries.ITEMS.getKey(Items.IRON_BOOTS).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.DIAMOND_HELMET) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.GOLD_COIN.get()), 5, 8, 1, "" + ForgeRegistries.ITEMS.getKey(Items.DIAMOND_HELMET).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.DIAMOND_CHESTPLATE) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.GOLD_COIN.get()), 8, 8, 1, "" + ForgeRegistries.ITEMS.getKey(Items.DIAMOND_CHESTPLATE).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.DIAMOND_LEGGINGS) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.GOLD_COIN.get()), 7, 8, 1, "" + ForgeRegistries.ITEMS.getKey(Items.DIAMOND_LEGGINGS).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.DIAMOND_BOOTS) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.GOLD_COIN.get()), 4, 8, 1, "" + ForgeRegistries.ITEMS.getKey(Items.DIAMOND_BOOTS).toString());
				}
			} else {
				if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.IRON_INGOT) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 16, 960, 1, "" + ForgeRegistries.ITEMS.getKey(Items.IRON_INGOT).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.GOLD_INGOT) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.SILVER_COIN.get()), 2, 960, 1, "" + ForgeRegistries.ITEMS.getKey(Items.GOLD_INGOT).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.NETHERITE_INGOT) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.GOLD_COIN.get()), 10, 960, 1, "" + ForgeRegistries.ITEMS.getKey(Items.NETHERITE_INGOT).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.COPPER_INGOT) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 4, 960, 1, ForgeRegistries.ITEMS.getKey(Items.COPPER_INGOT).toString());
				}
				if (ReignModModVariables.MapVariables.get(world).ERA >= 2) {
					if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.BONE) {
						SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 4, 480, 1, "" + ForgeRegistries.ITEMS.getKey(Items.BONE).toString());
					} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.LEATHER) {
						SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 8, 480, 1, "" + ForgeRegistries.ITEMS.getKey(Items.LEATHER).toString());
					} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.FEATHER) {
						SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 4, 480, 1, "" + ForgeRegistries.ITEMS.getKey(Items.FEATHER).toString());
					} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.STRING) {
						SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 4, 480, 1, "" + ForgeRegistries.ITEMS.getKey(Items.STRING).toString());
					} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.GUNPOWDER) {
						SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 12, 480, 1, "" + ForgeRegistries.ITEMS.getKey(Items.GUNPOWDER).toString());
					} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Blocks.WHITE_WOOL
							.asItem()) {
						SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 4, 960, 1, "" + ForgeRegistries.ITEMS.getKey(Blocks.WHITE_WOOL.asItem()).toString());
					} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.GLOWSTONE_DUST) {
						SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 12, 960, 1, "" + ForgeRegistries.ITEMS.getKey(Items.GLOWSTONE_DUST).toString());
					}
					if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.EXPERIENCE_BOTTLE) {
						SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 12, 960, 1, "" + ForgeRegistries.ITEMS.getKey(Items.EXPERIENCE_BOTTLE).toString());
					} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.HONEY_BOTTLE) {
						SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 16, 120, 1, "" + ForgeRegistries.ITEMS.getKey(Items.HONEY_BOTTLE).toString());
					} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.PAPER) {
						SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 4, 960, 3, "" + ForgeRegistries.ITEMS.getKey(Items.PAPER).toString());
					} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.ENDER_PEARL) {
						SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.SILVER_COIN.get()), 4, 960, 1, "" + ForgeRegistries.ITEMS.getKey(Items.ENDER_PEARL).toString());
					} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.SLIME_BALL) {
						SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 12, 960, 1, "" + ForgeRegistries.ITEMS.getKey(Items.SLIME_BALL).toString());
					}
				}
			}
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem().isEdible()
					&& (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getCount() >= 1) {
				if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.COOKED_PORKCHOP) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 4, 960, 1, "" + ForgeRegistries.ITEMS.getKey(Items.COOKED_PORKCHOP).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.COOKED_BEEF) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 4, 960, 1, "" + ForgeRegistries.ITEMS.getKey(Items.COOKED_BEEF).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.COOKED_CHICKEN) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 3, 960, 1, "" + ForgeRegistries.ITEMS.getKey(Items.COOKED_CHICKEN).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.BREAD) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 2, 960, 1, "" + ForgeRegistries.ITEMS.getKey(Items.BREAD).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.BAKED_POTATO) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 2, 960, 1, "" + ForgeRegistries.ITEMS.getKey(Items.BAKED_POTATO).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.COOKED_MUTTON) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 3, 960, 1, "" + ForgeRegistries.ITEMS.getKey(Items.COOKED_MUTTON).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.PUMPKIN_PIE) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 4, 960, 1, "" + ForgeRegistries.ITEMS.getKey(Items.PUMPKIN_PIE).toString());
				} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.COOKIE) {
					SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 4, 960, 4, "" + ForgeRegistries.ITEMS.getKey(Items.COOKIE).toString());
				}
			}
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.RAW_COPPER) {
				SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 3, 960, 1, "" + ForgeRegistries.ITEMS.getKey(Items.RAW_COPPER).toString());
			} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.RAW_IRON) {
				SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 12, 960, 1, "" + ForgeRegistries.ITEMS.getKey(Items.RAW_IRON).toString());
			} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.RAW_GOLD) {
				SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 28, 960, 1, "" + ForgeRegistries.ITEMS.getKey(Items.RAW_GOLD).toString());
			} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.NETHERITE_SCRAP) {
				SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.SILVER_COIN.get()), 32, 960, 1, "" + ForgeRegistries.ITEMS.getKey(Items.NETHERITE_SCRAP).toString());
			} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.DIAMOND) {
				SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.SILVER_COIN.get()), 10, 960, 1, "" + ForgeRegistries.ITEMS.getKey(Items.DIAMOND).toString());
			} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.EMERALD) {
				SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.SILVER_COIN.get()), 12, 960, 1, "" + ForgeRegistries.ITEMS.getKey(Items.EMERALD).toString());
			} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.REDSTONE) {
				SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 2, 960, 1, ForgeRegistries.ITEMS.getKey(Items.REDSTONE).toString());
			} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.LAPIS_LAZULI) {
				SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 4, 960, 1, ForgeRegistries.ITEMS.getKey(Items.LAPIS_LAZULI).toString());
			}
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Blocks.COBBLESTONE.asItem()) {
				SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 4, 960, 4, "" + ForgeRegistries.ITEMS.getKey(Blocks.COBBLESTONE.asItem()).toString());
			} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Blocks.SAND.asItem()) {
				SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 4, 960, 4, "" + ForgeRegistries.ITEMS.getKey(Blocks.SAND.asItem()).toString());
			} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.ARROW) {
				SellingProcedure.execute(world, x, y, z, entity, new ItemStack(ReignModModItems.COPPER_COIN.get()), 4, 960, 2, ForgeRegistries.ITEMS.getKey(Items.ARROW).toString());
			}
		}
	}
}
