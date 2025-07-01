package net.mcreator.reignmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.reignmod.world.inventory.StorageBarrelUIMenu;
import net.mcreator.reignmod.procedures.StorageBarrelCategoryTextGetProcedure;
import net.mcreator.reignmod.init.ReignModModScreens.WidgetScreen;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class StorageBarrelUIScreen extends AbstractContainerScreen<StorageBarrelUIMenu> implements WidgetScreen {
	private final static HashMap<String, Object> guistate = StorageBarrelUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();

	public StorageBarrelUIScreen(StorageBarrelUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 180;
		this.imageHeight = 166;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 133 && mouseX < leftPos + 151 && mouseY > topPos + 3 && mouseY < topPos + 16)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.storage_barrel_ui.tooltip_storage_barrel_help"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/storagebarrel_ui.png"), this.leftPos + 44, this.topPos + 16, 0, 0, 111, 58, 111, 58);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/plusui.png"), this.leftPos + 27, this.topPos + 37, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/crown.png"), this.leftPos + 134, this.topPos + 3, 0, 0, 16, 16, 16, 16);

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
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.storage_barrel_ui.label_storage_barrel"), 1, -10, -1, false);
		guiGraphics.drawString(this.font,

				StorageBarrelCategoryTextGetProcedure.execute(world, x, y, z), 30, 21, -1, false);
	}

	@Override
	public void init() {
		super.init();
	}
}
