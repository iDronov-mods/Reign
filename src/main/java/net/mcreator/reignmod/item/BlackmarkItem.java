
package net.mcreator.reignmod.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class BlackmarkItem extends Item {
	public BlackmarkItem() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON));
	}
}
