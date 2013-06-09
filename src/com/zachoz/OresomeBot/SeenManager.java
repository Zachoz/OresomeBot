package com.zachoz.OresomeBot;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PartEvent;

import com.zachoz.OresomeBot.Database.MySQL;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@SuppressWarnings("rawtypes")
public class SeenManager extends ListenerAdapter {

    public void onJoin(JoinEvent event) throws SQLException {

        MySQL mysql = new MySQL(OresomeBot.logger,
                "[OresomeBot]", OresomeBot.mysql_host,
                OresomeBot.mysql_port, OresomeBot.mysql_db,
                OresomeBot.mysql_user, OresomeBot.mysql_password);

        String user = event.getUser().getNick();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String lastseen = dateFormat.format(date);
        mysql.open();

        ResultSet rs = mysql.query("SELECT * FROM seenusers WHERE user='" + user + "'");

        if (rs.next()) {
            mysql.query("UPDATE seenusers SET lastseen = '" + lastseen + "' WHERE user='" + user + "'");
        } else {

            mysql.query("INSERT INTO seenusers (user, lastseen) VALUES ('" + user + "', '" + lastseen + "') ");

        }
        mysql.close();
    }

    public void onPart(PartEvent event) {

        MySQL mysql = new MySQL(OresomeBot.logger,
                "[OresomeBot]", OresomeBot.mysql_host,
                OresomeBot.mysql_port, OresomeBot.mysql_db,
                OresomeBot.mysql_user, OresomeBot.mysql_password);

        String user = event.getUser().getNick();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String lastseen = dateFormat.format(date);

        mysql.open();
        mysql.query("UPDATE seenusers SET lastseen = '" + lastseen + "' WHERE user='" + user + "'");
        mysql.close();

    }

    public void onMessage(MessageEvent event) {

        MySQL mysql = new MySQL(OresomeBot.logger,
                "[OresomeBot]", OresomeBot.mysql_host,
                OresomeBot.mysql_port, OresomeBot.mysql_db,
                OresomeBot.mysql_user, OresomeBot.mysql_password);

        mysql.open();
        String user = event.getUser().getNick();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String lastseen = dateFormat.format(date);
        try {
            mysql.query("UPDATE seenusers SET lastseen = '" + lastseen + "' WHERE user='" + user + "'");
        } catch (Exception e) {

        }

    }

}
