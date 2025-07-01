package net.mcreator.reignmod.market;

public class MarketItem {

    public static double MAX_AMOUNT = 6400.0;
    public enum MarketItemType {
        LOGS,
        FUEL,
        FOOD,
        ARMOR,
        TOOLS,
        ORES,
        BLOCKS,
        OTHER,
        EXPERIENCE
    }

    private MarketItemType itemType; // Тип товара
    private double baseAmount;     // Максимальное стандартно доступное количество на рынке
    private double inPack;        // Количество предметов в одной пачке
    private double price;         // Цена товара
    private double currentAmount; // Текущее доступное количество (по умолчанию 0)

    // ---------- Конструктор MarketItem ----------
    public MarketItem(double price, double baseAmount, double inPack, MarketItemType itemType) {
        this.itemType = itemType;
        this.price = price;
        this.baseAmount = baseAmount;
        this.inPack = inPack;
        this.currentAmount = 0.0;
    }

    // ---------- Геттеры и сеттеры MarketItem ----------

    public MarketItemType getItemType() {
        return itemType;
    }
    public void setItemType(MarketItemType itemType) {
        this.itemType = itemType;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) { this.price = price; }

    public double getBaseAmount() {
        return baseAmount;
    }
    public void setBaseAmount(double baseAmount) {
        this.baseAmount = baseAmount;
    }

    public double getInPack() {
        return inPack;
    }
    public void setInPack(double inPack) {
        this.inPack = inPack;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }
    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    // ---------- Модификаторы MarketItem ----------
    // Уменьшает текущее количество на amountToDecrease (но не ниже 0)
    public void adjustCurrentAmount(double delta) {
        this.currentAmount = Math.max(0, Math.min(MAX_AMOUNT, this.currentAmount + delta));
    }
}