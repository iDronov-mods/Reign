
package net.mcreator.reignmod.item;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;

public class RuinLord3Item extends RecordItem {
	public RuinLord3Item() {
		super(0, () -> ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("reign_mod:lasto-beth-lammen")), new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 3780);
	}
}
