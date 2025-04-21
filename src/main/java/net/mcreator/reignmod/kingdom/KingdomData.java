package net.mcreator.reignmod.kingdom;

import net.mcreator.reignmod.claim.capital.CapitalClaimManager;
import net.mcreator.reignmod.house.HouseManager;
import net.mcreator.reignmod.house.HouseSavedData;
import net.mcreator.reignmod.procedures.CapitalServeProcedure;
import net.mcreator.reignmod.procedures.CoffersUpdateInfoProcedure;
import net.mcreator.reignmod.procedures.FundUpdateInfoProcedure;
import net.mcreator.reignmod.procedures.KingOverthrowProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.EnumMap;

public class KingdomData {

    // ---------------------------- ПОЛЯ ----------------------------

    private final int[] fundCoordinates = new int[3];
    private final int[] coffersCoordinates = new int[3];
    private final int[] prisonCoordinates = new int[3];

    private int capitalEra = 0;
    private int capitalDiscontent = 0;
    private int sourceDisturbance = 0;

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

    public int getCapitalEra() {
        return capitalEra;
    }

    public void setCapitalEra(int value) {
        capitalEra = value;
    }

    public int getCapitalDiscontent() {
        return capitalDiscontent;
    }

    public void setCapitalDiscontent(int value) {
        capitalDiscontent = Math.max(0, Math.min(100, value));
    }

    public int getSourceDisturbance() {
        return sourceDisturbance;
    }

    public void setSourceDisturbance(int value) {
        sourceDisturbance = Math.max(0, Math.min(200, value));
    }

    public int[] getFundCoordinates() {
        return fundCoordinates;
    }

    public void setFundCoordinates(int x, int y, int z) {
        fundCoordinates[0] = x;
        fundCoordinates[1] = y;
        fundCoordinates[2] = z;
    }

    public int[] getCoffersCoordinates() {
        return coffersCoordinates;
    }

    public void setCoffersCoordinates(int x, int y, int z) {
        coffersCoordinates[0] = x;
        coffersCoordinates[1] = y;
        coffersCoordinates[2] = z;
    }

    public int[] getPrisonCoordinates() {
        return prisonCoordinates;
    }

    public void setPrisonCoordinates(int x, int y, int z) {
        prisonCoordinates[0] = x;
        prisonCoordinates[1] = y;
        prisonCoordinates[2] = z;
    }

    public String getCourtier(CourtPosition pos) {
        return courtiers.get(pos);
    }

    public void assignCourtier(CourtPosition pos, String playerId) {
        courtiers.put(pos, playerId);
    }

    public void dismissCourtier(CourtPosition pos) {
        courtiers.put(pos, null);
    }

    public EnumMap<CourtPosition, String> getAllCourtiers() {
        return new EnumMap<>(courtiers);
    }

    public CourtPosition getPlayerPosition(String playerId) {
        for (var entry : courtiers.entrySet()) {
            if (playerId.equals(entry.getValue())) return entry.getKey();
        }
        return null;
    }

    public boolean isPlayerInCourt(String playerId) {
        return courtiers.containsValue(playerId);
    }

    // ------------------------ ЛОГИКА ------------------------

    public void upgradeCapitalEra() {
        capitalEra++;
        updateFundInfo();
    }

    public int getCapitalMaintenance() {
        int multiplier = (capitalEra < 3) ? 1 : (capitalEra > 7) ? 3 : 2;

        return HouseManager.getDomainCount() * multiplier;
    }

    public void increaseCoffersAmount(int amount) {
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

    public boolean decreaseCoffersAmount(int amount) {
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

    public void feedCapital() {
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

    private void waveDiscontent() {

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

    private void updateFundInfo() {
        FundUpdateInfoProcedure.execute(HouseSavedData.getServerInstance(), fundCoordinates[0], fundCoordinates[1], fundCoordinates[2]);
    }

    private void updateCoffersInfo() {
        CoffersUpdateInfoProcedure.execute(HouseSavedData.getServerInstance(), coffersCoordinates[0], coffersCoordinates[1], coffersCoordinates[2]);
    }

    // ------------------------ БЛОКИ ------------------------

    public BlockState getFundBlockState() {
        return getBlockStateFromCoordinates(fundCoordinates);
    }

    public BlockState getCoffersBlockState() {
        return getBlockStateFromCoordinates(coffersCoordinates);
    }

    private BlockEntity getCoffersEntity(ServerLevel level) {
        return level.getBlockEntity(new BlockPos(coffersCoordinates[0], coffersCoordinates[1], coffersCoordinates[2]));
    }

    private BlockState getBlockStateFromCoordinates(int[] coordinates) {
        if (coordinates == null || coordinates.length != 3) return null;
        ServerLevel level = HouseSavedData.getServerInstance();
        return (level == null) ? null : level.getBlockState(new BlockPos(coordinates[0], coordinates[1], coordinates[2]));
    }
}
