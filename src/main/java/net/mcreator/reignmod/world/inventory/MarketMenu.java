
package net.mcreator.reignmod.world.inventory;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.procedures.MarketUpdateTickProcedure;
import net.mcreator.reignmod.procedures.MarketPlaceProcedure;
import net.mcreator.reignmod.procedures.ClearMarketSlotsProcedure;
import net.mcreator.reignmod.network.MarketSlotMessage;
import net.mcreator.reignmod.init.ReignModModMenus;
import net.mcreator.reignmod.client.gui.MarketScreen;
import net.mcreator.reignmod.ReignModMod;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

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
		this.internal = new ItemStackHandler(73);
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
		this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 5, 5) {
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
		this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, 23, 5) {
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
		this.customSlots.put(2, this.addSlot(new SlotItemHandler(internal, 2, 42, 5) {
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
		this.customSlots.put(6, this.addSlot(new SlotItemHandler(internal, 6, 116, 5) {
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
		this.customSlots.put(7, this.addSlot(new SlotItemHandler(internal, 7, 134, 5) {
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
		this.customSlots.put(8, this.addSlot(new SlotItemHandler(internal, 8, 152, 5) {
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
		this.customSlots.put(9, this.addSlot(new SlotItemHandler(internal, 9, 171, 5) {
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
		this.customSlots.put(10, this.addSlot(new SlotItemHandler(internal, 10, 189, 5) {
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
		this.customSlots.put(11, this.addSlot(new SlotItemHandler(internal, 11, 208, 5) {
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
		this.customSlots.put(12, this.addSlot(new SlotItemHandler(internal, 12, 226, 5) {
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
		this.customSlots.put(13, this.addSlot(new SlotItemHandler(internal, 13, 245, 5) {
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
		this.customSlots.put(14, this.addSlot(new SlotItemHandler(internal, 14, 264, 5) {
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
		this.customSlots.put(15, this.addSlot(new SlotItemHandler(internal, 15, 282, 5) {
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
		this.customSlots.put(16, this.addSlot(new SlotItemHandler(internal, 16, 300, 5) {
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
		this.customSlots.put(17, this.addSlot(new SlotItemHandler(internal, 17, 5, 23) {
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
		this.customSlots.put(18, this.addSlot(new SlotItemHandler(internal, 18, 23, 23) {
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
		this.customSlots.put(19, this.addSlot(new SlotItemHandler(internal, 19, 42, 23) {
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
		this.customSlots.put(23, this.addSlot(new SlotItemHandler(internal, 23, 116, 23) {
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
		this.customSlots.put(24, this.addSlot(new SlotItemHandler(internal, 24, 134, 23) {
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
		this.customSlots.put(25, this.addSlot(new SlotItemHandler(internal, 25, 152, 23) {
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
		this.customSlots.put(26, this.addSlot(new SlotItemHandler(internal, 26, 171, 23) {
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
		this.customSlots.put(27, this.addSlot(new SlotItemHandler(internal, 27, 189, 23) {
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
		this.customSlots.put(28, this.addSlot(new SlotItemHandler(internal, 28, 208, 23) {
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
		this.customSlots.put(29, this.addSlot(new SlotItemHandler(internal, 29, 226, 23) {
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
		this.customSlots.put(30, this.addSlot(new SlotItemHandler(internal, 30, 245, 23) {
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
		this.customSlots.put(31, this.addSlot(new SlotItemHandler(internal, 31, 264, 23) {
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
		this.customSlots.put(32, this.addSlot(new SlotItemHandler(internal, 32, 282, 23) {
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
		this.customSlots.put(33, this.addSlot(new SlotItemHandler(internal, 33, 300, 23) {
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
		this.customSlots.put(34, this.addSlot(new SlotItemHandler(internal, 34, 5, 41) {
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
		this.customSlots.put(35, this.addSlot(new SlotItemHandler(internal, 35, 23, 41) {
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
		this.customSlots.put(36, this.addSlot(new SlotItemHandler(internal, 36, 42, 41) {
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
		this.customSlots.put(40, this.addSlot(new SlotItemHandler(internal, 40, 116, 41) {
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
		this.customSlots.put(41, this.addSlot(new SlotItemHandler(internal, 41, 134, 41) {
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
		this.customSlots.put(42, this.addSlot(new SlotItemHandler(internal, 42, 152, 41) {
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
		this.customSlots.put(43, this.addSlot(new SlotItemHandler(internal, 43, 171, 41) {
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
		this.customSlots.put(44, this.addSlot(new SlotItemHandler(internal, 44, 189, 41) {
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
		this.customSlots.put(45, this.addSlot(new SlotItemHandler(internal, 45, 208, 41) {
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
		this.customSlots.put(46, this.addSlot(new SlotItemHandler(internal, 46, 226, 41) {
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
		this.customSlots.put(47, this.addSlot(new SlotItemHandler(internal, 47, 245, 41) {
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
		this.customSlots.put(48, this.addSlot(new SlotItemHandler(internal, 48, 264, 41) {
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
		this.customSlots.put(49, this.addSlot(new SlotItemHandler(internal, 49, 282, 41) {
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
		this.customSlots.put(50, this.addSlot(new SlotItemHandler(internal, 50, 300, 41) {
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
		this.customSlots.put(51, this.addSlot(new SlotItemHandler(internal, 51, 5, 59) {
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
		this.customSlots.put(52, this.addSlot(new SlotItemHandler(internal, 52, 23, 59) {
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
		this.customSlots.put(53, this.addSlot(new SlotItemHandler(internal, 53, 42, 59) {
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
		this.customSlots.put(57, this.addSlot(new SlotItemHandler(internal, 57, 116, 59) {
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
		this.customSlots.put(58, this.addSlot(new SlotItemHandler(internal, 58, 134, 59) {
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
		this.customSlots.put(59, this.addSlot(new SlotItemHandler(internal, 59, 152, 59) {
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
		this.customSlots.put(60, this.addSlot(new SlotItemHandler(internal, 60, 171, 59) {
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
		this.customSlots.put(61, this.addSlot(new SlotItemHandler(internal, 61, 189, 59) {
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
		this.customSlots.put(62, this.addSlot(new SlotItemHandler(internal, 62, 208, 59) {
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
		this.customSlots.put(63, this.addSlot(new SlotItemHandler(internal, 63, 226, 59) {
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
		this.customSlots.put(64, this.addSlot(new SlotItemHandler(internal, 64, 245, 59) {
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
		this.customSlots.put(65, this.addSlot(new SlotItemHandler(internal, 65, 264, 59) {
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
		this.customSlots.put(66, this.addSlot(new SlotItemHandler(internal, 66, 282, 59) {
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
		this.customSlots.put(67, this.addSlot(new SlotItemHandler(internal, 67, 300, 59) {
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
		this.customSlots.put(69, this.addSlot(new SlotItemHandler(internal, 69, 15, 102) {
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
		this.customSlots.put(70, this.addSlot(new SlotItemHandler(internal, 70, 33, 102) {
			private final int slot = 70;

			@Override
			public boolean mayPickup(Player entity) {
				return false;
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(71, this.addSlot(new SlotItemHandler(internal, 71, 51, 102) {
			private final int slot = 71;

			@Override
			public boolean mayPickup(Player entity) {
				return false;
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(72, this.addSlot(new SlotItemHandler(internal, 72, 69, 102) {
			private final int slot = 72;

			@Override
			public boolean mayPickup(Player entity) {
				return false;
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(68, this.addSlot(new SlotItemHandler(internal, 68, 115, 102) {
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
		for (int si = 0; si < 3; ++si)
			for (int sj = 0; sj < 9; ++sj)
				this.addSlot(new Slot(inv, sj + (si + 1) * 9, 144 + 8 + sj * 18, 0 + 84 + si * 18));
		for (int si = 0; si < 9; ++si)
			this.addSlot(new Slot(inv, si, 144 + 8 + si * 18, 0 + 142));
		ClearMarketSlotsProcedure.execute();
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
			if (index < 73) {
				if (!this.moveItemStackTo(itemstack1, 73, this.slots.size(), true))
					return ItemStack.EMPTY;
				slot.onQuickCraft(itemstack1, itemstack);
			} else if (!this.moveItemStackTo(itemstack1, 0, 73, false)) {
				if (index < 73 + 27) {
					if (!this.moveItemStackTo(itemstack1, 73 + 27, this.slots.size(), true))
						return ItemStack.EMPTY;
				} else {
					if (!this.moveItemStackTo(itemstack1, 73, 73 + 27, false))
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
					if (j == 51)
						continue;
					if (j == 52)
						continue;
					if (j == 53)
						continue;
					if (j == 54)
						continue;
					if (j == 55)
						continue;
					if (j == 56)
						continue;
					if (j == 57)
						continue;
					if (j == 58)
						continue;
					if (j == 59)
						continue;
					if (j == 60)
						continue;
					if (j == 61)
						continue;
					if (j == 62)
						continue;
					if (j == 63)
						continue;
					if (j == 64)
						continue;
					if (j == 65)
						continue;
					if (j == 66)
						continue;
					if (j == 67)
						continue;
					if (j == 69)
						continue;
					if (j == 70)
						continue;
					if (j == 71)
						continue;
					if (j == 72)
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
					if (i == 51)
						continue;
					if (i == 52)
						continue;
					if (i == 53)
						continue;
					if (i == 54)
						continue;
					if (i == 55)
						continue;
					if (i == 56)
						continue;
					if (i == 57)
						continue;
					if (i == 58)
						continue;
					if (i == 59)
						continue;
					if (i == 60)
						continue;
					if (i == 61)
						continue;
					if (i == 62)
						continue;
					if (i == 63)
						continue;
					if (i == 64)
						continue;
					if (i == 65)
						continue;
					if (i == 66)
						continue;
					if (i == 67)
						continue;
					if (i == 69)
						continue;
					if (i == 70)
						continue;
					if (i == 71)
						continue;
					if (i == 72)
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
			ReignModMod.PACKET_HANDLER.sendToServer(new MarketSlotMessage(slotid, x, y, z, ctype, meta, MarketScreen.getTextboxValues()));
			MarketSlotMessage.handleSlotAction(entity, slotid, ctype, meta, x, y, z, MarketScreen.getTextboxValues());
		}
	}

	public Map<Integer, Slot> get() {
		return customSlots;
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class MarketOtherMessage {
		private final int mode, x, y, z;
		private HashMap<String, String> textstate;

		public MarketOtherMessage(FriendlyByteBuf buffer) {
			this.mode = buffer.readInt();
			this.x = buffer.readInt();
			this.y = buffer.readInt();
			this.z = buffer.readInt();
			this.textstate = readTextState(buffer);
		}

		public MarketOtherMessage(int mode, int x, int y, int z, HashMap<String, String> textstate) {
			this.mode = mode;
			this.x = x;
			this.y = y;
			this.z = z;
			this.textstate = textstate;
		}

		public static void buffer(MarketOtherMessage message, FriendlyByteBuf buffer) {
			buffer.writeInt(message.mode);
			buffer.writeInt(message.x);
			buffer.writeInt(message.y);
			buffer.writeInt(message.z);
			writeTextState(message.textstate, buffer);
		}

		public static void handler(MarketOtherMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				Player entity = context.getSender();
				int mode = message.mode;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				HashMap<String, String> textstate = message.textstate;
				handleOtherAction(entity, mode, x, y, z, textstate);
			});
			context.setPacketHandled(true);
		}

		public static void handleOtherAction(Player entity, int mode, int x, int y, int z, HashMap<String, String> textstate) {
			Level world = entity.level();
			HashMap guistate = MarketMenu.guistate;
			for (Map.Entry<String, String> entry : textstate.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				guistate.put(key, value);
			}
			// security measure to prevent arbitrary chunk generation
			if (!world.hasChunkAt(new BlockPos(x, y, z)))
				return;
			if (mode == 0) {
				MarketUpdateTickProcedure.execute(world, entity);
			}
		}

		@SubscribeEvent
		public static void registerMessage(FMLCommonSetupEvent event) {
			ReignModMod.addNetworkMessage(MarketOtherMessage.class, MarketOtherMessage::buffer, MarketOtherMessage::new, MarketOtherMessage::handler);
		}

		public static void writeTextState(HashMap<String, String> map, FriendlyByteBuf buffer) {
			buffer.writeInt(map.size());
			for (Map.Entry<String, String> entry : map.entrySet()) {
				buffer.writeComponent(Component.literal(entry.getKey()));
				buffer.writeComponent(Component.literal(entry.getValue()));
			}
		}

		public static HashMap<String, String> readTextState(FriendlyByteBuf buffer) {
			int size = buffer.readInt();
			HashMap<String, String> map = new HashMap<>();
			for (int i = 0; i < size; i++) {
				String key = buffer.readComponent().getString();
				String value = buffer.readComponent().getString();
				map.put(key, value);
			}
			return map;
		}
	}
}
