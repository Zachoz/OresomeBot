package com.zachoz.OresomeBot.commands;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.*;

import com.zachoz.OresomeBot.Config;
import com.zachoz.OresomeBot.OresomeBot;

@SuppressWarnings("rawtypes")
public class OpmeCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) throws Exception {

	if (event.getMessage().equals(".opme")) {
	    String currentchannel = event.getChannel().getName();
	    String admin = "";
	    for (int i = 0; i < Config.admins.length; i++) {
		admin += Config.admins[i];
	    }

	    if (admin.contains(event.getUser().getNick())) {
		OresomeBot.bot.sendRawLineNow("mode " + currentchannel + " +o" + " " + event.getUser().getNick());

	    }

	}

    }
}
