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

import net.mcreator.reignmod.world.inventory.StrategyBlockUIMenu;
import net.mcreator.reignmod.procedures.StrategyBlockNoDirectionProcedure;
import net.mcreator.reignmod.procedures.StrategyBlockIsDirection3Procedure;
import net.mcreator.reignmod.procedures.StrategyBlockIsDirection2Procedure;
import net.mcreator.reignmod.procedures.StrategyBlockIsDirection1Procedure;
import net.mcreator.reignmod.procedures.StrategyBlockDefenceGetProcedure;
import net.mcreator.reignmod.procedures.StrategyBlockAttackGetProcedure;
import net.mcreator.reignmod.procedures.StraregyBlockIsDirection0Procedure;
import net.mcreator.reignmod.network.StrategyBlockUIButtonMessage;
import net.mcreator.reignmod.init.ReignModModScreens.WidgetScreen;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class StrategyBlockUIScreen extends AbstractContainerScreen<StrategyBlockUIMenu> implements WidgetScreen {
	private final static HashMap<String, Object> guistate = StrategyBlockUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	Button button_empty;
	Button button_empty1;
	ImageButton imagebutton_arrow;
	ImageButton imagebutton_arrow_d;
	ImageButton imagebutton_arrow_l;
	ImageButton imagebutton_arrow_u;
	ImageButton imagebutton_circle;

	public StrategyBlockUIScreen(StrategyBlockUIMenu container, Inventory inventory, Component text) {
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
		if (mouseX > leftPos + 142 && mouseX < leftPos + 158 && mouseY > topPos + -10 && mouseY < topPos + 1)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.strategy_block_ui.tooltip_strategy_block_help"), mouseX, mouseY);
		if (mouseX > leftPos + 14 && mouseX < leftPos + 38 && mouseY > topPos + 28 && mouseY < topPos + 52)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.strategy_block_ui.tooltip_expansion_help"), mouseX, mouseY);
		if (mouseX > leftPos + 138 && mouseX < leftPos + 162 && mouseY > topPos + 28 && mouseY < topPos + 52)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.strategy_block_ui.tooltip_defence_help"), mouseX, mouseY);
		if (mouseX > leftPos + 83 && mouseX < leftPos + 93 && mouseY > topPos + 0 && mouseY < topPos + 25)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.strategy_block_ui.tooltip_north_help"), mouseX, mouseY);
		if (mouseX > leftPos + 45 && mouseX < leftPos + 69 && mouseY > topPos + 37 && mouseY < topPos + 47)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.strategy_block_ui.tooltip_west_help"), mouseX, mouseY);
		if (mouseX > leftPos + 105 && mouseX < leftPos + 129 && mouseY > topPos + 37 && mouseY < topPos + 47)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.strategy_block_ui.tooltip_east_help"), mouseX, mouseY);
		if (mouseX > leftPos + 83 && mouseX < leftPos + 93 && mouseY > topPos + 62 && mouseY < topPos + 84)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.strategy_block_ui.tooltip_south_help"), mouseX, mouseY);
		if (mouseX > leftPos + 80 && mouseX < leftPos + 96 && mouseY > topPos + 34 && mouseY < topPos + 50)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.strategy_block_ui.tooltip_center_help"), mouseX, mouseY);
		if (mouseX > leftPos + 177 && mouseX < leftPos + 194 && mouseY > topPos + 20 && mouseY < topPos + 36)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.strategy_block_ui.tooltip_strategy_block_help2"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/strategyblock_ui.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/crown.png"), this.leftPos + 142, this.topPos + -12, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/arrow_selected.png"), this.leftPos + 101, this.topPos + 34, 0, 0, 32, 16, 32, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/arrow_selected_d.png"), this.leftPos + 80, this.topPos + 56, 0, 0, 16, 32, 16, 32);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/arrow_selected_l.png"), this.leftPos + 41, this.topPos + 34, 0, 0, 32, 16, 32, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/arrow_selected_u.png"), this.leftPos + 80, this.topPos + -4, 0, 0, 16, 32, 16, 32);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/circle_selected.png"), this.leftPos + 80, this.topPos + 34, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/coin_slot.png"), this.leftPos + 179, this.topPos + 4, 0, 0, 12, 12, 12, 12);

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
		ReignModMod.PACKET_HANDLER.sendToServer(new StrategyBlockUIMenu.StrategyBlockUIOtherMessage(0, x, y, z, textstate));
		StrategyBlockUIMenu.StrategyBlockUIOtherMessage.handleOtherAction(entity, 0, x, y, z, textstate);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.strategy_block_ui.label_strategy_block"), 1, -10, -1, false);
		guiGraphics.drawString(this.font,

				StrategyBlockAttackGetProcedure.execute(world, x, y, z), 29, 50, -52378, false);
		guiGraphics.drawString(this.font,

				StrategyBlockDefenceGetProcedure.execute(world, x, y, z), 152, 50, -6710887, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.strategy_block_ui.label_empty"), 183, 22, -1, false);
	}

	@Override
	public void init() {
		super.init();
		button_empty = Button.builder(Component.translatable("gui.reign_mod.strategy_block_ui.button_empty"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new StrategyBlockUIButtonMessage(0, x, y, z, textstate));
				StrategyBlockUIButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 125, this.topPos + 60, 18, 20).build();
		guistate.put("button:button_empty", button_empty);
		this.addRenderableWidget(button_empty);
		button_empty1 = Button.builder(Component.translatable("gui.reign_mod.strategy_block_ui.button_empty1"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new StrategyBlockUIButtonMessage(1, x, y, z, textstate));
				StrategyBlockUIButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 33, this.topPos + 60, 18, 20).build();
		guistate.put("button:button_empty1", button_empty1);
		this.addRenderableWidget(button_empty1);
		imagebutton_arrow = new ImageButton(this.leftPos + 101, this.topPos + 34, 32, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_arrow.png"), 32, 32, e -> {
			if (StrategyBlockIsDirection2Procedure.execute(world, x, y, z)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new StrategyBlockUIButtonMessage(2, x, y, z, textstate));
				StrategyBlockUIButtonMessage.handleButtonAction(entity, 2, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (StrategyBlockIsDirection2Procedure.execute(world, x, y, z))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_arrow", imagebutton_arrow);
		this.addRenderableWidget(imagebutton_arrow);
		imagebutton_arrow_d = new ImageButton(this.leftPos + 80, this.topPos + 56, 16, 32, 0, 0, 32, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_arrow_d.png"), 16, 64, e -> {
			if (StrategyBlockIsDirection3Procedure.execute(world, x, y, z)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new StrategyBlockUIButtonMessage(3, x, y, z, textstate));
				StrategyBlockUIButtonMessage.handleButtonAction(entity, 3, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (StrategyBlockIsDirection3Procedure.execute(world, x, y, z))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_arrow_d", imagebutton_arrow_d);
		this.addRenderableWidget(imagebutton_arrow_d);
		imagebutton_arrow_l = new ImageButton(this.leftPos + 41, this.topPos + 34, 32, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_arrow_l.png"), 32, 32, e -> {
			if (StraregyBlockIsDirection0Procedure.execute(world, x, y, z)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new StrategyBlockUIButtonMessage(4, x, y, z, textstate));
				StrategyBlockUIButtonMessage.handleButtonAction(entity, 4, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (StraregyBlockIsDirection0Procedure.execute(world, x, y, z))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_arrow_l", imagebutton_arrow_l);
		this.addRenderableWidget(imagebutton_arrow_l);
		imagebutton_arrow_u = new ImageButton(this.leftPos + 80, this.topPos + -4, 16, 32, 0, 0, 32, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_arrow_u.png"), 16, 64, e -> {
			if (StrategyBlockIsDirection1Procedure.execute(world, x, y, z)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new StrategyBlockUIButtonMessage(5, x, y, z, textstate));
				StrategyBlockUIButtonMessage.handleButtonAction(entity, 5, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (StrategyBlockIsDirection1Procedure.execute(world, x, y, z))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_arrow_u", imagebutton_arrow_u);
		this.addRenderableWidget(imagebutton_arrow_u);
		imagebutton_circle = new ImageButton(this.leftPos + 80, this.topPos + 34, 16, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_circle.png"), 16, 32, e -> {
			if (StrategyBlockNoDirectionProcedure.execute(world, x, y, z)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new StrategyBlockUIButtonMessage(6, x, y, z, textstate));
				StrategyBlockUIButtonMessage.handleButtonAction(entity, 6, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (StrategyBlockNoDirectionProcedure.execute(world, x, y, z))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_circle", imagebutton_circle);
		this.addRenderableWidget(imagebutton_circle);
	}
}
