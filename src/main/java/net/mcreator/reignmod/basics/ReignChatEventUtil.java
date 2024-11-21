package net.mcreator.reignmod.basics;

import net.minecraft.ChatFormatting;

public class ReignChatEventUtil {
    public static ChatFormatting getColour(String word) {
        ChatFormatting colour = ChatFormatting.getById(7);
        if (word.equalsIgnoreCase("timestamp")) {
            colour = ChatFormatting.getById(8);
        } else if (word.equalsIgnoreCase("chatmessage")) {
            colour = ChatFormatting.getById(15);
        }

        return colour;
    }
}
