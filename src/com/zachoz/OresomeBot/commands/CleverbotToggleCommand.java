package com.zachoz.OresomeBot.commands;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.*;

import com.zachoz.OresomeBot.CleverBot;
import com.zachoz.OresomeBot.Config;

@SuppressWarnings("rawtypes")
public class CleverbotToggleCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) throws Exception {

	if (event.getMessage().equals(".cleverboton")) {

	    String admin = "";
	    for (int i = 0; i < Config.admins.length; i++) {
		admin += Config.admins[i];
	    }

	    if (admin.contains(event.getUser().getNick())) {

		CleverBot.cleverbotEnabled = true;

		event.respond("CleverBot enabled.");

	    }

	}

	if (event.getMessage().equals(".cleverbotoff")) {

	    String admin = "";
	    for (int i = 0; i < Config.admins.length; i++) {
		admin += Config.admins[i];
	    }

	    if (admin.contains(event.getUser().getNick())) {

		CleverBot.cleverbotEnabled = false;

		event.respond("CleverBot disabled.");

	    }

	}

    }
}
