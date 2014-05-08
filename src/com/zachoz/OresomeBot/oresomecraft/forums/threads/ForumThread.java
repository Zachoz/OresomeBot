package com.zachoz.OresomeBot.oresomecraft.forums.threads;

public class ForumThread {

    private String title, link, date, author;
    private int node;

    public ForumThread(int node, String title, String link, String date, String author) {
        this.node = node;
        this.title = title;
        this.link = link;
        this.date = date;
        this.author = author;
    }

    public int getNode() {
        return this.node;
    }

    public String getTitle() {
        return this.title;
    }

    public String getLink() {
        return this.link;
    }

    public String getDate() {
        return this.date;
    }

    public String getAuthor() {
        return this.author;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ForumThread)) return false;
        ForumThread thread = (ForumThread) o;

        return thread.getNode() == this.getNode() &&
                thread.getTitle().equals(this.getTitle()) &&
                thread.getLink().equals(this.getLink()) &&
                thread.getAuthor().equals(this.getAuthor());
    }

}
