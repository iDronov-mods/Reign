package net.mcreator.reignmod.claim.chunk;

import net.mcreator.reignmod.house.HouseManager;
import net.mcreator.reignmod.house.HouseSavedData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import xaero.pac.common.server.api.OpenPACServerAPI;
import xaero.pac.common.server.claims.api.IServerClaimsManagerAPI;
import xaero.pac.common.server.player.config.api.IPlayerConfigAPI;
import xaero.pac.common.server.player.config.api.IPlayerConfigManagerAPI;
import xaero.pac.common.server.player.config.api.PlayerConfigOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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
            throw new IllegalStateException("ChunkClaimsData is not initialized. Call initialize(ServerLevel) first.");
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }

    public static ServerLevel getServerInstance() {
        return serverLevelInstance;
    }

    public Map<String, ClaimData> getClaims() {
        return claims;
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

    public boolean isClaimed(@NotNull ChunkPos pos) {
        return chunkToClaimId.containsKey(pos.toLong());
    }

    public void addClaim(ClaimData claimData) {
        setClaimedAreaConfig(UUID.fromString(claimData.getOwnerId()), claimData.getClaimName(), HouseManager.getIntColorFromName(claimData.getColor()));
        claims.put(claimData.getClaimId(), claimData);
        for (Long cid : claimData.getClaimedChunks()) {
            chunkToClaimId.put(cid, claimData.getClaimId());
            syncClaimWithOPAC(claimData.getOwnerId(), cid, true);
        }
        setDirty();
    }

    public void removeClaim(String claimId) {
        ClaimData removed = claims.remove(claimId);
        if (removed != null) {
            for (Long cid : removed.getClaimedChunks()) {
                String stored = chunkToClaimId.get(cid);
                if (claimId.equals(stored)) {
                    chunkToClaimId.remove(cid);
                    syncClaimWithOPAC(removed.getOwnerId(), cid, false);
                }
            }
            setDirty();
        }
    }

    public void addChunk(String claimId, long chunkId) {
        Optional<ClaimData> territory = getClaim(claimId);
        if (territory.isEmpty() || chunkToClaimId.containsKey(chunkId)) {
            return;
        }
        territory.get().addChunk(chunkId);
        territory.get().addOuterChunk(chunkId);

        chunkToClaimId.put(chunkId, claimId);
        syncClaimWithOPAC(territory.get().getOwnerId(), chunkId, true);
        setDirty();
    }

    public void removeChunk(String claimId, long chunkId) {
        Optional<ClaimData> territory = getClaim(claimId);
        if (territory.isEmpty() || !chunkToClaimId.containsKey(chunkId)) {
            return;
        }
        territory.get().removeChunk(chunkId);
        territory.get().removeOuterChunk(chunkId);

        chunkToClaimId.remove(chunkId);
        syncClaimWithOPAC(territory.get().getOwnerId(), chunkId, false);
        setDirty();
    }


    private static void syncClaimWithOPAC(String ownerId, long chunkId, boolean isClaim) {
        if (!ModList.get().isLoaded("openpartiesandclaims")) return;

        var server = HouseSavedData.getServerInstance().getServer();

        IServerClaimsManagerAPI mgr = OpenPACServerAPI.get(server).getServerClaimsManager();
        ResourceLocation dim = Level.OVERWORLD.location();

        ChunkPos pos = new ChunkPos(chunkId);
        UUID owner = UUID.fromString(ownerId);

        if (isClaim) {
            mgr.claim(dim, owner, 0, pos.x, pos.z, false);
        } else {
            mgr.unclaim(dim, pos.x, pos.z);
        }
    }


    public static void setClaimedAreaConfig(UUID ownerUuid, String areaName, int colorInt) {
        if (!ModList.get().isLoaded("openpartiesandclaims")) return;

        var server = HouseSavedData.getServerInstance().getServer();

        IPlayerConfigManagerAPI cfgMgr = OpenPACServerAPI.get(server).getPlayerConfigs();
        IPlayerConfigAPI cfg = cfgMgr.getLoadedConfig(ownerUuid);

        cfg.tryToSet(PlayerConfigOptions.CLAIMS_NAME,  areaName);
        cfg.tryToSet(PlayerConfigOptions.CLAIMS_COLOR, colorInt);
        cfg.tryToSet(PlayerConfigOptions.PROTECT_CLAIMED_CHUNKS, false);
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
        if (ModList.get().isLoaded("openpartiesandclaims")){
            for (ClaimData cd : claims.values()) {
                for (long chunkLong : cd.getClaimedChunks()) {
                    syncClaimWithOPAC(cd.getOwnerId(), chunkLong, true);
                }
            }
        }
    }
}