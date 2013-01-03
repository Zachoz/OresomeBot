package com.zachoz.OresomeBot;


import org.pircbotx.Channel;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.*;
import com.zachoz.OresomeBot.OresomeBot;

@SuppressWarnings("rawtypes")
public class kickbanCommand extends ListenerAdapter {

    
  
    


    public void onMessage(MessageEvent event) throws Exception {
	 Channel currentchannel = event.getChannel();
	
	
    
	 
	
	 String userarg = event.getMessage().split(" ")[1];
	 
	  if (event.getMessage().startsWith("!kickban") && event.getMessage().contains(userarg))   {
	 
	    
	      
	    
	   
	      if(currentchannel.hasVoice(event.getUser()) || currentchannel.isOp(event.getUser()) ) { 
	 

	   
	      
	      OresomeBot.bot.ban(currentchannel, userarg);
	      OresomeBot.bot.sendRawLine("kick " + event.getChannel().getName() + " " + userarg + " " + "Banned" );

	    event.respond("Banned and kicked: " + userarg);

	 
	 
	  } else {
	     event.respond("Only operators and voiced users may place bans.");
	  }
   
}
   
	 
    }

    }

    




    



