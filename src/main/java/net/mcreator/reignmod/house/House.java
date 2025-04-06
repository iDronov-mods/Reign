package net.mcreator.reignmod.house;

import net.mcreator.reignmod.procedures.ClearHouseProcedure;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.*;

/**
 * Класс, описывающий структуру Дома:
 * - Лорд, цвет, здоровье, участники и домены;
 * - Подозрительные игроки, разыскиваемые игроки;
 * - Уровень развития и привязанный claimId для чанк-привата.
 */
public class House implements INBTSerializable<CompoundTag> {

    //--------------------------------------------------------------------------------
    //                                  ПОЛЯ
    //--------------------------------------------------------------------------------

    private String lordUUID;
    private String houseTitle;
    private String houseColor;
    private String claimId;
    private int houseLevel;
    private int houseHP;
    private int[] houseIncubatorCoordinates;
    private int[] housePrisonCoordinates;
    private HashSet<String> players;
    private HashMap<String, Domain> domains;
    private HashSet<String> wantedPlayers;

    //--------------------------------------------------------------------------------
    //                                 КОНСТРУКТОРЫ
    //--------------------------------------------------------------------------------

    public House() {
        this.lordUUID = null;
        this.houseTitle = null;
        this.houseColor = null;
        this.houseHP = -1;
        this.players = new HashSet<>();
        this.domains = new HashMap<>();
        this.houseIncubatorCoordinates = new int[3];
        this.housePrisonCoordinates = new int[3];
        this.wantedPlayers = new HashSet<>();
        this.claimId = null;
        this.houseLevel = 1;
    }

    public House(String lordUUID, String houseTitle, String houseColor) {
        this.lordUUID = lordUUID;
        this.houseTitle = houseTitle;
        this.houseColor = houseColor;
        this.houseHP = 1000;
        this.players = new HashSet<>();
        this.players.add(lordUUID);
        this.domains = new HashMap<>();
        this.houseIncubatorCoordinates = new int[3];
        this.housePrisonCoordinates = new int[3];
        this.wantedPlayers = new HashSet<>();
        this.claimId = null;
        this.houseLevel = 1;
    }

    public House(CompoundTag nbt) {
        this();
        deserializeNBT(nbt);
    }

    //--------------------------------------------------------------------------------
    //                         ГЕТТЕРЫ / СЕТТЕРЫ ОСНОВНЫХ ПОЛЕЙ
    //--------------------------------------------------------------------------------

    public String getLordUUID() {
        return lordUUID;
    }

    public void setLordUUID(String lordUUID) {
        this.lordUUID = lordUUID;
    }

    public String getHouseTitle() {
        return houseTitle;
    }

    public void setHouseTitle(String houseTitle) {
        this.houseTitle = houseTitle;
    }

    public String getHouseColor() {
        return houseColor;
    }

    public void setHouseColor(String houseColor) {
        this.houseColor = houseColor;
    }

    public int getHouseHP() {
        return houseHP;
    }

    public void setHouseHP(int houseHP) {
        // Минимум 0, максимум 1000
        this.houseHP = Math.min(1000, Math.max(0, houseHP));
    }

    public int addHouseHP(int amount) {
        setHouseHP(this.houseHP + amount);
        return this.houseHP;
    }

    public HashSet<String> getPlayers() {
        return players;
    }

    public void setPlayers(HashSet<String> players) {
        this.players = players;
    }

    public HashMap<String, Domain> getDomains() {
        return domains;
    }

    public void setDomains(HashMap<String, Domain> domains) {
        this.domains = domains;
    }

    public int[] getHouseIncubatorCoordinates() {
        return houseIncubatorCoordinates;
    }

    public void setHouseIncubatorCoordinates(int[] coords) {
        this.houseIncubatorCoordinates = coords;
    }

    public int[] getHousePrisonCoordinates() {
        return housePrisonCoordinates;
    }

    public void setHousePrisonCoordinates(int[] coords) {
        this.housePrisonCoordinates = coords;
    }

    public boolean isNull() {
        return Objects.equals(lordUUID, null);
    }

    //--------------------------------------------------------------------------------
    //                         МЕТОДЫ УПРАВЛЕНИЯ ДОМЕНАМИ
    //--------------------------------------------------------------------------------

    public boolean containsDomain(String domainHeadUUID) {
        return domains.containsKey(domainHeadUUID);
    }

    public void emplaceDomain(String knightUUID, Component knightDisplayName) {
        if (domains.size() >= houseLevel) {
            return;
        }
        domains.putIfAbsent(knightUUID, new Domain(lordUUID, knightUUID, knightDisplayName));
        players.add(knightUUID);
    }

    public void pushDomain(Domain domain) {
        if (domains.size() >= houseLevel) {
            return;
        }
        domains.putIfAbsent(domain.getKnightUUID(), domain);
        players.add(domain.getKnightUUID());
    }

    public void removeDomain(String knightUUID) {
        Domain d = domains.get(knightUUID);
        if (d != null) {
            var serverInstance = HouseSavedData.getServerInstance();
            for (String playerId : d.getPlayers()) {
                if (serverInstance != null) {
                    Player p = serverInstance.getPlayerByUUID(UUID.fromString(playerId));
                    if (p != null) {
                        ClearHouseProcedure.execute(p);
                        HouseManager.playerPrefixSynchronize(p);
                    }
                }
                players.remove(playerId);
            }
        }
        domains.remove(knightUUID);
        HouseManager.allPlayersPrefixPacketSend();
    }

