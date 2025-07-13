package net.mcreator.reignmod.claim.chunk;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.world.level.ChunkPos;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ClaimData implements INBTSerializable<CompoundTag> {

    private String claimId;
    private String claimName;
    private String ownerId;
    private String ownerName;
    private String color;
    private ClaimType claimType;
    private int centerChunkX;
    private int centerChunkZ;

    private final Set<Long> claimedChunks = new HashSet<>();
    private final Set<Long> outerChunks = new HashSet<>();

    public ClaimData() {
    }

    public ClaimData(String claimId, String claimName, String ownerId, String ownerName, String color, ClaimType claimType, int centerChunkX, int centerChunkZ) {
        this.claimId = claimId;
        this.claimName = claimName;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.color = color;
        this.claimType = claimType;
        this.centerChunkX = centerChunkX;
        this.centerChunkZ = centerChunkZ;
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public String getClaimName() {
        return claimName;
    }

    public void setClaimName(String claimName) {
        this.claimName = claimName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ClaimType getClaimType() {
        return claimType;
    }

    public void setClaimType(ClaimType claimType) {
        this.claimType = claimType;
    }

    public int getCenterChunkX() {
        return centerChunkX;
    }

    public void setCenterChunkX(int centerChunkX) {
        this.centerChunkX = centerChunkX;
    }

    public int getCenterChunkZ() {
        return centerChunkZ;
    }

    public void setCenterChunkZ(int centerChunkZ) {
        this.centerChunkZ = centerChunkZ;
    }

    public boolean addChunk(long chunkId) {
        return claimedChunks.add(chunkId);
    }

    public boolean removeChunk(long chunkId) {
        boolean removed = claimedChunks.remove(chunkId);
        if (removed) {
            int[][] DIRS = { { 1, 0 }, { -1, 0 }, { 0,  1 }, { 0, -1 } };
            ChunkPos cp = new ChunkPos(chunkId);

            for (int[] d : DIRS) {
                int nx = cp.x + d[0];
                int nz = cp.z + d[1];
                long neighborId = ChunkPos.asLong(nx, nz);

                if (containsChunk(neighborId)) {
                    outerChunks.add(neighborId);
                }
            }
        }
        return removed;
    }

    public boolean containsChunk(long chunkId) {
        return claimedChunks.contains(chunkId);
    }

    public boolean addOuterChunk(long chunkId) {
        return outerChunks.add(chunkId);
    }

    public boolean removeOuterChunk(long chunkId) {
        return outerChunks.remove(chunkId);
    }

    public boolean containsOuterChunk(long chunkId) {
        return outerChunks.contains(chunkId);
    }

    public Set<Long> getClaimedChunks() {
        return Collections.unmodifiableSet(claimedChunks);
    }

    public Set<Long> getOuterChunks() { return Collections.unmodifiableSet(outerChunks); }

    // --- Сериализация ---

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("claim_id", claimId);
        tag.putString("claim_name", claimName);
        tag.putString("owner_id", ownerId);
        tag.putString("owner_name", ownerName);
        tag.putString("color", color);
        tag.putString("claim_type", claimType.name());
        tag.putInt("center_chunk_x", centerChunkX);
        tag.putInt("center_chunk_z", centerChunkZ);

        ListTag list = new ListTag();
        for (Long chunkId : claimedChunks) {
            list.add(StringTag.valueOf(chunkId.toString()));
        }
        tag.put("claimed_chunks", list);
        list = new ListTag();
        for (Long chunkId : outerChunks) {
            list.add(StringTag.valueOf(chunkId.toString()));
        }
        tag.put("outer_chunks", list);

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        this.claimId = tag.getString("claim_id");
        this.claimName = tag.getString("claim_name");
        this.ownerId = tag.getString("owner_id");
        this.ownerName = tag.getString("owner_name");
        this.color = tag.getString("color");
        this.claimType = ClaimType.valueOf(tag.getString("claim_type"));
        this.centerChunkX = tag.getInt("center_chunk_x");
        this.centerChunkZ = tag.getInt("center_chunk_z");

        this.claimedChunks.clear();
        ListTag list = tag.getList("claimed_chunks", 8);
        list.forEach(nbt -> {
            long cid = Long.parseLong(nbt.getAsString());
            this.claimedChunks.add(cid);
        });

        this.outerChunks.clear();
        list = tag.getList("outer_chunks", 8);
        list.forEach(nbt -> {
            long cid = Long.parseLong(nbt.getAsString());
            this.outerChunks.add(cid);
        });
    }
}
