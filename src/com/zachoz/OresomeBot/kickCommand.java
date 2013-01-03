package com.zachoz.OresomeBot;




import java.util.Set;

import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.*;
import com.zachoz.OresomeBot.OresomeBot;

@SuppressWarnings("rawtypes")
public class kickCommand extends ListenerAdapter {

	
    public void onMessage(MessageEvent event) throws Exception {
	 Channel currentchannel = event.getChannel();
	 String userarg = event.getMessage().split(" ")[1];
	 if (event.getMessage().equals(".kick")) {
	     
		 } 
    
	
	 
	  if (event.getMessage().startsWith(".kick") && event.getMessage().contains(userarg))   {
	 
	      
	      
	    User user = event.getUser();
	    
	   // Set<User> user = OresomeBot.bot.getUsers(event.getChannel());
	   
	      if(currentchannel.hasVoice(event.getUser()) || currentchannel.isOp(event.getUser()) ) { 
	 

	   String kickReason = "Kick request by " + event.getUser();
	      
	   OresomeBot.bot.kick(event.getChannel(), jj, kickReason);
	                                          //Error here.
	      //OresomeBot.bot.sendRawLine("kick " + event.getChannel() + userarg  + kickReason );

	    event.respond("Kicked: " + userarg);

	 
	 
	  } else {
	     event.respond("Only operators nd voiced users may place bans.");
	  }
   
}
   
	 
    }

    }

    




    



