package net.mcreator.reignmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.reignmod.world.inventory.PrivateShopBuyerUIMenu;
import net.mcreator.reignmod.procedures.WalletOutsideCostProcedure;
import net.mcreator.reignmod.procedures.PrivateShopNoLicenseProcedure;
import net.mcreator.reignmod.procedures.PrivateShopGetOwnerProcedure;
import net.mcreator.reignmod.procedures.PrivateShopCountGoodsProcedure;
import net.mcreator.reignmod.network.PrivateShopBuyerUIButtonMessage;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class PrivateShopBuyerUIScreen extends AbstractContainerScreen<PrivateShopBuyerUIMenu> {
	private final static HashMap<String, Object> guistate = PrivateShopBuyerUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	Button button_buy;
	Button button_x4;

	public PrivateShopBuyerUIScreen(PrivateShopBuyerUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("reign_mod:textures/screens/private_shop_buyer_ui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 92 && mouseX < leftPos + 116 && mouseY > topPos + -17 && mouseY < topPos + 7)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.private_shop_buyer_ui.tooltip_private_shop_help"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/crown.png"), this.leftPos + 96, this.topPos + -12, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/arrow.png"), this.leftPos + 72, this.topPos + 29, 0, 0, 32, 16, 32, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/wallet.png"), this.leftPos + 0, this.topPos + 161, 0, 0, 16, 16, 16, 16);

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
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.private_shop_buyer_ui.label_private_shop"), 0, -8, -1, false);
		guiGraphics.drawString(this.font,

				PrivateShopGetOwnerProcedure.execute(world, x, y, z), 122, -9, -1, false);
		guiGraphics.drawString(this.font,

				WalletOutsideCostProcedure.execute(entity), 15, 167, -1, false);
		guiGraphics.drawString(this.font,

				PrivateShopCountGoodsProcedure.execute(world, x, y, z), 43, 50, -12829636, false);
		if (PrivateShopNoLicenseProcedure.execute(entity))
			guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.private_shop_buyer_ui.label_not_licensed"), 0, -22, -3407821, false);
	}

	@Override
	public void init() {
		super.init();
		button_buy = Button.builder(Component.translatable("gui.reign_mod.private_shop_buyer_ui.button_buy"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new PrivateShopBuyerUIButtonMessage(0, x, y, z, textstate));
				PrivateShopBuyerUIButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 61, this.topPos + 59, 72, 20).build();
		guistate.put("button:button_buy", button_buy);
		this.addRenderableWidget(button_buy);
		button_x4 = Button.builder(Component.translatable("gui.reign_mod.private_shop_buyer_ui.button_x4"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new PrivateShopBuyerUIButtonMessage(1, x, y, z, textstate));
				PrivateShopBuyerUIButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 43, this.topPos + 59, 18, 20).build();
		guistate.put("button:button_x4", button_x4);
		this.addRenderableWidget(button_x4);
	}
}
