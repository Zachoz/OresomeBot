package com.zachoz.OresomeBot.oresomecraft.forums;

import com.zachoz.OresomeBot.Config;
import com.zachoz.OresomeBot.OresomeBot;

import java.util.Timer;

public class ForumManager {

    public static NodeView news, generalChat, banAppeals, userReports;

    public static final int NEWS_NODE_ID = 11;
    public static final int GENERALCHAT_NODE_ID = 2;
    public static final int BANAPPEALS_NODE_ID = 8;
    public static final int USERREPORTS_NODE_ID = 47;

    static Timer threadCheckTimer = new Timer();

    public static void setOrRefreshNodeViews() {
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

    public static void checkNewThreads() {
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
        threadCheckTimer.scheduleAtFixedRate(new NewThreadCheckerTask(), 1 * 60 * 1000, 1 * 60 * 1000);
        System.out.println("Successfully initiated forum checking task!");
    }

}
