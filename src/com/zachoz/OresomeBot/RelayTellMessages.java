package com.zachoz.OresomeBot;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;

import com.zachoz.OresomeBot.Database.MySQL;

@SuppressWarnings("rawtypes")
public class RelayTellMessages extends ListenerAdapter {

    public void onMessage(MessageEvent event) throws SQLException {

	MySQL mysql = new MySQL(OresomeBot.logger,
		"[OresomeBot]", OresomeBot.mysql_host,
		OresomeBot.mysql_port, OresomeBot.mysql_db,
		OresomeBot.mysql_user, OresomeBot.mysql_password);

	String speaker = event.getUser().getNick();
	String currentchannel = event.getChannel().getName();
	mysql.open();
	ResultSet rs = mysql.query("SELECT * FROM tellmessages WHERE recipient='" + speaker + "'");
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

	mysql.query("DELETE FROM tellmessages WHERE recipient='" + speaker + "'");
	mysql.close();
    }

    public void onJoin(JoinEvent event) throws Exception {
	MySQL mysql = new MySQL(OresomeBot.logger,
		"[OresomeBot]", OresomeBot.mysql_host,
		OresomeBot.mysql_port, OresomeBot.mysql_db,
		OresomeBot.mysql_user, OresomeBot.mysql_password);

	mysql.open();
	String speaker = event.getUser().getNick();
	String currentchannel = event.getChannel().getName();

	ResultSet rs = mysql.query("SELECT * FROM tellmessages WHERE recipient='" + speaker + "'");


	if (rs.next()) {
	    String channel = new String(rs.getString("channel"));
	    if (channel.equals(currentchannel)) {
		event.respond("You have messages! Speak to see them!");
	    }
	    mysql.close();
	}
    }

}