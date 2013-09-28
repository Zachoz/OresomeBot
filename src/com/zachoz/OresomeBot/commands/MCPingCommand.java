package com.zachoz.OresomeBot.commands;

import ch.jamiete.mcping.MinecraftPing;
import ch.jamiete.mcping.MinecraftPingReply;
import org.pircbotx.Colors;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class MCPingCommand extends ListenerAdapter {

    public void onMessage(MessageEvent event) {
        String[] args = event.getMessage().split(" ");
        if (event.getMessage().startsWith(".mcping ")) {
            if (args.length >= 2) {
                String address = event.getMessage().split(":")[0].split(" ")[1];
                String port = event.getMessage().split(":").length >= 2 ? event.getMessage().split(":")[1] : "25565";
                try {
                    MinecraftPingReply ping = new MinecraftPing().getPing(address, Integer.parseInt(port));

                    event.respond("MC Ping: (" + address + ":" + port + ")" + Colors.MAGENTA + " - " + Colors.NORMAL
                            + parseColours(ping.getMotd()) + Colors.MAGENTA + " - " + Colors.CYAN + ping.getVersion()
                            + Colors.MAGENTA + " - " + Colors.GREEN + ping.getOnlinePlayers() + "/" + ping.getMaxPlayers());
                } catch (Exception ex) {
                    event.respond(Colors.RED + "Unable to ping server!");
                }
            }
        }
    }

    public String parseColours(String motd) {
        return motd.replaceAll("§4", "\u000305").replaceAll("§c", Colors.RED + "").replaceAll("§6", "\u000307")
                .replaceAll("§e", Colors.YELLOW + "").replaceAll("§2", Colors.DARK_GREEN + "").replaceAll("§a", Colors.GREEN + "")
                .replaceAll("§b", Colors.CYAN + "").replaceAll("§3", Colors.TEAL + "").replaceAll("§1", Colors.DARK_BLUE + "")
                .replaceAll("§9", Colors.BLUE + "").replaceAll("§d", Colors.MAGENTA + "").replaceAll("§5", Colors.PURPLE + "")
                .replaceAll("§f", Colors.WHITE + "").replaceAll("§7", Colors.LIGHT_GRAY + "").replaceAll("§8", Colors.DARK_GRAY + "")
                .replaceAll("§0", Colors.BLACK + "");
    }

}