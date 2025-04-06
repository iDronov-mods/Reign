package net.mcreator.reignmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.reignmod.world.inventory.HouseIncubatorUIMenu;
import net.mcreator.reignmod.procedures.IncubatorCoalHelpProcedure;
import net.mcreator.reignmod.procedures.HouseHPValueProcedure;
import net.mcreator.reignmod.procedures.HouseHP9Procedure;
import net.mcreator.reignmod.procedures.HouseHP8Procedure;
import net.mcreator.reignmod.procedures.HouseHP7Procedure;
import net.mcreator.reignmod.procedures.HouseHP6Procedure;
import net.mcreator.reignmod.procedures.HouseHP5Procedure;
import net.mcreator.reignmod.procedures.HouseHP4Procedure;
import net.mcreator.reignmod.procedures.HouseHP3Procedure;
import net.mcreator.reignmod.procedures.HouseHP2Procedure;
import net.mcreator.reignmod.procedures.HouseHP1Procedure;
import net.mcreator.reignmod.procedures.HouseHP10Procedure;

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

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 269 && mouseX < leftPos + 293 && mouseY > topPos + -18 && mouseY < topPos + 6)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.house_incubator_ui.tooltip_incubator_label_help"), mouseX, mouseY);
		if (mouseX > leftPos + 70 && mouseX < leftPos + 86 && mouseY > topPos + 62 && mouseY < topPos + 78)
			guiGraphics.renderTooltip(font, Component.literal(IncubatorCoalHelpProcedure.execute()), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/incubatorui.png"), this.leftPos + 0, this.topPos + -17, 0, 0, 300, 200, 300, 200);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/crown.png"), this.leftPos + 273, this.topPos + -13, 0, 0, 16, 16, 16, 16);

		if (HouseHP1Procedure.execute(world, x, y, z, entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 45, this.topPos + 6, 0, 0, 20, 4, 20, 4);
		}
		if (HouseHP2Procedure.execute(world, x, y, z, entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 66, this.topPos + 6, 0, 0, 20, 4, 20, 4);
		}
		if (HouseHP3Procedure.execute(world, x, y, z, entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 87, this.topPos + 6, 0, 0, 20, 4, 20, 4);
		}
		if (HouseHP4Procedure.execute(world, x, y, z, entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 108, this.topPos + 6, 0, 0, 20, 4, 20, 4);
		}
		if (HouseHP5Procedure.execute(world, x, y, z, entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 129, this.topPos + 6, 0, 0, 20, 4, 20, 4);
		}
		if (HouseHP6Procedure.execute(world, x, y, z, entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 150, this.topPos + 6, 0, 0, 20, 4, 20, 4);
		}
		if (HouseHP7Procedure.execute(world, x, y, z, entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 171, this.topPos + 6, 0, 0, 20, 4, 20, 4);
		}
		if (HouseHP8Procedure.execute(world, x, y, z, entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 192, this.topPos + 6, 0, 0, 20, 4, 20, 4);
		}
		if (HouseHP9Procedure.execute(world, x, y, z, entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 213, this.topPos + 6, 0, 0, 20, 4, 20, 4);
		}
		if (HouseHP10Procedure.execute(world, x, y, z, entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 234, this.topPos + 6, 0, 0, 20, 4, 20, 4);
		}
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
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.house_incubator_ui.label_incubator"), 41, -7, -1, false);
		guiGraphics.drawString(this.font,

				HouseHPValueProcedure.execute(world, x, y, z, entity), 260, 3, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.house_incubator_ui.label_players"), 71, 31, -13421773, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.house_incubator_ui.label_domains"), 128, 31, -13421773, false);
	}

	@Override
	public void init() {
		super.init();
	}
}
