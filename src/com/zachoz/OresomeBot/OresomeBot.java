package com.zachoz.OresomeBot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;


import org.pircbotx.PircBotX;

import com.zachoz.OresomeBot.Database.MySQL;
import com.zachoz.OresomeBot.commands.*;

public class OresomeBot {
    


    public static PircBotX bot = new PircBotX();
    public final static Logger logger = Logger.getLogger("OresomeBot");
    
    
    static String mysql_host;
    static String mysql_db;
    static String mysql_user;
    static String mysql_password;
    static String mysql_port;
    public static MySQL mysql;
    

    
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
        
        setupDatabase();
        
       
    
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
        bot.getListenerManager().addListener(new tellCommand());
    }

  
    private static void setupDatabase() {
	mysql_host = Config.mysql_host;
	mysql_db = Config.mysql_db;
	mysql_user = Config.mysql_user;
	mysql_password = Config.mysql_password;
	mysql_port = Config.mysql_port;
 
		mysql = new MySQL(logger, "[OresomeBot]", mysql_host, mysql_port, mysql_db, mysql_user, mysql_password);
		
		System.out.println("Connecting to MySQL Database...");
		mysql.open();
		
		if (mysql.checkConnection()) {
		    System.out.println("Successfully connected to database!");
			
			if (!mysql.checkTable("tellmessage")) {
			    System.out.println("Creating table 'tellmessages' in database " + mysql_db);
				mysql.createTable("CREATE TABLE tellmessages ( id int NOT NULL AUTO_INCREMENT, channel VARCHAR(32) NOT NULL, sender VARCHAR(32) NOT NULL, recipient VARCHAR(32) NOT NULL, message VARCHAR(32) NOT NULL, PRIMARY KEY (id) ) ENGINE=MyISAM;");
			}
		} else {
		    System.out.println("Error connecting to database, there'll most likely be a lot of console errors!!");
		}
		mysql.close();
	}



}
