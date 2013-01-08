package com.zachoz.OresomeBot.commands;


import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import com.zachoz.OresomeBot.Config;
import com.zachoz.OresomeBot.OresomeBot;
@SuppressWarnings("rawtypes")
public class partCommand extends ListenerAdapter {

    
 
    public void onMessage(MessageEvent event) throws Exception {

	
	String channel = event.getMessage().split(" ")[1];
  if (event.getMessage().startsWith(".part") && event.getMessage().contains(channel)) {
      String admin = "";
      for (int i = 0 ; i < Config.admins.length; i++ ) {
	  admin += Config.admins[i];
      }
  
	  if(admin.contains(event.getUser().getNick())) { 
	      
	    OresomeBot.bot.sendRawLineNow("part" + " " + channel);
	    event.respond("Parting " + channel);
	    } 

	}

    }
}
