package com.maxisociety.nscp.network;

import com.maxisociety.nscp.NSCP;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class Network {
    private static Connection c;
    private static String HOST;
    private static String DATABASE;
    private static String USERNAME;
    private static String PASSWORD;
    private static String TABLE;

    private String url;

    public Network() {
        HOST = NSCP.getOptions().getMYSQL_HOST();
        DATABASE = NSCP.getOptions().getMYSQL_DATABASE();
        USERNAME = NSCP.getOptions().getMYSQL_USERNAME();
        PASSWORD = NSCP.getOptions().getMYSQL_PASSWORD();
        TABLE = NSCP.getOptions().getMYSQL_TABLE();
        url = "jdbc:mysql://" + HOST + ":3306/";
    }

    public void init() {
        try {
            Statement s = c.createStatement();
            final int i = s.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE + " (id int, username VARCHAR(40), infractions int)");
            if (i > 0) {
                Bukkit.getLogger().log(Level.INFO, "Created table '" + TABLE + "' with the fields id: int, username VARCHAR(40), infractions int)");
            } else
                Bukkit.getLogger().log(Level.INFO, "Database is ready to go. Let's rock.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void connect() {
        try {
            c = DriverManager.getConnection(url + DATABASE, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return c;
    }

}
