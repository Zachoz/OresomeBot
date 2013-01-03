package com.zachoz.OresomeBot.commands;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import com.zachoz.OresomeBot.OresomeBot;

@SuppressWarnings("rawtypes")
public class sayCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) {
	
	      if(event.getUser().getNick().equals("Zachoz|OnHoliday") 
		      || event.getUser().getNick().equals("Zachoz")) { 

	String channel = event.getMessage().split(" ")[1];
	String message = event.getMessage().split(" ")[2];
	if (event.getMessage().startsWith(".say") && event.getMessage().contains(channel)
		&& event.getMessage().contains(message)) {

	    OresomeBot.bot.sendRawLine("privmsg" + " " + channel + " " + message);

	    }
	}

    }
}