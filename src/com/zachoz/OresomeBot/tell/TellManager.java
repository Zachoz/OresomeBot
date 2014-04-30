package com.zachoz.OresomeBot.tell;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TellManager extends ListenerAdapter {

    private static HashMap<String, TellMessage[]> messages = new HashMap<String, TellMessage[]>();

    public static void addTellMessage(TellMessage message) {
        if (!messages.containsKey(message.getRecipient())) { // Has no current pending messages
            messages.put(message.getRecipient().toLowerCase(), new TellMessage[]{message});
        } else { // Has pending messages
            ArrayList<TellMessage> newMessages = new ArrayList<TellMessage>(Arrays.asList(messages.get(message.getRecipient().toLowerCase())));
            newMessages.add(message);
            TellMessage[] messagez = Arrays.copyOf(newMessages.toArray(), newMessages.toArray().length, TellMessage[].class);
            messages.put(message.getRecipient().toLowerCase(), messagez);
        }

        serialise(); // Save messages to disk
    }

    public void onMessage(MessageEvent event) {
        if (messages.containsKey(event.getUser().getNick().toLowerCase())) {
            for (TellMessage message : messages.get(event.getUser().getNick().toLowerCase())) {
                if (message.getType() == MessageType.CHANNEL) {
                    event.respond("Message from " + message.getSender() + ": " + message.getMessage());
                } else if (message.getType() == MessageType.PRIVATE) {
                    event.getBot().sendIRC().message(event.getUser().getNick(),
                            "Message from " + message.getSender() + ": " + message.getMessage());
                }
            }
            messages.remove(event.getUser().getNick().toLowerCase());

            serialise(); // Save messages to disk
        }
    }

    public void onJoin(JoinEvent event) {
        if (messages.containsKey(event.getUser().getNick())) {
            event.respond("Some messages are waiting for you! Speak to see them!");
        }
    }

    private static void serialise() {
        try {
            File file = new File("messages.ser");
            if (file.exists()) file.delete(); // Delete old copy;

            // Write file
            FileOutputStream fileOut = new FileOutputStream("messages.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(messages);
            out.close();
            fileOut.close();
            System.out.printf("Serialised messages!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadMessagesFromDisk() {
        try {
            FileInputStream fileStream = new FileInputStream("messages.ser");
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);
            messages = (HashMap) objectStream.readObject();
            fileStream.close();
            objectStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Failed to load serialised messages from disk!");
        }
    }

}
