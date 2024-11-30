
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
	public static final RegistryObject<SoundEvent> SAFE_OPEN = REGISTRY.register("safe_open", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("reign_mod", "safe_open")));
	public static final RegistryObject<SoundEvent> PICKLOCK_WORK = REGISTRY.register("picklock_work", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("reign_mod", "picklock_work")));
	public static final RegistryObject<SoundEvent> PICKLOCK_BREAK = REGISTRY.register("picklock_break", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("reign_mod", "picklock_break")));
	public static final RegistryObject<SoundEvent> PICKLOCK_WORK2 = REGISTRY.register("picklock_work2", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("reign_mod", "picklock_work2")));
	public static final RegistryObject<SoundEvent> RUINLORD1 = REGISTRY.register("ruinlord1", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("reign_mod", "ruinlord1")));
	public static final RegistryObject<SoundEvent> RUINLORD2 = REGISTRY.register("ruinlord2", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("reign_mod", "ruinlord2")));
	public static final RegistryObject<SoundEvent> RUINLORD3 = REGISTRY.register("ruinlord3", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("reign_mod", "ruinlord3")));
}
