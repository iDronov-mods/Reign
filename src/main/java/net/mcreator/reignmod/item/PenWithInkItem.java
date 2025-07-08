
package net.mcreator.reignmod.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class PenWithInkItem extends Item {
	public PenWithInkItem() {
		super(new Item.Properties().durability(10).rarity(Rarity.COMMON));
	}
}
