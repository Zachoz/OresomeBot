package com.zachoz.OresomeBot;


import java.io.FileNotFoundException;
import java.io.IOException;

import org.pircbotx.PircBotX;

import com.zachoz.OresomeBot.commands.*;

public class OresomeBot {
    


    public static PircBotX bot = new PircBotX();
    public static void main(String[] args) throws Exception, FileNotFoundException, IOException {

	

	
        
     
	try {
	    
	   Config.loadConfiguration(); 
	   
	} catch (FileNotFoundException ex) {
	    // This needs to generate the file if it doesn't exist.
	}
	
        
        // Bot configuration.
        bot.setVersion(Config.realname);
        bot.setLogin(Config.user);
        bot.setName(Config.nick);
        bot.identify(Config.password);
        bot.setVerbose(true);
        bot.connect(Config.server, Config.port); 
        
       
    
        for (int i = 0 ; i < Config.channels.length; i++ ) {
           bot.joinChannel(Config.channels[i]);
         }

        bot.getListenerManager().addListener(new Hello());
        bot.getListenerManager().addListener(new joinCommand());
        bot.getListenerManager().addListener(new partCommand());
        bot.getListenerManager().addListener(new banCommand());
        bot.getListenerManager().addListener(new unbanCommand());
        bot.getListenerManager().addListener(new kickCommand());
        bot.getListenerManager().addListener(new kickbanCommand());
        bot.getListenerManager().addListener(new muteCommand());
        bot.getListenerManager().addListener(new helpCommand());
        bot.getListenerManager().addListener(new infoCommand());
        bot.getListenerManager().addListener(new sayCommand());
        bot.getListenerManager().addListener(new reloadCommand());
        bot.getListenerManager().addListener(new nickCommand());
    }
    



}

