
package net.mcreator.reignmod.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class RawsourceItem extends Item {
	public RawsourceItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.RARE));
	}
}
