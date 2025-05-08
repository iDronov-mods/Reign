package net.mcreator.reignmod.house;

import net.mcreator.reignmod.kingdom.KingdomData;
import net.mcreator.reignmod.procedures.FoundationUpdateInfoProcedure;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.*;

/**
 * Домен (рыцарский), принадлежащий лорду.
 */
public class Domain implements INBTSerializable<CompoundTag> {
    public enum DomainDebuffs {
        disease,
        robbers
    }

    private String lordUUID;
    private String knightUUID;
    private Component domainTitle;
    private String claimId;
    private HashSet<String> players = new HashSet<>();
    private int[] domainFoundationCoordinates = new int[3];
    private final EnumMap<DomainDebuffs, Boolean> domainDebuffs = new EnumMap<>(DomainDebuffs.class);
    private final HashMap<String, Integer> suspectPlayers = new HashMap<>();

    /**
     * Новое поле domainHP (здоровье домена).
     * По умолчанию -1 в конструкторах без параметров,
     * и 300 в обычном конструкторе с lordUUID/knightUUID.
     */
    private int domainHP;

    public Domain() {
        this.domainTitle = Component.literal("");
        this.lordUUID = null;
        this.knightUUID = null;
        this.claimId = null;
        this.domainHP = -1;  // По умолчанию -1

        for (DomainDebuffs debuff : DomainDebuffs.values()) {
            this.domainDebuffs.put(debuff, false);
        }
    }

    public Domain(String lordUUID, String knightUUID, Component knightDisplayName) {
        this.lordUUID = lordUUID;
        this.knightUUID = knightUUID;
        this.domainTitle = knightDisplayName;
        this.players.add(knightUUID);
        this.claimId = null;
        this.domainHP = 300; // Стандартное значение 300

        for (DomainDebuffs debuff : DomainDebuffs.values()) {
            this.domainDebuffs.put(debuff, false);
        }
    }

    public Domain(CompoundTag nbt) {
        this.deserializeNBT(nbt);
    }

    // -------------------------------------------------
    //              Геттеры / Сеттеры
    // -------------------------------------------------
    public String getLordUUID() {
        return this.lordUUID;
    }

    public String getKnightUUID() {
        return this.knightUUID;
    }

    public Component getDomainTitle() {
        return this.domainTitle;
    }

    public HashSet<String> getPlayers() {
        return this.players;
    }

    public int[] getDomainFoundationCoordinates() {
        return domainFoundationCoordinates;
    }

    public void setDomainFoundationCoordinates(int[] coordinates) {
        this.domainFoundationCoordinates = coordinates;
        HouseSavedData.getInstance().setDirty();
    }

    /**
     * Возвращает текущее здоровье (HP) домена.
     */
    public int getDomainHP() {
        return this.domainHP;
    }

    /**
     * Устанавливает текущее здоровье домена.
     */
    public void setDomainHP(int domainHP) {
        this.domainHP = Math.min(300, Math.max(0, domainHP));

    }

    /**
     * Прибавляет (или убавляет, если отрицательное) HP к домену,
     * и возвращает итоговое значение.
     */
    public int addDomainHP(int amount) {
        this.setDomainHP(this.domainHP + amount);
        return this.domainHP;
    }

    public void setDomainTitle(Component domainTitle) {
        this.domainTitle = domainTitle;
    }

    public void setLordUUID(String lordUUID) {
        this.lordUUID = lordUUID;
    }

    public void setKnightUUID(String knightUUID) {
        this.knightUUID = knightUUID;
    }

    public void setPlayers(HashSet<String> players) {
        this.players = players;
    }

    // -------------------------------------------------
    //              Логика проверки
    // -------------------------------------------------
    public boolean isNull() {
        return Objects.equals(this.knightUUID, null);
    }

    public boolean isPlayerInDomain(String playerUUID) {
        return players.contains(playerUUID);
    }

    public void pushPlayer(String player) {
        this.players.add(player);
    }

    public void removePlayer(String player) {
        this.players.remove(player);
    }

    //--------------------------------------------------------------------------------
    //                            ПОДОЗРИТЕЛЬНЫЕ ИГРОКИ
    //--------------------------------------------------------------------------------

