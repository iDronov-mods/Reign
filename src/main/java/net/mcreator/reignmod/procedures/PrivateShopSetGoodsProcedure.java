package net.mcreator.reignmod.procedures;

import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import java.util.concurrent.atomic.AtomicInteger;

public class PrivateShopSetGoodsProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, ItemStack itemstack) {
		if (itemstack.is(ItemTags.create(new ResourceLocation("reign:coins"))) || itemstack.isEnchanted() || itemstack.isDamaged() || new Object() {
			public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
				AtomicInteger _retval = new AtomicInteger(0);
				BlockEntity _ent = world.getBlockEntity(pos);
				if (_ent != null)
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
				return _retval.get();
			}
		}.getAmount(world, BlockPos.containing(x, y, z), 0) > 0) {
			return true;
		}
		return false;
	}
}
