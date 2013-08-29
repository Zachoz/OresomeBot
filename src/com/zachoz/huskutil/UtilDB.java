package com.zachoz.huskutil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zachoz.OresomeBot.Config;

public class UtilDB {

    public static ResultSet query(String query) {
	Connection c = Config.mysql.open();
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
