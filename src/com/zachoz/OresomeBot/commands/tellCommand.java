package com.zachoz.OresomeBot.commands;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import com.zachoz.OresomeBot.OresomeBot;

@SuppressWarnings("rawtypes")
public class tellCommand extends ListenerAdapter {
  
    public void onMessage(MessageEvent event) throws SQLException {

	
	String message = event.getMessage();
	String user = event.getMessage().split(" ")[1];
	String channel = event.getChannel().getName();
	String[] ArrSay = message.split(" ");
	String outsay = "";

	if (event.getMessage().startsWith(".tell") && event.getMessage().contains(user) && event.getMessage().contains(message)) {
		if (ArrSay.length > 2) {
		   
			 for (int i = 2; i < ArrSay.length; i++) {
			 outsay += ArrSay[i];
			 if (i != ArrSay.length - 1) {
			 outsay += " ";
			}
			 }
			}
		
		OresomeBot.mysql.open();

		if (OresomeBot.mysql.checkConnection()) {
			try {
		   OresomeBot.mysql.query("INSERT INTO tellmessages (channel, sender, recipient, message) VALUES ('" + channel +"', '" + event.getUser().getNick() + "', '" + user +"', '" + outsay + "') ");

			
			event.respond("I'll pass that on to " + user + " when they're around next.");

		} catch (Exception e) {
		    
		}
	}
    }
    
    }


    }
   
	
    
    
  
    

