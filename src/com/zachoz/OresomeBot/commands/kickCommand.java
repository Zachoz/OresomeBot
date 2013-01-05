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
	 String message = event.getMessage();
	 String[] ArrSay = message.split(" ");
		String outsay = "";

	
	  if (event.getMessage().startsWith(".kick") && event.getMessage().contains(userarg))   {

	      if(currentchannel.hasVoice(event.getUser()) || currentchannel.isOp(event.getUser()) ) { 
			if (ArrSay.length > 2) {
				   
				 for (int i = 2; i < ArrSay.length; i++) {
				 outsay += ArrSay[i];
				 if (i != ArrSay.length - 1) {
				 outsay += " ";
				}
				 }
				}
	 
	  //String kickReason = "Kick request by " + event.getUser().getNick();
	                                       
	      OresomeBot.bot.sendRawLineNow("kick " + event.getChannel().getName() + " " + userarg + " " + outsay );
	      



	  } else {
	     event.respond("Only operators and voiced users may kick users.");
	    }

	}

    }

}