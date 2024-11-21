package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.house.HouseManager;

import java.util.function.Supplier;
import java.util.Map;

public class HouseCreateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		String color = "";
		String UUID_Player = "";
		String house_name = "";
		house_name = "Sun";
		color = "orange";
		if (!(house_name).isEmpty()) {
			if (!((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(10)).getItem() : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem())) {
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
								String _setval = entity.getStringUUID();
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
						}
					}
					world = _worldorig;
				}
			}
		}
	}
}
