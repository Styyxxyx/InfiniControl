package me.xinnir.nscp.listeners;

import me.xinnir.nscp.NSCP;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by maverick on 12/24/13.
 */
public class onLogin implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void playerLoginEvent(PlayerJoinEvent event) {
        NSCP.getNetwork().createUserData(event.getPlayer().getName(), event.getPlayer().getAddress().toString());
        NSCP.getNetwork().initializeChatData(event.getPlayer().getName());
    }
}
