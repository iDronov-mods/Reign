package net.mcreator.reignmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import net.mcreator.reignmod.world.inventory.RentalBlockUIMenu;
import net.mcreator.reignmod.procedures.RentalNotLockedProcedure;
import net.mcreator.reignmod.procedures.RentalIsLockedProcedure;
import net.mcreator.reignmod.procedures.RentalAreaProcedure;
import net.mcreator.reignmod.procedures.RentalAreaLockedProcedure;
import net.mcreator.reignmod.network.RentalBlockUIButtonMessage;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class RentalBlockUIScreen extends AbstractContainerScreen<RentalBlockUIMenu> {
	private final static HashMap<String, Object> guistate = RentalBlockUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	public static EditBox x_value;
	public static EditBox z_value;
	Button button_delete;
	Button button_set;

	public RentalBlockUIScreen(RentalBlockUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 223;
		this.imageHeight = 166;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		x_value.render(guiGraphics, mouseX, mouseY, partialTicks);
		z_value.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/rental_block_ui.png"), this.leftPos + 1, this.topPos + 0, 0, 0, 222, 166, 222, 166);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (x_value.isFocused())
			return x_value.keyPressed(key, b, c);
		if (z_value.isFocused())
			return z_value.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		x_value.tick();
		z_value.tick();
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String x_valueValue = x_value.getValue();
		String z_valueValue = z_value.getValue();
		super.resize(minecraft, width, height);
		x_value.setValue(x_valueValue);
		z_value.setValue(z_valueValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		if (RentalNotLockedProcedure.execute(world, x, y, z))
			guiGraphics.drawString(this.font,

					RentalAreaProcedure.execute(entity, guistate), 87, 9, -13421824, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.rental_block_ui.label_rental_block"), 1, -11, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.rental_block_ui.label_x"), 26, 54, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.rental_block_ui.label_z"), 53, 54, -1, false);
		if (RentalIsLockedProcedure.execute(world, x, y, z))
			guiGraphics.drawString(this.font,

					RentalAreaLockedProcedure.execute(world, x, y, z), 87, 9, -13421824, false);
	}

	@Override
	public void init() {
		super.init();
		x_value = new EditBox(this.font, this.leftPos + 17, this.topPos + 36, 24, 18, Component.translatable("gui.reign_mod.rental_block_ui.x_value"));
		x_value.setMaxLength(32767);
		guistate.put("text:x_value", x_value);
		this.addWidget(this.x_value);
		z_value = new EditBox(this.font, this.leftPos + 43, this.topPos + 36, 24, 18, Component.translatable("gui.reign_mod.rental_block_ui.z_value"));
		z_value.setMaxLength(32767);
		guistate.put("text:z_value", z_value);
		this.addWidget(this.z_value);
		button_delete = Button.builder(Component.translatable("gui.reign_mod.rental_block_ui.button_delete"), e -> {
			if (true) {
				textstate.put("textin:x_value", x_value.getValue());
				textstate.put("textin:z_value", z_value.getValue());
				ReignModMod.PACKET_HANDLER.sendToServer(new RentalBlockUIButtonMessage(0, x, y, z, textstate));
				RentalBlockUIButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 152, this.topPos + 46, 52, 20).build();
		guistate.put("button:button_delete", button_delete);
		this.addRenderableWidget(button_delete);
		button_set = Button.builder(Component.translatable("gui.reign_mod.rental_block_ui.button_set"), e -> {
			if (true) {
				textstate.put("textin:x_value", x_value.getValue());
				textstate.put("textin:z_value", z_value.getValue());
				ReignModMod.PACKET_HANDLER.sendToServer(new RentalBlockUIButtonMessage(1, x, y, z, textstate));
				RentalBlockUIButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 152, this.topPos + 24, 52, 20).build();
		guistate.put("button:button_set", button_set);
		this.addRenderableWidget(button_set);
	}
}
