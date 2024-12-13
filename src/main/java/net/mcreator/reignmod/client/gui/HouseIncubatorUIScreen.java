package net.mcreator.reignmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.reignmod.world.inventory.HouseIncubatorUIMenu;
import net.mcreator.reignmod.procedures.IncubatorGetFuelPerHourProcedure;
import net.mcreator.reignmod.procedures.IncubatorGetFoodPerHourProcedure;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class HouseIncubatorUIScreen extends AbstractContainerScreen<HouseIncubatorUIMenu> {
	private final static HashMap<String, Object> guistate = HouseIncubatorUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();

	public HouseIncubatorUIScreen(HouseIncubatorUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 300;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("reign_mod:textures/screens/house_incubator_ui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 269 && mouseX < leftPos + 293 && mouseY > topPos + -18 && mouseY < topPos + 6)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.house_incubator_ui.tooltip_incubator_label_help"), mouseX, mouseY);
		if (mouseX > leftPos + 15 && mouseX < leftPos + 51 && mouseY > topPos + 139 && mouseY < topPos + 160)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.house_incubator_ui.tooltip_incubator_fuel_help"), mouseX, mouseY);
		if (mouseX > leftPos + 249 && mouseX < leftPos + 285 && mouseY > topPos + 140 && mouseY < topPos + 160)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.house_incubator_ui.tooltip_incubator_food_help"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/incubatorui.png"), this.leftPos + 0, this.topPos + -17, 0, 0, 300, 200, 300, 200);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/crown.png"), this.leftPos + 273, this.topPos + -13, 0, 0, 16, 16, 16, 16);

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
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.house_incubator_ui.label_incubator"), 125, -23, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.house_incubator_ui.label_fuel"), 15, 139, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.house_incubator_ui.label_food"), 249, 139, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.house_incubator_ui.label_meat"), 159, 67, -1, false);
		guiGraphics.drawString(this.font,

				IncubatorGetFuelPerHourProcedure.execute(world, x, y, z, entity), 15, 149, -6710887, false);
		guiGraphics.drawString(this.font,

				IncubatorGetFoodPerHourProcedure.execute(world, x, y, z, entity), 249, 150, -6710887, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.house_incubator_ui.label_wool"), 69, 67, -1, false);
	}

	@Override
	public void init() {
		super.init();
	}
}
