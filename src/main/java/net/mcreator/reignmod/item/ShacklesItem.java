
package net.mcreator.reignmod.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class ShacklesItem extends Item {
	public ShacklesItem() {
		super(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON));
	}
}
