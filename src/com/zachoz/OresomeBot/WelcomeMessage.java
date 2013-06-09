package com.zachoz.OresomeBot;

import java.sql.ResultSet;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;

import com.zachoz.OresomeBot.Database.MySQL;

@SuppressWarnings("rawtypes")
public class WelcomeMessage extends ListenerAdapter {

    public void onJoin(JoinEvent event) throws Exception {

        MySQL mysql = new MySQL(OresomeBot.logger,
                "[OresomeBot]", OresomeBot.mysql_host,
                OresomeBot.mysql_port, OresomeBot.mysql_db,
                OresomeBot.mysql_user, OresomeBot.mysql_password);

        mysql.open();
        String speaker = event.getUser().getNick();
        String currentchannel = event.getChannel().getName();

        ResultSet rs = mysql.query("SELECT * FROM oresomejoinedusers WHERE users='" + speaker + "'");

        if (currentchannel.equals(Config.primarychannel)) {

            if (rs.next()) {
                // Just ignore them.
            } else {

                mysql.query("INSERT INTO oresomejoinedusers (users) VALUES ('" + speaker + "')");
                mysql.close();

                event.respond("Hi, welcome to the OresomeCraft IRC channel, if you have a question please ask it and wait patiently for a reply. To get our attention make sure you ping us, just mention our name in your message and we'll be alerted.");
            }
        }
    }

}