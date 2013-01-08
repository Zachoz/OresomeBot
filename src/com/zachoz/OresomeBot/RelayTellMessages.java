/*
 * Yes, I know this doesn't work properly. But it _kinda_ works...
 * 
 */

package com.zachoz.OresomeBot;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class RelayTellMessages extends ListenerAdapter {

    public void onMessage(MessageEvent event) throws SQLException {
        String currentchannel = event.getChannel().getName();
        String speaker = event.getUser().getNick();
        OresomeBot.mysql.open();
        ResultSet rs = OresomeBot.mysql.query("SELECT * FROM tellmessages WHERE recipient='" + speaker + "'");
      
        if (rs.next()) { // if they actually have a result set and move it to the first row
            // we got the first one, output the message
            //Integer id = new Integer(rs.getInt("id"));
            //String name = new String(rs.getString("recipient"));
            String sender = new String(rs.getString("sender"));
            String message = new String(rs.getString("message"));
            String messages = "[Message] Message from " + sender + " : " + message;
            event.respond(messages);
            // while there are more messages, send them
            while (rs.next()) {
                //id = rs.getInt("id");
                //name = rs.getString("recipient");
                sender = rs.getString("sender");
                message = rs.getString("message");
                messages = "[Message] Message from " + sender + ": " + message;
                event.respond(messages);
            }
        }
        OresomeBot.mysql.query("DELETE FROM tellmessages WHERE recipient='" + speaker + "'");
        OresomeBot.mysql.close();

}
}