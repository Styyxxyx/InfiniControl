package us.animetiddies.nscp.network;

import org.bukkit.Bukkit;
import us.animetiddies.nscp.NSCP;
import us.animetiddies.nscp.wrappers.User;

import java.sql.*;
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
            final int i = s.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE + " (id int NOT NULL AUTO_INCREMENT, username VARCHAR(40), infractions int, PRIMARY KEY (id))");
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

    public ResultSet getUserData(String username) {
        String query = "SELECT * FROM infinidata WHERE username = ?;";
        try {

            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, username);
            ResultSet res = ps.executeQuery();
            if (res.next())
                return res;
            else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createUserData(String username) {
        try {
            if (getUserData(username) == null) {
                String query = "INSERT INTO infinidata SET username = ?, infractions = ?";
                PreparedStatement ps = c.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, "0");
                ps.executeUpdate();
                return true;
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean executeUpdate(String query, User user) {
        try {
            PreparedStatement ps = c.prepareStatement(query);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }
    }

    public Connection getConnection() {
        return c;
    }

}
