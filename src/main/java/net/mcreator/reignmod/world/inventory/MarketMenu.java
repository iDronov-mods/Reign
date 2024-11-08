
package net.mcreator.reignmod.world.inventory;

import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.procedures.MarketUpdateProcedure;
import net.mcreator.reignmod.procedures.MarketPlaceProcedure;
import net.mcreator.reignmod.procedures.CloseMarketProcedure;
import net.mcreator.reignmod.procedures.ClearMarketSlotsProcedure;
import net.mcreator.reignmod.network.MarketSlotMessage;
import net.mcreator.reignmod.init.ReignModModMenus;
import net.mcreator.reignmod.ReignModMod;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

@Mod.EventBusSubscriber
public class MarketMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
	public final static HashMap<String, Object> guistate = new HashMap<>();
	public final Level world;
	public final Player entity;
	public int x, y, z;
	private ContainerLevelAccess access = ContainerLevelAccess.NULL;
	private IItemHandler internal;
	private final Map<Integer, Slot> customSlots = new HashMap<>();
	private boolean bound = false;
	private Supplier<Boolean> boundItemMatcher = null;
	private Entity boundEntity = null;
	private BlockEntity boundBlockEntity = null;

	public MarketMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(ReignModModMenus.MARKET.get(), id);
		this.entity = inv.player;
		this.world = inv.player.level();
		this.internal = new ItemStackHandler(70);
		BlockPos pos = null;
		if (extraData != null) {
			pos = extraData.readBlockPos();
			this.x = pos.getX();
			this.y = pos.getY();
			this.z = pos.getZ();
			access = ContainerLevelAccess.create(world, pos);
		}
		if (pos != null) {
			if (extraData.readableBytes() == 1) { // bound to item
				byte hand = extraData.readByte();
				ItemStack itemstack = hand == 0 ? this.entity.getMainHandItem() : this.entity.getOffhandItem();
				this.boundItemMatcher = () -> itemstack == (hand == 0 ? this.entity.getMainHandItem() : this.entity.getOffhandItem());
				itemstack.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
					this.internal = capability;
					this.bound = true;
				});
			} else if (extraData.readableBytes() > 1) { // bound to entity
				extraData.readByte(); // drop padding
				boundEntity = world.getEntity(extraData.readVarInt());
				if (boundEntity != null)
					boundEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						this.internal = capability;
						this.bound = true;
					});
			} else { // might be bound to block
				boundBlockEntity = this.world.getBlockEntity(pos);
				if (boundBlockEntity != null)
					boundBlockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						this.internal = capability;
						this.bound = true;
					});
			}
		}
		this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 7, 5) {
			private final int slot = 0;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, 25, 5) {
			private final int slot = 1;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(2, this.addSlot(new SlotItemHandler(internal, 2, 43, 5) {
			private final int slot = 2;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(3, this.addSlot(new SlotItemHandler(internal, 3, 61, 5) {
			private final int slot = 3;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(4, this.addSlot(new SlotItemHandler(internal, 4, 79, 5) {
			private final int slot = 4;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(5, this.addSlot(new SlotItemHandler(internal, 5, 97, 5) {
			private final int slot = 5;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(6, this.addSlot(new SlotItemHandler(internal, 6, 115, 5) {
			private final int slot = 6;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(7, this.addSlot(new SlotItemHandler(internal, 7, 133, 5) {
			private final int slot = 7;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(8, this.addSlot(new SlotItemHandler(internal, 8, 151, 5) {
			private final int slot = 8;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(9, this.addSlot(new SlotItemHandler(internal, 9, 169, 5) {
			private final int slot = 9;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(10, this.addSlot(new SlotItemHandler(internal, 10, 187, 5) {
			private final int slot = 10;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(11, this.addSlot(new SlotItemHandler(internal, 11, 205, 5) {
			private final int slot = 11;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(12, this.addSlot(new SlotItemHandler(internal, 12, 223, 5) {
			private final int slot = 12;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(13, this.addSlot(new SlotItemHandler(internal, 13, 241, 5) {
			private final int slot = 13;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(14, this.addSlot(new SlotItemHandler(internal, 14, 259, 5) {
			private final int slot = 14;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(15, this.addSlot(new SlotItemHandler(internal, 15, 277, 5) {
			private final int slot = 15;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(16, this.addSlot(new SlotItemHandler(internal, 16, 295, 5) {
			private final int slot = 16;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(17, this.addSlot(new SlotItemHandler(internal, 17, 7, 23) {
			private final int slot = 17;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(18, this.addSlot(new SlotItemHandler(internal, 18, 25, 23) {
			private final int slot = 18;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(19, this.addSlot(new SlotItemHandler(internal, 19, 43, 23) {
			private final int slot = 19;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(20, this.addSlot(new SlotItemHandler(internal, 20, 61, 23) {
			private final int slot = 20;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public void onTake(Player entity, ItemStack stack) {
				super.onTake(entity, stack);
				slotChanged(20, 1, 0);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(21, this.addSlot(new SlotItemHandler(internal, 21, 79, 23) {
			private final int slot = 21;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(22, this.addSlot(new SlotItemHandler(internal, 22, 97, 23) {
			private final int slot = 22;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(23, this.addSlot(new SlotItemHandler(internal, 23, 115, 23) {
			private final int slot = 23;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(24, this.addSlot(new SlotItemHandler(internal, 24, 133, 23) {
			private final int slot = 24;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(25, this.addSlot(new SlotItemHandler(internal, 25, 151, 23) {
			private final int slot = 25;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(26, this.addSlot(new SlotItemHandler(internal, 26, 169, 23) {
			private final int slot = 26;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(27, this.addSlot(new SlotItemHandler(internal, 27, 187, 23) {
			private final int slot = 27;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(28, this.addSlot(new SlotItemHandler(internal, 28, 205, 23) {
			private final int slot = 28;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(29, this.addSlot(new SlotItemHandler(internal, 29, 223, 23) {
			private final int slot = 29;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(30, this.addSlot(new SlotItemHandler(internal, 30, 241, 23) {
			private final int slot = 30;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(31, this.addSlot(new SlotItemHandler(internal, 31, 259, 23) {
			private final int slot = 31;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(32, this.addSlot(new SlotItemHandler(internal, 32, 277, 23) {
			private final int slot = 32;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(33, this.addSlot(new SlotItemHandler(internal, 33, 295, 23) {
			private final int slot = 33;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(34, this.addSlot(new SlotItemHandler(internal, 34, 7, 41) {
			private final int slot = 34;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(35, this.addSlot(new SlotItemHandler(internal, 35, 25, 41) {
			private final int slot = 35;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(36, this.addSlot(new SlotItemHandler(internal, 36, 43, 41) {
			private final int slot = 36;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(37, this.addSlot(new SlotItemHandler(internal, 37, 61, 41) {
			private final int slot = 37;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(38, this.addSlot(new SlotItemHandler(internal, 38, 79, 41) {
			private final int slot = 38;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(39, this.addSlot(new SlotItemHandler(internal, 39, 97, 41) {
			private final int slot = 39;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(40, this.addSlot(new SlotItemHandler(internal, 40, 115, 41) {
			private final int slot = 40;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(41, this.addSlot(new SlotItemHandler(internal, 41, 133, 41) {
			private final int slot = 41;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(42, this.addSlot(new SlotItemHandler(internal, 42, 151, 41) {
			private final int slot = 42;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(43, this.addSlot(new SlotItemHandler(internal, 43, 169, 41) {
			private final int slot = 43;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(44, this.addSlot(new SlotItemHandler(internal, 44, 187, 41) {
			private final int slot = 44;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(45, this.addSlot(new SlotItemHandler(internal, 45, 205, 41) {
			private final int slot = 45;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(46, this.addSlot(new SlotItemHandler(internal, 46, 223, 41) {
			private final int slot = 46;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(47, this.addSlot(new SlotItemHandler(internal, 47, 241, 41) {
			private final int slot = 47;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(48, this.addSlot(new SlotItemHandler(internal, 48, 259, 41) {
			private final int slot = 48;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(49, this.addSlot(new SlotItemHandler(internal, 49, 277, 41) {
			private final int slot = 49;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(50, this.addSlot(new SlotItemHandler(internal, 50, 295, 41) {
			private final int slot = 50;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(68, this.addSlot(new SlotItemHandler(internal, 68, 25, 84) {
			private final int slot = 68;

			@Override
			public boolean mayPickup(Player entity) {
				return false;
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(69, this.addSlot(new SlotItemHandler(internal, 69, 97, 84) {
			private final int slot = 69;

			@Override
			public boolean mayPickup(Player entity) {
				return false;
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(51, this.addSlot(new SlotItemHandler(internal, 51, 7, 59) {
			private final int slot = 51;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(52, this.addSlot(new SlotItemHandler(internal, 52, 25, 59) {
			private final int slot = 52;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(53, this.addSlot(new SlotItemHandler(internal, 53, 43, 59) {
			private final int slot = 53;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(54, this.addSlot(new SlotItemHandler(internal, 54, 61, 59) {
			private final int slot = 54;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(55, this.addSlot(new SlotItemHandler(internal, 55, 79, 59) {
			private final int slot = 55;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(56, this.addSlot(new SlotItemHandler(internal, 56, 97, 59) {
			private final int slot = 56;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(57, this.addSlot(new SlotItemHandler(internal, 57, 115, 59) {
			private final int slot = 57;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(58, this.addSlot(new SlotItemHandler(internal, 58, 133, 59) {
			private final int slot = 58;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(59, this.addSlot(new SlotItemHandler(internal, 59, 151, 59) {
			private final int slot = 59;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(60, this.addSlot(new SlotItemHandler(internal, 60, 169, 59) {
			private final int slot = 60;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(61, this.addSlot(new SlotItemHandler(internal, 61, 187, 59) {
			private final int slot = 61;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(62, this.addSlot(new SlotItemHandler(internal, 62, 205, 59) {
			private final int slot = 62;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(63, this.addSlot(new SlotItemHandler(internal, 63, 223, 59) {
			private final int slot = 63;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(64, this.addSlot(new SlotItemHandler(internal, 64, 241, 59) {
			private final int slot = 64;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(65, this.addSlot(new SlotItemHandler(internal, 65, 259, 59) {
			private final int slot = 65;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(66, this.addSlot(new SlotItemHandler(internal, 66, 277, 59) {
			private final int slot = 66;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(67, this.addSlot(new SlotItemHandler(internal, 67, 295, 59) {
			private final int slot = 67;

			@Override
			public boolean mayPickup(Player entity) {
				return !MarketPlaceProcedure.execute(world, entity, slot);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		for (int si = 0; si < 3; ++si)
			for (int sj = 0; sj < 9; ++sj)
				this.addSlot(new Slot(inv, sj + (si + 1) * 9, 143 + 8 + sj * 18, 0 + 84 + si * 18));
		for (int si = 0; si < 9; ++si)
			this.addSlot(new Slot(inv, si, 143 + 8 + si * 18, 0 + 142));
		ClearMarketSlotsProcedure.execute(entity);
	}

	@Override
	public boolean stillValid(Player player) {
		if (this.bound) {
			if (this.boundItemMatcher != null)
				return this.boundItemMatcher.get();
			else if (this.boundBlockEntity != null)
				return AbstractContainerMenu.stillValid(this.access, player, this.boundBlockEntity.getBlockState().getBlock());
			else if (this.boundEntity != null)
				return this.boundEntity.isAlive();
		}
		return true;
	}

	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot) this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index < 70) {
				if (!this.moveItemStackTo(itemstack1, 70, this.slots.size(), true))
					return ItemStack.EMPTY;
				slot.onQuickCraft(itemstack1, itemstack);
			} else if (!this.moveItemStackTo(itemstack1, 0, 70, false)) {
				if (index < 70 + 27) {
					if (!this.moveItemStackTo(itemstack1, 70 + 27, this.slots.size(), true))
						return ItemStack.EMPTY;
				} else {
					if (!this.moveItemStackTo(itemstack1, 70, 70 + 27, false))
						return ItemStack.EMPTY;
				}
				return ItemStack.EMPTY;
			}
			if (itemstack1.getCount() == 0)
				slot.set(ItemStack.EMPTY);
			else
				slot.setChanged();
			if (itemstack1.getCount() == itemstack.getCount())
				return ItemStack.EMPTY;
			slot.onTake(playerIn, itemstack1);
		}
		return itemstack;
	}

	@Override
	protected boolean moveItemStackTo(ItemStack p_38904_, int p_38905_, int p_38906_, boolean p_38907_) {
		boolean flag = false;
		int i = p_38905_;
		if (p_38907_) {
			i = p_38906_ - 1;
		}
		if (p_38904_.isStackable()) {
			while (!p_38904_.isEmpty()) {
				if (p_38907_) {
					if (i < p_38905_) {
						break;
					}
				} else if (i >= p_38906_) {
					break;
				}
				Slot slot = this.slots.get(i);
				ItemStack itemstack = slot.getItem();
				if (slot.mayPlace(itemstack) && !itemstack.isEmpty() && ItemStack.isSameItemSameTags(p_38904_, itemstack)) {
					int j = itemstack.getCount() + p_38904_.getCount();
					int maxSize = Math.min(slot.getMaxStackSize(), p_38904_.getMaxStackSize());
					if (j <= maxSize) {
						p_38904_.setCount(0);
						itemstack.setCount(j);
						slot.set(itemstack);
						flag = true;
					} else if (itemstack.getCount() < maxSize) {
						p_38904_.shrink(maxSize - itemstack.getCount());
						itemstack.setCount(maxSize);
						slot.set(itemstack);
						flag = true;
					}
				}
				if (p_38907_) {
					--i;
				} else {
					++i;
				}
			}
		}
		if (!p_38904_.isEmpty()) {
			if (p_38907_) {
				i = p_38906_ - 1;
			} else {
				i = p_38905_;
			}
			while (true) {
				if (p_38907_) {
					if (i < p_38905_) {
						break;
					}
				} else if (i >= p_38906_) {
					break;
				}
				Slot slot1 = this.slots.get(i);
				ItemStack itemstack1 = slot1.getItem();
				if (itemstack1.isEmpty() && slot1.mayPlace(p_38904_)) {
					if (p_38904_.getCount() > slot1.getMaxStackSize()) {
						slot1.setByPlayer(p_38904_.split(slot1.getMaxStackSize()));
					} else {
						slot1.setByPlayer(p_38904_.split(p_38904_.getCount()));
					}
					slot1.setChanged();
					flag = true;
					break;
				}
				if (p_38907_) {
					--i;
				} else {
					++i;
				}
			}
		}
		return flag;
	}

	@Override
	public void removed(Player playerIn) {
		super.removed(playerIn);
		CloseMarketProcedure.execute(world, x, y, z, entity);
		if (!bound && playerIn instanceof ServerPlayer serverPlayer) {
			if (!serverPlayer.isAlive() || serverPlayer.hasDisconnected()) {
				for (int j = 0; j < internal.getSlots(); ++j) {
					if (j == 0)
						continue;
					if (j == 1)
						continue;
					if (j == 2)
						continue;
					if (j == 3)
						continue;
					if (j == 4)
						continue;
					if (j == 5)
						continue;
					if (j == 6)
						continue;
					if (j == 7)
						continue;
					if (j == 8)
						continue;
					if (j == 9)
						continue;
					if (j == 10)
						continue;
					if (j == 11)
						continue;
					if (j == 12)
						continue;
					if (j == 13)
						continue;
					if (j == 14)
						continue;
					if (j == 15)
						continue;
					if (j == 16)
						continue;
					if (j == 17)
						continue;
					if (j == 18)
						continue;
					if (j == 19)
						continue;
					if (j == 20)
						continue;
					if (j == 21)
						continue;
					if (j == 22)
						continue;
					if (j == 23)
						continue;
					if (j == 24)
						continue;
					if (j == 25)
						continue;
					if (j == 26)
						continue;
					if (j == 27)
						continue;
					if (j == 28)
						continue;
					if (j == 29)
						continue;
					if (j == 30)
						continue;
					if (j == 31)
						continue;
					if (j == 32)
						continue;
					if (j == 33)
						continue;
					if (j == 34)
						continue;
					if (j == 35)
						continue;
					if (j == 36)
						continue;
					if (j == 37)
						continue;
					if (j == 38)
						continue;
					if (j == 39)
						continue;
					if (j == 40)
						continue;
					if (j == 41)
						continue;
					if (j == 42)
						continue;
					if (j == 43)
						continue;
					if (j == 44)
						continue;
					if (j == 45)
						continue;
					if (j == 46)
						continue;
					if (j == 47)
						continue;
					if (j == 48)
						continue;
					if (j == 49)
						continue;
					if (j == 50)
						continue;
					if (j == 68)
						continue;
					playerIn.drop(internal.extractItem(j, internal.getStackInSlot(j).getCount(), false), false);
				}
			} else {
				for (int i = 0; i < internal.getSlots(); ++i) {
					if (i == 0)
						continue;
					if (i == 1)
						continue;
					if (i == 2)
						continue;
					if (i == 3)
						continue;
					if (i == 4)
						continue;
					if (i == 5)
						continue;
					if (i == 6)
						continue;
					if (i == 7)
						continue;
					if (i == 8)
						continue;
					if (i == 9)
						continue;
					if (i == 10)
						continue;
					if (i == 11)
						continue;
					if (i == 12)
						continue;
					if (i == 13)
						continue;
					if (i == 14)
						continue;
					if (i == 15)
						continue;
					if (i == 16)
						continue;
					if (i == 17)
						continue;
					if (i == 18)
						continue;
					if (i == 19)
						continue;
					if (i == 20)
						continue;
					if (i == 21)
						continue;
					if (i == 22)
						continue;
					if (i == 23)
						continue;
					if (i == 24)
						continue;
					if (i == 25)
						continue;
					if (i == 26)
						continue;
					if (i == 27)
						continue;
					if (i == 28)
						continue;
					if (i == 29)
						continue;
					if (i == 30)
						continue;
					if (i == 31)
						continue;
					if (i == 32)
						continue;
					if (i == 33)
						continue;
					if (i == 34)
						continue;
					if (i == 35)
						continue;
					if (i == 36)
						continue;
					if (i == 37)
						continue;
					if (i == 38)
						continue;
					if (i == 39)
						continue;
					if (i == 40)
						continue;
					if (i == 41)
						continue;
					if (i == 42)
						continue;
					if (i == 43)
						continue;
					if (i == 44)
						continue;
					if (i == 45)
						continue;
					if (i == 46)
						continue;
					if (i == 47)
						continue;
					if (i == 48)
						continue;
					if (i == 49)
						continue;
					if (i == 50)
						continue;
					if (i == 68)
						continue;
					playerIn.getInventory().placeItemBackInInventory(internal.extractItem(i, internal.getStackInSlot(i).getCount(), false));
				}
			}
		}
	}

	private void slotChanged(int slotid, int ctype, int meta) {
		if (this.world != null && this.world.isClientSide()) {
			ReignModMod.PACKET_HANDLER.sendToServer(new MarketSlotMessage(slotid, x, y, z, ctype, meta));
			MarketSlotMessage.handleSlotAction(entity, slotid, ctype, meta, x, y, z);
		}
	}

	public Map<Integer, Slot> get() {
		return customSlots;
	}

	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		Player entity = event.player;
		if (event.phase == TickEvent.Phase.END && entity.containerMenu instanceof MarketMenu) {
			Level world = entity.level();
			double x = entity.getX();
			double y = entity.getY();
			double z = entity.getZ();
			MarketUpdateProcedure.execute(world, entity);
		}
	}
}
