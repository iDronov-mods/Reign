package net.mcreator.reignmod.basics;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mcreator.reignmod.house.HouseManager;
import net.mcreator.reignmod.house.HouseSavedData;
import net.mcreator.reignmod.house.House;
import net.mcreator.reignmod.procedures.FeedHeartProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.LevelAccessor;




@Mod.EventBusSubscriber
public class HourlyEventHandler {
    // Последнее время срабатывания (в миллисекундах)
    private static long lastExecutionTime = System.currentTimeMillis();
    
    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) { // Выполняем проверку на каждом серверном тике
            long currentTime = System.currentTimeMillis();
            
            // Проверяем, прошёл ли час (3600000 мс = 1 час)
            if (currentTime - lastExecutionTime >= 2 * 3600000L) { //каждые 3 ч
                lastExecutionTime = currentTime; // Обновляем время последнего срабатывания
                triggerHourlyEvent();           // Вызываем ваше событие
            }
        }
    }
    
    private static void triggerHourlyEvent() {
        // Ваша логика срабатывания события
        System.out.println("Another real hour has passed!");
        
        // Кормление Сердец Домов:
		var houses = HouseManager.getHousesCopies();
		
		houses.forEach(house -> {
			
			int[] coords = house.getHouseIncubatorCoordinates();
			int x = coords[0];
			int y = coords[1];
			int z = coords[2];

			int addHp = (int) FeedHeartProcedure.execute(HouseSavedData.getServerInstance(), x, y, z, house.getHouseHP(), house.getDomains().size(), house.getPlayers().size());
			

			if (HouseManager.getHouseByLordUUID(house.getLordUUID()).addHouseHP(addHp) == 0){
				HouseManager.deleteHouse(house.getLordUUID());
				HouseSavedData.getServerInstance().getServer().getPlayerList().broadcastSystemMessage(Component.literal(house.getHouseTitle()+" " + (Component.translatable("translation.key.house_delete").getString())), false);

			}

		});
    }
}
