package com.zachoz.AusfragBot;
//Java imports.
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

//PircBotX imports.
import org.pircbotx.PircBotX;

import com.zachoz.AusfragBot.commands.*;
//OresomeBot imports.


public class AusfragBot {

    public static PircBotX bot = new PircBotX();
    public final static Logger logger = Logger.getLogger("OresomeBot");
    
    public static void main(String[] args) throws Exception, FileNotFoundException, IOException {

	
	
 
	// Load properties file.
	try {
	    
	   Config.loadConfiguration(); 
	   
	} catch (FileNotFoundException ex) {
	    // This needs to generate the file if it doesn't exist.
	   
	}
        
        // Connection.
	bot.setAutoNickChange(true);
        bot.setVersion(Config.realname);
        bot.setLogin(Config.user);
        bot.setName(Config.nick);
        bot.identify(Config.password);
        bot.setVerbose(true);
        bot.connect(Config.server, Config.port); 

        // Join specified channels
        for (int i = 0 ; i < Config.channels.length; i++ ) {
           bot.joinChannel(Config.channels[i]);
         }
       
        // Load all commands & other listeners.
        bot.getListenerManager().addListener(new ReloadCommand());
        bot.getListenerManager().addListener(new JoinCommand());
        bot.getListenerManager().addListener(new PartCommand());
        bot.getListenerManager().addListener(new HelpCommand());
        bot.getListenerManager().addListener(new InfoCommand());
        bot.getListenerManager().addListener(new SayCommand());
        bot.getListenerManager().addListener(new NickCommand());
        bot.getListenerManager().addListener(new TicketCommand());
        bot.getListenerManager().addListener(new StatusCommand());
        bot.getListenerManager().addListener(new TcadminCommand());
        bot.getListenerManager().addListener(new CpanelCommand());
        bot.getListenerManager().addListener(new ServersCommand());
  
    }


}
