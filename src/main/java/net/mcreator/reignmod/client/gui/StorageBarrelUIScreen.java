package net.mcreator.reignmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.reignmod.world.inventory.StorageBarrelUIMenu;
import net.mcreator.reignmod.procedures.StorageBarrelCategoryTextGetProcedure;
import net.mcreator.reignmod.network.StorageBarrelUIButtonMessage;
import net.mcreator.reignmod.init.ReignModModScreens.WidgetScreen;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class StorageBarrelUIScreen extends AbstractContainerScreen<StorageBarrelUIMenu> implements WidgetScreen {
	private final static HashMap<String, Object> guistate = StorageBarrelUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	Button button_logs;
	Button button_fuel;
	Button button_ores;
	Button button_armors;
	Button button_tools;
	Button button_blocks;
	Button button_experience;
	Button button_other;
	Button button_foods;

	public StorageBarrelUIScreen(StorageBarrelUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 190;
		this.imageHeight = 166;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 161 && mouseX < leftPos + 179 && mouseY > topPos + 72 && mouseY < topPos + 85)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.storage_barrel_ui.tooltip_storage_barrel_help"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/crown.png"), this.leftPos + 163, this.topPos + 72, 0, 0, 16, 16, 16, 16);

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
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.storage_barrel_ui.label_storage_barrel"), 2, -10, -1, false);
		guiGraphics.drawString(this.font,

				StorageBarrelCategoryTextGetProcedure.execute(world, x, y, z), 3, 73, -16724890, false);
	}

	@Override
	public void init() {
		super.init();
		button_logs = Button.builder(Component.translatable("gui.reign_mod.storage_barrel_ui.button_logs"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new StorageBarrelUIButtonMessage(0, x, y, z, textstate));
				StorageBarrelUIButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 1, this.topPos + 84, 62, 20).build();
		guistate.put("button:button_logs", button_logs);
		this.addRenderableWidget(button_logs);
		button_fuel = Button.builder(Component.translatable("gui.reign_mod.storage_barrel_ui.button_fuel"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new StorageBarrelUIButtonMessage(1, x, y, z, textstate));
				StorageBarrelUIButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 64, this.topPos + 84, 62, 20).build();
		guistate.put("button:button_fuel", button_fuel);
		this.addRenderableWidget(button_fuel);
		button_ores = Button.builder(Component.translatable("gui.reign_mod.storage_barrel_ui.button_ores"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new StorageBarrelUIButtonMessage(2, x, y, z, textstate));
				StorageBarrelUIButtonMessage.handleButtonAction(entity, 2, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 127, this.topPos + 84, 62, 20).build();
		guistate.put("button:button_ores", button_ores);
		this.addRenderableWidget(button_ores);
		button_armors = Button.builder(Component.translatable("gui.reign_mod.storage_barrel_ui.button_armors"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new StorageBarrelUIButtonMessage(3, x, y, z, textstate));
				StorageBarrelUIButtonMessage.handleButtonAction(entity, 3, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 64, this.topPos + 105, 62, 20).build();
		guistate.put("button:button_armors", button_armors);
		this.addRenderableWidget(button_armors);
		button_tools = Button.builder(Component.translatable("gui.reign_mod.storage_barrel_ui.button_tools"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new StorageBarrelUIButtonMessage(4, x, y, z, textstate));
				StorageBarrelUIButtonMessage.handleButtonAction(entity, 4, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 1, this.topPos + 105, 62, 20).build();
		guistate.put("button:button_tools", button_tools);
		this.addRenderableWidget(button_tools);
		button_blocks = Button.builder(Component.translatable("gui.reign_mod.storage_barrel_ui.button_blocks"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new StorageBarrelUIButtonMessage(5, x, y, z, textstate));
				StorageBarrelUIButtonMessage.handleButtonAction(entity, 5, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 1, this.topPos + 126, 62, 20).build();
		guistate.put("button:button_blocks", button_blocks);
		this.addRenderableWidget(button_blocks);
		button_experience = Button.builder(Component.translatable("gui.reign_mod.storage_barrel_ui.button_experience"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new StorageBarrelUIButtonMessage(6, x, y, z, textstate));
				StorageBarrelUIButtonMessage.handleButtonAction(entity, 6, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 127, this.topPos + 126, 62, 20).build();
		guistate.put("button:button_experience", button_experience);
		this.addRenderableWidget(button_experience);
		button_other = Button.builder(Component.translatable("gui.reign_mod.storage_barrel_ui.button_other"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new StorageBarrelUIButtonMessage(7, x, y, z, textstate));
				StorageBarrelUIButtonMessage.handleButtonAction(entity, 7, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 64, this.topPos + 126, 62, 20).build();
		guistate.put("button:button_other", button_other);
		this.addRenderableWidget(button_other);
		button_foods = Button.builder(Component.translatable("gui.reign_mod.storage_barrel_ui.button_foods"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new StorageBarrelUIButtonMessage(8, x, y, z, textstate));
				StorageBarrelUIButtonMessage.handleButtonAction(entity, 8, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 127, this.topPos + 105, 62, 20).build();
		guistate.put("button:button_foods", button_foods);
		this.addRenderableWidget(button_foods);
	}
}
