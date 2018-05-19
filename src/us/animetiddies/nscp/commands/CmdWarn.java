package us.animetiddies.nscp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import us.animetiddies.nscp.NSCP;
import us.animetiddies.nscp.wrappers.User;

public class CmdWarn implements CommandExecutor {

    Plugin p;

    public CmdWarn(Plugin p) {
        this.p = p;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (sender.hasPermission("ic.warn")) {
            if (args.length < 1) {
                NSCP.getUtil().sendMessage(sender, "&cYou must supply a user to warn.");
                return false;
            }
            User user = new User(Bukkit.getPlayer(args[0]));
            if (args.length == 1) {
                user.addInfractions(1);
                NSCP.getUtil().sendMessage(sender, "Warned " + user.getName());
                user.sendMessage("You have violated a rule and have been warned! You currently have &c" + user.getInfractions() + " &7infractions!");

            } else {
                int increment = Integer.parseInt(args[1]);
                user.addInfractions(increment);
                NSCP.getUtil().sendMessage(sender, "Warned " + user.getName() + " with " + increment + " warnings.");
                NSCP.getUtil().sendMessage(sender, "They now have " + user.getInfractions() + " warnings.");
            }
        }
        return false;
    }
}
