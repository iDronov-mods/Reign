package net.mcreator.reignmod.mixins;

import net.mcreator.reignmod.block.PrivatedoorBlock;
import net.mcreator.reignmod.block.entity.PrivatedoorBlockEntity;
import net.mcreator.reignmod.networking.ClientPlayerData;
import net.mcreator.reignmod.networking.ReignNetworking;
import net.mcreator.reignmod.networking.packet.C2S.DoorOpenPermissionQueryC2SPacket;
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

import java.util.Objects;

@Mixin(DoorBlock.class)
public class DoorLockMixin {

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void blockDoorAccess(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<InteractionResult> cir) {
        if (!level.isClientSide && state.getBlock() instanceof PrivatedoorBlock) {
            BlockPos lowerPos = state.getValue(DoorBlock.HALF) == DoubleBlockHalf.LOWER ? pos : pos.below();
            BlockEntity entity = level.getBlockEntity(lowerPos);
            if (entity instanceof PrivatedoorBlockEntity door && reignMod$isDoorLocked(door, player, level, lowerPos)) {
                cir.setReturnValue(InteractionResult.PASS);
            }
        }
        if (level.isClientSide && state.getBlock() instanceof PrivatedoorBlock) {
            BlockPos lowerPos = state.getValue(DoorBlock.HALF) == DoubleBlockHalf.LOWER ? pos : pos.below();

            // Если нет данных по этой двери или они устарели — запросить у сервера
            if (ClientPlayerData.isLastKnownDoorEmpty() || !ClientPlayerData.isLastKnownDoor(lowerPos)) {
                ReignNetworking.sendToServer(new DoorOpenPermissionQueryC2SPacket(lowerPos));
            }

            // Если известно, что нельзя открыть — блокируем анимацию и показываем сообщение
            if (!ClientPlayerData.isLastKnownDoorAvailable()) {
                level.playSound(null, lowerPos, SoundEvents.IRON_DOOR_CLOSE,
                        SoundSource.BLOCKS, 0.5f, 1.0f);
                player.displayClientMessage(Component.translatable("translation.key.locked"), true);
                cir.setReturnValue(InteractionResult.PASS);
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

            if (!owner.isEmpty() && !reign$canOpen(player, owner, lockType)) {
                level.playSound(null, pos, SoundEvents.IRON_DOOR_CLOSE, SoundSource.BLOCKS, 0.5f, 1.0f);
                player.displayClientMessage(Component.translatable("translation.key.locked").append(" " + ownerName), true);
                return true;
            }
        }
        return false;
    }

    @Unique
    private boolean reign$canOpen(Player player, String owner, String lockType) {
        return switch (lockType) {
            case "personal" -> player.getStringUUID().equals(owner);
            case "domain" -> player.getStringUUID().equals(owner)
                    || Objects.equals(HouseManager.getDomainLordByKnight(owner), player.getStringUUID())
                    || Objects.equals(HouseManager.getPlayerDomainKnight(player), owner);
            case "house" -> Objects.equals(HouseManager.getPlayerHouseLord(player), owner);
            default -> false;
        };
    }
}