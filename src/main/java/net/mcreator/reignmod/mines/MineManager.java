package net.mcreator.reignmod.mines;

import net.mcreator.reignmod.init.ReignModModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.Map;
import java.util.Optional;

public class MineManager {

    public enum SetMineResult {
        SUCCESS,
        SKY_NOT_CLEAR,
        MINE_NOT_FOUND,
        POSITION_ALREADY_SET
    }

    public enum AddWorkerResult {
        SUCCESS,
        NOT_SERF,
        NOT_ON_DOMAIN,
        ALREADY_WORKING,
        MINE_NOT_FOUND,
        POSITION_NOT_SET,
        NOT_MINE_ON_CORDS,
        NO_BLOCK_ENTITY,
        TOO_MANY_WORKERS
    }

    public static Optional<MineData> tryCreateMine(ChunkPos chunkPos) {
        return MineUtils.tryGenerateMine(MineSavedData.getServerInstance(), chunkPos, MineSavedData.getRandom());
    }

    public static Optional<MineType> getMineType(ChunkPos chunkPos) {
        Optional<MineData> mineDataOpt = MineSavedData.getInstance().getMineData().getMine(chunkPos);
        return mineDataOpt.map(MineData::getType);
    }

    public static Optional<Double> getMineCapacity(ChunkPos chunkPos) {
        Optional<MineData> mineDataOpt = MineSavedData.getInstance().getMineData().getMine(chunkPos);
        return mineDataOpt.map(MineData::getCapacity);
    }

    public static SetMineResult setMineBlockPos(BlockPos blockPos) {
        MineSavedData saved = MineSavedData.getInstance();
        ServerLevel level = saved.getServerLevelInstance();
        if (MineUtils.calculateSkyVisibility(level, blockPos) < 1.0f) {
            return SetMineResult.SKY_NOT_CLEAR;
        }

        ChunkPos chunkPos = new ChunkPos(blockPos);

        MineChunkData data = saved.getMineData();
        Optional<MineData> mineOpt = data.getMine(chunkPos);
        if (mineOpt.isEmpty()) {
            return SetMineResult.MINE_NOT_FOUND;
        }
        MineData mine = mineOpt.get();

        if (mine.getMineBlockPos() != null) {
            return SetMineResult.POSITION_ALREADY_SET;
        }

        mine.setMineBlockPos(blockPos);
        Optional<BlockEntity> be_opt = data.getMineBlockEntity(level, chunkPos);
        be_opt.ifPresent(be -> {be.getPersistentData().putString("type", mine.getType().name()); be.setChanged();});

        saved.setDirty();
        return SetMineResult.SUCCESS;
    }

    public static boolean extract(ChunkPos chunkPos) {
        MineSavedData saved = MineSavedData.getInstance();
        MineChunkData data = saved.getMineData();
        ServerLevel level = saved.getServerLevelInstance();

        Optional<MineData> opt = data.getMine(chunkPos);
        if (opt.isEmpty()) return false;
        MineData mine = opt.get();

        BlockPos blockPos = mine.getMineBlockPos();
        if (blockPos == null) return false;

        if (!level.getBlockState(blockPos).getBlock().equals(ReignModModBlocks.MINE.get())) {
            mine.setMineBlockPos(null);
            MineSavedData.getInstance().setDirty();
            return false;
        }

        Optional<BlockEntity> be_opt = data.getMineBlockEntity(level, chunkPos);
        if (be_opt.isEmpty()) return false;
        BlockEntity be = be_opt.get();

        float skyRatio = MineUtils.calculateSkyVisibility(level, blockPos);
        if (skyRatio == 0) return false;

        CompoundTag pd = be.getPersistentData();
        double hours = pd.getDouble("remainingHours");
        if (hours <= 0) {
            saved.clearAssignmentsForChunk(chunkPos);
            pd.putDouble("remainingHours", 0);
            pd.putDouble("workerCount", 0);
            be.setChanged();
            MineSavedData.getInstance().setDirty();
            return false;
        }

        pd.putDouble("remainingHours", hours - 1);
        be.setChanged();

        int workerCount = (int) pd.getDouble("workerCount");
        double efficiency = workerCount / (double) MineConstants.MAX_WORKERS;
        if (efficiency <= 0) return false;

        double richMultiplier = mine.getRichness() == MineRichness.RICH ? MineConstants.RICH_MULTIPLIER : 1;

        double base = MineConstants.BASE_YIELDS.getOrDefault(mine.getType(), 1.0);

        double amount = Math.max(0, base * skyRatio * efficiency * richMultiplier);

        double extracted = Math.min(amount, mine.getCapacity());
        mine.reduceCapacity(extracted);
        pd.putDouble("oreLeft", mine.getCapacity());
        pd.putDouble("stored", pd.getDouble("stored") + extracted);
        be.setChanged();

        if (mine.getCapacity() <= 0d) {
            saved.clearAssignmentsForChunk(chunkPos);
            data.removeMine(chunkPos);
        }

        saved.setDirty();
        return true;
    }

    public static AddWorkerResult addWorker(ServerPlayer player, ChunkPos chunkPos) {
        String uuid = player.getUUID().toString();
        MineSavedData saved = MineSavedData.getInstance();
        ServerLevel level = saved.getServerLevelInstance();
        Map<String, ChunkPos> assignments = saved.getPlayerAssignments();

        if (!MineUtils.isSerf(player)) {
            return AddWorkerResult.NOT_SERF;
        }
        if (!MineUtils.isOnPlayerDomain(player, chunkPos)) {
            return AddWorkerResult.NOT_ON_DOMAIN;
        }
        if (assignments.containsKey(uuid)) {
            return AddWorkerResult.ALREADY_WORKING;
        }

        MineChunkData data = saved.getMineData();
        Optional<MineData> mineOpt = data.getMine(chunkPos);
        if (mineOpt.isEmpty()) {
            return AddWorkerResult.MINE_NOT_FOUND;
        }
        MineData mine = mineOpt.get();

        BlockPos pos = mine.getMineBlockPos();
        if (pos == null) {
            return AddWorkerResult.POSITION_NOT_SET;
        }

        if (!level.getBlockState(pos).getBlock().equals(ReignModModBlocks.MINE.get())) {
            return AddWorkerResult.NOT_MINE_ON_CORDS;
        }

        BlockEntity be = level.getBlockEntity(pos);
        if (be == null) {
            return AddWorkerResult.NO_BLOCK_ENTITY;
        }

        CompoundTag pd = be.getPersistentData();
        double workerCount = pd.getDouble("workerCount");
        double remainingHours = pd.getDouble("remainingHours");
        if (workerCount >= MineConstants.MAX_WORKERS) {
            return AddWorkerResult.TOO_MANY_WORKERS;
        }

        workerCount++;
        remainingHours += MineConstants.HOURS_PER_WORKER;

        pd.putDouble("workerCount", workerCount);
        pd.putDouble("remainingHours", remainingHours);
        be.setChanged();

        assignments.put(uuid, chunkPos);
        saved.setDirty();
        return AddWorkerResult.SUCCESS;
    }

    public static void extractAll() {
        MineSavedData saved = MineSavedData.getInstance();
        MineChunkData data = saved.getMineData();

        for (ChunkPos chunkPos : data.getMines().keySet()) {
            extract(chunkPos);
        }
    }
}
