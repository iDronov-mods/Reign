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

import net.mcreator.reignmod.world.inventory.WoodcutterwindowMenu;
import net.mcreator.reignmod.procedures.ReturnwoodcutterProcedure;
import net.mcreator.reignmod.procedures.Mlvl5Procedure;
import net.mcreator.reignmod.procedures.Mlvl4Procedure;
import net.mcreator.reignmod.procedures.Mlvl3Procedure;
import net.mcreator.reignmod.procedures.Mlvl2Procedure;
import net.mcreator.reignmod.procedures.Mlvl1Procedure;
import net.mcreator.reignmod.procedures.MainButtonUpProcedure;
import net.mcreator.reignmod.procedures.IsNotHaveMainLicensesProcedure;
import net.mcreator.reignmod.network.WoodcutterwindowButtonMessage;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class WoodcutterwindowScreen extends AbstractContainerScreen<WoodcutterwindowMenu> {
	private final static HashMap<String, Object> guistate = WoodcutterwindowMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	Button button_refuse;
	Button button_select;
	Button button_empty;
	ImageButton imagebutton_lvl_up_button;

	public WoodcutterwindowScreen(WoodcutterwindowMenu container, Inventory inventory, Component text) {
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
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/license_window_background.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 222, 166, 222, 166);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/main_licenses_expbar.png"), this.leftPos + 85, this.topPos + 25, 0, 0, 103, 32, 103, 32);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/big_woodcutter_logo.png"), this.leftPos + 9, this.topPos + 9, 0, 0, 48, 48, 48, 48);

		if (Mlvl1Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_first.png"), this.leftPos + 85, this.topPos + 38, 0, 0, 6, 6, 6, 6);
		}
		if (Mlvl2Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_next.png"), this.leftPos + 91, this.topPos + 38, 0, 0, 24, 6, 24, 6);
		}
		if (Mlvl3Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_next_r.png"), this.leftPos + 111, this.topPos + 44, 0, 0, 28, 7, 28, 7);
		}
		if (Mlvl3Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_next_l.png"), this.leftPos + 111, this.topPos + 31, 0, 0, 28, 7, 28, 7);
		}
		if (Mlvl4Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_next_corner_up.png"), this.leftPos + 139, this.topPos + 33, 0, 0, 22, 5, 22, 5);
		}
		if (Mlvl4Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_next_corner_down.png"), this.leftPos + 139, this.topPos + 44, 0, 0, 22, 5, 22, 5);
		}
		if (Mlvl4Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_first.png"), this.leftPos + 157, this.topPos + 38, 0, 0, 6, 6, 6, 6);
		}
		if (Mlvl5Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_next.png"), this.leftPos + 163, this.topPos + 38, 0, 0, 24, 6, 24, 6);
		}
		if (Mlvl1Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_first.png"), this.leftPos + 15, this.topPos + 70, 0, 0, 6, 6, 6, 6);
		}
		if (Mlvl2Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_first.png"), this.leftPos + 15, this.topPos + 88, 0, 0, 6, 6, 6, 6);
		}
		if (Mlvl3Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_first.png"), this.leftPos + 15, this.topPos + 106, 0, 0, 6, 6, 6, 6);
		}
		if (Mlvl4Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_first.png"), this.leftPos + 15, this.topPos + 124, 0, 0, 6, 6, 6, 6);
		}
		if (Mlvl5Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_first.png"), this.leftPos + 15, this.topPos + 142, 0, 0, 6, 6, 6, 6);
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
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.woodcutterwindow.label_woodcutter"), 63, 9, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.woodcutterwindow.label_main_license"), 1, -12, -1, false);
	}

	@Override
	public void init() {
		super.init();
		button_refuse = Button.builder(Component.translatable("gui.reign_mod.woodcutterwindow.button_refuse"), e -> {
			if (ReturnwoodcutterProcedure.execute(entity)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new WoodcutterwindowButtonMessage(0, x, y, z, textstate));
				WoodcutterwindowButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 15, this.topPos + 152, 56, 20).build(builder -> new Button(builder) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (ReturnwoodcutterProcedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		});
		guistate.put("button:button_refuse", button_refuse);
		this.addRenderableWidget(button_refuse);
		button_select = Button.builder(Component.translatable("gui.reign_mod.woodcutterwindow.button_select"), e -> {
			if (IsNotHaveMainLicensesProcedure.execute(entity)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new WoodcutterwindowButtonMessage(1, x, y, z, textstate));
				WoodcutterwindowButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 150, this.topPos + 152, 56, 20).build(builder -> new Button(builder) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (IsNotHaveMainLicensesProcedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		});
		guistate.put("button:button_select", button_select);
		this.addRenderableWidget(button_select);
		button_empty = Button.builder(Component.translatable("gui.reign_mod.woodcutterwindow.button_empty"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new WoodcutterwindowButtonMessage(2, x, y, z, textstate));
				WoodcutterwindowButtonMessage.handleButtonAction(entity, 2, x, y, z, textstate);
			}
		}).bounds(this.leftPos + -21, this.topPos + 1, 19, 20).build();
		guistate.put("button:button_empty", button_empty);
		this.addRenderableWidget(button_empty);
		imagebutton_lvl_up_button = new ImageButton(this.leftPos + 64, this.topPos + 33, 16, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_lvl_up_button.png"), 16, 32, e -> {
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (MainButtonUpProcedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_lvl_up_button", imagebutton_lvl_up_button);
		this.addRenderableWidget(imagebutton_lvl_up_button);
	}
}
