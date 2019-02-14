package me.xinnir.nscp.commands;

import me.xinnir.nscp.NSCP;
import me.xinnir.nscp.wrappers.User;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdPardon implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!sender.hasPermission("filter.stats")) {
            NSCP.getUtil().sendMessage(sender, "&cYou don't have permission to perform this command!");
            return false;
        }
        if (args.length < 1) {
            NSCP.getUtil().sendMessage(sender, "You must supply a user.");
        }

        User user = new User(Bukkit.getPlayer(args[0]));
        user.pardon();
        NSCP.getUtil().sendMessage(sender, "You have pardoned " + user.getName());

        return false;
    }
}
