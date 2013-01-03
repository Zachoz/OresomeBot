package com.zachoz.OresomeBot;


import java.util.Set;

import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.*;
import com.zachoz.OresomeBot.OresomeBot;
import org.pircbotx.hooks.events.VoiceEvent;

@SuppressWarnings("rawtypes")
public class banCommand extends ListenerAdapter {

    
    
  
    private Channel channel;
    


    public void onMessage(MessageEvent event) throws Exception {
	 Channel currentchannel = event.getChannel();
	
	 if (event.getMessage().equals(".ban")) {
	     
	
		 } 
    
	 
	
	 String userarg = event.getMessage().split(" ")[1];
	 
	  if (event.getMessage().startsWith(".ban") && event.getMessage().contains(userarg))   {
	 
	    
	      
	    
	   
	      if(currentchannel.hasVoice(event.getUser()) || currentchannel.isOp(event.getUser()) ) { 
	 

	   
	      
	      OresomeBot.bot.ban(currentchannel, userarg);

	    event.respond("Banning: " + userarg);

	 
	 
	  } else {
	     event.respond("Only operators nd voiced users may place bans.");
	  }
   
}
   
	 
    }

    }

    




    



