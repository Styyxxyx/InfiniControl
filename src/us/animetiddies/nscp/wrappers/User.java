package us.animetiddies.nscp.wrappers;

import org.bukkit.entity.Player;
import us.animetiddies.nscp.NSCP;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    Player player;

    public User(Player player) {
        this.player = player;
    }

    private ResultSet getUserData() {
        return NSCP.getNetwork().getUserData(player.getName());
    }

    public int getID() {
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

    public void sendMessage(String message) {
        NSCP.getUtil().sendMessage(player, message);
    }

    public boolean addInfractions(int amount) {

        int totalWarings = getInfractions() + amount;

        String query = "UPDATE infinidata " +
                "SET infractions = " + totalWarings + " " +
                "WHERE id = " + getID();
        NSCP.getNetwork().executeUpdate(query, this);
        sendMessage("You have violated a rule and have been warned! You currently have &c" + getInfractions() + " &7infractions!");
        return false;
    }

    public void pardon() {
        String query = "UPDATE infinidata " +
                "SET infractions = 0 WHERE id = " + getID();
        NSCP.getNetwork().executeUpdate(query, this);
        sendMessage("You have been pardoned.");
    }
}
