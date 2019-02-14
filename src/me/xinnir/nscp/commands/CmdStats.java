package me.xinnir.nscp.commands;

import me.xinnir.nscp.NSCP;
import me.xinnir.nscp.wrappers.User;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

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
        User user = new User(Bukkit.getPlayer(args[0]));
        NSCP.getUtil().sendMessage(sender, user.getName() + " has " + user.getInfractions() + " infractions.");
        return false;
    }
}
