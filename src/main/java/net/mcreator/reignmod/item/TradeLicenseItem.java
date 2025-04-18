
package net.mcreator.reignmod.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class TradeLicenseItem extends Item {
	public TradeLicenseItem() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON));
	}
}
