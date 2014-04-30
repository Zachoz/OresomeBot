package com.zachoz.OresomeBot;

import com.zachoz.OresomeBot.core.Utility;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.*;

import com.google.code.chatterbotapi.ChatterBot;
import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotSession;
import com.google.code.chatterbotapi.ChatterBotType;

@SuppressWarnings("rawtypes")
public class CleverBot extends ListenerAdapter {

    private ChatterBotFactory factory;
    private ChatterBot cleverbot;
    private ChatterBotSession botsession;

    public static boolean cleverbotEnabled = true;

    public CleverBot() {
        try {
            factory = new ChatterBotFactory();
            cleverbot = factory.create(ChatterBotType.CLEVERBOT);
            botsession = cleverbot.createSession();
            System.out.println("CleverBot successfully initiated!");
        } catch (Exception ex) {
            System.out.println("Failed to initiate CleverBot!");
            ex.printStackTrace();
        }
    }

    public void onMessage(MessageEvent event) throws Exception {
        if (event.getMessage().startsWith(OresomeBot.getBot().getNick() + ": ") ||
                event.getMessage().startsWith(OresomeBot.getBot().getNick() + ", ")) {
            if (cleverbotEnabled) {
                String s = botsession.think(Utility.getJoinedStrings(1, event.getMessage().split(" ")));
                event.respond(s);
            }
        }

    }
}