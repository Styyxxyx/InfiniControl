package me.xinnir.nscp;

import me.xinnir.nscp.commands.*;
import me.xinnir.nscp.listeners.*;
import me.xinnir.nscp.network.Network;
import me.xinnir.nscp.tasks.AutoAnnounce;
import me.xinnir.nscp.util.Options;
import me.xinnir.nscp.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Arrays;
import java.util.HashMap;

public class NSCP extends JavaPlugin {

    private static Options options;
    private static Util util;
    private static Network network;


    public static HashMap<String, Material> bowGunBlock = new HashMap<>();
    public static HashMap<String, EntityType> bowGunEntity = new HashMap<>();

    public static Network getNetwork() {
        return network;
    }

    public void onEnable() {

        PluginManager pm = Bukkit.getServer().getPluginManager();

        loadConfig();
        pm.registerEvents(new UnicodeFilter(), this);
        pm.registerEvents(new DomainFilter(this), this);
        pm.registerEvents(new Antispam(), this);
        pm.registerEvents(new FilterProfanity(), this);
        pm.registerEvents(new CapsFilter(), this);
        pm.registerEvents(new onLogin(), this);
        pm.registerEvents(new ChatLogger(), this);

        options = new Options(this);
        util = new Util(this);
        options.init();
        network = new Network();
        network.connect();
        network.init();

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new AutoAnnounce(), 0, getOptions().getDelay() * 20);

        getCommand("ireload").setExecutor(new CmdReload(this));
        getCommand("stats").setExecutor(new CmdStats(this));
        getCommand("announce").setExecutor(new CmdAnnounce());
        getCommand("warn").setExecutor(new CmdWarn(this));
        getCommand("pardon").setExecutor(new CmdPardon());
    }

    public static Util getUtil() {
        return util;
    }

    public static Options getOptions() {
        return options;
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        {
            /*
              Setup options for filtering.
             */
            getConfig().addDefault("options.filter.unicode", true);
            getConfig().addDefault("options.filter.caps", true);
            getConfig().addDefault("options.filter.profanity", true);
            getConfig().addDefault("options.enable.spamprotection", true);
            getConfig().addDefault("options.filter.domains", true);

            getConfig().addDefault("options.domains.kickOnAdvertise", false);
            getConfig().addDefault("options.domains.advertisement.replaceWith", "ADVERTISEMENT");
            getConfig().addDefault("options.domains.kickMessage", "&cAdvertising is not tolerated!");

            /*
              Add Defaults.
             */
            String[] defaultFilters = {"ass", "fuck", "fvck", "bitch", "nigger", "nigga", "fag", "f@g", "motherfucker", "fucker"};
            String[] exceptions = {"maxisociety.com", "google.com", "youtube.com"};
            String[] announcements = {"Place as many as needed."};

            getConfig().addDefault("curse.words", Arrays.asList(defaultFilters));
            getConfig().addDefault("options.domains.exceptions", Arrays.asList(exceptions));
            getConfig().addDefault("curse.replacement", "boop");
            getConfig().addDefault("options.caps.sentenceTriggerSize", 10);
            getConfig().addDefault("options.caps.threshold", 75);
            getConfig().addDefault("autoannounce.entries", Arrays.asList(announcements));
            getConfig().addDefault("autoannounce.interval", 60);
            getConfig().addDefault("autoannouce.prefix", "&2[&aBlockArray&2] ");
            getConfig().addDefault("mysql.host", "localhost");
            getConfig().addDefault("mysql.username", "username");
            getConfig().addDefault("mysql.password", "password");
            getConfig().addDefault("mysql.database", "infinicontrol");
            getConfig().addDefault("mysql.table", "infinidata");
        }
        saveConfig();
    }
}
