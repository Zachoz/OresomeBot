package com.zachoz.OresomeBot;
//Java imports.
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

//PircBotX imports.
import org.pircbotx.PircBotX;

//OresomeBot imports.
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

        bot.getListenerManager().addListener(new CleverBot());
        bot.getListenerManager().addListener(new JoinCommand());
        bot.getListenerManager().addListener(new RelayTellMessages());
        bot.getListenerManager().addListener(new PartCommand());
        bot.getListenerManager().addListener(new BanCommand());
        bot.getListenerManager().addListener(new UnbanCommand());
        bot.getListenerManager().addListener(new KickCommand());
        bot.getListenerManager().addListener(new KickbanCommand());
        bot.getListenerManager().addListener(new MuteCommand());
        bot.getListenerManager().addListener(new HelpCommand());
        bot.getListenerManager().addListener(new InfoCommand());
        bot.getListenerManager().addListener(new SayCommand());
        bot.getListenerManager().addListener(new ReloadCommand());
        bot.getListenerManager().addListener(new NickCommand());
        bot.getListenerManager().addListener(new TellCommand());
        bot.getListenerManager().addListener(new AutoopCommand());
        bot.getListenerManager().addListener(new DeautoopCommand());
        bot.getListenerManager().addListener(new AutovoiceCommand());
        bot.getListenerManager().addListener(new DeautovoiceCommand());
        bot.getListenerManager().addListener(new OpmeCommand());
        bot.getListenerManager().addListener(new DeopmeCommand());
        
        
 
        
        
        
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
