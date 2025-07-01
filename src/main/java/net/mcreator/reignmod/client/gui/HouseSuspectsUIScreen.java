package net.mcreator.reignmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.reignmod.world.inventory.HouseSuspectsUIMenu;
import net.mcreator.reignmod.procedures.ReturnSuspect7ValueProcedure;
import net.mcreator.reignmod.procedures.ReturnSuspect7Procedure;
import net.mcreator.reignmod.procedures.ReturnSuspect6ValueProcedure;
import net.mcreator.reignmod.procedures.ReturnSuspect6Procedure;
import net.mcreator.reignmod.procedures.ReturnSuspect5ValueProcedure;
import net.mcreator.reignmod.procedures.ReturnSuspect5Procedure;
import net.mcreator.reignmod.procedures.ReturnSuspect4ValueProcedure;
import net.mcreator.reignmod.procedures.ReturnSuspect4Procedure;
import net.mcreator.reignmod.procedures.ReturnSuspect3ValueProcedure;
import net.mcreator.reignmod.procedures.ReturnSuspect3Procedure;
import net.mcreator.reignmod.procedures.ReturnSuspect2ValueProcedure;
import net.mcreator.reignmod.procedures.ReturnSuspect2Procedure;
import net.mcreator.reignmod.procedures.ReturnSuspect1ValueProcedure;
import net.mcreator.reignmod.procedures.ReturnSuspect1Procedure;
import net.mcreator.reignmod.procedures.IsIndirectVassalsBeTrustedProcedure;
import net.mcreator.reignmod.procedures.IncubatorReturnDomainsProcedure;
import net.mcreator.reignmod.procedures.IncubatorReturnDomainPlayersProcedure;
import net.mcreator.reignmod.procedures.IncubatorReturnAllPlayersProcedure;
import net.mcreator.reignmod.procedures.IncubatorHouseLevelProcedure;
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
import net.mcreator.reignmod.network.HouseSuspectsUIButtonMessage;
import net.mcreator.reignmod.init.ReignModModScreens.WidgetScreen;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class HouseSuspectsUIScreen extends AbstractContainerScreen<HouseSuspectsUIMenu> implements WidgetScreen {
	private final static HashMap<String, Object> guistate = HouseSuspectsUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	public static Checkbox trusting_indirect_vassals;
	ImageButton imagebutton_tab_button;
	ImageButton imagebutton_remove_suspect_button;
	ImageButton imagebutton_remove_suspect_button1;
	ImageButton imagebutton_remove_suspect_button2;
	ImageButton imagebutton_remove_suspect_button3;
	ImageButton imagebutton_remove_suspect_button4;
	ImageButton imagebutton_remove_suspect_button5;
	ImageButton imagebutton_remove_suspect_button6;
	ImageButton imagebutton_jail_button;
	ImageButton imagebutton_jail_button1;
	ImageButton imagebutton_jail_button2;
	ImageButton imagebutton_jail_button3;
	ImageButton imagebutton_jail_button4;
	ImageButton imagebutton_jail_button5;
	ImageButton imagebutton_jail_button6;

	public HouseSuspectsUIScreen(HouseSuspectsUIMenu container, Inventory inventory, Component text) {
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
		if (mouseX > leftPos + 177 && mouseX < leftPos + 193 && mouseY > topPos + 13 && mouseY < topPos + 24)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.house_suspects_ui.tooltip_incubator_label_help"), mouseX, mouseY);
		if (mouseX > leftPos + 63 && mouseX < leftPos + 71 && mouseY > topPos + 30 && mouseY < topPos + 42)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.house_suspects_ui.tooltip_domain_players_help"), mouseX, mouseY);
		if (mouseX > leftPos + 119 && mouseX < leftPos + 127 && mouseY > topPos + 30 && mouseY < topPos + 41)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.house_suspects_ui.tooltip_domains_help"), mouseX, mouseY);
		if (mouseX > leftPos + 177 && mouseX < leftPos + 194 && mouseY > topPos + 30 && mouseY < topPos + 41)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.house_suspects_ui.tooltip_all_players_help"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/ui_incubator_suspects.png"), this.leftPos + 0, this.topPos + -17, 0, 0, 300, 200, 300, 200);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/crown.png"), this.leftPos + 177, this.topPos + 11, 0, 0, 16, 16, 16, 16);

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

	public HashMap<String, Object> getWidgets() {
		return guistate;
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
		textstate.put("checkboxin:trusting_indirect_vassals", trusting_indirect_vassals.selected() ? "true" : "false");
		ReignModMod.PACKET_HANDLER.sendToServer(new HouseSuspectsUIMenu.HouseSuspectsUIOtherMessage(0, x, y, z, textstate));
		HouseSuspectsUIMenu.HouseSuspectsUIOtherMessage.handleOtherAction(entity, 0, x, y, z, textstate);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.house_suspects_ui.label_incubator"), 41, -7, -1, false);
		guiGraphics.drawString(this.font,

				HouseHPValueProcedure.execute(world, x, y, z, entity), 260, 3, -1, false);
		guiGraphics.drawString(this.font,

				IncubatorReturnDomainPlayersProcedure.execute(world, x, y, z), 73, 32, -13421773, false);
		guiGraphics.drawString(this.font,

				IncubatorReturnDomainsProcedure.execute(world, x, y, z), 130, 32, -13421773, false);
		guiGraphics.drawString(this.font,

				IncubatorReturnAllPlayersProcedure.execute(world, x, y, z), 198, 32, -12829636, false);
		guiGraphics.drawString(this.font,

				IncubatorHouseLevelProcedure.execute(world, x, y, z), 178, -7, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.house_suspects_ui.label_suspects_label"), 81, 55, -16777216, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect1Procedure.execute(world, x, y, z), 81, 70, -12829636, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect1ValueProcedure.execute(world, x, y, z), 163, 70, -16777216, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect2Procedure.execute(world, x, y, z), 81, 82, -12829636, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect2ValueProcedure.execute(world, x, y, z), 163, 82, -16777216, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect3Procedure.execute(world, x, y, z), 81, 94, -12829636, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect3ValueProcedure.execute(world, x, y, z), 163, 94, -16777216, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect4Procedure.execute(world, x, y, z), 81, 106, -12829636, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect5Procedure.execute(world, x, y, z), 81, 118, -12829636, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect6Procedure.execute(world, x, y, z), 81, 130, -12829636, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect7Procedure.execute(world, x, y, z), 81, 142, -12829636, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect4ValueProcedure.execute(world, x, y, z), 163, 106, -16777216, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect5ValueProcedure.execute(world, x, y, z), 163, 118, -16777216, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect6ValueProcedure.execute(world, x, y, z), 163, 130, -16777216, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect7ValueProcedure.execute(world, x, y, z), 163, 142, -16777216, false);
	}

	@Override
	public void init() {
		super.init();
		imagebutton_tab_button = new ImageButton(this.leftPos + 33, this.topPos + 88, 23, 22, 0, 0, 22, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_tab_button.png"), 23, 44, e -> {
			if (true) {
				textstate.put("checkboxin:trusting_indirect_vassals", trusting_indirect_vassals.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new HouseSuspectsUIButtonMessage(0, x, y, z, textstate));
				HouseSuspectsUIButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_tab_button", imagebutton_tab_button);
		this.addRenderableWidget(imagebutton_tab_button);
		imagebutton_remove_suspect_button = new ImageButton(this.leftPos + 209, this.topPos + 72, 9, 7, 0, 0, 7, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_remove_suspect_button.png"), 9, 14, e -> {
			if (true) {
				textstate.put("checkboxin:trusting_indirect_vassals", trusting_indirect_vassals.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new HouseSuspectsUIButtonMessage(1, x, y, z, textstate));
				HouseSuspectsUIButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_remove_suspect_button", imagebutton_remove_suspect_button);
		this.addRenderableWidget(imagebutton_remove_suspect_button);
		imagebutton_remove_suspect_button1 = new ImageButton(this.leftPos + 209, this.topPos + 84, 9, 7, 0, 0, 7, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_remove_suspect_button1.png"), 9, 14, e -> {
			if (true) {
				textstate.put("checkboxin:trusting_indirect_vassals", trusting_indirect_vassals.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new HouseSuspectsUIButtonMessage(2, x, y, z, textstate));
				HouseSuspectsUIButtonMessage.handleButtonAction(entity, 2, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_remove_suspect_button1", imagebutton_remove_suspect_button1);
		this.addRenderableWidget(imagebutton_remove_suspect_button1);
		imagebutton_remove_suspect_button2 = new ImageButton(this.leftPos + 209, this.topPos + 96, 9, 7, 0, 0, 7, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_remove_suspect_button2.png"), 9, 14, e -> {
			if (true) {
				textstate.put("checkboxin:trusting_indirect_vassals", trusting_indirect_vassals.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new HouseSuspectsUIButtonMessage(3, x, y, z, textstate));
				HouseSuspectsUIButtonMessage.handleButtonAction(entity, 3, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_remove_suspect_button2", imagebutton_remove_suspect_button2);
		this.addRenderableWidget(imagebutton_remove_suspect_button2);
		imagebutton_remove_suspect_button3 = new ImageButton(this.leftPos + 209, this.topPos + 108, 9, 7, 0, 0, 7, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_remove_suspect_button3.png"), 9, 14, e -> {
			if (true) {
				textstate.put("checkboxin:trusting_indirect_vassals", trusting_indirect_vassals.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new HouseSuspectsUIButtonMessage(4, x, y, z, textstate));
				HouseSuspectsUIButtonMessage.handleButtonAction(entity, 4, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_remove_suspect_button3", imagebutton_remove_suspect_button3);
		this.addRenderableWidget(imagebutton_remove_suspect_button3);
		imagebutton_remove_suspect_button4 = new ImageButton(this.leftPos + 209, this.topPos + 120, 9, 7, 0, 0, 7, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_remove_suspect_button4.png"), 9, 14, e -> {
			if (true) {
				textstate.put("checkboxin:trusting_indirect_vassals", trusting_indirect_vassals.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new HouseSuspectsUIButtonMessage(5, x, y, z, textstate));
				HouseSuspectsUIButtonMessage.handleButtonAction(entity, 5, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_remove_suspect_button4", imagebutton_remove_suspect_button4);
		this.addRenderableWidget(imagebutton_remove_suspect_button4);
		imagebutton_remove_suspect_button5 = new ImageButton(this.leftPos + 209, this.topPos + 132, 9, 7, 0, 0, 7, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_remove_suspect_button5.png"), 9, 14, e -> {
			if (true) {
				textstate.put("checkboxin:trusting_indirect_vassals", trusting_indirect_vassals.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new HouseSuspectsUIButtonMessage(6, x, y, z, textstate));
				HouseSuspectsUIButtonMessage.handleButtonAction(entity, 6, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_remove_suspect_button5", imagebutton_remove_suspect_button5);
		this.addRenderableWidget(imagebutton_remove_suspect_button5);
		imagebutton_remove_suspect_button6 = new ImageButton(this.leftPos + 209, this.topPos + 144, 9, 7, 0, 0, 7, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_remove_suspect_button6.png"), 9, 14, e -> {
			if (true) {
				textstate.put("checkboxin:trusting_indirect_vassals", trusting_indirect_vassals.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new HouseSuspectsUIButtonMessage(7, x, y, z, textstate));
				HouseSuspectsUIButtonMessage.handleButtonAction(entity, 7, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_remove_suspect_button6", imagebutton_remove_suspect_button6);
		this.addRenderableWidget(imagebutton_remove_suspect_button6);
		imagebutton_jail_button = new ImageButton(this.leftPos + 197, this.topPos + 72, 9, 7, 0, 0, 7, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_jail_button.png"), 9, 14, e -> {
			if (true) {
				textstate.put("checkboxin:trusting_indirect_vassals", trusting_indirect_vassals.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new HouseSuspectsUIButtonMessage(8, x, y, z, textstate));
				HouseSuspectsUIButtonMessage.handleButtonAction(entity, 8, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_jail_button", imagebutton_jail_button);
		this.addRenderableWidget(imagebutton_jail_button);
		imagebutton_jail_button1 = new ImageButton(this.leftPos + 197, this.topPos + 84, 9, 7, 0, 0, 7, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_jail_button1.png"), 9, 14, e -> {
			if (true) {
				textstate.put("checkboxin:trusting_indirect_vassals", trusting_indirect_vassals.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new HouseSuspectsUIButtonMessage(9, x, y, z, textstate));
				HouseSuspectsUIButtonMessage.handleButtonAction(entity, 9, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_jail_button1", imagebutton_jail_button1);
		this.addRenderableWidget(imagebutton_jail_button1);
		imagebutton_jail_button2 = new ImageButton(this.leftPos + 197, this.topPos + 96, 9, 7, 0, 0, 7, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_jail_button2.png"), 9, 14, e -> {
			if (true) {
				textstate.put("checkboxin:trusting_indirect_vassals", trusting_indirect_vassals.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new HouseSuspectsUIButtonMessage(10, x, y, z, textstate));
				HouseSuspectsUIButtonMessage.handleButtonAction(entity, 10, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_jail_button2", imagebutton_jail_button2);
		this.addRenderableWidget(imagebutton_jail_button2);
		imagebutton_jail_button3 = new ImageButton(this.leftPos + 197, this.topPos + 108, 9, 7, 0, 0, 7, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_jail_button3.png"), 9, 14, e -> {
			if (true) {
				textstate.put("checkboxin:trusting_indirect_vassals", trusting_indirect_vassals.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new HouseSuspectsUIButtonMessage(11, x, y, z, textstate));
				HouseSuspectsUIButtonMessage.handleButtonAction(entity, 11, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_jail_button3", imagebutton_jail_button3);
		this.addRenderableWidget(imagebutton_jail_button3);
		imagebutton_jail_button4 = new ImageButton(this.leftPos + 197, this.topPos + 120, 9, 7, 0, 0, 7, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_jail_button4.png"), 9, 14, e -> {
			if (true) {
				textstate.put("checkboxin:trusting_indirect_vassals", trusting_indirect_vassals.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new HouseSuspectsUIButtonMessage(12, x, y, z, textstate));
				HouseSuspectsUIButtonMessage.handleButtonAction(entity, 12, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_jail_button4", imagebutton_jail_button4);
		this.addRenderableWidget(imagebutton_jail_button4);
		imagebutton_jail_button5 = new ImageButton(this.leftPos + 197, this.topPos + 132, 9, 7, 0, 0, 7, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_jail_button5.png"), 9, 14, e -> {
			if (true) {
				textstate.put("checkboxin:trusting_indirect_vassals", trusting_indirect_vassals.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new HouseSuspectsUIButtonMessage(13, x, y, z, textstate));
				HouseSuspectsUIButtonMessage.handleButtonAction(entity, 13, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_jail_button5", imagebutton_jail_button5);
		this.addRenderableWidget(imagebutton_jail_button5);
		imagebutton_jail_button6 = new ImageButton(this.leftPos + 197, this.topPos + 144, 9, 7, 0, 0, 7, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_jail_button6.png"), 9, 14, e -> {
			if (true) {
				textstate.put("checkboxin:trusting_indirect_vassals", trusting_indirect_vassals.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new HouseSuspectsUIButtonMessage(14, x, y, z, textstate));
				HouseSuspectsUIButtonMessage.handleButtonAction(entity, 14, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_jail_button6", imagebutton_jail_button6);
		this.addRenderableWidget(imagebutton_jail_button6);
		trusting_indirect_vassals = new Checkbox(this.leftPos + 57, this.topPos + 168, 20, 20, Component.translatable("gui.reign_mod.house_suspects_ui.trusting_indirect_vassals"),

				IsIndirectVassalsBeTrustedProcedure.execute(world, x, y, z, entity));
		guistate.put("checkbox:trusting_indirect_vassals", trusting_indirect_vassals);
		this.addRenderableWidget(trusting_indirect_vassals);
	}
}
