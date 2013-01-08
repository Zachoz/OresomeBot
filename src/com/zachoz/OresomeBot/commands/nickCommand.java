package com.zachoz.OresomeBot.commands;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import com.zachoz.OresomeBot.Config;
import com.zachoz.OresomeBot.OresomeBot;

@SuppressWarnings("rawtypes")
public class nickCommand extends ListenerAdapter {
    

    public void onMessage(MessageEvent event) {
	if (event.getMessage().split(" ").length > 1) {
	 String nickname = event.getMessage().split(" ")[1];
	  if (event.getMessage().startsWith(".nick") && event.getMessage().contains(nickname)) {
	      String admin = "";
  	      for (int i = 0 ; i < Config.admins.length; i++ ) {
  		  admin += Config.admins[i];
  	      }
  	      
  	  
  		  if(admin.contains(event.getUser().getNick())) { 
                OresomeBot.bot.changeNick(nickname);
	}
    }

}
}
}