package net.mcreator.reignmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.reignmod.world.inventory.FarmerWindowMenu;
import net.mcreator.reignmod.procedures.Mlvl5Procedure;
import net.mcreator.reignmod.procedures.Mlvl4Procedure;
import net.mcreator.reignmod.procedures.Mlvl2Procedure;
import net.mcreator.reignmod.procedures.Mlvl1Procedure;
import net.mcreator.reignmod.procedures.MainLvlUp5Procedure;
import net.mcreator.reignmod.procedures.MainLvlUp4Procedure;
import net.mcreator.reignmod.procedures.MainLvlUp3Procedure;
import net.mcreator.reignmod.procedures.MainLvlUp2Procedure;
import net.mcreator.reignmod.procedures.MainLvlUp1Procedure;
import net.mcreator.reignmod.procedures.IsRLvlProcedure;
import net.mcreator.reignmod.procedures.IsRLvlFor4Procedure;
import net.mcreator.reignmod.procedures.IsNotRLvlProcedure;
import net.mcreator.reignmod.procedures.IsNotRLvlFor4Procedure;
import net.mcreator.reignmod.procedures.IsNotHaveMainLicensesProcedure;
import net.mcreator.reignmod.procedures.IsHaveMainLicensesProcedure;
import net.mcreator.reignmod.network.FarmerWindowButtonMessage;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class FarmerWindowScreen extends AbstractContainerScreen<FarmerWindowMenu> {
	private final static HashMap<String, Object> guistate = FarmerWindowMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	Button button_select;
	Button button_empty;
	Button button_refuse;
	ImageButton imagebutton_upgrade_lvl;
	ImageButton imagebutton_upgrade_lvl1;
	ImageButton imagebutton_upgrade_lvl2;
	ImageButton imagebutton_upgrade_lvl3;
	ImageButton imagebutton_upgrade_lvl4;
	ImageButton imagebutton_upgrade_lvl5;

	public FarmerWindowScreen(FarmerWindowMenu container, Inventory inventory, Component text) {
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
		if (mouseX > leftPos + 62 && mouseX < leftPos + 70 && mouseY > topPos + 36 && mouseY < topPos + 44)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.farmer_window.tooltip_lvl1_description"), mouseX, mouseY);
		if (mouseX > leftPos + 86 && mouseX < leftPos + 94 && mouseY > topPos + 36 && mouseY < topPos + 44)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.farmer_window.tooltip_lvl2_description"), mouseX, mouseY);
		if (mouseX > leftPos + 110 && mouseX < leftPos + 118 && mouseY > topPos + 29 && mouseY < topPos + 37)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.farmer_window.tooltip_lvl3_description_l"), mouseX, mouseY);
		if (mouseX > leftPos + 110 && mouseX < leftPos + 118 && mouseY > topPos + 43 && mouseY < topPos + 51)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.farmer_window.tooltip_lvl3_description_r"), mouseX, mouseY);
		if (mouseX > leftPos + 134 && mouseX < leftPos + 142 && mouseY > topPos + 36 && mouseY < topPos + 44)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.farmer_window.tooltip_lvl4_description"), mouseX, mouseY);
		if (mouseX > leftPos + 158 && mouseX < leftPos + 166 && mouseY > topPos + 36 && mouseY < topPos + 44)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.farmer_window.tooltip_lvl5_description"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/license_window_background.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 222, 166, 222, 166);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/main_licenses_expbar.png"), this.leftPos + 63, this.topPos + 24, 0, 0, 103, 32, 103, 32);

		if (Mlvl1Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_first.png"), this.leftPos + 63, this.topPos + 37, 0, 0, 6, 6, 6, 6);
		}
		if (Mlvl2Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_next.png"), this.leftPos + 69, this.topPos + 37, 0, 0, 24, 6, 24, 6);
		}
		if (IsRLvlProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_next_r.png"), this.leftPos + 89, this.topPos + 43, 0, 0, 28, 7, 28, 7);
		}
		if (IsNotRLvlProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_next_l.png"), this.leftPos + 89, this.topPos + 30, 0, 0, 28, 7, 28, 7);
		}
		if (IsNotRLvlFor4Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_next_corner_up.png"), this.leftPos + 117, this.topPos + 32, 0, 0, 22, 5, 22, 5);
		}
		if (IsRLvlFor4Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_next_corner_down.png"), this.leftPos + 117, this.topPos + 43, 0, 0, 22, 5, 22, 5);
		}
		if (Mlvl4Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_first.png"), this.leftPos + 135, this.topPos + 37, 0, 0, 6, 6, 6, 6);
		}
		if (Mlvl5Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_next.png"), this.leftPos + 141, this.topPos + 37, 0, 0, 24, 6, 24, 6);
		}

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/big_farmer.png"), this.leftPos + 9, this.topPos + 9, 0, 0, 48, 48, 48, 48);

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
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.farmer_window.label_main_license"), 1, -12, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.farmer_window.label_l"), 117, 20, -6710887, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.farmer_window.label_r"), 117, 51, -6710887, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.farmer_window.label_string_1"), 15, 69, -3355444, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.farmer_window.label_string_2"), 15, 87, -3355444, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.farmer_window.label_string_3"), 15, 98, -3355444, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.farmer_window.label_string_4"), 15, 109, -3355444, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.farmer_window.label_string_5"), 15, 120, -3355444, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.farmer_window.label_string_6"), 15, 138, -10066330, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.farmer_window.label_main_label"), 63, 9, -1, false);
	}

	@Override
	public void init() {
		super.init();
		button_select = Button.builder(Component.translatable("gui.reign_mod.farmer_window.button_select"), e -> {
			if (IsNotHaveMainLicensesProcedure.execute(entity)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new FarmerWindowButtonMessage(0, x, y, z, textstate));
				FarmerWindowButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 149, this.topPos + 152, 56, 20).build(builder -> new Button(builder) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (IsNotHaveMainLicensesProcedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		});
		guistate.put("button:button_select", button_select);
		this.addRenderableWidget(button_select);
		button_empty = Button.builder(Component.translatable("gui.reign_mod.farmer_window.button_empty"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new FarmerWindowButtonMessage(1, x, y, z, textstate));
				FarmerWindowButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		}).bounds(this.leftPos + -21, this.topPos + 1, 19, 20).build();
		guistate.put("button:button_empty", button_empty);
		this.addRenderableWidget(button_empty);
		button_refuse = Button.builder(Component.translatable("gui.reign_mod.farmer_window.button_refuse"), e -> {
			if (IsHaveMainLicensesProcedure.execute(entity)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new FarmerWindowButtonMessage(2, x, y, z, textstate));
				FarmerWindowButtonMessage.handleButtonAction(entity, 2, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 18, this.topPos + 152, 67, 20).build(builder -> new Button(builder) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (IsHaveMainLicensesProcedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		});
		guistate.put("button:button_refuse", button_refuse);
		this.addRenderableWidget(button_refuse);
		imagebutton_upgrade_lvl = new ImageButton(this.leftPos + 58, this.topPos + 32, 16, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_upgrade_lvl.png"), 16, 32, e -> {
			if (MainLvlUp1Procedure.execute(entity)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new FarmerWindowButtonMessage(3, x, y, z, textstate));
				FarmerWindowButtonMessage.handleButtonAction(entity, 3, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (MainLvlUp1Procedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_upgrade_lvl", imagebutton_upgrade_lvl);
		this.addRenderableWidget(imagebutton_upgrade_lvl);
		imagebutton_upgrade_lvl1 = new ImageButton(this.leftPos + 82, this.topPos + 32, 16, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_upgrade_lvl1.png"), 16, 32, e -> {
			if (MainLvlUp2Procedure.execute(entity)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new FarmerWindowButtonMessage(4, x, y, z, textstate));
				FarmerWindowButtonMessage.handleButtonAction(entity, 4, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (MainLvlUp2Procedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_upgrade_lvl1", imagebutton_upgrade_lvl1);
		this.addRenderableWidget(imagebutton_upgrade_lvl1);
		imagebutton_upgrade_lvl2 = new ImageButton(this.leftPos + 106, this.topPos + 25, 16, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_upgrade_lvl2.png"), 16, 32, e -> {
			if (MainLvlUp3Procedure.execute(entity)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new FarmerWindowButtonMessage(5, x, y, z, textstate));
				FarmerWindowButtonMessage.handleButtonAction(entity, 5, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (MainLvlUp3Procedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_upgrade_lvl2", imagebutton_upgrade_lvl2);
		this.addRenderableWidget(imagebutton_upgrade_lvl2);
		imagebutton_upgrade_lvl3 = new ImageButton(this.leftPos + 106, this.topPos + 39, 16, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_upgrade_lvl3.png"), 16, 32, e -> {
			if (MainLvlUp3Procedure.execute(entity)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new FarmerWindowButtonMessage(6, x, y, z, textstate));
				FarmerWindowButtonMessage.handleButtonAction(entity, 6, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (MainLvlUp3Procedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_upgrade_lvl3", imagebutton_upgrade_lvl3);
		this.addRenderableWidget(imagebutton_upgrade_lvl3);
		imagebutton_upgrade_lvl4 = new ImageButton(this.leftPos + 130, this.topPos + 32, 16, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_upgrade_lvl4.png"), 16, 32, e -> {
			if (MainLvlUp4Procedure.execute(entity)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new FarmerWindowButtonMessage(7, x, y, z, textstate));
				FarmerWindowButtonMessage.handleButtonAction(entity, 7, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (MainLvlUp4Procedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_upgrade_lvl4", imagebutton_upgrade_lvl4);
		this.addRenderableWidget(imagebutton_upgrade_lvl4);
		imagebutton_upgrade_lvl5 = new ImageButton(this.leftPos + 154, this.topPos + 32, 16, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_upgrade_lvl5.png"), 16, 32, e -> {
			if (MainLvlUp5Procedure.execute(entity)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new FarmerWindowButtonMessage(8, x, y, z, textstate));
				FarmerWindowButtonMessage.handleButtonAction(entity, 8, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (MainLvlUp5Procedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_upgrade_lvl5", imagebutton_upgrade_lvl5);
		this.addRenderableWidget(imagebutton_upgrade_lvl5);
	}
}
