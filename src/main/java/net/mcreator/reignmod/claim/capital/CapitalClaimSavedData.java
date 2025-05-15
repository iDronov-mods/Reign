package net.mcreator.reignmod.claim.capital;

import net.mcreator.reignmod.basics.ReignSavedData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Хранит все приваты столицы.
 * Используется двумерный массив ownerGrid (тип ClaimOwner[][]) размером 257×257,
 * где для каждого блока хранится владелец привата (null, если приват отсутствует).
 */
public class CapitalClaimSavedData extends ReignSavedData {
    public static final int CAPITAL_SIZE = 511;
    private static final int PRIME = 31;
    private static CapitalClaimSavedData instance;
    private static String chunkClaimId = null;
    private int capitalHeartX = 0;
    private int capitalHeartZ = 0;
    // Карта заявок по владельцам
    private final HashMap<ClaimOwner, HashMap<Long, TerritoryClaim>> claimsMap = new HashMap<>();
    // ownerGrid: для каждого блока столицы хранится владелец привата (или null)
    private final ClaimOwner[][] ownerGrid = new ClaimOwner[CAPITAL_SIZE][CAPITAL_SIZE];
    // Булевая переменная, определяющая, включена ли система привата (по умолчанию false)
    private boolean capitalClaimsEnabled = false;

    public CapitalClaimSavedData() {
    }

    public CapitalClaimSavedData(CompoundTag compoundTag) {
        capitalHeartX = compoundTag.getInt("capital_center_x");
        capitalHeartZ = compoundTag.getInt("capital_center_z");

        ListTag claimList = compoundTag.getList("claims", 10); // 10 = CompoundTag
        for (int i = 0; i < claimList.size(); i++) {
            CompoundTag entryTag = claimList.getCompound(i);
            ClaimOwner owner = ClaimOwner.deserializeNBT(entryTag.getCompound("owner"));
            TerritoryClaim claim = TerritoryClaim.deserializeNBT(entryTag.getCompound("claim"));
            claimsMap.computeIfAbsent(owner, k -> new HashMap<>())
                    .put(calculateHash(claim.getCenterX(), claim.getCenterZ()), claim);
            fillOwnerGrid(owner, claim);
        }
        // При загрузке система остаётся отключенной
        capitalClaimsEnabled = compoundTag.getBoolean("capital_claims_enabled");

        // Claim ID
        String loadedClaim = compoundTag.getString("chunk_claim_id");
        chunkClaimId = loadedClaim.isEmpty() ? null : loadedClaim;
    }

    public static void initialize(ServerLevel serverLevel) {
        if (instance == null) {
            instance = serverLevel.getDataStorage().computeIfAbsent(
                    CapitalClaimSavedData::new, CapitalClaimSavedData::new, "capital_claims_data"
            );
            CapitalClaimManager.setCapitalCenter(instance.capitalHeartX, instance.capitalHeartZ);
            instance.serverLevelInstance = serverLevel;
        }
    }

