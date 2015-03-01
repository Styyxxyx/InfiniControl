package com.maxisociety.nscp.listeners;

import com.maxisociety.nscp.NSCP;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class CapsFilter implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void antiCaps(AsyncPlayerChatEvent event) {
        if (!NSCP.getOptions().FILTER_CAPSLOCK
                || event.getPlayer().hasPermission("filter.bypass.caps"))
            return;
        String message = event.getMessage();
        int realMessageLength = message.replaceAll("[^0-9a-zA-Z]+", "")
                .length();

        if (realMessageLength >= NSCP.getOptions().getCapstrigger()) {
            char[] messageChars = event.getMessage().toCharArray();

            int upperCaseCount = 0;

            for (char c : messageChars)
                if (Character.isUpperCase(c))
                    upperCaseCount++;
            if (upperCaseCount > 0) {

                double capsAmt = Math
                        .floor(((double) upperCaseCount / (double) realMessageLength) * 100.0);

                if (capsAmt >= NSCP.getOptions().getCapsThreshold()) {
                    event.setMessage(message.toLowerCase());
                }
            }
        }
    }
}
