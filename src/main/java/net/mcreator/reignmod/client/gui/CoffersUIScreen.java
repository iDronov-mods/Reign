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

import net.mcreator.reignmod.world.inventory.CoffersUIMenu;
import net.mcreator.reignmod.procedures.ReturnCoffersAmountProcedure;
import net.mcreator.reignmod.procedures.ReturnCapitalServiceProcedure;
import net.mcreator.reignmod.procedures.CapitalClaimDIsabledProcedure;
import net.mcreator.reignmod.network.CoffersUIButtonMessage;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class CoffersUIScreen extends AbstractContainerScreen<CoffersUIMenu> {
	private final static HashMap<String, Object> guistate = CoffersUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	Button button_take;
	Button button_pay_off;
	ImageButton imagebutton_tab_button;

	public CoffersUIScreen(CoffersUIMenu container, Inventory inventory, Component text) {
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
		if (mouseX > leftPos + 130 && mouseX < leftPos + 146 && mouseY > topPos + -13 && mouseY < topPos + 0)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.coffers_ui.tooltip_this_is_where_government_money_i"), mouseX, mouseY);
		if (mouseX > leftPos + 62 && mouseX < leftPos + 114 && mouseY > topPos + 61 && mouseY < topPos + 79)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.coffers_ui.tooltip_coffers_take_help"), mouseX, mouseY);
		if (mouseX > leftPos + 1 && mouseX < leftPos + 114 && mouseY > topPos + 1 && mouseY < topPos + 15)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.coffers_ui.tooltip_capital_service_help"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/coffers_ui_new.png"), this.leftPos + -52, this.topPos + -57, 0, 0, 280, 280, 280, 280);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/crown.png"), this.leftPos + 131, this.topPos + -14, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/platinum_coin.png"), this.leftPos + 79, this.topPos + 52, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/copper_coin.png"), this.leftPos + 3, this.topPos + 11, 0, 0, 16, 16, 16, 16);

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
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.coffers_ui.label_coffers"), 68, 11, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.coffers_ui.label_coffers1"), 0, -10, -1, false);
		guiGraphics.drawString(this.font,

				ReturnCapitalServiceProcedure.execute(world, x, y, z), 7, 4, -6710887, false);
		guiGraphics.drawString(this.font,

				ReturnCoffersAmountProcedure.execute(world, x, y, z), 19, 15, -3381760, false);
		if (CapitalClaimDIsabledProcedure.execute(world, x, y, z))
			guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.coffers_ui.label_pay_off_text"), 60, 173, -3407821, false);
	}

	@Override
	public void init() {
		super.init();
		button_take = Button.builder(Component.translatable("gui.reign_mod.coffers_ui.button_take"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new CoffersUIButtonMessage(0, x, y, z, textstate));
				CoffersUIButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 61, this.topPos + 60, 54, 20).build();
		guistate.put("button:button_take", button_take);
		this.addRenderableWidget(button_take);
		button_pay_off = Button.builder(Component.translatable("gui.reign_mod.coffers_ui.button_pay_off"), e -> {
			if (CapitalClaimDIsabledProcedure.execute(world, x, y, z)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new CoffersUIButtonMessage(1, x, y, z, textstate));
				CoffersUIButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		}).bounds(this.leftPos + -1, this.topPos + 169, 58, 20).build(builder -> new Button(builder) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (CapitalClaimDIsabledProcedure.execute(world, x, y, z))
					super.render(guiGraphics, gx, gy, ticks);
			}
		});
		guistate.put("button:button_pay_off", button_pay_off);
		this.addRenderableWidget(button_pay_off);
		imagebutton_tab_button = new ImageButton(this.leftPos + -23, this.topPos + 29, 23, 22, 0, 0, 22, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_tab_button.png"), 23, 44, e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new CoffersUIButtonMessage(2, x, y, z, textstate));
				CoffersUIButtonMessage.handleButtonAction(entity, 2, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_tab_button", imagebutton_tab_button);
		this.addRenderableWidget(imagebutton_tab_button);
	}
}
