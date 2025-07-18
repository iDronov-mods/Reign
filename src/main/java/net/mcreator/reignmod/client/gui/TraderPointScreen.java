package net.mcreator.reignmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.reignmod.world.inventory.TraderPointMenu;
import net.mcreator.reignmod.procedures.ReturnMarketCopperProcedure;
import net.mcreator.reignmod.init.ReignModModScreens.WidgetScreen;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class TraderPointScreen extends AbstractContainerScreen<TraderPointMenu> implements WidgetScreen {
	private final static HashMap<String, Object> guistate = TraderPointMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();

	public TraderPointScreen(TraderPointMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 200;
		this.imageHeight = 166;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 159 && mouseX < leftPos + 183 && mouseY > topPos + -21 && mouseY < topPos + 3)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.trader_point.tooltip_sell_your_goods_to_the_capital"), mouseX, mouseY);
		if (mouseX > leftPos + 0 && mouseX < leftPos + 18 && mouseY > topPos + 167 && mouseY < topPos + 185)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.trader_point.tooltip_coinage_help"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/trader.png"), this.leftPos + -25, this.topPos + -42, 0, 0, 250, 250, 250, 250);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/gold_coin.png"), this.leftPos + 177, this.topPos + 62, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/silver_coin.png"), this.leftPos + -6, this.topPos + 40, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/silver_coin.png"), this.leftPos + 6, this.topPos + 13, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/crown.png"), this.leftPos + 163, this.topPos + -12, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/copper_coin.png"), this.leftPos + 19, this.topPos + 168, 0, 0, 16, 16, 16, 16);

		RenderSystem.disableBlend();
	}

	public static HashMap<String, String> getTextboxValues() {
		return textstate;
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
		ReignModMod.PACKET_HANDLER.sendToServer(new TraderPointMenu.TraderPointOtherMessage(0, x, y, z, textstate));
		TraderPointMenu.TraderPointOtherMessage.handleOtherAction(entity, 0, x, y, z, textstate);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font,

				ReturnMarketCopperProcedure.execute(world), 35, 172, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.trader_point.label_trader"), 0, -10, -1, false);
	}

	@Override
	public void init() {
		super.init();
	}
}
