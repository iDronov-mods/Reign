package net.mcreator.reignmod.claim.chunk;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashSet;
import java.util.Set;

/**
 * Хранит данные об одном «приватном регионе»:
 * claimId, ownerId, claimType, координаты центра, и набор занятых чанков.
 */
public class ClaimData implements INBTSerializable<CompoundTag> {

    private final Set<Long> claimedChunks = new HashSet<>();
    private String claimId;
    private String ownerId;
    private ClaimType claimType;
    private int centerChunkX;
    private int centerChunkZ;

    public ClaimData() {
    }

    public ClaimData(String claimId, String ownerId, ClaimType claimType, int centerChunkX, int centerChunkZ) {
        this.claimId = claimId;
        this.ownerId = ownerId;
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

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
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

    public Set<Long> getClaimedChunks() {
        return claimedChunks;
    }

    // --- Сериализация ---

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("claim_id", claimId);
        tag.putString("owner_id", ownerId);
        tag.putString("claim_type", claimType.name());
        tag.putInt("center_chunk_x", centerChunkX);
        tag.putInt("center_chunk_z", centerChunkZ);

        ListTag list = new ListTag();
        for (Long chunkId : claimedChunks) {
            list.add(StringTag.valueOf(chunkId.toString()));
        }
        tag.put("claimed_chunks", list);

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        this.claimId = tag.getString("claim_id");
        this.ownerId = tag.getString("owner_id");
        this.claimType = ClaimType.valueOf(tag.getString("claim_type"));
        this.centerChunkX = tag.getInt("center_chunk_x");
        this.centerChunkZ = tag.getInt("center_chunk_z");

        this.claimedChunks.clear();
        ListTag list = tag.getList("claimed_chunks", 8);
        list.forEach(nbt -> {
            long cid = Long.parseLong(nbt.getAsString());
            this.claimedChunks.add(cid);
        });
    }
}
