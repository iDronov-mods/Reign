
package net.mcreator.reignmod.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class ReignCoinItem extends Item {
	public ReignCoinItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
