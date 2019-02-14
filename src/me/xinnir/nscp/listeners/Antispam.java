package me.xinnir.nscp.listeners;

import me.xinnir.nscp.NSCP;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.util.Vector;

public class Antispam implements Listener {

    @EventHandler
    public void onBowShoot(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        boolean isTNT = false;

        boolean isBlock = false;
        boolean isEntity = false;

        Player p = (Player) event.getEntity();
        Material type = NSCP.bowGunBlock.get(p.getName());
        EntityType eType = NSCP.bowGunEntity.get(p.getName());

        Location entityLocation = event.getProjectile().getLocation();
        Vector vel = event.getProjectile().getVelocity();

        if (type != null) {
            isBlock = true;
        }
        if (eType != null) {
            isEntity = true;
        }
        if (isBlock) {
            if (type.equals(Material.TNT))
                isTNT = true;
        }

        World world = event.getEntity().getWorld();
        if (isBlock) {
            byte bytes = 0x0;

            if (isTNT) {

                Entity e = world.spawnEntity(entityLocation,
                        EntityType.PRIMED_TNT);

                event.getProjectile().remove();

                e.setVelocity(vel);
            } else {

                if (isTNT) {

                    Entity e = world.spawnEntity(entityLocation,
                            EntityType.PRIMED_TNT);

                    event.getProjectile().remove();

                    e.setVelocity(vel);
                } else {
                    FallingBlock e = world.spawnFallingBlock(entityLocation,
                            type, bytes);

                    event.getProjectile().remove();

                    e.setVelocity(vel);
                }
            }
        }
        if (isEntity) {
            Entity e = world.spawnEntity(entityLocation, eType);
            event.getProjectile().remove();

            e.setVelocity(vel);
        }
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSpawn(CreatureSpawnEvent event) {
        if (event.getEntity() instanceof IronGolem) {
            event.setCancelled(true);
        }
    }
}
