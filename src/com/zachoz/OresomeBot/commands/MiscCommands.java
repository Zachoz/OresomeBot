package com.zachoz.OresomeBot.commands;

import com.zachoz.OresomeBot.core.ChannelCommand;
import com.zachoz.OresomeBot.core.PermissionLevel;
import com.zachoz.OresomeBot.core.PrivateMessageCommand;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

public class MiscCommands {

    @ChannelCommand(name = "ping",
            description = "Sends back pong!",
            permissionLevel = PermissionLevel.REGULAR)
    public static void ping(MessageEvent event, String[] args) {
        event.respond("pong!");
    }

    @PrivateMessageCommand(name = "ping",
            description = "Sends back pong!",
            permissionLevel = PermissionLevel.REGULAR)
    public static void ping(PrivateMessageEvent event, String[] args) {
        event.respond("pong!");
    }

}
