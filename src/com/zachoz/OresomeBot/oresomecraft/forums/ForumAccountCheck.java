package com.zachoz.OresomeBot.oresomecraft.forums;

import com.zachoz.OresomeBot.OresomeBot;

import java.util.Timer;
import java.util.TimerTask;

public class ForumAccountCheck extends TimerTask {

    /*
     * Checks all forum accounts to see if they're still authenticatable
     */

    public void run() {
        // Task timeout
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
                if (!account.authenticate()) {
                    ForumManager.forumAccounts.remove(user);
                    OresomeBot.getBot().sendIRC().message(user, "I couldn't authenticate your forum account!" +
                            " You're now logged out!");
                }
            }

            System.out.println("Completed forum authentication check!");

            ForumManager.serialiseForumAccounts();

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("An error occured while checking for account authentication!");
        }
    }

}
