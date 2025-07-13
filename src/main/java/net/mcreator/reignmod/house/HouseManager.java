package net.mcreator.reignmod.house;

import net.mcreator.reignmod.claim.capital.CapitalClaimManager;
import net.mcreator.reignmod.network.ReignModModVariables;
import net.mcreator.reignmod.networking.ReignNetworking;
import net.mcreator.reignmod.networking.packet.S2C.PlayerPrefixSyncS2CPacket;
import net.mcreator.reignmod.procedures.HouseDeleteProcedure;
import net.mcreator.reignmod.procedures.IsKingProcedure;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.common.UsernameCache;

import java.util.*;

public class HouseManager {

    private static final HashMap<String, String> colorCodes = fillColorCodes();

    private static HashMap<String, String> fillColorCodes() {
        HashMap<String, String> colorCodes = new HashMap<>();
        List<String> colors = List.of("yellow", "lime", "green", "aqua", "blue", "purple", "pink", "red", "orange", "black");
        List<String> codes = List.of("§e", "§a", "§2", "§b", "§9", "§5", "§d", "§c", "§6", "§8");

        Iterator<String> colorsIterator = colors.iterator();
        Iterator<String> codesIterator = codes.iterator();
        while (colorsIterator.hasNext() && codesIterator.hasNext()) {
            colorCodes.put(colorsIterator.next(), codesIterator.next());
        }
        return colorCodes;
    }

    public static int getIntColorFromName(String name) {
        if (name == null) return 16777215;
        DyeColor dye;
        if (name.equalsIgnoreCase("aqua")) {
            dye = DyeColor.LIGHT_BLUE;
        } else {
            try {
                dye = DyeColor.valueOf(name.toUpperCase());
            } catch (IllegalArgumentException e) {
                dye = DyeColor.WHITE;
            }
        }
        return dye.getTextColor();
    }

