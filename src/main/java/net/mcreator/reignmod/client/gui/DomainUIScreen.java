package net.mcreator.reignmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.reignmod.world.inventory.DomainUIMenu;
import net.mcreator.reignmod.procedures.DomainHP66Procedure;
import net.mcreator.reignmod.procedures.DomainHP56Procedure;
import net.mcreator.reignmod.procedures.DomainHP46Procedure;
import net.mcreator.reignmod.procedures.DomainHP36Procedure;
import net.mcreator.reignmod.procedures.DomainHP26Procedure;
import net.mcreator.reignmod.procedures.DomainHP16Procedure;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class DomainUIScreen extends AbstractContainerScreen<DomainUIMenu> {
	private final static HashMap<String, Object> guistate = DomainUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();

	public DomainUIScreen(DomainUIMenu container, Inventory inventory, Component text) {
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
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/ui_domain.png"), this.leftPos + 9, this.topPos + -16, 0, 0, 161, 66, 161, 66);

		if (DomainHP16Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 27, this.topPos + 1, 0, 0, 20, 4, 20, 4);
		}
		if (DomainHP26Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 48, this.topPos + 1, 0, 0, 20, 4, 20, 4);
		}
		if (DomainHP36Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 69, this.topPos + 1, 0, 0, 20, 4, 20, 4);
		}
		if (DomainHP46Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 90, this.topPos + 1, 0, 0, 20, 4, 20, 4);
		}
		if (DomainHP56Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 111, this.topPos + 1, 0, 0, 20, 4, 20, 4);
		}
		if (DomainHP66Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 132, this.topPos + 1, 0, 0, 20, 4, 20, 4);
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
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.domain_ui.label_hp"), 158, -3, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.domain_ui.label_players"), 54, 25, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
	}
}
