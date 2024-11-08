package net.mcreator.reignmod.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.init.ReignModModItems;

import java.util.concurrent.atomic.AtomicInteger;

public class AddToCoffersProcedure {
	public static void execute(LevelAccessor world, ItemStack type, double value) {
		if (type.getItem() == ReignModModItems.COPPER_COIN.get()) {
			{
				BlockEntity _ent = world.getBlockEntity(BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z));
				if (_ent != null) {
					final int _slotid = 3;
					final ItemStack _setstack = type.copy();
					_setstack.setCount((int) (new Object() {
						public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
							AtomicInteger _retval = new AtomicInteger(0);
							BlockEntity _ent = world.getBlockEntity(pos);
							if (_ent != null)
								_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
							return _retval.get();
						}
					}.getAmount(world, BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z), 3) + value));
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable)
							((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
					});
				}
			}
		} else if (type.getItem() == ReignModModItems.SILVER_COIN.get()) {
			{
				BlockEntity _ent = world.getBlockEntity(BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z));
				if (_ent != null) {
					final int _slotid = 2;
					final ItemStack _setstack = type.copy();
					_setstack.setCount((int) (new Object() {
						public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
							AtomicInteger _retval = new AtomicInteger(0);
							BlockEntity _ent = world.getBlockEntity(pos);
							if (_ent != null)
								_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
							return _retval.get();
						}
					}.getAmount(world, BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z), 2) + value));
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable)
							((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
					});
				}
			}
		} else if (type.getItem() == ReignModModItems.GOLD_COIN.get()) {
			{
				BlockEntity _ent = world.getBlockEntity(BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z));
				if (_ent != null) {
					final int _slotid = 1;
					final ItemStack _setstack = type.copy();
					_setstack.setCount((int) (new Object() {
						public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
							AtomicInteger _retval = new AtomicInteger(0);
							BlockEntity _ent = world.getBlockEntity(pos);
							if (_ent != null)
								_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
							return _retval.get();
						}
					}.getAmount(world, BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z), 1) + value));
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable)
							((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
					});
				}
			}
		} else if (type.getItem() == ReignModModItems.PLATINUM_COIN.get()) {
			{
				BlockEntity _ent = world.getBlockEntity(BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z));
				if (_ent != null) {
					final int _slotid = 0;
					final ItemStack _setstack = type.copy();
					_setstack.setCount((int) (new Object() {
						public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
							AtomicInteger _retval = new AtomicInteger(0);
							BlockEntity _ent = world.getBlockEntity(pos);
							if (_ent != null)
								_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
							return _retval.get();
						}
					}.getAmount(world, BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z), 0) + value));
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable)
							((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
					});
				}
			}
		}
	}
}
