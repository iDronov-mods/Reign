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

import net.mcreator.reignmod.world.inventory.WinViewMenu;
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
import net.mcreator.reignmod.procedures.ReturnAddLicenseLockProcedure;
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
import net.mcreator.reignmod.network.WinViewButtonMessage;
import net.mcreator.reignmod.ReignModMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class WinViewScreen extends AbstractContainerScreen<WinViewMenu> {
	private final static HashMap<String, Object> guistate = WinViewMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_main_license;
	Button button_minor_license;
	ImageButton imagebutton_lvl_up_button;
	ImageButton imagebutton_lvl_up_button1;

	public WinViewScreen(WinViewMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 152;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("reign_mod:textures/screens/win_view.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 11 && mouseX < leftPos + 35 && mouseY > topPos + -15 && mouseY < topPos + 9)
			guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_you_can_have_up_to_two_licenses"), mouseX, mouseY);
		if (ReturnwoodcutterProcedure.execute(entity))
			if (mouseX > leftPos + 35 && mouseX < leftPos + 41 && mouseY > topPos + 30 && mouseY < topPos + 36)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_25_chance_to_not_damage_the_axe"), mouseX, mouseY);
		if (ReturnwoodcutterProcedure.execute(entity))
			if (mouseX > leftPos + 83 && mouseX < leftPos + 89 && mouseY > topPos + 30 && mouseY < topPos + 36)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_20_additional_drops"), mouseX, mouseY);
		if (ReturnwoodcutterProcedure.execute(entity))
			if (mouseX > leftPos + 131 && mouseX < leftPos + 137 && mouseY > topPos + 31 && mouseY < topPos + 36)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_another_20_additional_drops"), mouseX, mouseY);
		if (ReturnwoodcutterProcedure.execute(entity))
			if (mouseX > leftPos + 59 && mouseX < leftPos + 65 && mouseY > topPos + 30 && mouseY < topPos + 36)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_1_to_axe_damage"), mouseX, mouseY);
		if (ReturnwoodcutterProcedure.execute(entity))
			if (mouseX > leftPos + 107 && mouseX < leftPos + 113 && mouseY > topPos + 30 && mouseY < topPos + 36)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_10_chance_to_gain_a_chopping_sp"), mouseX, mouseY);
		if (ReturnminerProcedure.execute(entity))
			if (mouseX > leftPos + 35 && mouseX < leftPos + 41 && mouseY > topPos + 30 && mouseY < topPos + 36)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_25_no_damage_to_pickaxe"), mouseX, mouseY);
		if (ReturnminerProcedure.execute(entity))
			if (mouseX > leftPos + 59 && mouseX < leftPos + 65 && mouseY > topPos + 30 && mouseY < topPos + 36)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_chance_to_get_a_haste_effect"), mouseX, mouseY);
		if (ReturnminerProcedure.execute(entity))
			if (mouseX > leftPos + 83 && mouseX < leftPos + 89 && mouseY > topPos + 30 && mouseY < topPos + 36)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_20_additional_drop"), mouseX, mouseY);
		if (ReturnminerProcedure.execute(entity))
			if (mouseX > leftPos + 107 && mouseX < leftPos + 113 && mouseY > topPos + 30 && mouseY < topPos + 36)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_luck_in_mining_precious_ores"), mouseX, mouseY);
		if (ReturnminerProcedure.execute(entity))
			if (mouseX > leftPos + 131 && mouseX < leftPos + 137 && mouseY > topPos + 30 && mouseY < topPos + 36)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_netherite_mining"), mouseX, mouseY);
		if (ReturnfarmerProcedure.execute(entity))
			if (mouseX > leftPos + 35 && mouseX < leftPos + 41 && mouseY > topPos + 30 && mouseY < topPos + 36)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_cattle_breeding"), mouseX, mouseY);
		if (ReturnfarmerProcedure.execute(entity))
			if (mouseX > leftPos + 59 && mouseX < leftPos + 65 && mouseY > topPos + 30 && mouseY < topPos + 36)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_10_yield_from_fields"), mouseX, mouseY);
		if (ReturnfarmerProcedure.execute(entity))
			if (mouseX > leftPos + 107 && mouseX < leftPos + 113 && mouseY > topPos + 30 && mouseY < topPos + 36)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_another_20_yield_from_fields"), mouseX, mouseY);
		if (ReturnfarmerProcedure.execute(entity))
			if (mouseX > leftPos + 83 && mouseX < leftPos + 89 && mouseY > topPos + 30 && mouseY < topPos + 36)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_growing_blockplants"), mouseX, mouseY);
		if (ReturnfarmerProcedure.execute(entity))
			if (mouseX > leftPos + 131 && mouseX < leftPos + 137 && mouseY > topPos + 30 && mouseY < topPos + 36)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_and_another_20_of_field_yields"), mouseX, mouseY);
		if (ReturnsmithProcedure.execute(entity))
			if (mouseX > leftPos + 35 && mouseX < leftPos + 41 && mouseY > topPos + 30 && mouseY < topPos + 36)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_smith_lvl1"), mouseX, mouseY);
		if (ReturnsmithProcedure.execute(entity))
			if (mouseX > leftPos + 59 && mouseX < leftPos + 65 && mouseY > topPos + 30 && mouseY < topPos + 36)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_smith_lvl2"), mouseX, mouseY);
		if (ReturnsmithProcedure.execute(entity))
			if (mouseX > leftPos + 83 && mouseX < leftPos + 89 && mouseY > topPos + 30 && mouseY < topPos + 36)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_smith_lvl3"), mouseX, mouseY);
		if (ReturnsmithProcedure.execute(entity))
			if (mouseX > leftPos + 107 && mouseX < leftPos + 113 && mouseY > topPos + 30 && mouseY < topPos + 36)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_smith_lvl4"), mouseX, mouseY);
		if (ReturnsmithProcedure.execute(entity))
			if (mouseX > leftPos + 131 && mouseX < leftPos + 137 && mouseY > topPos + 30 && mouseY < topPos + 36)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_smith_lvl5"), mouseX, mouseY);
		if (ReturnfisherProcedure.execute(entity))
			if (mouseX > leftPos + 35 && mouseX < leftPos + 41 && mouseY > topPos + 57 && mouseY < topPos + 63)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_fisher_1"), mouseX, mouseY);
		if (ReturnfisherProcedure.execute(entity))
			if (mouseX > leftPos + 59 && mouseX < leftPos + 65 && mouseY > topPos + 57 && mouseY < topPos + 63)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_fisher_2"), mouseX, mouseY);
		if (ReturnfisherProcedure.execute(entity))
			if (mouseX > leftPos + 83 && mouseX < leftPos + 89 && mouseY > topPos + 57 && mouseY < topPos + 63)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_fisher_3"), mouseX, mouseY);
		if (ReturnfisherProcedure.execute(entity))
			if (mouseX > leftPos + 107 && mouseX < leftPos + 113 && mouseY > topPos + 57 && mouseY < topPos + 63)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_fisher_4"), mouseX, mouseY);
		if (ReturnfisherProcedure.execute(entity))
			if (mouseX > leftPos + 131 && mouseX < leftPos + 137 && mouseY > topPos + 57 && mouseY < topPos + 63)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_fihsher_5"), mouseX, mouseY);
		if (ReturnsoldierProcedure.execute(entity))
			if (mouseX > leftPos + 35 && mouseX < leftPos + 41 && mouseY > topPos + 57 && mouseY < topPos + 63)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_soldier_1"), mouseX, mouseY);
		if (ReturnsoldierProcedure.execute(entity))
			if (mouseX > leftPos + 59 && mouseX < leftPos + 65 && mouseY > topPos + 57 && mouseY < topPos + 63)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_soldier_2"), mouseX, mouseY);
		if (ReturnsoldierProcedure.execute(entity))
			if (mouseX > leftPos + 83 && mouseX < leftPos + 89 && mouseY > topPos + 57 && mouseY < topPos + 63)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_soldier_3"), mouseX, mouseY);
		if (ReturnsoldierProcedure.execute(entity))
			if (mouseX > leftPos + 107 && mouseX < leftPos + 113 && mouseY > topPos + 57 && mouseY < topPos + 63)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_soldier_4"), mouseX, mouseY);
		if (ReturnsoldierProcedure.execute(entity))
			if (mouseX > leftPos + 131 && mouseX < leftPos + 137 && mouseY > topPos + 57 && mouseY < topPos + 63)
				guiGraphics.renderTooltip(font, Component.translatable("gui.reign_mod.win_view.tooltip_soldier_5"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/crown.png"), this.leftPos + 15, this.topPos + -11, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/license_slot.png"), this.leftPos + 12, this.topPos + 24, 0, 0, 18, 18, 18, 18);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/license_slot.png"), this.leftPos + 12, this.topPos + 51, 0, 0, 18, 18, 18, 18);

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_bar.png"), this.leftPos + 30, this.topPos + 25, 0, 0, 112, 16, 112, 16);

		if (Mlvl1Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_first.png"), this.leftPos + 35, this.topPos + 30, 0, 0, 6, 6, 6, 6);
		}
		if (Mlvl2Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_next.png"), this.leftPos + 41, this.topPos + 30, 0, 0, 24, 6, 24, 6);
		}
		if (Mlvl3Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_next.png"), this.leftPos + 65, this.topPos + 30, 0, 0, 24, 6, 24, 6);
		}
		if (Mlvl4Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_next.png"), this.leftPos + 89, this.topPos + 30, 0, 0, 24, 6, 24, 6);
		}
		if (Mlvl5Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_next.png"), this.leftPos + 113, this.topPos + 30, 0, 0, 24, 6, 24, 6);
		}

		guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_bar.png"), this.leftPos + 30, this.topPos + 52, 0, 0, 112, 16, 112, 16);

		if (Alvl1Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_first.png"), this.leftPos + 35, this.topPos + 57, 0, 0, 6, 6, 6, 6);
		}
		if (Alvl2Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_next.png"), this.leftPos + 41, this.topPos + 57, 0, 0, 24, 6, 24, 6);
		}
		if (Alvl3Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_next.png"), this.leftPos + 65, this.topPos + 57, 0, 0, 24, 6, 24, 6);
		}
		if (Alvl4Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_next.png"), this.leftPos + 89, this.topPos + 57, 0, 0, 24, 6, 24, 6);
		}
		if (Alvl5Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/lvl_point_next.png"), this.leftPos + 113, this.topPos + 57, 0, 0, 24, 6, 24, 6);
		}
		if (ReturnwoodcutterProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/woodcutter_logo.png"), this.leftPos + 13, this.topPos + 25, 0, 0, 16, 16, 16, 16);
		}
		if (ReturnsmithProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/smith_logo.png"), this.leftPos + 13, this.topPos + 25, 0, 0, 16, 16, 16, 16);
		}
		if (ReturnminerProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/miner_logo.png"), this.leftPos + 13, this.topPos + 25, 0, 0, 16, 16, 16, 16);
		}
		if (ReturnfarmerProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/farmer.png"), this.leftPos + 13, this.topPos + 25, 0, 0, 16, 16, 16, 16);
		}
		if (ReturnalchemistProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/alchemist_logo.png"), this.leftPos + 13, this.topPos + 52, 0, 0, 16, 16, 16, 16);
		}
		if (ReturnlibrarianProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/enchanter_logo.png"), this.leftPos + 13, this.topPos + 52, 0, 0, 16, 16, 16, 16);
		}
		if (ReturnfisherProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/fisher_logo.png"), this.leftPos + 13, this.topPos + 52, 0, 0, 16, 16, 16, 16);
		}
		if (ReturncowboyProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/cowboy_logo.png"), this.leftPos + 13, this.topPos + 25, 0, 0, 16, 16, 16, 16);
		}
		if (ReturnsoldierProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/soldier_logo.png"), this.leftPos + 13, this.topPos + 52, 0, 0, 16, 16, 16, 16);
		}
		if (ReturnhunterProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("reign_mod:textures/screens/hunter_logo.png"), this.leftPos + 13, this.topPos + 52, 0, 0, 16, 16, 16, 16);
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
		guiGraphics.drawString(this.font, Component.translatable("gui.reign_mod.win_view.label_licenses_are_your_line_of_busine"), -14, -23, -1, false);
	}

	@Override
	public void init() {
		super.init();
		button_main_license = Button.builder(Component.translatable("gui.reign_mod.win_view.button_main_license"), e -> {
			if (true) {
				ReignModMod.PACKET_HANDLER.sendToServer(new WinViewButtonMessage(0, x, y, z));
				WinViewButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 29, this.topPos + 85, 97, 20).build();
		guistate.put("button:button_main_license", button_main_license);
		this.addRenderableWidget(button_main_license);
		button_minor_license = Button.builder(Component.translatable("gui.reign_mod.win_view.button_minor_license"), e -> {
			if (ReturnAddLicenseLockProcedure.execute(world)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new WinViewButtonMessage(1, x, y, z));
				WinViewButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 29, this.topPos + 120, 97, 20).build(builder -> new Button(builder) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = ReturnAddLicenseLockProcedure.execute(world);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		});
		guistate.put("button:button_minor_license", button_minor_license);
		this.addRenderableWidget(button_minor_license);
		imagebutton_lvl_up_button = new ImageButton(this.leftPos + 13, this.topPos + 25, 16, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_lvl_up_button.png"), 16, 32, e -> {
			if (MainButtonUpProcedure.execute(entity)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new WinViewButtonMessage(2, x, y, z));
				WinViewButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = MainButtonUpProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_lvl_up_button", imagebutton_lvl_up_button);
		this.addRenderableWidget(imagebutton_lvl_up_button);
		imagebutton_lvl_up_button1 = new ImageButton(this.leftPos + 13, this.topPos + 52, 16, 16, 0, 0, 16, new ResourceLocation("reign_mod:textures/screens/atlas/imagebutton_lvl_up_button1.png"), 16, 32, e -> {
			if (AddButtonUpProcedure.execute(entity)) {
				ReignModMod.PACKET_HANDLER.sendToServer(new WinViewButtonMessage(3, x, y, z));
				WinViewButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = AddButtonUpProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_lvl_up_button1", imagebutton_lvl_up_button1);
		this.addRenderableWidget(imagebutton_lvl_up_button1);
	}
}
