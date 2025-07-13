package net.mcreator.reignmod.claim.chunk;

import net.mcreator.reignmod.claim.capital.CapitalClaimSavedData;
import net.mcreator.reignmod.house.Domain;
import net.mcreator.reignmod.house.House;
import net.mcreator.reignmod.house.HouseManager;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import xaero.pac.common.server.player.config.PlayerConfig;

import java.util.Optional;
import java.util.UUID;

public class ChunkClaimManager {

    public static boolean isAreaFree(ChunkPos center, ClaimType type) {
        int radius = (type == ClaimType.CAPITAL)
                ? ChunkClaimConstants.CAPITAL_CLAIM_RADIUS
                : ChunkClaimConstants.CLAIM_RADIUS;
        var storage = ChunkClaimSavedData.getInstance();
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                ChunkPos cp = new ChunkPos(center.x + dx, center.z + dz);
                if (storage.isClaimed(cp)) {
                    return false;
                }
            }
        }
        return true;
    }


    public static boolean createClaim(ServerPlayer sv, ClaimType claimType, int centerX, int centerY, int centerZ) {
        if (claimType == ClaimType.CAPITAL) {
            return false;
        }
        ChunkPos center = new ChunkPos(new BlockPos(centerX, centerY, centerZ));
        if (!isAreaFree(center, claimType)) {
            sv.displayClientMessage(Component.translatable("chunkclaim.add.fail.occupied"), true);
            return false;
        }
        Domain foundDomain;
        House foundHouse;
        String claimId;
        if (claimType == ClaimType.DOMAIN) {
            foundDomain = HouseManager.getDomainByKnightUUID(sv.getStringUUID());
            foundHouse = HouseManager.getHouseByLordUUID(foundDomain.getLordUUID());
            claimId = foundDomain.getClaimId();
        } else {
            foundHouse = HouseManager.getHouseByLordUUID(sv.getStringUUID());
            foundDomain =  foundHouse.getDomains().get(sv.getStringUUID());
            claimId = foundHouse.getClaimId();
        }

        String claimName = foundHouse.getHouseTitle();
        String ownerName =  foundHouse.getHouseTitleWithColor() + ": " + foundDomain.getDomainTitle().getString();
        String color =  foundHouse.getHouseColor();

        if (claimId != null) {
            sv.displayClientMessage(Component.translatable("chunkclaim.add.fail.already_own"), true);
            return false;
        }

        claimId = UUID.randomUUID().toString();
        ClaimData data = new ClaimData(claimId, claimName, sv.getStringUUID(), ownerName, color, claimType, center.x, center.z);

        int radius = ChunkClaimConstants.CLAIM_RADIUS;
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                var chunkId = ChunkPos.asLong(center.x + dx, center.z + dz);
                data.addChunk(chunkId);
                if (dx == -radius || dx == radius || dz == -radius || dz == radius) {
                    data.addOuterChunk(chunkId);
                }
            }
        }

        ChunkClaimSavedData.getInstance().addClaim(data);
        if (claimType == ClaimType.DOMAIN) {
            Domain domain = HouseManager.getDomainByKnightUUID(sv.getStringUUID());
            domain.setClaimId(claimId);
            domain.setDomainFoundationCoordinates(new int[]{centerX, centerY, centerZ});
        } else {
            HouseManager.getHouseByLordUUID(sv.getStringUUID()).setClaimId(claimId);
        }

        sv.displayClientMessage(Component.translatable("chunkclaim.add.success"), true);
        return true;
    }

    public static boolean createCapitalClaim(int centerX, int centerY, int centerZ) {
        var center = new ChunkPos(new BlockPos(centerX, centerY, centerZ));
        if (!isAreaFree(center, ClaimType.CAPITAL)) {
            return false;
        }

        String claimId = CapitalClaimSavedData.getChunkClaimId();
        String ownerName = Component.translatable("chunkclaim.capital.name").getString();

        if (claimId != null) {
            return false;
        }

        claimId = UUID.randomUUID().toString();
        String ownerId = ModList.get().isLoaded("openpartiesandclaims") ? PlayerConfig.SERVER_CLAIM_UUID.toString() : claimId;

        ClaimData data = new ClaimData(claimId, ownerName, ownerId, ownerName, "white", ClaimType.CAPITAL, center.x, center.z);

        int radius = ChunkClaimConstants.CAPITAL_CLAIM_RADIUS;

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                data.addChunk(ChunkPos.asLong(center.x + dx, center.z + dz));
            }
        }

        ChunkClaimSavedData.getInstance().addClaim(data);
        CapitalClaimSavedData.setChunkClaimId(claimId);
        return true;
    }

    public static void removeClaim(ServerPlayer sv, String claimId) {
        if (ChunkClaimSavedData.getInstance().getClaim(claimId).isPresent()) {
            removeClaim(claimId);
            sv.displayClientMessage(Component.translatable("chunkclaim.remove.success"), true);
        }
        sv.displayClientMessage(Component.translatable("chunkclaim.remove.not_found"), true);
    }

    public static void removeClaim(ServerPlayer sp) {
        var claim = getClaimIdByChunk(new ChunkPos(sp.getOnPos()));
        if (claim.isPresent()) {
            removeClaim(claim.get());
            sp.displayClientMessage(Component.translatable("chunkclaim.remove.success"), false);
        }
        sp.displayClientMessage(Component.translatable("chunkclaim.remove.not_found"), false);
    }

    public static void removeClaim(String claimId) {
        if (ChunkClaimSavedData.getInstance().getClaim(claimId).isPresent()) {
            String ownerId = ChunkClaimSavedData.getInstance().getClaim(claimId).get().getOwnerId();
            ClaimType type = ChunkClaimSavedData.getInstance().getClaim(claimId).get().getClaimType();
            if (type == ClaimType.DOMAIN) {
                HouseManager.getDomainByKnightUUID(ownerId).setClaimId(null);
            } else if (type == ClaimType.HOUSE) {
                HouseManager.getHouseByLordUUID(ownerId).setClaimId(null);
            } else {
                CapitalClaimSavedData.setChunkClaimId(null);
            }
            ChunkClaimSavedData.getInstance().removeClaim(claimId);
        }
    }

    public static Optional<String> getChunkOwner(int chunkX, int chunkZ) {
        return ChunkClaimSavedData.getInstance()
                .getClaimByChunk(chunkX, chunkZ)
                .map(ClaimData::getOwnerId);
    }

    public static Optional<String> getChunkOwnerName(ChunkPos chunkPos) {
        return ChunkClaimSavedData.getInstance()
                .getClaimByChunk(chunkPos.x, chunkPos.z).map(ClaimData::getOwnerName);
    }

    public static boolean isOwnerOfChunk(String ownerId, int chunkX, int chunkZ) {
        return getChunkOwner(chunkX, chunkZ)
                .filter(o -> o.equals(ownerId))
                .isPresent();
    }

    public static Optional<String> getClaimIdByChunk(int chunkX, int chunkZ) {
        return ChunkClaimSavedData.getInstance()
                .getClaimByChunk(chunkX, chunkZ)
                .map(ClaimData::getClaimId);
    }

    public static Optional<String> getClaimIdByChunk(ChunkPos cp) {
        return getClaimIdByChunk(cp.x, cp.z);
    }

    public static boolean hasPermission(ServerPlayer player, BlockPos pos) {
        return hasPermission(player, new ChunkPos(pos));
    }

    public static boolean hasPermission(ServerPlayer player, ChunkPos pos) {
        if (!player.level().dimension().equals(Level.OVERWORLD)) {
            return true;
        }
        if (player.gameMode.isCreative()) {
            return true;
        }

        var claimOpt = ChunkClaimSavedData.getInstance().getClaimByChunk(pos.x, pos.z);
        if (claimOpt.isEmpty()) {
            return true;
        }

        var claimData = claimOpt.get();
        if (claimData.getClaimType() == ClaimType.HOUSE) {
            // HOUSE
            House chunkHouse = HouseManager.getHouseByLordUUID(claimData.getOwnerId());
            if (!chunkHouse.isNull()) {
                return chunkHouse.getDomains().containsKey(player.getStringUUID()) ||
                        chunkHouse.getDomains().get(chunkHouse.getLordUUID()).getPlayers().contains(player.getStringUUID()) ||
                        chunkHouse.isIndirectVassalsBeTrusted() && chunkHouse.getPlayers().contains(player.getStringUUID());
            }
        } else if (claimData.getClaimType() == ClaimType.DOMAIN){
            // DOMAIN
            Domain domain = HouseManager.getDomainByKnightUUID(claimData.getOwnerId());
            House chunkHouse = HouseManager.getHouseByLordUUID(domain.getLordUUID());
            if (!domain.isNull()) {
                if (!chunkHouse.isNull() && chunkHouse.isIndirectVassalsBeTrusted() && chunkHouse.getPlayers().contains(player.getStringUUID())) {
                    return true;
                }
                return domain.getPlayers().contains(player.getStringUUID()) || domain.getLordUUID().equals(player.getStringUUID());
            }
        } else {
            return true;
        }

        return false;
    }
}