    public static CapitalClaimSavedData getInstance() {
        if (instance == null) {
            throw new IllegalStateException("CapitalClaimsData is not initialized. Call initialize(ServerLevel) first.");
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }

    public static long calculateHash(int x, int z) {
        return ((long) x * PRIME) ^ ((long) z * PRIME);
    }

    // Методы включения/выключения системы привата
    public void enable() {
        capitalClaimsEnabled = true;
        setDirty();
    }

    public void disable() {
        capitalClaimsEnabled = false;
        setDirty();
    }

    public boolean isCapitalClaimsEnabled() {
        return capitalClaimsEnabled;
    }

    public static String getChunkClaimId() {
        return chunkClaimId;
    }

    public static void setChunkClaimId(String chunkClaimId) {
        CapitalClaimSavedData.chunkClaimId = chunkClaimId;
    }

    /**
     * Добавляет приват для указанного владельца.
     * Возвращает true, если область свободна и заявка успешно добавлена.
     */
    public boolean addClaim(ClaimOwner owner, TerritoryClaim claim) {
        if (!capitalClaimsEnabled) return false;
        if (!isClaimValid(claim)) return false;
        long hash = calculateHash(claim.getCenterX(), claim.getCenterZ());
        claimsMap.computeIfAbsent(owner, k -> new HashMap<>()).put(hash, claim);
        fillOwnerGrid(owner, claim);
        setDirty();
        return true;
    }

    /**
     * Удаляет приват по центру (centerX, centerZ). Возвращает true, если заявка была удалена.
     */
    public boolean removeClaim(int centerX, int centerZ) {
        if (!capitalClaimsEnabled) return false;
        ClaimOwner owner = getOwnerAt(centerX, centerZ);
        if (owner == null) return false;
        long hash = calculateHash(centerX, centerZ);
        Map<Long, TerritoryClaim> ownerClaims = claimsMap.get(owner);
        if (ownerClaims != null) {
            TerritoryClaim claim = ownerClaims.remove(hash);
            if (claim != null) {
                clearOwnerGrid(claim);
                setDirty();
                return true;
            }
        }
        return false;
    }

    /**
     * Возвращает владельца привата, покрывающего блок с координатами (x, z), за O(1).
     */
    public ClaimOwner getOwnerAt(int x, int z) {
        if (x < 0 || x >= CAPITAL_SIZE || z < 0 || z >= CAPITAL_SIZE) return null;
        return ownerGrid[x][z];
    }

    /**
     * Проверяет, что новый приват находится в пределах столицы и не перекрывает существующие.
     */
    private boolean isClaimValid(TerritoryClaim newClaim) {
        if (newClaim.getStartX() < 0 || newClaim.getEndX() > CAPITAL_SIZE ||
                newClaim.getStartZ() < 0 || newClaim.getEndZ() > CAPITAL_SIZE)
            return false;
        for (int x = newClaim.getStartX(); x < newClaim.getEndX(); x++) {
            for (int z = newClaim.getStartZ(); z < newClaim.getEndZ(); z++) {
                if (ownerGrid[x][z] != null) return false;
            }
        }
        return true;
    }

    // Заполняет ownerGrid для каждого блока, принадлежащего данному привату
    private void fillOwnerGrid(ClaimOwner owner, TerritoryClaim claim) {
        for (int x = claim.getStartX(); x < claim.getEndX(); x++) {
            for (int z = claim.getStartZ(); z < claim.getEndZ(); z++) {
                ownerGrid[x][z] = owner;
            }
        }
    }

    // Очищает ownerGrid для блоков, занимаемых заявкой
    private void clearOwnerGrid(TerritoryClaim claim) {
        for (int x = claim.getStartX(); x < claim.getEndX(); x++) {
            for (int z = claim.getStartZ(); z < claim.getEndZ(); z++) {
                ownerGrid[x][z] = null;
            }
        }
    }

    public int getCapitalCenterX() {
        return capitalHeartX;
    }

    public int getCapitalCenterZ() {
        return capitalHeartZ;
    }

    public void setCapitalCenter(int x, int z) {
        capitalHeartX = x;
        capitalHeartZ = z;
        setDirty();
    }

    @Override
    protected String getDataKey() {
        return "capital_claims_data";
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag compoundTag) {
        compoundTag.putInt("capital_center_x", capitalHeartX);
        compoundTag.putInt("capital_center_z", capitalHeartZ);

        ListTag claimList = new ListTag();
        for (Map.Entry<ClaimOwner, HashMap<Long, TerritoryClaim>> ownerEntry : claimsMap.entrySet()) {
            for (Map.Entry<Long, TerritoryClaim> claimEntry : ownerEntry.getValue().entrySet()) {
                CompoundTag entryTag = new CompoundTag();
                entryTag.put("owner", ownerEntry.getKey().serializeNBT());
                entryTag.put("claim", claimEntry.getValue().serializeNBT());
                claimList.add(entryTag);
            }
        }
        compoundTag.put("claims", claimList);
        compoundTag.putBoolean("capital_claims_enabled", capitalClaimsEnabled);

        // Claim ID
        compoundTag.putString("chunk_claim_id", (chunkClaimId == null) ? "" : chunkClaimId);

        return compoundTag;
    }
}
