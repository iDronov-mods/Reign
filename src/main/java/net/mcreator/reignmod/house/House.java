package net.mcreator.reignmod.house;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.INBTSerializable;
import net.mcreator.reignmod.procedures.ClearHouseProcedure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

public class House implements INBTSerializable<CompoundTag> {
    private String lordUUID;
    private String houseTitle;
    private String houseColor;
    private int houseHP;
    private HashSet<String> players = new HashSet<>();
    private HashMap<String, Domain> domains = new HashMap<>();
    private int[] houseIncubatorCoordinates = new int[3];
    private int[] housePrisonCoordinates = new int[3];

    public House() {
        this.lordUUID = "null";
        this.houseTitle = "null";
        this.houseColor = "null";
        this.houseHP = -1;
    }
    public House(String lordUUID, String houseTitle, String houseColor) {
        this.lordUUID = lordUUID;
        this.houseTitle = houseTitle;
        this.houseColor = houseColor;
        this.players.add(lordUUID);
        this.houseHP = 1000;
    }
    public House(CompoundTag nbt) {
        deserializeNBT(nbt);
    }

    public String getLordUUID() {return this.lordUUID; }
    public String getHouseTitle() { return this.houseTitle; }
    public String getHouseColor() { return this.houseColor; }
    public int getHouseHP() {return this.houseHP; }
    public int[] getHouseIncubatorCoordinates() {
        return this.houseIncubatorCoordinates;
    }
    public int[] getHousePrisonCoordinates() {
        return this.housePrisonCoordinates;
    }

    public void setLordUUID(String lordUUID) { this.lordUUID = lordUUID; }
    public void setHouseTitle(String houseTitle) { this.houseTitle = houseTitle; }
    public void setHouseColor(String houseColor) { this.houseColor = houseColor; }
    public void setHouseHP(int houseHP) {
        this.houseHP = Math.min(1000, Math.max(0, houseHP));
    }
    public void setHouseIncubatorCoordinates(int[] houseIncubatorCoordinates) {
        this.houseIncubatorCoordinates = houseIncubatorCoordinates;
    }
    public void setHousePrisonCoordinates(int[] housePrisonCoordinates) {
        this.housePrisonCoordinates = housePrisonCoordinates;
    }

    public int addHouseHP(int houseHP) {
        this.setHouseHP(this.houseHP + houseHP);
        return this.houseHP;
    }
    
    public HashSet<String> getPlayers() { return this.players; }
    public HashMap<String, Domain> getDomains() { return this.domains; }
    public void setPlayers(HashSet<String> players) { this.players = players; }
    public void setDomains(HashMap<String, Domain> domains) { this.domains = domains; }

    public boolean isNull() { return Objects.equals(this.lordUUID, "null"); }

    public void pushPlayerToDomain(String knightUUID, String playerUUID) {
        if (this.domains.containsKey(knightUUID)) {
            this.domains.get(knightUUID).pushPlayer(playerUUID);
            this.players.add(playerUUID);
        }
    }
    public void removePlayerFromDomain(String knightUUID, String playerUUID) {
        if (this.domains.containsKey(knightUUID)) {
            var serverInstance = HouseSavedData.getServerInstance();
            if (serverInstance != null) {
                Player playerInstance = serverInstance.getPlayerByUUID(UUID.fromString(playerUUID));
                if (playerInstance != null) {
                    ClearHouseProcedure.execute(playerInstance);
                    HouseManager.playerPrefixSynchronize(playerInstance);
                }
            }
            this.domains.get(knightUUID).removePlayer(playerUUID);
            this.players.remove(playerUUID);
        }
    }

    public boolean containsDomain(String domainHeadUUID) { return this.domains.containsKey(domainHeadUUID); }
    public void addDomain(String knightUUID, Component knightDisplayName) {
        this.domains.putIfAbsent(knightUUID, new Domain(this.lordUUID, knightUUID, knightDisplayName));
        this.players.add(knightUUID);
    }
    public void pushDomain(Domain domain) {
        this.domains.putIfAbsent(domain.getKnightUUID(), domain);
        this.players.add(domain.getKnightUUID());
    }
    public void removeDomain(String knightUUID) {
        var serverInstance = HouseSavedData.getServerInstance();
        this.domains.get(knightUUID).getPlayers().forEach(player -> {
            if (serverInstance != null) {
                Player playerInstance = serverInstance.getPlayerByUUID(UUID.fromString(player));
                if (playerInstance != null) {
                    ClearHouseProcedure.execute(playerInstance);
                    HouseManager.playerPrefixSynchronize(playerInstance);
                }
            }
            this.players.remove(player);
        });
        this.domains.remove(knightUUID);
        HouseManager.allPlayersPrefixPacketSend();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("lord_uuid", this.lordUUID);
        tag.putString("house_title", this.houseTitle);
        tag.putString("house_color", this.houseColor);
        tag.putInt("house_hp", this.houseHP);

        tag.putIntArray("house_incubator_coordinates", this.houseIncubatorCoordinates);
        tag.putIntArray("house_prison_coordinates", this.housePrisonCoordinates);

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
        this.lordUUID = nbt.getString("lord_uuid");
        this.houseTitle = nbt.getString("house_title");
        this.houseColor = nbt.getString("house_color");
        this.houseHP = nbt.getInt("house_hp");

        this.houseIncubatorCoordinates = nbt.getIntArray("house_incubator_coordinates");
        this.housePrisonCoordinates = nbt.getIntArray("house_prison_coordinates");

        this.players.clear();
        ListTag playersTag = nbt.getList("players", 8);
        playersTag.forEach(tag -> this.players.add(tag.getAsString()));

        this.domains.clear();
        ListTag domainsTag = nbt.getList("domains", 10);
        domainsTag.forEach(tag -> pushDomain(new Domain((CompoundTag) tag)));
    }
}