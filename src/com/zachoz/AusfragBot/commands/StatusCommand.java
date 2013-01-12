package com.zachoz.AusfragBot.commands;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import com.zachoz.AusfragBot.AusfragBot;

@SuppressWarnings("rawtypes")
public class StatusCommand extends ListenerAdapter {

    List<String> tcadminOnline = new ArrayList<String>();
    ArrayList<String> tcadminOffline = new ArrayList<String>();
    ArrayList<String> ftpOnline = new ArrayList<String>();
    ArrayList<String> ftpOffline = new ArrayList<String>();
    
   
    
    public void onMessage(MessageEvent event) {
	if (event.getMessage().equals("!status")) {
	     String currentchannel = event.getChannel().getName();

	     AusfragBot.bot.sendMessage(currentchannel, "-------------- Ausfrag network status ---------------");
	    runQueries();
	    
	    String tcastatus = "";
	    for (String tcaStatus : tcadminOnline) {
	        tcastatus += tcaStatus + ", ";
	    }
	   AusfragBot.bot.sendMessage(currentchannel, "TCAdmin is online on: " + tcastatus);
	   
	   if(!tcadminOffline.isEmpty()) {
	    String tcastatusoffline = "";
	    for (String tcaStatusoff : tcadminOffline) {
		tcastatusoffline += tcaStatusoff + ", ";
	    }
	    AusfragBot.bot.sendMessage(currentchannel, "TCAdmin is offline on: " + tcastatusoffline);
	    
	    }
	   
	    String ftpstatus = "";
	    for (String ftpStatus : ftpOnline)  {
	        ftpstatus += ftpStatus + ", ";
	    }
	   AusfragBot.bot.sendMessage(currentchannel, "FTP is online on: " + ftpstatus);
	   
	   if(!ftpOffline.isEmpty()) {
	    String ftpstatusoffline = "";
	    for (String ftpStatusoffline : ftpOffline)  {
		ftpstatusoffline += ftpStatusoffline + ", ";
	    }
	    AusfragBot.bot.sendMessage(currentchannel, "TCAdmin is offline on: " + ftpstatusoffline);
	    
	    }
	   AusfragBot.bot.sendMessage(currentchannel, "Type '!servers' to view the names of all Ausfrag servers.");
	   AusfragBot.bot.sendMessage(currentchannel, "-------------- Ausfrag network status ---------------");
	}
    }
	    public void runQueries() {
	    
	    Socket s = null;
	    /*
	     * Check if TCAdmin is online on all servers:
	     */
	    
	    // Check for Alpha.
	    
	    try {
	        s = new Socket();
	        s.setReuseAddress(true);
	        SocketAddress sa = new InetSocketAddress("alpha.ausfrag.com", 8880);
	        s.connect(sa, 3000);
	        //event.respond("TCAdmin on Alpha is online!");
	        tcadminOnline.add("Alpha");
	    } catch (IOException e) {
		//event.respond("TCAdmin on is offline!");
	        tcadminOffline.add("Alpha");
	    } 
	    
	    
	    // Check for Bravo.
	    try {
	        s = new Socket();
	        s.setReuseAddress(true);
	        SocketAddress sa = new InetSocketAddress("bravo.ausfrag.com", 8880);
	        s.connect(sa, 3000);
	        //event.respond("TCAdmin on Bravo is online!");
	        tcadminOnline.add("Bravo");
	    } catch (IOException e) {
		//event.respond("TCAdmin on Bravo is offline!");
		tcadminOffline.add("Bravo");
	    } 
	    
	    
	    // Check for Charlie.
	    try {
	        s = new Socket();
	        s.setReuseAddress(true);
	        SocketAddress sa = new InetSocketAddress("charlie.ausfrag.com", 8880);
	        s.connect(sa, 3000);
	        //event.respond("TCAdmin on Charlie is online!");
	        tcadminOnline.add("Charlie");
	    } catch (IOException e) {
		//event.respond("TCAdmin on Charlie is offline!");
		tcadminOffline.add("Charlie");
	    } 
	    
	    
	    // Check for Delta.
	    try {
	        s = new Socket();
	        s.setReuseAddress(true);
	        SocketAddress sa = new InetSocketAddress("delta.ausfrag.com", 8880);
	        s.connect(sa, 3000);
	        //event.respond("TCAdmin on Delta is online!");
	        tcadminOnline.add("Delta");
	    } catch (IOException e) {
		//event.respond("TCAdmin on Delta is offline!");
		tcadminOffline.add("Delta");
	    } 
	    
	    
	    // Check for Echo.
	    try {
	        s = new Socket();
	        s.setReuseAddress(true);
	        SocketAddress sa = new InetSocketAddress("echo.ausfrag.com", 8880);
	        s.connect(sa, 3000);
	        //event.respond("TCAdmin on Echo is online!");
	        tcadminOnline.add("Echo");
	    } catch (IOException e) {
		//event.respond("TCAdmin on Echo is offline!");
		tcadminOffline.add("Echo");
	    } 
	    
	    
	    // Check for Foxtrot.
	    try {
	        s = new Socket();
	        s.setReuseAddress(true);
	        SocketAddress sa = new InetSocketAddress("foxtrot.ausfrag.com", 8880);
	        s.connect(sa, 3000);
	        //event.respond("TCAdmin on Foxtrot is online!");
	        tcadminOnline.add("Foxtrot");
	    } catch (IOException e) {
		//event.respond("TCAdmin on Foxtrot is offline!");
		tcadminOffline.add("Foxtrot");
	    } 
	    
	
	    
	    
	    
	    /*
	     * Check if FTP is online on all servers:
	     */
	    
            // Check for Alpha.
	  
	    try {
	        s = new Socket();
	        s.setReuseAddress(true);
	        SocketAddress sa = new InetSocketAddress("alpha.ausfrag.com", 21);
	        s.connect(sa, 3000);
	        ftpOnline.add("Alpha");
	    } catch (IOException e) {
		ftpOffline.add("Alpha");
	    } 
	    
	    
	    // Check for Bravo.
	    try {
	        s = new Socket();
	        s.setReuseAddress(true);
	        SocketAddress sa = new InetSocketAddress("bravo.ausfrag.com", 21);
	        s.connect(sa, 3000);
	        ftpOnline.add("Bravo");
	    } catch (IOException e) {
		ftpOffline.add("Bravo");
	    } 
	    
	    
	    // Check for Charlie.
	    try {
	        s = new Socket();
	        s.setReuseAddress(true);
	        SocketAddress sa = new InetSocketAddress("charlie.ausfrag.com", 21);
	        s.connect(sa, 3000);
	        ftpOnline.add("Charlie");
	    } catch (IOException e) {
		ftpOffline.add("Charlie");
	    } 
	    
	    
	    // Check for Delta.
	    try {
	        s = new Socket();
	        s.setReuseAddress(true);
	        SocketAddress sa = new InetSocketAddress("delta.ausfrag.com", 21);
	        s.connect(sa, 3000);
	        ftpOnline.add("Delta");
	    } catch (IOException e) {
		ftpOffline.add("Delta");
	    } 
	    
	    
	    // Check for Echo.
	    try {
	        s = new Socket();
	        s.setReuseAddress(true);
	        SocketAddress sa = new InetSocketAddress("echo.ausfrag.com", 21);
	        s.connect(sa, 3000);
	        ftpOnline.add("Echo");
	    } catch (IOException e) {
		ftpOffline.add("Echo");
	    } 
	    
	    
	    // Check for Foxtrot.
	    try {
	        s = new Socket();
	        s.setReuseAddress(true);
	        SocketAddress sa = new InetSocketAddress("foxtrot.ausfrag.com", 21);
	        s.connect(sa, 3000);
	        ftpOnline.add("Foxtrot");
	    } catch (IOException e) {
		ftpOffline.add("Foxtrot");
	}

    }

}
