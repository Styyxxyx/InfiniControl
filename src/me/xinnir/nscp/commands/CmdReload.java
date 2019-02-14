package me.xinnir.nscp.commands;

import me.xinnir.nscp.NSCP;
import me.xinnir.nscp.tasks.AutoAnnounce;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.logging.Level;

public class CmdReload implements CommandExecutor {

    private Plugin plugin;

    public CmdReload(Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command,
                             String alias, String[] args) {

        if (!sender.hasPermission("filter.reload")) {
            NSCP.getUtil().sendMessage(sender, "&cYou don't have permission to perform this command!");
            return false;
        }
        if (!(sender instanceof Player)) {
            plugin.reloadConfig();
            plugin.getLogger().log(Level.INFO, "Configuration successfully reloaded.");
            Bukkit.getScheduler().cancelTasks(plugin);
            Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,
                    new AutoAnnounce(), 0, NSCP.getOptions().getDelay() * 20);
        } else {
            plugin.reloadConfig();
            NSCP.getUtil().sendMessage(sender, "Configuration successfully reloaded.");

            Bukkit.getScheduler().cancelTasks(plugin);
            Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,
                    new AutoAnnounce(), 0, NSCP.getOptions().getDelay() * 20);
        }

        return false;
    }

}
