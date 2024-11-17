package net.mcreator.reignmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import net.mcreator.reignmod.world.inventory.NewHouseMenu;
import net.mcreator.reignmod.procedures.ReturnMeProcedure;
import net.mcreator.reignmod.network.NewHouseButtonMessage;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class NewHouseScreen extends AbstractContainerScreen<NewHouseMenu> {
	private final static HashMap<String, Object> guistate = NewHouseMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox house_name;
	Button button_create;

	public NewHouseScreen(NewHouseMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 180;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("reign_mod:textures/screens/new_house.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		house_name.render(guiGraphics, mouseX, mouseY, partialTicks);
		if (ReturnMeProcedure.execute(entity) instanceof LivingEntity livingEntity) {
			InventoryScreen.renderEntityInInventoryFollowsAngle(guiGraphics, this.leftPos + 207, this.topPos + 84, 30, 0.5f + (float) Math.atan((this.leftPos + 207 - mouseX) / 40.0), (float) Math.atan((this.topPos + 35 - mouseY) / 40.0),
					livingEntity);
		}
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 151 && mouseX < leftPos + 175 && mouseY > topPos + -18 && mouseY < topPos + 6)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.new_house.tooltip_napishitie_imia_i_vybieritie_tsviet_bud"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/crown.png"), this.leftPos + 155, this.topPos + -12, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/gold_coin.png"), this.leftPos + 187, this.topPos + 102, 0, 0, 16, 16, 16, 16);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (house_name.isFocused())
			return house_name.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		house_name.tick();
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String house_nameValue = house_name.getValue();
		super.resize(minecraft, width, height);
		house_name.setValue(house_nameValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.new_house.label_name"), 9, 7, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.new_house.label_found_a_house"), 0, -11, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.new_house.label_4"), 183, 105, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.new_house.label_coins"), 202, 105, -1, false);
	}

	@Override
	public void init() {
		super.init();
		house_name = new EditBox(this.font, this.leftPos + 10, this.topPos + 19, 160, 18, Component.translatable("gui.reign_mod.new_house.house_name"));
		house_name.setMaxLength(32767);
		guistate.put("text:house_name", house_name);
		this.addWidget(this.house_name);
		button_create = Button.builder(Component.translatable("gui.reign_mod.new_house.button_create"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new NewHouseButtonMessage(0, x, y, z));
				NewHouseButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 182, this.topPos + 83, 48, 20).build();
		guistate.put("button:button_create", button_create);
		this.addRenderableWidget(button_create);
	}
}
