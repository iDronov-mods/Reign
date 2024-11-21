package net.mcreator.reignmod.basics;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ReignChatEvent {
    public static Component onClientChat(ChatType type, Component message, UUID senderUUID) {
        String chatInRaw = message.getString();
        if (!chatInRaw.startsWith("<") || !chatInRaw.contains("> ")) {
            return message;
        }

        String[] cirspl = chatInRaw.split("> ");
        if (cirspl.length < 2) {
            return message;
        }

        String timestamp = new SimpleDateFormat("HH:mm").format(new Date());
        String user = cirspl[0].substring(1);
        String messageString = chatInRaw.replace(cirspl[0] + "> ", "");

        return getMutableComponent(timestamp, user, messageString);
    }

    @NotNull
    private static MutableComponent getMutableComponent(String timestamp, String user, String messageString) {
        MutableComponent output = Component.literal("");
        String raw_outputstring = "%timestamp% | %username%: %chatmessage%";
        for (String word : raw_outputstring.split("%")) {
            ChatFormatting colour = ReignChatEventUtil.getColour(word);
            String toappend = word;

            if (word.equalsIgnoreCase("timestamp")) {
                toappend = timestamp;
            }
            else if (word.equalsIgnoreCase("username")) {
                toappend = user;
            }
            else if (word.equalsIgnoreCase("chatmessage")) {
                toappend = messageString;
            }

            MutableComponent wordcomponent = Component.literal(toappend);
            wordcomponent.withStyle(colour);
            output.append(wordcomponent);
        }
        return output;
    }
}


