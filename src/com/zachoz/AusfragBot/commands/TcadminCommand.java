package com.zachoz.AusfragBot.commands;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class TcadminCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) {
	if (event.getMessage().equals("!tcadmin")) {

	    event.respond("Log into TCAdmin here: http://my.ausfrag.com");

	}
    }
}