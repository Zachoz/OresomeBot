package com.zachoz.OresomeBot;

import java.sql.ResultSet;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;

@SuppressWarnings("rawtypes")
public class WelcomeMessage extends ListenerAdapter {

    public void onJoin(JoinEvent event) throws Exception {
	OresomeBot.mysql.open();
	String speaker = event.getUser().getNick();
	String currentchannel = event.getChannel().getName();

	ResultSet rs = OresomeBot.mysql.query("SELECT * FROM oresomejoinedusers WHERE users='" + speaker + "'");

	if (currentchannel.equals(Config.primarychannel)) {

	    if (rs.next()) {
		// Just ignore them.
	    } else {

		OresomeBot.mysql.query("INSERT INTO oresomejoinedusers (users) VALUES ('" + speaker + "')");
		OresomeBot.mysql.close();

		event.respond("Hi, welcome to the OresomeCraft IRC channel, if you have a question please ask it and wait patiently for a reply. To get our attention make sure you ping us, just mention our name in your message and we'll be alerted.");
	    }
	}
    }

}