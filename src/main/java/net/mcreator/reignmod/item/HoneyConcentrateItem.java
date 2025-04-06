
package net.mcreator.reignmod.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class HoneyConcentrateItem extends Item {
	public HoneyConcentrateItem() {
		super(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(6).saturationMod(0.3f).alwaysEat().build()));
	}
}
