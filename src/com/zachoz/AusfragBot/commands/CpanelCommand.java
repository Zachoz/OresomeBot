package com.zachoz.AusfragBot.commands;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class CpanelCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) {
	if (event.getMessage().equals("!cpanel")) {

	    event.respond("Log into cPanel here: http://ausfrag.com:2082");

	}
    }
}