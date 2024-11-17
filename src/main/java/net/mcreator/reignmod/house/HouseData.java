package net.mcreator.reignmod.house;

import java.util.HashMap;
import java.util.List;

public class HouseData{
    private final HashMap<String, House> houses;
    private final HashMap<String, Domain> domains;
    private final HashMap<String, Boolean> houseAvailableColors;

    public HouseData() {
        this.houses = new HashMap<>();
        this.domains = new HashMap<>();
        this.houseAvailableColors = new HashMap<>();

        List<String> houseColors = List.of("yellow", "lime", "green", "aqua", "blue", "purple", "pink", "red", "orange", "black");
        houseColors.forEach(color -> {
            this.houseAvailableColors.put(color, true);
        });
    }
    public HouseData(HouseData houseData) {
        this.houses = houseData.houses;
        this.domains = houseData.domains;
        this.houseAvailableColors = houseData.houseAvailableColors;
    }

    public HashMap<String, House> getHouses() { return this.houses;}
    public HashMap<String, Domain> getDomains() { return this.domains;}
    public HashMap<String, Boolean> getHouseAvailableColors() { return this.houseAvailableColors;}


    public int[] getHouseIncubatorCoordinates(String lordUUID) {
        return findHouseByLord(lordUUID).getHouseIncubatorCoordinates();
    }

    public void setHouseIncubatorCoordinates(String lordUUID, int x, int y, int z) {
        if (!findHouseByLord(lordUUID).isNull()) {
            findHouseByLord(lordUUID).setHouseIncubatorCoordinates(new int[] {x, y, z});
        }
    }

    public int[] getHousePrisonCoordinates(String lordUUID) {
        return findHouseByLord(lordUUID).getHousePrisonCoordinates();
    }

    public void setHousePrisonCoordinates(String lordUUID, int x, int y, int z) {
        if (!findHouseByLord(lordUUID).isNull()) {
            findHouseByLord(lordUUID).setHousePrisonCoordinates(new int[] {x, y, z});
        }
    }

    public House findHouseByPlayerSuzerain(String suzerainUUID) {
        if (!findHouseByLord(suzerainUUID).isNull()) {
            return findHouseByLord(suzerainUUID);
        }
        if (!findHouseByKnight(suzerainUUID).isNull()) {
            return findHouseByKnight(suzerainUUID);
        }
        return new House();
    }

    public House findHouseByLord(String lordUUID) {
        return this.houses.getOrDefault(lordUUID, new House());
    }

    public House findHouseByKnight(String knightUUID) {
        Domain domain = this.domains.getOrDefault(knightUUID, new Domain());
        if (!domain.isNull()) {
            return findHouseByLord(domain.getLordUUID());
        }
        return new House();
    }

    public Domain findDomainByKnight(String knightUUID) {
        return this.domains.getOrDefault(knightUUID, new Domain());
    }

    public void pushHouse(House house) {
        this.houses.putIfAbsent(house.getLordUUID(), house);
        this.houseAvailableColors.put(house.getHouseColor(), false);
    }

    public void removeHouse(House house) {
        this.houses.remove(house.getLordUUID());
        this.houseAvailableColors.replace(house.getHouseColor(), true);
    }

    public void pushDomain(House house, Domain domain) {
        if (this.houses.containsKey(house.getLordUUID())) {
            this.houses.get(house.getLordUUID()).pushDomain(domain);
            this.domains.putIfAbsent(domain.getKnightUUID(), domain);
        }
    }

    public void removeDomain(House house, Domain domain) {
        if (this.houses.containsKey(house.getLordUUID())) {
            this.houses.get(house.getLordUUID()).removeDomain(domain.getKnightUUID());
            this.domains.remove(domain.getKnightUUID());
        }
    }

    public boolean pushPlayerToDomain(House house, Domain domain, String playerUUID) {
        if (this.houses.containsKey(house.getLordUUID()) && this.domains.containsKey(domain.getKnightUUID())) {
            this.houses.get(house.getLordUUID()).pushPlayerToDomain(domain.getKnightUUID(), playerUUID);
            this.domains.get(domain.getKnightUUID()).pushPlayer(playerUUID);
            return true;
        }
        return false;
    }

    public void removePlayerFromDomain(House house, Domain domain, String playerUUID) {
        if (this.houses.containsKey(house.getLordUUID()) && this.domains.containsKey(domain.getKnightUUID())) {
            this.houses.get(house.getLordUUID()).removePlayerFromDomain(domain.getKnightUUID(), playerUUID);
            this.domains.get(domain.getKnightUUID()).removePlayer(playerUUID);
        }
    }
}
