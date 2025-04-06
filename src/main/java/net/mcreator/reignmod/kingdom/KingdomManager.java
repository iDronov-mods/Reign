package net.mcreator.reignmod.kingdom;

import net.mcreator.reignmod.house.House;
import net.mcreator.reignmod.house.HouseManager;
import net.mcreator.reignmod.house.HouseSavedData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;

import java.util.Collection;

public class KingdomManager {

    //--------------------------------------------------------------------------------
    //                                  ПОЛЯ
    //--------------------------------------------------------------------------------

    private static int[] fundCoordinates = new int[3];
    private static int[] coffersCoordinates = new int[3];
    private static int[] capitalPrisonCoordinates = new int[3];
    private static int surgingSource = 0;
    private static int discontent = 0;
    private static int era = 0;

    //--------------------------------------------------------------------------------
    //                                  ГЕТТЕРЫ И СЕТТЕРЫ
    //--------------------------------------------------------------------------------

    public static int getEra() {
        return era;
    }

    public static void setEra(int era) {
        KingdomManager.era = era;
    }

    public static int[] getFundCoordinates() {
        return fundCoordinates;
    }

    public static void setFundCoordinates(int x, int y, int z) {
        KingdomManager.fundCoordinates[0] = x;
        KingdomManager.fundCoordinates[1] = y;
        KingdomManager.fundCoordinates[2] = z;
    }

    public static int[] getCoffersCoordinates() {
        return coffersCoordinates;
    }

    public static void setCoffersCoordinates(int x, int y, int z) {
        KingdomManager.coffersCoordinates[0] = x;
        KingdomManager.coffersCoordinates[1] = y;
        KingdomManager.coffersCoordinates[2] = z;
    }

    public static int[] getCapitalPrisonCoordinates() {
        return capitalPrisonCoordinates;
    }

    public static void setCapitalPrisonCoordinates(int x, int y, int z) {
        KingdomManager.capitalPrisonCoordinates[0] = x;
        KingdomManager.capitalPrisonCoordinates[1] = y;
        KingdomManager.capitalPrisonCoordinates[2] = z;
    }

    public static int getDiscontent() {
        return discontent;
    }

    public static void setDiscontent(int discontent) {
        KingdomManager.discontent = Math.max(0, Math.min(100, discontent));
    }

    public static void setSurgingSource(int surgingSource) {
        KingdomManager.surgingSource = Math.max(0, Math.min(200, surgingSource));
    }

    public static int getSurgingSource() {
        return surgingSource;
    }

    //--------------------------------------------------------------------------------
    //                               ГЕТТЕРЫ БЛОКОВ
    //--------------------------------------------------------------------------------

    public static BlockState getFundBlockState() {
        return getBlockStateFromCoords(fundCoordinates);
    }

    public static BlockState getCoffersBlockState() {
        return getBlockStateFromCoords(coffersCoordinates);
    }

    private static BlockState getBlockStateFromCoords(int[] coords) {
        if (coords == null || coords.length != 3) return null;
        ServerLevel level = HouseSavedData.getServerInstance();
        if (level == null) return null;
        return level.getBlockState(new BlockPos(coords[0], coords[1], coords[2]));
    }

    private static BlockEntity getCoffersBlockEntity(ServerLevel level) {
        int[] coords = coffersCoordinates;
        if (coords == null || coords.length != 3) return null;
        return level.getBlockEntity(new BlockPos(coords[0], coords[1], coords[2]));
    }

    //--------------------------------------------------------------------------------
    //                               ОСНОВНЫЕ МЕТОДЫ
    //--------------------------------------------------------------------------------

    public static void upgradeEra() {
        KingdomManager.era += 1;
    }

    public static int getCapitalService() {
        Collection<House> houses = HouseManager.getHousesCopies();
        int domains_count = 0;
        int k = 1;
        if (era < 3) k = 1;
        else if (era > 7) k = 3;
        else k = 2;
        for (House house : houses) {
            domains_count += house.getDomains().size();
        }
        return domains_count * k;
    }

    public static void addToCoffersAmount(int value) {
        ServerLevel level = HouseSavedData.getServerInstance();
        if (level == null) return;

        BlockEntity be = getCoffersBlockEntity(level);
        if (be == null) return;

        CompoundTag tag = be.saveWithFullMetadata();
        int currentAmount = tag.getInt("amount");
        tag.putInt("amount", currentAmount + value);
        be.load(tag);
        be.setChanged();
    }

    public static boolean removeFromCoffersAmount(int value) {
        ServerLevel level = HouseSavedData.getServerInstance();
        if (level == null) return false;

        BlockEntity be = getCoffersBlockEntity(level);
        if (be == null) return false;

        CompoundTag tag = be.saveWithFullMetadata();
        int currentAmount = tag.getInt("amount");

        if (currentAmount < value) {
            return false; // Недостаточно средств
        }

        tag.putInt("amount", currentAmount - value);
        be.load(tag);
        be.setChanged();
        return true;
    }

}
