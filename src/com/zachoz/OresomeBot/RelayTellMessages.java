package com.zachoz.OresomeBot;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class RelayTellMessages extends ListenerAdapter {

    public void onMessage(MessageEvent event) throws SQLException {
	String speaker = event.getUser().getNick();
	String currentchannel = event.getChannel().getName();
	OresomeBot.mysql.open();
	ResultSet rs = OresomeBot.mysql.query("SELECT * FROM tellmessages WHERE recipient='" + speaker + "'");
	if (rs.next()) {
	    if (!event.getMessage().startsWith(".tell ") && !event.getMessage().startsWith(".seen ")) {
		String channel = rs.getString("channel");
		if (channel.equals(currentchannel)) {
		 String sender = rs.getString("sender");
	         String message = rs.getString("message");
	         event.respond("[Message] From " + sender + ": " + message);
	    
	    while (rs.next()) {
		 sender = rs.getString("sender");
	         message = rs.getString("message");
	         event.respond("[Message] From " + sender + ": " + message);
		    }

		}
	    }
	}

        OresomeBot.mysql.query("DELETE FROM tellmessages WHERE recipient='" + speaker + "'");
	OresomeBot.mysql.close();
    }
    
    public void onJoin(JoinEvent event) throws Exception {
	 OresomeBot.mysql.open();
	 String speaker = event.getUser().getNick();
	 String currentchannel = event.getChannel().getName();

	 ResultSet rs = OresomeBot.mysql.query("SELECT * FROM tellmessages WHERE recipient='" + speaker + "'");


	     if (rs.next()) {
	     String channel = new String(rs.getString("channel"));
	     if (channel.equals(currentchannel)) {
	     event.respond("You have messages! Speak to see them!");
	    }
	     OresomeBot.mysql.close();
	}
    }

}