package net.mcreator.reignmod.claim.capital;

import net.minecraft.nbt.CompoundTag;

/**
 * Описывает прямоугольную область привата в столице.
 * Ширина и высота должны быть положительными и нечётными.
 */
public class TerritoryClaim {
    private final int startX, startZ, endX, endZ;
    private final int centerX, centerZ, centerY;

    public TerritoryClaim(int centerX, int centerY, int centerZ, int width, int height) {
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("Width and height must be positive!");
        if (width % 2 == 0 || height % 2 == 0)
            throw new IllegalArgumentException("Width and height must be odd numbers!");
        this.centerX = centerX;
        this.centerZ = centerZ;
        this.startX = centerX - (width / 2);
        this.startZ = centerZ - (height / 2);
        this.endX = startX + width;
        this.endZ = startZ + height;
        this.centerY = centerY;
    }

    public static TerritoryClaim deserializeNBT(CompoundTag tag) {
        int startX = tag.getInt("startX");
        int startZ = tag.getInt("startZ");
        int endX = tag.getInt("endX");
        int endZ = tag.getInt("endZ");
        int centerX = tag.getInt("centerX");
        int centerZ = tag.getInt("centerZ");
        int centerY = tag.getInt("centerY");
        int width = endX - startX;
        int height = endZ - startZ;
        return new TerritoryClaim(centerX, centerY, centerZ, width, height);
    }

    public int getStartX() {
        return startX;
    }

    public int getStartZ() {
        return startZ;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndZ() {
        return endZ;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterZ() {
        return centerZ;
    }

    public int getCenterY() {
        return centerY;
    }

    /**
     * Проверяет, находится ли блок с координатами (x, z) внутри данного привата.
     */
    public boolean contains(int x, int z) {
        return x >= startX && x < endX && z >= startZ && z < endZ;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TerritoryClaim other)) return false;
        return centerX == other.centerX && centerZ == other.centerZ && centerY == other.centerY;
    }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("startX", startX);
        tag.putInt("startZ", startZ);
        tag.putInt("endX", endX);
        tag.putInt("endZ", endZ);
        tag.putInt("centerX", centerX);
        tag.putInt("centerZ", centerZ);
        tag.putInt("centerY", centerY);
        return tag;
    }
}