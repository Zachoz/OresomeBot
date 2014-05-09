package com.zachoz.OresomeBot.commands;

import com.zachoz.OresomeBot.core.PermissionLevel;
import com.zachoz.OresomeBot.core.PrivateMessageCommand;
import com.zachoz.OresomeBot.oresomecraft.forums.ForumAccount;
import com.zachoz.OresomeBot.oresomecraft.forums.ForumManager;
import com.zachoz.OresomeBot.oresomecraft.forums.alerts.Alert;
import com.zachoz.OresomeBot.oresomecraft.forums.alerts.AlertManager;
import org.apache.commons.lang3.StringUtils;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import java.util.ArrayList;

public class ForumCommands {

    @PrivateMessageCommand(name = "forumlogin",
            description = "Authenticates you to the forums",
            usage = ".forumlogin <Email> <Password>",
            permissionLevel = PermissionLevel.REGULAR,
            minArgs = 2)
    public static void forumlogin(PrivateMessageEvent event, String[] args) {
        ForumAccount account = new ForumAccount(args[0], args[1], event.getUser().getNick());

        if (ForumManager.forumAccounts.containsKey(event.getUser().getNick())) {
            event.respond("You're already logged in as " + ForumManager.forumAccounts.get(event.getUser().getNick()).getAssociatedNick() +
                    "! Please use '.forumlogout' first!");
            return;
        }

        if (account.authenticate()) {
            if (ForumManager.accountAlreadyExists(account) != null) {
                event.respond("We noticed another active session under the nick " +
                        ForumManager.accountAlreadyExists(account).getAssociatedNick() + "! So I've logged that out!");

                ForumManager.forumAccounts.remove(ForumManager.accountAlreadyExists(account).getAssociatedNick());
            }

            event.respond("Successfully logged into the forums!");
            event.respond("To logout use .forumlogout ! If you change your nick and want " +
                    "to deauthorise any of your previous nicks from using your forum account, re-run this command " +
                    "on your new nickname to logout any old sessions! You may use .forumlogout afterwards to completely " +
                    "remove all associations!");
            event.respond("Please ensure you're authenticated with NickServ! If you're not, please run .forumlogout now!");
            ForumManager.forumAccounts.put(event.getUser().getNick(), account);
        } else {
            event.respond("Authentication failed!");
        }
    }

    @PrivateMessageCommand(name = "forumlogout",
            description = "Logs you out of the forums",
            usage = ".forumlogout",
            permissionLevel = PermissionLevel.REGULAR)
    public static void forumlogout(PrivateMessageEvent event, String[] args) {
        if (ForumManager.forumAccounts.containsKey(event.getUser().getNick())) {
            ForumManager.forumAccounts.remove(event.getUser().getNick());
            event.respond("Successfully logged you out of the forums!");
        } else {
            event.respond("It appears there are no forum sessions under this nickname!");
            event.respond("If you were logged in on another nickname, please run '.forumlogin <email> <password>' to " +
                    "remove the old session! You may run .forumlogout after doing this to remove all associations!");
        }
    }

    @PrivateMessageCommand(name = "latestalerts",
            description = "Authenticates you to the forums",
            permissionLevel = PermissionLevel.REGULAR)
    public static void latestalerts(PrivateMessageEvent event, String[] args) {
        ForumAccount account = ForumManager.forumAccounts.get(event.getUser().getNick());

        if (account == null) {
            event.respond("You are not logged in! Use '.forumlogin <User> <Password>' to login!");
            return;
        }

        ArrayList<Alert> alerts = account.queryAlerts();
        if (!alerts.isEmpty()) {
            for (Alert alert : alerts) {
                event.respond(alert.getIRCMessage());
            }
        } else {
            event.respond("You have no recent alerts!");
        }
    }

    @PrivateMessageCommand(name = "checkalerts",
            description = "checks alerts",
            permissionLevel = PermissionLevel.REGULAR)
    public static void checkalerts(PrivateMessageEvent event, String[] args) {
        ForumAccount account = ForumManager.forumAccounts.get(event.getUser().getNick());

        if (account == null) {
            event.respond("You are not logged in! Use '.forumlogin <User> <Password>' to login!");
            return;
        }

        if (account.getAlerts() == null) {
            account.setAlerts(account.queryAlerts());
            event.respond("No alerts were previously cached, so I've now cached your latest alerts! " +
                    "To view your alerts use .latestalerts");
            return;
        }

        ArrayList<Alert> newAlerts = AlertManager.getNewAlerts(account);
        if (!newAlerts.isEmpty()) {
            for (Alert alert : newAlerts) {
                event.respond(alert.getIRCMessage());
            }
        } else {
            event.respond("You have no recent alerts!");
        }
    }

    @PrivateMessageCommand(name = "forumusers",
            description = "Lists logged in forum users",
            permissionLevel = PermissionLevel.BOT_ADMIN)
    public static void forumusers(PrivateMessageEvent event, String[] args) {
        event.respond("Logged in users:" + StringUtils.join(ForumManager.forumAccounts.keySet(), ", "));
    }

}
