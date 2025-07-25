package net.mcreator.reignmod.basics;

import net.mcreator.reignmod.configuration.ReignCommonConfiguration;
import net.mcreator.reignmod.kingdom.KingdomData;
import net.mcreator.reignmod.kingdom.KingdomManager;
import net.mcreator.reignmod.mines.MineManager;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mcreator.reignmod.house.HouseManager;





@Mod.EventBusSubscriber
public class HourlyEventHandler {
    // Последнее время срабатывания (в миллисекундах)
    private static long lastExecutionTime = System.currentTimeMillis();
    
    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) { // Выполняем проверку на каждом серверном тике
            long currentTime = System.currentTimeMillis();
            
            // Проверяем, прошёл ли час (3600000 мс = 1 час)
            if (currentTime - lastExecutionTime >= ConfigLoader.getHourlyMealPeriod() * 3600000L) { //каждые 1 ч
                lastExecutionTime = currentTime; // Обновляем время последнего срабатывания
                triggerHourlyEvent();           // Вызываем ваше событие
            }
        }
    }
    
    private static void triggerHourlyEvent() {
        // Ваша логика срабатывания события
        System.out.println("Another real hour has passed!");

        if (!ReignCommonConfiguration.DISABLE_HOUSE_FEEDING.get()) HouseManager.feedHouses();
        if (!ReignCommonConfiguration.DISABLE_CAPITAL_FEEDING.get()) KingdomManager.feedCapital();
        MineManager.extractAll();

    }



}
