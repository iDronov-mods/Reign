
package net.mcreator.reignmod.item;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;

public class RuinLord2Item extends RecordItem {
	public RuinLord2Item() {
		super(0, () -> ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("reign_mod:ruinlord2")), new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 3760);
	}
}
