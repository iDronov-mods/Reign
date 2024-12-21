package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.init.ReignModModItems;

import java.util.function.Supplier;
import java.util.Map;

public class MarketPlaceProcedure {
	public static boolean execute(LevelAccessor world, Entity entity, double slot) {
		if (entity == null)
			return false;
		if (slot == 0) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Blocks.OAK_LOG).copy();
				_setstack.setCount(4);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).logs_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 17) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Blocks.BIRCH_LOG).copy();
				_setstack.setCount(4);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).logs_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 34) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Blocks.SPRUCE_LOG).copy();
				_setstack.setCount(4);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).logs_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 51) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Blocks.JUNGLE_LOG).copy();
				_setstack.setCount(4);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).logs_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 2) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.CHARCOAL).copy();
				_setstack.setCount(2);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).coal_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 19) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.COAL).copy();
				_setstack.setCount(2);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).coal_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 9) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.IRON_PICKAXE).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.SILVER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 3, ReignModModVariables.MapVariables.get(world).tools_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 26) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.IRON_AXE).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.SILVER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 3, ReignModModVariables.MapVariables.get(world).tools_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 43) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.IRON_SWORD).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.SILVER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 2, ReignModModVariables.MapVariables.get(world).tools_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 60) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.IRON_SHOVEL).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.SILVER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 1, ReignModModVariables.MapVariables.get(world).tools_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 7) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.IRON_HELMET).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.SILVER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 5, ReignModModVariables.MapVariables.get(world).armor_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 24) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.IRON_CHESTPLATE).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.SILVER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 8, ReignModModVariables.MapVariables.get(world).armor_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 41) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.IRON_LEGGINGS).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.SILVER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 7, ReignModModVariables.MapVariables.get(world).armor_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 58) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.IRON_BOOTS).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.SILVER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).armor_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 10) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.DIAMOND_PICKAXE).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.GOLD_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 3, ReignModModVariables.MapVariables.get(world).tools_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 27) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.DIAMOND_AXE).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.GOLD_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 3, ReignModModVariables.MapVariables.get(world).tools_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 44) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.DIAMOND_SWORD).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.GOLD_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 2, ReignModModVariables.MapVariables.get(world).tools_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 61) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.DIAMOND_SHOVEL).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.GOLD_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 1, ReignModModVariables.MapVariables.get(world).tools_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 8) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.DIAMOND_HELMET).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.GOLD_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 5, ReignModModVariables.MapVariables.get(world).armor_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 25) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.DIAMOND_CHESTPLATE).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.GOLD_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 8, ReignModModVariables.MapVariables.get(world).armor_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 42) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.DIAMOND_LEGGINGS).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.GOLD_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 7, ReignModModVariables.MapVariables.get(world).armor_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 59) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.DIAMOND_BOOTS).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.GOLD_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).armor_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 22) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.IRON_INGOT).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 16, ReignModModVariables.MapVariables.get(world).ores_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 4) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.DIAMOND).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.SILVER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 10, ReignModModVariables.MapVariables.get(world).ores_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 39) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.GOLD_INGOT).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.SILVER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 2, ReignModModVariables.MapVariables.get(world).ores_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 38) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.REDSTONE).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 2, ReignModModVariables.MapVariables.get(world).ores_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 56) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.NETHERITE_INGOT).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.GOLD_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 10, ReignModModVariables.MapVariables.get(world).ores_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 65) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.GLOWSTONE_DUST).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 12, ReignModModVariables.MapVariables.get(world).ores_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		}
		if (slot == 1) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Blocks.DARK_OAK_LOG).copy();
				_setstack.setCount(4);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).logs_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 18) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Blocks.ACACIA_LOG).copy();
				_setstack.setCount(4);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).logs_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 35) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Blocks.MANGROVE_LOG).copy();
				_setstack.setCount(4);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).logs_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 52) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Blocks.CHERRY_LOG).copy();
				_setstack.setCount(4);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).logs_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 36) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Blocks.COBBLESTONE).copy();
				_setstack.setCount(4);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).other_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 53) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Blocks.SAND).copy();
				_setstack.setCount(4);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).other_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		}
		if (slot == 5) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.COPPER_INGOT).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).ores_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 55) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.LAPIS_LAZULI).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).ores_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 50) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.PAPER).copy();
				_setstack.setCount(3);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).other_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 49) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.ENDER_PEARL).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.SILVER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).other_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 21) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.EMERALD).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.SILVER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 12, ReignModModVariables.MapVariables.get(world).ores_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		}
		if (slot == 15) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.BONE).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).other_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 16) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.LEATHER).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 8, ReignModModVariables.MapVariables.get(world).other_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 33) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.FEATHER).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).other_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 66) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.GUNPOWDER).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 12, ReignModModVariables.MapVariables.get(world).other_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 32) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.STRING).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).other_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 48) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Blocks.WHITE_WOOL).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).other_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 31) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.EXPERIENCE_BOTTLE).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 12, ReignModModVariables.MapVariables.get(world).other_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 67) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.SLIME_BALL).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 12, ReignModModVariables.MapVariables.get(world).other_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		}
		if (slot == 3) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.RAW_COPPER).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 3, ReignModModVariables.MapVariables.get(world).ores_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 20) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.RAW_IRON).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 12, ReignModModVariables.MapVariables.get(world).ores_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 37) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.RAW_GOLD).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 28, ReignModModVariables.MapVariables.get(world).ores_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 54) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.NETHERITE_SCRAP).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.SILVER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 32, ReignModModVariables.MapVariables.get(world).ores_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 6) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.SHEARS).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.SILVER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 2, ReignModModVariables.MapVariables.get(world).tools_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 23) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.BOW).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 20, ReignModModVariables.MapVariables.get(world).tools_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 57) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.SHIELD).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 18, ReignModModVariables.MapVariables.get(world).tools_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 40) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.ARROW).copy();
				_setstack.setCount(2);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).other_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		}
		if (slot == 29) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.COOKED_PORKCHOP).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).food_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 12) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.COOKED_BEEF).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).food_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 46) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.COOKED_CHICKEN).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 3, ReignModModVariables.MapVariables.get(world).food_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 11) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.BREAD).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 2, ReignModModVariables.MapVariables.get(world).food_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 28) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.BAKED_POTATO).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 2, ReignModModVariables.MapVariables.get(world).food_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 63) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.COOKED_MUTTON).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 3, ReignModModVariables.MapVariables.get(world).food_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 14) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.HONEY_BOTTLE).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 16, ReignModModVariables.MapVariables.get(world).food_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 45) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.PUMPKIN_PIE).copy();
				_setstack.setCount(1);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).food_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		} else if (slot == 62) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(Items.COOKIE).copy();
				_setstack.setCount(4);
				((Slot) _slots.get(69)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
				_setstack.setCount((int) CalculateTaxProcedure.execute(world, entity, entity, 4, ReignModModVariables.MapVariables.get(world).food_price));
				((Slot) _slots.get(68)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		}
		SlotUpdateProcedure.execute(entity);
		return true;
	}
}
