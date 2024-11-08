package net.mcreator.reignmod.house;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.saveddata.SavedData;

public class HouseManager extends SavedData {

    private final HouseData houseData = new HouseData();

    public static HouseManager get(Entity entity) {
        ServerLevel level = entity.getServer().overworld();
        return level.getDataStorage().computeIfAbsent(HouseManager::load, HouseManager::new, "houseData");
    }

    public HouseManager() {}

    public static HouseManager load(CompoundTag tag) {
        HouseManager manager = new HouseManager();
        ListTag housesTag = tag.getList("houses", 8);
        housesTag.forEach(houseTag -> manager.houseData.addHouse(new House((CompoundTag) houseTag)));
        return manager;
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

    public Boolean createHouse(String name, String headUUID, String color) {
        if (houseData.getHouses().containsKey(name.hashCode()) || !houseData.getHouseAvailableColors().get(color)) return false;
        House house = new House(name, headUUID, color);
        house.players.add(headUUID);
        houseData.addHouse(house);
        setDirty();
        return true;
    }
}