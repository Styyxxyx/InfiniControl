package us.animetiddies.nscp.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Stream;

public class Util {

    private Plugin plugin;

    public Util(Plugin p) {
        this.plugin = p;
    }

    public void sendMessage(Object p, String message) {
        message = ChatColor.translateAlternateColorCodes('&', "&8[&6InfiniControl&8]:&7 " + message);
        if (p instanceof Player) {
            Player player = (Player) p;
            player.sendMessage(message);
        } else {
            plugin.getLogger().log(Level.SEVERE, this.colorString(message, '&'));
        }
    }

    public void asyncKick(final Player p, final String message, Plugin plugin) {
        Bukkit.getScheduler().runTask(plugin, () -> p.kickPlayer(ChatColor.translateAlternateColorCodes('&', message)));
    }

    public void createFile(String fileName, Plugin plugin) {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(plugin.getDataFolder() + "/" + fileName), "utf-8"));
            writer.write("warnings 0");
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, "Couldn't create player Data: " + ex.getMessage());
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
                plugin.getLogger().log(Level.SEVERE, "An error occured: " + ex.getMessage());
            }
        }
    }

    public boolean incrementWarnings(Player p, int amount) {
        List<String> newLines = new ArrayList<>();
        String fileName = plugin.getDataFolder() + "/" + p.getName();
        try {
            Object[] lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8).toArray();
            String line = lines[0].toString();
            int currentWarnings = Integer.parseInt(line.split(" ")[1]);
            int newWarnings = currentWarnings + amount;
            if (line.contains("warnings " + currentWarnings)) {
                line = line.replace("warnings " + currentWarnings, "warnings " + newWarnings);
                newLines.add(line);
            } else {
                newLines.add(line);
            }

            Files.write(Paths.get(fileName), newLines, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Reader readFile(String fileName, Plugin plugin) {
        Reader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(plugin.getDataFolder() + "/" + fileName)));
            return reader;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String readLineByLine(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    public String colorString(String s, char symbol) {
        return ChatColor.translateAlternateColorCodes(symbol, s);
    }

    public ArrayList<String> getLinesFromFile(String fileName, Plugin plugin) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(plugin.getDataFolder() + "/" + fileName));
            String line;

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
