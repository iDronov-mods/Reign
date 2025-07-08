package net.mcreator.reignmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.reignmod.world.inventory.StrategyBlockUIMenu;
import net.mcreator.reignmod.procedures.StrategyBlockDefenceGetProcedure;
import net.mcreator.reignmod.procedures.StrategyBlockAttackGetProcedure;
import net.mcreator.reignmod.init.ReignModModScreens.WidgetScreen;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class StrategyBlockUIScreen extends AbstractContainerScreen<StrategyBlockUIMenu> implements WidgetScreen {
	private final static HashMap<String, Object> guistate = StrategyBlockUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();

	public StrategyBlockUIScreen(StrategyBlockUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("reign_mod:textures/screens/strategy_block_ui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 142 && mouseX < leftPos + 158 && mouseY > topPos + -10 && mouseY < topPos + 1)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.strategy_block_ui.tooltip_strategy_block_help"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/strategyblock_ui.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/crown.png"), this.leftPos + 142, this.topPos + -12, 0, 0, 16, 16, 16, 16);

		RenderSystem.disableBlend();
	}

	public HashMap<String, Object> getWidgets() {
		return guistate;
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		ReignModMod.PACKET_HANDLER.sendToServer(new StrategyBlockUIMenu.StrategyBlockUIOtherMessage(0, x, y, z, textstate));
		StrategyBlockUIMenu.StrategyBlockUIOtherMessage.handleOtherAction(entity, 0, x, y, z, textstate);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.strategy_block_ui.label_strategy_block"), 1, -10, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.strategy_block_ui.label_expansion"), 7, 8, -65485, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.strategy_block_ui.label_defence"), 126, 8, -10040065, false);
		guiGraphics.drawString(this.font,

				StrategyBlockAttackGetProcedure.execute(world, x, y, z), 15, 61, -1, false);
		guiGraphics.drawString(this.font,

				StrategyBlockDefenceGetProcedure.execute(world, x, y, z), 139, 61, -1, false);
	}

	@Override
	public void init() {
		super.init();
	}
}
