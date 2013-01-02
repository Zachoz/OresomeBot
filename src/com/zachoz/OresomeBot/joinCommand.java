package com.zachoz.OresomeBot;

import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.*;
import com.zachoz.OresomeBot.OresomeBot;

@SuppressWarnings("rawtypes")
public class joinCommand extends ListenerAdapter {

    
   

    joinCommand(OresomeBot bot, Channel channel, User user, String message) { 
	this.user = user;
    }

    public void onMessage(MessageEvent event) throws Exception {
	
	
	 if (event.getMessage().equals(".join")) {
	     
		 
		    event.respond("Please specify a channel to join.");
		 } 
    

	
	
	 String channelarg = event.getMessage().substring(6).replaceAll("^\\s+", "").replaceAll("\\s+$", "");
	  if (event.getMessage().startsWith(".join") && event.getMessage().contains(channelarg)) {
	      //diz shit can't get "user" variable
	      if(user.equals("Zachoz|OnHoliday")) { 

	    //OresomeBot.bot.joinChannel(channel);
	      OresomeBot.bot.sendRawLine("join" + " " + channelarg);

	    event.respond("Attempted to join channel " + channelarg);

	 
	 
	  } else {
	     event.respond("Nope.");
	  }
   
}
   
	 }
}
    



