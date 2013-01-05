package com.zachoz.OresomeBot.commands;


import org.pircbotx.Channel;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.*;
import com.zachoz.OresomeBot.OresomeBot;

@SuppressWarnings("rawtypes")
public class kickCommand extends ListenerAdapter {

	
    public void onMessage(MessageEvent event) throws Exception {
	 Channel currentchannel = event.getChannel();
	 String userarg = event.getMessage().split(" ")[1];

	  if (event.getMessage().startsWith(".kick") && event.getMessage().contains(userarg))   {

	      if(currentchannel.hasVoice(event.getUser()) || currentchannel.isOp(event.getUser()) ) { 
	 
	   String kickReason = "Kick request by " + event.getUser();
	                                       
	      OresomeBot.bot.sendRawLineNow("kick " + event.getChannel().getName() + " " + userarg + " " + kickReason );

	    event.respond("Kicked: " + userarg);

	  } else {
	     event.respond("Only operators and voiced users may kick users.");
	    }

	}

    }

}
    




    



