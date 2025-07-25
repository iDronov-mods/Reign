package net.mcreator.reignmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.reignmod.world.inventory.MineUIMenu;
import net.mcreator.reignmod.procedures.MineTypeGetProcedure;
import net.mcreator.reignmod.procedures.MineTimeGetProcedure;
import net.mcreator.reignmod.procedures.MinePlayersGetProcedure;
import net.mcreator.reignmod.procedures.MineLeftGetProcedure;
import net.mcreator.reignmod.procedures.MineExtractedGetProcedure;
import net.mcreator.reignmod.network.MineUIButtonMessage;
import net.mcreator.reignmod.init.ReignModModScreens.WidgetScreen;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class MineUIScreen extends AbstractContainerScreen<MineUIMenu> implements WidgetScreen {
	private final static HashMap<String, Object> guistate = MineUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	Button button_take;

	public MineUIScreen(MineUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 150;
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

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/mineui.png"), this.leftPos + 0, this.topPos + 30, 0, 0, 150, 96, 150, 96);

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
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.mine_ui.label_mine"), 0, 19, -1, false);
		guiGraphics.drawString(this.font,

				MineTypeGetProcedure.execute(world, x, y, z), 8, 39, -1, false);
		guiGraphics.drawString(this.font,

				MinePlayersGetProcedure.execute(world, x, y, z), 8, 78, -6710887, false);
		guiGraphics.drawString(this.font,

				MineLeftGetProcedure.execute(world, x, y, z), 91, 19, -26317, false);
		guiGraphics.drawString(this.font,

				MineExtractedGetProcedure.execute(world, x, y, z), 8, 89, -13395712, false);
		guiGraphics.drawString(this.font,

				MineTimeGetProcedure.execute(world, x, y, z), 8, 50, -6710887, false);
	}

	@Override
	public void init() {
		super.init();
		button_take = Button.builder(Component.translatable("gui.reign_mod.mine_ui.button_take"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new MineUIButtonMessage(0, x, y, z, textstate));
				MineUIButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 47, this.topPos + 100, 58, 20).build();
		guistate.put("button:button_take", button_take);
		this.addRenderableWidget(button_take);
	}
}
