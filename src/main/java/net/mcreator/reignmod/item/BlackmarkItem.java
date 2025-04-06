
package net.mcreator.reignmod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class BlackmarkItem extends Item {
	public BlackmarkItem() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON));
	}
}
