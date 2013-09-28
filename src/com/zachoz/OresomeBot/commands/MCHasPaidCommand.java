package com.zachoz.OresomeBot.commands;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@SuppressWarnings("rawtypes")
public class MCHasPaidCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) {
        String[] args = event.getMessage().split(" ");
        if (event.getMessage().startsWith(".haspaid ")) {
            try {
                URL url = new URL("https://minecraft.net/haspaid.jsp?user=" + args[1]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // Open URL connection
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                if (in.readLine().equals("true")) {
                    event.respond(args[1] + " is a premium Minecraft account!");
                } else {
                    event.respond(args[1] + " is NOT a premium Minecraft account!");
                }

            } catch (Exception ex) {
                event.respond("Something went wrong while trying to check!");
            }
        }
    }

}