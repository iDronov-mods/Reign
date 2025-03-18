package net.mcreator.reignmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.reignmod.world.inventory.LicensesWindowMenu;
import net.mcreator.reignmod.procedures.ReturnwoodcutterProcedure;
import net.mcreator.reignmod.procedures.ReturnsoldierProcedure;
import net.mcreator.reignmod.procedures.ReturnsmithProcedure;
import net.mcreator.reignmod.procedures.ReturnminerProcedure;
import net.mcreator.reignmod.procedures.ReturnlibrarianProcedure;
import net.mcreator.reignmod.procedures.ReturnhunterProcedure;
import net.mcreator.reignmod.procedures.ReturnfisherProcedure;
import net.mcreator.reignmod.procedures.ReturnfarmerProcedure;
import net.mcreator.reignmod.procedures.ReturncowboyProcedure;
import net.mcreator.reignmod.procedures.ReturnalchemistProcedure;
import net.mcreator.reignmod.procedures.ReturnReverseNotWoodcutterProcedure;
import net.mcreator.reignmod.procedures.ReturnReverseNotSoldierProcedure;
import net.mcreator.reignmod.procedures.ReturnReverseNotSmithProcedure;
import net.mcreator.reignmod.procedures.ReturnReverseNotMinerProcedure;
import net.mcreator.reignmod.procedures.ReturnReverseNotHunterProcedure;
import net.mcreator.reignmod.procedures.ReturnReverseNotFisherProcedure;
import net.mcreator.reignmod.procedures.ReturnReverseNotFarmerProcedure;
import net.mcreator.reignmod.procedures.ReturnReverseNotEnchanterProcedure;
import net.mcreator.reignmod.procedures.ReturnReverseNotCowboyProcedure;
import net.mcreator.reignmod.procedures.ReturnReverseNotAlchemistProcedure;
import net.mcreator.reignmod.procedures.ReturnNotWoodcutterProcedure;
import net.mcreator.reignmod.procedures.ReturnNotSoldierProcedure;
import net.mcreator.reignmod.procedures.ReturnNotSmithProcedure;
import net.mcreator.reignmod.procedures.ReturnNotMinerProcedure;
import net.mcreator.reignmod.procedures.ReturnNotHunterProcedure;
import net.mcreator.reignmod.procedures.ReturnNotFisherProcedure;
import net.mcreator.reignmod.procedures.ReturnNotFarmerProcedure;
import net.mcreator.reignmod.procedures.ReturnNotEnchanterProcedure;
import net.mcreator.reignmod.procedures.ReturnNotCowboyProcedure;
import net.mcreator.reignmod.procedures.ReturnNotAlchemistProcedure;
import net.mcreator.reignmod.procedures.ReturnMeProcedure;
import net.mcreator.reignmod.procedures.Mlvl5Procedure;
import net.mcreator.reignmod.procedures.Mlvl4Procedure;
import net.mcreator.reignmod.procedures.Mlvl3Procedure;
import net.mcreator.reignmod.procedures.Mlvl2Procedure;
import net.mcreator.reignmod.procedures.Mlvl1Procedure;
import net.mcreator.reignmod.procedures.MainButtonUpProcedure;
import net.mcreator.reignmod.procedures.Alvl5Procedure;
import net.mcreator.reignmod.procedures.Alvl4Procedure;
import net.mcreator.reignmod.procedures.Alvl3Procedure;
import net.mcreator.reignmod.procedures.Alvl2Procedure;
import net.mcreator.reignmod.procedures.Alvl1Procedure;
import net.mcreator.reignmod.procedures.AddButtonUpProcedure;
import net.mcreator.reignmod.network.LicensesWindowButtonMessage;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class LicensesWindowScreen extends AbstractContainerScreen<LicensesWindowMenu> {
	private final static HashMap<String, Object> guistate = LicensesWindowMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	ImageButton imagebutton_icon_license_slot;
	ImageButton imagebutton_icon_license_slot1;
	ImageButton imagebutton_icon_license_slot2;
	ImageButton imagebutton_icon_license_slot3;
	ImageButton imagebutton_icon_license_slot4;
	ImageButton imagebutton_icon_license_slot5;
	ImageButton imagebutton_icon_license_slot6;
	ImageButton imagebutton_icon_license_slot7;
	ImageButton imagebutton_icon_license_slot8;
	ImageButton imagebutton_icon_license_slot9;

	public LicensesWindowScreen(LicensesWindowMenu container, Inventory inventory, Component text) {
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
		if (ReturnMeProcedure.execute(entity) instanceof LivingEntity livingEntity) {
			InventoryScreen.renderEntityInInventoryFollowsAngle(guiGraphics, this.leftPos + 87, this.topPos + 154, 60, 0f + (float) Math.atan((this.leftPos + 87 - mouseX) / 40.0), (float) Math.atan((this.topPos + 105 - mouseY) / 40.0), livingEntity);
		}
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/licensesui.png"), this.leftPos + -63, this.topPos + -17, 0, 0, 300, 200, 300, 200);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/hunter_logo.png"), this.leftPos + 142, this.topPos + 155, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/farmer.png"), this.leftPos + -26, this.topPos + 117, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/miner_logo.png"), this.leftPos + -26, this.topPos + 33, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/cowboy_logo.png"), this.leftPos + 16, this.topPos + 155, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/enchanter_logo.png"), this.leftPos + 198, this.topPos + 75, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/soldier_logo.png"), this.leftPos + 184, this.topPos + 117, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/woodcutter_logo.png"), this.leftPos + 16, this.topPos + -5, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/smith_logo.png"), this.leftPos + -40, this.topPos + 75, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/alchemist_logo.png"), this.leftPos + 184, this.topPos + 33, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/fisher_logo.png"), this.leftPos + 142, this.topPos + -5, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/licensessmallwin.png"), this.leftPos + 46, this.topPos + -8, 0, 0, 82, 22, 82, 22);

		if (Mlvl1Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_first.png"), this.leftPos + 58, this.topPos + -6, 0, 0, 6, 6, 6, 6);
		}
		if (Mlvl2Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/small_lvl_point_next.png"), this.leftPos + 64, this.topPos + -6, 0, 0, 16, 6, 16, 6);
		}
		if (Mlvl3Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/small_lvl_point_next.png"), this.leftPos + 80, this.topPos + -6, 0, 0, 16, 6, 16, 6);
		}
		if (Mlvl4Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/small_lvl_point_next.png"), this.leftPos + 96, this.topPos + -6, 0, 0, 16, 6, 16, 6);
		}
		if (Mlvl5Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/small_lvl_point_next.png"), this.leftPos + 112, this.topPos + -6, 0, 0, 16, 6, 16, 6);
		}
		if (Alvl1Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_first.png"), this.leftPos + 58, this.topPos + 6, 0, 0, 6, 6, 6, 6);
		}
		if (Alvl2Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/small_lvl_point_next.png"), this.leftPos + 64, this.topPos + 6, 0, 0, 16, 6, 16, 6);
		}
		if (Alvl3Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/small_lvl_point_next.png"), this.leftPos + 80, this.topPos + 6, 0, 0, 16, 6, 16, 6);
		}
		if (Alvl4Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/small_lvl_point_next.png"), this.leftPos + 96, this.topPos + 6, 0, 0, 16, 6, 16, 6);
		}
		if (Alvl5Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/small_lvl_point_next.png"), this.leftPos + 112, this.topPos + 6, 0, 0, 16, 6, 16, 6);
		}
		if (ReturnhunterProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/hunter_logo.png"), this.leftPos + 47, this.topPos + 5, 0, 0, 8, 8, 8, 8);
		}
		if (ReturnsoldierProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/soldier_logo.png"), this.leftPos + 47, this.topPos + 5, 0, 0, 8, 8, 8, 8);
		}
		if (ReturnalchemistProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/alchemist_logo.png"), this.leftPos + 47, this.topPos + 5, 0, 0, 8, 8, 8, 8);
		}
		if (ReturnlibrarianProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/enchanter_logo.png"), this.leftPos + 47, this.topPos + 5, 0, 0, 8, 8, 8, 8);
		}
		if (ReturnfisherProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/fisher_logo.png"), this.leftPos + 47, this.topPos + 5, 0, 0, 8, 8, 8, 8);
		}
		if (ReturnfarmerProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/farmer.png"), this.leftPos + 47, this.topPos + -7, 0, 0, 8, 8, 8, 8);
		}
		if (ReturnsmithProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/smith_logo.png"), this.leftPos + 47, this.topPos + -7, 0, 0, 8, 8, 8, 8);
		}
		if (ReturncowboyProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/cowboy_logo.png"), this.leftPos + 47, this.topPos + -7, 0, 0, 8, 8, 8, 8);
		}
		if (ReturnminerProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/miner_logo.png"), this.leftPos + 47, this.topPos + -7, 0, 0, 8, 8, 8, 8);
		}
		if (ReturnwoodcutterProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/woodcutter_logo.png"), this.leftPos + 47, this.topPos + -7, 0, 0, 8, 8, 8, 8);
		}
		if (ReturnNotWoodcutterProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/icon_license_closed.png"), this.leftPos + 16, this.topPos + -5, 0, 0, 16, 16, 16, 16);
		}
		if (ReturnNotMinerProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/icon_license_closed.png"), this.leftPos + -26, this.topPos + 33, 0, 0, 16, 16, 16, 16);
		}
		if (ReturnNotSmithProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/icon_license_closed.png"), this.leftPos + -40, this.topPos + 75, 0, 0, 16, 16, 16, 16);
		}
		if (ReturnNotFarmerProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/icon_license_closed.png"), this.leftPos + -26, this.topPos + 117, 0, 0, 16, 16, 16, 16);
		}
		if (ReturnNotCowboyProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/icon_license_closed.png"), this.leftPos + 16, this.topPos + 155, 0, 0, 16, 16, 16, 16);
		}
		if (ReturnNotFisherProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/icon_license_closed.png"), this.leftPos + 142, this.topPos + -5, 0, 0, 16, 16, 16, 16);
		}
		if (ReturnNotAlchemistProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/icon_license_closed.png"), this.leftPos + 184, this.topPos + 33, 0, 0, 16, 16, 16, 16);
		}
		if (ReturnNotEnchanterProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/icon_license_closed.png"), this.leftPos + 198, this.topPos + 75, 0, 0, 16, 16, 16, 16);
		}
		if (ReturnNotSoldierProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/icon_license_closed.png"), this.leftPos + 184, this.topPos + 118, 0, 0, 16, 16, 16, 16);
		}
		if (ReturnNotHunterProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/icon_license_closed.png"), this.leftPos + 142, this.topPos + 155, 0, 0, 16, 16, 16, 16);
		}
		if (MainButtonUpProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_up_button.png"), this.leftPos + 47, this.topPos + -7, 0, 0, 8, 8, 8, 8);
		}
		if (AddButtonUpProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_up_button.png"), this.leftPos + 47, this.topPos + 5, 0, 0, 8, 8, 8, 8);
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
	}

	@Override
	public void init() {
		super.init();
		imagebutton_icon_license_slot = new ImageButton(this.leftPos + -28, this.topPos + 31, 20, 20, 0, 0, 20, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_icon_license_slot.png"), 20, 40, e -> {
			if (ReturnReverseNotMinerProcedure.execute(entity)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new LicensesWindowButtonMessage(0, x, y, z, textstate));
				LicensesWindowButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (ReturnReverseNotMinerProcedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_icon_license_slot", imagebutton_icon_license_slot);
		this.addRenderableWidget(imagebutton_icon_license_slot);
		imagebutton_icon_license_slot1 = new ImageButton(this.leftPos + 14, this.topPos + -7, 20, 20, 0, 0, 20, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_icon_license_slot1.png"), 20, 40, e -> {
			if (ReturnReverseNotWoodcutterProcedure.execute(entity)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new LicensesWindowButtonMessage(1, x, y, z, textstate));
				LicensesWindowButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (ReturnReverseNotWoodcutterProcedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_icon_license_slot1", imagebutton_icon_license_slot1);
		this.addRenderableWidget(imagebutton_icon_license_slot1);
		imagebutton_icon_license_slot2 = new ImageButton(this.leftPos + -42, this.topPos + 73, 20, 20, 0, 0, 20, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_icon_license_slot2.png"), 20, 40, e -> {
			if (ReturnReverseNotSmithProcedure.execute(entity)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new LicensesWindowButtonMessage(2, x, y, z, textstate));
				LicensesWindowButtonMessage.handleButtonAction(entity, 2, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (ReturnReverseNotSmithProcedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_icon_license_slot2", imagebutton_icon_license_slot2);
		this.addRenderableWidget(imagebutton_icon_license_slot2);
		imagebutton_icon_license_slot3 = new ImageButton(this.leftPos + -28, this.topPos + 115, 20, 20, 0, 0, 20, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_icon_license_slot3.png"), 20, 40, e -> {
			if (ReturnReverseNotFarmerProcedure.execute(entity)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new LicensesWindowButtonMessage(3, x, y, z, textstate));
				LicensesWindowButtonMessage.handleButtonAction(entity, 3, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (ReturnReverseNotFarmerProcedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_icon_license_slot3", imagebutton_icon_license_slot3);
		this.addRenderableWidget(imagebutton_icon_license_slot3);
		imagebutton_icon_license_slot4 = new ImageButton(this.leftPos + 14, this.topPos + 153, 20, 20, 0, 0, 20, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_icon_license_slot4.png"), 20, 40, e -> {
			if (ReturnReverseNotCowboyProcedure.execute(entity)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new LicensesWindowButtonMessage(4, x, y, z, textstate));
				LicensesWindowButtonMessage.handleButtonAction(entity, 4, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (ReturnReverseNotCowboyProcedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_icon_license_slot4", imagebutton_icon_license_slot4);
		this.addRenderableWidget(imagebutton_icon_license_slot4);
		imagebutton_icon_license_slot5 = new ImageButton(this.leftPos + 182, this.topPos + 31, 20, 20, 0, 0, 20, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_icon_license_slot5.png"), 20, 40, e -> {
			if (ReturnReverseNotAlchemistProcedure.execute(entity)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new LicensesWindowButtonMessage(5, x, y, z, textstate));
				LicensesWindowButtonMessage.handleButtonAction(entity, 5, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (ReturnReverseNotAlchemistProcedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_icon_license_slot5", imagebutton_icon_license_slot5);
		this.addRenderableWidget(imagebutton_icon_license_slot5);
		imagebutton_icon_license_slot6 = new ImageButton(this.leftPos + 196, this.topPos + 73, 20, 20, 0, 0, 20, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_icon_license_slot6.png"), 20, 40, e -> {
			if (ReturnReverseNotEnchanterProcedure.execute(entity)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new LicensesWindowButtonMessage(6, x, y, z, textstate));
				LicensesWindowButtonMessage.handleButtonAction(entity, 6, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (ReturnReverseNotEnchanterProcedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_icon_license_slot6", imagebutton_icon_license_slot6);
		this.addRenderableWidget(imagebutton_icon_license_slot6);
		imagebutton_icon_license_slot7 = new ImageButton(this.leftPos + 182, this.topPos + 115, 20, 20, 0, 0, 20, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_icon_license_slot7.png"), 20, 40, e -> {
			if (ReturnReverseNotSoldierProcedure.execute(entity)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new LicensesWindowButtonMessage(7, x, y, z, textstate));
				LicensesWindowButtonMessage.handleButtonAction(entity, 7, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (ReturnReverseNotSoldierProcedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_icon_license_slot7", imagebutton_icon_license_slot7);
		this.addRenderableWidget(imagebutton_icon_license_slot7);
		imagebutton_icon_license_slot8 = new ImageButton(this.leftPos + 140, this.topPos + 153, 20, 20, 0, 0, 20, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_icon_license_slot8.png"), 20, 40, e -> {
			if (ReturnReverseNotHunterProcedure.execute(entity)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new LicensesWindowButtonMessage(8, x, y, z, textstate));
				LicensesWindowButtonMessage.handleButtonAction(entity, 8, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (ReturnReverseNotHunterProcedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_icon_license_slot8", imagebutton_icon_license_slot8);
		this.addRenderableWidget(imagebutton_icon_license_slot8);
		imagebutton_icon_license_slot9 = new ImageButton(this.leftPos + 140, this.topPos + -7, 20, 20, 0, 0, 20, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_icon_license_slot9.png"), 20, 40, e -> {
			if (ReturnReverseNotFisherProcedure.execute(entity)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new LicensesWindowButtonMessage(9, x, y, z, textstate));
				LicensesWindowButtonMessage.handleButtonAction(entity, 9, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (ReturnReverseNotFisherProcedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_icon_license_slot9", imagebutton_icon_license_slot9);
		this.addRenderableWidget(imagebutton_icon_license_slot9);
	}
}
