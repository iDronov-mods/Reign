package net.mcreator.reignmod.market;

public class MarketItem {
    private double price;         // Цена товара
    private double maxAmount;     // Максимальное доступное количество на рынке
    private double inPack;        // Количество предметов в одной пачке
    private double currentAmount; // Текущее доступное количество (по умолчанию 0)

    // ---------- Конструктор MarketItem ----------
    public MarketItem(double price, double maxAmount, double inPack) {
        this.price = price;
        this.maxAmount = maxAmount;
        this.inPack = inPack;
        this.currentAmount = 0.0;
    }

    // ---------- Геттеры и сеттеры MarketItem ----------
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) { this.price = price; }

    public double getMaxAmount() {
        return maxAmount;
    }
    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
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
    public void decreaseCurrentAmount(double amountToDecrease) {
        this.currentAmount = Math.max(0, this.currentAmount - amountToDecrease);
    }

    // Увеличивает текущее количество на amountToIncrease (но не выше maxAmount)
    public void increaseCurrentAmount(double amountToIncrease) {
        this.currentAmount = Math.min(this.maxAmount, this.currentAmount + amountToIncrease);
    }
}