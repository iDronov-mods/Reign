package net.mcreator.reignmod.basics;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

/**
 * Абстрактный класс для сохранённых данных, содержащий общее поле serverLevelInstance
 * и требующий от подклассов определить уникальный ключ сохранения.
 */
public abstract class ReignSavedData extends SavedData {
    protected ServerLevel serverLevelInstance;

    /**
     * Каждый подкласс должен вернуть уникальный ключ данных для сохранения в DataStorage.
     */
    protected abstract String getDataKey();

    public ServerLevel getServerLevelInstance() {
        return serverLevelInstance;
    }
}
