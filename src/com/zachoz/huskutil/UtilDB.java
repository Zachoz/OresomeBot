package com.zachoz.huskutil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zachoz.OresomeBot.OresomeBot;
import com.zachoz.OresomeBot.Database.MySQL;

public class UtilDB {

    static MySQL mysql = new MySQL(OresomeBot.logger,
            "[OresomeBot]", OresomeBot.mysql_host,
            OresomeBot.mysql_port, "bans",
            OresomeBot.mysql_user, OresomeBot.mysql_password);

    public static ResultSet query(String query) {
        Connection c = mysql.open();
        Statement s;
        try {
            s = c.createStatement();
            return s.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
