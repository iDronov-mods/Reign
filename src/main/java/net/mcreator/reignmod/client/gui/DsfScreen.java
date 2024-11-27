package net.mcreator.reignmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.reignmod.world.inventory.DsfMenu;
import net.mcreator.reignmod.network.DsfButtonMessage;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class DsfScreen extends AbstractContainerScreen<DsfMenu> {
	private final static HashMap<String, Object> guistate = DsfMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	Button button_woodcutter;
	Button button_blacksmith;
	Button button_miner;
	Button button_farmer;
	Button button_button_cowboy;

	public DsfScreen(DsfMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 150;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("reign_mod:textures/screens/dsf.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 6 && mouseX < leftPos + 30 && mouseY > topPos + -14 && mouseY < topPos + 10)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.dsf.tooltip_vybieritie_osnovnoi_stil_ighry"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/crown.png"), this.leftPos + 11, this.topPos + -11, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/woodcutter_logo.png"), this.leftPos + 20, this.topPos + 25, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/smith_logo.png"), this.leftPos + 20, this.topPos + 52, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/miner_logo.png"), this.leftPos + 20, this.topPos + 79, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/.png"), this.leftPos + 20, this.topPos + 106, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/cowboy_logo.png"), this.leftPos + 20, this.topPos + 133, 0, 0, 16, 16, 16, 16);

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
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.dsf.label_osnovnyie_litsienzii"), 29, 7, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_woodcutter = Button.builder(Component.translatable("gui.reign_mod.dsf.button_woodcutter"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new DsfButtonMessage(0, x, y, z, textstate));
				DsfButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 56, this.topPos + 23, 77, 20).build();
		guistate.put("button:button_woodcutter", button_woodcutter);
		this.addRenderableWidget(button_woodcutter);
		button_blacksmith = Button.builder(Component.translatable("gui.reign_mod.dsf.button_blacksmith"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new DsfButtonMessage(1, x, y, z, textstate));
				DsfButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 56, this.topPos + 50, 77, 20).build();
		guistate.put("button:button_blacksmith", button_blacksmith);
		this.addRenderableWidget(button_blacksmith);
		button_miner = Button.builder(Component.translatable("gui.reign_mod.dsf.button_miner"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new DsfButtonMessage(2, x, y, z, textstate));
				DsfButtonMessage.handleButtonAction(entity, 2, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 56, this.topPos + 77, 76, 20).build();
		guistate.put("button:button_miner", button_miner);
		this.addRenderableWidget(button_miner);
		button_farmer = Button.builder(Component.translatable("gui.reign_mod.dsf.button_farmer"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new DsfButtonMessage(3, x, y, z, textstate));
				DsfButtonMessage.handleButtonAction(entity, 3, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 56, this.topPos + 104, 76, 20).build();
		guistate.put("button:button_farmer", button_farmer);
		this.addRenderableWidget(button_farmer);
		button_button_cowboy = Button.builder(Component.translatable("gui.reign_mod.dsf.button_button_cowboy"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new DsfButtonMessage(4, x, y, z, textstate));
				DsfButtonMessage.handleButtonAction(entity, 4, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 56, this.topPos + 131, 76, 20).build();
		guistate.put("button:button_button_cowboy", button_button_cowboy);
		this.addRenderableWidget(button_button_cowboy);
	}
}
