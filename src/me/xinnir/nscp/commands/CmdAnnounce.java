package me.xinnir.nscp.commands;

import me.xinnir.nscp.NSCP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdAnnounce implements CommandExecutor {

    public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
                             String[] arg3) {

        if (arg0.hasPermission("ic.announcenow"))
            Bukkit.getServer().broadcastMessage(
                    ChatColor.translateAlternateColorCodes('&', NSCP
                            .getOptions().getPrefix()
                            + NSCP.getOptions().getRandEntry()));

        return true;
    }
}
