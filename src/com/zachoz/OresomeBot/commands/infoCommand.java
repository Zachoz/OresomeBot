package com.zachoz.OresomeBot.commands;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import com.zachoz.OresomeBot.Config;

@SuppressWarnings("rawtypes")
public class infoCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) {
        if (event.getMessage().equals(".info")) {
            String admin = "";
	      for (int i = 0 ; i < Config.admins.length; i++ ) {
		  admin += Config.admins[i];
	      }
	      
	  
		  if(admin.contains(event.getUser().getNick())) { 
                event.respond("Hi, I'm OresomeBot, an IRC bot designed by Zachoz for primary use in OresomeCraft IRC channels. I'm written in Java and use the PircBotx Library. I also have various useful administrative and user commands.");
	}
    }
    }
    }