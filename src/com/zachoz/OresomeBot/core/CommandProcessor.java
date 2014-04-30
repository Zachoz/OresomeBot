package com.zachoz.OresomeBot.core;

import com.zachoz.OresomeBot.Config;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CommandProcessor extends ListenerAdapter {

    private static final HashMap<String, Method> commandMap = new HashMap<String, Method>();
    private static final HashMap<String, Method> privateMessageCommandMap = new HashMap<String, Method>();
    public static final String COMMAND_PREFIX = ".";

    // Channel commands
    public void onMessage(MessageEvent event) throws Exception {
        String message = event.getMessage();
        String[] seperated;
        if (event.getMessage().contains(" ")) seperated = event.getMessage().split(" ");
        else seperated = new String[]{event.getMessage()};

        // If it's a command, let's execute it!
        if (commandMap.containsKey(seperated[0])) {
            String commandString = seperated[0];
            Method method = commandMap.get(commandString);
            ChannelCommand command = method.getAnnotation(ChannelCommand.class);

            // Build args
            String[] args = buildArgs(seperated);

            // Execute command. (null represents object to invoke on, since method is static no object)
            // Check permissions
            if (hasPermission(command.permissionLevel(), event.getUser(), event.getChannel())) {
                if (args.length < command.minArgs()) {
                    event.respond("Not enough arguements! Usage: " + command.usage());
                } else if (args.length > command.maxArgs()) {
                    event.respond("Too many arguements! Usage: " + command.usage());
                } else {
                    // Okay, everything's all good, lets run the command!
                    method.invoke(null, event, args);
                }
            } else {
                event.respond("You do not have permission to execute this command!");
            }
        }
    }

    // Private message commands
    public void onPrivateMessage(PrivateMessageEvent event) throws Exception {
        String message = event.getMessage();
        String[] seperated;
        if (event.getMessage().contains(" ")) seperated = event.getMessage().split(" ");
        else seperated = new String[]{event.getMessage()};

        // If it's a command, let's execute it!
        if (privateMessageCommandMap.containsKey(seperated[0])) {
            String commandString = seperated[0];
            Method method = privateMessageCommandMap.get(commandString);
            PrivateMessageCommand command = method.getAnnotation(PrivateMessageCommand.class);

            // Build args
            String[] args = buildArgs(seperated);

            // Execute command. (null represents object to invoke on, since method is static no object)
            // Check permissions
            if (hasPermission(command.permissionLevel(), event.getUser(), null)) {
                if (args.length < command.minArgs()) {
                    event.respond("Not enough arguements! Usage: " + command.usage());
                } else if (args.length > command.maxArgs()) {
                    event.respond("Too many arguements! Usage: " + command.usage());
                } else {
                    // Okay, everything's all good, lets run the command!
                    method.invoke(null, event, args);
                }
            } else {
                event.respond("You do not have permission to execute this command!");
            }
        }
    }

    public static void registerCommandClass(Class clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            // Channel commands
            if (method.isAnnotationPresent(ChannelCommand.class)) {
                Annotation annotation = method.getAnnotation(ChannelCommand.class);
                ChannelCommand command = (ChannelCommand) annotation;
                commandMap.put(COMMAND_PREFIX + command.name(), method);
            }

            // Private message
            if (method.isAnnotationPresent(PrivateMessageCommand.class)) {
                Annotation annotation = method.getAnnotation(PrivateMessageCommand.class);
                PrivateMessageCommand command = (PrivateMessageCommand) annotation;
                privateMessageCommandMap.put(COMMAND_PREFIX + command.name(), method);
            }
        }
    }

    private static boolean hasPermission(PermissionLevel level, User user, Channel channel) {
        if (level == PermissionLevel.REGULAR) return true;
        else if (level == PermissionLevel.VOICE && channel != null && (channel.hasVoice(user) || channel.isOp(user) || channel.isSuperOp(user)))
            return true;
        else if (level == PermissionLevel.OPERATOR && channel != null && (channel.isOp(user) || channel.isSuperOp(user))) return true;
        else if (level == PermissionLevel.SUPER_OPERATOR && channel != null && (channel.isSuperOp(user))) return true;
        else if (level == PermissionLevel.BOT_ADMIN) return Arrays.asList(Config.admins).contains(user.getNick());
        return false;
    }

    private static String[] buildArgs(String[] seperated) {
        ArrayList<String> argsBuilder = new ArrayList<String>(Arrays.asList(seperated));
        argsBuilder.remove(0); // remove the command string
        return Arrays.copyOf(argsBuilder.toArray(), argsBuilder.toArray().length, String[].class);
    }

}
