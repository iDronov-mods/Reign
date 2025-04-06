package net.mcreator.reignmod.basics.lock;


import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.core.Direction;

public class LockChecker {

    /**
     * Проверяет, является ли сундук двойным или дверью, и если да, то проверяет наличие владельца у второй половины.
     *
     * @param state Состояние блока, на который нажал игрок.
     * @param level Уровень (мир).
     * @param pos   Позиция блока.
     * @return true, если сундук или дверь двойные и у их второй половины уже есть владелец.
     */
    public static boolean isLockedByOther(BlockState state, Level level, BlockPos pos) {
        if (state.getBlock() instanceof ChestBlock) {
            ChestType chestType = state.getValue(ChestBlock.TYPE);
            if (chestType == ChestType.SINGLE) {
                return false; // Одиночный сундук, соседнего нет
            }

            Direction facing = state.getValue(ChestBlock.FACING);
            Direction offsetDirection = chestType == ChestType.LEFT ? facing.getClockWise() : facing.getCounterClockWise();
            BlockPos neighborPos = pos.relative(offsetDirection);

            BlockEntity neighborEntity = level.getBlockEntity(neighborPos);
            if (neighborEntity instanceof ChestBlockEntity neighborChest) {
                CompoundTag neighborTag = neighborChest.getPersistentData();
                return neighborTag.contains("owner"); // Проверяем, есть ли у соседнего сундука владелец
            }
        }
        
        if (state.getBlock() instanceof DoorBlock) {
            DoubleBlockHalf half = state.getValue(DoorBlock.HALF);
            BlockPos neighborPos = half == DoubleBlockHalf.LOWER ? pos.above() : pos.below();
            BlockState neighborState = level.getBlockState(neighborPos);
            
            if (neighborState.getBlock() instanceof DoorBlock) {
                BlockEntity neighborEntity = level.getBlockEntity(neighborPos);
                if (neighborEntity != null) {
                    CompoundTag neighborTag = neighborEntity.getPersistentData();
                    return neighborTag.contains("owner"); // Проверяем, есть ли у второй половины двери владелец
                }
            }
        }
        
        return false;
    }
}
