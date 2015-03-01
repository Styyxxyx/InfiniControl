package com.maxisociety.nscp.tasks;

import com.maxisociety.nscp.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.maxisociety.nscp.NSCP;

public class AutoAnnounce implements Runnable {
	public void run() {
		Bukkit.getServer().broadcastMessage(
                Util.colorString(NSCP.getOptions().getPrefix() + NSCP.getOptions()
						.getRandEntry(), '&'));
	}
}
