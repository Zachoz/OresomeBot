package com.zachoz.OresomeBot;


import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import com.zachoz.OresomeBot.OresomeBot;
@SuppressWarnings("rawtypes")
public class partCommand extends ListenerAdapter {

    
 
    public void onMessage(MessageEvent event) throws Exception {

	
	String channel = event.getMessage().substring(6).replaceAll("^\\s+", "").replaceAll("\\s+$", "");
  if (event.getMessage().startsWith(".part") && event.getMessage().contains(channel)) {
	    
	    OresomeBot.bot.sendRawLineNow("part" + " " + channel);
	    event.respond("Parting " + channel);
	} else {
	    //Nope.
	}
	 



}

}

