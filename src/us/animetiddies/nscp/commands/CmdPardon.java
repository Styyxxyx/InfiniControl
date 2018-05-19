package us.animetiddies.nscp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import us.animetiddies.nscp.NSCP;
import us.animetiddies.nscp.wrappers.User;

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
