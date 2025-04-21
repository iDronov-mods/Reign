package net.mcreator.reignmod.kingdom;

import net.mcreator.reignmod.basics.ReignSavedData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class KingdomSavedData extends ReignSavedData {
    private final KingdomData kingdomData = new KingdomData();

    private static KingdomSavedData instance;

    public KingdomSavedData() {}

    public KingdomSavedData(CompoundTag tag) {
        loadFromNBT(tag);
    }

    @Override
    protected String getDataKey() {
        return "kingdom_data";
    }

    public static void initialize(ServerLevel serverLevel) {
        if (instance == null) {
            instance = serverLevel.getDataStorage().computeIfAbsent(KingdomSavedData::new, KingdomSavedData::new, "kingdom_data");
            instance.serverLevelInstance = serverLevel;
        }
    }

    public static void resetInstance() {
        instance = null;
    }

    public static KingdomSavedData getInstance() {
        if (instance == null) {
            throw new IllegalStateException("KingdomSavedData is not initialized!");
        }
        return instance;
    }

    public KingdomData getKingdomData() {
        return this.kingdomData;
    }

    public static ServerLevel getServerInstance() {
        return getInstance().getServerLevelInstance();
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag tag) {
        tag.putInt("capital_era", getKingdomData().getCapitalEra());
        tag.putInt("capital_discontent", getKingdomData().getCapitalDiscontent());
        tag.putInt("source_disturbance", getKingdomData().getSourceDisturbance());

        tag.putIntArray("fund_coordinates", getKingdomData().getFundCoordinates());
        tag.putIntArray("coffers_coordinates", getKingdomData().getCoffersCoordinates());
        tag.putIntArray("prison_coordinates", getKingdomData().getPrisonCoordinates());

        CompoundTag courtTag = new CompoundTag();
        for (Map.Entry<KingdomData.CourtPosition, String> entry : getKingdomData().getAllCourtiers().entrySet()) {
            if (entry.getValue() != null) {
                courtTag.putString(entry.getKey().name(), entry.getValue());
            }
        }
        tag.put("court_positions", courtTag);

        return tag;
    }

    private void loadFromNBT(CompoundTag tag) {
        getKingdomData().setCapitalEra(tag.getInt("capital_era"));
        getKingdomData().setCapitalDiscontent(tag.getInt("capital_discontent"));
        getKingdomData().setSourceDisturbance(tag.getInt("source_disturbance"));

        int[] fundCoordinates = tag.getIntArray("fund_coordinates");
        int[] coffersCoordinates = tag.getIntArray("coffers_coordinates");
        int[] prisonCoordinates = tag.getIntArray("prison_coordinates");

        getKingdomData().setFundCoordinates(fundCoordinates[0], fundCoordinates[1], fundCoordinates[2]);
        getKingdomData().setCoffersCoordinates(coffersCoordinates[0], coffersCoordinates[1], coffersCoordinates[2]);
        getKingdomData().setPrisonCoordinates(prisonCoordinates[0], prisonCoordinates[1], prisonCoordinates[2]);

        if (tag.contains("court_positions")) {
            CompoundTag courtTag = tag.getCompound("court_positions");
            for (String key : courtTag.getAllKeys()) {
                KingdomData.CourtPosition pos = KingdomData.CourtPosition.valueOf(key);
                getKingdomData().assignCourtier(pos, courtTag.getString(key));
            }
        }
    }
}
