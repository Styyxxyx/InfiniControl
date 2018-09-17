package us.animetiddies.nscp.util;

import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Options {

    private boolean FILTER_UNICODE;
    private boolean FILTER_CAPSLOCK;
    private boolean FILTER_PROFANITY;
    private boolean ENABLE_SPAM_PROTECTION;
    private boolean FILTER_DOMAINS;
    private String MYSQL_USERNAME;
    private String MYSQL_PASSWORD;
    private String MYSQL_DATABASE;
    private String MYSQL_TABLE;
    private String MYSQL_HOST;
    private Plugin plugin;
    private Random rand = new Random();

    public Options(Plugin plugin) {
        this.plugin = plugin;
    }

    public void init() {
        FILTER_UNICODE = plugin.getConfig().getBoolean("options.filter.unicode");
        FILTER_CAPSLOCK = plugin.getConfig().getBoolean("options.filter.caps");
        FILTER_PROFANITY = plugin.getConfig().getBoolean("options.filter.profanity");
        ENABLE_SPAM_PROTECTION = plugin.getConfig().getBoolean("options.filter.unicode");
        FILTER_DOMAINS = plugin.getConfig().getBoolean("options.filter.domains");
        MYSQL_HOST = plugin.getConfig().getString("mysql.host");
        MYSQL_USERNAME = plugin.getConfig().getString("mysql.username");
        MYSQL_PASSWORD = plugin.getConfig().getString("mysql.password");
        MYSQL_DATABASE = plugin.getConfig().getString("mysql.database");
        MYSQL_TABLE = plugin.getConfig().getString("mysql.table");
    }

    public List<String> getCurseWords() {
        List<String> curseWords = plugin.getConfig().getStringList("curse.words");

        List<String> fixedCurseWords = new ArrayList<>();

        for (String s : curseWords) {
            fixedCurseWords.add(s.toLowerCase());
        }
        return fixedCurseWords;
    }

    public String getReplacement() {
        return plugin.getConfig().getString("curse.replacement");
    }

    public int getDelay() {
        return plugin.getConfig().getInt("autoannounce.interval");
    }

    private int getRandNum() {
        return rand.nextInt(getAnnouncements().size());
    }

    public String getRandEntry() {
        return (String) getAnnouncements().get(getRandNum());
    }

    private List<?> getAnnouncements() {
        return plugin.getConfig().getList("autoannounce.entries");
    }

    public int getCapsThreshold() {
        return plugin.getConfig().getInt("options.caps.threshold");
    }

    public int getCapstrigger() {
        return plugin.getConfig().getInt("options.caps.sentenceTriggerSize");
    }

    public String getReplacementForAds() {
        return plugin.getConfig().getString("options.domains.advertisement.replaceWith");
    }

    public boolean kickOnAdvertise() {
        return plugin.getConfig().getBoolean("options.domains.kickOnAdvertise");
    }

    public String getKickMessage() {
        return plugin.getConfig().getString("options.domains.kickMessage");
    }

    public List<String> getURLExceptions() {
        return plugin.getConfig().getStringList("options.domains.exceptions");
    }

    public String getPrefix() {
        return plugin.getConfig().getString("autoannouce.prefix");
    }

    public boolean filterUnicode() {
        return FILTER_UNICODE;
    }

    public boolean filterCapsLock() {
        return FILTER_CAPSLOCK;
    }

    public boolean enableProfanityFilter() {
        return FILTER_PROFANITY;
    }

    public boolean enableSpamProtection() {
        return ENABLE_SPAM_PROTECTION;
    }

    public boolean filterDomains() {
        return FILTER_DOMAINS;
    }

    public String getMYSQL_USERNAME() {
        return MYSQL_USERNAME;
    }

    public String getMYSQL_PASSWORD() {
        return MYSQL_PASSWORD;
    }

    public String getMYSQL_DATABASE() {
        return MYSQL_DATABASE;
    }

    public String getMYSQL_TABLE() {
        return MYSQL_TABLE;
    }

    public String getMYSQL_HOST() {
        return MYSQL_HOST;
    }
}
