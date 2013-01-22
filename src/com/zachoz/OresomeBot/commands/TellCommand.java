package com.zachoz.OresomeBot.commands;

import java.sql.SQLException;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import com.zachoz.OresomeBot.OresomeBot;

@SuppressWarnings("rawtypes")
public class TellCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) throws SQLException {
	if (event.getMessage().split(" ").length > 1) {

	    String message = event.getMessage();
	    String user = event.getMessage().split(" ")[1];
	    String channel = event.getChannel().getName();
	    String[] ArrSay = message.split(" ");
	    String outsay = "";

	    if (event.getMessage().startsWith(".tell ")
		    && event.getMessage().contains(user)
		    && event.getMessage().contains(message)) {
		
		for (int i = 2; i < ArrSay.length; i++) {
		    if (ArrSay[i].contains("'")) {
			String temp = "";
			String temp2 = ArrSay[i];
			for (int j = 0; j < temp2.length(); j++) {
			    String temp3 = "" + temp2.charAt(j);
			    if (!temp3.equals("'")) {
				temp += temp3;
			    }
			}
			outsay += temp;
		    } else {
			outsay += ArrSay[i];
		    }
		    if (i != ArrSay.length - 1) {
			outsay += " ";
		    }
		}

		OresomeBot.mysql.open();

		if (OresomeBot.mysql.checkConnection()) {
		    try {
			OresomeBot.mysql.query("INSERT INTO tellmessages (channel, sender, recipient, message) VALUES ('" + channel + "', '" + event.getUser().getNick() + "', '" + user + "', '" + outsay + "') ");

			event.respond("I'll pass that on to " + user + " when they're around next.");

		    } catch (Exception e) {

		    }
		}
	    }

	}

    }
}
