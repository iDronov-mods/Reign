package net.mcreator.reignmod.procedures;

import net.mcreator.reignmod.market.MarketManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;
import java.util.function.Supplier;

public class MarketUpdateProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Blocks.OAK_LOG).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:oak_log").intValue());
			((Slot) _slots.get(0)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Blocks.BIRCH_LOG).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:birch_log").intValue());
			((Slot) _slots.get(17)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Blocks.SPRUCE_LOG).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:spruce_log").intValue());
			((Slot) _slots.get(34)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Blocks.JUNGLE_LOG).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:jungle_log").intValue());
			((Slot) _slots.get(51)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Blocks.DARK_OAK_LOG).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:dark_oak_log").intValue());
			((Slot) _slots.get(1)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Blocks.ACACIA_LOG).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:acacia_log").intValue());
			((Slot) _slots.get(18)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Blocks.MANGROVE_LOG).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:mangrove_log").intValue());
			((Slot) _slots.get(35)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Blocks.CHERRY_LOG).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:cherry_log").intValue());
			((Slot) _slots.get(52)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.CHARCOAL).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:charcoal").intValue());
			((Slot) _slots.get(2)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.COAL).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:coal").intValue());
			((Slot) _slots.get(19)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.RAW_COPPER).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:raw_copper").intValue());
			((Slot) _slots.get(3)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.RAW_IRON).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:raw_iron").intValue());
			((Slot) _slots.get(20)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.RAW_GOLD).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:raw_gold").intValue());
			((Slot) _slots.get(37)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.NETHERITE_SCRAP).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:netherite_scrap").intValue());
			((Slot) _slots.get(54)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.DIAMOND).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:diamond").intValue());
			((Slot) _slots.get(4)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.EMERALD).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:emerald").intValue());
			((Slot) _slots.get(21)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.REDSTONE).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:redstone").intValue());
			((Slot) _slots.get(38)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.LAPIS_LAZULI).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:lapis_lazuli").intValue());
			((Slot) _slots.get(55)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.COPPER_INGOT).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:copper_ingot").intValue());
			((Slot) _slots.get(5)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.IRON_INGOT).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:iron_ingot").intValue());
			((Slot) _slots.get(22)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.GOLD_INGOT).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:gold_ingot").intValue());
			((Slot) _slots.get(39)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.NETHERITE_INGOT).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:netherite_ingot").intValue());
			((Slot) _slots.get(56)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.SHEARS).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:shears").intValue());
			((Slot) _slots.get(6)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.BOW).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:bow").intValue());
			((Slot) _slots.get(23)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.ARROW).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:arrow").intValue());
			((Slot) _slots.get(40)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.SHIELD).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:shiled").intValue());
			((Slot) _slots.get(57)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.IRON_HELMET).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:iron_helmet").intValue());
			((Slot) _slots.get(7)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.IRON_CHESTPLATE).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:iron_chestplate").intValue());
			((Slot) _slots.get(24)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.IRON_LEGGINGS).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:iron_leggings").intValue());
			((Slot) _slots.get(41)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.IRON_BOOTS).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:iron_boots").intValue());
			((Slot) _slots.get(58)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.DIAMOND_HELMET).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:diamond_helmet").intValue());
			((Slot) _slots.get(8)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.DIAMOND_CHESTPLATE).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:diamond_chestplate").intValue());
			((Slot) _slots.get(25)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.DIAMOND_LEGGINGS).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:diamond_leggings").intValue());
			((Slot) _slots.get(42)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.DIAMOND_BOOTS).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:diamond_boots").intValue());
			((Slot) _slots.get(59)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.IRON_PICKAXE).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:iron_pickaxe").intValue());
			((Slot) _slots.get(9)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.IRON_AXE).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:iron_axe").intValue());
			((Slot) _slots.get(26)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.IRON_SWORD).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:iron_sword").intValue());
			((Slot) _slots.get(43)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.IRON_SHOVEL).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:iron_shovel").intValue());
			((Slot) _slots.get(60)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.DIAMOND_PICKAXE).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:diamond_pickaxe").intValue());
			((Slot) _slots.get(10)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.DIAMOND_AXE).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:diamond_axe").intValue());
			((Slot) _slots.get(27)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.DIAMOND_SWORD).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:diamond_sword").intValue());
			((Slot) _slots.get(44)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.DIAMOND_SHOVEL).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:diamond_shovel").intValue());
			((Slot) _slots.get(61)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.BREAD).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:bread").intValue());
			((Slot) _slots.get(11)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.BAKED_POTATO).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:baked_potato").intValue());
			((Slot) _slots.get(28)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.PUMPKIN_PIE).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:pumpkin_pie").intValue());
			((Slot) _slots.get(45)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.CARROT).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:carrot").intValue());
			((Slot) _slots.get(62)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.COOKED_BEEF).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:cooked_beef").intValue());
			((Slot) _slots.get(12)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.COOKED_PORKCHOP).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:cooked_porkchop").intValue());
			((Slot) _slots.get(29)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.COOKED_CHICKEN).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:cooked_chicken").intValue());
			((Slot) _slots.get(46)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.COOKED_COD).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:cooked_cod").intValue());
			((Slot) _slots.get(63)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Blocks.COBBLESTONE).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:cobblestone").intValue());
			((Slot) _slots.get(13)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Blocks.SAND).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:sand").intValue());
			((Slot) _slots.get(30)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Blocks.WHITE_WOOL).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:white_wool").intValue());
			((Slot) _slots.get(47)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Blocks.CLAY).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:clay").intValue());
			((Slot) _slots.get(64)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.GLASS_BOTTLE).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:glass_bottle").intValue());
			((Slot) _slots.get(36)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.HONEY_BOTTLE).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:honey_bottle").intValue());
			((Slot) _slots.get(14)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.EXPERIENCE_BOTTLE).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:experience_bottle").intValue());
			((Slot) _slots.get(53)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.BLAZE_ROD).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:blaze_rod").intValue());
			((Slot) _slots.get(48)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.GLOWSTONE_DUST).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:glowstone_dust").intValue());
			((Slot) _slots.get(65)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.BONE).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:bone").intValue());
			((Slot) _slots.get(15)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.STRING).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:string").intValue());
			((Slot) _slots.get(32)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.ENDER_PEARL).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:ender_pearl").intValue());
			((Slot) _slots.get(49)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.GUNPOWDER).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:gunpowder").intValue());
			((Slot) _slots.get(66)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.LEATHER).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:leather").intValue());
			((Slot) _slots.get(16)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.FEATHER).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:feather").intValue());
			((Slot) _slots.get(33)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.PAPER).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:paper").intValue());
			((Slot) _slots.get(50)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Items.SLIME_BALL).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:slime_ball").intValue());
			((Slot) _slots.get(67)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			ItemStack _setstack = new ItemStack(Blocks.HAY_BLOCK).copy();
			_setstack.setCount((int) MarketManager.getItemCount("minecraft:hay_block").intValue());
			((Slot) _slots.get(31)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
	}
}
