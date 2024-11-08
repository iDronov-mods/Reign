package net.mcreator.reignmod.house;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashMap;
import java.util.HashSet;

public class House implements INBTSerializable<CompoundTag> {
    private String name;
    private String headUUID;
    private String color;
    public HashSet<String> players = new HashSet<>();
    public HashMap<String, Domain> domains = new HashMap<>();

    public House() {
        this.name = "null";
        this.headUUID = "null";
        this.color = "null";
    }
    public House(String name, String headUUID, String color) {
        this.name = name;
        this.headUUID = headUUID;
        this.color = color;
    }

    public House(CompoundTag nbt) {
        deserializeNBT(nbt);
    }

    public String getName() { return this.name; }
    public String getHeadUUID() { return this.headUUID; }
    public String getColor() { return this.color; }
    public HashSet<String> getPlayers() { return this.players; }
    public HashMap<String, Domain> getDomains() { return this.domains; }

    public void setName(String name) { this.name = name; }
    public void setHeadUUID(String head) { this.headUUID = head; }
    public void setColor(String color) { this.color = color; }
    public void setPlayers(HashSet<String> players) { this.players = players; }
    public void setDomains(HashMap<String, Domain> domains) { this.domains = domains; }

    public void addPlayerToDomain(String domainHeadUUID, String playerUUID) { if (domains.containsKey(domainHeadUUID)) domains.get(domainHeadUUID).addPlayer(playerUUID);}
    public void removePlayerFromDomain(String domainHeadUUID, String playerUUID) { if (domains.containsKey(domainHeadUUID)) domains.get(domainHeadUUID).removePlayer(playerUUID);}

    public void createDomain(String playerUUID, String playerName) { this.domains.putIfAbsent(playerUUID, new Domain(playerUUID, playerName)); }
    public void addDomain(Domain domain) {this.domains.putIfAbsent(domain.getHeadUUID(), domain);}
    public void removeDomain(String playerUUID) { this.domains.remove(playerUUID); }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("name", this.name);
        tag.putString("head_uuid", this.headUUID);
        tag.putString("color", this.color);

        ListTag playersTag = new ListTag();
        this.players.forEach(player -> playersTag.add(StringTag.valueOf(player)));
        tag.put("players", playersTag);

        ListTag domainsTag = new ListTag();
        this.domains.forEach((domainHead, domain) -> domainsTag.add(domain.serializeNBT()));
        tag.put("domains", domainsTag);

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.name = nbt.getString("name");
        this.headUUID = nbt.getString("head_uuid");
        this.color = nbt.getString("color");

        this.players.clear();
        ListTag playersTag = nbt.getList("players", 10);
        playersTag.forEach(tag -> this.players.add(tag.getAsString()));

        this.domains.clear();
        ListTag domainsTag = nbt.getList("domains", 10);
        domainsTag.forEach(tag -> addDomain(new Domain((CompoundTag) tag)));
    }
}