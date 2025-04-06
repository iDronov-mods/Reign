package net.mcreator.reignmod.house;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class HouseCommands {

    public static String getHouseWantedPlayers(ServerPlayer player) {

        var wantedList = HouseManager.getHouseWantedPlayers(player);
        StringBuilder wantedListMerged = new StringBuilder(Component.translatable("house.get_house_wanted").getString()).append('\n');
        for (var wanted : wantedList) {
            wantedListMerged.append(wanted).append('\n');
        }
        return wantedListMerged.toString();
    }

    public static String getDomainPlayers(String knightUUID) {
        var domainPlayers = HouseManager.getDomainPlayers(knightUUID);
        StringBuilder domainPlayersMerged = new StringBuilder(Component.translatable("house.get_domain_players").getString()).append('\n');
        for (var wanted : domainPlayers) {
            domainPlayersMerged.append(wanted).append('\n');
        }
        return domainPlayersMerged.toString();
    }

    public static String getDomainSuspectPlayers(String knightUUID, int count) {
        var suspectList = HouseManager.getDomainSuspectPlayers(knightUUID, count);
        StringBuilder suspectListMerged = new StringBuilder(Component.translatable("house.get_domain_suspect").getString()).append('\n');
        for (var wanted : suspectList) {
            suspectListMerged.append(wanted).append('\n');
        }
        return suspectListMerged.toString();
    }
}
