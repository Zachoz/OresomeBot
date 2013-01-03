package com.zachoz.OresomeBot.commands;


import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import com.zachoz.OresomeBot.OresomeBot;
@SuppressWarnings("rawtypes")
public class partCommand extends ListenerAdapter {

    
 
    public void onMessage(MessageEvent event) throws Exception {

	
	String channel = event.getMessage().split(" ")[1];
  if (event.getMessage().startsWith(".part") && event.getMessage().contains(channel)) {
      if(event.getUser().getNick().equals("Zachoz|OnHoliday") 
	      || event.getUser().getNick().equals("Zachoz")) { 
	    OresomeBot.bot.sendRawLineNow("part" + " " + channel);
	    event.respond("Parting " + channel);
	} else {
	    //Nope.
	}
	 



}

}
}
