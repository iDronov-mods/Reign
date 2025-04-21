package net.mcreator.reignmod.kingdom;

import net.minecraft.world.level.block.state.BlockState;

import java.util.EnumMap;

public class KingdomManager {

    private static KingdomData get() {
        return KingdomSavedData.getInstance().getKingdomData();
    }

    // ---------------------------- ГЕТТЕРЫ / СЕТТЕРЫ ----------------------------

    public static int getCapitalEra() { return get().getCapitalEra(); }
    public static void setCapitalEra(int value) { get().setCapitalEra(value); KingdomSavedData.getInstance().setDirty();}

    public static int getCapitalDiscontent() { return get().getCapitalDiscontent(); }
    public static void setCapitalDiscontent(int value) { get().setCapitalDiscontent(value); KingdomSavedData.getInstance().setDirty();}

    public static int getSourceDisturbance() { return get().getSourceDisturbance(); }
    public static void setSourceDisturbance(int value) { get().setSourceDisturbance(value); KingdomSavedData.getInstance().setDirty();}

    public static int[] getFundCoordinates() { return get().getFundCoordinates(); }
    public static void setFundCoordinates(int x, int y, int z) { get().setFundCoordinates(x, y, z); KingdomSavedData.getInstance().setDirty();}

    public static int[] getCoffersCoordinates() { return get().getCoffersCoordinates(); }
    public static void setCoffersCoordinates(int x, int y, int z) { get().setCoffersCoordinates(x, y, z); KingdomSavedData.getInstance().setDirty();}

    public static int[] getPrisonCoordinates() { return get().getPrisonCoordinates(); }
    public static void setPrisonCoordinates(int x, int y, int z) { get().setPrisonCoordinates(x, y, z); KingdomSavedData.getInstance().setDirty();}

    public static String getCourtier(KingdomData.CourtPosition pos) { return get().getCourtier(pos); }
    public static void assignCourtier(KingdomData.CourtPosition pos, String playerId) { get().assignCourtier(pos, playerId); KingdomSavedData.getInstance().setDirty();}
    public static void dismissCourtier(KingdomData.CourtPosition pos) { get().dismissCourtier(pos); KingdomSavedData.getInstance().setDirty();}

    public static EnumMap<KingdomData.CourtPosition, String> getAllCourtiers() { return get().getAllCourtiers(); }

    public static KingdomData.CourtPosition getPlayerPosition(String playerId) { return get().getPlayerPosition(playerId); }

    public static boolean isPlayerInCourt(String playerId) { return get().isPlayerInCourt(playerId); }

    // ---------------------------- ЛОГИКА ----------------------------

    public static void upgradeCapitalEra() { get().upgradeCapitalEra(); KingdomSavedData.getInstance().setDirty();}

    public static int getCapitalMaintenance() { return get().getCapitalMaintenance(); }

    public static void feedCapital() { get().feedCapital();KingdomSavedData.getInstance().setDirty();}

    // ---------------------------- БЛОКИ ----------------------------

    public static BlockState getFundBlockState() { return get().getFundBlockState(); }

    public static BlockState getCoffersBlockState() { return get().getCoffersBlockState(); }
}
