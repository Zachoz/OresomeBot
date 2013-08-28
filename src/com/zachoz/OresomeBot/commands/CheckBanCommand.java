package com.zachoz.OresomeBot.commands;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

public class CheckBanCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) {

	String[] line = event.getMessage().split(" ");

	if (event.getMessage().contains("!checkban ")) {
	    String player = line[1];
	    String server = line[2];
	    
	    switch(server) {
	    case "battle":
		event.respond(battleBan(player));
	    case "smp":
		event.respond(smpBan(player));
	    case "onslaught":
		event.respond(onslaughtBan(player));
	    case "pvp":
		event.respond(pvpBan(player));
	    }
	}
	
    }

    private String pvpBan(String player) {
	ResultSet rs = null;
	String query = "SELECT * FROM `mb_bans_pvp` WHERE `banned` = '" + player + "';";
	try {
	    rs.next();
	    if(rs.getString(player) != null) return player + " is banned frm PvP.";
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	
	return player + " is not banned from PvP.";
    }

    private String onslaughtBan(String player) {
	ResultSet rs = null;
	String query = "SELECT * FROM `mb_bans_onslaught` WHERE `banned` = '" + player + "';";
	try {
	    rs.next();
	    if(rs.getString(player) != null) return player + " is banned from Onslaught, banned by " + rs.getString("banned_by");
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	
	return player + " is not banned from PvP.";
    }

    private String smpBan(String player) {
	ResultSet rs = null;
	String query = "SELECT * FROM `mb_bans_smp` WHERE `banned` = '" + player + "';";
	try {
	    rs.next();
	    if(rs.getString(player) != null) return player + " is banned from SMP, banned by " + rs.getString("banned_by");
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	
	return player + " is not banned from PvP.";
    }

    private String battleBan(String player) {
	ResultSet rs = null;
	String query = "SELECT * FROM `mb_bans_battles` WHERE `banned` = '" + player + "';";
	try {
	    rs.next();
	    if(rs.getString(player) != null) return player + " is banned from Battles, banned by " + rs.getString("banned_by");
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	
	return player + " is not banned from Battles.";
    }
    
}
