package net.mcreator.reignmod.claim.chunk;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Singleton-хранилище всех приватов:
 * chunkToClaimId (для поиска claimId по чанку) и claims (для хранения ClaimData).
 */
public class ChunkClaimSavedData extends SavedData {
    private static ChunkClaimSavedData instance;
    private static ServerLevel serverLevelInstance;

    private final Map<Long, String> chunkToClaimId = new HashMap<>();
    private final Map<String, ClaimData> claims = new HashMap<>();

    private ChunkClaimSavedData() {
    }

    private ChunkClaimSavedData(CompoundTag nbt) {
        load(nbt);
    }

    public static void initialize(ServerLevel serverLevel) {
        if (instance == null) {
            instance = serverLevel.getDataStorage().computeIfAbsent(
                    ChunkClaimSavedData::new,
                    ChunkClaimSavedData::new,
                    "chunk_data"
            );
            serverLevelInstance = serverLevel;
        }
    }

    public static ChunkClaimSavedData getInstance() {
        if (instance == null) {
            instance = new ChunkClaimSavedData();
        }
        return instance;
    }

    public static ServerLevel getServerInstance() {
        return serverLevelInstance;
    }

    public Optional<ClaimData> getClaim(String claimId) {
        return Optional.ofNullable(claims.get(claimId));
    }

    public Optional<ClaimData> getClaimByChunk(int chunkX, int chunkZ) {
        long cId = ChunkPos.asLong(chunkX, chunkZ);
        return getClaimByChunk(cId);
    }

    public Optional<ClaimData> getClaimByChunk(long chunkId) {
        String claimId = chunkToClaimId.get(chunkId);
        if (claimId == null) return Optional.empty();
        return Optional.ofNullable(claims.get(claimId));
    }

    public void addClaim(ClaimData claimData) {
        claims.put(claimData.getClaimId(), claimData);
        for (Long cid : claimData.getClaimedChunks()) {
            chunkToClaimId.put(cid, claimData.getClaimId());
        }
        setDirty();
    }

    public void removeClaim(String claimId) {
        ClaimData removed = claims.remove(claimId);
        if (removed != null) {
            for (Long c : removed.getClaimedChunks()) {
                String stored = chunkToClaimId.get(c);
                if (claimId.equals(stored)) {
                    chunkToClaimId.remove(c);
                }
            }
            setDirty();
        }
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag tag) {
        // chunks
        ListTag chunkList = new ListTag();
        for (Map.Entry<Long, String> e : chunkToClaimId.entrySet()) {
            CompoundTag ct = new CompoundTag();
            ct.putLong("chunk_id", e.getKey());
            ct.putString("claim_id", e.getValue());
            chunkList.add(ct);
        }
        tag.put("chunks", chunkList);

        // claims
        CompoundTag claimsTag = new CompoundTag();
        for (Map.Entry<String, ClaimData> e : claims.entrySet()) {
            claimsTag.put(e.getKey(), e.getValue().serializeNBT());
        }
        tag.put("claims", claimsTag);

        return tag;
    }

    private void load(CompoundTag tag) {
        if (tag.contains("chunks")) {
            ListTag chunkList = tag.getList("chunks", 10);
            for (int i = 0; i < chunkList.size(); i++) {
                CompoundTag ct = chunkList.getCompound(i);
                long cId = ct.getLong("chunk_id");
                String claimId = ct.getString("claim_id");
                chunkToClaimId.put(cId, claimId);
            }
        }
        if (tag.contains("claims")) {
            CompoundTag claimsTag = tag.getCompound("claims");
            for (String key : claimsTag.getAllKeys()) {
                CompoundTag ctag = claimsTag.getCompound(key);
                ClaimData cd = new ClaimData();
                cd.deserializeNBT(ctag);

                if (!cd.getClaimId().equals(key)) {
                    cd.setClaimId(key);
                }
                claims.put(key, cd);
            }
        }
    }
}