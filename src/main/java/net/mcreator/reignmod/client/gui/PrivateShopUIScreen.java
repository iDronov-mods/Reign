package net.mcreator.reignmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.reignmod.world.inventory.PrivateShopUIMenu;
import net.mcreator.reignmod.procedures.PrivateShopProfitProcedure;
import net.mcreator.reignmod.procedures.PrivateShopGetOwnerProcedure;
import net.mcreator.reignmod.procedures.PrivateShopCountGoodsProcedure;
import net.mcreator.reignmod.network.PrivateShopUIButtonMessage;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class PrivateShopUIScreen extends AbstractContainerScreen<PrivateShopUIMenu> {
	private final static HashMap<String, Object> guistate = PrivateShopUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	Button button_gcf;
	Button button_clear;

	public PrivateShopUIScreen(PrivateShopUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 96 && mouseX < leftPos + 120 && mouseY > topPos + -21 && mouseY < topPos + 3)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.private_shop_ui.tooltip_private_shop_help"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/crown.png"), this.leftPos + 100, this.topPos + -12, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/privateshopownerui.png"), this.leftPos + -22, this.topPos + -17, 0, 0, 220, 200, 220, 200);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/copper_coin.png"), this.leftPos + 9, this.topPos + 67, 0, 0, 16, 16, 16, 16);

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
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.private_shop_ui.label_private_shop"), 0, -8, -1, false);
		guiGraphics.drawString(this.font,

				PrivateShopGetOwnerProcedure.execute(world, x, y, z), 123, -8, -1, false);
		guiGraphics.drawString(this.font,

				PrivateShopCountGoodsProcedure.execute(world, x, y, z), 139, 31, -1, false);
		guiGraphics.drawString(this.font,

				PrivateShopProfitProcedure.execute(world, x, y, z), 25, 71, -1, false);
	}

	@Override
	public void init() {
		super.init();
		button_gcf = Button.builder(Component.translatable("gui.reign_mod.private_shop_ui.button_gcf"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new PrivateShopUIButtonMessage(0, x, y, z, textstate));
				PrivateShopUIButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 7, this.topPos + 160, 54, 20).build();
		guistate.put("button:button_gcf", button_gcf);
		this.addRenderableWidget(button_gcf);
		button_clear = Button.builder(Component.translatable("gui.reign_mod.private_shop_ui.button_clear"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new PrivateShopUIButtonMessage(1, x, y, z, textstate));
				PrivateShopUIButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 116, this.topPos + 160, 53, 20).build();
		guistate.put("button:button_clear", button_clear);
		this.addRenderableWidget(button_clear);
	}
}
