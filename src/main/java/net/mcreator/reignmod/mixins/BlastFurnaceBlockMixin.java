package net.mcreator.reignmod.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraft.network.chat.Component;
import net.mcreator.reignmod.network.ReignModModVariables;
import net.minecraft.world.level.block.BlastFurnaceBlock;



@Mixin(BlastFurnaceBlock.class)
public class BlastFurnaceBlockMixin {
    @Inject(at = @At("HEAD"), method = "openContainer", cancellable = true)
    private void openContainer(Level p_53631_, BlockPos p_53632_, Player entity, CallbackInfo cir) {
    	if (!(entity.getCapability(ReignModModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ReignModModVariables.PlayerVariables())).license_smith ) 
    	{
    		entity.displayClientMessage(Component.literal((Component.translatable("NotSmithSay").getString())), true);
    		    		cir.cancel();
    	}
	}
}