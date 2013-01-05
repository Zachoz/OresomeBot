package com.zachoz.OresomeBot.commands;


import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.*;
import com.zachoz.OresomeBot.OresomeBot;

@SuppressWarnings("rawtypes")
public class joinCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) throws Exception {

	 if (event.getMessage().equals(".join")) {
 
		    event.respond("Please specify a channel to join.");
		 } 

	 String channelarg = event.getMessage().split(" ")[1];
	  if (event.getMessage().startsWith(".join") && event.getMessage().contains(channelarg)) {
 
	      if(event.getUser().getNick().equals("Zachoz|OnHoliday") 
	      || event.getUser().getNick().equals("Zachoz")) { 

	      OresomeBot.bot.sendRawLineNow("join" + " " + channelarg);

	    event.respond("Attempted to join channel " + channelarg);

	  } else {
	     event.respond("Nope.");
	    }

	}

    }
}


