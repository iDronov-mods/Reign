package net.mcreator.reignmod.house;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashSet;

public class House implements INBTSerializable<CompoundTag> {
    private String name;
    private String headUUID;
    private String color;
    public HashSet<String> players = new HashSet<>();

    // Конструкторы
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

    // Геттеры и сеттеры
    public String getName() { return name; }
    public String getHeadUUID() { return headUUID; }
    public String getColor() { return color; }

    public void setName(String name) { this.name = name; }
    public void setHeadUUID(String head) { this.headUUID = head; }
    public void setColor(String color) { this.color = color; }

    // Сериализация
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("name", this.name);
        tag.putString("head_uuid", this.headUUID);
        tag.putString("color", this.color);

        ListTag playersTag = new ListTag();
        this.players.forEach(player -> playersTag.add(StringTag.valueOf(player)));
        tag.put("players", playersTag);

        return tag;
    }

    // Десериализация
    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.name = nbt.getString("name");
        this.headUUID = nbt.getString("head_uuid");
        this.color = nbt.getString("color");

        this.players.clear();
        ListTag playersTag = nbt.getList("players", 8);
        playersTag.forEach(tag -> this.players.add(tag.getAsString()));
    }
}