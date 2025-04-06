package net.mcreator.reignmod.basics.lock;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class DoorLockUtil {

    /**
     * Устанавливает замок на дверь/люк/калитку, если поддерживается.
     * @param x координата X
     * @param y координата Y
     * @param z координата Z
     * @param level серверный уровень
     * @param owner UUID владельца
     * @param ownerName имя владельца
     * @param lockType тип замка (personal, domain, house)
     */
    public static void trySetLock(double x, double y, double z, ServerLevel level, ServerPlayer player, String owner, String ownerName, String lockType) {
        BlockPos pos = new BlockPos((int) x, (int) y, (int) z);
        BlockState state = level.getBlockState(pos);

        if (state.getBlock() instanceof DoorBlock && state.getValue(DoorBlock.HALF) == DoubleBlockHalf.UPPER) {
            pos = pos.below();
        }

        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof IDoorLockHandler lock) {
            lock.reign$setLockData(owner, ownerName, lockType);
            be.setChanged();
            player.displayClientMessage(Component.literal("§aУстановлен замок: " + lockType), true);
        } else {
            player.displayClientMessage(Component.literal("§cЭтот блок нельзя запереть."), true);
        }

    }
}