package com.zachoz.OresomeBot.commands;

import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.*;
import com.zachoz.OresomeBot.OresomeBot;

@SuppressWarnings("rawtypes")
public class BanCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) throws Exception {
        if (event.getMessage().split(" ").length > 1) {
            Channel currentchannel = event.getChannel();

            String userarg = event.getMessage().split(" ")[1];
            User user = OresomeBot.bot.getUser(userarg);
            String hostmask = user.getHostmask();

            if (event.getMessage().startsWith(".ban ") && event.getMessage().contains(userarg)) {

                if (currentchannel.hasVoice(event.getUser()) || currentchannel.isOp(event.getUser())) {

                    //OresomeBot.bot.ban(currentchannel, hostmask);
                    OresomeBot.bot.sendRawLineNow("mode" + " " + event.getChannel().getName() + " +b " + hostmask);

                }

            }

        }

    }
}
