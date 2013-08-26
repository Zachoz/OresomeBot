package com.zachoz.OresomeBot.commands;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class CheckCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) {

        String[] line = event.getMessage().split(" ");

        if (event.getMessage().contains(".check ")) {
            try {
                InetAddress checkme = InetAddress.getByName(line[1]);
                event.respond(event.getUser() + ": " + checkme.getHostName());
            } catch (UnknownHostException e) {
                event.respond("Either you're retarded or this guy has hacks.");
            }
        }
    }
}