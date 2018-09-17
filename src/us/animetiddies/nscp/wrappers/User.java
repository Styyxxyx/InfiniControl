package us.animetiddies.nscp.wrappers;

import org.bukkit.entity.Player;
import us.animetiddies.nscp.NSCP;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    Player player;

    public User(Player player) {
        this.player = player;
    }

    private ResultSet getUserData() {
        return NSCP.getNetwork().executeQuery("SELECT * FROM infinidata WHERE username = '" + player.getName() + "'");
    }

    private ResultSet getChatHistory() {
        return NSCP.getNetwork().executeQuery("SELECT * FROM chatlogs WHERE username = '" + player.getName() + "'");
    }

    ;

    private int getID() {
        try {
            return getUserData().getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getName() {
        return player.getName();
    }

    public int getInfractions() {
        try {
            return getUserData().getInt("infractions");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private void sendMessage(String message) {
        NSCP.getUtil().sendMessage(player, message);
    }

    public boolean addInfractions(int amount) {

        int totalWarings = getInfractions() + amount;

        String query = "UPDATE infinidata " +
                "SET infractions = " + totalWarings + " " +
                "WHERE id = " + getID();
        sendMessage("You have violated a rule and have been warned! You currently have &c" + getInfractions() + " &7infractions!");
        return NSCP.getNetwork().executeUpdate(query);
    }

    public void pardon() {
        String query = "UPDATE infinidata " +
                "SET infractions = 0 WHERE id = " + getID();
        NSCP.getNetwork().executeUpdate(query);
        sendMessage("You have been pardoned.");
    }

    public boolean logMessage(String message) {
        String query = null;
        query = "INSERT INTO chatlogs(username, messages) VALUES('" + getName() + "', ?)";
        try {
            PreparedStatement ps = NSCP.getNetwork().getConnection().prepareStatement(query);
            ps.setString(1, message);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
