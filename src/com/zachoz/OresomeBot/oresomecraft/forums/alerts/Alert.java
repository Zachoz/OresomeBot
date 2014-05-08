package com.zachoz.OresomeBot.oresomecraft.forums.alerts;

import com.zachoz.OresomeBot.oresomecraft.forums.ForumAccount;
import com.zachoz.OresomeBot.oresomecraft.forums.ForumManager;

public class Alert {

    private ForumAccount account;
    private AlertType type;
    private Integer postId, threadId;
    private String date, title, alertCauser;

    public Alert(ForumAccount account, AlertType type, Integer postId, Integer threadId, String date, String title, String alertCauser) {
        this.account = account;
        this.type = type;
        this.postId = postId;
        this.threadId = threadId;
        this.date = date;
        this.title = title;
        this.alertCauser = alertCauser;
    }

    public synchronized ForumAccount getAccount() {
        return this.account;
    }

    public synchronized AlertType getType() {
        return this.type;
    }

    public synchronized Integer getPostId() {
        return this.postId;
    }

    public synchronized Integer getThreadId() {
        return this.threadId;
    }

    public synchronized String getDate() {
        return this.date;
    }

    public synchronized String getTitle() {
        return this.title;
    }

    public synchronized String getAlertCauser() {
        return this.alertCauser;
    }

    public synchronized String getUrl() {
        if (AlertType.threadBased(this.type)) {
            return ForumManager.ORESOMECRAFT_FORUMS_URL + "threads/." + getThreadId() + "/#post-" + getPostId();
        } else if (this.type == AlertType.PROFILE_POST || this.type == AlertType.PROFILE_LIKE) {
            return ForumManager.ORESOMECRAFT_FORUMS_URL + "members/." + account.getForumId() + "/#profile-post-" + getPostId();
        } else {
            return "";
        }
    }

    public synchronized String getIRCMessage() {
        if (this.type == AlertType.POST)
            return "[Forums] " + alertCauser + " replied to thread: " + title + "! " + getUrl();
        if (this.type == AlertType.LIKE)
            return "[Forums] " + alertCauser + " liked your post in thread: " + title + "! " + getUrl();
        if (this.type == AlertType.QUOTE)
            return "[Forums] " + alertCauser + " quoted your post in thread: " + title + "! " + getUrl();
        if (this.type == AlertType.TAG)
            return "[Forums] " + alertCauser + " tagged you in a post in thread: " + title + "! " + getUrl();
        if (this.type == AlertType.FOLLOWING) return "[Forums] " + alertCauser + " is now following you!";
        if (this.type == AlertType.PROFILE_POST)
            return "[Forums] " + alertCauser + " wrote a message on your profile! " + getUrl();
        if (this.type == AlertType.PROFILE_LIKE)
            return "[Forums] " + alertCauser + " liked a post on your profile! " + getUrl();
        if (this.type == AlertType.TROPHY) return "[Forums] you have earned a new trophy!";
        return ""; // Impossible unless 'type' is equal to null
    }

    @Override
    public boolean equals(Object o) { // Doesn't cover everything but is accurate enough
        if (!(o instanceof Alert)) return false;
        Alert alert = (Alert) o;
        return getType() == alert.getType() && getDate().equals(alert.getDate()) &&
                getAlertCauser().equals(alert.getAlertCauser()) && getUrl().equals(alert.getUrl());
    }

}
