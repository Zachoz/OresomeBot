package com.zachoz.AusfragBot.commands;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import com.zachoz.AusfragBot.AusfragBot;

@SuppressWarnings("rawtypes")
public class ServersCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) {
	if (event.getMessage().equals("!servers")) {
	    String currentchannel = event.getChannel().getName();

	    AusfragBot.bot.sendMessage(currentchannel, "------------------- Ausfrag servers --------------------");
	    AusfragBot.bot.sendMessage(currentchannel, "Web servers: Webby");
	    AusfragBot.bot.sendMessage(currentchannel, "Game servers: Alpha, Bravo, Charlie, Delta, Echo, Foxtrot");
	    AusfragBot.bot.sendMessage(currentchannel, "Voice servers: Vocal");
	    AusfragBot.bot.sendMessage(currentchannel, "------------------- Ausfrag servers --------------------");
	}
    }
}