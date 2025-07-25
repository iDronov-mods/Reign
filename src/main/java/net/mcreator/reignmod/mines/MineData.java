package net.mcreator.reignmod.mines;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.nbt.CompoundTag;

public class MineData {
    private final ChunkPos chunkPos;
    private final MineType type;
    private final MineRichness richness;
    private double capacity;
    private BlockPos mineBlockPos;

    public MineData(ChunkPos chunkPos, MineType type, MineRichness richness) {
        this.chunkPos = chunkPos;
        this.type = type;
        this.richness = richness;
        double baseCap = MineConstants.MAX_CAPACITY.getOrDefault(type, 0.0);
        this.capacity = baseCap * (richness == MineRichness.RICH ? MineConstants.RICH_CAPACITY_MULTIPLIER : 1.0);
        this.mineBlockPos = null;
    }

    public ChunkPos getChunkPos() {
        return chunkPos;
    }

    public MineType getType() {
        return type;
    }

    public MineRichness getRichness() {
        return richness;
    }

    public double getCapacity() {
        return capacity;
    }

    public void reduceCapacity(double amount) {
        this.capacity = Math.max(0.0, this.capacity - amount);
    }

    public BlockPos getMineBlockPos() {
        return mineBlockPos;
    }

    public void setMineBlockPos(BlockPos pos) {
        this.mineBlockPos = pos;
    }

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("chunkX", chunkPos.x);
        tag.putInt("chunkZ", chunkPos.z);
        tag.putString("type", type.name());
        tag.putString("richness", richness.name());
        tag.putDouble("capacity", capacity);

        if (mineBlockPos != null) {
            tag.putLong("minePos", mineBlockPos.asLong());
        }

        return tag;
    }

    public static MineData load(CompoundTag tag) {
        int chunkX = tag.getInt("chunkX");
        int chunkZ = tag.getInt("chunkZ");
        ChunkPos chunk = new ChunkPos(chunkX, chunkZ);

        MineType type = MineType.valueOf(tag.getString("type"));
        MineRichness richness = MineRichness.valueOf(tag.getString("richness"));

        MineData data = new MineData(chunk, type, richness);
        data.capacity = tag.getDouble("capacity");

        if (tag.contains("minePos")) {
            data.setMineBlockPos(BlockPos.of(tag.getLong("minePos")));
        }

        return data;
    }
}

