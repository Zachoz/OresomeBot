package com.zachoz.OresomeBot.commands;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import com.zachoz.OresomeBot.OresomeBot;

@SuppressWarnings("rawtypes")
public class sayCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) {
	
	      if(event.getUser().getNick().equals("Zachoz|OnHoliday") 
		      || event.getUser().getNick().equals("Zachoz")) { 

	String message = event.getMessage();
	String channel = event.getMessage().split(" ")[1];
	String[] ArrSay = message.split(" ");
	String outsay = "";

	if (event.getMessage().startsWith(".say") && event.getMessage().contains(channel) && event.getMessage().contains(message)) {
		if (ArrSay.length > 2) {
		   
			 for (int i = 2; i < ArrSay.length; i++) {
			 outsay += ArrSay[i];
			 if (i != ArrSay.length - 1) {
			 outsay += " ";
			}
			 }
			}
	   
	    OresomeBot.bot.sendMessage(channel, outsay);
	

	    }
	}

    }
}