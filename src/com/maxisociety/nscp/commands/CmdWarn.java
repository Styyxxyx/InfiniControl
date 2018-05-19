package com.maxisociety.nscp.commands;

import com.maxisociety.nscp.NSCP;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

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
            int warnings = 0;
            for (String s : NSCP.getUtil().getLinesFromFile(p.getDataFolder() + args[0], p)) {
                warnings = Integer.parseInt(s.split(" ")[1]);
            }
            if (args.length == 1) {
                Player player = Bukkit.getPlayer(args[0]);
                NSCP.getUtil().sendMessage(sender, "Warned " + player.getName());
                NSCP.getUtil().incrementWarnings(player, 1);
            } else {
                Player player = Bukkit.getPlayer(args[0]);
                NSCP.getUtil().sendMessage(sender, "Warned " + player.getName() + " with " + args[1] + " warnings.");
                NSCP.getUtil().incrementWarnings(player, Integer.parseInt(args[1]));
                NSCP.getUtil().sendMessage(sender, "They now have " + warnings + " warnings.");
            }
        }
        return false;
    }
}
