package com.maxisociety.nscp.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.maxisociety.nscp.NSCP;

public class Util {

	static NSCP nscp = new NSCP();

	public static void sendMessage(Player p, String message) {
		message = ChatColor.translateAlternateColorCodes('&',
				"&8[&6InfiniControl&8]:&7 " + message);
		p.sendMessage(message);
	}

	public static void asyncKick(final Player p, final String message, Plugin plugin) {
		Bukkit.getScheduler().runTask(plugin, new Runnable() {
			public void run() {
				p.kickPlayer(ChatColor.translateAlternateColorCodes('&',
						message));
			}
		});
	}

	public static void createFile(String fileName, Plugin plugin) {
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(plugin.getDataFolder() + "/"
							+ fileName), "utf-8"));
			writer.write("warnings: 0");
		} catch (IOException ex) {
			plugin.getLogger().log(Level.SEVERE,
					"Couldn't create player Data: " + ex.getMessage());
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
				plugin.getLogger().log(Level.SEVERE,
						"An error occured: " + ex.getMessage());
			}
		}
	}

	public static ArrayList<String> getLinesFromFile(String fileName,
			Plugin plugin) {
		ArrayList<String> lines = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					plugin.getDataFolder() + "/" + fileName));
			String line;

			while ((line = br.readLine()) != null) {
				lines.add(line);
				continue;
			}
			br.close();
		} catch (IOException e) {
		}
		return lines;
	}
}
