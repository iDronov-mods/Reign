package net.mcreator.reignmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.reignmod.world.inventory.DomainUIMenu;
import net.mcreator.reignmod.procedures.ReturnSuspect7ValueProcedure;
import net.mcreator.reignmod.procedures.ReturnSuspect7Procedure;
import net.mcreator.reignmod.procedures.ReturnSuspect6ValueProcedure;
import net.mcreator.reignmod.procedures.ReturnSuspect6Procedure;
import net.mcreator.reignmod.procedures.ReturnSuspect5ValueProcedure;
import net.mcreator.reignmod.procedures.ReturnSuspect5Procedure;
import net.mcreator.reignmod.procedures.ReturnSuspect4ValueProcedure;
import net.mcreator.reignmod.procedures.ReturnSuspect4Procedure;
import net.mcreator.reignmod.procedures.ReturnSuspect3ValueProcedure;
import net.mcreator.reignmod.procedures.ReturnSuspect3Procedure;
import net.mcreator.reignmod.procedures.ReturnSuspect2ValueProcedure;
import net.mcreator.reignmod.procedures.ReturnSuspect2Procedure;
import net.mcreator.reignmod.procedures.ReturnSuspect1ValueProcedure;
import net.mcreator.reignmod.procedures.ReturnSuspect1Procedure;
import net.mcreator.reignmod.procedures.ReturnRobbersProcedure;
import net.mcreator.reignmod.procedures.ReturnDomainPlayersProcedure;
import net.mcreator.reignmod.procedures.ReturnDomainHpProcedure;
import net.mcreator.reignmod.procedures.ReturnDiseaseProcedure;
import net.mcreator.reignmod.procedures.DomainHP66Procedure;
import net.mcreator.reignmod.procedures.DomainHP56Procedure;
import net.mcreator.reignmod.procedures.DomainHP46Procedure;
import net.mcreator.reignmod.procedures.DomainHP36Procedure;
import net.mcreator.reignmod.procedures.DomainHP26Procedure;
import net.mcreator.reignmod.procedures.DomainHP16Procedure;
import net.mcreator.reignmod.network.DomainUIButtonMessage;
import net.mcreator.reignmod.init.ReignModModScreens.WidgetScreen;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class DomainUIScreen extends AbstractContainerScreen<DomainUIMenu> implements WidgetScreen {
	private final static HashMap<String, Object> guistate = DomainUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	ImageButton imagebutton_remove_suspect_button;

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
		if (mouseX > leftPos + 146 && mouseX < leftPos + 157 && mouseY > topPos + 24 && mouseY < topPos + 35)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.domain_ui.tooltip_delete_claim_help"), mouseX, mouseY);
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
		if (ReturnRobbersProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/robbers_logo.png"), this.leftPos + 35, this.topPos + -11, 0, 0, 8, 8, 8, 8);
		}
		if (ReturnDiseaseProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/disease_logo.png"), this.leftPos + 25, this.topPos + -11, 0, 0, 8, 8, 8, 8);
		}
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
		guiGraphics.drawString(this.font,

				ReturnDomainHpProcedure.execute(world, x, y, z), 158, -3, -1, false);
		guiGraphics.drawString(this.font,

				ReturnDomainPlayersProcedure.execute(world, x, y, z), 54, 25, -12829636, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect1Procedure.execute(world, x, y, z), 7, 66, -1, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect1ValueProcedure.execute(world, x, y, z), 111, 66, -52378, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.domain_ui.label_suspects_list_label"), 17, 47, -1, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect2Procedure.execute(world, x, y, z), 7, 79, -1, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect3Procedure.execute(world, x, y, z), 7, 92, -1, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect4Procedure.execute(world, x, y, z), 7, 106, -1, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect5Procedure.execute(world, x, y, z), 7, 119, -1, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect6Procedure.execute(world, x, y, z), 7, 132, -1, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect7Procedure.execute(world, x, y, z), 7, 145, -1, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect2ValueProcedure.execute(world, x, y, z), 111, 79, -52378, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect3ValueProcedure.execute(world, x, y, z), 111, 92, -52378, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect4ValueProcedure.execute(world, x, y, z), 111, 107, -52378, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect5ValueProcedure.execute(world, x, y, z), 111, 119, -52378, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect6ValueProcedure.execute(world, x, y, z), 111, 133, -52378, false);
		guiGraphics.drawString(this.font,

				ReturnSuspect7ValueProcedure.execute(world, x, y, z), 111, 145, -52378, false);
	}

	@Override
	public void init() {
		super.init();
		imagebutton_remove_suspect_button = new ImageButton(this.leftPos + 147, this.topPos + 26, 9, 7, 0, 0, 7, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_remove_suspect_button.png"), 9, 14, e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new DomainUIButtonMessage(0, x, y, z, textstate));
				DomainUIButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_remove_suspect_button", imagebutton_remove_suspect_button);
		this.addRenderableWidget(imagebutton_remove_suspect_button);
	}
}
