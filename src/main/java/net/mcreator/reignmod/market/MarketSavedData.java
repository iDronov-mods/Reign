package net.mcreator.reignmod.market;

import net.mcreator.reignmod.basics.ReignSavedData;
import net.mcreator.reignmod.kingdom.KingdomData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.mcreator.reignmod.basics.ConfigLoader;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс, хранящий динамические данные рынка – текущее количество товаров.
 * Сериализуются только currentAmount для каждого товара.
 * Остальные параметры (price, maxAmount, inPack) берутся из конфигурационного файла.
 */
public class MarketSavedData extends ReignSavedData {

    // ---------- Карта товаров ----------
    private HashMap<String, MarketItem> marketItems;
    private EnumMap<MarketItem.MarketItemType, Double> storageBarrels;

    private static MarketSavedData instance;
    private boolean requireRefresh = true;

    public MarketSavedData() {
        loadDefaults();
    }

    // Конструктор восстановления из NBT
    public MarketSavedData(CompoundTag compoundTag) {
        loadDefaults();
        if (compoundTag.contains("marketData")) {
            CompoundTag dataTag = compoundTag.getCompound("marketData");
            for (String key : dataTag.getAllKeys()) {
                double currentAmount = dataTag.getDouble(key);
                if (marketItems.containsKey(key)) {
                    marketItems.get(key).setCurrentAmount(currentAmount);
                }
            }
        }

        if (compoundTag.contains("barrelsData")) {
            CompoundTag dataTag = compoundTag.getCompound("barrelsData");
            for (String key : dataTag.getAllKeys()) {
                storageBarrels.put(MarketItem.MarketItemType.valueOf(key), dataTag.getDouble(key));
            }
        }
    }

    private void loadDefaults() {
        marketItems = new HashMap<>();
        for (Map.Entry<String, MarketItem> entry : ConfigLoader.getMarketItems().entrySet()) {
            MarketItem defaultItem = entry.getValue();
            marketItems.put(entry.getKey(), new MarketItem(
                    defaultItem.getPrice(),
                    defaultItem.getBaseAmount(),
                    defaultItem.getInPack(),
                    defaultItem.getItemType()
            ));
        }

        storageBarrels = new EnumMap<>(MarketItem.MarketItemType.class);
        for (MarketItem.MarketItemType itemType : MarketItem.MarketItemType.values()) {
            storageBarrels.put(itemType, 0.0);
        }
    }

    @Override
    protected String getDataKey() {
        return "market_data";
    }

    public static void initialize(ServerLevel serverLevel) {
        if (instance == null) {
            instance = serverLevel.getDataStorage().computeIfAbsent(
                    MarketSavedData::new,
                    MarketSavedData::new,
                    "market_data"
            );
            instance.serverLevelInstance = serverLevel;
        }
    }

    public static MarketSavedData getInstance() {
        if (instance == null) {
            throw new IllegalStateException("MarketSavedData has not been initialized. Call initialize(ServerLevel) first.");
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }

    public static ServerLevel getServerInstance() {
        return getInstance().getServerLevelInstance();
    }

    // ---------- Методы управления флагом обновления экрана ----------
    public void markRefreshNeeded() {
        this.requireRefresh = true;
    }
    public void clearRefreshFlag() {
        this.requireRefresh = false;
    }
    public boolean isRefreshRequired() {
        return requireRefresh;
    }

    // ---------- Сериализация ----------
    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag compoundTag) {
        CompoundTag dataTag = new CompoundTag();
        for (Map.Entry<String, MarketItem> entry : marketItems.entrySet()) {
            dataTag.putDouble(entry.getKey(), entry.getValue().getCurrentAmount());
        }
        compoundTag.put("marketData", dataTag);

        CompoundTag barrelsTag = new CompoundTag();
        for (Map.Entry<MarketItem.MarketItemType, Double> entry : storageBarrels.entrySet()) {
            if (entry.getValue() != null) {
                barrelsTag.putDouble(entry.getKey().name(), entry.getValue());
            }
        }
        compoundTag.put("barrelsData", dataTag);
        return compoundTag;
    }

    public HashMap<String, MarketItem> getMarketItems() {
        return marketItems;
    }

    public EnumMap<MarketItem.MarketItemType, Double> getStorageBarrels() {
        return storageBarrels;
    }
}
