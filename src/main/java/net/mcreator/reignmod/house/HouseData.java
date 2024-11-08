package net.mcreator.reignmod.house;

import java.util.HashMap;
import java.util.List;

public class HouseData{
    private final HashMap<Integer, House> houses;
    private final HashMap<String, Boolean> houseAvailableColors;

    public HouseData() {
        this.houses = new HashMap<>();
        this.houseAvailableColors = new HashMap<>();

        List<String> houseColors = List.of("yellow", "lime", "green", "aqua", "blue", "purple", "pink", "red", "orange", "black");
        houseColors.forEach(color -> {
            this.houseAvailableColors.put(color, true);
        });
    }
    public HouseData(HouseData houseData) {
        houses = houseData.houses;
        houseAvailableColors = houseData.houseAvailableColors;
    }

    public HashMap<Integer, House> getHouses() { return this.houses;}
    public HashMap<String, Boolean> getHouseAvailableColors() { return this.houseAvailableColors;}

    public void addHouse(House house) {
        houses.put(house.getName().hashCode(), house);
        houseAvailableColors.put(house.getColor(), false);
    }
}
