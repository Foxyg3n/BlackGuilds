package com.blackmc.blackguilds.Raid;

import com.blackmc.blackguilds.App;
import com.blackmc.blackguilds.Commands.Raid;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class RaidEnd implements Listener {

    Raid raid;
    App plugin;

    FPlayers fPlayers = FPlayers.getInstance();

    public RaidEnd(App plugin) {

        Bukkit.getPluginManager().registerEvents(this, plugin);
        raid = new Raid(plugin);

    }

    @EventHandler
    public void onBossDeath(EntityDeathEvent e) {

        Entity boss = e.getEntity();

        if (boss.getName().toString().equals("\u00a74\u00a7lBOSS BRUTAL") && raid.raidStarted()) {

            Bukkit.getServer().broadcastMessage(boss.getCustomName().toString());

            Player p = e.getEntity().getKiller();
            FPlayer fp = fPlayers.getByPlayer(p);

            raid.stopRaid(fp, "win");

        }

    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {

        Player p = e.getEntity();
        FPlayer fp = fPlayers.getByPlayer(p);

        if(inRange(p, 73, 178, -472, 317, 127, -681) && raid.raidStarted()) {

            raid.raidPlayers.get(fp.getFaction().getTag()).remove(fp);
            if(raid.raidPlayers.get(fp.getFaction().getTag()).size() == 0) raid.stopRaid(fp, "lose");

        }

    }

    public boolean inRange(Player player, double x1, double y1, double z1, double x2, double y2, double z2) {

        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();

        double placeholder;

        if(x1 > x2) {
            placeholder = x1;
            x1 = x2;
            x2 = placeholder;
        }
        if(y1 > y2) {
            placeholder = y1;
            y1 = y2;
            y2 = placeholder;
        }
        if(z1 > z2) {
            placeholder = z1;
            z1 = z2;
            z2 = placeholder;
        }

        if(x > x1 && y > y1 && z > z1 && x < x2 && y < y2 && z < z2) return true;

        return false;

    }

}