package com.maxisociety.nscp.tasks;

import com.maxisociety.nscp.NSCP;
import org.bukkit.Bukkit;

public class AutoAnnounce implements Runnable {
    public void run() {
        Bukkit.getServer().broadcastMessage(
                NSCP.getUtil().colorString(NSCP.getOptions().getPrefix() + NSCP.getOptions()
                        .getRandEntry(), '&'));
    }
}
