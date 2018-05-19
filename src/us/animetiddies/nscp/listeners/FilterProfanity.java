package us.animetiddies.nscp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import us.animetiddies.nscp.NSCP;
import us.animetiddies.nscp.wrappers.User;

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
        String message = event.getMessage();
        String fixedMessage = "";

        URL api;
        try {
            //String[] wordsToParse = event.getMessage().split(" ");
            //String[] violations = new String[wordsToParse.length];

            //for (int i = 0; i < violations.length; i++) {
            api = new URL("http://www.wdylike.appspot.com/?q=" + message.replaceAll("\\s", "%20")
                    .replaceAll("_", "%20"));
            BufferedReader in = new BufferedReader(new InputStreamReader(api.openStream()));

            while ((response = in.readLine()) != null) {
                if (response.equalsIgnoreCase("true")) {
                    //violations[i] = wordsToParse[i];
                    event.setCancelled(true);
                    NSCP.getUtil().sendMessage(event.getPlayer(), "&cProfanity is not tolerated!");
                    new User(event.getPlayer()).addInfractions(1);
                    apiFind = true;
                    break;
                }
            }
            //}

//            for(String s : violations){
//                message = message.replaceAll(s, NSCP.getOptions().getReplacement());
//            }

            //event.setMessage(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!apiFind) {
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
