package net.mcreator.reignmod.house;

import net.mcreator.reignmod.procedures.ClearHouseProcedure;
import net.mcreator.reignmod.procedures.IncubatorUpdateInfoProcedure;
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
    private String claimId = null;
    private int houseHP;
    private HashSet<String> players;
    private int[] housePlusCoordinates = new int[3];
    private int[] houseIncubatorCoordinates = new int[3];
    private int[] housePrisonCoordinates = new int[3];
    private int[] houseStrategyBlockCoordinates = new int[3];
    private final EnumMap<HouseNeedType, Integer> needs = new EnumMap<>(HouseNeedType.class);
    private final HashMap<String, Domain> domains = new HashMap<>();
    private final HashSet<String> wantedPlayers = new HashSet<>();
    private Boolean trustInIndirectVassals = false;

    public final static int MAX_NEED_AMOUNT = 4096;

    //--------------------------------------------------------------------------------
    //                                 КОНСТРУКТОРЫ И ОПЕРАТОРЫ
    //--------------------------------------------------------------------------------

    public House() {
        this.lordUUID = null;
        this.houseTitle = null;
        this.houseColor = null;
        this.houseHP = -1;
        this.players = new HashSet<>();
    }

    public House(String lordUUID, String houseTitle, String houseColor) {
        this.lordUUID = lordUUID;
        this.houseTitle = houseTitle;
        this.houseColor = houseColor;
        this.houseHP = 1000;
        this.players = new HashSet<>();
        this.players.add(lordUUID);
    }

    public House(CompoundTag nbt) {
        this();
        deserializeNBT(nbt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof House other)) return false;
        return Objects.equals(this.lordUUID, other.lordUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lordUUID);
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

    public String getHouseTitleWithColor() {
        return HouseManager.getHouseColorCode(getHouseColor()) + getHouseTitle() + "§r";
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


    public int[] getHousePlusCoordinates() {
        return housePlusCoordinates;
    }

    public void setHousePlusCoordinates(int[] coordinates) {
        this.housePlusCoordinates = coordinates;
        HouseSavedData.getInstance().setDirty();
    }


    public int[] getHouseIncubatorCoordinates() {
        return houseIncubatorCoordinates;
    }

    public void setHouseIncubatorCoordinates(int[] coordinates) {
        this.houseIncubatorCoordinates = coordinates;
        HouseSavedData.getInstance().setDirty();
    }


    public int[] getHousePrisonCoordinates() {
        return housePrisonCoordinates;
    }

    public void setHousePrisonCoordinates(int[] coordinates) {
        this.housePrisonCoordinates = coordinates;
        HouseSavedData.getInstance().setDirty();
    }

    public int[] getHouseStrategyBlockCoordinates() {
        return houseStrategyBlockCoordinates;
    }

    public void setHouseStrategyBlockCoordinates(int[] coordinates) {
        this.houseStrategyBlockCoordinates = coordinates;
        HouseSavedData.getInstance().setDirty();
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
        if (domains.size() >= getHouseLevel()) {
            return;
        }
        domains.putIfAbsent(knightUUID, new Domain(lordUUID, knightUUID, knightDisplayName));
        players.add(knightUUID);
    }

    public void pushDomain(Domain domain) {
        if (domains.size() >= getHouseLevel()) {
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
            if (Objects.equals(knightUUID, lordUUID) ? dom.getPlayers().size() >= 8 : dom.getPlayers().size() >= 5) {
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
        getDomains().forEach((k, v) -> v.adjustSuspicionForPlayer(playerId, -100));
    }

    public void removeWantedPlayer(String playerId) {
        wantedPlayers.remove(playerId);
    }

    public boolean isWanted(String playerId) {
        return wantedPlayers.contains(playerId);
    }

    //--------------------------------------------------------------------------------
    //                                 ПОТРЕБНОСТИ
    //--------------------------------------------------------------------------------

    public int getNeed(HouseNeedType type) {
        return needs.getOrDefault(type, 0);
    }

    public void setNeed(HouseNeedType type, int value) {
        needs.put(type, Math.min(MAX_NEED_AMOUNT, Math.max(0, value)));
    }

    public int adjustNeed(HouseNeedType type, int delta) {
        int newValue = getNeed(type) + delta;
        setNeed(type, newValue);
        return getNeed(type);
    }

    public int getFreeNeedCapacity(HouseNeedType type){
        return MAX_NEED_AMOUNT - getNeed(type);
    }

    public void updateIncubatorInfo(){
        IncubatorUpdateInfoProcedure.execute(HouseSavedData.getServerInstance(), houseIncubatorCoordinates[0], houseIncubatorCoordinates[1], houseIncubatorCoordinates[2], lordUUID);
    }

    //--------------------------------------------------------------------------------
    //                                 CLAIM ID
    //--------------------------------------------------------------------------------

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
        HouseSavedData.getInstance().setDirty();
    }

    //--------------------------------------------------------------------------------
    //                           УРОВЕНЬ ДОМА (1..10)
    //--------------------------------------------------------------------------------

    public int getHouseLevel() {
        return Math.min(10, 1 + players.size() / 3) ;
    }

    public boolean canCreateDomain() {
        return domains.size() < getHouseLevel();
    }
    //--------------------------------------------------------------------------------
    //                                 ДЕБАФФЫ ДОМЕНОВ
    //--------------------------------------------------------------------------------

    public Optional<Boolean> getDebuff(String knightUUID, Domain.DomainDebuffs debuff) {
        if (domains.containsKey(knightUUID)) {
            return Optional.ofNullable(domains.get(knightUUID).getDebuff(debuff));
        }
        return Optional.empty();
    }

    public boolean toggleOnDebuff(String knightUUID, Domain.DomainDebuffs debuff) {
        if (domains.containsKey(knightUUID)) {
            domains.get(knightUUID).toggleOnDebuff(debuff);
            return true;
        }
        return false;
    }

    public boolean toggleOffDebuff(String knightUUID, Domain.DomainDebuffs debuff) {
        if (domains.containsKey(knightUUID)) {
            domains.get(knightUUID).toggleOffDebuff(debuff);
            return true;
        }
        return false;
    }

    //--------------------------------------------------------------------------------
    //                                 ВЕРА В ВАССАЛОВ
    //--------------------------------------------------------------------------------

    public boolean isIndirectVassalsBeTrusted() {
        return this.trustInIndirectVassals;
    }

    public void toggleOnIndirectVassalsTrust() {
       this.trustInIndirectVassals = true;
        HouseSavedData.getInstance().setDirty();
    }

    public void toggleOffIndirectVassalsTrust() {
        this.trustInIndirectVassals = false;
        HouseSavedData.getInstance().setDirty();
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

        tag.putIntArray("house_plus_coordinates", housePlusCoordinates);
        tag.putIntArray("house_incubator_coordinates", houseIncubatorCoordinates);
        tag.putIntArray("house_prison_coordinates", housePrisonCoordinates);
        tag.putIntArray("house_strategy_block_coordinates", houseStrategyBlockCoordinates);

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

        // Needs
        CompoundTag needsTag = new CompoundTag();
        for (HouseNeedType type : HouseNeedType.values()) {
            needsTag.putInt(type.name(), getNeed(type));
        }
        tag.put("house_needs", needsTag);

        // Claim ID
        tag.putString("house_claim_id", (claimId == null) ? "" : claimId);

        // Trust in indirect vassals
        tag.putBoolean("trust_in_indirect_vassals", trustInIndirectVassals);

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        lordUUID = nbt.getString("lord_uuid");
        houseTitle = nbt.getString("house_title");
        houseColor = nbt.getString("house_color");
        houseHP = nbt.getInt("house_hp");

        housePlusCoordinates = nbt.getIntArray("house_plus_coordinates");
        houseIncubatorCoordinates = nbt.getIntArray("house_incubator_coordinates");
        housePrisonCoordinates = nbt.getIntArray("house_prison_coordinates");
        houseStrategyBlockCoordinates = nbt.getIntArray("house_strategy_block_coordinates");

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
            var domain = new Domain(domainNBT);
            pushDomain(domain);
        }

        // Wanted
        wantedPlayers.clear();
        if (nbt.contains("wanted")) {
            ListTag wantedList = nbt.getList("wanted", 8);
            for (int i = 0; i < wantedList.size(); i++) {
                wantedPlayers.add(wantedList.getString(i));
            }
        }

        // Needs
        if (nbt.contains("house_needs")) {
            CompoundTag needsTag = nbt.getCompound("house_needs");
            for (HouseNeedType type : HouseNeedType.values()) {
                setNeed(type, needsTag.getInt(type.name()));
            }
        }

        // Claim ID
        String loadedClaim = nbt.getString("house_claim_id");
        claimId = loadedClaim.isEmpty() ? null : loadedClaim;

        // Trust in indirect vassals
        trustInIndirectVassals = nbt.getBoolean("trust_in_indirect_vassals");
    }
}
