package net.mcreator.reignmod.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.init.ReignModModItems;

import java.util.List;
import java.util.ArrayList;

public class EraResetProcedure {
	public static void execute(LevelAccessor world) {
		ItemStack item = ItemStack.EMPTY;
		ItemStack wallet = ItemStack.EMPTY;
		List<Object> coinsArray = new ArrayList<>();
		double coinsCount = 0;
		double arrayIndex = 0;
		double slotIndex = 0;
		double refund = 0;
		if (ReignModModVariables.MapVariables.get(world).ERA == 0) {
			{
				BlockEntity _ent = world.getBlockEntity(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X, ReignModModVariables.MapVariables.get(world).CAPITAL_Y, ReignModModVariables.MapVariables.get(world).CAPITAL_Z));
				if (_ent != null) {
					final int _slotid = 0;
					final ItemStack _setstack = new ItemStack(ReignModModItems.PLATINUM_COIN.get()).copy();
					_setstack.setCount(1);
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable)
							((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
					});
				}
			}
		} else if (ReignModModVariables.MapVariables.get(world).ERA == 1) {
			{
				BlockEntity _ent = world.getBlockEntity(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X, ReignModModVariables.MapVariables.get(world).CAPITAL_Y, ReignModModVariables.MapVariables.get(world).CAPITAL_Z));
				if (_ent != null) {
					final int _slotid = 0;
					final ItemStack _setstack = new ItemStack(ReignModModItems.PLATINUM_COIN.get()).copy();
					_setstack.setCount(3);
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable)
							((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
					});
				}
			}
		} else if (ReignModModVariables.MapVariables.get(world).ERA == 2) {
			{
				BlockEntity _ent = world.getBlockEntity(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X, ReignModModVariables.MapVariables.get(world).CAPITAL_Y, ReignModModVariables.MapVariables.get(world).CAPITAL_Z));
				if (_ent != null) {
					final int _slotid = 0;
					final ItemStack _setstack = new ItemStack(ReignModModItems.PLATINUM_COIN.get()).copy();
					_setstack.setCount(5);
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable)
							((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
					});
				}
			}
		} else if (ReignModModVariables.MapVariables.get(world).ERA == 3) {
			{
				BlockEntity _ent = world.getBlockEntity(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X, ReignModModVariables.MapVariables.get(world).CAPITAL_Y, ReignModModVariables.MapVariables.get(world).CAPITAL_Z));
				if (_ent != null) {
					final int _slotid = 0;
					final ItemStack _setstack = new ItemStack(ReignModModItems.PLATINUM_COIN.get()).copy();
					_setstack.setCount(10);
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable)
							((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
					});
				}
			}
		} else if (ReignModModVariables.MapVariables.get(world).ERA == 4) {
			{
				BlockEntity _ent = world.getBlockEntity(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X, ReignModModVariables.MapVariables.get(world).CAPITAL_Y, ReignModModVariables.MapVariables.get(world).CAPITAL_Z));
				if (_ent != null) {
					final int _slotid = 0;
					final ItemStack _setstack = new ItemStack(ReignModModItems.PLATINUM_COIN.get()).copy();
					_setstack.setCount(15);
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable)
							((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
					});
				}
			}
		} else if (ReignModModVariables.MapVariables.get(world).ERA == 5) {
			{
				BlockEntity _ent = world.getBlockEntity(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X, ReignModModVariables.MapVariables.get(world).CAPITAL_Y, ReignModModVariables.MapVariables.get(world).CAPITAL_Z));
				if (_ent != null) {
					final int _slotid = 0;
					final ItemStack _setstack = new ItemStack(ReignModModItems.PLATINUM_COIN.get()).copy();
					_setstack.setCount(20);
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable)
							((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
					});
				}
			}
		} else if (ReignModModVariables.MapVariables.get(world).ERA == 6) {
			{
				BlockEntity _ent = world.getBlockEntity(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X, ReignModModVariables.MapVariables.get(world).CAPITAL_Y, ReignModModVariables.MapVariables.get(world).CAPITAL_Z));
				if (_ent != null) {
					final int _slotid = 0;
					final ItemStack _setstack = new ItemStack(ReignModModItems.PLATINUM_COIN.get()).copy();
					_setstack.setCount(25);
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable)
							((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
					});
				}
			}
		} else if (ReignModModVariables.MapVariables.get(world).ERA == 7) {
			{
				BlockEntity _ent = world.getBlockEntity(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X, ReignModModVariables.MapVariables.get(world).CAPITAL_Y, ReignModModVariables.MapVariables.get(world).CAPITAL_Z));
				if (_ent != null) {
					final int _slotid = 0;
					final ItemStack _setstack = new ItemStack(ReignModModItems.PLATINUM_COIN.get()).copy();
					_setstack.setCount(30);
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable)
							((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
					});
				}
			}
		} else if (ReignModModVariables.MapVariables.get(world).ERA == 8) {
			{
				BlockEntity _ent = world.getBlockEntity(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X, ReignModModVariables.MapVariables.get(world).CAPITAL_Y, ReignModModVariables.MapVariables.get(world).CAPITAL_Z));
				if (_ent != null) {
					final int _slotid = 0;
					final ItemStack _setstack = new ItemStack(ReignModModItems.PLATINUM_COIN.get()).copy();
					_setstack.setCount(40);
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable)
							((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
					});
				}
			}
		} else if (ReignModModVariables.MapVariables.get(world).ERA == 9) {
			{
				BlockEntity _ent = world.getBlockEntity(BlockPos.containing(ReignModModVariables.MapVariables.get(world).CAPITAL_X, ReignModModVariables.MapVariables.get(world).CAPITAL_Y, ReignModModVariables.MapVariables.get(world).CAPITAL_Z));
				if (_ent != null) {
					final int _slotid = 0;
					final ItemStack _setstack = new ItemStack(ReignModModItems.PLATINUM_COIN.get()).copy();
					_setstack.setCount(50);
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable)
							((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
					});
				}
			}
		}
	}
}
