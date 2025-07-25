package net.mcreator.reignmod.mines;

import net.mcreator.reignmod.claim.chunk.ChunkClaimManager;
import net.mcreator.reignmod.claim.chunk.ChunkClaimSavedData;
import net.mcreator.reignmod.house.HouseManager;
import net.mcreator.reignmod.init.ReignModModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import org.apache.logging.log4j.LogManager;

import java.util.*;

public class MineUtils {

    public static Component getLocalizedBiomeName(ChunkPos pos) {
        ServerLevel level = ChunkClaimSavedData.getServerInstance();
        Holder<Biome> holder = level.getBiome(pos.getMiddleBlockPosition(300));
        return holder.unwrapKey()
                .map(key -> {
                    ResourceLocation id = key.location(); // например, minecraft:plains
                    String translationKey = "biome." + id.getNamespace() + "." + id.getPath();
                    return Component.translatable(translationKey);
                })
                .orElse(Component.literal("Unknown"));
    }

    public static float calculateSkyVisibility(ServerLevel level, BlockPos minePos) {
        int visible = 0;
        int total = MineConstants.VISIBILITY_DIAMETER * MineConstants.VISIBILITY_DIAMETER;

        int baseX = minePos.getX();
        int baseY = minePos.getY();
        int non_bedrock_layer = level.getMinBuildHeight() + 5;
        int baseZ = minePos.getZ();

        int checkFromY = minePos.getY() > non_bedrock_layer ? minePos.getY() + 1 : non_bedrock_layer;

        if (!level.getBlockState(new BlockPos(baseX, baseY + 1, baseZ)).getBlock().equals(Blocks.AIR)) {
            return 0.0f;
        }

        for (int dx = -MineConstants.VISIBILITY_RADIUS; dx <= MineConstants.VISIBILITY_RADIUS; dx++) {
            for (int dz = -MineConstants.VISIBILITY_RADIUS; dz <= MineConstants.VISIBILITY_RADIUS; dz++) {
                BlockPos pos = new BlockPos(baseX + dx, checkFromY, baseZ + dz);
                if (level.canSeeSky(pos)) {
                    visible++;
                }
            }
        }
        return visible / (float) total;
    }

    public static Optional<MineData> tryGenerateMine(ServerLevel level, ChunkPos chunkPos, RandomSource random) {
        MineSavedData saved = MineSavedData.getInstance();
        MineChunkData data = saved.getMineData();

        if (data.isChecked(chunkPos)) return data.getMine(chunkPos);
        data.markChecked(chunkPos);

        BlockPos sample = chunkPos.getMiddleBlockPosition(300);
        var biomeHolder = level.getBiome(sample);

        if (random.nextDouble() > (biomeHolder.is(BiomeTags.IS_MOUNTAIN) || biomeHolder.is(BiomeTags.IS_HILL) ? MineConstants.MINE_SPAWN_CHANCE_MOUNTAIN_AND_HILL : MineConstants.MINE_SPAWN_CHANCE)) {
            saved.setDirty();
            return Optional.empty();
        }

        List<MineType> allowed = getAllowedTypesForBiome(biomeHolder);
        if (allowed.isEmpty()) {
            saved.setDirty();
            return Optional.empty();
        }

        MineType type = getWeightedRandomType(allowed, random);
        MineRichness richness = random.nextDouble() < MineConstants.RICH_MINE_CHANCE ? MineRichness.RICH : MineRichness.POOR;

        MineData mine = new MineData(chunkPos, type, richness);
        data.addMine(chunkPos, mine);

        saved.setDirty();
        return Optional.of(mine);
    }

    public static boolean isSerf(ServerPlayer sp) {
        var domain = HouseManager.getDomainByKnightUUID(HouseManager.getPlayerSuzerain(sp));
        return !domain.isNull() && !domain.getKnightUUID().equals(sp.getStringUUID()) && domain.getPlayers().contains(sp.getStringUUID());
    }

    public static boolean isOnPlayerDomain(ServerPlayer sp, ChunkPos c) {
        var house = HouseManager.getPlayerHouse(sp);
        if (house.isNull()) {
            return false;
        }
        return ChunkClaimManager.isOwnerOfChunk(house.getLordUUID(), c.x, c.z);
    }

    private static List<MineType> getAllowedTypesForBiome(Holder<Biome> biome) {
        if (biome.is(BiomeTags.WATER_ON_MAP_OUTLINES) || biome.is(BiomeTags.IS_BEACH) || biome.is(BiomeTags.IS_NETHER) || biome.is(BiomeTags.IS_END)) {
            return List.of();
        }

        List<MineType> result = new ArrayList<>();
        result.add(MineType.COPPER);
        result.add(MineType.SOURCE);

        if (biome.is(BiomeTags.IS_FOREST) || biome.is(BiomeTags.IS_TAIGA) || biome.is(BiomeTags.IS_MOUNTAIN) || biome.is(BiomeTags.IS_SAVANNA)) {
            result.add(MineType.IRON);
        }

        if (biome.is(BiomeTags.IS_MOUNTAIN) || biome.is(BiomeTags.IS_BADLANDS)) {
            result.add(MineType.GOLD);
        }

        if (biome.is(BiomeTags.IS_JUNGLE) || biome.is(BiomeTags.IS_HILL)) {
            result.add(MineType.DIAMOND);
        }

        if (biome.is(BiomeTags.IS_JUNGLE) || biome.is(BiomeTags.IS_MOUNTAIN)) {
            result.add(MineType.EMERALD);
        }

        return result;
    }


    private static MineType getWeightedRandomType(List<MineType> types, RandomSource random) {
        NavigableMap<Double, MineType> map = new TreeMap<>();
        double total = 0.0;

        for (MineType type : types) {
            double weight = MineConstants.TYPE_WEIGHTS.getOrDefault(type, 1.0);
            if (weight <= 0) continue;
            total += weight;
            map.put(total, type);
        }

        double r = random.nextDouble() * total;
        return map.higherEntry(r).getValue();
    }
}