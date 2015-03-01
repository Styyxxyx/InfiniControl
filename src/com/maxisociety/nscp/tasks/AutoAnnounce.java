package com.maxisociety.nscp.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.maxisociety.nscp.NSCP;

public class AutoAnnounce implements Runnable {
	public void run() {
		Bukkit.getServer().broadcastMessage(
				ChatColor.translateAlternateColorCodes('&', NSCP.getOptions().getPrefix() + NSCP.getOptions()
						.getRandEntry()));
	}
}
