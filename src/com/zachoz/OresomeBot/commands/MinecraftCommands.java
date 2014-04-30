package com.zachoz.OresomeBot.commands;

import ch.jamiete.mcping.MinecraftPing;
import ch.jamiete.mcping.MinecraftPingOptions;
import ch.jamiete.mcping.MinecraftPingReply;
import com.zachoz.OresomeBot.core.ChannelCommand;
import com.zachoz.OresomeBot.core.PermissionLevel;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

public class MinecraftCommands {

    @ChannelCommand(name = "mcping",
            description = "Queires a Minecraft server",
            usage = ".mcping <server>",
            permissionLevel = PermissionLevel.REGULAR,
            minArgs = 1)
    public static void mcping(MessageEvent event, String[] args) {
        String address = args[0].split(":")[0].split(" ")[0];
        String port = args[0].split(":").length >= 2 ? args[0].split(":")[1] : "25565";
        try {
            MinecraftPingOptions options = new MinecraftPingOptions();
            options.setHostname(address);
            options.setPort(Integer.parseInt(port));
            MinecraftPingReply ping = new MinecraftPing().getPing(options);

            event.respond("MC Ping: (" + address + ":" + port + ")" + Colors.MAGENTA + " - " + Colors.NORMAL
                    + parseColours(ping.getDescription()) + Colors.MAGENTA + " - " + Colors.CYAN + ping.getVersion().getName()
                    + Colors.MAGENTA + " - " + Colors.GREEN + ping.getPlayers().getOnline() + "/" + ping.getPlayers().getMax());
        } catch (Exception ex) {
            event.respond(Colors.RED + "Unable to ping server!");
        }
    }

    private static String parseColours(String motd) {
        return motd.replaceAll("§4", "\u000305").replaceAll("§c", Colors.RED + "").replaceAll("§6", "\u000307")
                .replaceAll("§e", Colors.YELLOW + "").replaceAll("§2", Colors.DARK_GREEN + "").replaceAll("§a", Colors.GREEN + "")
                .replaceAll("§b", Colors.CYAN + "").replaceAll("§3", Colors.TEAL + "").replaceAll("§1", Colors.DARK_BLUE + "")
                .replaceAll("§9", Colors.BLUE + "").replaceAll("§d", Colors.MAGENTA + "").replaceAll("§5", Colors.PURPLE + "")
                .replaceAll("§f", Colors.WHITE + "").replaceAll("§7", Colors.LIGHT_GRAY + "").replaceAll("§8", Colors.DARK_GRAY + "")
                .replaceAll("§0", Colors.BLACK + "");
    }
}
