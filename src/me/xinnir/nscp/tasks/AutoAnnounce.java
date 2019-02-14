package me.xinnir.nscp.tasks;

import org.bukkit.Bukkit;
import me.xinnir.nscp.NSCP;

public class AutoAnnounce implements Runnable {
    public void run() {
        Bukkit.getServer().broadcastMessage(NSCP.getUtil().colorString(NSCP.getOptions().getPrefix() + NSCP.getOptions().getRandEntry(), '&'));
    }
}