    public void adjustSuspicionForPlayer(String playerId, int amount) {
        int current = suspectPlayers.getOrDefault(playerId, 0);
        int updated = current + amount;

        if (updated <= 0) {
            suspectPlayers.remove(playerId);
            return;
        }
        if (updated >= 100) {
            suspectPlayers.remove(playerId);
            HouseManager.getHouseByLordUUID(getLordUUID()).addWantedPlayer(playerId);
            return;
        }
        suspectPlayers.put(playerId, updated);
        HouseSavedData.getInstance().setDirty();
    }

    public void adjustSuspicionForAll(int amount) {
        List<String> keys = new ArrayList<>(suspectPlayers.keySet());
        for (String playerId : keys) {
            adjustSuspicionForPlayer(playerId, amount);
        }
        HouseSavedData.getInstance().setDirty();
    }

    public List<Map.Entry<String, Integer>> getSortedSuspects(int maxCount) {
        Vector<Map.Entry<String, Integer>> list = new Vector<>(suspectPlayers.entrySet());
        list.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));
        if (maxCount > 0 && maxCount < list.size()) {
            return list.subList(0, maxCount);
        }
        return list;
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
    //                                 ДЕБАФФЫ ДОМЕНОВ
    //--------------------------------------------------------------------------------

    public Boolean getDebuff(DomainDebuffs debuff) {
        return domainDebuffs.get(debuff);
    }

    public void toggleOnDebuff(DomainDebuffs debuff) {
        domainDebuffs.put(debuff, true);
        HouseSavedData.getInstance().setDirty();
    }

    public void toggleOffDebuff(DomainDebuffs debuff) {
        domainDebuffs.put(debuff, false);
        HouseSavedData.getInstance().setDirty();
    }

    // -------------------------------------------------
    //    Сериализация/десериализация (INBTSerializable)
    // -------------------------------------------------
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("lord_uuid", this.lordUUID);
        tag.putString("knight_uuid", this.knightUUID);
        tag.putString("domain_title", this.domainTitle.getString());

        // Сохраняем domainHP
        tag.putInt("domain_hp", this.domainHP);

        tag.putIntArray("domain_foundation_coordinates", domainFoundationCoordinates);

        ListTag playersTag = new ListTag();
        this.players.forEach(player -> playersTag.add(StringTag.valueOf(player)));
        tag.put("players", playersTag);

        // Suspect players
        ListTag suspectList = new ListTag();
        for (Map.Entry<String, Integer> entry : suspectPlayers.entrySet()) {
            CompoundTag eTag = new CompoundTag();
            eTag.putString("player_id", entry.getKey());
            eTag.putInt("suspicion", entry.getValue());
            suspectList.add(eTag);
        }
        tag.put("suspects", suspectList);

        // Domain Debuffs
        CompoundTag debuffsTag = new CompoundTag();
        for (Map.Entry<DomainDebuffs, Boolean> entry : domainDebuffs.entrySet()) {
            if (entry.getValue() != null) {
                debuffsTag.putBoolean(entry.getKey().name(), entry.getValue());
            }
        }
        tag.put("domain_debuffs", debuffsTag);

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.lordUUID = nbt.getString("lord_uuid");
        this.knightUUID = nbt.getString("knight_uuid");
        this.domainTitle = Component.literal(nbt.getString("domain_title"));

        // Загружаем domainHP
        this.domainHP = nbt.getInt("domain_hp");

        domainFoundationCoordinates = nbt.getIntArray("domain_foundation_coordinates");

        this.players.clear();
        ListTag playersTag = nbt.getList("players", 8);
        playersTag.forEach(tag -> this.players.add(tag.getAsString()));

        // Suspects
        suspectPlayers.clear();
        if (nbt.contains("suspects")) {
            ListTag suspectList = nbt.getList("suspects", 10);
            for (int i = 0; i < suspectList.size(); i++) {
                CompoundTag eTag = suspectList.getCompound(i);
                String pid = eTag.getString("player_id");
                int susValue = eTag.getInt("suspicion");
                suspectPlayers.put(pid, susValue);
            }
        }

        // Domain Debuffs
        if (nbt.contains("domain_debuffs")) {
            CompoundTag debuffsTag = nbt.getCompound("domain_debuffs");
            for (String key : debuffsTag.getAllKeys()) {
                DomainDebuffs pos = DomainDebuffs.valueOf(key);
                domainDebuffs.put(pos, debuffsTag.getBoolean(key));
            }
        }
    }

}
