package com.zachoz.AusfragBot.commands;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class TicketCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) {
	if (event.getMessage().equals("!ticket")) {

	    event.respond("Open up a support ticket at: http://goo.gl/Vfduu !");

	}
    }
}