    //--------------------------------------------------------------------------------
    //                   МЕТОДЫ УПРАВЛЕНИЯ ИГРОКАМИ В ДОМЕНАХ
    //--------------------------------------------------------------------------------

    public void pushPlayerToDomain(String knightUUID, String playerUUID) {
        if (domains.containsKey(knightUUID)) {
            Domain dom = domains.get(knightUUID);
            if (dom.getPlayers().size() >= 5) {
                return;
            }
            dom.pushPlayer(playerUUID);
            players.add(playerUUID);
        }
    }

    public void removePlayerFromDomain(String knightUUID, String playerUUID) {
        if (domains.containsKey(knightUUID)) {
            var serverInstance = HouseSavedData.getServerInstance();
            if (serverInstance != null) {
                Player p = serverInstance.getPlayerByUUID(UUID.fromString(playerUUID));
                if (p != null) {
                    ClearHouseProcedure.execute(p);
                    HouseManager.playerPrefixSynchronize(p);
                }
            }
            domains.get(knightUUID).removePlayer(playerUUID);
            players.remove(playerUUID);
        }
    }

    //--------------------------------------------------------------------------------
    //                              ИГРОКИ В РОЗЫСКЕ
    //--------------------------------------------------------------------------------

    public HashSet<String> getWantedPlayers() {
        return wantedPlayers;
    }

    public void addWantedPlayer(String playerId) {
        wantedPlayers.add(playerId);
    }

    public void removeWantedPlayer(String playerId) {
        wantedPlayers.remove(playerId);
    }

    public boolean isWanted(String playerId) {
        return wantedPlayers.contains(playerId);
    }

    //--------------------------------------------------------------------------------
    //                                 CLAIM ID
    //--------------------------------------------------------------------------------

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    //--------------------------------------------------------------------------------
    //                           УРОВЕНЬ ДОМА (1..10)
    //--------------------------------------------------------------------------------

    public int getHouseLevel() {
        return houseLevel;
    }

    public void setHouseLevel(int newLevel) {
        this.houseLevel = Math.max(1, Math.min(10, newLevel));
    }

    public boolean canLevelUp() {
        if (houseLevel >= 10) return false;
        return (players.size() >= (houseLevel * 3));
    }

    public void levelUp() {
        if (canLevelUp()) {
            houseLevel++;
            if (houseLevel > 10) {
                houseLevel = 10;
            }
        }
    }

    public boolean canLevelDown() {
        if (houseLevel <= 1) return false;
        return (domains.size() <= (houseLevel - 1));
    }

    public void levelDown() {
        if (canLevelDown()) {
            houseLevel--;
            if (houseLevel < 1) {
                houseLevel = 1;
            }
        }
    }

    //--------------------------------------------------------------------------------
    //                        СЕРИАЛИЗАЦИЯ / ДЕСЕРИАЛИЗАЦИЯ
    //--------------------------------------------------------------------------------

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();

        tag.putString("lord_uuid", lordUUID);
        tag.putString("house_title", houseTitle);
        tag.putString("house_color", houseColor);
        tag.putInt("house_hp", houseHP);

        tag.putIntArray("house_incubator_coordinates", houseIncubatorCoordinates);
        tag.putIntArray("house_prison_coordinates", housePrisonCoordinates);

        // Players
        ListTag playersTag = new ListTag();
        for (String p : players) {
            playersTag.add(StringTag.valueOf(p));
        }
        tag.put("players", playersTag);

        // Domains
        ListTag domainsTag = new ListTag();
        for (Map.Entry<String, Domain> e : domains.entrySet()) {
            domainsTag.add(e.getValue().serializeNBT());
        }
        tag.put("domains", domainsTag);

        // Wanted players
        ListTag wantedList = new ListTag();
        for (String w : wantedPlayers) {
            wantedList.add(StringTag.valueOf(w));
        }
        tag.put("wanted", wantedList);

        // Claim ID
        tag.putString("house_claimId", (claimId == null) ? "" : claimId);

        // House level
        tag.putInt("house_level", houseLevel);

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        lordUUID = nbt.getString("lord_uuid");
        houseTitle = nbt.getString("house_title");
        houseColor = nbt.getString("house_color");
        houseHP = nbt.getInt("house_hp");

        houseIncubatorCoordinates = nbt.getIntArray("house_incubator_coordinates");
        housePrisonCoordinates = nbt.getIntArray("house_prison_coordinates");

        // Players
        players.clear();
        ListTag playersTag = nbt.getList("players", 8);
        for (int i = 0; i < playersTag.size(); i++) {
            players.add(playersTag.getString(i));
        }

        // Domains
        domains.clear();
        ListTag domainsTag = nbt.getList("domains", 10);
        for (int i = 0; i < domainsTag.size(); i++) {
            CompoundTag domainNBT = domainsTag.getCompound(i);
            pushDomain(new Domain(domainNBT));
        }

        // Wanted
        wantedPlayers.clear();
        if (nbt.contains("wanted")) {
            ListTag wantedList = nbt.getList("wanted", 8);
            for (int i = 0; i < wantedList.size(); i++) {
                wantedPlayers.add(wantedList.getString(i));
            }
        }

        // Claim ID
        String loadedClaim = nbt.getString("house_claimId");
        claimId = loadedClaim.isEmpty() ? null : loadedClaim;

        // House level
        houseLevel = nbt.getInt("house_level");
    }
}
