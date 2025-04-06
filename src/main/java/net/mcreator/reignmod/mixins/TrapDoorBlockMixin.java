package net.mcreator.reignmod.mixins;

import net.mcreator.reignmod.house.HouseManager;
import net.mcreator.reignmod.basics.lock.IDoorLockHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TrapDoorBlock.class)
public abstract class TrapDoorBlockMixin {

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    public void reign$onUse(BlockState state, Level level, BlockPos pos, Player player,
                            InteractionHand hand, BlockHitResult hit,
                            CallbackInfoReturnable<InteractionResult> cir) {
        if (level.isClientSide) return;

        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof IDoorLockHandler lock)) return;

        String owner = lock.reign$getOwner();
        String ownerName = lock.reign$getOwnerName();
        String lockType = lock.reign$getLockType();

        if (!owner.isEmpty() && !canOpen(player, owner, lockType)) {
            level.playSound(null, pos, SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundSource.BLOCKS, 0.5f, 1.0f);
            player.displayClientMessage(Component.translatable("translation.key.locked").append(" " + ownerName), true);
            cir.setReturnValue(InteractionResult.FAIL);
        }
    }

    @Inject(method = "neighborChanged", at = @At("HEAD"), cancellable = true)
    public void reign$onNeighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean flag, CallbackInfo ci) {
        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof IDoorLockHandler lock)) return;

        String lockType = lock.reign$getLockType();
        if (!lockType.isEmpty() && !lockType.equals("none")) {
            ci.cancel();
        }
    }

    @Unique
    private boolean canOpen(Player player, String owner, String lock_type) {
        return switch (lock_type) {
            case "personal" -> player.getStringUUID().equals(owner);
            case "domain" -> player.getStringUUID().equals(owner)
                    || HouseManager.getDomainLordByKnight(owner).equals(player.getStringUUID())
                    || HouseManager.getPlayerDomainKnight(player).equals(owner);
            case "house" -> HouseManager.getPlayerHouseLord(player).equals(owner);
            default -> false;
        };
    }
}