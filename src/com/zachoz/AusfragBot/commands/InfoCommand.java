package com.zachoz.AusfragBot.commands;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class InfoCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) {

	if (event.getMessage().equals("!info")) {

	event.respond("Hi, I'm Ausfrag(Bot), a fork of OresomeBot. I have many utilities and commands designed specifically for use in the channel.");
	}
    }
}
