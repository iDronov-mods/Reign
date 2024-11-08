package net.mcreator.reignmod.house;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashSet;

public class Domain implements INBTSerializable<CompoundTag> {
    private String name;
    private String headUUID;
    public HashSet<String> players = new HashSet<>();

    public Domain() {
        this.name = "null";
        this.headUUID = "null";
    }
    public Domain(String name, String headUUID) {
        this.name = name;
        this.headUUID = headUUID;
    }

    public Domain(CompoundTag nbt) {
        deserializeNBT(nbt);
    }

    public String getName() { return this.name; }
    public String getHeadUUID() { return this.headUUID; }
    public HashSet<String> getPlayers() { return this.players; }

    public void setName(String name) { this.name = name; }
    public void setHeadUUID(String headUUID) { this.headUUID = headUUID; }
    public void setPlayers(HashSet<String> players) { this.players = players; }

    public void addPlayer(String player) { this.players.add(player); }
    public void removePlayer(String player) { this.players.remove(player); }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("name", this.name);
        tag.putString("head_uuid", this.headUUID);

        ListTag playersTag = new ListTag();
        this.players.forEach(player -> playersTag.add(StringTag.valueOf(player)));
        tag.put("players", playersTag);

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.name = nbt.getString("name");
        this.headUUID = nbt.getString("head_uuid");

        this.players.clear();
        ListTag playersTag = nbt.getList("players", 8);
        playersTag.forEach(tag -> this.players.add(tag.getAsString()));
    }
}
