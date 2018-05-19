package com.maxisociety.nscp.listeners;

import com.maxisociety.nscp.NSCP;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class FilterProfanity implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void noProfanity(AsyncPlayerChatEvent event) {

        if (event.isCancelled())
            return;

        if (!NSCP.getOptions().enableProfanityFilter()
                || event.getPlayer().hasPermission("filter.bypass.profanity"))
            return;

        String response;

        boolean apiFind = false;

        URL api;
        try {
            api = new URL("http://www.wdyl.com/profanity?q="
                    + event.getMessage().replaceAll("\\s", "%20")
                    .replaceAll("_", "%20"));
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    api.openStream()));

            while ((response = in.readLine()) != null) {
                response = response.replaceAll("\\{|\\}|response|\\:|\"|\\s",
                        "");
                if (response.equalsIgnoreCase("true")) {
                    event.setCancelled(true);
                    NSCP.getUtil().sendMessage(event.getPlayer(),
                            "&cProfanity is not tolerated!");
                    apiFind = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!apiFind) {
            String message = event.getMessage();

            String fixedMessage = "";

            List<String> cussWords = NSCP.getOptions().getCurseWords();

            for (String s : message.split(" ")) {
                if (cussWords.contains(s.toLowerCase())) {
                    fixedMessage += NSCP.getOptions().getReplacement();
                } else {
                    fixedMessage += s + " ";
                }
            }
            event.setMessage(fixedMessage);
        }

    }
}
