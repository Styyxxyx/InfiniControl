package com.maxisociety.nscp.util;

import org.bukkit.entity.Player;

/**
 * Created by maverick on 12/24/13.
 */
public class User {
    Player player;

    public User(Player player) {
        this.player = player;
    }

    public int getInfractions() {
        return 0;
    }
}
