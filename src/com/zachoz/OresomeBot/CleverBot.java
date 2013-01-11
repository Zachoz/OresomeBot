package com.zachoz.OresomeBot;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.*;

import com.google.code.chatterbotapi.ChatterBot;
import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotSession;
import com.google.code.chatterbotapi.ChatterBotType;
import com.zachoz.OresomeBot.OresomeBot;

@SuppressWarnings("rawtypes")
public class CleverBot extends ListenerAdapter {

    private ChatterBotFactory factory;
    private ChatterBot cleverbot;
    private ChatterBotSession botsession;
    
    public static boolean cleverbotEnabled = true;

    public CleverBot() throws Exception {
	factory = new ChatterBotFactory();
	cleverbot = factory.create(ChatterBotType.CLEVERBOT);
	botsession = cleverbot.createSession();
    }

    public void onMessage(MessageEvent event) throws Exception {
	String message = event.getMessage();
	String[] ArrSay = message.split(" ");
	String outsay = "";

	for (int i = 2; i < ArrSay.length; i++) {
	    outsay += ArrSay[i];
	    if (i != ArrSay.length - 1) {
		outsay += " ";
	    }

	}

	if (event.getMessage().split(" ").length > 1) {

	    if (event.getMessage().startsWith(OresomeBot.bot.getNick() + ": ")) {
		if (cleverbotEnabled == true) {

		    String s = botsession.think(outsay);
		    event.respond(s);

		}
	    }

	}
    }
}