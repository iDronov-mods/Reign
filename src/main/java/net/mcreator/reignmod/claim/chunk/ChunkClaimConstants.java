package net.mcreator.reignmod.claim.chunk;

public class ChunkClaimConstants {
    public static final int CLAIM_DIAMETER = 21;
    public static final int CLAIM_RADIUS = CLAIM_DIAMETER / 2;

    public static final int CAPITAL_CLAIM_DIAMETER = 35;
    public static final int CAPITAL_CLAIM_RADIUS = CAPITAL_CLAIM_DIAMETER / 2;

    public static final int DOMAIN_SHAFT_LENGTH = 3;
    public static final int HOUSE_SHAFT_LENGTH = 4;

    private ChunkClaimConstants() {
        throw new UnsupportedOperationException(
                "ChunkClaimConstants — утилитарный класс, не должен инстанцироваться"
        );
    }
}
