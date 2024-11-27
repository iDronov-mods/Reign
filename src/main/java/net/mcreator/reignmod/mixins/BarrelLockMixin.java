package net.mcreator.reignmod.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.mcreator.reignmod.house.HouseManager;

@Mixin(BarrelBlock.class)
public class BarrelLockMixin {

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void blockBarrelAccess(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<InteractionResult> cir) {
        if (!level.isClientSide) { // Проверка выполняется только на серверной стороне
            if (level.getBlockEntity(pos) instanceof BarrelBlockEntity barrel) {
                if (isBarrelLocked(barrel, player, level, pos)) {
                    cir.setReturnValue(InteractionResult.FAIL);
                }
            }
        }
    }

    /**
     * Проверяет, заблокирована ли бочка.
     *
     * @param barrel Бочка, которую нужно проверить.
     * @param player Игрок, который пытается открыть бочку.
     * @param level  Уровень (мир).
     * @param pos    Позиция бочки.
     * @return true, если бочка заблокирована.
     */
    private boolean isBarrelLocked(BarrelBlockEntity barrel, Player player, Level level, BlockPos pos) {
        CompoundTag tag = barrel.getPersistentData(); // Получаем NBT-данные бочки

        if (tag.contains("owner")) {
            String owner = tag.getString("owner");
            String ownerName = tag.getString("owner_name");
            String lock_type = tag.getString("lock_type");

            if (!owner.isEmpty() && !canOpen(player, owner, lock_type)) {
                level.playSound(null, pos, SoundEvents.WOODEN_DOOR_CLOSE, SoundSource.BLOCKS, 0.5f, 1.0f);

                player.displayClientMessage(Component.translatable("translation.key.locked").append(" " + ownerName), true);

                return true; // Бочка заблокирована
            }
        }
        return false; // Бочка не заблокирована
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
}
