package net.mcreator.reignmod.basics;

import net.mcreator.reignmod.configuration.ReignCommonConfiguration;
import net.mcreator.reignmod.configuration.ReignMarketConfiguration;
import net.mcreator.reignmod.market.MarketItem;

import java.util.HashMap;

public class ConfigLoader {
    // -------------------- Переменные и структуры данных --------------------

    // Хранение данных товаров: ключ – название предмета, значение – объект с ценой и макс количеством
    private static final HashMap<String, MarketItem> marketItems = new HashMap<>();

    // ---------- Getter для карты товаров ----------
    public static HashMap<String, MarketItem> getMarketItems() {
        return marketItems;
    }


    // Статические переменные для настроек из ReignCommonConfiguration
    private static boolean DISABLE_VANILLA_VILLAGERS;
    private static boolean DISABLE_MARKET_TAX;
    private static boolean DISABLE_HOUSE_FEEDING;
    private static boolean DISABLE_DAILY_PAYOUTS;
    private static boolean DISABLE_CAPITAL_FEEDING;
    private static double HOURLY_MEAL_PERIOD;

    // ---------- Методы файла конфигурации "Общие настройки" ----------

    /**
     * Возвращает булево значение конфигурационного файла - "Выключены ли ванильные жители".
     */
    public static boolean areVillagersDisabled() {
        return DISABLE_VANILLA_VILLAGERS;
    }

    /**
     * Возвращает булево значение конфигурационного файла - "Выключены ли налоги городского рынка".
     */
    public static boolean areMarketTaxesDisabled() {
        return DISABLE_MARKET_TAX;
    }

    /**
     * Возвращает булево значение конфигурационного файла - "Выключено ли питание домов".
     */
    public static boolean isHouseFeedingDisabled() {
        return DISABLE_HOUSE_FEEDING;
    }

    /**
     * Возвращает булево значение конфигурационного файла - "Выключены ли ежедневные выплаты".
     */
    public static boolean areDailyPayoutsDisabled() {
        return DISABLE_DAILY_PAYOUTS;
    }

    public static boolean isCapitalFeedingDisabled() {
        return DISABLE_CAPITAL_FEEDING;
    }

    public static double getHourlyMealPeriod() {
        return HOURLY_MEAL_PERIOD;
    }

    // -------------------- Приватные методы класса --------------------

    /**
     * Ограничивает значение в пределах [min, max]
     */
    private static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    /**
     * Вычисляет итоговую цену:
     * 1. Множитель приводится к диапазону [0.5, 2.0]
     * 2. Базовая цена приводится к диапазону [4.0, 4096.0]
     * 3. Итоговая цена = базовая цена * множитель, и результат ограничивается диапазоном [4.0, 8192.0]
     */
    private static double computePrice(double basePrice, double multiplier) {
        double validMultiplier = clamp(multiplier, 0.5, 2.0);
        double validBasePrice = clamp(basePrice, 4.0, 4096.0);
        double computedPrice = validBasePrice * validMultiplier;
        return clamp(computedPrice, 4.0, 8192.0);
    }

    /**
     * Метод инициализации, который должен быть вызван для загрузки конфигураций.
     * Вызывает загрузку как рыночных цен, так и общих настроек.
     */
    public static void initialize() {
        loadMarketPrices();
        loadCommonConfig();
    }

