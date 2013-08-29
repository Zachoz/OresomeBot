package com.zachoz.OresomeBot.commands;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import com.zachoz.huskutil.UtilDB;


public class CheckBanCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) {

        String[] line = event.getMessage().split(" ");

        if (event.getMessage().contains("!checkban ")) {
            String player = line[1];
            String server = line[2];
            if(server.equalsIgnoreCase("battle")) {
                battleBan(player);
            } else if (server.equalsIgnoreCase("smp")) {
                smpBan(player);
            } else if (server.equalsIgnoreCase("onslaught")) {
                event.respond(onslaughtBan(player));
            } else if (server.equalsIgnoreCase("pvp")) {
                event.respond(pvpBan(player));
            } else {
                event.respond(event.getUser().getNick() + "Unknown paramater [2] Server doesn't exist! !checkban [player] [smp|pvp|battle|onslaught]");
            }
        }

    }

    private String pvpBan(String player) {
        ResultSet rs = null;
        String query = "SELECT * FROM `bm_bans_pvp` WHERE `banned` = '" + player + "';";
        try {
            rs = UtilDB.query(query);
            rs.next();
            if(rs.getString(player) != null) return player + " is banned from PvP, banned by " + rs.getString("banned_by") + " for the reason of '" + rs.getString("ban_reason") + "'.";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return player + " is not banned from PvP.";
    }

    private String onslaughtBan(String player) {
        ResultSet rs = null;
        String query = "SELECT * FROM `mb_bans_onslaught` WHERE `banned` = '" + player + "';";
        try {
            rs = UtilDB.query(query);
            rs.next();
            if(rs.getString(player) != null) return player + " is banned from Onslaught, banned by " + rs.getString("banned_by") + " for the reason of '" + rs.getString("ban_reason") + "'.";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return player + " is not banned from PvP.";
    }

    private String smpBan(String player) {
        ResultSet rs = null;
        String query = "SELECT * FROM `mb_bans_smp` WHERE `banned` = '" + player + "';";
        try {
            rs = UtilDB.query(query);
            rs.next();
            if(rs.getString(player) != null) return player + " is banned from SMP, banned by " + rs.getString("banned_by") + " for the reason of '" + rs.getString("ban_reason") + "'.";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return player + " is not banned from PvP.";
    }

    private String battleBan(String player) {
        ResultSet rs = null;
        String query = "SELECT * FROM `mb_bans_battles` WHERE `banned` = '" + player + "';";
        try {
            rs = UtilDB.query(query);
            rs.next();
            if(rs.getString(player) != null) return player + " is banned from Battles, banned by " + rs.getString("banned_by") + " for the reason of '" + rs.getString("ban_reason") + "'.";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return player + " is not banned from Battles.";
    }

}
