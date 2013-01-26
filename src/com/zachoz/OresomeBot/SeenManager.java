package com.zachoz.OresomeBot;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PartEvent;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@SuppressWarnings("rawtypes")
public class SeenManager extends ListenerAdapter {
    
    public void onJoin(JoinEvent event) throws SQLException {
	String user = event.getUser().getNick();
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	String lastseen = dateFormat.format(date);
	OresomeBot.mysql.open();
	
	ResultSet rs = OresomeBot.mysql.query("SELECT * FROM seenusers WHERE user='" + user + "'");
	
	if(rs.next()) {
	    OresomeBot.mysql.query("UPDATE seenusers SET lastseen = '"+lastseen+"' WHERE user='"+ user +"'");
	} else {
	    
	   OresomeBot.mysql.query("INSERT INTO seenusers (user, lastseen) VALUES ('" + user + "', '" + lastseen + "') "); 
	   
	}
	OresomeBot.mysql.close();
    }
    
    public void onPart(PartEvent event) {
	String user = event.getUser().getNick();
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	String lastseen = dateFormat.format(date);
	
	OresomeBot.mysql.open();
	OresomeBot.mysql.query("UPDATE seenusers SET lastseen = '"+lastseen+"' WHERE user='"+ user +"'");
	OresomeBot.mysql.close();
	
    }
    
    public void onMessage(MessageEvent event) {
	OresomeBot.mysql.open();
	String user = event.getUser().getNick();
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	String lastseen = dateFormat.format(date);
	try {
	OresomeBot.mysql.query("UPDATE seenusers SET lastseen = '"+lastseen+"' WHERE user='"+ user +"'");
	} catch (Exception e){
	    
	}
	
    }

}
