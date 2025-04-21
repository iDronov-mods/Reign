package net.mcreator.reignmod.house;

import net.mcreator.reignmod.claim.chunk.ChunkClaimConstants;
import net.mcreator.reignmod.claim.chunk.ChunkClaimManager;
import net.mcreator.reignmod.procedures.DestroyObeliskProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

import java.util.HashMap;
import java.util.List;

public class HouseData{
    private final HashMap<String, House> houses;
    private final HashMap<String, Domain> domains;
    private final HashMap<String, String> playerCodes;
    private final HashMap<String, Boolean> houseAvailableColors;

    public HouseData() {
        this.houses = new HashMap<>();
        this.domains = new HashMap<>();
        this.playerCodes = new HashMap<>();
        this.houseAvailableColors = new HashMap<>();

        List<String> houseColors = List.of("yellow", "lime", "green", "aqua", "blue", "purple", "pink", "red", "orange", "black");
        houseColors.forEach(color -> {
            this.houseAvailableColors.put(color, true);
        });
    }
    public HouseData(HouseData houseData) {
        this.houses = houseData.houses;
        this.domains = houseData.domains;
        this.playerCodes = houseData.playerCodes;
        this.houseAvailableColors = houseData.houseAvailableColors;
    }

    public HashMap<String, House> getHouses() { return this.houses;}
    public HashMap<String, Domain> getDomains() { return this.domains;}
    public HashMap<String, String> getPlayerCodes() { return this.playerCodes;}
    public HashMap<String, Boolean> getHouseAvailableColors() { return this.houseAvailableColors;}

    public int[] getHousePlusCoordinates(String lordUUID) {
        return findHouseByLord(lordUUID).getHousePlusCoordinates();
    }

    public void setHousePlusCoordinates(String lordUUID, int x, int y, int z) {
        if (!findHouseByLord(lordUUID).isNull()) {
            findHouseByLord(lordUUID).setHousePlusCoordinates(new int[] {x, y, z});
        }
    }

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

    public void addOrUpdatePlayerCode(String playerUUID, String code) {
        this.playerCodes.put(playerUUID, code);
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
        if (!this.domains.getOrDefault(knightUUID, new Domain()).isNull()) {
            return findHouseByLord(this.domains.getOrDefault(knightUUID, new Domain()).getLordUUID());
        }
        return new House();
    }

    public Domain findDomainByKnight(String knightUUID) {
        return this.domains.getOrDefault(knightUUID, new Domain());
    }

    public void pushHouse(House house) {
        this.domains.putAll(house.getDomains());
        this.houses.putIfAbsent(house.getLordUUID(), house);
        pushDomain(this.findHouseByLord(house.getLordUUID()), new Domain(house.getLordUUID(), house.getLordUUID(), Component.translatable("lord_domain")));
        this.houseAvailableColors.put(house.getHouseColor(), false);
    }

    public int[] removeHouse(House house) {
        House houseCopy = new House(house.serializeNBT());
        houseCopy.getDomains().forEach((s, domain) -> this.removeDomain(house, domain));
        this.houses.remove(house.getLordUUID());
        this.houseAvailableColors.replace(house.getHouseColor(), true);

        if (houseCopy.getClaimId() != null) {
            var c = houseCopy.getHouseIncubatorCoordinates();
            HouseSavedData.getServerInstance().destroyBlock(new BlockPos(c[0], c[1] + ChunkClaimConstants.HOUSE_SHAFT_LENGTH, c[2]), false);
            HouseSavedData.getServerInstance().destroyBlock(new BlockPos(c[0], c[1] + ChunkClaimConstants.HOUSE_SHAFT_LENGTH - 1, c[2]), false);
            HouseSavedData.getServerInstance().destroyBlock(new BlockPos(c[0], c[1] + ChunkClaimConstants.HOUSE_SHAFT_LENGTH - 2, c[2]), false);
            DestroyObeliskProcedure.execute(HouseSavedData.getServerInstance(), c[0], c[1], c[2]);
            ChunkClaimManager.removeClaim(houseCopy.getClaimId());
        }

        return houseCopy.getHousePlusCoordinates();
    }

    public void pushDomain(House house, Domain domain) {
        if (this.houses.containsKey(house.getLordUUID())) {
            this.houses.get(house.getLordUUID()).pushDomain(domain);
            this.addDomain(domain);
        }
    }

    public void addDomain(Domain domain) {
        this.domains.putIfAbsent(domain.getKnightUUID(), domain);
    }

    public void removeDomain(House house, Domain domain) {
        if (this.houses.containsKey(house.getLordUUID())) {
            if (domain.getClaimId() != null) {
                var c = domain.getDomainFoundationCoordinates();
                HouseSavedData.getServerInstance().destroyBlock(new BlockPos(c[0], c[1] + ChunkClaimConstants.DOMAIN_SHAFT_LENGTH, c[2]), false);
                HouseSavedData.getServerInstance().destroyBlock(new BlockPos(c[0], c[1] + ChunkClaimConstants.DOMAIN_SHAFT_LENGTH - 1, c[2]), false);
                DestroyObeliskProcedure.execute(HouseSavedData.getServerInstance(), c[0], c[1], c[2]);
                ChunkClaimManager.removeClaim(domain.getClaimId());
            }
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
