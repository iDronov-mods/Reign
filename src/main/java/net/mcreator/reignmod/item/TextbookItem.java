
package net.mcreator.reignmod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class TextbookItem extends Item {
	public TextbookItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.RARE));
	}
}
