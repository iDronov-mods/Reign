
package net.mcreator.reignmod.client.screens;

import org.checkerframework.checker.units.qual.h;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.Minecraft;

import net.mcreator.reignmod.procedures.ReturnwoodcutterProcedure;
import net.mcreator.reignmod.procedures.ReturnsmithProcedure;
import net.mcreator.reignmod.procedures.ReturnminerProcedure;
import net.mcreator.reignmod.procedures.ReturnfarmerProcedure;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class InventoryADDOverlay {
	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void eventHandler(ScreenEvent.Render.Post event) {
		if (event.getScreen() instanceof InventoryScreen) {
			int w = event.getScreen().width;
			int h = event.getScreen().height;
			Level world = null;
			double x = 0;
			double y = 0;
			double z = 0;
			Player entity = Minecraft.getInstance().player;
			if (entity != null) {
				world = entity.level();
				x = entity.getX();
				y = entity.getY();
				z = entity.getZ();
			}
			RenderSystem.disableDepthTest();
			RenderSystem.depthMask(false);
			RenderSystem.enableBlend();
			RenderSystem.setShader(GameRenderer::getPositionTexShader);
			RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			RenderSystem.setShaderColor(1, 1, 1, 1);
			if (true) {
				if (ReturnwoodcutterProcedure.execute(entity)) {
					event.getGuiGraphics().blit(new ResourceLocation("reign_mod:textures/screens/woodcutter_logo.png"), 6, 8, 0, 0, 16, 16, 16, 16);
				}
				if (ReturnsmithProcedure.execute(entity)) {
					event.getGuiGraphics().blit(new ResourceLocation("reign_mod:textures/screens/smith_logo.png"), 6, 8, 0, 0, 16, 16, 16, 16);
				}
				if (ReturnfarmerProcedure.execute(entity)) {
					event.getGuiGraphics().blit(new ResourceLocation("reign_mod:textures/screens/.png"), 6, 8, 0, 0, 16, 16, 16, 16);
				}
				if (ReturnminerProcedure.execute(entity)) {
					event.getGuiGraphics().blit(new ResourceLocation("reign_mod:textures/screens/miner_logo.png"), 6, 8, 0, 0, 16, 16, 16, 16);
				}
				event.getGuiGraphics().blit(new ResourceLocation("reign_mod:textures/screens/crown.png"), w / 2 + 54, h / 2 + -94, 0, 0, 16, 16, 16, 16);

			}
			RenderSystem.depthMask(true);
			RenderSystem.defaultBlendFunc();
			RenderSystem.enableDepthTest();
			RenderSystem.disableBlend();
			RenderSystem.setShaderColor(1, 1, 1, 1);
		}
	}
}
