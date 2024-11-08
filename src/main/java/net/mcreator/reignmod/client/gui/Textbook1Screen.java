package net.mcreator.reignmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.reignmod.world.inventory.Textbook1Menu;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class Textbook1Screen extends AbstractContainerScreen<Textbook1Menu> {
	private final static HashMap<String, Object> guistate = Textbook1Menu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_next;
	Button button_back;

	public Textbook1Screen(Textbook1Menu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 210;
		this.imageHeight = 166;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/bookui.png"), this.leftPos + 9, this.topPos + 16, 0, 0, 192, 128, 192, 128);

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
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.textbook_1.label_textbook"), 9, 5, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.textbook_1.label_reignmod"), 158, 5, -13312, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.textbook_1.label_1_litsienzii"), 30, 20, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_next = Button.builder(Component.translatable("gui.reign_mod.textbook_1.button_next"), e -> {
		}).bounds(this.leftPos + 169, this.topPos + 135, 39, 20).build();
		guistate.put("button:button_next", button_next);
		this.addRenderableWidget(button_next);
		button_back = Button.builder(Component.translatable("gui.reign_mod.textbook_1.button_back"), e -> {
		}).bounds(this.leftPos + 129, this.topPos + 135, 38, 20).build();
		guistate.put("button:button_back", button_back);
		this.addRenderableWidget(button_back);
	}
}
