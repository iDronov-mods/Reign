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

import net.mcreator.reignmod.world.inventory.KingtableUIMenu;
import net.mcreator.reignmod.procedures.RentalSealDoneProcedure;
import net.mcreator.reignmod.network.KingtableUIButtonMessage;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class KingtableUIScreen extends AbstractContainerScreen<KingtableUIMenu> {
	private final static HashMap<String, Object> guistate = KingtableUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	public static EditBox owners;
	ImageButton imagebutton_seal;

	public KingtableUIScreen(KingtableUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 200;
		this.imageHeight = 166;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		owners.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 148 && mouseX < leftPos + 172 && mouseY > topPos + -18 && mouseY < topPos + 6)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.kingtable_ui.tooltip_kingtable_help"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/newkingtable_ui.png"), this.leftPos + -50, this.topPos + 0, 0, 0, 300, 166, 300, 166);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/crown.png"), this.leftPos + 152, this.topPos + -13, 0, 0, 16, 16, 16, 16);

		if (RentalSealDoneProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/seal_done.png"), this.leftPos + 77, this.topPos + 59, 0, 0, 24, 18, 24, 18);
		}
		RenderSystem.disableBlend();
	}

	public static HashMap<String, String> getTextboxValues() {
		textstate.put("textin:owners", owners.getValue());
		return textstate;
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (owners.isFocused())
			return owners.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		owners.tick();
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String ownersValue = owners.getValue();
		super.resize(minecraft, width, height);
		owners.setValue(ownersValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.kingtable_ui.label_kingtable_lable"), 0, -11, -1, false);
	}

	@Override
	public void init() {
		super.init();
		owners = new EditBox(this.font, this.leftPos + 76, this.topPos + 18, 65, 18, Component.translatable("gui.reign_mod.kingtable_ui.owners"));
		owners.setMaxLength(32767);
		guistate.put("text:owners", owners);
		this.addWidget(this.owners);
		imagebutton_seal = new ImageButton(this.leftPos + 80, this.topPos + 59, 18, 18, 0, 0, 18, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_seal.png"), 18, 36, e -> {
			if (true) {
				textstate.put("textin:owners", owners.getValue());
				ReignModMod.PACKET_HANDLER.sendToServer(new KingtableUIButtonMessage(0, x, y, z, textstate));
				KingtableUIButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_seal", imagebutton_seal);
		this.addRenderableWidget(imagebutton_seal);
	}
}
