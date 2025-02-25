package net.mcreator.reignmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.client.gui.components.EditBox;

import net.mcreator.reignmod.network.ReignModModVariables;

import java.util.HashMap;

public class RoyaleSettingsTikProcedure {
	public static void execute(LevelAccessor world, HashMap guistate) {
		if (guistate == null)
			return;
		if (guistate.get("text:logs_textfield") instanceof EditBox _tf)
			_tf.setValue((Math.round(ReignModModVariables.MapVariables.get(world).logs_price * 100) + "%"));
		if (guistate.get("text:coal_textfield") instanceof EditBox _tf)
			_tf.setValue((Math.round(ReignModModVariables.MapVariables.get(world).coal_price * 100) + "%"));
		if (guistate.get("text:food_textfield") instanceof EditBox _tf)
			_tf.setValue((Math.round(ReignModModVariables.MapVariables.get(world).food_price * 100) + "%"));
		if (guistate.get("text:armor_textfield") instanceof EditBox _tf)
			_tf.setValue((Math.round(ReignModModVariables.MapVariables.get(world).armor_price * 100) + "%"));
		if (guistate.get("text:tools_textfield") instanceof EditBox _tf)
			_tf.setValue((Math.round(ReignModModVariables.MapVariables.get(world).tools_price * 100) + "%"));
		if (guistate.get("text:ores_textfield") instanceof EditBox _tf)
			_tf.setValue((Math.round(ReignModModVariables.MapVariables.get(world).ores_price * 100) + "%"));
		if (guistate.get("text:other_textfield") instanceof EditBox _tf)
			_tf.setValue((Math.round(ReignModModVariables.MapVariables.get(world).other_price * 100) + "%"));
	}
}
