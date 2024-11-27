package net.mcreator.reignmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import net.mcreator.reignmod.world.inventory.RoyaleSettingsMenu;
import net.mcreator.reignmod.network.RoyaleSettingsButtonMessage;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class RoyaleSettingsScreen extends AbstractContainerScreen<RoyaleSettingsMenu> {
	private final static HashMap<String, Object> guistate = RoyaleSettingsMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	public static EditBox logs_textfield;
	public static EditBox tools_textfield;
	public static EditBox armor_textfield;
	public static EditBox food_textfield;
	public static EditBox coal_textfield;
	public static EditBox ores_textfield;
	public static EditBox other_textfield;
	ImageButton imagebutton_but_up;
	ImageButton imagebutton_but_down;
	ImageButton imagebutton_but_up1;
	ImageButton imagebutton_but_down1;
	ImageButton imagebutton_but_up2;
	ImageButton imagebutton_but_down2;
	ImageButton imagebutton_but_up3;
	ImageButton imagebutton_but_down3;
	ImageButton imagebutton_but_up4;
	ImageButton imagebutton_but_down4;
	ImageButton imagebutton_but_up5;
	ImageButton imagebutton_but_down5;
	ImageButton imagebutton_but_up6;
	ImageButton imagebutton_but_down6;

	public RoyaleSettingsScreen(RoyaleSettingsMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 238;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("reign_mod:textures/screens/royale_settings.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		logs_textfield.render(guiGraphics, mouseX, mouseY, partialTicks);
		tools_textfield.render(guiGraphics, mouseX, mouseY, partialTicks);
		armor_textfield.render(guiGraphics, mouseX, mouseY, partialTicks);
		food_textfield.render(guiGraphics, mouseX, mouseY, partialTicks);
		coal_textfield.render(guiGraphics, mouseX, mouseY, partialTicks);
		ores_textfield.render(guiGraphics, mouseX, mouseY, partialTicks);
		other_textfield.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 216 && mouseX < leftPos + 240 && mouseY > topPos + -16 && mouseY < topPos + 8)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.royale_settings.tooltip_set_the_of_tax_that_will_go_to"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/copper_coin.png"), this.leftPos + 181, this.topPos + 16, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/silver_coin.png"), this.leftPos + 181, this.topPos + 76, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/silver_coin.png"), this.leftPos + 181, this.topPos + 96, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/copper_coin.png"), this.leftPos + 181, this.topPos + 57, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/copper_coin.png"), this.leftPos + 181, this.topPos + 36, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/silver_coin.png"), this.leftPos + 181, this.topPos + 116, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/gold_coin.png"), this.leftPos + 181, this.topPos + 136, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/crown.png"), this.leftPos + 220, this.topPos + -12, 0, 0, 16, 16, 16, 16);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (logs_textfield.isFocused())
			return logs_textfield.keyPressed(key, b, c);
		if (tools_textfield.isFocused())
			return tools_textfield.keyPressed(key, b, c);
		if (armor_textfield.isFocused())
			return armor_textfield.keyPressed(key, b, c);
		if (food_textfield.isFocused())
			return food_textfield.keyPressed(key, b, c);
		if (coal_textfield.isFocused())
			return coal_textfield.keyPressed(key, b, c);
		if (ores_textfield.isFocused())
			return ores_textfield.keyPressed(key, b, c);
		if (other_textfield.isFocused())
			return other_textfield.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		logs_textfield.tick();
		tools_textfield.tick();
		armor_textfield.tick();
		food_textfield.tick();
		coal_textfield.tick();
		ores_textfield.tick();
		other_textfield.tick();
		textstate.put("textin:logs_textfield", logs_textfield.getValue());
		textstate.put("textin:tools_textfield", tools_textfield.getValue());
		textstate.put("textin:armor_textfield", armor_textfield.getValue());
		textstate.put("textin:food_textfield", food_textfield.getValue());
		textstate.put("textin:coal_textfield", coal_textfield.getValue());
		textstate.put("textin:ores_textfield", ores_textfield.getValue());
		textstate.put("textin:other_textfield", other_textfield.getValue());
		ReignModMod.PACKET_HANDLER.sendToServer(new RoyaleSettingsMenu.RoyaleSettingsOtherMessage(0, x, y, z, textstate));
		RoyaleSettingsMenu.RoyaleSettingsOtherMessage.handleOtherAction(entity, 0, x, y, z, textstate);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String logs_textfieldValue = logs_textfield.getValue();
		String tools_textfieldValue = tools_textfield.getValue();
		String armor_textfieldValue = armor_textfield.getValue();
		String food_textfieldValue = food_textfield.getValue();
		String coal_textfieldValue = coal_textfield.getValue();
		String ores_textfieldValue = ores_textfield.getValue();
		String other_textfieldValue = other_textfield.getValue();
		super.resize(minecraft, width, height);
		logs_textfield.setValue(logs_textfieldValue);
		tools_textfield.setValue(tools_textfieldValue);
		armor_textfield.setValue(armor_textfieldValue);
		food_textfield.setValue(food_textfieldValue);
		coal_textfield.setValue(coal_textfieldValue);
		ores_textfield.setValue(ores_textfieldValue);
		other_textfield.setValue(other_textfieldValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.royale_settings.label_royal_decrees"), 1, -12, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.royale_settings.label_price"), 112, 4, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.royale_settings.label_logs"), 8, 19, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.royale_settings.label_tools"), 8, 79, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.royale_settings.label_armor"), 9, 99, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.royale_settings.label_food"), 8, 59, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.royale_settings.label_coal"), 8, 39, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.royale_settings.label_ores"), 8, 119, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.royale_settings.label_other"), 8, 139, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		logs_textfield = new EditBox(this.font, this.leftPos + 61, this.topPos + 15, 118, 18, Component.translatable("gui.reign_mod.royale_settings.logs_textfield"));
		logs_textfield.setMaxLength(32767);
		guistate.put("text:logs_textfield", logs_textfield);
		this.addWidget(this.logs_textfield);
		tools_textfield = new EditBox(this.font, this.leftPos + 61, this.topPos + 75, 118, 18, Component.translatable("gui.reign_mod.royale_settings.tools_textfield"));
		tools_textfield.setMaxLength(32767);
		guistate.put("text:tools_textfield", tools_textfield);
		this.addWidget(this.tools_textfield);
		armor_textfield = new EditBox(this.font, this.leftPos + 61, this.topPos + 95, 118, 18, Component.translatable("gui.reign_mod.royale_settings.armor_textfield"));
		armor_textfield.setMaxLength(32767);
		guistate.put("text:armor_textfield", armor_textfield);
		this.addWidget(this.armor_textfield);
		food_textfield = new EditBox(this.font, this.leftPos + 61, this.topPos + 55, 118, 18, Component.translatable("gui.reign_mod.royale_settings.food_textfield"));
		food_textfield.setMaxLength(32767);
		guistate.put("text:food_textfield", food_textfield);
		this.addWidget(this.food_textfield);
		coal_textfield = new EditBox(this.font, this.leftPos + 61, this.topPos + 35, 118, 18, Component.translatable("gui.reign_mod.royale_settings.coal_textfield"));
		coal_textfield.setMaxLength(32767);
		guistate.put("text:coal_textfield", coal_textfield);
		this.addWidget(this.coal_textfield);
		ores_textfield = new EditBox(this.font, this.leftPos + 61, this.topPos + 115, 118, 18, Component.translatable("gui.reign_mod.royale_settings.ores_textfield"));
		ores_textfield.setMaxLength(32767);
		guistate.put("text:ores_textfield", ores_textfield);
		this.addWidget(this.ores_textfield);
		other_textfield = new EditBox(this.font, this.leftPos + 61, this.topPos + 135, 118, 18, Component.translatable("gui.reign_mod.royale_settings.other_textfield"));
		other_textfield.setMaxLength(32767);
		guistate.put("text:other_textfield", other_textfield);
		this.addWidget(this.other_textfield);
		imagebutton_but_up = new ImageButton(this.leftPos + 197, this.topPos + 16, 16, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_but_up.png"), 16, 32, e -> {
			if (true) {
				textstate.put("textin:logs_textfield", logs_textfield.getValue());
				textstate.put("textin:tools_textfield", tools_textfield.getValue());
				textstate.put("textin:armor_textfield", armor_textfield.getValue());
				textstate.put("textin:food_textfield", food_textfield.getValue());
				textstate.put("textin:coal_textfield", coal_textfield.getValue());
				textstate.put("textin:ores_textfield", ores_textfield.getValue());
				textstate.put("textin:other_textfield", other_textfield.getValue());
				ReignModMod.PACKET_HANDLER.sendToServer(new RoyaleSettingsButtonMessage(0, x, y, z, textstate));
				RoyaleSettingsButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_but_up", imagebutton_but_up);
		this.addRenderableWidget(imagebutton_but_up);
		imagebutton_but_down = new ImageButton(this.leftPos + 215, this.topPos + 16, 16, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_but_down.png"), 16, 32, e -> {
			if (true) {
				textstate.put("textin:logs_textfield", logs_textfield.getValue());
				textstate.put("textin:tools_textfield", tools_textfield.getValue());
				textstate.put("textin:armor_textfield", armor_textfield.getValue());
				textstate.put("textin:food_textfield", food_textfield.getValue());
				textstate.put("textin:coal_textfield", coal_textfield.getValue());
				textstate.put("textin:ores_textfield", ores_textfield.getValue());
				textstate.put("textin:other_textfield", other_textfield.getValue());
				ReignModMod.PACKET_HANDLER.sendToServer(new RoyaleSettingsButtonMessage(1, x, y, z, textstate));
				RoyaleSettingsButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_but_down", imagebutton_but_down);
		this.addRenderableWidget(imagebutton_but_down);
		imagebutton_but_up1 = new ImageButton(this.leftPos + 197, this.topPos + 36, 16, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_but_up1.png"), 16, 32, e -> {
			if (true) {
				textstate.put("textin:logs_textfield", logs_textfield.getValue());
				textstate.put("textin:tools_textfield", tools_textfield.getValue());
				textstate.put("textin:armor_textfield", armor_textfield.getValue());
				textstate.put("textin:food_textfield", food_textfield.getValue());
				textstate.put("textin:coal_textfield", coal_textfield.getValue());
				textstate.put("textin:ores_textfield", ores_textfield.getValue());
				textstate.put("textin:other_textfield", other_textfield.getValue());
				ReignModMod.PACKET_HANDLER.sendToServer(new RoyaleSettingsButtonMessage(2, x, y, z, textstate));
				RoyaleSettingsButtonMessage.handleButtonAction(entity, 2, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_but_up1", imagebutton_but_up1);
		this.addRenderableWidget(imagebutton_but_up1);
		imagebutton_but_down1 = new ImageButton(this.leftPos + 215, this.topPos + 36, 16, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_but_down1.png"), 16, 32, e -> {
			if (true) {
				textstate.put("textin:logs_textfield", logs_textfield.getValue());
				textstate.put("textin:tools_textfield", tools_textfield.getValue());
				textstate.put("textin:armor_textfield", armor_textfield.getValue());
				textstate.put("textin:food_textfield", food_textfield.getValue());
				textstate.put("textin:coal_textfield", coal_textfield.getValue());
				textstate.put("textin:ores_textfield", ores_textfield.getValue());
				textstate.put("textin:other_textfield", other_textfield.getValue());
				ReignModMod.PACKET_HANDLER.sendToServer(new RoyaleSettingsButtonMessage(3, x, y, z, textstate));
				RoyaleSettingsButtonMessage.handleButtonAction(entity, 3, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_but_down1", imagebutton_but_down1);
		this.addRenderableWidget(imagebutton_but_down1);
		imagebutton_but_up2 = new ImageButton(this.leftPos + 197, this.topPos + 56, 16, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_but_up2.png"), 16, 32, e -> {
			if (true) {
				textstate.put("textin:logs_textfield", logs_textfield.getValue());
				textstate.put("textin:tools_textfield", tools_textfield.getValue());
				textstate.put("textin:armor_textfield", armor_textfield.getValue());
				textstate.put("textin:food_textfield", food_textfield.getValue());
				textstate.put("textin:coal_textfield", coal_textfield.getValue());
				textstate.put("textin:ores_textfield", ores_textfield.getValue());
				textstate.put("textin:other_textfield", other_textfield.getValue());
				ReignModMod.PACKET_HANDLER.sendToServer(new RoyaleSettingsButtonMessage(4, x, y, z, textstate));
				RoyaleSettingsButtonMessage.handleButtonAction(entity, 4, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_but_up2", imagebutton_but_up2);
		this.addRenderableWidget(imagebutton_but_up2);
		imagebutton_but_down2 = new ImageButton(this.leftPos + 215, this.topPos + 56, 16, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_but_down2.png"), 16, 32, e -> {
			if (true) {
				textstate.put("textin:logs_textfield", logs_textfield.getValue());
				textstate.put("textin:tools_textfield", tools_textfield.getValue());
				textstate.put("textin:armor_textfield", armor_textfield.getValue());
				textstate.put("textin:food_textfield", food_textfield.getValue());
				textstate.put("textin:coal_textfield", coal_textfield.getValue());
				textstate.put("textin:ores_textfield", ores_textfield.getValue());
				textstate.put("textin:other_textfield", other_textfield.getValue());
				ReignModMod.PACKET_HANDLER.sendToServer(new RoyaleSettingsButtonMessage(5, x, y, z, textstate));
				RoyaleSettingsButtonMessage.handleButtonAction(entity, 5, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_but_down2", imagebutton_but_down2);
		this.addRenderableWidget(imagebutton_but_down2);
		imagebutton_but_up3 = new ImageButton(this.leftPos + 197, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_but_up3.png"), 16, 32, e -> {
			if (true) {
				textstate.put("textin:logs_textfield", logs_textfield.getValue());
				textstate.put("textin:tools_textfield", tools_textfield.getValue());
				textstate.put("textin:armor_textfield", armor_textfield.getValue());
				textstate.put("textin:food_textfield", food_textfield.getValue());
				textstate.put("textin:coal_textfield", coal_textfield.getValue());
				textstate.put("textin:ores_textfield", ores_textfield.getValue());
				textstate.put("textin:other_textfield", other_textfield.getValue());
				ReignModMod.PACKET_HANDLER.sendToServer(new RoyaleSettingsButtonMessage(6, x, y, z, textstate));
				RoyaleSettingsButtonMessage.handleButtonAction(entity, 6, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_but_up3", imagebutton_but_up3);
		this.addRenderableWidget(imagebutton_but_up3);
		imagebutton_but_down3 = new ImageButton(this.leftPos + 215, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_but_down3.png"), 16, 32, e -> {
			if (true) {
				textstate.put("textin:logs_textfield", logs_textfield.getValue());
				textstate.put("textin:tools_textfield", tools_textfield.getValue());
				textstate.put("textin:armor_textfield", armor_textfield.getValue());
				textstate.put("textin:food_textfield", food_textfield.getValue());
				textstate.put("textin:coal_textfield", coal_textfield.getValue());
				textstate.put("textin:ores_textfield", ores_textfield.getValue());
				textstate.put("textin:other_textfield", other_textfield.getValue());
				ReignModMod.PACKET_HANDLER.sendToServer(new RoyaleSettingsButtonMessage(7, x, y, z, textstate));
				RoyaleSettingsButtonMessage.handleButtonAction(entity, 7, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_but_down3", imagebutton_but_down3);
		this.addRenderableWidget(imagebutton_but_down3);
		imagebutton_but_up4 = new ImageButton(this.leftPos + 197, this.topPos + 96, 16, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_but_up4.png"), 16, 32, e -> {
			if (true) {
				textstate.put("textin:logs_textfield", logs_textfield.getValue());
				textstate.put("textin:tools_textfield", tools_textfield.getValue());
				textstate.put("textin:armor_textfield", armor_textfield.getValue());
				textstate.put("textin:food_textfield", food_textfield.getValue());
				textstate.put("textin:coal_textfield", coal_textfield.getValue());
				textstate.put("textin:ores_textfield", ores_textfield.getValue());
				textstate.put("textin:other_textfield", other_textfield.getValue());
				ReignModMod.PACKET_HANDLER.sendToServer(new RoyaleSettingsButtonMessage(8, x, y, z, textstate));
				RoyaleSettingsButtonMessage.handleButtonAction(entity, 8, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_but_up4", imagebutton_but_up4);
		this.addRenderableWidget(imagebutton_but_up4);
		imagebutton_but_down4 = new ImageButton(this.leftPos + 215, this.topPos + 96, 16, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_but_down4.png"), 16, 32, e -> {
			if (true) {
				textstate.put("textin:logs_textfield", logs_textfield.getValue());
				textstate.put("textin:tools_textfield", tools_textfield.getValue());
				textstate.put("textin:armor_textfield", armor_textfield.getValue());
				textstate.put("textin:food_textfield", food_textfield.getValue());
				textstate.put("textin:coal_textfield", coal_textfield.getValue());
				textstate.put("textin:ores_textfield", ores_textfield.getValue());
				textstate.put("textin:other_textfield", other_textfield.getValue());
				ReignModMod.PACKET_HANDLER.sendToServer(new RoyaleSettingsButtonMessage(9, x, y, z, textstate));
				RoyaleSettingsButtonMessage.handleButtonAction(entity, 9, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_but_down4", imagebutton_but_down4);
		this.addRenderableWidget(imagebutton_but_down4);
		imagebutton_but_up5 = new ImageButton(this.leftPos + 197, this.topPos + 116, 16, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_but_up5.png"), 16, 32, e -> {
			if (true) {
				textstate.put("textin:logs_textfield", logs_textfield.getValue());
				textstate.put("textin:tools_textfield", tools_textfield.getValue());
				textstate.put("textin:armor_textfield", armor_textfield.getValue());
				textstate.put("textin:food_textfield", food_textfield.getValue());
				textstate.put("textin:coal_textfield", coal_textfield.getValue());
				textstate.put("textin:ores_textfield", ores_textfield.getValue());
				textstate.put("textin:other_textfield", other_textfield.getValue());
				ReignModMod.PACKET_HANDLER.sendToServer(new RoyaleSettingsButtonMessage(10, x, y, z, textstate));
				RoyaleSettingsButtonMessage.handleButtonAction(entity, 10, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_but_up5", imagebutton_but_up5);
		this.addRenderableWidget(imagebutton_but_up5);
		imagebutton_but_down5 = new ImageButton(this.leftPos + 215, this.topPos + 116, 16, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_but_down5.png"), 16, 32, e -> {
			if (true) {
				textstate.put("textin:logs_textfield", logs_textfield.getValue());
				textstate.put("textin:tools_textfield", tools_textfield.getValue());
				textstate.put("textin:armor_textfield", armor_textfield.getValue());
				textstate.put("textin:food_textfield", food_textfield.getValue());
				textstate.put("textin:coal_textfield", coal_textfield.getValue());
				textstate.put("textin:ores_textfield", ores_textfield.getValue());
				textstate.put("textin:other_textfield", other_textfield.getValue());
				ReignModMod.PACKET_HANDLER.sendToServer(new RoyaleSettingsButtonMessage(11, x, y, z, textstate));
				RoyaleSettingsButtonMessage.handleButtonAction(entity, 11, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_but_down5", imagebutton_but_down5);
		this.addRenderableWidget(imagebutton_but_down5);
		imagebutton_but_up6 = new ImageButton(this.leftPos + 197, this.topPos + 136, 16, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_but_up6.png"), 16, 32, e -> {
			if (true) {
				textstate.put("textin:logs_textfield", logs_textfield.getValue());
				textstate.put("textin:tools_textfield", tools_textfield.getValue());
				textstate.put("textin:armor_textfield", armor_textfield.getValue());
				textstate.put("textin:food_textfield", food_textfield.getValue());
				textstate.put("textin:coal_textfield", coal_textfield.getValue());
				textstate.put("textin:ores_textfield", ores_textfield.getValue());
				textstate.put("textin:other_textfield", other_textfield.getValue());
				ReignModMod.PACKET_HANDLER.sendToServer(new RoyaleSettingsButtonMessage(12, x, y, z, textstate));
				RoyaleSettingsButtonMessage.handleButtonAction(entity, 12, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_but_up6", imagebutton_but_up6);
		this.addRenderableWidget(imagebutton_but_up6);
		imagebutton_but_down6 = new ImageButton(this.leftPos + 215, this.topPos + 136, 16, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_but_down6.png"), 16, 32, e -> {
			if (true) {
				textstate.put("textin:logs_textfield", logs_textfield.getValue());
				textstate.put("textin:tools_textfield", tools_textfield.getValue());
				textstate.put("textin:armor_textfield", armor_textfield.getValue());
				textstate.put("textin:food_textfield", food_textfield.getValue());
				textstate.put("textin:coal_textfield", coal_textfield.getValue());
				textstate.put("textin:ores_textfield", ores_textfield.getValue());
				textstate.put("textin:other_textfield", other_textfield.getValue());
				ReignModMod.PACKET_HANDLER.sendToServer(new RoyaleSettingsButtonMessage(13, x, y, z, textstate));
				RoyaleSettingsButtonMessage.handleButtonAction(entity, 13, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_but_down6", imagebutton_but_down6);
		this.addRenderableWidget(imagebutton_but_down6);
	}
}
