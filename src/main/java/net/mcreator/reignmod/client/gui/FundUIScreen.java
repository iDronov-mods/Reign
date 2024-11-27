package net.mcreator.reignmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.reignmod.world.inventory.FundUIMenu;
import net.mcreator.reignmod.procedures.ReturnEra9Procedure;
import net.mcreator.reignmod.procedures.ReturnEra8Procedure;
import net.mcreator.reignmod.procedures.ReturnEra7Procedure;
import net.mcreator.reignmod.procedures.ReturnEra6Procedure;
import net.mcreator.reignmod.procedures.ReturnEra5Procedure;
import net.mcreator.reignmod.procedures.ReturnEra4Procedure;
import net.mcreator.reignmod.procedures.ReturnEra3Procedure;
import net.mcreator.reignmod.procedures.ReturnEra2Procedure;
import net.mcreator.reignmod.procedures.ReturnEra1Procedure;
import net.mcreator.reignmod.procedures.ReturnEra10Procedure;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class FundUIScreen extends AbstractContainerScreen<FundUIMenu> {
	private final static HashMap<String, Object> guistate = FundUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();

	public FundUIScreen(FundUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 222;
		this.imageHeight = 166;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 165 && mouseX < leftPos + 184 && mouseY > topPos + 67 && mouseY < topPos + 82)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.fund_ui.tooltip_donate_coins_to_advance_to_a_new"), mouseX, mouseY);
		if (mouseX > leftPos + 24 && mouseX < leftPos + 30 && mouseY > topPos + -7 && mouseY < topPos + -1)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.fund_ui.tooltip_market_expansion"), mouseX, mouseY);
		if (mouseX > leftPos + 216 && mouseX < leftPos + 222 && mouseY > topPos + -7 && mouseY < topPos + -1)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.fund_ui.tooltip_opening_the_end"), mouseX, mouseY);
		if (mouseX > leftPos + 0 && mouseX < leftPos + 6 && mouseY > topPos + -7 && mouseY < topPos + -1)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.fund_ui.tooltip_iron_processing"), mouseX, mouseY);
		if (mouseX > leftPos + 48 && mouseX < leftPos + 54 && mouseY > topPos + -7 && mouseY < topPos + -1)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.fund_ui.tooltip_additional_license"), mouseX, mouseY);
		if (mouseX > leftPos + 72 && mouseX < leftPos + 78 && mouseY > topPos + -7 && mouseY < topPos + -1)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.fund_ui.tooltip_processing_of_precious_ores"), mouseX, mouseY);
		if (mouseX > leftPos + 96 && mouseX < leftPos + 102 && mouseY > topPos + -7 && mouseY < topPos + -1)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.fund_ui.tooltip_redstone_kesearch"), mouseX, mouseY);
		if (mouseX > leftPos + 120 && mouseX < leftPos + 126 && mouseY > topPos + -7 && mouseY < topPos + -1)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.fund_ui.tooltip_1_kingdom"), mouseX, mouseY);
		if (mouseX > leftPos + 144 && mouseX < leftPos + 150 && mouseY > topPos + -7 && mouseY < topPos + -1)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.fund_ui.tooltip_openning_the_nether"), mouseX, mouseY);
		if (mouseX > leftPos + 192 && mouseX < leftPos + 198 && mouseY > topPos + -7 && mouseY < topPos + -1)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.fund_ui.tooltip_3_kingdoms"), mouseX, mouseY);
		if (mouseX > leftPos + 168 && mouseX < leftPos + 174 && mouseY > topPos + -7 && mouseY < topPos + -1)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.fund_ui.tooltip_opening_sticky_pistons"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/found.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 222, 166, 222, 166);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/eras_bar.png"), this.leftPos + -5, this.topPos + -28, 0, 0, 232, 32, 232, 32);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/crown.png"), this.leftPos + 167, this.topPos + 70, 0, 0, 16, 16, 16, 16);

		if (ReturnEra4Procedure.execute(world)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/era_point_next.png"), this.leftPos + 54, this.topPos + -7, 0, 0, 24, 6, 24, 6);
		}
		if (ReturnEra5Procedure.execute(world)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/era_point_next.png"), this.leftPos + 78, this.topPos + -7, 0, 0, 24, 6, 24, 6);
		}
		if (ReturnEra6Procedure.execute(world)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/era_point_next.png"), this.leftPos + 102, this.topPos + -7, 0, 0, 24, 6, 24, 6);
		}
		if (ReturnEra7Procedure.execute(world)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/era_point_next.png"), this.leftPos + 126, this.topPos + -7, 0, 0, 24, 6, 24, 6);
		}
		if (ReturnEra8Procedure.execute(world)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/era_point_next.png"), this.leftPos + 150, this.topPos + -7, 0, 0, 24, 6, 24, 6);
		}
		if (ReturnEra9Procedure.execute(world)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/era_point_next.png"), this.leftPos + 174, this.topPos + -7, 0, 0, 24, 6, 24, 6);
		}
		if (ReturnEra10Procedure.execute(world)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/era_point_next.png"), this.leftPos + 198, this.topPos + -7, 0, 0, 24, 6, 24, 6);
		}
		if (ReturnEra1Procedure.execute(world)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/era_point_first.png"), this.leftPos + 0, this.topPos + -7, 0, 0, 6, 6, 6, 6);
		}
		if (ReturnEra2Procedure.execute(world)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/era_point_next.png"), this.leftPos + 6, this.topPos + -7, 0, 0, 24, 6, 24, 6);
		}
		if (ReturnEra3Procedure.execute(world)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/era_point_next.png"), this.leftPos + 30, this.topPos + -7, 0, 0, 24, 6, 24, 6);
		}
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
		ReignModMod.PACKET_HANDLER.sendToServer(new FundUIMenu.FundUIOtherMessage(0, x, y, z, textstate));
		FundUIMenu.FundUIOtherMessage.handleOtherAction(entity, 0, x, y, z, textstate);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.fund_ui.label_forward_to_a_new_time"), 30, 70, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
	}
}
