package com.zachoz.OresomeBot.commands;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.*;

import com.google.gson.Gson;

@SuppressWarnings("rawtypes")
public class GoogleCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) throws Exception {
	if (event.getMessage().split(" ").length > 1) {
	    String message = event.getMessage();

	    String[] ArrSay = message.split(" ");
	    String outsay = "";

	    for (int i = 1; i < ArrSay.length; i++) {
		if (ArrSay[i].contains("'")) {
		    String temp = "";
		    String temp2 = ArrSay[i];
		    for (int j = 0; j < temp2.length(); j++) {
			String temp3 = "" + temp2.charAt(j);
			if (!temp3.equals("'")) {
			    temp += temp3;
			}
		    }
		    outsay += temp;
		} else {
		    outsay += ArrSay[i];
		}
		if (i != ArrSay.length - 1) {
		    outsay += " ";
		}
	    }

	    if (event.getMessage().startsWith(".google ") && event.getMessage().contains(outsay)) {

		String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
		String charset = "UTF-8";

		URL url = new URL(google + URLEncoder.encode(outsay, charset));
		Reader reader = new InputStreamReader(url.openStream(), charset);
		GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);

		String title = results.getResponseData().getResults().get(0).getTitle();
		String finaltitle = title.replace("<b>", "");

		event.respond(finaltitle.replace("</b>", ""));
		event.respond(results.getResponseData().getResults().get(0).getUrl());
	    }

	}
    }


    public class GoogleResults {

	private ResponseData responseData;
	public ResponseData getResponseData() { return responseData; }
	public void setResponseData(ResponseData responseData) { this.responseData = responseData; }
	public String toString() { return "ResponseData[" + responseData + "]"; }

	class ResponseData {
	    private List<Result> results;
	    public List<Result> getResults() { return results; }
	    public void setResults(List<Result> results) { this.results = results; }
	    public String toString() { return "Results[" + results + "]"; }
	}

	class Result {
	    private String url;
	    private String title;
	    public String getUrl() { return url; }
	    public String getTitle() { return title; }
	    public void setUrl(String url) { this.url = url; }
	    public void setTitle(String title) { this.title = title; }
	    public String toString() { return "Result[url:" + url +",title:" + title + "]"; }
	}

    }
}
