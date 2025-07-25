package net.mcreator.reignmod.mines;

import net.mcreator.reignmod.basics.ReignSavedData;
import net.mcreator.reignmod.house.House;
import net.mcreator.reignmod.house.HouseSavedData;
import net.mcreator.reignmod.mines.MineChunkData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MineSavedData extends ReignSavedData {
    public static final String ID = "mine_data";
    private static final RandomSource RANDOM = RandomSource.create();

    private final MineChunkData mineData = new MineChunkData();

    private final Map<String, ChunkPos> playerAssignments = new HashMap<>();

    private static MineSavedData instance;

    public MineSavedData() {}

    public MineSavedData(CompoundTag compoundTag) {
        mineData.load(compoundTag);

        ListTag list = compoundTag.getList("playerAssignments", Tag.TAG_COMPOUND);
        for (Tag raw : list) {
            CompoundTag entry = (CompoundTag) raw;
            String uuid = entry.getString("uuid");
            int x = entry.getInt("chunkX");
            int z = entry.getInt("chunkZ");
            playerAssignments.put(uuid, new ChunkPos(x, z));
        }
    }

    public static void initialize(ServerLevel serverLevel) {
        if (instance == null) {
            instance = serverLevel.getDataStorage().computeIfAbsent(MineSavedData::new, MineSavedData::new, ID);
            instance.serverLevelInstance = serverLevel;
        }
    }

    public static void resetInstance() {
        instance = null;
    }

    public static MineSavedData getInstance() {
        if (instance == null) {
            throw new IllegalStateException("MineSavedData has not been initialized. Call initialize(ServerLevel) first.");
        }
        return instance;
    }

    public static ServerLevel getServerInstance() {
        return getInstance().getServerLevelInstance();
    }

    public static RandomSource getRandom() {
        return RANDOM;
    }

    @Override
    protected String getDataKey() {
        return ID;
    }

    @Override
    public @NotNull CompoundTag save(CompoundTag tag) {
        tag.merge(mineData.save());

        ListTag list = new ListTag();
        for (Map.Entry<String, ChunkPos> e : playerAssignments.entrySet()) {
            CompoundTag entry = new CompoundTag();
            entry.putString("uuid", e.getKey());
            entry.putInt("chunkX", e.getValue().x);
            entry.putInt("chunkZ", e.getValue().z);
            list.add(entry);
        }
        tag.put("playerAssignments", list);
        return tag;
    }

    public MineChunkData getMineData() {
        return mineData;
    }

    public Map<String, ChunkPos> getPlayerAssignments() {
        return playerAssignments;
    }

    public void clearAssignmentsForChunk(ChunkPos chunkPos) {
        playerAssignments.entrySet().removeIf(stringChunkPosEntry -> stringChunkPosEntry.getValue().equals(chunkPos));
    }
}
