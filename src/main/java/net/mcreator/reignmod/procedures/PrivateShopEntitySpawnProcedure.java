//package net.mcreator.reignmod.procedures;
//
//import com.mojang.math.Transformation;
//import net.mcreator.reignmod.ReignModMod;
//import net.minecraft.world.phys.AABB;
//import net.minecraft.world.level.LevelAccessor;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.item.ItemStack;
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
//public class PrivateShopEntitySpawnProcedure {
//	public static void execute(LevelAccessor world, double x, double y, double z, ItemStack item) {
//		double x_block = 0;
//		double y_block = 0;
//		double z_block = 0;
//
//		if (world instanceof ServerLevel _origLevel) {
//			LevelAccessor _worldorig = world;
//			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
//
//			if (world != null) {
//
//				x_block = x;
//				y_block = y;
//				z_block = z;
//
//				if (!item.isEmpty() && world instanceof Level level) {
//					// Удаляем старые сущности отображения внутри блока
//					AABB box = new AABB(x_block, y_block + 0.6, z_block, x_block + 1, y_block + 0.9, z_block + 1);
//					List<Entity> foundEntities = level.getEntities(null, box);
//					for (Entity e : foundEntities) {
//						if (e.getType() == EntityType.ITEM_DISPLAY && "shop_display".equals(e.getName().getString())) {
//							e.discard();
//						}
//					}
//
//					Display.ItemDisplay display = new Display.ItemDisplay(EntityType.ITEM_DISPLAY, level);
//					display.setPos(x_block + 0.5, y_block + 0.8, z_block + 0.5); // Центр блока
//					display.setCustomName(Component.literal("shop_display"));
//					display.setCustomNameVisible(false);
//
//					try {
//						Method setItemStackMethod = Display.ItemDisplay.class.getDeclaredMethod("setItemStack", ItemStack.class);
//						setItemStackMethod.setAccessible(true);
//						setItemStackMethod.invoke(display, item.copy());
//					} catch (Exception e) {
//						ReignModMod.LOGGER.error("Failed to set ItemStack", e);
//					}
//
//					Random random = new Random();
//					float rotXDeg = 0f;
//					float rotYDeg;
//					float rotZDeg = 0f;
//
//					float scaleRate;
//
//					if (item.getItem() instanceof BlockItem) {
//						rotYDeg = random.nextFloat() * 360f;
//						scaleRate = 0.3f;
//
//					} else {
//						rotZDeg = random.nextFloat() * 360f;
//						rotYDeg = 90f;
//						scaleRate = 0.7f;
//					}
//
//					Quaternionf rotation = new Quaternionf().rotateXYZ(
//							(float) Math.toRadians(rotXDeg),
//							(float) Math.toRadians(rotYDeg),
//							(float) Math.toRadians(rotZDeg)
//					);
//
//					Vector3f scale = new Vector3f(scaleRate, scaleRate, scaleRate);
//
//					Vector3f translation = new Vector3f(0f, 0.2f, 0f);
//
//					Transformation transformation = new Transformation(
//							translation,
//							rotation,
//							scale,
//							null
//					);
//
//					try {
//						Method setTransform = Display.class.getDeclaredMethod("setTransformation", Transformation.class);
//						setTransform.setAccessible(true);
//						setTransform.invoke(display, transformation);
//					} catch (Exception e) {
//						ReignModMod.LOGGER.error("Failed to set transformation for ItemDisplay", e);
//					}
//
//					// Добавляем в мир
//					if (!level.isClientSide()) {
//						level.addFreshEntity(display);
//					}
//				}
//			}
//			world = _worldorig;
//		}
//	}
//}