    /**
     * Загружает цены и максимальное количество товаров из ReignMarketConfiguration с учётом умножения на множитель категории.
     * Для предметов из категории Tools используется значение TOOLS_MAX_AMOUNT,
     * для всех остальных – GOODS_MAX_AMOUNT.
     */
    private static void loadMarketPrices() {
        marketItems.clear();

        // Получаем максимальные количества из конфигурации
        double toolsMaxAmount = clamp(ReignMarketConfiguration.TOOLS_MAX_AMOUNT.get(), 1.0, 1023.0);
        double goodsMaxAmount = clamp(ReignMarketConfiguration.GOODS_MAX_AMOUNT.get(), 1.0, 1023.0);

        // Logs
        marketItems.put("minecraft:oak_log", new MarketItem(
                computePrice(ReignMarketConfiguration.OAK_LOG.get(), ReignMarketConfiguration.LOGS_MULTIPLIER.get()),
                goodsMaxAmount, 8.0));
        marketItems.put("minecraft:spruce_log", new MarketItem(
                computePrice(ReignMarketConfiguration.SPRUCE_LOG.get(), ReignMarketConfiguration.LOGS_MULTIPLIER.get()),
                goodsMaxAmount, 8.0));
        marketItems.put("minecraft:birch_log", new MarketItem(
                computePrice(ReignMarketConfiguration.BIRCH_LOG.get(), ReignMarketConfiguration.LOGS_MULTIPLIER.get()),
                goodsMaxAmount, 8.0));
        marketItems.put("minecraft:acacia_log", new MarketItem(
                computePrice(ReignMarketConfiguration.ACACIA_LOG.get(), ReignMarketConfiguration.LOGS_MULTIPLIER.get()),
                goodsMaxAmount, 8.0));
        marketItems.put("minecraft:dark_oak_log", new MarketItem(
                computePrice(ReignMarketConfiguration.DARK_OAK_LOG.get(), ReignMarketConfiguration.LOGS_MULTIPLIER.get()),
                goodsMaxAmount, 8.0));
        marketItems.put("minecraft:jungle_log", new MarketItem(
                computePrice(ReignMarketConfiguration.JUNGLE_LOG.get(), ReignMarketConfiguration.LOGS_MULTIPLIER.get()),
                goodsMaxAmount, 8.0));
        marketItems.put("minecraft:mangrove_log", new MarketItem(
                computePrice(ReignMarketConfiguration.MANGROVE_LOG.get(), ReignMarketConfiguration.LOGS_MULTIPLIER.get()),
                goodsMaxAmount, 8.0));
        marketItems.put("minecraft:cherry_log", new MarketItem(
                computePrice(ReignMarketConfiguration.CHERRY_LOG.get(), ReignMarketConfiguration.LOGS_MULTIPLIER.get()),
                goodsMaxAmount, 8.0));


        // Fuel
        marketItems.put("minecraft:coal", new MarketItem(
                computePrice(ReignMarketConfiguration.COAL.get(), ReignMarketConfiguration.FUEL_MULTIPLIER.get()),
                goodsMaxAmount, 4.0));
        marketItems.put("minecraft:charcoal", new MarketItem(
                computePrice(ReignMarketConfiguration.CHARCOAL.get(), ReignMarketConfiguration.FUEL_MULTIPLIER.get()),
                goodsMaxAmount, 4.0));


        // Ores
        marketItems.put("minecraft:raw_copper", new MarketItem(
                computePrice(ReignMarketConfiguration.RAW_COPPER.get(), ReignMarketConfiguration.ORES_MULTIPLIER.get()),
                goodsMaxAmount, 4.0));
        marketItems.put("minecraft:raw_iron", new MarketItem(
                computePrice(ReignMarketConfiguration.RAW_IRON.get(), ReignMarketConfiguration.ORES_MULTIPLIER.get()),
                goodsMaxAmount, 2.0));
        marketItems.put("minecraft:raw_gold", new MarketItem(
                computePrice(ReignMarketConfiguration.RAW_GOLD.get(), ReignMarketConfiguration.ORES_MULTIPLIER.get()),
                goodsMaxAmount, 1.0));
        marketItems.put("minecraft:diamond", new MarketItem(
                computePrice(ReignMarketConfiguration.DIAMOND.get(), ReignMarketConfiguration.ORES_MULTIPLIER.get()),
                goodsMaxAmount, 1.0));
        marketItems.put("minecraft:lapis_lazuli", new MarketItem(
                computePrice(ReignMarketConfiguration.LAPIS_LAZULI.get(), ReignMarketConfiguration.ORES_MULTIPLIER.get()),
                goodsMaxAmount, 4.0));
        marketItems.put("minecraft:redstone", new MarketItem(
                computePrice(ReignMarketConfiguration.REDSTONE.get(), ReignMarketConfiguration.ORES_MULTIPLIER.get()),
                goodsMaxAmount, 8.0));
        marketItems.put("minecraft:emerald", new MarketItem(
                computePrice(ReignMarketConfiguration.EMERALD.get(), ReignMarketConfiguration.ORES_MULTIPLIER.get()),
                goodsMaxAmount, 1.0));
        marketItems.put("minecraft:netherite_scrap", new MarketItem(
                computePrice(ReignMarketConfiguration.NETHERITE_SCRAP.get(), ReignMarketConfiguration.ORES_MULTIPLIER.get()),
                goodsMaxAmount, 1.0));

        // Ingots
        marketItems.put("minecraft:copper_ingot", new MarketItem(
                computePrice(ReignMarketConfiguration.COPPER_INGOT.get(), ReignMarketConfiguration.INGOTS_MULTIPLIER.get()),
                goodsMaxAmount, 2.0));
        marketItems.put("minecraft:iron_ingot", new MarketItem(
                computePrice(ReignMarketConfiguration.IRON_INGOT.get(), ReignMarketConfiguration.INGOTS_MULTIPLIER.get()),
                goodsMaxAmount, 2.0));
        marketItems.put("minecraft:gold_ingot", new MarketItem(
                computePrice(ReignMarketConfiguration.GOLD_INGOT.get(), ReignMarketConfiguration.INGOTS_MULTIPLIER.get()),
                goodsMaxAmount, 1.0));
        marketItems.put("minecraft:netherite_ingot", new MarketItem(
                computePrice(ReignMarketConfiguration.NETHERITE_INGOT.get(), ReignMarketConfiguration.INGOTS_MULTIPLIER.get()),
                goodsMaxAmount, 1.0));

        // Tools
        marketItems.put("minecraft:iron_sword", new MarketItem(
                computePrice(ReignMarketConfiguration.IRON_SWORD.get(), ReignMarketConfiguration.TOOLS_MULTIPLIER.get()),
                toolsMaxAmount, 1.0));
        marketItems.put("minecraft:iron_pickaxe", new MarketItem(
                computePrice(ReignMarketConfiguration.IRON_PICKAXE.get(), ReignMarketConfiguration.TOOLS_MULTIPLIER.get()),
                toolsMaxAmount, 1.0));
        marketItems.put("minecraft:iron_axe", new MarketItem(
                computePrice(ReignMarketConfiguration.IRON_AXE.get(), ReignMarketConfiguration.TOOLS_MULTIPLIER.get()),
                toolsMaxAmount, 1.0));
        marketItems.put("minecraft:iron_shovel", new MarketItem(
                computePrice(ReignMarketConfiguration.IRON_SHOVEL.get(), ReignMarketConfiguration.TOOLS_MULTIPLIER.get()),
                toolsMaxAmount, 1.0));
        marketItems.put("minecraft:iron_helmet", new MarketItem(
                computePrice(ReignMarketConfiguration.IRON_HELMET.get(), ReignMarketConfiguration.TOOLS_MULTIPLIER.get()),
                toolsMaxAmount, 1.0));
        marketItems.put("minecraft:iron_chestplate", new MarketItem(
                computePrice(ReignMarketConfiguration.IRON_CHESTPLATE.get(), ReignMarketConfiguration.TOOLS_MULTIPLIER.get()),
                toolsMaxAmount, 1.0));
        marketItems.put("minecraft:iron_leggings", new MarketItem(
                computePrice(ReignMarketConfiguration.IRON_LEGGINGS.get(), ReignMarketConfiguration.TOOLS_MULTIPLIER.get()),
                toolsMaxAmount, 1.0));
        marketItems.put("minecraft:iron_boots", new MarketItem(
                computePrice(ReignMarketConfiguration.IRON_BOOTS.get(), ReignMarketConfiguration.TOOLS_MULTIPLIER.get()),
                toolsMaxAmount, 1.0));
        marketItems.put("minecraft:diamond_sword", new MarketItem(
                computePrice(ReignMarketConfiguration.DIAMOND_SWORD.get(), ReignMarketConfiguration.TOOLS_MULTIPLIER.get()),
                toolsMaxAmount, 1.0));
        marketItems.put("minecraft:diamond_pickaxe", new MarketItem(
                computePrice(ReignMarketConfiguration.DIAMOND_PICKAXE.get(), ReignMarketConfiguration.TOOLS_MULTIPLIER.get()),
                toolsMaxAmount, 1.0));
        marketItems.put("minecraft:diamond_axe", new MarketItem(
                computePrice(ReignMarketConfiguration.DIAMOND_AXE.get(), ReignMarketConfiguration.TOOLS_MULTIPLIER.get()),
                toolsMaxAmount, 1.0));
        marketItems.put("minecraft:diamond_shovel", new MarketItem(
                computePrice(ReignMarketConfiguration.DIAMOND_SHOVEL.get(), ReignMarketConfiguration.TOOLS_MULTIPLIER.get()),
                toolsMaxAmount, 1.0));
        marketItems.put("minecraft:diamond_helmet", new MarketItem(
                computePrice(ReignMarketConfiguration.DIAMOND_HELMET.get(), ReignMarketConfiguration.TOOLS_MULTIPLIER.get()),
                toolsMaxAmount, 1.0));
        marketItems.put("minecraft:diamond_chestplate", new MarketItem(
                computePrice(ReignMarketConfiguration.DIAMOND_CHESTPLATE.get(), ReignMarketConfiguration.TOOLS_MULTIPLIER.get()),
                toolsMaxAmount, 1.0));
        marketItems.put("minecraft:diamond_leggings", new MarketItem(
                computePrice(ReignMarketConfiguration.DIAMOND_LEGGINGS.get(), ReignMarketConfiguration.TOOLS_MULTIPLIER.get()),
                toolsMaxAmount, 1.0));
        marketItems.put("minecraft:diamond_boots", new MarketItem(
                computePrice(ReignMarketConfiguration.DIAMOND_BOOTS.get(), ReignMarketConfiguration.TOOLS_MULTIPLIER.get()),
                toolsMaxAmount, 1.0));
        marketItems.put("minecraft:shield", new MarketItem(
                computePrice(ReignMarketConfiguration.SHIELD.get(), ReignMarketConfiguration.TOOLS_MULTIPLIER.get()),
                toolsMaxAmount, 1.0));
        marketItems.put("minecraft:shears", new MarketItem(
                computePrice(ReignMarketConfiguration.SHEARS.get(), ReignMarketConfiguration.TOOLS_MULTIPLIER.get()),
                toolsMaxAmount, 1.0));
        marketItems.put("minecraft:bow", new MarketItem(
                computePrice(ReignMarketConfiguration.BOW.get(), ReignMarketConfiguration.TOOLS_MULTIPLIER.get()),
                toolsMaxAmount, 1.0));

        // Food
        marketItems.put("minecraft:bread", new MarketItem(
                computePrice(ReignMarketConfiguration.BREAD.get(), ReignMarketConfiguration.FOOD_MULTIPLIER.get()),
                goodsMaxAmount, 4.0));
        marketItems.put("minecraft:baked_potato", new MarketItem(
                computePrice(ReignMarketConfiguration.BAKED_POTATO.get(), ReignMarketConfiguration.FOOD_MULTIPLIER.get()),
                goodsMaxAmount, 4.0));
        marketItems.put("minecraft:pumpkin_pie", new MarketItem(
                computePrice(ReignMarketConfiguration.PUMPKIN_PIE.get(), ReignMarketConfiguration.FOOD_MULTIPLIER.get()),
                goodsMaxAmount, 2.0));
        marketItems.put("minecraft:carrot", new MarketItem(
                computePrice(ReignMarketConfiguration.CARROT.get(), ReignMarketConfiguration.FOOD_MULTIPLIER.get()),
                goodsMaxAmount, 6.0));
        marketItems.put("minecraft:cooked_beef", new MarketItem(
                computePrice(ReignMarketConfiguration.COOKED_BEEF.get(), ReignMarketConfiguration.FOOD_MULTIPLIER.get()),
                goodsMaxAmount, 2.0));
        marketItems.put("minecraft:cooked_porkchop", new MarketItem(
                computePrice(ReignMarketConfiguration.COOKED_PORKCHOP.get(), ReignMarketConfiguration.FOOD_MULTIPLIER.get()),
                goodsMaxAmount, 2.0));
        marketItems.put("minecraft:cooked_chicken", new MarketItem(
                computePrice(ReignMarketConfiguration.COOKED_CHICKEN.get(), ReignMarketConfiguration.FOOD_MULTIPLIER.get()),
                goodsMaxAmount, 3.0));
        marketItems.put("minecraft:cooked_cod", new MarketItem(
                computePrice(ReignMarketConfiguration.COOKED_COD.get(), ReignMarketConfiguration.FOOD_MULTIPLIER.get()),
                goodsMaxAmount, 4.0));
        marketItems.put("minecraft:hay_block", new MarketItem(
                computePrice(ReignMarketConfiguration.HAY_BLOCK.get(), ReignMarketConfiguration.FOOD_MULTIPLIER.get()),
                goodsMaxAmount, 1.0));
        marketItems.put("minecraft:honey_bottle", new MarketItem(
                computePrice(ReignMarketConfiguration.HONEY_BOTTLE.get(), ReignMarketConfiguration.FOOD_MULTIPLIER.get()),
                goodsMaxAmount, 1.0));

        // Blocks
        marketItems.put("minecraft:cobblestone", new MarketItem(
                computePrice(ReignMarketConfiguration.COBBLESTONE.get(), ReignMarketConfiguration.BLOCKS_MULTIPLIER.get()),
                goodsMaxAmount, 16.0));
        marketItems.put("minecraft:sand", new MarketItem(
                computePrice(ReignMarketConfiguration.SAND.get(), ReignMarketConfiguration.BLOCKS_MULTIPLIER.get()),
                goodsMaxAmount, 16.0));
        marketItems.put("minecraft:white_wool", new MarketItem(
                computePrice(ReignMarketConfiguration.WHITE_WOOL.get(), ReignMarketConfiguration.BLOCKS_MULTIPLIER.get()),
                goodsMaxAmount, 8.0));
        marketItems.put("minecraft:clay", new MarketItem(
                computePrice(ReignMarketConfiguration.CLAY.get(), ReignMarketConfiguration.BLOCKS_MULTIPLIER.get()),
                goodsMaxAmount, 2.0));

        // Other
        marketItems.put("minecraft:glass_bottle", new MarketItem(
                computePrice(ReignMarketConfiguration.GLASS_BOTTLE.get(), ReignMarketConfiguration.OTHER_MULTIPLIER.get()),
                goodsMaxAmount, 8.0));
        marketItems.put("minecraft:arrow", new MarketItem(
                computePrice(ReignMarketConfiguration.ARROW.get(), ReignMarketConfiguration.OTHER_MULTIPLIER.get()),
                goodsMaxAmount, 4.0));
        marketItems.put("minecraft:experience_bottle", new MarketItem(
                computePrice(ReignMarketConfiguration.EXPERIENCE_BOTTLE.get(), 1.0),
                goodsMaxAmount, 1.0));
        marketItems.put("minecraft:gunpowder", new MarketItem(
                computePrice(ReignMarketConfiguration.GUNPOWDER.get(), ReignMarketConfiguration.OTHER_MULTIPLIER.get()),
                goodsMaxAmount, 1.0));
        marketItems.put("minecraft:bone", new MarketItem(
                computePrice(ReignMarketConfiguration.BONE.get(), ReignMarketConfiguration.OTHER_MULTIPLIER.get()),
                goodsMaxAmount, 1.0));
        marketItems.put("minecraft:string", new MarketItem(
                computePrice(ReignMarketConfiguration.STRING.get(), ReignMarketConfiguration.OTHER_MULTIPLIER.get()),
                goodsMaxAmount, 1.0));
        marketItems.put("minecraft:slime_ball", new MarketItem(
                computePrice(ReignMarketConfiguration.SLIME_BALL.get(), ReignMarketConfiguration.OTHER_MULTIPLIER.get()),
                goodsMaxAmount, 1.0));
        marketItems.put("minecraft:paper", new MarketItem(
                computePrice(ReignMarketConfiguration.PAPER.get(), ReignMarketConfiguration.OTHER_MULTIPLIER.get()),
                goodsMaxAmount, 3.0));
        marketItems.put("minecraft:feather", new MarketItem(
                computePrice(ReignMarketConfiguration.FEATHER.get(), ReignMarketConfiguration.OTHER_MULTIPLIER.get()),
                goodsMaxAmount, 1.0));
        marketItems.put("minecraft:glowstone_dust", new MarketItem(
                computePrice(ReignMarketConfiguration.GLOWSTONE_DUST.get(), ReignMarketConfiguration.OTHER_MULTIPLIER.get()),
                goodsMaxAmount, 1.0));
        marketItems.put("minecraft:ender_pearl", new MarketItem(
                computePrice(ReignMarketConfiguration.ENDER_PEARL.get(), ReignMarketConfiguration.OTHER_MULTIPLIER.get()),
                goodsMaxAmount, 1.0));
        marketItems.put("minecraft:leather", new MarketItem(
                computePrice(ReignMarketConfiguration.ENDER_PEARL.get(), ReignMarketConfiguration.OTHER_MULTIPLIER.get()),
                goodsMaxAmount, 1.0));
        marketItems.put("minecraft:blaze_rod", new MarketItem(
                computePrice(ReignMarketConfiguration.BLAZE_ROD.get(), ReignMarketConfiguration.OTHER_MULTIPLIER.get()),
                goodsMaxAmount, 1.0));


    }

    /**
     * Загружает настройки из ReignCommonConfiguration и сохраняет их
     * в отдельных статических булевых переменных.
     */
    private static void loadCommonConfig() {
        DISABLE_VANILLA_VILLAGERS = ReignCommonConfiguration.DISABLE_VANILLA_VILLAGERS.get();
        DISABLE_MARKET_TAX = ReignCommonConfiguration.DISABLE_MARKET_TAX.get();
        DISABLE_HOUSE_FEEDING = ReignCommonConfiguration.DISABLE_HOUSE_FEEDING.get();
        DISABLE_DAILY_PAYOUTS = ReignCommonConfiguration.DISABLE_DAILY_PAYOUTS.get();
        DISABLE_CAPITAL_FEEDING = ReignCommonConfiguration.DISABLE_CAPITAL_FEEDING.get();
        HOURLY_MEAL_PERIOD = ReignCommonConfiguration.HOURLY_MEAL_PERIOD.get();
    }
}