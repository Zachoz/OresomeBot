package com.zachoz.OresomeBot.oresomecraft.forums;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zachoz.OresomeBot.OresomeBot;
import com.zachoz.OresomeBot.oresomecraft.forums.alerts.Alert;
import com.zachoz.OresomeBot.oresomecraft.forums.alerts.AlertType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class ForumAccount implements Serializable {

    public String email, password, authHash, associatedNick;
    int forumId = -1;
    transient boolean authenticated = false;
    transient ArrayList<Alert> alerts;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public ForumAccount(String email, String password, String associatedNick) {
        this.email = email;
        this.password = password;
        this.associatedNick = associatedNick;
    }

    public synchronized String getEmail() {
        return this.email;
    }

    public synchronized String getPassword() {
        return this.password;
    }

    public synchronized boolean isAuthenticated() {
        return this.authenticated;
    }

    public synchronized String getAssociatedNick() {
        return this.associatedNick;
    }

    public synchronized int getForumId() {
        return this.forumId;
    }

    public synchronized int fetchForumId() throws Exception {
        if (this.forumId != -1) return this.forumId; // Forum ID already cached

        try {
            String link = "api.php?action=getUser&hash=" + this.email + ":" + this.authHash;

            URL url = new URL("http://web.oresomecraft.com/forums/" + link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // Open URL connection
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) sb.append(inputLine);

            String response = sb.toString();

            return Integer.parseInt(new JsonParser().parse(response).getAsJsonObject().get("user_id").toString().replaceAll("\"", ""));
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public synchronized boolean authenticate() {
        if (!email.matches(EMAIL_PATTERN)) {
            OresomeBot.getBot().sendIRC().message(associatedNick, "Please enter a valid email address!");
            return false; // Could be an injection attempt
        }

        if (password.contains("&")) {
            OresomeBot.getBot().sendIRC().message(associatedNick, "Please do not use passwords with the '&' symbol in" +
                    " them, we're forced to block them due to potential injection attacks!");
            return false;
        }

        try {
            String link = "api.php?action=authenticate&username=" + this.email + "&password=" + this.password;

            URL url = new URL("http://web.oresomecraft.com/forums/" + link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // Open URL connection
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) sb.append(inputLine);

            String response = sb.toString();

            if (response.contains("{\"error\":5,\"message\":\"Authentication error: \\\"Invalid username or password!\\\"\"}")) {
                return false; // Incorrect login credentials
            }

            this.authHash = new JsonParser().parse(response).getAsJsonObject().get("hash").toString().replaceAll("\"", "");
            this.authenticated = true;

            this.forumId = fetchForumId();

            return true; // Authentication successful!

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public synchronized ArrayList<Alert> queryAlerts() {
        ArrayList<Alert> localAlerts = new ArrayList<Alert>();
        try {
            String link = "api.php?action=getAlerts&hash=" + this.email + ":" + this.authHash + "&type=fetchPopupItems";

            URL url = new URL("http://web.oresomecraft.com/forums/" + link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // Open URL connection
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) sb.append(inputLine);

            String response = sb.toString();

            JsonObject object = new JsonParser().parse(response).getAsJsonObject();

            if (!object.get("alerts").isJsonArray()) { // If it's a json array it means they have no alerts!
                for (Map.Entry<String, JsonElement> entry : object.getAsJsonObject("alerts").entrySet()) {
                    JsonElement element = entry.getValue();

                    // Determine the alert type
                    AlertType type = AlertType.fromString(element.getAsJsonObject().get("action").toString().replaceAll("\"", ""));

                    if (element.getAsJsonObject().get("content_type").toString().replaceAll("\"", "").equals("profile_post") && type == AlertType.LIKE)
                        type = AlertType.PROFILE_LIKE;
                    else if (element.getAsJsonObject().get("content_type").toString().replaceAll("\"", "").equals("profile_post"))
                        type = AlertType.PROFILE_POST;

                    // Get required details
                    Integer postId = AlertType.threadBased(type) ? Integer.parseInt(element.getAsJsonObject().
                            getAsJsonObject("content").get("post_id").toString().replaceAll("\"", "")) : null;
                    Integer threadId = AlertType.threadBased(type) ? Integer.parseInt(element.getAsJsonObject().
                            getAsJsonObject("content").get("thread_id").toString().replaceAll("\"", "")) : null;
                    String date = element.getAsJsonObject().get("event_date").toString().replaceAll("\"", "");
                    String title = AlertType.threadBased(type) ? element.getAsJsonObject().getAsJsonObject("content").get("title").toString().replaceAll("\"", "") : null;
                    String alertCauser = element.getAsJsonObject().getAsJsonObject("user").get("username").toString().replaceAll("\"", "");

                    // Some special fixes afterwards
                    if (type == AlertType.PROFILE_POST) postId = Integer.parseInt(element.getAsJsonObject().
                            getAsJsonObject("content").get("profile_post_id").toString().replaceAll("\"", ""));

                    localAlerts.add(new Alert(this, type, postId, threadId, date, title, alertCauser));
                }
            }

            return localAlerts;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public synchronized ArrayList<Alert> getAlerts() {
        return this.alerts;
    }

    public synchronized void setAlerts(ArrayList<Alert> alerts) {
        this.alerts = alerts;
    }

}