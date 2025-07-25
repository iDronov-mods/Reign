package net.mcreator.reignmod.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.market.MarketManager;
import net.mcreator.reignmod.init.ReignModModItems;

import java.util.function.Supplier;
import java.util.Map;

public class TradingProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		String stringName = "";
		ItemStack item = ItemStack.EMPTY;
		double id = 0;
		double maxAmount = 0;
		double inPack = 0;
		double pay = 0;
		double slot = 0;
		double coinage_count = 0;
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				if (!((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).isDamaged())) {
					item = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).copy();
					stringName = ForgeRegistries.ITEMS.getKey(item.getItem()).toString();
					if (MarketManager.itemInMarket(stringName)) {
						maxAmount = MarketManager.getMaxAmount(stringName);
						inPack = MarketManager.getInPack(stringName);
						if (new Object() {
							public int getAmount(int sltid) {
								if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
									ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
									if (stack != null)
										return stack.getCount();
								}
								return 0;
							}
						}.getAmount(0) >= inPack) {
							if (MarketManager.getItemCount(stringName) + inPack <= maxAmount) {
								pay = GetPriceWithFillFactorProcedure.execute(world, stringName);
								if (MarketPayProcedure.execute(world, pay)) {
									MarketManager.increaseItemAmount(stringName, inPack);
									if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										((Slot) _slots.get(0)).remove((int) inPack);
										_player.containerMenu.broadcastChanges();
									}
									if (pay >= 4096) {
										if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
											ItemStack _setstack = new ItemStack(ReignModModItems.PLATINUM_COIN.get()).copy();
											_setstack.setCount((int) (Math.floor(pay / 4096) + new Object() {
												public int getAmount(int sltid) {
													if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
														ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
														if (stack != null)
															return stack.getCount();
													}
													return 0;
												}
											}.getAmount(4)));
											((Slot) _slots.get(4)).set(_setstack);
											_player.containerMenu.broadcastChanges();
										}
										pay = pay - Math.floor(pay / 4096) * 4096;
									}
									if (pay >= 256) {
										if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
											ItemStack _setstack = new ItemStack(ReignModModItems.GOLD_COIN.get()).copy();
											_setstack.setCount((int) (Math.floor(pay / 256) + new Object() {
												public int getAmount(int sltid) {
													if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
														ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
														if (stack != null)
															return stack.getCount();
													}
													return 0;
												}
											}.getAmount(3)));
											((Slot) _slots.get(3)).set(_setstack);
											_player.containerMenu.broadcastChanges();
										}
										pay = pay - Math.floor(pay / 256) * 256;
									}
									if (pay >= 16) {
										if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
											ItemStack _setstack = new ItemStack(ReignModModItems.SILVER_COIN.get()).copy();
											_setstack.setCount((int) (Math.floor(pay / 16) + new Object() {
												public int getAmount(int sltid) {
													if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
														ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
														if (stack != null)
															return stack.getCount();
													}
													return 0;
												}
											}.getAmount(2)));
											((Slot) _slots.get(2)).set(_setstack);
											_player.containerMenu.broadcastChanges();
										}
										pay = pay - Math.floor(pay / 16) * 16;
									}
									if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
										_setstack.setCount((int) (pay + new Object() {
											public int getAmount(int sltid) {
												if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
													ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
													if (stack != null)
														return stack.getCount();
												}
												return 0;
											}
										}.getAmount(1)));
										((Slot) _slots.get(1)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
									while (new Object() {
										public int getAmount(int sltid) {
											if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
												ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
												if (stack != null)
													return stack.getCount();
											}
											return 0;
										}
									}.getAmount(1) >= 16) {
										if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
											((Slot) _slots.get(1)).remove(16);
											_player.containerMenu.broadcastChanges();
										}
										if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
											ItemStack _setstack = new ItemStack(ReignModModItems.SILVER_COIN.get()).copy();
											_setstack.setCount((int) (new Object() {
												public int getAmount(int sltid) {
													if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
														ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
														if (stack != null)
															return stack.getCount();
													}
													return 0;
												}
											}.getAmount(2) + 1));
											((Slot) _slots.get(2)).set(_setstack);
											_player.containerMenu.broadcastChanges();
										}
									}
									while (new Object() {
										public int getAmount(int sltid) {
											if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
												ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
												if (stack != null)
													return stack.getCount();
											}
											return 0;
										}
									}.getAmount(2) >= 16) {
										if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
											((Slot) _slots.get(2)).remove(16);
											_player.containerMenu.broadcastChanges();
										}
										if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
											ItemStack _setstack = new ItemStack(ReignModModItems.GOLD_COIN.get()).copy();
											_setstack.setCount((int) (new Object() {
												public int getAmount(int sltid) {
													if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
														ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
														if (stack != null)
															return stack.getCount();
													}
													return 0;
												}
											}.getAmount(3) + 1));
											((Slot) _slots.get(3)).set(_setstack);
											_player.containerMenu.broadcastChanges();
										}
									}
									while (new Object() {
										public int getAmount(int sltid) {
											if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
												ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
												if (stack != null)
													return stack.getCount();
											}
											return 0;
										}
									}.getAmount(3) >= 16) {
										if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
											((Slot) _slots.get(3)).remove(16);
											_player.containerMenu.broadcastChanges();
										}
										if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
											ItemStack _setstack = new ItemStack(ReignModModItems.PLATINUM_COIN.get()).copy();
											_setstack.setCount((int) (new Object() {
												public int getAmount(int sltid) {
													if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
														ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
														if (stack != null)
															return stack.getCount();
													}
													return 0;
												}
											}.getAmount(4) + 1));
											((Slot) _slots.get(4)).set(_setstack);
											_player.containerMenu.broadcastChanges();
										}
									}
									SoundGiveCoinProcedure.execute(world, x, y, z);
								} else {
									if (entity instanceof Player _player && !_player.level().isClientSide())
										_player.displayClientMessage(Component.literal((Component.translatable("translation.key.market_cant_pay").getString())), true);
								}
							}
						}
					}
					if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(5)).getItem() : ItemStack.EMPTY).getItem() == Items.COPPER_INGOT
							&& !ReignModModVariables.MapVariables.get(world).coinage_block) {
						coinage_count = new Object() {
							public int getAmount(int sltid) {
								if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
									ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
									if (stack != null)
										return stack.getCount();
								}
								return 0;
							}
						}.getAmount(5);
						if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
							((Slot) _slots.get(5)).set(ItemStack.EMPTY);
							_player.containerMenu.broadcastChanges();
						}
						ReignModModVariables.MapVariables.get(world).market_copper = ReignModModVariables.MapVariables.get(world).market_copper + coinage_count * 8;
						ReignModModVariables.MapVariables.get(world).syncData(world);
						if ((entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).license_miner
								&& (entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).MAIN_LVL >= 2) {
							ReignModModVariables.MapVariables.get(world).market_copper_all = ReignModModVariables.MapVariables.get(world).market_copper_all + coinage_count * 9;
							ReignModModVariables.MapVariables.get(world).syncData(world);
							if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
								ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
								_setstack.setCount((int) (coinage_count + new Object() {
									public int getAmount(int sltid) {
										if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
											ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
											if (stack != null)
												return stack.getCount();
										}
										return 0;
									}
								}.getAmount(1)));
								((Slot) _slots.get(1)).set(_setstack);
								_player.containerMenu.broadcastChanges();
							}
							while (new Object() {
								public int getAmount(int sltid) {
									if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
										if (stack != null)
											return stack.getCount();
									}
									return 0;
								}
							}.getAmount(1) >= 16) {
								if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
									((Slot) _slots.get(1)).remove(16);
									_player.containerMenu.broadcastChanges();
								}
								if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
									ItemStack _setstack = new ItemStack(ReignModModItems.SILVER_COIN.get()).copy();
									_setstack.setCount((int) (new Object() {
										public int getAmount(int sltid) {
											if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
												ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
												if (stack != null)
													return stack.getCount();
											}
											return 0;
										}
									}.getAmount(2) + 1));
									((Slot) _slots.get(2)).set(_setstack);
									_player.containerMenu.broadcastChanges();
								}
							}
							SoundGiveCoinProcedure.execute(world, x, y, z);
						} else {
							ReignModModVariables.MapVariables.get(world).market_copper_all = ReignModModVariables.MapVariables.get(world).market_copper_all + coinage_count * 8;
							ReignModModVariables.MapVariables.get(world).syncData(world);
						}
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("reign_mod:minting")), SoundSource.BLOCKS, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("reign_mod:minting")), SoundSource.BLOCKS, 1, 1, false);
							}
						}
					}
					if (item.is(ItemTags.create(new ResourceLocation("reign:work_coins")))) {
						if (item.getItem() == ReignModModItems.WOODCUTTER_COIN.get()) {
							pay = 5;
						} else if (item.getItem() == ReignModModItems.MINER_COIN.get()) {
							pay = 48;
						} else if (item.getItem() == ReignModModItems.SMITH_COIN.get()) {
							pay = 176;
						} else if (item.getItem() == ReignModModItems.FARMER_COIN.get()) {
							pay = 18;
						} else if (item.getItem() == ReignModModItems.COWBOY_COIN.get()) {
							pay = 14;
						}
						if (ReignModModVariables.MapVariables.get(world).market_copper >= pay) {
							if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
								((Slot) _slots.get(0)).remove(1);
								_player.containerMenu.broadcastChanges();
							}
							if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
								ItemStack _setstack = new ItemStack(ReignModModItems.COPPER_COIN.get()).copy();
								_setstack.setCount((int) (pay + new Object() {
									public int getAmount(int sltid) {
										if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
											ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
											if (stack != null)
												return stack.getCount();
										}
										return 0;
									}
								}.getAmount(1)));
								((Slot) _slots.get(1)).set(_setstack);
								_player.containerMenu.broadcastChanges();
							}
							ReignModModVariables.MapVariables.get(world).market_copper = ReignModModVariables.MapVariables.get(world).market_copper - pay;
							ReignModModVariables.MapVariables.get(world).syncData(world);
							while (new Object() {
								public int getAmount(int sltid) {
									if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
										if (stack != null)
											return stack.getCount();
									}
									return 0;
								}
							}.getAmount(1) >= 16) {
								if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
									((Slot) _slots.get(1)).remove(16);
									_player.containerMenu.broadcastChanges();
								}
								if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
									ItemStack _setstack = new ItemStack(ReignModModItems.SILVER_COIN.get()).copy();
									_setstack.setCount((int) (new Object() {
										public int getAmount(int sltid) {
											if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
												ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
												if (stack != null)
													return stack.getCount();
											}
											return 0;
										}
									}.getAmount(2) + 1));
									((Slot) _slots.get(2)).set(_setstack);
									_player.containerMenu.broadcastChanges();
								}
							}
							while (new Object() {
								public int getAmount(int sltid) {
									if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
										if (stack != null)
											return stack.getCount();
									}
									return 0;
								}
							}.getAmount(2) >= 16) {
								if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
									((Slot) _slots.get(2)).remove(16);
									_player.containerMenu.broadcastChanges();
								}
								if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
									ItemStack _setstack = new ItemStack(ReignModModItems.GOLD_COIN.get()).copy();
									_setstack.setCount((int) (new Object() {
										public int getAmount(int sltid) {
											if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
												ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
												if (stack != null)
													return stack.getCount();
											}
											return 0;
										}
									}.getAmount(3) + 1));
									((Slot) _slots.get(3)).set(_setstack);
									_player.containerMenu.broadcastChanges();
								}
							}
							while (new Object() {
								public int getAmount(int sltid) {
									if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
										if (stack != null)
											return stack.getCount();
									}
									return 0;
								}
							}.getAmount(3) >= 16) {
								if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
									((Slot) _slots.get(3)).remove(16);
									_player.containerMenu.broadcastChanges();
								}
								if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
									ItemStack _setstack = new ItemStack(ReignModModItems.PLATINUM_COIN.get()).copy();
									_setstack.setCount((int) (new Object() {
										public int getAmount(int sltid) {
											if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
												ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
												if (stack != null)
													return stack.getCount();
											}
											return 0;
										}
									}.getAmount(4) + 1));
									((Slot) _slots.get(4)).set(_setstack);
									_player.containerMenu.broadcastChanges();
								}
							}
							SoundGiveCoinProcedure.execute(world, x, y, z);
						} else {
							if (entity instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal((Component.translatable("translation.key.market_cant_pay").getString())), true);
						}
					}
				}
			}
			world = _worldorig;
		}
	}
}
