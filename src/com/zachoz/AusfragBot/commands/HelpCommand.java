package com.zachoz.AusfragBot.commands;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import com.zachoz.AusfragBot.AusfragBot;

@SuppressWarnings("rawtypes")
public class HelpCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) {
        if (event.getMessage().equals("!help")) {
                event.respond("Check your private messages for help!");
                AusfragBot.bot.sendMessage(event.getUser(), "---------------- AusfragBot IRC Bot Help ----------------");
                AusfragBot.bot.sendMessage(event.getUser(), "--------------------- User Commands ---------------------");
                AusfragBot.bot.sendMessage(event.getUser(), "!status - Show Ausfrag network and services statuses.");
                AusfragBot.bot.sendMessage(event.getUser(), "!ticket - Quick link to open a support ticket.");
                AusfragBot.bot.sendMessage(event.getUser(), "!tcadmin - Quick link to TCAdmin control panel.");
                AusfragBot.bot.sendMessage(event.getUser(), "!cpanel - Quick link to cPanel control panel.");
                AusfragBot.bot.sendMessage(event.getUser(), "!servers - View names of all Ausfrag server machines.");
                AusfragBot.bot.sendMessage(event.getUser(), "!help - PM you the help message you're reading right now.");
                AusfragBot.bot.sendMessage(event.getUser(), "---------------- Bot Admin only commands ----------------");
                AusfragBot.bot.sendMessage(event.getUser(), "!join <Channel> - Join an IRC channel.");
                AusfragBot.bot.sendMessage(event.getUser(), "!part <Channel> - Leave an IRC channel.");
                AusfragBot.bot.sendMessage(event.getUser(), "!say <Channel> <Message> - Say a message as the bot in a channel.");
                AusfragBot.bot.sendMessage(event.getUser(), "!reload - Reload configuration file.");
                AusfragBot.bot.sendMessage(event.getUser(), "---------------- AusfragBot IRC Bot Help ----------------");
	}
    }

}