package com.zachoz.OresomeBot.oresomecraft.forums;

import java.util.TimerTask;

public class NewThreadCheckerTask extends TimerTask {

    public void run() {
        ForumManager.checkNewThreads();
        System.out.println("Completed forums thread check!");
    }

}
