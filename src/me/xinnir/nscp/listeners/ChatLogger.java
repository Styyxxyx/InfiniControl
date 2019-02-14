package me.xinnir.nscp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import me.xinnir.nscp.wrappers.User;

import java.time.LocalDateTime;

public class ChatLogger implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        User user = new User(event.getPlayer());
        LocalDateTime now = LocalDateTime.now();

        user.logMessage("[" + now + "]: " + message);
        Bukkit.broadcastMessage("[" + now + "]" + message + " yeet");

    }
}
