package com.zachoz.OresomeBot.commands;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import com.zachoz.OresomeBot.OresomeBot;

@SuppressWarnings("rawtypes")
public class SendRawLineCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) {
        if (event.getMessage().split(" ").length > 1) {

            String message = event.getMessage();
            String line = event.getMessage().split(" ")[1];
            String[] ArrSay = message.split(" ");
            String outsay = "";

            if (event.getMessage().startsWith(".sendrawline ")
                    && event.getMessage().contains(line)) {

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
                OresomeBot.bot.sendRawLine(outsay);

            }
        }
    }
}
