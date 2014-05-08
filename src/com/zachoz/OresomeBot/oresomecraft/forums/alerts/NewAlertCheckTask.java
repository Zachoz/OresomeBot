package com.zachoz.OresomeBot.oresomecraft.forums.alerts;

import com.zachoz.OresomeBot.oresomecraft.forums.ForumAccount;
import com.zachoz.OresomeBot.oresomecraft.forums.ForumManager;

import java.util.Timer;
import java.util.TimerTask;

public class NewAlertCheckTask extends TimerTask {

    public void run() {
        new Timer().schedule(new TimerTask() {
            public void run() {
                if (Thread.currentThread().isAlive()) {
                    Thread.currentThread().interrupt();
                }
            }
        }, 30 * 1000);

        try {
            for (String user : ForumManager.forumAccounts.keySet()) {
                ForumAccount account = ForumManager.forumAccounts.get(user);
                if (account.isAuthenticated())
                    AlertManager.checkAndSendNewAlerts(account, user);
            }
            System.out.println("Completed new alerts check!");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("An error occured while checking for new alerts!");
        }
    }

}
