package net.mcreator.reignmod.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.mcreator.reignmod.house.HouseManager;



@Mixin(ChestBlock.class)
public class ChestLockMixin {

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void blockChestAccess(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<InteractionResult> cir) {
        if (!level.isClientSide) { // Проверка выполняется только на серверной стороне
            if (level.getBlockEntity(pos) instanceof ChestBlockEntity chest) {
                // Проверяем текущий сундук
                if (isChestLocked(chest, player, level, pos)) {
                    cir.setReturnValue(InteractionResult.FAIL);
                    return;
                }

                // Проверяем соседний сундук, если сундук двойной
                BlockEntity connectedChest = getConnectedChest(level, state, pos);
                if (connectedChest instanceof ChestBlockEntity neighborChest && isChestLocked(neighborChest, player, level, pos)) {
                    cir.setReturnValue(InteractionResult.FAIL);
                }
            }
        }
    }

    /**
     * Проверяет, заблокирован ли сундук.
     *
     * @param chest  Сундук, который нужно проверить.
     * @param player Игрок, который пытается открыть сундук.
     * @param level  Уровень (мир).
     * @param pos    Позиция сундука.
     * @return true, если сундук заблокирован.
     */
    private boolean isChestLocked(ChestBlockEntity chest, Player player, Level level, BlockPos pos) {
        CompoundTag tag = chest.getPersistentData(); // Получаем NBT-данные сундука

        if (tag.contains("owner")) {
            String owner = tag.getString("owner");
            String ownerName = tag.getString("owner_name");
            String lock_type = tag.getString("lock_type");

            // Проверяем, совпадает ли имя игрока с именем владельца
            if (owner != "" && !canOpen(player, owner, lock_type)) {
                // Воспроизведение звука
                level.playSound(null, pos, SoundEvents.WOODEN_DOOR_CLOSE, SoundSource.BLOCKS, 0.5f, 1.0f);

                // Отображение сообщения игроку
                player.displayClientMessage(Component.translatable("translation.key.locked").append(" " + ownerName), true);

                return true; // Сундук заблокирован
            }
        }
        return false; // Сундук не заблокирован
    }

	private boolean canOpen(Player player, String owner, String lock_type) {
        return switch (lock_type) {
            case "personal" -> player.getStringUUID().equals(owner);
            case "domain" -> player.getStringUUID().equals(owner) ||
                    HouseManager.getDomainLordByKnight(owner).equals(player.getStringUUID()) ||
                    HouseManager.getPlayerDomainKnight(player).equals(owner);
            case "house" -> HouseManager.getPlayerHouseLord(player).equals(owner);
            default -> false;
        };
    }
    

    /**
     * Получает соседний сундук, если сундук двойной.
     *
     * @param level Уровень (мир).
     * @param state Состояние текущего сундука.
     * @param pos   Позиция текущего сундука.
     * @return Соседний сундук или null.
     */
    private BlockEntity getConnectedChest(Level level, BlockState state, BlockPos pos) {
        if (state.getBlock() instanceof ChestBlock) {
            ChestType chestType = state.getValue(ChestBlock.TYPE); // Тип сундука
            Direction facing = state.getValue(ChestBlock.FACING); // Направление сундука

            if (chestType != ChestType.SINGLE) {
                // Для левого сундука сосед справа, для правого - слева
                Direction offsetDirection = chestType == ChestType.LEFT ? facing.getClockWise() : facing.getCounterClockWise();
                BlockPos neighborPos = pos.relative(offsetDirection);

                BlockState neighborState = level.getBlockState(neighborPos);
                if (neighborState.getBlock() instanceof ChestBlock && neighborState.getValue(ChestBlock.TYPE) != ChestType.SINGLE) {
                    return level.getBlockEntity(neighborPos); // Возвращаем соседний сундук
                }
            }
        }
        return null; // Нет соседнего сундука
    }
}
