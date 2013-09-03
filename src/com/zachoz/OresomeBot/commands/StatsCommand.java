package com.zachoz.OresomeBot.commands;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zachoz.OresomeBot.Config;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

@SuppressWarnings("rawtypes")
public class StatsCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) {

        String[] args = event.getMessage().split(" ");
        if (event.getMessage().startsWith(".stats ")) {
            if (args.length >= 2) {
                try {
                    URL url = new URL(Config.siteURL + "/battleapi.php?name=" + args[1].replaceAll("'", ""));
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // Open URL connection
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb1 = new StringBuilder();
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) sb1.append(inputLine);

                    if (!new JsonParser().parse(sb1.toString()).getAsJsonObject().get("user_exists").toString().equals("true")) {
                        event.respond("User not found!");
                        return;
                    }

                    JsonObject jsonStats = new JsonParser().parse(sb1.toString()).getAsJsonObject().getAsJsonObject("stats");
                    String message = ("Battle stats for: " + jsonStats.get("username").toString() +
                            " - Kills: " + jsonStats.get("kills").toString() +
                            " | Deaths: " + jsonStats.get("deaths").toString() +
                            " | K/D: " + (roundDouble(Double.parseDouble(jsonStats.get("kills").toString().replace('"', '/').replaceAll("/", ""))
                            / roundDouble(Double.parseDouble(jsonStats.get("deaths").toString().replace('"', '/').replaceAll("/", "")))) +
                            " | FFA wins: " + jsonStats.get("ffa_wins").toString() +
                            " | Infection wins: " + jsonStats.get("infection_wins").toString())).replace('"', '/').replaceAll("/", "");

                    event.respond(message);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } else {
                event.respond("Please specific a user!");
            }
        }
    }

    private static double roundDouble(double d) {
        DecimalFormat format = new DecimalFormat("#.##");
        try {
            return Double.valueOf(format.format(d));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}