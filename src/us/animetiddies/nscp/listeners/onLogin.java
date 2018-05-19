package us.animetiddies.nscp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import us.animetiddies.nscp.NSCP;

/**
 * Created by maverick on 12/24/13.
 */
public class onLogin implements Listener {

    private Plugin plugin;

    public onLogin(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void playerLoginEvent(PlayerJoinEvent event) {
        NSCP.getNetwork().createUserData(event.getPlayer().getName());
    }
}
