package net.mcreator.reignmod.basics;

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ReignChatEventSubscriber {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onClientChat(ClientChatReceivedEvent e) {
        Component originalMessage = e.getMessage();
        Component newMessage = ReignChatEvent.onClientChat(null, e.getMessage(), e.getSender());

        if (originalMessage != newMessage) {
            e.setMessage(newMessage);
        }
    }
}
