package us.animetiddies.nscp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import us.animetiddies.nscp.NSCP;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnicodeFilter implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onChat(AsyncPlayerChatEvent event) {

        if (!NSCP.getOptions().filterUnicode()
                || event.getPlayer().hasPermission("filter.bypass.unicode"))
            return;
        Pattern regex = Pattern.compile(".*[^\\x20-\\x7F].*");

        Matcher regexMatcher = regex.matcher(event.getMessage());

        if (regexMatcher.find()) {
            NSCP.getUtil().sendMessage(event.getPlayer(),
                    "Only english and ASCII characters, please.");
            event.setCancelled(true);
        }
    }

}
