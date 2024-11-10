package net.mcreator.reignmod.house;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashSet;
import java.util.Objects;

public class Domain implements INBTSerializable<CompoundTag> {
    private String lordUUID;
    private String knightUUID;
    private String domainTitle;
    public HashSet<String> players = new HashSet<>();

    public Domain() {
        this.domainTitle = "null";
        this.lordUUID = "null";
        this.knightUUID = "null";
    }
    public Domain(String lordUUID, String knightUUID, String knightDisplayName) {
        this.lordUUID = lordUUID;
        this.knightUUID = knightUUID;
        this.domainTitle = knightDisplayName;
    }
    public Domain(CompoundTag nbt) {
        deserializeNBT(nbt);
    }

    public String getLordUUID() { return this.lordUUID; }
    public String getKnightUUID() { return this.knightUUID; }
    public String getDomainTitle() { return this.domainTitle; }
    public HashSet<String> getPlayers() { return this.players; }

    public void setDomainTitle(String domainTitle) { this.domainTitle = domainTitle; }
    public void setLordUUID(String lordUUID) { this.lordUUID = lordUUID; }
    public void setKnightUUID(String knightUUID) { this.knightUUID = knightUUID; }
    public void setPlayers(HashSet<String> players) { this.players = players; }

    public boolean isNull() { return Objects.equals(this.knightUUID, "null"); }

    public void pushPlayer(String player) { this.players.add(player); }
    public void removePlayer(String player) { this.players.remove(player); }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("lord_uuid", this.lordUUID);
        tag.putString("knight_uuid", this.lordUUID);
        tag.putString("domain_title", this.domainTitle);

        ListTag playersTag = new ListTag();
        this.players.forEach(player -> playersTag.add(StringTag.valueOf(player)));
        tag.put("players", playersTag);

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.lordUUID = nbt.getString("lord_uuid");
        this.knightUUID = nbt.getString("knight_uuid");
        this.domainTitle = nbt.getString("domain_title");

        this.players.clear();
        ListTag playersTag = nbt.getList("players", 10);
        playersTag.forEach(tag -> this.players.add(tag.getAsString()));
    }
}