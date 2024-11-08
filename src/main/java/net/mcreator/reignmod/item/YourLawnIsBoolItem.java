
package net.mcreator.reignmod.item;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;

public class YourLawnIsBoolItem extends RecordItem {
	public YourLawnIsBoolItem() {
		super(0, () -> ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("reign_mod:yourlawnisbool")), new Item.Properties().stacksTo(1).rarity(Rarity.EPIC), 1200);
	}
}
