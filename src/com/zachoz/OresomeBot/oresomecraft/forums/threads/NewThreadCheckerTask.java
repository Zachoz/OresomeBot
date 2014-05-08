package com.zachoz.OresomeBot.oresomecraft.forums.threads;

import com.zachoz.OresomeBot.oresomecraft.forums.ForumManager;

import java.util.Timer;
import java.util.TimerTask;

public class NewThreadCheckerTask extends TimerTask {

    public void run() {
        new Timer().schedule(new TimerTask() {
            public void run() {
                if (Thread.currentThread().isAlive()) {
                    Thread.currentThread().interrupt();
                }
            }
        }, 30 * 1000);

        try {
            ForumManager.checkNewThreads();
            System.out.println("Completed forums thread check!");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("An error occured while checking for new threads!");
        }
    }

}
