package net.mcreator.reignmod.claim.capital;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;

import java.util.HashSet;
import java.util.UUID;

/**
 * Хранит информацию о владельце привата: основной владелец и совладельцы.
 */
public class ClaimOwner {
    private final UUID mainOwner;
    private final HashSet<UUID> coOwners = new HashSet<>();

    public ClaimOwner(UUID mainOwner) {
        this.mainOwner = mainOwner;
    }

    public UUID getMainOwner() {
        return mainOwner;
    }

    public HashSet<UUID> getCoOwners() {
        return coOwners;
    }

    public void addCoOwner(UUID coOwner) {
        coOwners.add(coOwner);
    }

    public void removeCoOwner(UUID coOwner) {
        coOwners.remove(coOwner);
    }

    /**
     * Проверяет, имеет ли указанный UUID доступ (является ли основным владельцем или совладельцем).
     */
    public boolean hasAccess(UUID playerUUID) {
        return mainOwner.equals(playerUUID) || coOwners.contains(playerUUID);
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof ClaimOwner other && mainOwner.equals(other.mainOwner));
    }

    @Override
    public int hashCode() {
        return mainOwner.hashCode();
    }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("mainOwner", mainOwner.toString());
        ListTag coOwnersList = new ListTag();
        for (UUID uuid : coOwners) {
            coOwnersList.add(StringTag.valueOf(uuid.toString()));
        }
        tag.put("coOwners", coOwnersList);
        return tag;
    }

    public static ClaimOwner deserializeNBT(CompoundTag tag) {
        UUID main = UUID.fromString(tag.getString("mainOwner"));
        ClaimOwner owner = new ClaimOwner(main);
        ListTag coOwnersList = tag.getList("coOwners", 8); // 8 = строковые теги
        for (int i = 0; i < coOwnersList.size(); i++) {
            owner.addCoOwner(UUID.fromString(coOwnersList.getString(i)));
        }
        return owner;
    }
}