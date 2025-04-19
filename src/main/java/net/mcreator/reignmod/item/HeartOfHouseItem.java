
package net.mcreator.reignmod.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class HeartOfHouseItem extends Item {
	public HeartOfHouseItem() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
	}
}
