package com.zachoz.OresomeBot.tell;

import com.zachoz.OresomeBot.core.ChannelCommand;
import com.zachoz.OresomeBot.core.PermissionLevel;
import com.zachoz.OresomeBot.core.PrivateMessageCommand;
import com.zachoz.OresomeBot.core.Utility;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

public class TellCommands {

    @ChannelCommand(name = "tell",
            description = "Leave a message for a user",
            usage = ".tell <User> <Message>",
            permissionLevel = PermissionLevel.REGULAR,
            minArgs = 2)
    public static void tell(MessageEvent event, String[] args) {
        TellMessage message = new TellMessage(
                event.getUser().getNick(),
                args[0],
                Utility.getJoinedStrings(1, args),
                MessageType.CHANNEL
        );

        TellManager.addTellMessage(message);
        event.respond("I'll deliver that message to " + args[0] + " when I see them around next!");
    }

    @PrivateMessageCommand(name = "tell",
            description = "Leave a message for a user",
            usage = ".tell <User> <Message>",
            permissionLevel = PermissionLevel.REGULAR,
            minArgs = 2)
    public static void tell(PrivateMessageEvent event, String[] args) {
        TellMessage message = new TellMessage(
                event.getUser().getNick(),
                args[0],
                Utility.getJoinedStrings(1, args),
                MessageType.PRIVATE
        );

        TellManager.addTellMessage(message);
        event.respond("I'll deliver that message to " + args[0] + " when I see them around next!");
    }

}
