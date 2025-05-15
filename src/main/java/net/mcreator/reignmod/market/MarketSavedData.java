package net.mcreator.reignmod.market;

import net.mcreator.reignmod.basics.ReignSavedData;
import net.mcreator.reignmod.kingdom.KingdomManager;
import net.mcreator.reignmod.kingdom.KingdomSavedData;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.mcreator.reignmod.basics.ConfigLoader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
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
    }

    private void loadDefaults() {
        // Создаем глубокую копию карты, чтобы динамические данные не зависели от конфигурации
        marketItems = new HashMap<>();
        for (Map.Entry<String, MarketItem> entry : ConfigLoader.getMarketItems().entrySet()) {
            MarketItem defaultItem = entry.getValue();
            marketItems.put(entry.getKey(), new MarketItem(
                    defaultItem.getPrice(),
                    defaultItem.getMaxAmount(),
                    defaultItem.getInPack()
            ));
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
        return compoundTag;
    }

    public HashMap<String, MarketItem> getMarketItems() {
        return marketItems;
    }
}
