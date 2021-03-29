package com.blackmc.blackguilds.FactionsSync;

import com.blackmc.blackguilds.App;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class FacKillSync implements Listener {
 
    public FacKillSync(App plugin) {

        Bukkit.getPluginManager().registerEvents(this, plugin);

    }

    @EventHandler
    public void onKill(EntityDeathEvent e) {

        if(e.getEntity() instanceof Player) {

            Player p = (Player) e.getEntity();
            FPlayer fp = FPlayers.getInstance().getByPlayer(p);

            Bukkit.broadcastMessage("Siemka, wlasnie zabiles chlopa, gratuluję gróbasie");

            fp.alterPower(fp.getPower() + 2);

        }

    }

}