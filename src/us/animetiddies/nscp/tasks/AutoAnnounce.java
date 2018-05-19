package us.animetiddies.nscp.tasks;

import org.bukkit.Bukkit;
import us.animetiddies.nscp.NSCP;

public class AutoAnnounce implements Runnable {
    public void run() {
        Bukkit.getServer().broadcastMessage(
                NSCP.getUtil().colorString(NSCP.getOptions().getPrefix() + NSCP.getOptions()
                        .getRandEntry(), '&'));
    }
}
