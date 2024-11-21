package net.mcreator.reignmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.reignmod.world.inventory.MarketMenu;
import net.mcreator.reignmod.procedures.TextMarketTaxProcedure;
import net.mcreator.reignmod.procedures.TextMarketBarProcedure;
import net.mcreator.reignmod.procedures.ReturnMarketBar33Procedure;
import net.mcreator.reignmod.procedures.ReturnMarketBar23Procedure;
import net.mcreator.reignmod.procedures.ReturnMarketBar13Procedure;
import net.mcreator.reignmod.procedures.ReturnMarketBar03Procedure;
import net.mcreator.reignmod.network.MarketButtonMessage;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class MarketScreen extends AbstractContainerScreen<MarketMenu> {
	private final static HashMap<String, Object> guistate = MarketMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_buy;
	Button button_x4;

	public MarketScreen(MarketMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 319;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("reign_mod:textures/screens/market.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 286 && mouseX < leftPos + 310 && mouseY > topPos + -22 && mouseY < topPos + 2)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.market.tooltip_buy_goods_for_money"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/crown.png"), this.leftPos + 290, this.topPos + -12, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/copper_coin.png"), this.leftPos + 36, this.topPos + -14, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/silver_coin.png"), this.leftPos + 47, this.topPos + -14, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/gold_coin.png"), this.leftPos + 58, this.topPos + -14, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/arrow.png"), this.leftPos + 53, this.topPos + 84, 0, 0, 32, 16, 32, 16);

		if (ReturnMarketBar03Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/count_bar0-3.png"), this.leftPos + 53, this.topPos + 102, 0, 0, 31, 6, 31, 6);
		}
		if (ReturnMarketBar13Procedure.execute(world, entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/count_bar1-3.png"), this.leftPos + 53, this.topPos + 102, 0, 0, 31, 6, 31, 6);
		}
		if (ReturnMarketBar23Procedure.execute(world, entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/count_bar2-3.png"), this.leftPos + 53, this.topPos + 102, 0, 0, 31, 6, 31, 6);
		}
		if (ReturnMarketBar33Procedure.execute(world, entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/count_bar3-3.png"), this.leftPos + 53, this.topPos + 102, 0, 0, 31, 6, 31, 6);
		}

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/copper_coin.png"), this.leftPos + -1, this.topPos + 159, 0, 0, 16, 16, 16, 16);

		RenderSystem.disableBlend();
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
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.market.label_market"), 0, -11, -1, false);
		guiGraphics.drawString(this.font,

				TextMarketBarProcedure.execute(world, entity), 61, 109, -12829636, false);
		guiGraphics.drawString(this.font,

				TextMarketTaxProcedure.execute(world, entity), 6, 126, -6710887, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.market.label_wallet_value"), 15, 162, -1, false);
	}

	@Override
	public void init() {
		super.init();
		button_buy = Button.builder(Component.translatable("gui.reign_mod.market.button_buy"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new MarketButtonMessage(0, x, y, z));
				MarketButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 24, this.topPos + 139, 90, 20).build();
		guistate.put("button:button_buy", button_buy);
		this.addRenderableWidget(button_buy);
		button_x4 = Button.builder(Component.translatable("gui.reign_mod.market.button_x4"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new MarketButtonMessage(1, x, y, z));
				MarketButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 6, this.topPos + 139, 18, 20).build();
		guistate.put("button:button_x4", button_x4);
		this.addRenderableWidget(button_x4);
	}
}
