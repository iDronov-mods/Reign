package net.mcreator.reignmod.kingdom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KingdomSavedData extends SavedData {
    private static final String DATA_NAME = "kingdom_data";
    private static final Logger LOGGER = LogManager.getLogger();

    public KingdomSavedData() {}

    public static KingdomSavedData load(CompoundTag tag) {
        KingdomSavedData data = new KingdomSavedData();

        LOGGER.info("Loading KingdomSavedData...");

        if (tag.contains("FundX")) {
            KingdomManager.setFundCoordinates(
                    tag.getInt("FundX"),
                    tag.getInt("FundY"),
                    tag.getInt("FundZ")
            );
            LOGGER.info("FundCoordinates loaded: {}", arrayToString(KingdomManager.getFundCoordinates()));
        }

        if (tag.contains("CoffersX")) {
            KingdomManager.setCoffersCoordinates(
                    tag.getInt("CoffersX"),
                    tag.getInt("CoffersY"),
                    tag.getInt("CoffersZ")
            );
            LOGGER.info("CoffersCoordinates loaded: {}", arrayToString(KingdomManager.getCoffersCoordinates()));
        }

        if (tag.contains("PrisonX")) {
            KingdomManager.setCapitalPrisonCoordinates(
                    tag.getInt("PrisonX"),
                    tag.getInt("PrisonY"),
                    tag.getInt("PrisonZ")
            );
            LOGGER.info("CapitalPrisonCoordinates loaded: {}", arrayToString(KingdomManager.getCapitalPrisonCoordinates()));
        }

        if (tag.contains("SurgingSource")) {
            KingdomManager.setSurgingSource(tag.getInt("SurgingSource"));
            LOGGER.info("SurgingSource loaded: {}", KingdomManager.getSurgingSource());
        }

        if (tag.contains("Discontent")) {
            KingdomManager.setDiscontent(tag.getInt("Discontent"));
            LOGGER.info("Discontent loaded: {}", KingdomManager.getDiscontent());
        }

        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        LOGGER.info("Saving KingdomSavedData...");

        int[] fund = KingdomManager.getFundCoordinates();
        tag.putInt("FundX", fund[0]);
        tag.putInt("FundY", fund[1]);
        tag.putInt("FundZ", fund[2]);

        int[] coffers = KingdomManager.getCoffersCoordinates();
        tag.putInt("CoffersX", coffers[0]);
        tag.putInt("CoffersY", coffers[1]);
        tag.putInt("CoffersZ", coffers[2]);

        int[] prison = KingdomManager.getCapitalPrisonCoordinates();
        tag.putInt("PrisonX", prison[0]);
        tag.putInt("PrisonY", prison[1]);
        tag.putInt("PrisonZ", prison[2]);

        tag.putInt("SurgingSource", KingdomManager.getSurgingSource());
        tag.putInt("Discontent", KingdomManager.getDiscontent());

        return tag;
    }

    public static KingdomSavedData get(ServerLevel level) {
        LOGGER.info("Retrieving KingdomSavedData...");
        return level.getDataStorage().computeIfAbsent(KingdomSavedData::load, KingdomSavedData::new, DATA_NAME);
    }

    public static void set(ServerLevel level, KingdomSavedData data) {
        LOGGER.info("Saving KingdomSavedData to storage...");
        level.getDataStorage().set(DATA_NAME, data);
        data.setDirty();
    }

    public void markDirtyAndSave(ServerLevel level) {
        LOGGER.info("Marking KingdomSavedData as dirty and saving...");
        set(level, this);
    }

    private static String arrayToString(int[] coords) {
        return "[" + coords[0] + ", " + coords[1] + ", " + coords[2] + "]";
    }
}
