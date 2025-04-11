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
import net.mcreator.reignmod.procedures.WalletOutsideCostProcedure;
import net.mcreator.reignmod.procedures.TextMarketTaxProcedure;
import net.mcreator.reignmod.procedures.ReturnMarketCopperProcedure;
import net.mcreator.reignmod.procedures.ReturnMarketBar33Procedure;
import net.mcreator.reignmod.procedures.ReturnMarketBar23Procedure;
import net.mcreator.reignmod.procedures.ReturnMarketBar13Procedure;
import net.mcreator.reignmod.procedures.CopperCostTextProcedure;
import net.mcreator.reignmod.network.MarketButtonMessage;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class MarketScreen extends AbstractContainerScreen<MarketMenu> {
	private final static HashMap<String, Object> guistate = MarketMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	Button button_buy;
	Button button_x4;
	Button button_x16;

	public MarketScreen(MarketMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 323;
		this.imageHeight = 166;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 287 && mouseX < leftPos + 311 && mouseY > topPos + -22 && mouseY < topPos + 2)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.market.tooltip_buy_goods_for_money"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/market_ui.png"), this.leftPos + -1, this.topPos + 0, 0, 0, 324, 166, 324, 166);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/crown.png"), this.leftPos + 292, this.topPos + -12, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/copper_coin.png"), this.leftPos + 35, this.topPos + -14, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/silver_coin.png"), this.leftPos + 46, this.topPos + -14, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/gold_coin.png"), this.leftPos + 57, this.topPos + -14, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/count_bar0-3.png"), this.leftPos + 106, this.topPos + 90, 0, 0, 31, 6, 31, 6);

		if (ReturnMarketBar13Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/count_bar1-3.png"), this.leftPos + 106, this.topPos + 90, 0, 0, 31, 6, 31, 6);
		}
		if (ReturnMarketBar23Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/count_bar2-3.png"), this.leftPos + 106, this.topPos + 90, 0, 0, 31, 6, 31, 6);
		}
		if (ReturnMarketBar33Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/count_bar3-3.png"), this.leftPos + 106, this.topPos + 90, 0, 0, 31, 6, 31, 6);
		}

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/wallet.png"), this.leftPos + 0, this.topPos + 166, 0, 0, 16, 16, 16, 16);

		RenderSystem.disableBlend();
	}

	public static HashMap<String, String> getTextboxValues() {
		return textstate;
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
		ReignModMod.PACKET_HANDLER.sendToServer(new MarketMenu.MarketOtherMessage(0, x, y, z, textstate));
		MarketMenu.MarketOtherMessage.handleOtherAction(entity, 0, x, y, z, textstate);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.market.label_market"), 0, -10, -1, false);
		guiGraphics.drawString(this.font,

				TextMarketTaxProcedure.execute(world, entity), 15, 121, -3355444, false);
		guiGraphics.drawString(this.font,

				WalletOutsideCostProcedure.execute(entity), 15, 171, -1, false);
		guiGraphics.drawString(this.font,

				CopperCostTextProcedure.execute(entity), 15, 90, -3355444, false);
		guiGraphics.drawString(this.font,

				ReturnMarketCopperProcedure.execute(world), 74, -11, -3355444, false);
	}

	@Override
	public void init() {
		super.init();
		button_buy = Button.builder(Component.translatable("gui.reign_mod.market.button_buy"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new MarketButtonMessage(0, x, y, z, textstate));
				MarketButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 54, this.topPos + 140, 88, 20).build();
		guistate.put("button:button_buy", button_buy);
		this.addRenderableWidget(button_buy);
		button_x4 = Button.builder(Component.translatable("gui.reign_mod.market.button_x4"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new MarketButtonMessage(1, x, y, z, textstate));
				MarketButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 32, this.topPos + 140, 21, 20).build();
		guistate.put("button:button_x4", button_x4);
		this.addRenderableWidget(button_x4);
		button_x16 = Button.builder(Component.translatable("gui.reign_mod.market.button_x16"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new MarketButtonMessage(2, x, y, z, textstate));
				MarketButtonMessage.handleButtonAction(entity, 2, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 10, this.topPos + 140, 21, 20).build();
		guistate.put("button:button_x16", button_x16);
		this.addRenderableWidget(button_x16);
	}
}
