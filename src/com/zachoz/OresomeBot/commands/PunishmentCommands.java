package com.zachoz.OresomeBot.commands;

import com.zachoz.OresomeBot.core.ChannelCommand;
import com.zachoz.OresomeBot.core.PermissionLevel;
import com.zachoz.OresomeBot.core.Utility;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

public class PunishmentCommands {

    @ChannelCommand(name = "ban",
            description = "Bans a user/hostmask",
            usage = ".ban <User/Hostmask>",
            permissionLevel = PermissionLevel.VOICE,
            minArgs = 1)
    public static void ban(MessageEvent event, String[] args) {
        User user = Utility.getUser(event.getChannel(), args[0]);
        if (user != null) { // They're banning a logged in user
            event.getChannel().send().ban(user.getHostmask());
        } else { // They're banning a hostmask
            event.getChannel().send().ban(args[0]);
        }
    }

    @ChannelCommand(name = "kick",
            description = "Kicks a user",
            usage = ".kick <User> <reason>",
            permissionLevel = PermissionLevel.VOICE,
            minArgs = 2)
    public static void kick(MessageEvent event, String[] args) {
        User user = Utility.getUser(event.getChannel(), args[0]);
        if (user != null) {
            event.getChannel().send().kick(user, Utility.getJoinedStrings(1, args));
        } else {
            event.respond("User not found!");
        }
    }

    @ChannelCommand(name = "kickban",
            description = "Bans and kicks a user",
            usage = ".kickban <User> <Reason>",
            permissionLevel = PermissionLevel.VOICE,
            minArgs = 2)
    public static void kickban(MessageEvent event, String[] args) {
        User user = Utility.getUser(event.getChannel(), args[0]);
        if (user != null) { // They're banning a logged in user
            event.getChannel().send().ban(user.getHostmask());
            event.getChannel().send().kick(user, Utility.getJoinedStrings(1, args));
        } else {
            event.respond("User not found! Try .ban to ban hostmasks!");
        }
    }

    @ChannelCommand(name = "unban",
            description = "Unbans a user/hostmask",
            usage = ".unban <User/Hostmask>",
            permissionLevel = PermissionLevel.VOICE,
            minArgs = 1)
    public static void unban(MessageEvent event, String[] args) {
        User user = Utility.getUser(event.getChannel(), args[0]);
        if (user != null) { // They're unbanning a logged in user
            event.getChannel().send().unBan(user.getHostmask());
        } else { // They're unbanning a hostmask
            event.getChannel().send().unBan(args[0]);
        }
    }

    @ChannelCommand(name = "mute",
            description = "Mutes a user/hostmask",
            usage = ".mute <User/Hostmask>",
            permissionLevel = PermissionLevel.VOICE,
            minArgs = 1)
    public static void mute(MessageEvent event, String[] args) {
        User user = Utility.getUser(event.getChannel(), args[0]);
        if (user != null) { // They're muting a logged in user
            event.getChannel().send().setMode("+q ", user.getHostmask()); // Uses freenode quiet modes
        } else { // They're muting a hostmask
            event.getChannel().send().setMode("+q ", args[0]); // Uses freenode quiet modes
        }
    }

    @ChannelCommand(name = "unmute",
            description = "Unmutes a user/hostmask",
            usage = ".unmute <User/Hostmask>",
            permissionLevel = PermissionLevel.VOICE,
            minArgs = 1)
    public static void unmute(MessageEvent event, String[] args) {
        User user = Utility.getUser(event.getChannel(), args[0]);
        if (user != null) { // They're unmuting a logged in user
            event.getChannel().send().setMode("-q ", user.getHostmask()); // Uses freenode quiet modes
        } else { // They're unmuting a hostmask
            event.getChannel().send().setMode("-q ", args[0]); // Uses freenode quiet modes
        }
    }

}
