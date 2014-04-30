package com.zachoz.OresomeBot.core;

import org.pircbotx.Channel;
import org.pircbotx.User;

public class Utility {

    public static User getUser(Channel channel, String user) {
        for (User usr : channel.getUsers()) if (usr.getNick().equals(user)) return usr;
        return null;
    }

    public static String getJoinedStrings(int initialIndex, String[] args) {
        StringBuilder buffer = new StringBuilder(args[initialIndex]);
        for (int i = initialIndex + 1; i < args.length; ++i) {
            buffer.append(" ").append(args[i]);
        }
        return buffer.toString();
    }

}
