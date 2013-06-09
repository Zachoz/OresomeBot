package com.zachoz.OresomeBot.commands;

import java.io.FileNotFoundException;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.*;

import com.zachoz.OresomeBot.Config;

@SuppressWarnings("rawtypes")
public class ReloadCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) throws Exception {

        if (event.getMessage().equals(".reload ")) {

            String admin = "";
            for (int i = 0; i < Config.admins.length; i++) {
                admin += Config.admins[i];
            }

            if (admin.contains(event.getUser().getNick())) {
                try {

                    Config.loadConfiguration();

                } catch (FileNotFoundException ex) {
                    // This needs to generate the file if it doesn't exist.
                }

                event.respond("Reloading configuration.");

            }

        }

    }
}
