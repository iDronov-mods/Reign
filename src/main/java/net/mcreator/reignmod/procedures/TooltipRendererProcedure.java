package net.mcreator.reignmod.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.Screen;

import net.mcreator.reignmod.init.ReignModModItems;
import net.mcreator.reignmod.init.ReignModModBlocks;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class TooltipRendererProcedure {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		execute(event, event.getItemStack(), event.getToolTip());
	}

	public static void execute(ItemStack itemstack, List<Component> tooltip) {
		execute(null, itemstack, tooltip);
	}

	private static void execute(@Nullable Event event, ItemStack itemstack, List<Component> tooltip) {
		if (tooltip == null)
			return;
		String type_text = "";
		if (itemstack.getItem() == ReignModModBlocks.HOARDER_BLOCK.get().asItem()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.hoarder").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.hoarder_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModBlocks.FUND.get().asItem()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.fund").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.fund_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModBlocks.SAFE.get().asItem()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.safe").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.safe_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModBlocks.COFFERS.get().asItem()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.coffers").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.coffers_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModBlocks.RENTAL_BLOCK.get().asItem()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.rentalblock").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.rentalblock_short").getString())));
			}
		}
		if (itemstack.getItem() == ReignModModBlocks.MARKET_BLOCK.get().asItem()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.market").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.market_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModBlocks.TRADE_BLOCK.get().asItem()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.trader").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.trader_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModItems.COPPER_LOCK.get()) {
			tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.copperlock_short").getString())));
		} else if (itemstack.getItem() == ReignModModItems.IRON_LOCK.get()) {
			tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.ironlock_short").getString())));
		} else if (itemstack.getItem() == ReignModModItems.GOLD_LOCK.get()) {
			tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.goldlock_short").getString())));
		}
		if (itemstack.getItem() == ReignModModBlocks.KINGTABLE.get().asItem()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.royale_table").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.royale_table_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModItems.HEART_OF_HOUSE.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.heart_of_house").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.heart_of_house_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModBlocks.INCUBATOR.get().asItem()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.incubator").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.incubator_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModBlocks.PRIVATEDOOR.get().asItem()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.private_door").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.private_door_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModItems.PICKLOCK.get()) {
			tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.lockpick_short").getString())));
		} else if (itemstack.getItem() == ReignModModItems.ENDER_PICKLOCK.get()) {
			tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.ender_lockpick_short").getString())));
		}
		if (itemstack.getItem() == ReignModModItems.SHACKLES.get()) {
			tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.shackles_short").getString())));
		} else if (itemstack.getItem() == ReignModModBlocks.FINIAL_OF_MIGHT.get().asItem()) {
			tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.finial_of_might_short").getString())));
		} else if (itemstack.getItem() == ReignModModBlocks.FINIAL_OF_REIGN.get().asItem()) {
			tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.finial_of_reign_short").getString())));
		} else if (itemstack.getItem() == ReignModModBlocks.OBELISK_FOUNDATION.get().asItem()) {
			tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.obelisk_foundation_short").getString())));
		} else if (itemstack.getItem() == ReignModModBlocks.SHAFT.get().asItem()) {
			tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.shaft_short").getString())));
		}
		if (itemstack.getItem() == ReignModModItems.HONEY_CONCENTRATE.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.honey_concentrate").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.honey_concentrate_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModItems.RIGHT_HAND.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.right_hand").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.right_hand_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModItems.MARSHAL_INSIGNIA.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.marshal_insignia").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.marshal_insignia_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModItems.TREASURER_KEY.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.treasurer_key").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.treasurer_key_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModItems.ARCHITECT_COMPASS.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.architect_compass").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.architect_compass_short").getString())));
			}
		}
		if (itemstack.getItem() == ReignModModItems.PRIVATE_RENTAL_1.get()) {
			if (!(itemstack.getOrCreateTag().getString("owners")).isEmpty()) {
				tooltip.add(Component.literal(("\u00A7f\u00A7o" + itemstack.getOrCreateTag().getString("owners"))));
			}
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.private_rent").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.private_rent1_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModItems.PRIVATE_RENTAL_2.get()) {
			if (!(itemstack.getOrCreateTag().getString("owners")).isEmpty()) {
				tooltip.add(Component.literal(("\u00A7f\u00A7o" + itemstack.getOrCreateTag().getString("owners"))));
			}
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.private_rent").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.private_rent2_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModItems.PRIVATE_RENTAL_3.get()) {
			if (!(itemstack.getOrCreateTag().getString("owners")).isEmpty()) {
				tooltip.add(Component.literal(("\u00A7f\u00A7o" + itemstack.getOrCreateTag().getString("owners"))));
			}
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.private_rent").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.private_rent3_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModItems.STATE_RENTAL.get()) {
			if (!(itemstack.getOrCreateTag().getString("owners")).isEmpty()) {
				tooltip.add(Component.literal(("\u00A7f\u00A7o" + itemstack.getOrCreateTag().getString("owners"))));
			}
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.state_rent").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.state_rent_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModItems.TRADE_LICENSE.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.trade_license").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.trade_license_short").getString())));
			}
		}
		if (itemstack.getItem() == ReignModModItems.DEEP_ROCK_SAMPLE.get()) {
			if (!(itemstack.getOrCreateTag().getString("biome")).isEmpty()) {
				if ((itemstack.getOrCreateTag().getString("type")).isEmpty()) {
					type_text = "\u00A78" + Component.translatable("translation.key.tooltip.sample_not_detected").getString();
				} else if ((itemstack.getOrCreateTag().getString("type")).equals("COPPER")) {
					type_text = "\u00A76" + Component.translatable("translation.key.tooltip.sample_copper").getString();
				} else if ((itemstack.getOrCreateTag().getString("type")).equals("IRON")) {
					type_text = "\u00A7f" + Component.translatable("translation.key.tooltip.sample_iron").getString();
				} else if ((itemstack.getOrCreateTag().getString("type")).equals("GOLD")) {
					type_text = "\u00A7e" + Component.translatable("translation.key.tooltip.sample_gold").getString();
				} else if ((itemstack.getOrCreateTag().getString("type")).equals("EMERALD")) {
					type_text = "\u00A72" + Component.translatable("translation.key.tooltip.sample_emerald").getString();
				} else if ((itemstack.getOrCreateTag().getString("type")).equals("DIAMOND")) {
					type_text = "\u00A73" + Component.translatable("translation.key.tooltip.sample_diamond").getString();
				} else if ((itemstack.getOrCreateTag().getString("type")).equals("SOURCE")) {
					type_text = "\u00A7b" + Component.translatable("translation.key.tooltip.sample_source").getString();
				}
				tooltip.add(Component.literal(("\u00A79" + Component.translatable("translation.key.tooltip.sample_type").getString() + "\u00A77 " + type_text)));
				tooltip.add(Component.literal(("\u00A7a" + Component.translatable("translation.key.tooltip.sample_biome").getString() + "\u00A77 " + itemstack.getOrCreateTag().getString("biome"))));
				tooltip.add(Component.literal(("\u00A7c" + Component.translatable("translation.key.tooltip.sample_coords").getString() + " \u00A77x: " + new java.text.DecimalFormat("##").format(itemstack.getOrCreateTag().getDouble("x")) + " z: "
						+ new java.text.DecimalFormat("##").format(itemstack.getOrCreateTag().getDouble("z")))));
			}
		} else if (itemstack.getItem() == ReignModModItems.SOURCE_BOTTLE.get()) {
			tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.source_bottle_short").getString())));
		} else if (itemstack.getItem() == ReignModModItems.DEEPCRACK.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.mine").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.mine_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModBlocks.STRATEGY_BLOCK.get().asItem()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.strategy_block").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.strategy_block_short").getString())));
			}
		} else if (itemstack.getItem() == ReignModModBlocks.STORAGE_BARREL.get().asItem()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.storage_barrel").getString())));
			} else {
				tooltip.add(Component.literal(("\u00A77\u00A7o" + Component.translatable("translation.key.tooltip.storage_barrel_short").getString())));
			}
		}
	}
}
