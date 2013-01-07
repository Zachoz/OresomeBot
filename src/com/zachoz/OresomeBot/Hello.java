package com.zachoz.OresomeBot;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class Hello extends ListenerAdapter {

    public void onMessage(MessageEvent event) throws SQLException {
        String currentchannel = event.getChannel().getName();
	String speaker = event.getUser().getNick();
	OresomeBot.mysql.open();
	ResultSet speakinguser = OresomeBot.mysql.query("SELECT recipient FROM tellmessages WHERE recipient='" + speaker + "'");
	speakinguser.next();
	String speakinguserResult = speakinguser.getString("recipient");
  
	if (speaker.equals(speakinguserResult)) {

	    ResultSet getRecipient = OresomeBot.mysql.query("SELECT recipient FROM tellmessages WHERE recipient='" + speaker + "'");
	    
	    ResultSet getSender = OresomeBot.mysql.query("SELECT sender FROM tellmessages WHERE recipient='" + speaker + "'");
	    getSender.next();
	    String getSenderResult = getSender.getString("sender");
	    
	    ResultSet getMessage = OresomeBot.mysql.query("SELECT message FROM tellmessages WHERE recipient='" + speaker + "'");
	    getMessage.next();
	    String getMessageResult = getMessage.getString("message");
	    
	   
	    String messages = "[Message] Message from " + getSenderResult + " : " + getMessageResult;
	    event.respond(messages);
	    
	   OresomeBot.mysql.close();
	
	} else {
	    
	    
	}
	
  }

}