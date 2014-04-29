package com.zachoz.OresomeBot.commands;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@SuppressWarnings("rawtypes")
public class HowLongUntilConverted extends ListenerAdapter {

    public void onMessage(MessageEvent event) {
        String[] args = event.getMessage().split(" ");
        if (event.getMessage().startsWith(".howlonguntilconversioniscomplete")) {
            try {
                URL url = new URL("http://mc.oresomecraft.com/conversionprogress.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // Open URL connection
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                event.respond("Users remaing to be converted: " + in.readLine());

            } catch (Exception ex) {
                event.respond("Something fucked up while checking. So we're probs pretty fucked.");
            }
        }
    }

}