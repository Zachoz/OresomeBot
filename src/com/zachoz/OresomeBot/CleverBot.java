package com.zachoz.OresomeBot;


import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.*;

import com.google.code.chatterbotapi.ChatterBot;
import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotSession;
import com.google.code.chatterbotapi.ChatterBotType;
import com.zachoz.OresomeBot.OresomeBot;

@SuppressWarnings("rawtypes")
public class CleverBot extends ListenerAdapter {
    
    

    public void onMessage(MessageEvent event) throws Exception {
	
	       ChatterBotFactory factory = new ChatterBotFactory();
			ChatterBot cleverbot = factory.create(ChatterBotType.CLEVERBOT);
		        ChatterBotSession botsession = cleverbot.createSession();
	
	String message = event.getMessage();
	String[] ArrSay = message.split(" ");
	String outsay = ""; 
	
	 for (int i = 2; i < ArrSay.length; i++) {
		 outsay += ArrSay[i];
		 if (i != ArrSay.length - 1) {
			 outsay += " ";
			}
		
		 }
		
    
	if (event.getMessage().split(" ").length > 1) {

	 if (event.getMessage().startsWith(OresomeBot.bot.getNick() + ": ")) {
	     
	    String s = botsession.think(outsay);
	    event.respond(s);
	     
		 } 

 
	  
    } 
}
    


    
}


