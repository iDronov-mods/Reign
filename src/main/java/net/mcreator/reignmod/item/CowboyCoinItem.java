
package net.mcreator.reignmod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class CowboyCoinItem extends Item {
	public CowboyCoinItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
