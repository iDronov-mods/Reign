package net.mcreator.reignmod.kingdom;

import net.mcreator.reignmod.claim.capital.CapitalClaimManager;
import net.mcreator.reignmod.house.House;
import net.mcreator.reignmod.house.HouseManager;
import net.mcreator.reignmod.house.HouseSavedData;
import net.mcreator.reignmod.procedures.*;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.nbt.CompoundTag;

import java.util.Collection;
import java.util.EnumMap;

public class KingdomData {

    // ---------------------------- ПОЛЯ ----------------------------

    private static final int[] fundCoordinates = new int[3];
    private static final int[] coffersCoordinates = new int[3];
    private static final int[] prisonCoordinates = new int[3];

    private static int capitalEra = 0;
    private static int capitalDiscontent = 0;
    private static int sourceDisturbance = 0;

    public enum CourtPosition {
        HAND_OF_THE_KING,
        TREASURER,
        ARCHITECT,
        MARSHAL
    }

    private static final EnumMap<CourtPosition, String> courtiers = new EnumMap<>(CourtPosition.class);
    static {
        for (CourtPosition pos : CourtPosition.values()) {
            courtiers.put(pos, null);
        }
    }

    // ------------------------ ГЕТТЕРЫ И СЕТТЕРЫ ------------------------

    public static int getCapitalEra() { return capitalEra; }
    public static void setCapitalEra(int value) { capitalEra = value; }

    public static int getCapitalDiscontent() { return capitalDiscontent; }
    public static void setCapitalDiscontent(int value) {
        capitalDiscontent = Math.max(0, Math.min(100, value));
    }

    public static int getSourceDisturbance() { return sourceDisturbance; }
    public static void setSourceDisturbance(int value) {
        sourceDisturbance = Math.max(0, Math.min(200, value));
    }

    public static int[] getFundCoordinates() { return fundCoordinates; }
    public static void setFundCoordinates(int x, int y, int z) {
        fundCoordinates[0] = x;
        fundCoordinates[1] = y;
        fundCoordinates[2] = z;
    }

    public static int[] getCoffersCoordinates() { return coffersCoordinates; }
    public static void setCoffersCoordinates(int x, int y, int z) {
        coffersCoordinates[0] = x;
        coffersCoordinates[1] = y;
        coffersCoordinates[2] = z;
    }

    public static int[] getPrisonCoordinates() { return prisonCoordinates; }
    public static void setPrisonCoordinates(int x, int y, int z) {
        prisonCoordinates[0] = x;
        prisonCoordinates[1] = y;
        prisonCoordinates[2] = z;
    }

    public static String getCourtier(CourtPosition pos) {
        return courtiers.get(pos);
    }

    public static void assignCourtier(CourtPosition pos, String playerId) {
        courtiers.put(pos, playerId);
    }

    public static void dismissCourtier(CourtPosition pos) {
        courtiers.put(pos, null);
    }

    public static EnumMap<CourtPosition, String> getAllCourtiers() {
        return new EnumMap<>(courtiers);
    }

    public static CourtPosition getPlayerPosition(String playerId) {
        for (var entry : courtiers.entrySet()) {
            if (playerId.equals(entry.getValue())) return entry.getKey();
        }
        return null;
    }

    public static boolean isPlayerInCourt(String playerId) {
        return courtiers.containsValue(playerId);
    }

    // ------------------------ ЛОГИКА ------------------------

    public static void upgradeCapitalEra() {
        capitalEra++;
        updateFundInfo();
    }

    public static int getCapitalMaintenance() {
        int multiplier = (capitalEra < 3) ? 1 : (capitalEra > 7) ? 3 : 2;

        return HouseManager.getDomainCount() * multiplier;
    }

    public static void increaseCoffersAmount(int amount) {
        ServerLevel level = HouseSavedData.getServerInstance();
        if (level == null) return;

        BlockEntity blockEntity = getCoffersEntity(level);
        if (blockEntity == null) return;

        CompoundTag tag = blockEntity.saveWithFullMetadata();
        int current = tag.getInt("amount");
        tag.putInt("amount", current + amount);
        blockEntity.load(tag);
        blockEntity.setChanged();
    }

    public static boolean decreaseCoffersAmount(int amount) {
        ServerLevel level = HouseSavedData.getServerInstance();
        if (level == null) return false;

        BlockEntity blockEntity = getCoffersEntity(level);
        if (blockEntity == null) return false;

        CompoundTag tag = blockEntity.saveWithFullMetadata();
        int current = tag.getInt("amount");
        if (current < amount) return false;

        tag.putInt("amount", current - amount);
        blockEntity.load(tag);
        blockEntity.setChanged();
        return true;
    }
    
    public static void feedCapital() {
        boolean have = CapitalServeProcedure.execute(HouseSavedData.getServerInstance());

        if (have) {
            setSourceDisturbance(getSourceDisturbance() - 1);
            CapitalClaimManager.enable();
        }
        else {
            if(getSourceDisturbance() < 90) setSourceDisturbance(getSourceDisturbance() + 5);
            CapitalClaimManager.disable();
        }

        waveDiscontent();
        updateCoffersInfo();

    }

    public static void waveDiscontent() {

        if (getSourceDisturbance() >= 100) {
            setCapitalDiscontent(getCapitalDiscontent() + 1);
        }
        if (getSourceDisturbance() >= 150) {
            setCapitalDiscontent(getCapitalDiscontent() + 1);
        }
        if (getSourceDisturbance() == 200) {
            setCapitalDiscontent(getCapitalDiscontent() + 1);
        }

        if (getCapitalDiscontent() >= 100) {
            KingOverthrowProcedure.execute(HouseSavedData.getServerInstance());
            setCapitalDiscontent(0);
            setSourceDisturbance(50);
        }

        updateFundInfo();
    }

    public static void updateFundInfo() {
        FundUpdateInfoProcedure.execute(HouseSavedData.getServerInstance(), fundCoordinates[0], fundCoordinates[1], fundCoordinates[2]);
    }

    public static void updateCoffersInfo() {
        CoffersUpdateInfoProcedure.execute(HouseSavedData.getServerInstance(), coffersCoordinates[0], coffersCoordinates[1], coffersCoordinates[2]);
    }

    // ------------------------ БЛОКИ ------------------------

    public static BlockState getFundBlockState() {
        return getBlockStateFromCoordinates(fundCoordinates);
    }

    public static BlockState getCoffersBlockState() {
        return getBlockStateFromCoordinates(coffersCoordinates);
    }

    private static BlockEntity getCoffersEntity(ServerLevel level) {
        return level.getBlockEntity(new BlockPos(coffersCoordinates[0], coffersCoordinates[1], coffersCoordinates[2]));
    }

    private static BlockState getBlockStateFromCoordinates(int[] coordinates) {
        if (coordinates == null || coordinates.length != 3) return null;
        ServerLevel level = HouseSavedData.getServerInstance();
        return (level == null) ? null : level.getBlockState(new BlockPos(coordinates[0], coordinates[1], coordinates[2]));
    }
}
