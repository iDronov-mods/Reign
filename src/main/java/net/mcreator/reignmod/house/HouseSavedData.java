package net.mcreator.reignmod.house;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public class HouseSavedData extends SavedData {

    private final HouseData houseData = new HouseData();

    private static HouseSavedData instance;

    public HouseSavedData() {}

    public HouseSavedData(CompoundTag compoundTag) {
        ListTag housesTag = compoundTag.getList("houses", 10);
        housesTag.forEach(houseTag -> this.houseData.pushHouse(new House((CompoundTag) houseTag)));
    }

    public static void initialize(ServerLevel serverLevel) {
        if (instance == null) {
            instance = serverLevel.getDataStorage().computeIfAbsent(HouseSavedData::new, HouseSavedData::new, "house_data");
        }
    }

    public static HouseSavedData getInstance() {
        if (instance == null) {
            throw new IllegalStateException("HouseSavedData has not been initialized. Call initialize(ServerLevel) first.");
        }
        return instance;
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

    public House getPlayerHouse(String suzerainUUID) {
        return houseData.findHouseByPlayerSuzerain(suzerainUUID);
    }

    public Domain getPlayerDomain(String playerUUID, String suzerainUUID) {
        if (!houseData.findDomainByKnight(playerUUID).isNull()) {
            return houseData.findDomainByKnight(playerUUID);
        }
        return houseData.findDomainByKnight(suzerainUUID);
    }

    public Boolean isColorAvailable(String color) {
        return houseData.getHouseAvailableColors().get(color);
    }

    public Boolean addHouse(String lordUUID, String houseTitle, String houseColor, int houseHeartIdentifier) {
        if (this.houseData.getHouses().containsKey(lordUUID) || !this.houseData.getHouseAvailableColors().get(houseColor)) return false;
        this.houseData.pushHouse(new House(lordUUID, houseTitle, houseColor, houseHeartIdentifier));
        setDirty();
        return true;
    }

    public void removeHouse(String lordUUID) {
        this.houseData.removeHouse(this.houseData.findHouseByLord(lordUUID));
        setDirty();
    }

    public Boolean addDomain(String lordUUID, String knightUUID, String knightDisplayName) {
        if (!this.houseData.getHouses().containsKey(lordUUID) || this.houseData.getHouses().get(lordUUID).containsDomain(knightUUID)) return false;
        this.houseData.pushDomain(this.houseData.findHouseByLord(lordUUID), new Domain(lordUUID, knightUUID, Component.literal(knightDisplayName)));
        setDirty();
        return true;
    }

    public void removeDomain(String lordUUID, String knightUUID) {
        if (!this.houseData.findHouseByLord(lordUUID).isNull()) {
            this.houseData.removeDomain(this.houseData.findHouseByLord(lordUUID), this.houseData.findDomainByKnight(knightUUID));
            setDirty();
        }
    }

    public boolean pushPlayerToDomain(String knightUUID, String playerUUID) {
        if (this.houseData.pushPlayerToDomain(this.houseData.findHouseByKnight(knightUUID),  this.houseData.findDomainByKnight(knightUUID), playerUUID)) {
            setDirty();
            return true;
        }
        return false;
    }

    public void removePlayerFromDomain(String knightUUID, String playerUUID) {
        this.houseData.removePlayerFromDomain(this.houseData.findHouseByKnight(knightUUID), this.houseData.findDomainByKnight(knightUUID), playerUUID);
        setDirty();
    }
}