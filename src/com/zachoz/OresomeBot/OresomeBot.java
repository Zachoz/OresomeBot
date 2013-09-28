package com.zachoz.OresomeBot;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

import org.pircbotx.PircBotX;
import org.pircbotx.UtilSSLSocketFactory;
import org.pircbotx.exception.IrcException;

import com.zachoz.OresomeBot.Database.*;
import com.zachoz.OresomeBot.commands.*;

public class OresomeBot {

    public static PircBotX bot = new PircBotX();
    public final static Logger logger = Logger.getLogger("OresomeBot");

    public static String mysql_host;
    public static String mysql_db;
    public static String mysql_user;
    public static String mysql_password;
    public static String mysql_port;
    public static MySQL mysql;

    public static void main(String[] args) {

        // Load properties file.
        try {
            Config.loadConfiguration();
        } catch (IOException ex) {
            // This needs to generate the file if it doesn't exist.
        }

        try {
            // Connection:
            // Auto change nick if already taken.
            bot.setAutoNickChange(true);
            // Set bot version ("realname")
            bot.setVersion("OresomeBot IRC Bot, by Zachoz.");
            // Set login username.
            bot.setLogin(Config.user);
            // Set initial nickname
            bot.setName(Config.nick);
            // Identify with NickServ
            bot.identify(Config.password);
            // Output a TON if info to console.
            bot.setVerbose(true);

            // Connect to the IRC server
            if (Config.SSL && !Config.serverpassword.isEmpty()) {
                bot.connect(Config.server, Config.port, Config.serverpassword, new UtilSSLSocketFactory().trustAllCertificates());
            } else if (Config.SSL && Config.serverpassword.isEmpty()) {
                bot.connect(Config.server, Config.port, new UtilSSLSocketFactory().trustAllCertificates());
            } else {
                bot.connect(Config.server, Config.port);
            }

            // Set bot message delay
            bot.setMessageDelay(Config.messagedelay);
            // Auto reconnect to IRC server if disconnected
            bot.setAutoReconnect(true);
            // Auto rejoin channels if disconnected
            bot.setAutoReconnectChannels(true);
            // Join channels specified in config.
            joinChannels();
            // Setup MySQL DB.
            setupDatabase();

            RelayTellMessages.mysql.open();
            SeenManager.mysql.open();

        } catch (IrcException e) {
            // TODO: Fix itself? I dunno...
        } catch (IOException e) {
            // TODO: hmm...
        }

        // Load listeners
        loadListeners();
        endCommand();
    }

    public static void joinChannels() {
        // Join specified channels
        for (int i = 0; i < Config.channels.length; i++) {
            bot.joinChannel(Config.channels[i]);
        }
    }

    public static void loadListeners() {

        // Load all commands & other listeners.
        bot.getListenerManager().addListener(new ReloadCommand());
        bot.getListenerManager().addListener(new JoinCommand());
        bot.getListenerManager().addListener(new RelayTellMessages());
        bot.getListenerManager().addListener(new WelcomeMessage());
        bot.getListenerManager().addListener(new PartCommand());
        bot.getListenerManager().addListener(new BanCommand());
        bot.getListenerManager().addListener(new UnbanCommand());
        bot.getListenerManager().addListener(new KickCommand());
        bot.getListenerManager().addListener(new KickbanCommand());
        bot.getListenerManager().addListener(new MuteCommand());
        bot.getListenerManager().addListener(new HelpCommand());
        bot.getListenerManager().addListener(new CheckCommand());
        bot.getListenerManager().addListener(new InfoCommand());
        bot.getListenerManager().addListener(new SayCommand());
        bot.getListenerManager().addListener(new NickCommand());
        bot.getListenerManager().addListener(new TellCommand());
        bot.getListenerManager().addListener(new AutoopCommand());
        bot.getListenerManager().addListener(new DeautoopCommand());
        bot.getListenerManager().addListener(new AutovoiceCommand());
        bot.getListenerManager().addListener(new DeautovoiceCommand());
        bot.getListenerManager().addListener(new OpmeCommand());
        bot.getListenerManager().addListener(new DeopmeCommand());
        bot.getListenerManager().addListener(new CleverbotToggleCommand());
        bot.getListenerManager().addListener(new SeenManager());
        bot.getListenerManager().addListener(new SeenCommand());
        bot.getListenerManager().addListener(new GoogleCommand());
        bot.getListenerManager().addListener(new SendRawLineCommand());
        bot.getListenerManager().addListener(new StatsCommand());
        bot.getListenerManager().addListener(new MCPingCommand());

        try {
            bot.getListenerManager().addListener(new CleverBot());
        } catch (Exception e) {
            // yay, no channel spam
        }

    }

    private static void setupDatabase() {
        mysql_host = Config.mysql_host;
        mysql_db = Config.mysql_db;
        mysql_user = Config.mysql_user;
        mysql_password = Config.mysql_password;
        mysql_port = Config.mysql_port;

        mysql = new MySQL(logger, "[OresomeBot]", mysql_host, mysql_port,
                mysql_db, mysql_user, mysql_password);

        System.out.println("Connecting to MySQL database...");
        mysql.open();

        if (mysql.checkConnection()) {
            System.out.println("Successfully connected to database!");

            if (!mysql.checkTable("tellmessages")) {
                System.out.println("Creating table 'tellmessages' in database " + mysql_db);
                mysql.createTable("CREATE TABLE tellmessages ( id int NOT NULL AUTO_INCREMENT, channel VARCHAR(32) NOT NULL, sender VARCHAR(32) NOT NULL, recipient VARCHAR(32) NOT NULL, message VARCHAR(32) NOT NULL, PRIMARY KEY (id) ) ENGINE=MyISAM;");
            }
            if (!mysql.checkTable("oresomejoinedusers")) {
                System.out.println("Creating table 'oresomejoinedusers' in database " + mysql_db);
                mysql.createTable("CREATE TABLE oresomejoinedusers (id int NOT NULL AUTO_INCREMENT, users VARCHAR(32) NOT NULL, PRIMARY KEY (id) ) ENGINE=MyISAM;");
            }
            if (!mysql.checkTable("seenusers")) {
                System.out.println("Creating table 'seenusers' in database " + mysql_db);
                mysql.createTable("CREATE TABLE seenusers (id int NOT NULL AUTO_INCREMENT, user VARCHAR(32) NOT NULL, lastseen VARCHAR(32) NOT NULL, PRIMARY KEY (id) ) ENGINE=MyISAM;");
            }
        } else {
            System.out.println("Error connecting to database, there'll most likely be a lot of console errors!!");
        }
        mysql.close();
    }

    public static void endCommand() {
        Scanner reader = new Scanner(System.in);
        String command = reader.nextLine();
        if (command.equals("end")) {
            // Yes this is dodgy, I'll fix it later, but at least the damn thing stops!
            bot.disconnect();
            bot.shutdown();
            System.out.println("Bot shutting down! Cya!");
            System.exit(0);
        }

    }

}