    public static Collection<House> getHouses() {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            return houseSavedData.getHouseData().getHouses().values();
        }
        return new ArrayList<>();
    }

	public static Collection<House> getHousesCopies() {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            Collection<House> houses = houseSavedData.getHouseData().getHouses().values();
            Collection<House> housesCopies = new ArrayList<>();
            houses.forEach(house -> {housesCopies.add(new House(house.serializeNBT()));});
            return housesCopies;
        }
        return new ArrayList<>();
    }
    
    public static int getHousesCount() {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            return houseSavedData.getHouseData().getHouses().size();
        }
        return 0;
    }

    public static boolean createHouse(Player lordPlayer, String houseTitle, String houseColor) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();
        if (houseSavedData != null) {
            return houseSavedData.addHouse(lordPlayer.getStringUUID(), houseTitle, houseColor);
        }
        return false;
    }

    public static boolean createDomain(Player lordPlayer, Player knightPlayer) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            return houseSavedData.addDomain(lordPlayer.getStringUUID(), knightPlayer.getStringUUID(), knightPlayer.getDisplayName().getString());
        }
        return false;
    }

    public static void deleteHouse(String lordUUID) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            HouseDeleteProcedure.execute(HouseSavedData.getServerInstance(), getHouseByLordUUID(lordUUID).getHouseTitleWithColor());
            houseSavedData.removeHouse(lordUUID);
        }
    }

    public static void deleteDomain(Player lordPlayer, Player knightPlayer) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            houseSavedData.removeDomain(lordPlayer.getStringUUID(), knightPlayer.getStringUUID());
        }
    }

    public static void deleteDomain(Player knightPlayer) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            houseSavedData.removeDomain(getDomainLordByKnight(knightPlayer.getStringUUID()), knightPlayer.getStringUUID());
        }
    }

   public static boolean pushPlayerToDomain(Player knightPlayer, Player player) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            return houseSavedData.pushPlayerToDomain(knightPlayer.getStringUUID(), player.getStringUUID());
        }
        return false;
    }

    public static void removePlayerFromDomain(Player player) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            String suzerainUUID = getPlayerSuzerain(player);
            houseSavedData.removePlayerFromDomain(suzerainUUID, player.getStringUUID());
        }
    }

    public static boolean removePlayerFromDomain(String knightUUID, String playerName) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            UUID found = CapitalClaimManager.getOfflinePlayerUUID(HouseSavedData.getServerInstance().getServer(), playerName);
            if (found != null) {
                houseSavedData.removePlayerFromDomain(knightUUID, found.toString());
                return true;
            }
        }
        return false;
    }

    public enum removeDirectVassalResult {
        PLAYER_NOT_FOUND,
        INSUFFICIENT_AUTHORITY,
        DOMAIN_IS_PROTECTED,
        SUCCESS
    }

    public static removeDirectVassalResult removeDirectVassal(String suzerainUUID, String playerName) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            Domain foundDomain = houseSavedData.getHouseData().findDomainByKnight(suzerainUUID);
            House foundHouse = houseSavedData.getHouseData().findHouseByLord(suzerainUUID);

            UUID foundPlayerUUID = CapitalClaimManager.getOfflinePlayerUUID(HouseSavedData.getServerInstance().getServer(), playerName);
            if (foundPlayerUUID == null) {
                return removeDirectVassalResult.PLAYER_NOT_FOUND;
            }

            if (!foundHouse.isNull()) {
                Domain playerDomain = houseSavedData.getHouseData().findDomainByKnight(foundPlayerUUID.toString());
                if (!playerDomain.isNull() && playerDomain.getLordUUID().equals(suzerainUUID)) {
                    if (playerDomain.getClaimId() != null) {
                        return removeDirectVassalResult.DOMAIN_IS_PROTECTED;
                    }

                    houseSavedData.removeDomain(suzerainUUID, foundPlayerUUID.toString());
                    return removeDirectVassalResult.SUCCESS;
                }
            }

            if (!foundDomain.isNull()) {
                if (foundDomain.getPlayers().contains(foundPlayerUUID.toString())) {
                    houseSavedData.removePlayerFromDomain(suzerainUUID, foundPlayerUUID.toString());
                    return removeDirectVassalResult.SUCCESS;
                }
            }
        }
        return removeDirectVassalResult.INSUFFICIENT_AUTHORITY;
    }

    public static boolean addWantedPlayer(String lordUUID, String playerName) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();
        if (houseSavedData != null) {
            UUID found = CapitalClaimManager.getOfflinePlayerUUID(HouseSavedData.getServerInstance().getServer(), playerName);
            if (found != null) {
                houseSavedData.addWantedPlayer(lordUUID, found.toString());
                return true;
            }
        }
        return false;
    }

    public static boolean removeWantedPlayer(String lordUUID, String playerName) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();
        if (houseSavedData != null) {
            UUID found = CapitalClaimManager.getOfflinePlayerUUID(HouseSavedData.getServerInstance().getServer(), playerName);
            if (found != null) {
                houseSavedData.removeWantedPlayer(lordUUID, found.toString());
                return true;
            }
        }
        return false;
    }

    public static boolean isPlayerLord(Player lordPlayer) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            return !houseSavedData.getHouseData().findHouseByLord(lordPlayer.getStringUUID()).isNull();
        }
        return false;
    }

    public static boolean isPlayerKnight(Player knightPlayer) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            return !houseSavedData.getHouseData().findDomainByKnight(knightPlayer.getStringUUID()).isNull();
        }
        return false;
    }

    public static boolean isColorAvailable(String color) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            return houseSavedData.isColorAvailable(color);
        }
        return false;
    }

    public static House getHouseByLordUUID(String lordUUID) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            return houseSavedData.getHouseData().findHouseByLord(lordUUID);
        }
        return new House();
    }

    public static Domain getDomainByKnightUUID(String knightUUID) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            return houseSavedData.getHouseData().findDomainByKnight(knightUUID);
        }
        return new Domain();
    }

    public static String getPlayerSuzerain(Player player) {
        return (player.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).house;
    }

    public static String getPlayerDisplayName(Player player) {
        String displayNameWithPrefix = player.getDisplayName().getString();
        int playerNameLength = player.getName().getString().length();
        return displayNameWithPrefix.substring(displayNameWithPrefix.length() - playerNameLength);
    }

    public static void playerPrefixSynchronize(Player player) {
        String colorPrefix = HouseManager.getPlayerHouseColorCode(player);
        if (!Objects.equals(colorPrefix, "")) {

            if (IsKingProcedure.execute(player.getCommandSenderWorld(), player)) {
                colorPrefix = "§r[§6👑§r] " + colorPrefix + "§l";
            } else if (HouseManager.isPlayerLord(player)) {
                colorPrefix = "§r[" + colorPrefix + "🏰§r] " + colorPrefix + "§l";
            } else if (HouseManager.isPlayerKnight(player)) {
                colorPrefix = "§r[" + colorPrefix + "🗡§r] " + colorPrefix;
            }
        }
        String finalPlayerPrefix = colorPrefix;
        HouseSavedData houseSavedData = HouseSavedData.getInstance();
        houseSavedData.getHouseData().addOrUpdatePlayerCode(player.getStringUUID(), finalPlayerPrefix);

        player.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
            capability.player_prefix = finalPlayerPrefix;
            capability.syncPlayerVariables(player);
        });

        if (player instanceof ServerPlayer) {
            ReignNetworking.sendToPlayer(new PlayerPrefixSyncS2CPacket(houseSavedData.getHouseData().getPlayerCodes()), (ServerPlayer) player);
        }
        player.refreshDisplayName();
        if (player instanceof ServerPlayer) {
            ((ServerPlayer) player).refreshTabListName();
        }
    }

    public static void allPlayersPrefixPacketSend() {
        var serverInstance = HouseSavedData.getServerInstance();
        serverInstance.getPlayers(serverPlayer -> true).forEach(serverPlayer -> {
            HouseSavedData houseSavedData = HouseSavedData.getInstance();
            ReignNetworking.sendToPlayer(new PlayerPrefixSyncS2CPacket(houseSavedData.getHouseData().getPlayerCodes()), serverPlayer);
        });
    }

    public static House getPlayerHouse(Player player) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            String suzerainUUID = getPlayerSuzerain(player);
            return houseSavedData.getPlayerHouse(suzerainUUID);
        }
        return new House();
    }

    public static House getHouseBySuzerainUUID(String suzerainUUID) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            return HouseSavedData.getInstance().getHouseData().findHouseByPlayerSuzerain(suzerainUUID);
        }
        return new House();
    }

    public static String getPlayerHouseTitle(Player player) {
        return getPlayerHouse(player).getHouseTitle();
    }

    public static String getPlayerHouseColor(Player player) {
        return getPlayerHouse(player).getHouseColor();
    }

    public static String getPlayerHouseColorCode(Player player) {
        return colorCodes.getOrDefault(getPlayerHouseColor(player), "");
    }

    public static String getHouseColorCode(String color) {
        return colorCodes.getOrDefault(color, "");
    }

    public static String getPlayerHouseLord(Player player) {
        return getPlayerHouse(player).getLordUUID();
    }

    public static int getHouseDomainCount(Player player) {
        return getPlayerHouse(player).getDomains().size();
    }

    public static int getHousePlayerCount(Player player) {
        return getPlayerHouse(player).getPlayers().size();
    }

    public static ArrayList<String> getHouseWantedPlayers(ServerPlayer serverPlayer) {
        var found = getPlayerHouse(serverPlayer).getWantedPlayers();
        ArrayList<String> domainWantedPlayers = new ArrayList<>(found.size());
        found.forEach( (sus) -> domainWantedPlayers.add(HouseSavedData.getInstance().getHouseData().getPlayerCodes().getOrDefault(sus, "") +
                getOfflinePlayerName(sus)));
        return domainWantedPlayers;
    }

    public static Domain getPlayerDomain(Player player) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            String suzerainUUID = getPlayerSuzerain(player);
            return houseSavedData.getPlayerDomain(player.getStringUUID(), suzerainUUID);
        }
        return new Domain();
    }

    public static String getDomainLordByKnight(String knightUUID) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();
        if (houseSavedData != null) {
            return houseSavedData.getHouseData().findDomainByKnight(knightUUID).getLordUUID();
        }
        return null;
    }

    public static String getPlayerDomainTitle(Player player) {
        return getPlayerDomain(player).getDomainTitle().getString();
    }

    public static String getPlayerDomainKnight(Player player) {
        return getPlayerDomain(player).getKnightUUID();
    }

    public static int getDomainPlayerCount(Player player) {
        return getPlayerDomain(player).getPlayers().size();
    }

    public static int getDomainCount() {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();
        if (houseSavedData != null) {
            return houseSavedData.getHouseData().getDomains().size();
        }
        return -1;
    }

    public static ArrayList<String> getDomainPlayers(String knightUUID) {
        var found = getDomainByKnightUUID(knightUUID).getPlayers();
        ArrayList<String> domainPlayers = new ArrayList<>(found.size());
        found.forEach( (sus) -> domainPlayers.add(HouseSavedData.getInstance().getHouseData().getPlayerCodes().getOrDefault(sus, "") +
                getOfflinePlayerName(sus)));
        return domainPlayers;
    }

    public static ArrayList<String> getDomainSuspectPlayers(String knightUUID, int count) {
        var found = getDomainByKnightUUID(knightUUID).getSortedSuspects(count);
        ArrayList<String> domainSuspectPlayers = new ArrayList<>(found.size());
        found.forEach( (sus) -> domainSuspectPlayers.add(HouseSavedData.getInstance().getHouseData().getPlayerCodes().getOrDefault(sus.getKey(), "") +
                getOfflinePlayerName(sus.getKey()) + "§r: " + sus.getValue()));
        return domainSuspectPlayers;
    }

    public static ArrayList<String> getIlyaDomainSuspectPlayers(String knightUUID, int count) {
        var found = getDomainByKnightUUID(knightUUID).getSortedSuspects(count);
        ArrayList<String> domainSuspectPlayers = new ArrayList<>(found.size());
        found.forEach( (sus) -> {
            domainSuspectPlayers.add(HouseSavedData.getInstance().getHouseData().getPlayerCodes().getOrDefault(sus.getKey(), "") +
                    getOfflinePlayerName(sus.getKey()));
            domainSuspectPlayers.add(String.valueOf(sus.getValue()));
        });
        while (domainSuspectPlayers.size() < count * 2) {
            domainSuspectPlayers.add("");
        }
        return domainSuspectPlayers;
    }

    public Optional<Boolean> getDomainDebuff(String lordUUID, String knightUUID, Domain.DomainDebuffs debuff) {
        return getHouseByLordUUID(lordUUID).getDebuff(knightUUID, debuff);
    }

    public boolean toggleOnDebuff(String lordUUID, String knightUUID, Domain.DomainDebuffs debuff) {
        return getHouseByLordUUID(lordUUID).toggleOnDebuff(knightUUID, debuff);
    }

    public boolean toggleOffDebuff(String lordUUID, String knightUUID, Domain.DomainDebuffs debuff) {
        return getHouseByLordUUID(lordUUID).toggleOffDebuff(knightUUID, debuff);
    }

    private static int[] getHousePlusCoordinates(String lordUUID) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            return houseSavedData.getHouseData().getHousePlusCoordinates(lordUUID);
        }
        return new int[] {0, 0, 0};
    }
    public static boolean isHousePlusNull(String lordUUID) {
        return Arrays.equals(getHousePlusCoordinates(lordUUID), new int[]{0, 0, 0});
    }
    public static int getHousePlusX(String lordUUID) {
        return getHousePlusCoordinates(lordUUID)[0];
    }
    public static int getHousePlusY(String lordUUID) {
        return getHousePlusCoordinates(lordUUID)[1];
    }
    public static int getHousePlusZ(String lordUUID) {
        return getHousePlusCoordinates(lordUUID)[2];
    }

    public static void setHousePlusCoordinates(String lordUUID, int x, int y, int z) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            houseSavedData.getHouseData().setHousePlusCoordinates(lordUUID, x, y, z);
            houseSavedData.setDirty();
        }
    }

    private static int[] getHouseIncubatorCoordinates(String lordUUID) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            return houseSavedData.getHouseData().getHouseIncubatorCoordinates(lordUUID);
        }
        return new int[] {0, 0, 0};
    }
    public static boolean isHouseIncubatorNull(String lordUUID) {
        return Arrays.equals(getHouseIncubatorCoordinates(lordUUID), new int[]{0, 0, 0});
    }
    public static int getHouseIncubatorX(String lordUUID) {
        return getHouseIncubatorCoordinates(lordUUID)[0];
    }
    public static int getHouseIncubatorY(String lordUUID) {
        return getHouseIncubatorCoordinates(lordUUID)[1];
    }
    public static int getHouseIncubatorZ(String lordUUID) {
        return getHouseIncubatorCoordinates(lordUUID)[2];
    }


    public static void setHouseIncubatorCoordinates(String lordUUID, int x, int y, int z) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            houseSavedData.getHouseData().setHouseIncubatorCoordinates(lordUUID, x, y, z);
            houseSavedData.setDirty();
        }
    }

    private static int[] getHousePrisonCoordinates(String lordUUID) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            return houseSavedData.getHouseData().getHousePrisonCoordinates(lordUUID);
        }
        return new int[] {0, 0, 0};
    }
    public static boolean isHousePrisonNull(String lordUUID) {
        return Arrays.equals(getHousePrisonCoordinates(lordUUID), new int[]{0, 0, 0});
    }
    public static int getHousePrisonX(String lordUUID) {
        return getHousePrisonCoordinates(lordUUID)[0];
    }
    public static int getHousePrisonY(String lordUUID) {
        return getHousePrisonCoordinates(lordUUID)[1];
    }
    public static int getHousePrisonZ(String lordUUID) {
        return getHousePrisonCoordinates(lordUUID)[2];
    }

    public void setHousePrisonCoordinates(String lordUUID, int x, int y, int z) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();

        if (houseSavedData != null) {
            houseSavedData.getHouseData().setHousePrisonCoordinates(lordUUID, x, y, z);
            houseSavedData.setDirty();
        }
    }

    public static int getHouseNeed(ServerPlayer player, HouseNeedType type) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();
        return houseSavedData.getHouseNeed(player.getStringUUID(), type);
    }

    public static void setHouseNeed(ServerPlayer player, HouseNeedType type, int value) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();
        houseSavedData.setHouseNeed(player.getStringUUID(), type, value);
    }

    public static int adjustHouseNeed(ServerPlayer player, HouseNeedType type, int delta) {
        HouseSavedData houseSavedData = HouseSavedData.getInstance();
        return houseSavedData.adjustHouseNeed(player.getStringUUID(), type, delta);
    }

    public static void feedHouses() {
        ArrayList<String> houses_to_delete = new ArrayList<>();
        var houses = HouseManager.getHouses();

        for (House house : houses) {
            var domains = house.getDomains().values();
            int lvl = house.getHouseLevel();
            int add_hp = -70 - (lvl * 12);

            if (house.getPlayers().size() < 3) add_hp -= 100;

            int need_fuel = domains.size() * 2 + 4;
            int need_bread = house.getPlayers().size() + 1;
            int need_roots = lvl >= 3 ? house.getPlayers().size() : 0;
            int need_meat = lvl >= 4 ? domains.size() : 0;
            int need_wool = lvl >= 5 ? domains.size() : 0;
            int need_sweets = lvl >= 6 ? 2 : 0;
            int need_jewelry = lvl >= 7 ? 2 : 0;

            if (house.getNeed(HouseNeedType.FUEL) >= need_fuel) add_hp += 50;
            house.adjustNeed(HouseNeedType.FUEL, -need_fuel);

            if (house.getNeed(HouseNeedType.BREAD) >= need_bread)  add_hp += 50;
            house.adjustNeed(HouseNeedType.BREAD, -need_bread);

            if (house.getNeed(HouseNeedType.ROOTS) >= need_roots && lvl >= 3) add_hp += 20;
            house.adjustNeed(HouseNeedType.ROOTS, -need_roots);

            if (house.getNeed(HouseNeedType.MEAT) >= need_meat && lvl >= 4) add_hp += 20;
            house.adjustNeed(HouseNeedType.MEAT, -need_meat);

            if (house.getNeed(HouseNeedType.WOOL) >= need_wool && lvl >= 5) add_hp += 10;
            house.adjustNeed(HouseNeedType.WOOL, -need_wool);

            if (house.getNeed(HouseNeedType.SWEETS) >= need_sweets && lvl >= 6) add_hp += 10;
            house.adjustNeed(HouseNeedType.SWEETS, -need_sweets);

            if (house.getNeed(HouseNeedType.JEWELRY) >= need_jewelry && lvl >= 7) add_hp += 10;
            house.adjustNeed(HouseNeedType.JEWELRY, -need_jewelry);

            int add_from_domains = 0;
            for (Domain domain : domains) {
                domain.adjustSuspicionForAll(-1);

                damageDomain(domain);
                chanceDomainEvents(domain);

                add_from_domains += 3 * (domain.getDomainHP()/300);
            }

            add_hp += add_from_domains;

            if(house.addHouseHP(Math.max(-40, Math.min(add_hp, 40))) == 0) {
                houses_to_delete.add(house.getLordUUID());
            }

            HouseSavedData.getInstance().setDirty();
            house.updateIncubatorInfo();
        }

        houses_to_delete.forEach(HouseManager::deleteHouse);
    }

    private static void damageDomain(Domain domain) {
        int add = 2;
        if(domain.getDebuff(Domain.DomainDebuffs.disease)) add -= 4;
        if(domain.getDebuff(Domain.DomainDebuffs.robbers)) add -= 7;
        domain.addDomainHP(add);
    }

    private static void chanceDomainEvents(Domain domain) {
        int x = (int) (Math.random() * 1000) + 1;
        if(x == 1) domain.toggleOnDebuff(Domain.DomainDebuffs.robbers);
        x = (int) (Math.random() * 100) + 1;
        if(x == 1) domain.toggleOffDebuff(Domain.DomainDebuffs.disease);
    }

    public static String getOfflinePlayerName(String playerUUID) {
        String playerName = UsernameCache.getLastKnownUsername(UUID.fromString(playerUUID));
        if (playerName != null) {
            return playerName;
        }
        return "";
    }
}
