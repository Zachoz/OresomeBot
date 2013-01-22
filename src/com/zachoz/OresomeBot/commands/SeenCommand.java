package com.zachoz.OresomeBot.commands;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import com.zachoz.OresomeBot.OresomeBot;

@SuppressWarnings("rawtypes")
public class SeenCommand extends ListenerAdapter {
    public void onMessage(MessageEvent event) throws SQLException {
	if (event.getMessage().split(" ").length > 1) {
	    String user = event.getMessage().split(" ")[1];

	    if (event.getMessage().startsWith(".seen ") && event.getMessage().contains(user)) {
		OresomeBot.mysql.open();
		ResultSet rs = OresomeBot.mysql.query("SELECT * FROM seenusers WHERE user='" + user + "'");

		if (rs.next()) {
		    String lastseen = new String(rs.getString("lastseen"));
		    event.respond("I last saw this user: " + lastseen);
		    
		} else {
		    event.respond("I've never seen this user!");
		}
	    }
	}
    }
}
