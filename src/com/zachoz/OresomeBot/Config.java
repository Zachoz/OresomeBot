package com.zachoz.OresomeBot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config  {

    public static Properties config = new Properties();
    static String nick;
    static String user;
    static String server;
    static int port;
    static String password;
    static String realname;
    static String[] channels;
    public static String[] admins;

    public static void loadConfiguration() throws FileNotFoundException, IOException {
	config.load(new FileInputStream("OresomeBot.properties"));
	nick = config.getProperty("nick");
	user = config.getProperty("user");
	server = config.getProperty("server");
	port = Integer.parseInt(config.getProperty("port"));
	password = config.getProperty("password");
	channels = config.getProperty("channels").split(",");
	realname = config.getProperty("realname");
	admins = config.getProperty("admins").split(",");

    }
}