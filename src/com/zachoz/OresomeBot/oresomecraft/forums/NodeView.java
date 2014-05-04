package com.zachoz.OresomeBot.oresomecraft.forums;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.Element;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class NodeView implements Cloneable {

    int nodeId;
    private String title, description, link;
    private ArrayList<ForumThread> threads = new ArrayList<ForumThread>();


    public NodeView(int nodeId) {
        this.nodeId = nodeId;
    }

    public NodeView build() {
        SAXBuilder builder = new SAXBuilder();
        Document document;

        try {
            // Get the forum views
            URL url = new URL("http://web.oresomecraft.com/forums/forums/." + this.nodeId + "/index.rss");
            URLConnection urlConnection = url.openConnection();
            urlConnection.setReadTimeout(5000); // 5 seconds
            urlConnection.setConnectTimeout(5000); // 5 seconds

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            final StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) stringBuilder.append(line);

            bufferedReader.close();

            if (!stringBuilder.toString().equals("")) {
                // Build the views
                document = builder.build(new StringReader(stringBuilder.toString()));

                // Set forum info
                Element nodeDetails = (Element) document.getRootElement().getChildren("channel").get(0);
                this.title = nodeDetails.getChildText("title");
                this.description = nodeDetails.getChildText("description");
                this.link = nodeDetails.getChildText("link");

                // Get threads
                for (Object item : nodeDetails.getChildren("item")) {
                    Element element = (Element) item;
                    String title = element.getChildText("title");
                    String date = element.getChildText("pubDate");
                    String link = element.getChildText("link");
                    String author = element.getChildText("author");

                    this.threads.add(new ForumThread(this.nodeId, title, link, date, author));
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return this;
    }

    public ArrayList<ForumThread> getThreads() {
        return this.threads;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getLink() {
        return this.link;
    }

}
