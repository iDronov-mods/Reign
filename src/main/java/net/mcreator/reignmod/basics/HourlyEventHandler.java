package net.mcreator.reignmod.basics;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class HourlyEventHandler {
    // Последнее время срабатывания (в миллисекундах)
    private static long lastExecutionTime = System.currentTimeMillis();
    
    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) { // Выполняем проверку на каждом серверном тике
            long currentTime = System.currentTimeMillis();
            
            // Проверяем, прошёл ли час (3600000 мс = 1 час)
            if (currentTime - lastExecutionTime >= 3600000L) {
                lastExecutionTime = currentTime; // Обновляем время последнего срабатывания
                triggerHourlyEvent();           // Вызываем ваше событие
            }
        }
    }
    
    private static void triggerHourlyEvent() {
        // Ваша логика срабатывания события
        System.out.println("Прошёл ещё один реальный час!");
        
        // Здесь можно добавить любые действия, например:
        // - выдача игрокам бонусов,
        // - запуск внутриигровых событий,
        // - сохранение данных.
    }
}
