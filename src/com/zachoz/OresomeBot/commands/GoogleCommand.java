package com.zachoz.OresomeBot.commands;

import com.google.gson.Gson;
import com.zachoz.OresomeBot.core.ChannelCommand;
import com.zachoz.OresomeBot.core.PermissionLevel;
import com.zachoz.OresomeBot.core.Utility;
import org.pircbotx.hooks.events.MessageEvent;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class GoogleCommand {

    @ChannelCommand(name = "google",
            description = "Google something",
            permissionLevel = PermissionLevel.REGULAR,
            minArgs = 1)
    public static void google(MessageEvent event, String[] args) {
        try {
            String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
            String charset = "UTF-8";

            URL url = new URL(google + URLEncoder.encode(Utility.getJoinedStrings(0, args), charset));
            Reader reader = new InputStreamReader(url.openStream(), charset);
            GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);

            String title = results.getResponseData().getResults().get(0).getTitle();
            String finaltitle = title.replace("<b>", "");

            event.respond(finaltitle.replace("</b>", ""));
            event.respond(results.getResponseData().getResults().get(0).getUrl());
        } catch (Exception ex) {
            event.respond("Something went wrong while trying to google that!");
        }
    }

    public class GoogleResults {

        private ResponseData responseData;

        public ResponseData getResponseData() {
            return responseData;
        }

        public void setResponseData(ResponseData responseData) {
            this.responseData = responseData;
        }

        public String toString() {
            return "ResponseData[" + responseData + "]";
        }

        class ResponseData {
            private List<Result> results;

            public List<Result> getResults() {
                return results;
            }

            public void setResults(List<Result> results) {
                this.results = results;
            }

            public String toString() {
                return "Results[" + results + "]";
            }
        }

        class Result {
            private String url;
            private String title;

            public String getUrl() {
                return url;
            }

            public String getTitle() {
                return title;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String toString() {
                return "Result[url:" + url + ",title:" + title + "]";
            }
        }
    }

}
