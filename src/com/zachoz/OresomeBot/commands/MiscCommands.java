package com.zachoz.OresomeBot.commands;

import com.zachoz.OresomeBot.core.ChannelCommand;
import com.zachoz.OresomeBot.core.PermissionLevel;
import com.zachoz.OresomeBot.core.PrivateMessageCommand;
import com.zachoz.OresomeBot.oresomecraft.forums.ForumManager;
import com.zachoz.OresomeBot.oresomecraft.forums.NodeView;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

public class MiscCommands {

    @ChannelCommand(name = "ping",
            description = "Sends back pong!",
            permissionLevel = PermissionLevel.OPERATOR)
    public static void ping(MessageEvent event, String[] args) {
        event.respond("pong!");
    }

    @PrivateMessageCommand(name = "ping",
            description = "Sends back pong!",
            permissionLevel = PermissionLevel.REGULAR)
    public static void ping(PrivateMessageEvent event, String[] args) {
        event.respond("pong!");
    }

    @ChannelCommand(name = "debug",
            description = "Sends back pong!",
            permissionLevel = PermissionLevel.REGULAR)
    public static void debug(MessageEvent event, String[] args) {
        ForumManager.generalChat = new NodeView(2).build();
    }

    @ChannelCommand(name = "debug1",
            description = "Sends back pong!",
            permissionLevel = PermissionLevel.REGULAR)
    public static void debug1(MessageEvent event, String[] args) {
        event.respond(ForumManager.newThread(ForumManager.generalChat, new NodeView(2).build()));
    }

}
