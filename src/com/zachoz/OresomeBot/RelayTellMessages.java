/*
 * Yes, I know this doesn't work properly. But it _kinda_ works...
 * 
 */

package com.zachoz.OresomeBot;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class RelayTellMessages extends ListenerAdapter {

    public void onMessage(MessageEvent event) throws SQLException {
        String currentchannel = event.getChannel().getName();
	String speaker = event.getUser().getNick();
	OresomeBot.mysql.open();
	ResultSet speakinguser = OresomeBot.mysql.query("SELECT recipient FROM tellmessages WHERE recipient='" + speaker + "'");
	speakinguser.next();
	String speakinguserResult = speakinguser.getString("recipient");
  
	if (speaker.equals(speakinguserResult)) {

	    ResultSet getRecipient = OresomeBot.mysql.query("SELECT recipient FROM tellmessages WHERE recipient='" + speaker + "'");
	    getRecipient.next();
	    String getRecipientResult = getRecipient.getString("recipient");
	    
	    ResultSet getSender = OresomeBot.mysql.query("SELECT sender FROM tellmessages WHERE recipient='" + speaker + "'");
	    getSender.next();
	    
	    ResultSet getMessage = OresomeBot.mysql.query("SELECT message FROM tellmessages WHERE recipient='" + speaker + "'");
	    getMessage.next();
	    
	   
	    while(speakinguser.next()){
		
	
		 Integer id = new Integer(speakinguser.getInt("id"));
		 String name = new String(speakinguser.getString("recipient"));
		 String sender = new String(speakinguser.getString("sender"));
		 String message = new String(speakinguser.getString("message"));

		    String getMessageResult = getMessage.getString("message");
		    String getSenderResult = getSender.getString("sender");
		 
		    
		    
		 
		   String messages = "[Message] Message from " + getSenderResult + " : " + getMessageResult;
	    event.respond(messages);
		}
	    
	  
	    
	   OresomeBot.mysql.close();
	
	} else {
	    
	    
	}
	
  }

}