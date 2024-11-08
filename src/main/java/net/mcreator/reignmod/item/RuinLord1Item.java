
package net.mcreator.reignmod.item;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;

public class RuinLord1Item extends RecordItem {
	public RuinLord1Item() {
		super(0, () -> ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("reign_mod:the-calm-before-the-storm")), new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 3960);
	}
}
