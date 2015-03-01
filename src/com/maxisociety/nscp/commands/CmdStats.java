package com.maxisociety.nscp.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.maxisociety.nscp.util.Util;

/**
 * Created by maverick on 12/24/13.
 */
public class CmdStats implements CommandExecutor {

    Plugin plugin;

    public CmdStats(Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (!sender.hasPermission("filter.stats")) {
            Util.sendMessage((Player) sender, "&cYou don't have permission to perform this command!");
            return false;
        }

        ArrayList<String> stats;

        Player player = Bukkit.getPlayer(args[0]);
        if (player != null) {
            stats = Util.getLinesFromFile(args[0], plugin);
            if (stats.isEmpty()) {
                Util.sendMessage((Player) sender, "&CNo stats for the online Player " + args[0] + " were found.(That's not right..)");
                Util.sendMessage((Player) sender, "&aI've generated a new player file for " + args[0] + "!");
                Util.createFile(args[0], plugin);
                return false;
            }
            for (String s : stats) {
                Util.sendMessage((Player) sender, s);
            }
        } else {
            if (Bukkit.getServer().getOfflinePlayer(args[0]) != null) {
                stats = Util.getLinesFromFile(args[0], plugin);
                if (stats.isEmpty()) {
                    Util.sendMessage((Player) sender, "&cThat player hasn't played before!");
                    return false;
                }
                for (String s : stats) {
                    Util.sendMessage((Player) sender, s);
                }
            }
        }
        return false;
    }
}
