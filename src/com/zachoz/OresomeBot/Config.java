package com.zachoz.OresomeBot;

import java.io.*;
import java.util.Properties;

import com.zachoz.OresomeBot.Database.MySQL;

public class Config {

    public static Properties config = new Properties();
    static String nick;
    static String user;
    static String server;
    static int port;
    static String password;
    static String serverpassword;
    static boolean SSL;
    static int messagedelay;
    public static String siteURL;
    public static String[] channels;
    public static String[] admins;
    public static String primarychannel;
    public static String oresomecraftAdminChannel;
    public static boolean forumThreadChecker;

    public static void loadConfiguration() throws IOException {
        config.load(new FileInputStream("OresomeBot.properties"));
        nick = config.getProperty("nick");
        user = config.getProperty("user");
        server = config.getProperty("server");
        port = Integer.parseInt(config.getProperty("port"));
        SSL = Boolean.parseBoolean(config.getProperty("SSL"));
        password = config.getProperty("password");
        serverpassword = config.getProperty("serverpassword");
        primarychannel = config.getProperty("primarychannel");
        channels = config.getProperty("channels").split(",");
        messagedelay = Integer.parseInt(config.getProperty("messagedelay"));
        admins = config.getProperty("admins").split(",");
        siteURL = config.getProperty("site_url");
        forumThreadChecker = Boolean.parseBoolean(config.getProperty("forum_thread_check"));
        oresomecraftAdminChannel = config.getProperty("oresomecraft_admin_channel");
    }

    public static void copyConfig() {
        InputStream stream = Config.class.getResourceAsStream("/OresomeBot.properties");
        OutputStream resStreamOut;
        int readBytes;
        byte[] buffer = new byte[4096];
        try {
            resStreamOut = new FileOutputStream(new File("OresomeBot.properties"));
            while ((readBytes = stream.read(buffer)) > 0) {
                resStreamOut.write(buffer, 0, readBytes);
            }

            stream.close();
            resStreamOut.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}