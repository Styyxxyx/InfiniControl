package com.maxisociety.nscp.listeners;

import com.maxisociety.nscp.util.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

/**
 * Created by maverick on 12/24/13.
 */
public class onLogin implements Listener {

    Plugin plugin;

    public onLogin(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void playerLoginEvent(PlayerJoinEvent event) {
        Util.createFile(event.getPlayer().getName(), plugin);
    }
}
