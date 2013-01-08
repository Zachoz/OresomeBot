package com.zachoz.OresomeBot.commands;


import org.pircbotx.Channel;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.*;
import com.zachoz.OresomeBot.OresomeBot;

@SuppressWarnings("rawtypes")
public class unbanCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) throws Exception {
	 Channel currentchannel = event.getChannel();

	 String userarg = event.getMessage().split(" ")[1];
	 
	  if (event.getMessage().startsWith(".unban") && event.getMessage().contains(userarg))   {

	      if(currentchannel.hasVoice(event.getUser()) || currentchannel.isOp(event.getUser()) ) { 

		  OresomeBot.bot.sendRawLineNow("mode" + " " + event.getChannel().getName() + " -b " + userarg);

	    event.respond("Unbanning: " + userarg);

	    }

	}

    }

}




    



