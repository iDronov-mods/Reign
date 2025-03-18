package net.mcreator.reignmod.house;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashSet;
import java.util.Objects;

/**
 * Домен (рыцарский), принадлежащий лорду.
 */
public class Domain implements INBTSerializable<CompoundTag> {
    private String lordUUID;
    private String knightUUID;
    private Component domainTitle;
    private HashSet<String> players = new HashSet<>();

    /**
     * Новое поле domainHP (здоровье домена).
     * По умолчанию -1 в конструкторах без параметров,
     * и 300 в обычном конструкторе с lordUUID/knightUUID.
     */
    private int domainHP;

    public Domain() {
        this.domainTitle = Component.literal("null");
        this.lordUUID = "null";
        this.knightUUID = "null";
        this.domainHP = -1;  // По умолчанию -1
    }

    public Domain(String lordUUID, String knightUUID, Component knightDisplayName) {
        this.lordUUID = lordUUID;
        this.knightUUID = knightUUID;
        this.domainTitle = knightDisplayName;
        this.players.add(knightUUID);
        this.domainHP = 300; // Стандартное значение 300
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
        return Objects.equals(this.knightUUID, "null");
    }

    public boolean isPlayerInDomain(String playerUUID) {
        return players.contains(playerUUID);
    }

    public boolean pushPlayer(String player) {
        return this.players.add(player);
    }

    public void removePlayer(String player) {
        this.players.remove(player);
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

        ListTag playersTag = new ListTag();
        this.players.forEach(player -> playersTag.add(StringTag.valueOf(player)));
        tag.put("players", playersTag);

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.lordUUID = nbt.getString("lord_uuid");
        this.knightUUID = nbt.getString("knight_uuid");
        this.domainTitle = Component.literal(nbt.getString("domain_title"));

        // Загружаем domainHP
        this.domainHP = nbt.getInt("domain_hp");

        this.players.clear();
        ListTag playersTag = nbt.getList("players", 8);
        playersTag.forEach(tag -> this.players.add(tag.getAsString()));
    }
}
