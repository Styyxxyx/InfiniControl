package com.maxisociety.nscp.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.plugin.Plugin;

public class Options {
	public boolean FILTER_UNICODE;
	public boolean FILTER_CAPSLOCK;
	public boolean FILTER_PROFANITY;
	public boolean ENABLE_SPAM_PROTECTION;
	public boolean FILTER_DOMAINS;
	Plugin plugin;

	private Random rand = new Random();

	public Options(Plugin plugin) {
		this.plugin = plugin;
	}

	public void init() {
		FILTER_UNICODE = plugin.getConfig()
				.getBoolean("options.filter.unicode");
		FILTER_CAPSLOCK = plugin.getConfig().getBoolean("options.filter.caps");
		FILTER_PROFANITY = plugin.getConfig().getBoolean(
				"options.filter.profanity");
		ENABLE_SPAM_PROTECTION = plugin.getConfig().getBoolean(
				"options.filter.unicode");
		FILTER_DOMAINS = plugin.getConfig()
				.getBoolean("options.filter.domains");
	}

	public List<String> getCurseWords() {
		List<String> curseWords = plugin.getConfig().getStringList(
				"curse.words");

		List<String> fixedCurseWords = new ArrayList<String>();

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

	public int getRandNum() {
		return rand.nextInt(getAnnouncements().size());
	}

	public String getRandEntry() {
		return (String) getAnnouncements().get(getRandNum());
	}

	public List<?> getAnnouncements() {
		return plugin.getConfig().getList("autoannounce.entries");
	}

	public int getCapsThreshold() {
		return plugin.getConfig().getInt("options.caps.threshold");
	}

	public int getCapstrigger() {
		return plugin.getConfig().getInt("options.caps.sentenceTriggerSize");
	}

	public String getReplacementForAds() {
		return plugin.getConfig().getString(
				"options.domains.advertisement.replaceWith");
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
}
