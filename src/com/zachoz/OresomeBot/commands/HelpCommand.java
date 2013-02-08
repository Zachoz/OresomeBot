package com.zachoz.OresomeBot.commands;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import com.zachoz.OresomeBot.OresomeBot;

@SuppressWarnings("rawtypes")
public class HelpCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) {
        if (event.getMessage().equals(".help")) {
                event.respond("Check your private messages for help!");
                OresomeBot.bot.sendMessage(event.getUser(), "---------------- OresomeBot IRC Bot Help ----------------");
                OresomeBot.bot.sendMessage(event.getUser(), "--------------------- User Commands ---------------------");
                OresomeBot.bot.sendMessage(event.getUser(), ".info - Show information about OresomeBot.");
                OresomeBot.bot.sendMessage(event.getUser(), ".tell <User> <Message> - Leave a message for another user.");
                OresomeBot.bot.sendMessage(event.getUser(), ".seen <User> - Show when a user was last seen.");
                OresomeBot.bot.sendMessage(event.getUser(), ".help - PM you the help message you're reading right now.");
                OresomeBot.bot.sendMessage(event.getUser(), ".google <Search> - Search something on google.");
                OresomeBot.bot.sendMessage(event.getUser(), "\"OresomeBot: <Your Message>\" - Speak to OresomeBot.");
                OresomeBot.bot.sendMessage(event.getUser(), "---------------- OP/voiced user commands ----------------");
                OresomeBot.bot.sendMessage(event.getUser(), "For channel admin commands type '.help admin'");
                OresomeBot.bot.sendMessage(event.getUser(), "---------------- Bot Admin only commands ----------------");
                OresomeBot.bot.sendMessage(event.getUser(), "For bot admin commands type '.help botadmin'");
                OresomeBot.bot.sendMessage(event.getUser(), "---------------- OresomeBot IRC Bot Help ----------------");
                
	} 
        else if (event.getMessage().equals(".help admin")) {
            event.respond("Check your private messages for help!");
	    OresomeBot.bot.sendMessage(event.getUser(), "---------------- OresomeBot IRC Bot Help ----------------");
	    OresomeBot.bot.sendMessage(event.getUser(), "---------------- OP/voiced user commands ----------------");
            OresomeBot.bot.sendMessage(event.getUser(), ".ban <User> - Ban a user from the IRC channel.");
            OresomeBot.bot.sendMessage(event.getUser(), ".unban <User> - Unban a user from the IRC channel.");
            OresomeBot.bot.sendMessage(event.getUser(), ".kick <User> - Kick a user from the IRC channel.");
            OresomeBot.bot.sendMessage(event.getUser(), ".kickban <User> - Kicks and bans a user from the IRC channel.");
            OresomeBot.bot.sendMessage(event.getUser(), ".mute <User> - Silence a user in the IRC channel.");
            OresomeBot.bot.sendMessage(event.getUser(), ".unmute <User> - Un-silence a user in the IRC channel.");
            OresomeBot.bot.sendMessage(event.getUser(), "---------------- OresomeBot IRC Bot Help ----------------");
	    
	}
        else if (event.getMessage().equals(".help botadmin")) {
            event.respond("Check your private messages for help!");
	    OresomeBot.bot.sendMessage(event.getUser(), "---------------- OresomeBot IRC Bot Help ----------------");
	    OresomeBot.bot.sendMessage(event.getUser(), "---------------- Bot Admin only commands ----------------");
            OresomeBot.bot.sendMessage(event.getUser(), ".join <Channel> - Join an IRC channel.");
            OresomeBot.bot.sendMessage(event.getUser(), ".part <Channel> - Leave an IRC channel.");
            OresomeBot.bot.sendMessage(event.getUser(), ".say <Channel> <Message> - Say a message as the bot in a channel.");
            OresomeBot.bot.sendMessage(event.getUser(), ".reload - Reload configuration file.");
            OresomeBot.bot.sendMessage(event.getUser(), ".cleverboton /.cleverbotoff - Toggle CleverBot.");
            OresomeBot.bot.sendMessage(event.getUser(), "---------------- OresomeBot IRC Bot Help ----------------");
	    
	}
    }

}