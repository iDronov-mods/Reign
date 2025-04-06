
package net.mcreator.reignmod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class RightHandItem extends Item {
	public RightHandItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
