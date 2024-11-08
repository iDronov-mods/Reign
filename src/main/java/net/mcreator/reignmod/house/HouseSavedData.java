package net.mcreator.reignmod.house;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public class HouseSavedData extends SavedData {

    private final HouseData houseData = new HouseData();

    public HouseSavedData() {}

    public HouseSavedData(CompoundTag tag) {
        ListTag housesTag = tag.getList("houses", 10);
        housesTag.forEach(houseTag -> this.houseData.addHouse(new House((CompoundTag) houseTag)));
    }

    public static HouseSavedData getOrCreate(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(HouseSavedData::new, HouseSavedData::new, "house_data");
    }

    @Override
    public CompoundTag save(CompoundTag tag) { // Сохранение словаря домов
        ListTag housesTag = new ListTag();
        houseData.getHouses().forEach((houseName, house) -> housesTag.add(house.serializeNBT()));
        tag.put("houses", housesTag);
        return tag;
    }

    public Boolean isColorAvailable(String color) {
        return houseData.getHouseAvailableColors().get(color);
    }

    public House findHouseByName(String name) {
        return houseData.getHouses().getOrDefault(name.hashCode(), new House());
    }

    public Boolean addHouse(String name, String headUUID, String color) {
        if (houseData.getHouses().containsKey(name.hashCode()) || !houseData.getHouseAvailableColors().get(color)) return false;
        House house = new House(name, headUUID, color);
        house.players.add(headUUID);
        houseData.addHouse(house);
        setDirty();
        return true;
    }
}