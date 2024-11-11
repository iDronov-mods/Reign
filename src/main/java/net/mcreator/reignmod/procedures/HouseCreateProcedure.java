package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.client.gui.components.EditBox;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.house.HouseManager;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

public class HouseCreateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		String color = "";
		String UUID_Player = "";
		String house_name = "";
		house_name = guistate.containsKey("text:house_name") ? ((EditBox) guistate.get("text:house_name")).getValue() : "";
		if (!(house_name).isEmpty()) {
			if (!((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(10)).getItem() : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem())) {
				if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(10)).getItem() : ItemStack.EMPTY).getItem() == Blocks.YELLOW_BANNER.asItem()) {
					ReignModModVariables.MapVariables.get(world).yellow_free = true;
					ReignModModVariables.MapVariables.get(world).syncData(world);
					color = "yellow";
				}
				if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(10)).getItem() : ItemStack.EMPTY).getItem() == Blocks.LIME_BANNER.asItem()) {
					ReignModModVariables.MapVariables.get(world).lime_free = true;
					ReignModModVariables.MapVariables.get(world).syncData(world);
					color = "lime";
				}
				if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(10)).getItem() : ItemStack.EMPTY).getItem() == Blocks.GREEN_BANNER.asItem()) {
					ReignModModVariables.MapVariables.get(world).green_free = true;
					ReignModModVariables.MapVariables.get(world).syncData(world);
					color = "green";
				}
				if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(10)).getItem() : ItemStack.EMPTY).getItem() == Blocks.CYAN_BANNER.asItem()) {
					ReignModModVariables.MapVariables.get(world).biruz_free = true;
					ReignModModVariables.MapVariables.get(world).syncData(world);
					color = "aqua";
				}
				if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(10)).getItem() : ItemStack.EMPTY).getItem() == Blocks.BLUE_BANNER.asItem()) {
					ReignModModVariables.MapVariables.get(world).blue_free = true;
					ReignModModVariables.MapVariables.get(world).syncData(world);
					color = "blue";
				}
				if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(10)).getItem() : ItemStack.EMPTY).getItem() == Blocks.PURPLE_BANNER.asItem()) {
					ReignModModVariables.MapVariables.get(world).purple_free = true;
					ReignModModVariables.MapVariables.get(world).syncData(world);
					color = "purple";
				}
				if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(10)).getItem() : ItemStack.EMPTY).getItem() == Blocks.PINK_BANNER.asItem()) {
					ReignModModVariables.MapVariables.get(world).pink_free = true;
					ReignModModVariables.MapVariables.get(world).syncData(world);
					color = "pink";
				}
				if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(10)).getItem() : ItemStack.EMPTY).getItem() == Blocks.RED_BANNER.asItem()) {
					ReignModModVariables.MapVariables.get(world).red_free = true;
					ReignModModVariables.MapVariables.get(world).syncData(world);
					color = "red";
				}
				if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(10)).getItem() : ItemStack.EMPTY).getItem() == Blocks.ORANGE_BANNER.asItem()) {
					ReignModModVariables.MapVariables.get(world).orange_free = true;
					ReignModModVariables.MapVariables.get(world).syncData(world);
					color = "orange";
				}
				if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(10)).getItem() : ItemStack.EMPTY).getItem() == Items.BLACK_BANNER) {
					ReignModModVariables.MapVariables.get(world).black_free = true;
					ReignModModVariables.MapVariables.get(world).syncData(world);
					color = "black";
				}
				UUID_Player = entity.getStringUUID();
				if (world instanceof ServerLevel _origLevel) {
					LevelAccessor _worldorig = world;
					world = _origLevel.getServer().getLevel(Level.OVERWORLD);
					if (world != null) {
						if (!HouseManager.createHouse(entity, house_name, color, 0)) {
							if (entity instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal((Component.translatable("translation.key.houseNameTaken").getString())), false);
						} else {
							{
								String _setval = house_name;
								entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.house = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
							{
								double _setval = x;
								entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.Kingdom_X = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
							{
								double _setval = y;
								entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.Kingdom_Y = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
							{
								double _setval = z;
								entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.Kingdom_Z = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
							world.setBlock(BlockPos.containing(x, y, z),
									((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(10)).getItem() : ItemStack.EMPTY)
											.getItem() instanceof BlockItem _bi ? _bi.getBlock().defaultBlockState() : Blocks.AIR.defaultBlockState()),
									3);
							{
								int _value = (int) DirectionFlagProcedure.execute(world, x, z);
								BlockPos _pos = BlockPos.containing(x, y, z);
								BlockState _bs = world.getBlockState(_pos);
								if (_bs.getBlock().getStateDefinition().getProperty("rotation") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
									world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
							}
							if (!world.isClientSide() && world.getServer() != null)
								world.getServer().getPlayerList().broadcastSystemMessage(Component.literal((Component.translatable("newhouse").getString() + " " + house_name)), false);
							if (!world.isClientSide() && world.getServer() != null)
								world.getServer().getPlayerList().broadcastSystemMessage(Component.literal((Component.translatable("newlord").getString() + "" + entity.getDisplayName().getString())), false);
							{
								boolean _setval = true;
								entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.isLord = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
						}
					}
					world = _worldorig;
				}
			}
		}
	}
}
