package com.zachoz.OresomeBot;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

import com.zachoz.OresomeBot.commands.*;
import com.zachoz.OresomeBot.core.CommandProcessor;
import com.zachoz.OresomeBot.core.InternalEventHandler;
import com.zachoz.OresomeBot.oresomecraft.forums.ForumManager;
import com.zachoz.OresomeBot.tell.TellCommands;
import com.zachoz.OresomeBot.tell.TellManager;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.UtilSSLSocketFactory;
import org.pircbotx.exception.IrcException;

import com.zachoz.OresomeBot.Database.*;

/*
    OresomeBot IRC bot
    @author Zach de Koning (Zachoz)
 */
public class OresomeBot {

    private static PircBotX bot;
    public final static Logger logger = Logger.getLogger("OresomeBot");

    public static String mysql_host;
    public static String mysql_db;
    public static String mysql_user;
    public static String mysql_password;
    public static String mysql_port;
    public static MySQL mysql;

    @SuppressWarnings("rawtypes")
    public static void main(String[] args) {

        // Load properties file.
        try {
            Config.loadConfiguration();
        } catch (IOException ex) {
            System.out.println("No config file exists! Generating now!");
            Config.copyConfig(); // Generate a new config
            try {
                Config.loadConfiguration();
            } catch (Exception exception) {
                // Yeah we can't really go on from here.
                System.out.println("Unable to create a config file, shutting down!");
                System.exit(1);
            }
        }

        registerCommands();

        TellManager.loadMessagesFromDisk();

        if (Config.forumThreadChecker) ForumManager.initiate();

        // Build basic configuration
        Configuration.Builder configurationBuilder = new Configuration.Builder()
                .setAutoNickChange(true)
                .setVersion("OresomeBot IRC Bot, by Zachoz.")
                .setLogin(Config.user)
                .setName(Config.nick)
                .setMessageDelay(Config.messagedelay)
                .setServerHostname(Config.server)
                .setServerPort(Config.port)
                .setServerPassword(Config.serverpassword);

        if (!Config.password.equals("")) configurationBuilder.setNickservPassword(Config.password);
        if (Config.SSL) configurationBuilder.setSocketFactory(new UtilSSLSocketFactory().trustAllCertificates());

        // Add listeners
        configurationBuilder.addListener(new CommandProcessor())
                .addListener(new InternalEventHandler())
                .addListener(new CleverBot())
                .addListener(new TellManager());

        bot = new PircBotX(configurationBuilder.buildConfiguration());

        try {
            bot.startBot();

            //setupDatabase();

            //RelayTellMessages.mysql.open();
            //SeenManager.mysql.open();

        } catch (IrcException e) {
            // TODO: Fix itself? I dunno...
        } catch (IOException e) {
            // TODO: hmm...
        }

        endCommand();
    }

    private static void registerCommands() {
        CommandProcessor.registerCommandClass(PunishmentCommands.class);
        CommandProcessor.registerCommandClass(AdminCommands.class);
        CommandProcessor.registerCommandClass(MinecraftCommands.class);
        CommandProcessor.registerCommandClass(MiscCommands.class);
        CommandProcessor.registerCommandClass(OresomeCraftCommands.class);
        CommandProcessor.registerCommandClass(GoogleCommand.class);
        CommandProcessor.registerCommandClass(TellCommands.class);
        CommandProcessor.registerCommandClass(ForumCommands.class);
    }

    public static PircBotX getBot() {
        return bot;
    }

    public static void endCommand() {
        Scanner reader = new Scanner(System.in);
        String command = reader.nextLine();
        if (command.equals("end")) {
            // Yes this is dodgy, I'll fix it later, but at least the damn thing stops!
            bot.stopBotReconnect();
            bot.sendIRC().quitServer("Shutting down");
            System.out.println("Bot shutting down! Cya!");
            System.exit(0);
        }

    }

}
