package net.mcreator.reignmod.basics;

import net.minecraft.world.item.ItemStack;
import java.util.Objects;

public class PrivateShopHandler {

	public static boolean ItemStackWithNbtEqual(ItemStack lhs, ItemStack rhs) {
        return (Objects.equals(lhs.getItem(), rhs.getItem()) && Objects.equals(lhs.getTag(), rhs.getTag()));
    }	
}
