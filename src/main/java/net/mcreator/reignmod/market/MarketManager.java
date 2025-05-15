package net.mcreator.reignmod.market;

import net.mcreator.reignmod.network.ReignModModVariables;

import java.util.Collection;
import java.util.HashMap;

/**
 * Класс-менеджер для работы с данными рынка.
 * Все методы имеют асимптотику O(1), так как используются операции над HashMap.
 */
public class MarketManager {

    /**
     * Возвращает цену товара по его названию.
     * @param itemName имя товара (например, "minecraft:iron_sword")
     * @return цена товара или null, если товар не найден
     */
    public static Double getPrice(String itemName) {
        MarketSavedData data = MarketSavedData.getInstance();
        MarketItem item = data.getMarketItems().get(itemName);
        return (item != null) ? item.getPrice() : null;
    }

    /**
     * Возвращает максимальное количество товара по его названию.
     * @param itemName имя товара (например, "minecraft:iron_sword")
     * @return максимальное количество или null, если товар не найден
     */
    public static Double getMaxAmount(String itemName) {
        MarketSavedData data = MarketSavedData.getInstance();
        MarketItem item = data.getMarketItems().get(itemName);
        return (item != null) ? item.getMaxAmount() : null;
    }

    /**
     * Возвращает количество товара в одной пачке по его названию.
     * @param itemName имя товара (например, "minecraft:iron_sword")
     * @return количество в пачке или null, если товар не найден
     */
    public static Double getInPack(String itemName) {
        MarketSavedData data = MarketSavedData.getInstance();
        MarketItem item = data.getMarketItems().get(itemName);
        return (item != null) ? item.getInPack() : null;
    }

    /**
     * Возвращает текущее количество товара по его названию.
     * @param itemName имя товара (например, "minecraft:iron_sword")
     * @return текущее количество или null, если товар не найден
     */
    public static Double getItemCount(String itemName) {
        MarketSavedData data = MarketSavedData.getInstance();
        MarketItem item = data.getMarketItems().get(itemName);
        return (item != null) ? item.getCurrentAmount() : null;
    }

    /**
     * Проверяет, присутствует ли товар в рынке.
     * @param itemName имя товара (например, "minecraft:iron_sword")
     * @return true, если товар есть в базе, иначе false
     */
    public static boolean itemInMarket(String itemName) {
        MarketSavedData data = MarketSavedData.getInstance();
        return data.getMarketItems().containsKey(itemName);
    }

    /**
     * Возвращает словарь всех товаров.
     * @return HashMap с товарами
     */
    public static HashMap<String, MarketItem> getMarketItems() {
        return MarketSavedData.getInstance().getMarketItems();
    }

    /**
     * Возвращает коллекцию всех товаров.
     * @return коллекция MarketItem
     */
    public static Collection<MarketItem> getMarketItemsCollection() {
        return MarketSavedData.getInstance().getMarketItems().values();
    }

    /**
     * Увеличивает текущее количество указанного товара на значение, переданное вторым параметром.
     * @param itemName имя товара
     * @param amountToIncrease значение, на которое нужно увеличить
     */
    public static void increaseItemAmount(String itemName, double amountToIncrease) {
        MarketSavedData data = MarketSavedData.getInstance();
        MarketItem item = data.getMarketItems().get(itemName);
        if (item != null) {
            item.increaseCurrentAmount(amountToIncrease);
            data.markRefreshNeeded();
            data.setDirty();
        }
    }

    /**
     * Уменьшает текущее количество указанного товара на значение, переданное вторым параметром.
     * @param itemName имя товара
     * @param amountToDecrease значение, на которое нужно уменьшить
     */
    public static void decreaseItemAmount(String itemName, double amountToDecrease) {
        MarketSavedData data = MarketSavedData.getInstance();
        MarketItem item = data.getMarketItems().get(itemName);
        if (item != null) {
            item.decreaseCurrentAmount(amountToDecrease);
            data.markRefreshNeeded();
            data.setDirty();
        }
    }

    public static void markRefresh() {
        MarketSavedData.getInstance().markRefreshNeeded();
    }

    public static void clearRefresh() {
        MarketSavedData.getInstance().clearRefreshFlag();
    }

    public static boolean isRefreshNeeded() {
        return MarketSavedData.getInstance().isRefreshRequired();
    }
}