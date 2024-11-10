package net.mcreator.reignmod.house;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public class HouseSavedData extends SavedData {

    private final HouseData houseData = new HouseData();

    public HouseSavedData() {}

    public HouseSavedData(CompoundTag compoundTag) {
        ListTag housesTag = compoundTag.getList("houses", 10);
        housesTag.forEach(houseTag -> this.houseData.pushHouse(new House((CompoundTag) houseTag)));
    }

    public static HouseSavedData getOrCreate(ServerLevel serverLevel) {
        return serverLevel.getDataStorage().computeIfAbsent(HouseSavedData::new, HouseSavedData::new, "house_data");
    }

    @Override
    public CompoundTag save(CompoundTag compoundTag) { // Сохранение словаря домов
        ListTag housesListTag = new ListTag();
        this.houseData.getHouses().forEach((lordUUID, house) -> housesListTag.add(house.serializeNBT()));
        compoundTag.put("houses", housesListTag);
        return compoundTag;
    }

    public HouseData getHouseData() {
        return this.houseData;
    }

    public Boolean isColorAvailable(String color) {
        return houseData.getHouseAvailableColors().get(color);
    }

    public Boolean addHouse(String lordUUID, String houseTitle, String houseColor) {
        if (this.houseData.getHouses().containsKey(lordUUID) || !this.houseData.getHouseAvailableColors().get(houseColor)) return false;
        this.houseData.pushHouse(new House(lordUUID, houseTitle, houseColor));
        setDirty();
        return true;
    }

    public void removeHouse(String lordUUID) {
        this.houseData.removeHouse(this.houseData.findHouseByLord(lordUUID));
        setDirty();
    }

    public Boolean addDomain(String lordUUID, String knightUUID, String knightDisplayName) {
        if (this.houseData.getHouses().containsKey(lordUUID) || this.houseData.getHouses().get(lordUUID).containsDomain(knightUUID)) return false;
        this.houseData.findHouseByLord(lordUUID).addDomain(knightUUID, knightDisplayName);
        setDirty();
        return true;
    }

    public void removeDomain(String lordUUID, String knightUUID) {
        House house = this.houseData.findHouseByLord(lordUUID);
        if (!house.isNull()) {
            this.houseData.removeDomain(house, this.houseData.findDomainByKnight(knightUUID));
            setDirty();
        }
    }
}