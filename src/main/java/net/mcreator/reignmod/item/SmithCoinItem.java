
package net.mcreator.reignmod.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class SmithCoinItem extends Item {
	public SmithCoinItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
