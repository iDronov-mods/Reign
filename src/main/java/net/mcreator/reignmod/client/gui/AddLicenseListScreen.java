package net.mcreator.reignmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.reignmod.world.inventory.AddLicenseListMenu;
import net.mcreator.reignmod.network.AddLicenseListButtonMessage;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class AddLicenseListScreen extends AbstractContainerScreen<AddLicenseListMenu> {
	private final static HashMap<String, Object> guistate = AddLicenseListMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_fisher;
	Button button_enchanter;
	Button button_alchemist;
	Button button_soldier;
	Button button_hunter_button;

	public AddLicenseListScreen(AddLicenseListMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 150;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("reign_mod:textures/screens/add_license_list.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 6 && mouseX < leftPos + 30 && mouseY > topPos + -14 && mouseY < topPos + 10)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.add_license_list.tooltip_vybieritie_osnovnoi_stil_ighry"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/crown.png"), this.leftPos + 11, this.topPos + -11, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/enchanter_logo.png"), this.leftPos + 20, this.topPos + 52, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/alchemist_logo.png"), this.leftPos + 20, this.topPos + 79, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/fisher_logo.png"), this.leftPos + 20, this.topPos + 25, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/soldier_logo.png"), this.leftPos + 20, this.topPos + 106, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/hunter_logo.png"), this.leftPos + 20, this.topPos + 133, 0, 0, 16, 16, 16, 16);

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
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.add_license_list.label_dop_litsienzii"), 41, 4, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_fisher = Button.builder(Component.translatable("gui.reign_mod.add_license_list.button_fisher"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new AddLicenseListButtonMessage(0, x, y, z));
				AddLicenseListButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 56, this.topPos + 23, 77, 20).build();
		guistate.put("button:button_fisher", button_fisher);
		this.addRenderableWidget(button_fisher);
		button_enchanter = Button.builder(Component.translatable("gui.reign_mod.add_license_list.button_enchanter"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new AddLicenseListButtonMessage(1, x, y, z));
				AddLicenseListButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 56, this.topPos + 50, 77, 20).build();
		guistate.put("button:button_enchanter", button_enchanter);
		this.addRenderableWidget(button_enchanter);
		button_alchemist = Button.builder(Component.translatable("gui.reign_mod.add_license_list.button_alchemist"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new AddLicenseListButtonMessage(2, x, y, z));
				AddLicenseListButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + 56, this.topPos + 77, 77, 20).build();
		guistate.put("button:button_alchemist", button_alchemist);
		this.addRenderableWidget(button_alchemist);
		button_soldier = Button.builder(Component.translatable("gui.reign_mod.add_license_list.button_soldier"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new AddLicenseListButtonMessage(3, x, y, z));
				AddLicenseListButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}).bounds(this.leftPos + 56, this.topPos + 104, 77, 20).build();
		guistate.put("button:button_soldier", button_soldier);
		this.addRenderableWidget(button_soldier);
		button_hunter_button = Button.builder(Component.translatable("gui.reign_mod.add_license_list.button_hunter_button"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new AddLicenseListButtonMessage(4, x, y, z));
				AddLicenseListButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		}).bounds(this.leftPos + 56, this.topPos + 131, 77, 20).build();
		guistate.put("button:button_hunter_button", button_hunter_button);
		this.addRenderableWidget(button_hunter_button);
	}
}
