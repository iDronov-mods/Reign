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

import net.mcreator.reignmod.world.inventory.CoffersTaxUIMenu;
import net.mcreator.reignmod.procedures.ToolsTaxTextProcedure;
import net.mcreator.reignmod.procedures.PrivateTaxTextProcedure;
import net.mcreator.reignmod.procedures.OtherTaxTextProcedure;
import net.mcreator.reignmod.procedures.OresTaxTextProcedure;
import net.mcreator.reignmod.procedures.LogsTaxTextProcedure;
import net.mcreator.reignmod.procedures.IsCoinageBlockProcedure;
import net.mcreator.reignmod.procedures.FoodTaxTextProcedure;
import net.mcreator.reignmod.procedures.ExpTaxTextProcedure;
import net.mcreator.reignmod.procedures.CoalTaxTextProcedure;
import net.mcreator.reignmod.procedures.ArmorTaxTextProcedure;
import net.mcreator.reignmod.network.CoffersTaxUIButtonMessage;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class CoffersTaxUIScreen extends AbstractContainerScreen<CoffersTaxUIMenu> {
	private final static HashMap<String, Object> guistate = CoffersTaxUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	public static Checkbox coinage;
	ImageButton imagebutton_tab_button;
	ImageButton imagebutton_tax_up_button;
	ImageButton imagebutton_tax_down_button;
	ImageButton imagebutton_tax_up_button1;
	ImageButton imagebutton_tax_down_button1;
	ImageButton imagebutton_tax_up_button2;
	ImageButton imagebutton_tax_down_button2;
	ImageButton imagebutton_tax_up_button3;
	ImageButton imagebutton_tax_down_button3;
	ImageButton imagebutton_tax_up_button4;
	ImageButton imagebutton_tax_down_button4;
	ImageButton imagebutton_tax_up_button5;
	ImageButton imagebutton_tax_down_button5;
	ImageButton imagebutton_tax_up_button6;
	ImageButton imagebutton_tax_down_button6;
	ImageButton imagebutton_tax_up_button7;
	ImageButton imagebutton_tax_down_button7;
	ImageButton imagebutton_tax_up_button8;
	ImageButton imagebutton_tax_down_button8;

	public CoffersTaxUIScreen(CoffersTaxUIMenu container, Inventory inventory, Component text) {
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
		if (mouseX > leftPos + 144 && mouseX < leftPos + 160 && mouseY > topPos + -11 && mouseY < topPos + 1)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.coffers_tax_ui.tooltip_taxation_help"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/coffers_tax_ui.png"), this.leftPos + -52, this.topPos + -57, 0, 0, 280, 280, 280, 280);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/crown.png"), this.leftPos + 144, this.topPos + -13, 0, 0, 16, 16, 16, 16);

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
	public void containerTick() {
		super.containerTick();
		textstate.put("checkboxin:coinage", coinage.selected() ? "true" : "false");
		ReignModMod.PACKET_HANDLER.sendToServer(new CoffersTaxUIMenu.CoffersTaxUIOtherMessage(0, x, y, z, textstate));
		CoffersTaxUIMenu.CoffersTaxUIOtherMessage.handleOtherAction(entity, 0, x, y, z, textstate);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.coffers_tax_ui.label_taxation"), 0, -10, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.coffers_tax_ui.label_logs_tax_label"), 14, 13, -16777216, false);
		guiGraphics.drawString(this.font,

				LogsTaxTextProcedure.execute(world), 99, 13, -10092493, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.coffers_tax_ui.label_coal_tax_label"), 14, 27, -16777216, false);
		guiGraphics.drawString(this.font,

				CoalTaxTextProcedure.execute(world), 99, 27, -10092493, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.coffers_tax_ui.label_ores_tax_label"), 14, 41, -16777216, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.coffers_tax_ui.label_tools_tax_label"), 14, 55, -16777216, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.coffers_tax_ui.label_armor_tax_label"), 14, 69, -16777216, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.coffers_tax_ui.label_food_tax_label"), 14, 83, -16777216, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.coffers_tax_ui.label_exp_tax_label"), 14, 97, -16777216, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.coffers_tax_ui.label_private_tax_label"), 14, 125, -16777216, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.coffers_tax_ui.label_other_tax_label"), 14, 111, -16777216, false);
		guiGraphics.drawString(this.font,

				OresTaxTextProcedure.execute(world), 99, 41, -10092493, false);
		guiGraphics.drawString(this.font,

				ToolsTaxTextProcedure.execute(world), 99, 55, -10092493, false);
		guiGraphics.drawString(this.font,

				ArmorTaxTextProcedure.execute(world), 99, 69, -10092493, false);
		guiGraphics.drawString(this.font,

				FoodTaxTextProcedure.execute(world), 99, 83, -10092493, false);
		guiGraphics.drawString(this.font,

				ExpTaxTextProcedure.execute(world), 99, 97, -10092493, false);
		guiGraphics.drawString(this.font,

				OtherTaxTextProcedure.execute(world), 99, 111, -10092493, false);
		guiGraphics.drawString(this.font,

				PrivateTaxTextProcedure.execute(world), 99, 125, -10092493, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.coffers_tax_ui.label_coinage_block_label"), 33, 141, -16777216, false);
	}

	@Override
	public void init() {
		super.init();
		imagebutton_tab_button = new ImageButton(this.leftPos + -23, this.topPos + 5, 23, 22, 0, 0, 22, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_tab_button.png"), 23, 44, e -> {
			if (true) {
				textstate.put("checkboxin:coinage", coinage.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new CoffersTaxUIButtonMessage(0, x, y, z, textstate));
				CoffersTaxUIButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_tab_button", imagebutton_tab_button);
		this.addRenderableWidget(imagebutton_tab_button);
		imagebutton_tax_up_button = new ImageButton(this.leftPos + 140, this.topPos + 13, 9, 8, 0, 0, 8, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_tax_up_button.png"), 9, 16, e -> {
			if (true) {
				textstate.put("checkboxin:coinage", coinage.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new CoffersTaxUIButtonMessage(1, x, y, z, textstate));
				CoffersTaxUIButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_tax_up_button", imagebutton_tax_up_button);
		this.addRenderableWidget(imagebutton_tax_up_button);
		imagebutton_tax_down_button = new ImageButton(this.leftPos + 154, this.topPos + 13, 9, 8, 0, 0, 8, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_tax_down_button.png"), 9, 16, e -> {
			if (true) {
				textstate.put("checkboxin:coinage", coinage.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new CoffersTaxUIButtonMessage(2, x, y, z, textstate));
				CoffersTaxUIButtonMessage.handleButtonAction(entity, 2, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_tax_down_button", imagebutton_tax_down_button);
		this.addRenderableWidget(imagebutton_tax_down_button);
		imagebutton_tax_up_button1 = new ImageButton(this.leftPos + 140, this.topPos + 27, 9, 8, 0, 0, 8, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_tax_up_button1.png"), 9, 16, e -> {
			if (true) {
				textstate.put("checkboxin:coinage", coinage.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new CoffersTaxUIButtonMessage(3, x, y, z, textstate));
				CoffersTaxUIButtonMessage.handleButtonAction(entity, 3, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_tax_up_button1", imagebutton_tax_up_button1);
		this.addRenderableWidget(imagebutton_tax_up_button1);
		imagebutton_tax_down_button1 = new ImageButton(this.leftPos + 154, this.topPos + 27, 9, 8, 0, 0, 8, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_tax_down_button1.png"), 9, 16, e -> {
			if (true) {
				textstate.put("checkboxin:coinage", coinage.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new CoffersTaxUIButtonMessage(4, x, y, z, textstate));
				CoffersTaxUIButtonMessage.handleButtonAction(entity, 4, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_tax_down_button1", imagebutton_tax_down_button1);
		this.addRenderableWidget(imagebutton_tax_down_button1);
		imagebutton_tax_up_button2 = new ImageButton(this.leftPos + 140, this.topPos + 41, 9, 8, 0, 0, 8, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_tax_up_button2.png"), 9, 16, e -> {
			if (true) {
				textstate.put("checkboxin:coinage", coinage.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new CoffersTaxUIButtonMessage(5, x, y, z, textstate));
				CoffersTaxUIButtonMessage.handleButtonAction(entity, 5, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_tax_up_button2", imagebutton_tax_up_button2);
		this.addRenderableWidget(imagebutton_tax_up_button2);
		imagebutton_tax_down_button2 = new ImageButton(this.leftPos + 154, this.topPos + 41, 9, 8, 0, 0, 8, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_tax_down_button2.png"), 9, 16, e -> {
			if (true) {
				textstate.put("checkboxin:coinage", coinage.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new CoffersTaxUIButtonMessage(6, x, y, z, textstate));
				CoffersTaxUIButtonMessage.handleButtonAction(entity, 6, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_tax_down_button2", imagebutton_tax_down_button2);
		this.addRenderableWidget(imagebutton_tax_down_button2);
		imagebutton_tax_up_button3 = new ImageButton(this.leftPos + 140, this.topPos + 55, 9, 8, 0, 0, 8, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_tax_up_button3.png"), 9, 16, e -> {
			if (true) {
				textstate.put("checkboxin:coinage", coinage.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new CoffersTaxUIButtonMessage(7, x, y, z, textstate));
				CoffersTaxUIButtonMessage.handleButtonAction(entity, 7, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_tax_up_button3", imagebutton_tax_up_button3);
		this.addRenderableWidget(imagebutton_tax_up_button3);
		imagebutton_tax_down_button3 = new ImageButton(this.leftPos + 154, this.topPos + 55, 9, 8, 0, 0, 8, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_tax_down_button3.png"), 9, 16, e -> {
			if (true) {
				textstate.put("checkboxin:coinage", coinage.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new CoffersTaxUIButtonMessage(8, x, y, z, textstate));
				CoffersTaxUIButtonMessage.handleButtonAction(entity, 8, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_tax_down_button3", imagebutton_tax_down_button3);
		this.addRenderableWidget(imagebutton_tax_down_button3);
		imagebutton_tax_up_button4 = new ImageButton(this.leftPos + 140, this.topPos + 69, 9, 8, 0, 0, 8, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_tax_up_button4.png"), 9, 16, e -> {
			if (true) {
				textstate.put("checkboxin:coinage", coinage.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new CoffersTaxUIButtonMessage(9, x, y, z, textstate));
				CoffersTaxUIButtonMessage.handleButtonAction(entity, 9, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_tax_up_button4", imagebutton_tax_up_button4);
		this.addRenderableWidget(imagebutton_tax_up_button4);
		imagebutton_tax_down_button4 = new ImageButton(this.leftPos + 154, this.topPos + 69, 9, 8, 0, 0, 8, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_tax_down_button4.png"), 9, 16, e -> {
			if (true) {
				textstate.put("checkboxin:coinage", coinage.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new CoffersTaxUIButtonMessage(10, x, y, z, textstate));
				CoffersTaxUIButtonMessage.handleButtonAction(entity, 10, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_tax_down_button4", imagebutton_tax_down_button4);
		this.addRenderableWidget(imagebutton_tax_down_button4);
		imagebutton_tax_up_button5 = new ImageButton(this.leftPos + 140, this.topPos + 83, 9, 8, 0, 0, 8, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_tax_up_button5.png"), 9, 16, e -> {
			if (true) {
				textstate.put("checkboxin:coinage", coinage.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new CoffersTaxUIButtonMessage(11, x, y, z, textstate));
				CoffersTaxUIButtonMessage.handleButtonAction(entity, 11, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_tax_up_button5", imagebutton_tax_up_button5);
		this.addRenderableWidget(imagebutton_tax_up_button5);
		imagebutton_tax_down_button5 = new ImageButton(this.leftPos + 154, this.topPos + 83, 9, 8, 0, 0, 8, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_tax_down_button5.png"), 9, 16, e -> {
			if (true) {
				textstate.put("checkboxin:coinage", coinage.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new CoffersTaxUIButtonMessage(12, x, y, z, textstate));
				CoffersTaxUIButtonMessage.handleButtonAction(entity, 12, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_tax_down_button5", imagebutton_tax_down_button5);
		this.addRenderableWidget(imagebutton_tax_down_button5);
		imagebutton_tax_up_button6 = new ImageButton(this.leftPos + 140, this.topPos + 97, 9, 8, 0, 0, 8, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_tax_up_button6.png"), 9, 16, e -> {
			if (true) {
				textstate.put("checkboxin:coinage", coinage.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new CoffersTaxUIButtonMessage(13, x, y, z, textstate));
				CoffersTaxUIButtonMessage.handleButtonAction(entity, 13, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_tax_up_button6", imagebutton_tax_up_button6);
		this.addRenderableWidget(imagebutton_tax_up_button6);
		imagebutton_tax_down_button6 = new ImageButton(this.leftPos + 153, this.topPos + 97, 9, 8, 0, 0, 8, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_tax_down_button6.png"), 9, 16, e -> {
			if (true) {
				textstate.put("checkboxin:coinage", coinage.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new CoffersTaxUIButtonMessage(14, x, y, z, textstate));
				CoffersTaxUIButtonMessage.handleButtonAction(entity, 14, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_tax_down_button6", imagebutton_tax_down_button6);
		this.addRenderableWidget(imagebutton_tax_down_button6);
		imagebutton_tax_up_button7 = new ImageButton(this.leftPos + 140, this.topPos + 111, 9, 8, 0, 0, 8, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_tax_up_button7.png"), 9, 16, e -> {
			if (true) {
				textstate.put("checkboxin:coinage", coinage.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new CoffersTaxUIButtonMessage(15, x, y, z, textstate));
				CoffersTaxUIButtonMessage.handleButtonAction(entity, 15, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_tax_up_button7", imagebutton_tax_up_button7);
		this.addRenderableWidget(imagebutton_tax_up_button7);
		imagebutton_tax_down_button7 = new ImageButton(this.leftPos + 153, this.topPos + 111, 9, 8, 0, 0, 8, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_tax_down_button7.png"), 9, 16, e -> {
			if (true) {
				textstate.put("checkboxin:coinage", coinage.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new CoffersTaxUIButtonMessage(16, x, y, z, textstate));
				CoffersTaxUIButtonMessage.handleButtonAction(entity, 16, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_tax_down_button7", imagebutton_tax_down_button7);
		this.addRenderableWidget(imagebutton_tax_down_button7);
		imagebutton_tax_up_button8 = new ImageButton(this.leftPos + 140, this.topPos + 125, 9, 8, 0, 0, 8, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_tax_up_button8.png"), 9, 16, e -> {
			if (true) {
				textstate.put("checkboxin:coinage", coinage.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new CoffersTaxUIButtonMessage(17, x, y, z, textstate));
				CoffersTaxUIButtonMessage.handleButtonAction(entity, 17, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_tax_up_button8", imagebutton_tax_up_button8);
		this.addRenderableWidget(imagebutton_tax_up_button8);
		imagebutton_tax_down_button8 = new ImageButton(this.leftPos + 153, this.topPos + 125, 9, 8, 0, 0, 8, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_tax_down_button8.png"), 9, 16, e -> {
			if (true) {
				textstate.put("checkboxin:coinage", coinage.selected() ? "true" : "false");
				ReignModMod.PACKET_HANDLER.sendToServer(new CoffersTaxUIButtonMessage(18, x, y, z, textstate));
				CoffersTaxUIButtonMessage.handleButtonAction(entity, 18, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_tax_down_button8", imagebutton_tax_down_button8);
		this.addRenderableWidget(imagebutton_tax_down_button8);
		coinage = new Checkbox(this.leftPos + 11, this.topPos + 139, 20, 20, Component.translatable("gui.reign_mod.coffers_tax_ui.coinage"),

				IsCoinageBlockProcedure.execute(world));
		guistate.put("checkbox:coinage", coinage);
		this.addRenderableWidget(coinage);
	}
}
