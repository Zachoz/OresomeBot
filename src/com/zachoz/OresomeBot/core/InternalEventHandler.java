package com.zachoz.OresomeBot.core;

import com.zachoz.OresomeBot.Config;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;

public class InternalEventHandler extends ListenerAdapter {

    public void onConnect(ConnectEvent event) { // Properly join channels
        for (String channel : Config.channels) {
            event.getBot().sendIRC().joinChannel(channel);
        }
    }
}
