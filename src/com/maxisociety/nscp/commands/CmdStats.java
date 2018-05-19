package com.maxisociety.nscp.commands;

import com.maxisociety.nscp.NSCP;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

/**
 * Created by maverick on 12/24/13.
 */
public class CmdStats implements CommandExecutor {

    private Plugin plugin;

    public CmdStats(Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (!sender.hasPermission("filter.stats")) {
            NSCP.getUtil().sendMessage(sender, "&cYou don't have permission to perform this command!");
            return false;
        }
        if (args.length < 1) {
            NSCP.getUtil().sendMessage(sender, "You must supply a user.");
        }
        ArrayList<String> stats;

        Player player = Bukkit.getPlayer(args[0]);
        if (player != null) {
            stats = NSCP.getUtil().getLinesFromFile(args[0], plugin);
            if (stats.isEmpty()) {
                NSCP.getUtil().sendMessage(sender, "&CNo stats for the online Player " + args[0] + " were found.(That's not right..)");
                NSCP.getUtil().sendMessage(sender, "&aI've generated a new player file for " + args[0] + "!");
                NSCP.getUtil().createFile(args[0], plugin);
                return false;
            }
            for (String s : stats) {
                NSCP.getUtil().sendMessage(sender, s);
            }
        } else {
            if (Bukkit.getServer().getOfflinePlayer(args[0]) != null) {
                stats = NSCP.getUtil().getLinesFromFile(args[0], plugin);
                if (stats.isEmpty()) {
                    NSCP.getUtil().sendMessage(sender, "&cThat player hasn't played before!");
                    return false;
                }
                for (String s : stats) {
                    NSCP.getUtil().sendMessage(sender, s);
                }
            }
        }
        return false;
    }
}
