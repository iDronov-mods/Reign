package net.mcreator.reignmod.mines;

import java.util.Map;

public class MineConstants {
    public static final double MINE_SPAWN_CHANCE = 0.5;
    public static final double MINE_SPAWN_CHANCE_MOUNTAIN_AND_HILL = 0.01;

    public static final double RICH_MINE_CHANCE = 0.2;
    public static final double RICH_MULTIPLIER = 1.5;
    public static final double RICH_CAPACITY_MULTIPLIER = 3;

    public static final int MAX_WORKERS = 4;
    public static final int HOURS_PER_WORKER = 12;

    public static final int VISIBILITY_RADIUS = 3;
    public static final int VISIBILITY_DIAMETER = VISIBILITY_RADIUS * 2 + 1;

    public static final Map<MineType, Double> TYPE_WEIGHTS = Map.of(
        MineType.COPPER, 60.0,
        MineType.IRON, 60.0,
        MineType.GOLD, 40.0,
        MineType.DIAMOND, 20.0,
        MineType.EMERALD, 20.0,
        MineType.SOURCE, 5.0
    );

    public static final Map<MineType, Double> BASE_YIELDS = Map.of(
            MineType.COPPER, 3.0,
            MineType.IRON, 3.0,
            MineType.GOLD, 1.0,
            MineType.DIAMOND, 0.5,
            MineType.EMERALD, 0.25,
            MineType.SOURCE, 0.1
    );

    public static final Map<MineType, Double> MAX_CAPACITY = Map.of(
            MineType.COPPER, 2500.0,
            MineType.IRON, 3000.0,
            MineType.GOLD, 1000.0,
            MineType.DIAMOND, 700.0,
            MineType.EMERALD, 250.0,
            MineType.SOURCE, 300.0
    );
}