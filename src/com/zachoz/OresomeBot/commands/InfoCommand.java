package com.zachoz.OresomeBot.commands;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class InfoCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) {

        if (event.getMessage().equals(".info")) {

            event.respond("Hi, I'm OresomeBot, an IRC bot designed by Zachoz for primary use in OresomeCraft IRC channels. I'm written in Java and use the PircBotx Library. I also have various useful administrative and user commands.");

        }
    }
}