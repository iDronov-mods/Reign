
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.reignmod.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

import net.mcreator.reignmod.potion.SuspectMobEffect;
import net.mcreator.reignmod.potion.CriminalMobEffect;
import net.mcreator.reignmod.potion.BeeEffectMobEffect;
import net.mcreator.reignmod.ReignModMod;

public class ReignModModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ReignModMod.MODID);
	public static final RegistryObject<MobEffect> CRIMINAL = REGISTRY.register("criminal", () -> new CriminalMobEffect());
	public static final RegistryObject<MobEffect> BEE_EFFECT = REGISTRY.register("bee_effect", () -> new BeeEffectMobEffect());
	public static final RegistryObject<MobEffect> SUSPECT = REGISTRY.register("suspect", () -> new SuspectMobEffect());
}
