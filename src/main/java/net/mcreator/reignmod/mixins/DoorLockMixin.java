package net.mcreator.reignmod.mixins;

import net.mcreator.reignmod.block.PrivatedoorBlock;
import net.mcreator.reignmod.block.entity.PrivatedoorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.BlockHitResult;
import net.mcreator.reignmod.house.HouseManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DoorBlock.class)
public class DoorLockMixin {

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void blockDoorAccess(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<InteractionResult> cir) {
        if (!level.isClientSide && state.getBlock() instanceof PrivatedoorBlock) {
            BlockPos lowerPos = state.getValue(DoorBlock.HALF) == DoubleBlockHalf.LOWER ? pos : pos.below();
            BlockEntity entity = level.getBlockEntity(lowerPos);
            if (entity instanceof PrivatedoorBlockEntity door && reignMod$isDoorLocked(door, player, level, lowerPos)) {
                cir.setReturnValue(InteractionResult.FAIL);
            }
        }
    }

    @Unique
    private boolean reignMod$isDoorLocked(PrivatedoorBlockEntity door, Player player, Level level, BlockPos pos) {
        CompoundTag tag = door.getPersistentData();

        if (tag.contains("owner")) {
            String owner = tag.getString("owner");
            String ownerName = tag.getString("owner_name");
            String lockType = tag.getString("lock_type");

            if (!owner.isEmpty() && !canOpen(player, owner, lockType)) {
                level.playSound(null, pos, SoundEvents.IRON_DOOR_CLOSE, SoundSource.BLOCKS, 0.5f, 1.0f);
                player.displayClientMessage(Component.translatable("translation.key.locked").append(" " + ownerName), true);
                return true;
            }
        }
        return false;
    }

    @Unique
    private boolean canOpen(Player player, String owner, String lockType) {
        return switch (lockType) {
            case "personal" -> player.getStringUUID().equals(owner);
            case "domain" -> player.getStringUUID().equals(owner)
                    || HouseManager.getDomainLordByKnight(owner).equals(player.getStringUUID())
                    || HouseManager.getPlayerDomainKnight(player).equals(owner);
            case "house" -> HouseManager.getPlayerHouseLord(player).equals(owner);
            default -> false;
        };
    }
}