package net.mcreator.reignmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.reignmod.world.inventory.LicenseIsSelectedMenu;
import net.mcreator.reignmod.procedures.ReturnwoodcutterProcedure;
import net.mcreator.reignmod.procedures.ReturnsmithProcedure;
import net.mcreator.reignmod.procedures.ReturnminerProcedure;
import net.mcreator.reignmod.procedures.ReturnfarmerProcedure;
import net.mcreator.reignmod.procedures.ReturncowboyProcedure;
import net.mcreator.reignmod.procedures.RefuseLockProcedure;
import net.mcreator.reignmod.network.LicenseIsSelectedButtonMessage;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class LicenseIsSelectedScreen extends AbstractContainerScreen<LicenseIsSelectedMenu> {
	private final static HashMap<String, Object> guistate = LicenseIsSelectedMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	Button button_refuse;

	public LicenseIsSelectedScreen(LicenseIsSelectedMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 200;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("reign_mod:textures/screens/license_is_selected.png");

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
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/crown.png"), this.leftPos + 173, this.topPos + -13, 0, 0, 16, 16, 16, 16);

		if (ReturnfarmerProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/farmer.png"), this.leftPos + 90, this.topPos + 61, 0, 0, 16, 16, 16, 16);
		}
		if (ReturnminerProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/miner_logo.png"), this.leftPos + 90, this.topPos + 61, 0, 0, 16, 16, 16, 16);
		}
		if (ReturnsmithProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/smith_logo.png"), this.leftPos + 90, this.topPos + 61, 0, 0, 16, 16, 16, 16);
		}
		if (ReturnwoodcutterProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/woodcutter_logo.png"), this.leftPos + 90, this.topPos + 61, 0, 0, 16, 16, 16, 16);
		}
		if (ReturncowboyProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/cowboy_logo.png"), this.leftPos + 90, this.topPos + 61, 0, 0, 16, 16, 16, 16);
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
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.license_is_selected.label_the_main_license_has_been_select"), 9, 12, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.license_is_selected.label_do_you_want_to_give_it_up"), 10, 28, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.license_is_selected.label_all_experience_will_be_lost"), 31, 142, -3407821, false);
	}

	@Override
	public void init() {
		super.init();
		button_refuse = Button.builder(Component.translatable("gui.reign_mod.license_is_selected.button_refuse"), e -> {
			if (RefuseLockProcedure.execute(entity)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new LicenseIsSelectedButtonMessage(0, x, y, z, textstate));
				LicenseIsSelectedButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 56, this.topPos + 108, 86, 20).build(builder -> new Button(builder) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (RefuseLockProcedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		});
		guistate.put("button:button_refuse", button_refuse);
		this.addRenderableWidget(button_refuse);
	}
}
