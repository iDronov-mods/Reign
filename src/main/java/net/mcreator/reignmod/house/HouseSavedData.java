package net.mcreator.reignmod.house;

import net.mcreator.reignmod.basics.ReignSavedData;
import net.mcreator.reignmod.claim.capital.CapitalClaimManager;
import net.mcreator.reignmod.procedures.ReturnPlusProcedure;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class HouseSavedData extends ReignSavedData {

    private final HouseData houseData = new HouseData();

    private static HouseSavedData instance;

    public HouseSavedData() {}

    public HouseSavedData(CompoundTag compoundTag) {
        ListTag housesTag = compoundTag.getList("houses", 10);
        housesTag.forEach(houseTag -> this.houseData.pushHouse(new House((CompoundTag) houseTag)));

        this.houseData.getDomains().clear();
        this.houseData.getHouses().values().forEach((House house) -> {
            house.getDomains().values().forEach(houseData::addDomain);
        });

        ListTag playersListTag = compoundTag.getList("players", 8);
        ListTag playersCodesListTag = compoundTag.getList("player_codes", 8);
        Iterator<Tag> i1 = playersListTag.iterator();
        Iterator<Tag> i2 = playersCodesListTag.iterator();
        while (i1.hasNext() && i2.hasNext()) {
            this.houseData.addOrUpdatePlayerCode(i1.next().getAsString(), i2.next().getAsString());
        }
    }

    public static void initialize(ServerLevel serverLevel) {
        if (instance == null) {
            instance = serverLevel.getDataStorage().computeIfAbsent(HouseSavedData::new, HouseSavedData::new, "house_data");
            instance.serverLevelInstance = serverLevel;
        }
    }

    public static void resetInstance() {
        instance = null;
    }

    public static HouseSavedData getInstance() {
        if (instance == null) {
            throw new IllegalStateException("HouseSavedData has not been initialized. Call initialize(ServerLevel) first.");
        }
        return instance;
    }

    public static ServerLevel getServerInstance() {
        return getInstance().getServerLevelInstance();
    }

    @Override
    protected String getDataKey() {
        return "house_data";
    }

    @Override
    public @NotNull CompoundTag save(CompoundTag compoundTag) {
        ListTag housesListTag = new ListTag();
        this.houseData.getHouses().forEach((lordUUID, house) -> housesListTag.add(house.serializeNBT()));
        compoundTag.put("houses", housesListTag);

        ListTag playersListTag = new ListTag();
        ListTag playersCodesListTag = new ListTag();
        this.houseData.getPlayerCodes().forEach((playerUUID, playerCode) -> {
            playersListTag.add(StringTag.valueOf(playerUUID));
            playersCodesListTag.add(StringTag.valueOf(playerCode));
        });
        compoundTag.put("players", playersListTag);
        compoundTag.put("player_codes", playersCodesListTag);

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

    public Boolean addHouse(String lordUUID, String houseTitle, String houseColor) {
        if (this.houseData.getHouses().containsKey(lordUUID) || !this.houseData.getHouseAvailableColors().get(houseColor)) return false;
        this.houseData.pushHouse(new House(lordUUID, houseTitle, houseColor));
        setDirty();
        return true;
    }

     public void removeHouse(String lordUUID) {
        if (this.houseData.getHouses().containsKey(lordUUID)) {
            int[] plusCoordinates = this.houseData.removeHouse(this.houseData.findHouseByLord(lordUUID));
            ReturnPlusProcedure.execute(getServerInstance(),plusCoordinates[0],plusCoordinates[1],plusCoordinates[2]);
            setDirty();
        }
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

    public void addWantedPlayer(String lordUUID, String playerUUID) {
        this.houseData.findHouseByLord(lordUUID).addWantedPlayer(playerUUID);
        setDirty();
    }

    public void removeWantedPlayer(String lordUUID, String playerUUID) {
        this.houseData.findHouseByLord(lordUUID).removeWantedPlayer(playerUUID);
        setDirty();
    }

    public int getHouseNeed(String lordUUID, HouseNeedType type) {
        House found = this.houseData.findHouseByLord(lordUUID);
        if (!found.isNull()) {
            return found.getNeed(type);
        }
        return -1;
    }

    public void setHouseNeed(String lordUUID, HouseNeedType type, int value) {
        House found = this.houseData.findHouseByLord(lordUUID);
        if (!found.isNull()) {
            found.setNeed(type, value);
            setDirty();
        }
    }

    public int adjustHouseNeed(String lordUUID, HouseNeedType type, int delta) {
        House found = this.houseData.findHouseByLord(lordUUID);
        if (!found.isNull()) {
            var adjusting = found.adjustNeed(type, delta);
            setDirty();
            return adjusting;
        }
        return -1;
    }
}