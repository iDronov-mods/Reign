package net.mcreator.reignmod.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.world.inventory.PlayerSearchUIMenu;
import net.mcreator.reignmod.init.ReignModModMenus;

import javax.annotation.Nullable;

import java.util.function.Supplier;
import java.util.concurrent.atomic.AtomicReference;
import java.util.Map;

import io.netty.buffer.Unpooled;

@Mod.EventBusSubscriber
public class PlayerSearchProcedure {
	@SubscribeEvent
	public static void onRightClickEntity(PlayerInteractEvent.EntityInteract event) {
		if (event.getHand() != event.getEntity().getUsedItemHand())
			return;
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getTarget(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		execute(null, world, x, y, z, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				if (entity instanceof Player) {
					if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem()) {
						if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem()
								|| (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem()) {
							if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem()
									|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem()
									|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem()
									|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem()) {
								if (entity.isShiftKeyDown()) {
									if (sourceentity instanceof ServerPlayer _ent) {
										BlockPos _bpos = BlockPos.containing(x, y, z);
										NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
											@Override
											public Component getDisplayName() {
												return Component.literal("PlayerSearchUI");
											}

											@Override
											public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
												return new PlayerSearchUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
											}
										}, _bpos);
									}
									if (sourceentity instanceof ServerPlayer _player && !world.isClientSide())
										ReignModModMenus.setText("Label", (Component.translatable("translation.key.player_search").getString() + " " + entity.getDisplayName().getString()), _player);
									if (world instanceof Level _level) {
										if (!_level.isClientSide()) {
											_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")), SoundSource.PLAYERS, 1, 1);
										} else {
											_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")), SoundSource.PLAYERS, 1, 1, false);
										}
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(0, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(0, entity)).getCount());
										((Slot) _slots.get(0)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(1, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(1, entity)).getCount());
										((Slot) _slots.get(1)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(2, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(2, entity)).getCount());
										((Slot) _slots.get(2)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(3, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(3, entity)).getCount());
										((Slot) _slots.get(3)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(4, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(4, entity)).getCount());
										((Slot) _slots.get(4)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(5, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(5, entity)).getCount());
										((Slot) _slots.get(5)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(6, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(6, entity)).getCount());
										((Slot) _slots.get(6)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(7, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(7, entity)).getCount());
										((Slot) _slots.get(7)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(8, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(8, entity)).getCount());
										((Slot) _slots.get(8)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(9, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(9, entity)).getCount());
										((Slot) _slots.get(9)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(10, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(10, entity)).getCount());
										((Slot) _slots.get(10)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(11, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(11, entity)).getCount());
										((Slot) _slots.get(11)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(12, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(12, entity)).getCount());
										((Slot) _slots.get(12)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(13, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(13, entity)).getCount());
										((Slot) _slots.get(13)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(14, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(14, entity)).getCount());
										((Slot) _slots.get(14)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(15, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(15, entity)).getCount());
										((Slot) _slots.get(15)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(16, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(16, entity)).getCount());
										((Slot) _slots.get(16)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(17, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(17, entity)).getCount());
										((Slot) _slots.get(17)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(18, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(18, entity)).getCount());
										((Slot) _slots.get(18)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(19, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(19, entity)).getCount());
										((Slot) _slots.get(19)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(20, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(20, entity)).getCount());
										((Slot) _slots.get(20)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(21, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(21, entity)).getCount());
										((Slot) _slots.get(21)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(22, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(22, entity)).getCount());
										((Slot) _slots.get(22)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(23, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(23, entity)).getCount());
										((Slot) _slots.get(23)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(24, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(24, entity)).getCount());
										((Slot) _slots.get(24)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(25, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(25, entity)).getCount());
										((Slot) _slots.get(25)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(26, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(26, entity)).getCount());
										((Slot) _slots.get(26)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(27, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(27, entity)).getCount());
										((Slot) _slots.get(27)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(28, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(28, entity)).getCount());
										((Slot) _slots.get(28)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(29, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(29, entity)).getCount());
										((Slot) _slots.get(29)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(30, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(30, entity)).getCount());
										((Slot) _slots.get(30)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(31, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(31, entity)).getCount());
										((Slot) _slots.get(31)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(32, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(32, entity)).getCount());
										((Slot) _slots.get(32)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(33, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(33, entity)).getCount());
										((Slot) _slots.get(33)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(34, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(34, entity)).getCount());
										((Slot) _slots.get(34)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									if (sourceentity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = (new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(35, entity)).copy();
										_setstack.setCount((new Object() {
											public ItemStack getItemStack(int sltid, Entity entity) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
													_retval.set(capability.getStackInSlot(sltid).copy());
												});
												return _retval.get();
											}
										}.getItemStack(35, entity)).getCount());
										((Slot) _slots.get(35)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
								}
							}
						}
					}
				}
			}
			world = _worldorig;
		}
	}
}
