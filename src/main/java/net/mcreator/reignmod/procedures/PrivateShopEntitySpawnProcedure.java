package net.mcreator.reignmod.procedures;

import com.mojang.math.Transformation;
import net.mcreator.reignmod.ReignModMod;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

public class PrivateShopEntitySpawnProcedure {

	public static void removeEntityDisplays(LevelAccessor world, double x, double y, double z) {
		if (world instanceof ServerLevel _origLevel) {
			Level level = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (level != null) {
				AABB box = new AABB(x, y + 0.6, z, x + 1, y + 0.9, z + 1);
				List<Entity> foundEntities = level.getEntities(null, box);

				for (Entity e : foundEntities) {
					if (e.getType() == EntityType.ITEM_DISPLAY && "shop_display".equals(e.getName().getString())) {
						e.discard();
					}
				}
			}
		}
	}


	public static void spawnEntity(LevelAccessor world, double x, double y, double z, ItemStack item) {
		if (world instanceof ServerLevel _origLevel) {
			Level level = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (level != null && !item.isEmpty()) {
				Display.ItemDisplay display = new Display.ItemDisplay(EntityType.ITEM_DISPLAY, level);
				display.setPos(x + 0.5, y + 0.7, z + 0.5);
				display.setCustomName(Component.literal("shop_display"));
				display.setCustomNameVisible(false);

				try {
					Method setItemStackMethod = Display.ItemDisplay.class.getDeclaredMethod("setItemStack", ItemStack.class);
					setItemStackMethod.setAccessible(true);
					setItemStackMethod.invoke(display, item.copy());
				} catch (Exception e) {
					ReignModMod.LOGGER.error("Failed to set ItemStack", e);
				}

				Vector3f translation;

				Random random = new Random();
				float rotXDeg = 0f;
				float rotYDeg;
				float rotZDeg = 0f;

				float scaleRate;

				if (item.getItem() instanceof BlockItem) {
					rotYDeg = random.nextFloat() * 360f;
					scaleRate = 0.3f;

					translation = new Vector3f(0f, 0.2f, 0f);

				} else {
					rotZDeg = random.nextFloat() * 360f;
					rotXDeg = 90f;
					rotYDeg = 0f;
					scaleRate = 0.7f;

					translation = new Vector3f(0f, 0.08f, 0f);
				}

				Quaternionf rotation = new Quaternionf().rotateXYZ(
						(float) Math.toRadians(rotXDeg),
						(float) Math.toRadians(rotYDeg),
						(float) Math.toRadians(rotZDeg)
				);

				Vector3f scale = new Vector3f(scaleRate, scaleRate, scaleRate);


				Transformation transformation = new Transformation(
						translation,
						rotation,
						scale,
						null
				);

				try {
					Method setTransform = Display.class.getDeclaredMethod("setTransformation", Transformation.class);
					setTransform.setAccessible(true);
					setTransform.invoke(display, transformation);
				} catch (Exception e) {
					ReignModMod.LOGGER.error("Failed to set transformation for ItemDisplay", e);
				}

				// Добавляем в мир
				if (!level.isClientSide()) {
					level.addFreshEntity(display);
				}
			}
		}
	}
}
