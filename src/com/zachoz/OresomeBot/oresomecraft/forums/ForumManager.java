package com.zachoz.OresomeBot.oresomecraft.forums;

import com.zachoz.OresomeBot.Config;
import com.zachoz.OresomeBot.OresomeBot;
import com.zachoz.OresomeBot.oresomecraft.forums.alerts.NewAlertCheckTask;
import com.zachoz.OresomeBot.oresomecraft.forums.threads.ForumThread;
import com.zachoz.OresomeBot.oresomecraft.forums.threads.NewThreadCheckerTask;
import com.zachoz.OresomeBot.oresomecraft.forums.threads.NodeView;

import java.io.*;
import java.util.HashMap;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;

public class ForumManager {

    public static NodeView news, generalChat, banAppeals, userReports;
    public static ConcurrentHashMap<String, ForumAccount> forumAccounts = new ConcurrentHashMap<String, ForumAccount>();

    public static final int NEWS_NODE_ID = 11;
    public static final int GENERALCHAT_NODE_ID = 2;
    public static final int BANAPPEALS_NODE_ID = 8;
    public static final int USERREPORTS_NODE_ID = 47;

    public static final String ORESOMECRAFT_FORUMS_URL = "https://oresomecraft.com/forums/";

    static Timer threadCheckTimer = new Timer(), alertCheckTimer = new Timer(), authCheckTimer = new Timer();

    public static void setOrRefreshNodeViews() throws Exception {
        news = new NodeView(NEWS_NODE_ID).build();
        generalChat = new NodeView(GENERALCHAT_NODE_ID).build();
        banAppeals = new NodeView(BANAPPEALS_NODE_ID).build();
        userReports = new NodeView(USERREPORTS_NODE_ID).build();
    }

    public static ForumThread getLatestThread(NodeView view) {
        return view.getThreads().get(0);
    }

    public static String newThread(NodeView oldView, NodeView newView) {
        if (getLatestThread(oldView).equals(getLatestThread(newView))) {
            return ""; // No new thread
        } else { // There's a new thread!
            ForumThread thread = newView.getThreads().get(0);

            return "A new forum thread has been posted by " + thread.getAuthor() + "! " +
                    thread.getTitle() + " - " + thread.getLink();
        }
    }

    public static void checkNewThreads() throws Exception {
        if (news == null) setOrRefreshNodeViews(); // Forum views haven't been set yet

        NodeView newsLocal = new NodeView(NEWS_NODE_ID).build();
        NodeView generalChatLocal = new NodeView(GENERALCHAT_NODE_ID).build();
        NodeView banAppealsLocal = new NodeView(BANAPPEALS_NODE_ID).build();
        NodeView userReportsLocal = new NodeView(USERREPORTS_NODE_ID).build();

        if (!newThread(news, newsLocal).equals("")) // News forum
            OresomeBot.getBot().sendIRC().message(Config.primarychannel, newThread(news, newsLocal));

        if (!newThread(generalChat, generalChatLocal).equals("")) // General chat forum
            OresomeBot.getBot().sendIRC().message(Config.primarychannel, newThread(generalChat, generalChatLocal));

        if (!newThread(banAppeals, banAppealsLocal).equals("")) // Ban appeals forum
            OresomeBot.getBot().sendIRC().message("#oresomecraft-admin", newThread(banAppeals, banAppealsLocal));

        if (!newThread(userReports, userReportsLocal).equals("")) // User reports forum
            OresomeBot.getBot().sendIRC().message("#oresomecraft-admin", newThread(userReports, userReportsLocal));

        setOrRefreshNodeViews(); // Update NodeViews to latest
    }

    public static void initiate() {
        loadSerialisedForumAccounts();

        threadCheckTimer.scheduleAtFixedRate(new NewThreadCheckerTask(), 1 * 60 * 1000, 1 * 60 * 1000);
        authCheckTimer.scheduleAtFixedRate(new ForumAccountCheck(), 1 * 30 * 1000, 1 * 60 * 1000);
        alertCheckTimer.scheduleAtFixedRate(new NewAlertCheckTask(), 1 * 30 * 1000, 1 * 60 * 1000);
        System.out.println("Successfully initiated forum checking tasks!");
    }

    public static ForumAccount accountAlreadyExists(ForumAccount account) {
        for (ForumAccount acc : forumAccounts.values()) {
            if (acc.getForumId() == account.getForumId()) return acc;
        }
        return null;
    }

    public static void serialiseForumAccounts() {
        try {
            File file = new File("forum_accounts.ser");
            if (file.exists()) file.delete(); // Delete old copy;

            // Write file
            FileOutputStream fileOut = new FileOutputStream("forum_accounts.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(forumAccounts);
            out.close();
            fileOut.close();
            System.out.println("Serialised forum accounts!!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadSerialisedForumAccounts() {
        try {
            FileInputStream fileStream = new FileInputStream("forum_accounts.ser");
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);
            forumAccounts = (ConcurrentHashMap) objectStream.readObject();
            fileStream.close();
            objectStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Failed to load serialised forum accounts from disk!");
        }
    }

}
