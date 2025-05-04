package net.mcreator.reignmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.reignmod.world.inventory.HouseIncubatorUIMenu;
import net.mcreator.reignmod.procedures.NotHaveWoolProcedure;
import net.mcreator.reignmod.procedures.NotHaveSweetsProcedure;
import net.mcreator.reignmod.procedures.NotHaveRootsProcedure;
import net.mcreator.reignmod.procedures.NotHaveMeatProcedure;
import net.mcreator.reignmod.procedures.NotHaveJewelryProcedure;
import net.mcreator.reignmod.procedures.NotHaveFuelProcedure;
import net.mcreator.reignmod.procedures.NotHaveBreadProcedure;
import net.mcreator.reignmod.procedures.Needwool55Procedure;
import net.mcreator.reignmod.procedures.Needwool45Procedure;
import net.mcreator.reignmod.procedures.Needwool35Procedure;
import net.mcreator.reignmod.procedures.Needwool25Procedure;
import net.mcreator.reignmod.procedures.Needwool15Procedure;
import net.mcreator.reignmod.procedures.Needsweets55Procedure;
import net.mcreator.reignmod.procedures.Needsweets45Procedure;
import net.mcreator.reignmod.procedures.Needsweets35Procedure;
import net.mcreator.reignmod.procedures.Needsweets25Procedure;
import net.mcreator.reignmod.procedures.Needsweets15Procedure;
import net.mcreator.reignmod.procedures.Needroots55Procedure;
import net.mcreator.reignmod.procedures.Needroots45Procedure;
import net.mcreator.reignmod.procedures.Needroots35Procedure;
import net.mcreator.reignmod.procedures.Needroots25Procedure;
import net.mcreator.reignmod.procedures.Needroots15Procedure;
import net.mcreator.reignmod.procedures.Needmeat55Procedure;
import net.mcreator.reignmod.procedures.Needmeat45Procedure;
import net.mcreator.reignmod.procedures.Needmeat35Procedure;
import net.mcreator.reignmod.procedures.Needmeat25Procedure;
import net.mcreator.reignmod.procedures.Needmeat15Procedure;
import net.mcreator.reignmod.procedures.Needjewelry55Procedure;
import net.mcreator.reignmod.procedures.Needjewelry45Procedure;
import net.mcreator.reignmod.procedures.Needjewelry35Procedure;
import net.mcreator.reignmod.procedures.Needjewelry25Procedure;
import net.mcreator.reignmod.procedures.Needjewelry15Procedure;
import net.mcreator.reignmod.procedures.Needfuel55Procedure;
import net.mcreator.reignmod.procedures.Needfuel45Procedure;
import net.mcreator.reignmod.procedures.Needfuel35Procedure;
import net.mcreator.reignmod.procedures.Needfuel25Procedure;
import net.mcreator.reignmod.procedures.Needfuel15Procedure;
import net.mcreator.reignmod.procedures.Needbread55Procedure;
import net.mcreator.reignmod.procedures.Needbread45Procedure;
import net.mcreator.reignmod.procedures.Needbread35Procedure;
import net.mcreator.reignmod.procedures.Needbread25Procedure;
import net.mcreator.reignmod.procedures.Needbread15Procedure;
import net.mcreator.reignmod.procedures.IncubatorWoolHelpProcedure;
import net.mcreator.reignmod.procedures.IncubatorSweatsHelpProcedure;
import net.mcreator.reignmod.procedures.IncubatorRootsHelpProcedure;
import net.mcreator.reignmod.procedures.IncubatorReturnDomainsProcedure;
import net.mcreator.reignmod.procedures.IncubatorReturnDomainPlayersProcedure;
import net.mcreator.reignmod.procedures.IncubatorReturnAllPlayersProcedure;
import net.mcreator.reignmod.procedures.IncubatorMeatHelpProcedure;
import net.mcreator.reignmod.procedures.IncubatorJewelryHelpProcedure;
import net.mcreator.reignmod.procedures.IncubatorHouseLevelProcedure;
import net.mcreator.reignmod.procedures.IncubatorCoalHelpProcedure;
import net.mcreator.reignmod.procedures.IncubatorBreadHelpProcedure;
import net.mcreator.reignmod.procedures.HouseHPValueProcedure;
import net.mcreator.reignmod.procedures.HouseHP9Procedure;
import net.mcreator.reignmod.procedures.HouseHP8Procedure;
import net.mcreator.reignmod.procedures.HouseHP7Procedure;
import net.mcreator.reignmod.procedures.HouseHP6Procedure;
import net.mcreator.reignmod.procedures.HouseHP5Procedure;
import net.mcreator.reignmod.procedures.HouseHP4Procedure;
import net.mcreator.reignmod.procedures.HouseHP3Procedure;
import net.mcreator.reignmod.procedures.HouseHP2Procedure;
import net.mcreator.reignmod.procedures.HouseHP1Procedure;
import net.mcreator.reignmod.procedures.HouseHP10Procedure;
import net.mcreator.reignmod.procedures.HaveWoolProcedure;
import net.mcreator.reignmod.procedures.HaveSweetsProcedure;
import net.mcreator.reignmod.procedures.HaveRootsProcedure;
import net.mcreator.reignmod.procedures.HaveMeatProcedure;
import net.mcreator.reignmod.procedures.HaveJewelryProcedure;
import net.mcreator.reignmod.procedures.HaveFuelProcedure;
import net.mcreator.reignmod.procedures.HaveBreadProcedure;
import net.mcreator.reignmod.network.HouseIncubatorUIButtonMessage;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class HouseIncubatorUIScreen extends AbstractContainerScreen<HouseIncubatorUIMenu> {
	private final static HashMap<String, Object> guistate = HouseIncubatorUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	ImageButton imagebutton_tab_button;
	ImageButton imagebutton_remove_suspect_button;

	public HouseIncubatorUIScreen(HouseIncubatorUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 300;
		this.imageHeight = 166;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 177 && mouseX < leftPos + 194 && mouseY > topPos + 12 && mouseY < topPos + 23)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.house_incubator_ui.tooltip_incubator_label_help"), mouseX, mouseY);
		if (mouseX > leftPos + 70 && mouseX < leftPos + 86 && mouseY > topPos + 62 && mouseY < topPos + 78)
			guiGraphics.renderTooltip(font, Component.literal(IncubatorCoalHelpProcedure.execute(world, x, y, z)), mouseX, mouseY);
		if (mouseX > leftPos + 94 && mouseX < leftPos + 110 && mouseY > topPos + 62 && mouseY < topPos + 78)
			guiGraphics.renderTooltip(font, Component.literal(IncubatorBreadHelpProcedure.execute(world, x, y, z)), mouseX, mouseY);
		if (mouseX > leftPos + 118 && mouseX < leftPos + 134 && mouseY > topPos + 62 && mouseY < topPos + 78)
			guiGraphics.renderTooltip(font, Component.literal(IncubatorRootsHelpProcedure.execute(world, x, y, z)), mouseX, mouseY);
		if (mouseX > leftPos + 142 && mouseX < leftPos + 158 && mouseY > topPos + 62 && mouseY < topPos + 78)
			guiGraphics.renderTooltip(font, Component.literal(IncubatorMeatHelpProcedure.execute(world, x, y, z)), mouseX, mouseY);
		if (mouseX > leftPos + 166 && mouseX < leftPos + 182 && mouseY > topPos + 62 && mouseY < topPos + 78)
			guiGraphics.renderTooltip(font, Component.literal(IncubatorWoolHelpProcedure.execute(world, x, y, z)), mouseX, mouseY);
		if (mouseX > leftPos + 190 && mouseX < leftPos + 206 && mouseY > topPos + 62 && mouseY < topPos + 78)
			guiGraphics.renderTooltip(font, Component.literal(IncubatorSweatsHelpProcedure.execute(world, x, y, z)), mouseX, mouseY);
		if (mouseX > leftPos + 214 && mouseX < leftPos + 230 && mouseY > topPos + 62 && mouseY < topPos + 78)
			guiGraphics.renderTooltip(font, Component.literal(IncubatorJewelryHelpProcedure.execute(world, x, y, z)), mouseX, mouseY);
		if (mouseX > leftPos + 63 && mouseX < leftPos + 71 && mouseY > topPos + 30 && mouseY < topPos + 41)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.house_incubator_ui.tooltip_domain_players"), mouseX, mouseY);
		if (mouseX > leftPos + 118 && mouseX < leftPos + 127 && mouseY > topPos + 30 && mouseY < topPos + 40)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.house_incubator_ui.tooltip_domains_help"), mouseX, mouseY);
		if (mouseX > leftPos + 177 && mouseX < leftPos + 193 && mouseY > topPos + 31 && mouseY < topPos + 40)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.house_incubator_ui.tooltip_all_players_help"), mouseX, mouseY);
		if (mouseX > leftPos + 248 && mouseX < leftPos + 259 && mouseY > topPos + 29 && mouseY < topPos + 40)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.house_incubator_ui.tooltip_delete_claim_help"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/incubatorui.png"), this.leftPos + 0, this.topPos + -17, 0, 0, 300, 200, 300, 200);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/crown.png"), this.leftPos + 177, this.topPos + 11, 0, 0, 16, 16, 16, 16);

		if (HouseHP1Procedure.execute(world, x, y, z, entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 45, this.topPos + 6, 0, 0, 20, 4, 20, 4);
		}
		if (HouseHP2Procedure.execute(world, x, y, z, entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 66, this.topPos + 6, 0, 0, 20, 4, 20, 4);
		}
		if (HouseHP3Procedure.execute(world, x, y, z, entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 87, this.topPos + 6, 0, 0, 20, 4, 20, 4);
		}
		if (HouseHP4Procedure.execute(world, x, y, z, entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 108, this.topPos + 6, 0, 0, 20, 4, 20, 4);
		}
		if (HouseHP5Procedure.execute(world, x, y, z, entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 129, this.topPos + 6, 0, 0, 20, 4, 20, 4);
		}
		if (HouseHP6Procedure.execute(world, x, y, z, entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 150, this.topPos + 6, 0, 0, 20, 4, 20, 4);
		}
		if (HouseHP7Procedure.execute(world, x, y, z, entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 171, this.topPos + 6, 0, 0, 20, 4, 20, 4);
		}
		if (HouseHP8Procedure.execute(world, x, y, z, entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 192, this.topPos + 6, 0, 0, 20, 4, 20, 4);
		}
		if (HouseHP9Procedure.execute(world, x, y, z, entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 213, this.topPos + 6, 0, 0, 20, 4, 20, 4);
		}
		if (HouseHP10Procedure.execute(world, x, y, z, entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/house_hp_point.png"), this.leftPos + 234, this.topPos + 6, 0, 0, 20, 4, 20, 4);
		}
		if (Needfuel15Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_1-5.png"), this.leftPos + 68, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needfuel25Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_2-5.png"), this.leftPos + 68, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needfuel35Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_3-5.png"), this.leftPos + 68, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needfuel45Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_4-5.png"), this.leftPos + 68, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needfuel55Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_5-5.png"), this.leftPos + 68, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (NotHaveFuelProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/incubator_need_not_have.png"), this.leftPos + 77, this.topPos + 79, 0, 0, 2, 1, 2, 1);
		}
		if (HaveFuelProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/incubator_need_have.png"), this.leftPos + 77, this.topPos + 79, 0, 0, 2, 1, 2, 1);
		}
		if (Needbread15Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_1-5.png"), this.leftPos + 92, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needbread25Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_2-5.png"), this.leftPos + 92, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needbread35Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_3-5.png"), this.leftPos + 92, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needbread45Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_4-5.png"), this.leftPos + 92, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needbread55Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_5-5.png"), this.leftPos + 92, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (NotHaveBreadProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/incubator_need_not_have.png"), this.leftPos + 101, this.topPos + 79, 0, 0, 2, 1, 2, 1);
		}
		if (HaveBreadProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/incubator_need_have.png"), this.leftPos + 101, this.topPos + 79, 0, 0, 2, 1, 2, 1);
		}
		if (Needroots15Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_1-5.png"), this.leftPos + 116, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needroots25Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_2-5.png"), this.leftPos + 116, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needroots35Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_3-5.png"), this.leftPos + 116, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needroots45Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_4-5.png"), this.leftPos + 116, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needroots55Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_5-5.png"), this.leftPos + 116, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (NotHaveRootsProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/incubator_need_not_have.png"), this.leftPos + 125, this.topPos + 79, 0, 0, 2, 1, 2, 1);
		}
		if (HaveRootsProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/incubator_need_have.png"), this.leftPos + 125, this.topPos + 79, 0, 0, 2, 1, 2, 1);
		}
		if (Needmeat15Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_1-5.png"), this.leftPos + 140, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needmeat25Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_2-5.png"), this.leftPos + 140, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needmeat35Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_3-5.png"), this.leftPos + 140, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needmeat45Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_4-5.png"), this.leftPos + 140, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needmeat55Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_5-5.png"), this.leftPos + 140, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (NotHaveMeatProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/incubator_need_not_have.png"), this.leftPos + 149, this.topPos + 79, 0, 0, 2, 1, 2, 1);
		}
		if (HaveMeatProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/incubator_need_have.png"), this.leftPos + 149, this.topPos + 79, 0, 0, 2, 1, 2, 1);
		}
		if (Needwool15Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_1-5.png"), this.leftPos + 164, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needwool25Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_2-5.png"), this.leftPos + 164, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needwool35Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_3-5.png"), this.leftPos + 164, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needwool45Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_4-5.png"), this.leftPos + 164, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needwool55Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_5-5.png"), this.leftPos + 164, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (NotHaveWoolProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/incubator_need_not_have.png"), this.leftPos + 173, this.topPos + 79, 0, 0, 2, 1, 2, 1);
		}
		if (HaveWoolProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/incubator_need_have.png"), this.leftPos + 173, this.topPos + 79, 0, 0, 2, 1, 2, 1);
		}
		if (Needsweets15Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_1-5.png"), this.leftPos + 188, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needsweets25Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_2-5.png"), this.leftPos + 188, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needsweets35Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_3-5.png"), this.leftPos + 188, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needsweets45Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_4-5.png"), this.leftPos + 188, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needsweets55Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_5-5.png"), this.leftPos + 188, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (NotHaveSweetsProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/incubator_need_not_have.png"), this.leftPos + 197, this.topPos + 79, 0, 0, 2, 1, 2, 1);
		}
		if (HaveSweetsProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/incubator_need_have.png"), this.leftPos + 197, this.topPos + 79, 0, 0, 2, 1, 2, 1);
		}
		if (Needjewelry15Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_1-5.png"), this.leftPos + 212, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needjewelry25Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_2-5.png"), this.leftPos + 212, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needjewelry35Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_3-5.png"), this.leftPos + 212, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needjewelry45Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_4-5.png"), this.leftPos + 212, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (Needjewelry55Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/need_5-5.png"), this.leftPos + 212, this.topPos + 60, 0, 0, 19, 19, 19, 19);
		}
		if (NotHaveJewelryProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/incubator_need_not_have.png"), this.leftPos + 221, this.topPos + 79, 0, 0, 2, 1, 2, 1);
		}
		if (HaveJewelryProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/incubator_need_have.png"), this.leftPos + 221, this.topPos + 79, 0, 0, 2, 1, 2, 1);
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
	public void containerTick() {
		super.containerTick();
		ReignModMod.PACKET_HANDLER.sendToServer(new HouseIncubatorUIMenu.HouseIncubatorUIOtherMessage(0, x, y, z, textstate));
		HouseIncubatorUIMenu.HouseIncubatorUIOtherMessage.handleOtherAction(entity, 0, x, y, z, textstate);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.house_incubator_ui.label_incubator"), 41, -7, -1, false);
		guiGraphics.drawString(this.font,

				HouseHPValueProcedure.execute(world, x, y, z, entity), 260, 3, -1, false);
		guiGraphics.drawString(this.font,

				IncubatorReturnDomainPlayersProcedure.execute(world, x, y, z), 73, 32, -13421773, false);
		guiGraphics.drawString(this.font,

				IncubatorReturnDomainsProcedure.execute(world, x, y, z), 130, 32, -13421773, false);
		guiGraphics.drawString(this.font,

				IncubatorReturnAllPlayersProcedure.execute(world, x, y, z), 198, 32, -12829636, false);
		guiGraphics.drawString(this.font,

				IncubatorHouseLevelProcedure.execute(world, x, y, z), 178, -7, -1, false);
	}

	@Override
	public void init() {
		super.init();
		imagebutton_tab_button = new ImageButton(this.leftPos + 33, this.topPos + 112, 23, 22, 0, 0, 22, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_tab_button.png"), 23, 44, e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new HouseIncubatorUIButtonMessage(0, x, y, z, textstate));
				HouseIncubatorUIButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_tab_button", imagebutton_tab_button);
		this.addRenderableWidget(imagebutton_tab_button);
		imagebutton_remove_suspect_button = new ImageButton(this.leftPos + 249, this.topPos + 31, 9, 7, 0, 0, 7, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_remove_suspect_button.png"), 9, 14, e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new HouseIncubatorUIButtonMessage(1, x, y, z, textstate));
				HouseIncubatorUIButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_remove_suspect_button", imagebutton_remove_suspect_button);
		this.addRenderableWidget(imagebutton_remove_suspect_button);
	}
}
