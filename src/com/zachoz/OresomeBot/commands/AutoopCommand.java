package com.zachoz.OresomeBot.commands;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.*;

import com.zachoz.OresomeBot.Config;
import com.zachoz.OresomeBot.OresomeBot;

@SuppressWarnings("rawtypes")
public class AutoopCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) throws Exception {
	if (event.getMessage().split(" ").length > 1) {

	    String user = event.getMessage().split(" ")[1];
	    String currentchannel = event.getChannel().getName();
	    if (event.getMessage().startsWith(".autoop") && event.getMessage().contains(user)) {

		String admin = "";
		for (int i = 0; i < Config.admins.length; i++) {
		    admin += Config.admins[i];
		}

		if (admin.contains(event.getUser().getNick())) {
		    OresomeBot.bot.sendMessage("ChanServ", "flags " + currentchannel + " " + user + " +O ");

		    event.respond("Attempted set ChanServ flag +O on " + user);

		}

	    }

	}
    }
}
