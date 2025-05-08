package net.mcreator.reignmod.procedures;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Display;
import net.minecraft.world.inventory.Slot;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import org.joml.Vector3f;
import org.joml.Quaternionf;
import org.joml.Matrix4f;

import java.lang.reflect.Method;
import java.util.function.Supplier;
import java.util.Map;
import java.util.List;
import java.util.Random;

public class PrivateShopGoodsEntitySpawnProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null) return;

		ItemStack item = ItemStack.EMPTY;
		double x_block = 0;
		double y_block = 0;
		double z_block = 0;

		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);

			if (world != null) {
				// Получаем предмет из слота
				if (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt) {
					Slot slot = (Slot) _slt.get(2); // Получаем слот
					item = slot.getItem(); // Получаем предмет из слота
				}

				x_block = x;
				y_block = y;
				z_block = z;

				if (!item.isEmpty() && world instanceof Level level) {
					// Удаляем старые сущности отображения внутри блока
					AABB box = new AABB(x_block, y_block + 0.6, z_block, x_block + 1, y_block + 0.8, z_block + 1);
					List<Entity> foundEntities = level.getEntities(null, box);
					for (Entity e : foundEntities) {
						if (e.getType() == EntityType.ITEM_DISPLAY && "shop_display".equals(e.getName().getString())) {
							e.discard();
						}
					}

					// Создаем объект ItemDisplay
					Display.ItemDisplay display = new Display.ItemDisplay(EntityType.ITEM_DISPLAY, level);
					display.setPos(x_block + 0.5, y_block + 0.67, z_block + 0.5); // Центр блока
					display.setCustomName(Component.literal("shop_display"));
					display.setCustomNameVisible(false);

					// Устанавливаем предмет через reflection
					try {
						Method setItemStackMethod = Display.ItemDisplay.class.getDeclaredMethod("setItemStack", ItemStack.class);
						setItemStackMethod.setAccessible(true);
						setItemStackMethod.invoke(display, item.copy());
					} catch (Exception e) {
						e.printStackTrace();
					}

					// Случайный поворот по Y и фиксированный X (положить плашмя)
					Random random = new Random();
					float rotY = random.nextFloat() * 360f;
					float rotX = (float) Math.toRadians(90f); // Лежит

					Quaternionf rotation = new Quaternionf().rotateXYZ(rotX, (float) Math.toRadians(rotY), 0f);

					// Масштаб: половинный
					Vector3f scale = new Vector3f(0.5f, 0.5f, 0.5f);

					// Матрица трансформации
					Matrix4f transform = new Matrix4f()
							.identity()
							.translate(0f, 0f, 0f)
							.rotate(rotation)
							.scale(scale);

					// Применяем трансформацию
					try {
						Method setTransformMethod = Display.class.getMethod("setTransformationMatrix", Matrix4f.class);
						setTransformMethod.invoke(display, transform);
					} catch (Exception e) {
						e.printStackTrace();
					}

					// Добавляем в мир
					if (!level.isClientSide()) {
						level.addFreshEntity(display);
					}
				}
			}
			world = _worldorig;
		}
	}
}
