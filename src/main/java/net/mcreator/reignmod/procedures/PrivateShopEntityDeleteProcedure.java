//package net.mcreator.reignmod.procedures;
//
//import com.mojang.math.Transformation;
//import net.mcreator.reignmod.ReignModMod;
//import net.minecraft.world.phys.AABB;
//import net.minecraft.world.level.LevelAccessor;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.world.entity.Display;
//import net.minecraft.world.inventory.Slot;
//import net.minecraft.server.level.ServerLevel;
//import net.minecraft.network.chat.Component;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.BlockItem;
//
//import org.joml.Vector3f;
//import org.joml.Quaternionf;
//
//import java.lang.reflect.Method;
//import java.util.function.Supplier;
//import java.util.Map;
//import java.util.List;
//import java.util.Random;
//
//public class PrivateShopEntityDeleteProcedure {
//	public static void execute(LevelAccessor world, double x, double y, double z) {
//
//		double x_block = 0;
//		double y_block = 0;
//		double z_block = 0;
//
//		if (world instanceof ServerLevel _origLevel) {
//			LevelAccessor _worldorig = world;
//			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
//
//
//				x_block = x;
//				y_block = y;
//				z_block = z;
//
//				if (world instanceof Level level) {
//					// Удаляем старые сущности отображения внутри блока
//					AABB box = new AABB(x_block, y_block + 0.6, z_block, x_block + 1, y_block + 0.8, z_block + 1);
//					List<Entity> foundEntities = level.getEntities(null, box);
//					for (Entity e : foundEntities) {
//						if (e.getType() == EntityType.ITEM_DISPLAY && "shop_display".equals(e.getName().getString())) {
//							e.discard();
//						}
//					}
//
//
//				}
//
//			world = _worldorig;
//		}
//
//	}
//
//}
