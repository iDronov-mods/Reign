package net.mcreator.reignmod.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.init.ReignModModItems;
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
		ItemStack heart = ItemStack.EMPTY;
		house_name = guistate.containsKey("textin:house_name") ? (String) guistate.get("textin:house_name") : "";
		if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(10)).getItem() : ItemStack.EMPTY).getItem() == Blocks.YELLOW_BANNER.asItem()) {
			color = "yellow";
		} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(10)).getItem() : ItemStack.EMPTY).getItem() == Blocks.LIME_BANNER.asItem()) {
			color = "lime";
		} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(10)).getItem() : ItemStack.EMPTY).getItem() == Blocks.GREEN_BANNER.asItem()) {
			color = "green";
		} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(10)).getItem() : ItemStack.EMPTY).getItem() == Blocks.CYAN_BANNER.asItem()) {
			color = "aqua";
		} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(10)).getItem() : ItemStack.EMPTY).getItem() == Blocks.BLUE_BANNER.asItem()) {
			color = "blue";
		} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(10)).getItem() : ItemStack.EMPTY).getItem() == Blocks.PURPLE_BANNER.asItem()) {
			color = "purple";
		} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(10)).getItem() : ItemStack.EMPTY).getItem() == Blocks.PINK_BANNER.asItem()) {
			color = "pink";
		} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(10)).getItem() : ItemStack.EMPTY).getItem() == Blocks.RED_BANNER.asItem()) {
			color = "red";
		} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(10)).getItem() : ItemStack.EMPTY).getItem() == Blocks.ORANGE_BANNER.asItem()) {
			color = "orange";
		} else if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(10)).getItem() : ItemStack.EMPTY).getItem() == Blocks.BLACK_BANNER.asItem()) {
			color = "black";
		}
		if (!(house_name).isEmpty() && ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).house).isEmpty()) {
			if (!((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(10)).getItem() : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem())) {
				UUID_Player = entity.getStringUUID();
				if (world instanceof ServerLevel _origLevel) {
					LevelAccessor _worldorig = world;
					world = _origLevel.getServer().getLevel(Level.OVERWORLD);
					if (world != null) {
						if (WalletPayProcedure.execute(entity, 1024) || new Object() {
							public boolean checkGamemode(Entity _ent) {
								if (_ent instanceof ServerPlayer _serverPlayer) {
									return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
								} else if (_ent.level().isClientSide() && _ent instanceof Player _player) {
									return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
											&& Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
								}
								return false;
							}
						}.checkGamemode(entity)) {
							if (!HouseManager.createHouse((Player) entity, house_name, color)) {
								if (entity instanceof Player _player && !_player.level().isClientSide())
									_player.displayClientMessage(Component.literal((Component.translatable("translation.key.houseNameTaken").getString())), false);
								WalletGiveProcedure.execute(entity, 1024);
							} else {
								{
									String _setval = entity.getStringUUID();
									entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.house = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
								HouseManager.playerPrefixSynchronize((Player) entity);
								HouseManager.allPlayersPrefixPacketSend();
								HouseManager.setHousePlusCoordinates(UUID_Player, (int) x, (int) y, (int) z);
								if (world instanceof Level _level)
									_level.getScoreboard().addPlayerTeam(color);
								{
									Entity _entityTeam = entity;
									PlayerTeam _pt = _entityTeam.level().getScoreboard().getPlayerTeam(color);
									if (_pt != null) {
										if (_entityTeam instanceof Player _player)
											_entityTeam.level().getScoreboard().addPlayerToTeam(_player.getGameProfile().getName(), _pt);
										else
											_entityTeam.level().getScoreboard().addPlayerToTeam(_entityTeam.getStringUUID(), _pt);
									}
								}
								heart = new ItemStack(ReignModModItems.HEART_OF_HOUSE.get()).copy();
								heart.getOrCreateTag().putString("UUID", (entity.getStringUUID()));
								if (entity instanceof Player _player) {
									ItemStack _setstack = heart.copy();
									_setstack.setCount(1);
									ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
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
									world.getServer().getPlayerList().broadcastSystemMessage(Component.literal((Component.translatable("newhouse").getString() + " " + HouseManager.getHouseByLordUUID(UUID_Player).getHouseTitleWithColor())), false);
								if (!world.isClientSide() && world.getServer() != null)
									world.getServer().getPlayerList().broadcastSystemMessage(Component.literal((Component.translatable("newlord").getString() + "" + entity.getDisplayName().getString())), false);
							}
						} else {
							if (entity instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal((Component.translatable("translation.key.houseNoPayMoney").getString())), false);
						}
					}
					world = _worldorig;
				}
			}
		}
	}
}
