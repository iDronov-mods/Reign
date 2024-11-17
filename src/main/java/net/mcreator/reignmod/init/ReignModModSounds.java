
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.reignmod.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.reignmod.ReignModMod;

public class ReignModModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ReignModMod.MODID);
	public static final RegistryObject<SoundEvent> COIN1 = REGISTRY.register("coin1", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("reign_mod", "coin1")));
	public static final RegistryObject<SoundEvent> COIN2 = REGISTRY.register("coin2", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("reign_mod", "coin2")));
	public static final RegistryObject<SoundEvent> COIN3 = REGISTRY.register("coin3", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("reign_mod", "coin3")));
	public static final RegistryObject<SoundEvent> YOURLAWNISBOOL = REGISTRY.register("yourlawnisbool", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("reign_mod", "yourlawnisbool")));
	public static final RegistryObject<SoundEvent> LOCKED_CHEST = REGISTRY.register("locked_chest", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("reign_mod", "locked_chest")));
	public static final RegistryObject<SoundEvent> WALLET_SOUND = REGISTRY.register("wallet_sound", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("reign_mod", "wallet_sound")));
	public static final RegistryObject<SoundEvent> MARKET_OPEN = REGISTRY.register("market_open", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("reign_mod", "market_open")));
	public static final RegistryObject<SoundEvent> WALLET_SHAKE = REGISTRY.register("wallet_shake", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("reign_mod", "wallet_shake")));
	public static final RegistryObject<SoundEvent> THE_CALM_BEFORE_THE_STORM = REGISTRY.register("the-calm-before-the-storm", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("reign_mod", "the-calm-before-the-storm")));
	public static final RegistryObject<SoundEvent> LIGHT_AFTER_DARKNESS = REGISTRY.register("light-after-darkness", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("reign_mod", "light-after-darkness")));
	public static final RegistryObject<SoundEvent> LASTO_BETH_LAMMEN = REGISTRY.register("lasto-beth-lammen", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("reign_mod", "lasto-beth-lammen")));
	public static final RegistryObject<SoundEvent> SAFE_OPEN = REGISTRY.register("safe_open", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("reign_mod", "safe_open")));
}
