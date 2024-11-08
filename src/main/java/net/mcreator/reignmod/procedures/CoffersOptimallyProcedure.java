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

public class CoffersOptimallyProcedure {
	public static void execute(LevelAccessor world) {
		if (new Object() {
			public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
				AtomicInteger _retval = new AtomicInteger(0);
				BlockEntity _ent = world.getBlockEntity(pos);
				if (_ent != null)
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
				return _retval.get();
			}
		}.getAmount(world, BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z), 3) >= 16) {
			while (new Object() {
				public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
					AtomicInteger _retval = new AtomicInteger(0);
					BlockEntity _ent = world.getBlockEntity(pos);
					if (_ent != null)
						_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
					return _retval.get();
				}
			}.getAmount(world, BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z), 3) >= 16) {
				{
					BlockEntity _ent = world.getBlockEntity(BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z));
					if (_ent != null) {
						final int _slotid = 3;
						final int _amount = 16;
						_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
							if (capability instanceof IItemHandlerModifiable) {
								ItemStack _stk = capability.getStackInSlot(_slotid).copy();
								_stk.shrink(_amount);
								((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _stk);
							}
						});
					}
				}
				{
					BlockEntity _ent = world.getBlockEntity(BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z));
					if (_ent != null) {
						final int _slotid = 2;
						final ItemStack _setstack = new ItemStack(ReignModModItems.SILVER_COIN.get()).copy();
						_setstack.setCount((int) (new Object() {
							public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
								AtomicInteger _retval = new AtomicInteger(0);
								BlockEntity _ent = world.getBlockEntity(pos);
								if (_ent != null)
									_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
								return _retval.get();
							}
						}.getAmount(world, BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z), 2) + 1));
						_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
							if (capability instanceof IItemHandlerModifiable)
								((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
						});
					}
				}
			}
		}
		if (new Object() {
			public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
				AtomicInteger _retval = new AtomicInteger(0);
				BlockEntity _ent = world.getBlockEntity(pos);
				if (_ent != null)
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
				return _retval.get();
			}
		}.getAmount(world, BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z), 2) >= 16) {
			while (new Object() {
				public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
					AtomicInteger _retval = new AtomicInteger(0);
					BlockEntity _ent = world.getBlockEntity(pos);
					if (_ent != null)
						_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
					return _retval.get();
				}
			}.getAmount(world, BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z), 2) >= 16) {
				{
					BlockEntity _ent = world.getBlockEntity(BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z));
					if (_ent != null) {
						final int _slotid = 2;
						final int _amount = 16;
						_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
							if (capability instanceof IItemHandlerModifiable) {
								ItemStack _stk = capability.getStackInSlot(_slotid).copy();
								_stk.shrink(_amount);
								((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _stk);
							}
						});
					}
				}
				{
					BlockEntity _ent = world.getBlockEntity(BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z));
					if (_ent != null) {
						final int _slotid = 1;
						final ItemStack _setstack = new ItemStack(ReignModModItems.GOLD_COIN.get()).copy();
						_setstack.setCount((int) (new Object() {
							public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
								AtomicInteger _retval = new AtomicInteger(0);
								BlockEntity _ent = world.getBlockEntity(pos);
								if (_ent != null)
									_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
								return _retval.get();
							}
						}.getAmount(world, BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z), 1) + 1));
						_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
							if (capability instanceof IItemHandlerModifiable)
								((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
						});
					}
				}
			}
		}
		if (new Object() {
			public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
				AtomicInteger _retval = new AtomicInteger(0);
				BlockEntity _ent = world.getBlockEntity(pos);
				if (_ent != null)
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
				return _retval.get();
			}
		}.getAmount(world, BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z), 1) >= 16) {
			while (new Object() {
				public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
					AtomicInteger _retval = new AtomicInteger(0);
					BlockEntity _ent = world.getBlockEntity(pos);
					if (_ent != null)
						_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
					return _retval.get();
				}
			}.getAmount(world, BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z), 1) >= 16) {
				{
					BlockEntity _ent = world.getBlockEntity(BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z));
					if (_ent != null) {
						final int _slotid = 1;
						final int _amount = 16;
						_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
							if (capability instanceof IItemHandlerModifiable) {
								ItemStack _stk = capability.getStackInSlot(_slotid).copy();
								_stk.shrink(_amount);
								((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _stk);
							}
						});
					}
				}
				{
					BlockEntity _ent = world.getBlockEntity(BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z));
					if (_ent != null) {
						final int _slotid = 0;
						final ItemStack _setstack = new ItemStack(ReignModModItems.PLATINUM_COIN.get()).copy();
						_setstack.setCount((int) (new Object() {
							public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
								AtomicInteger _retval = new AtomicInteger(0);
								BlockEntity _ent = world.getBlockEntity(pos);
								if (_ent != null)
									_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
								return _retval.get();
							}
						}.getAmount(world, BlockPos.containing(ReignModModVariables.MapVariables.get(world).VC_X, ReignModModVariables.MapVariables.get(world).VC_Y, ReignModModVariables.MapVariables.get(world).VC_Z), 0) + 1));
						_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
							if (capability instanceof IItemHandlerModifiable)
								((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
						});
					}
				}
			}
		}
	}
}
