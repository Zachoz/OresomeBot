package com.zachoz.AusfragBot.commands;


import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import com.zachoz.AusfragBot.Config;
import com.zachoz.AusfragBot.AusfragBot;
@SuppressWarnings("rawtypes")
public class PartCommand extends ListenerAdapter {

    
 
    public void onMessage(MessageEvent event) throws Exception {
	if (event.getMessage().split(" ").length > 1) {

	
	String channel = event.getMessage().split(" ")[1];
  if (event.getMessage().startsWith("!part") && event.getMessage().contains(channel)) {
      String admin = "";
      for (int i = 0 ; i < Config.admins.length; i++ ) {
	  admin += Config.admins[i];
      }
  
	  if(admin.contains(event.getUser().getNick())) { 
	      
	    AusfragBot.bot.sendRawLineNow("part" + " " + channel);
	    event.respond("Parting " + channel);
	    } 

	}

    }
}
}
