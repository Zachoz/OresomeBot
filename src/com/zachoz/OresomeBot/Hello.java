package com.zachoz.OresomeBot;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

public class Hello extends ListenerAdapter {

    public void onMessage(MessageEvent event) {
        if (event.getMessage().equals(".hello")) {
                event.respond("Hello");
}
}

}