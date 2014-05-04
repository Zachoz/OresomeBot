package com.zachoz.OresomeBot.commands;

import com.zachoz.OresomeBot.core.ChannelCommand;
import com.zachoz.OresomeBot.core.PermissionLevel;
import com.zachoz.OresomeBot.core.PrivateMessageCommand;
import com.zachoz.OresomeBot.core.Utility;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

public class AdminCommands {

    @ChannelCommand(name = "join",
            description = "Joins a channel",
            usage = ".join <Channel>",
            permissionLevel = PermissionLevel.BOT_ADMIN,
            minArgs = 1)
    public static void join(MessageEvent event, String[] args) {
        event.getBot().sendIRC().joinChannel(args[0]);
    }

    @ChannelCommand(name = "part",
            description = "Parts a channel",
            usage = ".part <Channel>",
            permissionLevel = PermissionLevel.BOT_ADMIN,
            minArgs = 1)
    public static void part(MessageEvent event, String[] args) {
        event.getBot().sendRaw().rawLine("PART " + args[0]);
    }

    @ChannelCommand(name = "nick",
            description = "Changes the bot's nick",
            usage = ".nick <Nick>",
            permissionLevel = PermissionLevel.BOT_ADMIN,
            minArgs = 1)
    public static void nick(MessageEvent event, String[] args) {
        event.getBot().sendIRC().changeNick(args[0]);
    }

    @PrivateMessageCommand(name = "say",
            description = "Sends a message to a channel or user",
            usage = ".say <Channel/User> <Message>",
            permissionLevel = PermissionLevel.BOT_ADMIN,
            minArgs = 2)
    public static void say(PrivateMessageEvent event, String[] args) {
        event.getBot().sendIRC().message(args[0], Utility.getJoinedStrings(1, args));
    }
}
