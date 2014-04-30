package com.zachoz.OresomeBot.commands;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zachoz.OresomeBot.core.ChannelCommand;
import com.zachoz.OresomeBot.core.PermissionLevel;
import com.zachoz.OresomeBot.oresomecraft.forums.ForumManager;
import com.zachoz.OresomeBot.oresomecraft.forums.ForumThread;
import com.zachoz.OresomeBot.oresomecraft.forums.NodeView;
import org.pircbotx.hooks.events.MessageEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class OresomeCraftCommands {

    @ChannelCommand(name = "news",
            description = "Shows the latest OresomeCraft news",
            usage = ".news",
            permissionLevel = PermissionLevel.REGULAR)
    public static void latestNews(MessageEvent event, String[] args) {
        ForumThread thread = new NodeView(ForumManager.NEWS_NODE_ID).build().getThreads().get(0);
        event.respond("Latest news: " + thread.getTitle() + " - " + thread.getLink());
    }

    @ChannelCommand(name = "stats",
            description = "Shows battle statisitcs of a player",
            usage = ".stats <player>",
            permissionLevel = PermissionLevel.REGULAR,
            minArgs = 1)
    public static void stats(MessageEvent event, String[] args) {
        try {
            URL url = new URL("http://mc.oresomecraft.com/battleapi.php?name=" + args[0].replaceAll("'", ""));
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
                    " | Infection wins: " + jsonStats.get("infection_wins").toString()) +
                    " | Highest Killstreak: " + jsonStats.get("highest_killstreak").toString()).replace('"', '/').replaceAll("/", "");

            event.respond(message);
        } catch (Exception ex) {
            ex.printStackTrace();
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
