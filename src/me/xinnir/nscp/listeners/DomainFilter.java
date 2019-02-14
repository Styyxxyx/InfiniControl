package me.xinnir.nscp.listeners;

import me.xinnir.nscp.NSCP;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DomainFilter implements Listener {

    private Plugin plugin;

    public DomainFilter(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void NoIP(final AsyncPlayerChatEvent event) {

        if (!NSCP.getOptions().filterDomains() || event.getPlayer().hasPermission("filter.bypass.domains"))
            return;
        String messageToSendToAdmins = event.getMessage();
        String message = event.getMessage();

        List<String> exceptions = NSCP.getOptions().getURLExceptions();

        boolean found = false;

        Pattern p = Pattern.compile("\\b\\d{1,3}+\\p{P}*\\d{1,3}+\\p{P}*\\d{1,3}+\\p{P}*\\d{1,3}+\\b|((?:[\\w]+\\.)+)([a-zA-Z]{2,4})");
        Matcher m = p.matcher(message);
        mainLoop:
        while (m.find()) {
            for (String s : exceptions) {
                if (s.contains(m.group()))
                    continue mainLoop;
            }
            found = true;
            message = message.replace(m.group(), NSCP.getOptions()
                    .getReplacementForAds());
            for (Player p1 : Bukkit.getServer().getOnlinePlayers()) {
                if (p1.hasPermission("ic.admin") || p1.isOp())
                    NSCP.getUtil().sendMessage(p1, "Player: &6" + event.getPlayer().getName()
                            + "&7 AKA:&6 " + event.getPlayer().getDisplayName()
                            + " &7tried to advertise. Message: &6"
                            + messageToSendToAdmins + "&7.");
                plugin.getLogger().log(Level.SEVERE, NSCP.getUtil().colorString("Player: &6"
                        + event.getPlayer().getName() + "&7 AKA:&6 "
                        + event.getPlayer().getDisplayName()
                        + " &7tried to advertise. Message: &6"
                        + messageToSendToAdmins + "&7.", '&'));
            }
        }
        if (NSCP.getOptions().kickOnAdvertise() && found) {
            NSCP.getUtil().asyncKick(event.getPlayer(), NSCP.getOptions().getKickMessage(), plugin);
            event.setCancelled(true);
        } else {
            event.setMessage(message);
        }

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void NoIPMessage(final PlayerCommandPreprocessEvent event) {

        if (!NSCP.getOptions().enableProfanityFilter() || event.getPlayer().hasPermission("filter.bypass.domains"))
            return;
        String messageToSendToAdmins = event.getMessage();
        String message = event.getMessage();

        List<String> exceptions = NSCP.getOptions().getURLExceptions();

        boolean found = false;

        Pattern p = Pattern.compile("\\b\\d{1,3}+\\p{P}*\\d{1,3}+\\p{P}*\\d{1,3}+\\p{P}*\\d{1,3}+\\b|((?:[\\w]+\\.)+)([a-zA-Z]{2,4})");
        Matcher m = p.matcher(message);
        mainLoop:
        while (m.find()) {
            for (String s : exceptions) {
                if (s.contains(m.group()))
                    continue mainLoop;
            }
            found = true;
            message = message.replace(m.group(), NSCP.getOptions().getReplacementForAds());
            for (Player p1 : Bukkit.getServer().getOnlinePlayers()) {
                if (p1.hasPermission("ic.admin"))
                    NSCP.getUtil().sendMessage(p1, "Player: &6"
                            + event.getPlayer().getName() + "&7 AKA:&6 "
                            + event.getPlayer().getDisplayName()
                            + " &7tried to advertise. Message: &6"
                            + messageToSendToAdmins + "&7.");
            }
        }
        if (NSCP.getOptions().kickOnAdvertise() && found) {
            NSCP.getUtil().asyncKick(event.getPlayer(), NSCP.getOptions().getKickMessage(), plugin);
            event.setCancelled(true);
        } else {
            event.setMessage(message);
        }

    }
}
