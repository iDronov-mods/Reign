package net.mcreator.reignmod.mines;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.*;

public class MineChunkData {

    private final Map<ChunkPos, MineData> mines = new HashMap<>();
    private final Set<ChunkPos> checkedChunks = new HashSet<>();

    public void addMine(ChunkPos chunkPos, MineData data) {
        mines.put(chunkPos, data);
        checkedChunks.add(chunkPos);
    }

    public void removeMine(ChunkPos chunkPos) {
        mines.remove(chunkPos);
    }

    public Map<ChunkPos, MineData> getMines() {
        return mines;
    }

    public Optional<MineData> getMine(ChunkPos chunkPos) {
        return Optional.ofNullable(mines.get(chunkPos));
    }

    public boolean isChecked(ChunkPos chunkPos) {
        return checkedChunks.contains(chunkPos);
    }

    public void markChecked(ChunkPos chunkPos) {
        checkedChunks.add(chunkPos);
    }

    public void setMineBlockPos(ChunkPos chunkPos, BlockPos pos) {
        MineData mine = mines.get(chunkPos);
        if (mine != null) {
            mine.setMineBlockPos(pos);
        }
    }

    public Optional<BlockEntity> getMineBlockEntity(ServerLevel level, ChunkPos chunkPos) {
        MineData mine = mines.get(chunkPos);
        if (mine == null || mine.getMineBlockPos() == null) return Optional.empty();

        BlockPos pos = mine.getMineBlockPos();
        if (!level.isLoaded(pos)) return Optional.empty();

        BlockEntity be = level.getBlockEntity(pos);
        return Optional.ofNullable(be);
    }

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        ListTag mineList = new ListTag();

        for (Map.Entry<ChunkPos, MineData> entry : mines.entrySet()) {
            CompoundTag mineTag = entry.getValue().save();
            mineList.add(mineTag);
        }
        tag.put("mines", mineList);

        ListTag checkedTag = new ListTag();
        for (ChunkPos pos : checkedChunks) {
            CompoundTag c = new CompoundTag();
            c.putInt("x", pos.x);
            c.putInt("z", pos.z);
            checkedTag.add(c);
        }
        tag.put("checkedChunks", checkedTag);

        return tag;
    }

    public void load(CompoundTag tag) {
        mines.clear();
        checkedChunks.clear();

        ListTag mineList = tag.getList("mines", Tag.TAG_COMPOUND);
        for (Tag raw : mineList) {
            CompoundTag mineTag = (CompoundTag) raw;
            MineData mine = MineData.load(mineTag);
            mines.put(mine.getChunkPos(), mine);
        }

        ListTag checkedTag = tag.getList("checkedChunks", Tag.TAG_COMPOUND);
        for (Tag raw : checkedTag) {
            CompoundTag c = (CompoundTag) raw;
            int x = c.getInt("x");
            int z = c.getInt("z");
            checkedChunks.add(new ChunkPos(x, z));
        }
    }
}
