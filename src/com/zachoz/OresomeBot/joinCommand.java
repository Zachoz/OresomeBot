package com.zachoz.OresomeBot;


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
	      //diz shit can't get "user" variable
	      //User zachoz = user;
	      
	      
	      if(event.getUser().getNick().equals("Zachoz|OnHoliday") 
	      || event.getUser().getNick().equals("Zachoz")) { 

	    //OresomeBot.bot.joinChannel(channel);
	      OresomeBot.bot.sendRawLine("join" + " " + channelarg);

	    event.respond("Attempted to join channel " + channelarg);

	 
	 
	  } else {
	     event.respond("Nope.");
	  }
   
}
   
	 }
}
    



